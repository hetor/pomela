#!/bin/bash

CURRENT_USER="tao.he"

function doCustom() {
    IP="106.2.33.38"
    [ "$1" == "pay" ] && APP="8191" || APP="8182"

    grep -e '[0-9]\{25\}' data/orderIds.txt | sed 's/[^0-9]/|/g' | sed 's/|[0-9]\{1,24\}|/|/g' | sed 's/|[0-9]\{26,\}|/|/g' | sed 's/|/\n/g' | grep -v '^$' | sort | uniq > data/orderIds.tmp
    ORDER_NUM=`grep -v '^$' data/orderIds.tmp | wc -l`

    for orderId in `cat data/orderIds.tmp`
    do
        curl "http://$IP:$APP/manualinvoke/acquireCustoms?orderId=$orderId"
        #echo "http://$IP:$APP/manualinvoke/acquireCustoms?orderId=$orderId"
    done

ssh $CURRENT_USER@$IP -p 1046 <<-!
    cd /home/appops/haitaoTest/pre3/tomcat-haitao-pay-Ins1/logs
    tail -$ORDER_NUM PayLog.log
!
}

function doRefund() {
    ORDER_OUT_ID="$1"
    
	mysql -uhaitao_read -pOfX5LtGcI3Yad1 -h10.120.154.154 -P6000 haitao-mirror <<-EOF 1>result.t 2>err.t
	    select id,out_id,user_id,user_name,create_time,update_time,deliver_time,pay_time,succ_time,close_time,pre_expire_time,origin_goods_total_amount,origin_pay_total_amount,pay_total_amount,\
	    tax_amount,coupon_amount,other_service_fee,express_china_fee,express_oversea_fee,cost_amount,state,trade_id,logistic_info_id,deliver_date_type,deliver_expect_time,customs_place,db_update_time,\
	    international_cost_amount,transit_cost_amount,commission_cost_amount,pay_method,hqg_extra_fee,real_pay_method_order_id\
	    from orders where out_id = '$ORDER_OUT_ID';
	
	    select id,out_id,user_id,user_name,trade_id,trade_out_id,order_id,order_out_id,order_item_id,order_item_out_id,create_time,update_time,transfer_time,amount,state,pay_method,account_out,\
	    account_in,note,type,from_type,sku_data,db_update_time,account_in_name\
	    from trade_refund_transfer where order_out_id='$ORDER_OUT_ID';
	
	    select id,order_id,gorder_id,user_name,user_id,create_time,update_time,close_time,pay_time,pre_expire_time,origin_total_amount,origin_pay_total_amount,pay_total_amount,coupon_amount,\
	    activity_amount,tax_amount,express_oversea_fee,express_china_fee,other_service_fee,cost_amount,coupon_id,state,real_pay_method,pay_method,invoice,wyb_order_id,description,hand_fee,\
	    entry_from,real_pay_method_order_id,pay_method_sub_type,db_update_time,owner_type,real_pay_method_account,is_merge_payment\
	    from trade where order_id = '$ORDER_OUT_ID';
	EOF

#ssh $CURRENT_USER@223.252.220.187 -p 1046 <<-!
#    cd /home/$CURRENT_USER/ddb4.5.7.2/scripts
#    ./haitaoMirror.sh
#    select id, pay_method, state, type, from_type, amount, FROM_UNIXTIME(transfer_time/1000,'%Y-%m-%d %h:%i:%s') as transfer_time, note, * from trade_refund_transfer \
#        where order_out_id='$ORDER_OUT_ID';
#    select pay_method, state from orders where out_id = '$ORDER_OUT_ID';
#    select real_pay_method_order_id, gorder_id, pay_method, pay_method_sub_type, state, is_merge_payment from trade where order_id = '$ORDER_OUT_ID';
#    exit;
#!
}

case ${1} in
  "-r")
       doRefund "${2}" 
       shift 2
       ;;
  "-c")
       doCustom "${2}"
       shift 2
       ;;
  *)
       echo "Usage ${0} {one|two|three}"
       ;;
esac

