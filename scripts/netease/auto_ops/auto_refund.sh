#!/bin/bash
export LANG=zh_CN.UTF-8

function doRefund() {
    ORDER_OUT_ID="$1"
    echo $ORDER_OUT_ID

	mysql -uhaitao_read -pOfX5LtGcI3Yad1 -h10.120.154.154 -P6000 haitao-mirror <<-EOF 1>result.tmp 2>err.tmp
            select state,trade_id,pay_method,hqg_extra_fee,real_pay_method_order_id,from_unixtime(deliver_time/1000) as deliver_time,from_unixtime(pay_time/1000) as pay_time,from_unixtime(succ_time/1000) as succ_time,from_unixtime(close_time/1000) as close_time from orders where out_id = '$ORDER_OUT_ID';

            select out_id,order_out_id,order_item_out_id,from_unixtime(create_time/1000) as create_time,from_unixtime(transfer_time/1000) as transfer_time,amount,state,pay_method,account_out,account_in,type,from_type from trade_refund_transfer where order_out_id='$ORDER_OUT_ID';

            select gorder_id,from_unixtime(create_time/1000) as create_time,from_unixtime(update_time/1000) as update_time,from_unixtime(close_time/1000) as close_time,from_unixtime(pay_time/1000) as pay_time,state,real_pay_method,pay_method,wyb_order_id,real_pay_method_order_id,pay_method_sub_type,owner_type,real_pay_method_account,is_merge_payment from trade where order_id = '$ORDER_OUT_ID';

	    select * from retry_task where search_key = '$ORDER_OUT_ID';
	EOF

    sed 's/\s/|/g' result.tmp | sed 's/|/ /g' > result.tmp

    cat result.tmp | awk '\
        NR==2{printf "%10s %10s %10s %10s",$1,$2,$3,$4}\
        NR==4{printf "%10s %10s %10s %10s",$1,$2,$3,$4}\
        NR==6{printf "%10s %10s %10s %10s\n",$1,$2,$3,$4}'
}


case ${1} in
    "-o")
        doRefund "${2}"
        shift 2
        ;;
    *)
        echo "Usage {-o orderId}"
        ;;
esac
