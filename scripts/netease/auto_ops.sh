#!/bin/bash

CURRENT_USER="tao.he"

TEST_IP=(19 223.252.220.20 223.252.220.185 223.252.220.187 223.252.220.184 223.252.220.70 223.252.220.125 106.2.44.22 223.252.220.212 223.252.220.188 223.252.220.208 106.2.44.21 106.2.44.36 106.2.44.37 106.2.44.38 106.2.44.39 106.2.44.40 106.2.44.41 106.2.44.42 106.2.44.43)
INTEGRATION_IP=(1 223.252.220.198)
HOTFIX_IP=(1 223.252.220.183)
PRE_IP=(4 binjiang-haitao5.server.163.org binjiang-haitao13.server.163.org 106.2.33.38 106.2.33.4)
ONLINE_HAITAO_IP=(9 binjiang-haitao1 binjiang-haitao2 hzabj-haitao-haitao1 hzabj-haitao-haitao2 hzaxs-haitao-haitao1 hzaxs-haitao-haitao2 classa-haitao1 classa-haitao2 classa-haitao11)
ONLINE_PAY_IP=(4 hzabj-haitao-pay1 hzabj-haitao-pay2 hzaxs-haitao-pay1 hzaxs-haitao-pay2)


function doCustom() {
    IP="106.2.33.38"
    [ "$1" == "pay" ] && APP="8191" || APP="8182"

    grep -e '[0-9]\{25\}' orderIds.txt | sed 's/[^0-9]/|/g' | sed 's/|[0-9]\{1,24\}|/|/g' | sed 's/|[0-9]\{26,\}|/|/g' \
        | sed 's/|/\n/g' | grep -v '^$' | sort | uniq > orderIds.tmp

    ORDER_NUM=`grep -v '^$' orderIds.tmp | wc -l`

    for orderId in `cat orderIds.tmp`
    do
        #curl "http://$IP:$APP/manualinvoke/acquireCustoms?orderId=$orderId"
        echo "http://$IP:$APP/manualinvoke/acquireCustoms?orderId=$orderId"
    done

	ssh $CURRENT_USER@$IP -p 1046 <<-EOF
	    cd /home/appops/haitaoTest/pre3/tomcat-haitao-pay-Ins1/logs
	    tail -$ORDER_NUM PayLog.log
	EOF
}

function doRefund() {
    ORDER_OUT_ID="$1"
    echo $ORDER_OUT_ID

	mysql -uhaitao_read -pOfX5LtGcI3Yad1 -h10.120.154.154 -P6000 haitao-mirror <<-EOF 1>result.t 2>err.t
	    select id,out_id,user_id,user_name,create_time,update_time,deliver_time,pay_time,succ_time,close_time,pre_expire_time,\
	    origin_goods_total_amount,origin_pay_total_amount,pay_total_amount,tax_amount,coupon_amount,other_service_fee,\
	    express_china_fee,express_oversea_fee,cost_amount,state,trade_id,logistic_info_id,deliver_date_type,deliver_expect_time,\
	    customs_place,db_update_time,international_cost_amount,transit_cost_amount,commission_cost_amount,pay_method,hqg_extra_fee,\
	    real_pay_method_order_id from orders where out_id = '$ORDER_OUT_ID';
	
	    select id,out_id,user_id,user_name,trade_id,trade_out_id,order_id,order_out_id,order_item_id,order_item_out_id,create_time,\
	    update_time,transfer_time,amount,state,pay_method,account_out,account_in,note,type,from_type,sku_data,db_update_time,\
	    account_in_name from trade_refund_transfer where order_out_id='$ORDER_OUT_ID';
	
	    select id,order_id,gorder_id,user_name,user_id,create_time,update_time,close_time,pay_time,pre_expire_time,origin_total_amount,\
	    origin_pay_total_amount,pay_total_amount,coupon_amount,activity_amount,tax_amount,express_oversea_fee,express_china_fee,\
	    other_service_fee,cost_amount,coupon_id,state,real_pay_method,pay_method,invoice,wyb_order_id,description,hand_fee,entry_from,\
	    real_pay_method_order_id,pay_method_sub_type,db_update_time,owner_type,real_pay_method_account,is_merge_payment from trade where order_id = '$ORDER_OUT_ID';

	    select * from retry_task where search_key = '$ORDER_OUT_ID';
	EOF

    printf "%10s %10s %10s %10s %10s %10s %10s",  "order_id", "transfer_time", "ÍË¿î×´Ì¬", "pay_method", "real_pay_method_order_id", "is_merge_payment", "pay_method_sub_type", "¶©µ¥×´Ì¬"

    sed 's/\s/|/g' result.t | sed 's/|/ /g' > result.t

    cat result.t | awk '\
        NR==2{printf "%10s %10s %10s %10s",$1,$2,$3,$4}\
        NR==4{printf "%10s %10s %10s %10s",$1,$2,$3,$4}\
        NR==6{printf "%10s %10s %10s %10s\n",$1,$2,$3,$4}'
}

function ssh2Test() {
    echo "log-- ssh $CURRENT_USER@${TEST_IP[$1]} -p 1046"
    ssh $CURRENT_USER@${TEST_IP[$1]} -p 1046
}

function ssh2Pre() {
    echo "log-- ssh $CURRENT_USER@${PRE_IP[$1]} -p 1046"
    ssh $CURRENT_USER@${PRE_IP[$1]} -p 1046
}

function ssh2Hotfix() {
    echo "log-- ssh $CURRENT_USER@${HOTFIX_IP[$1]} -p 1046"
    ssh $CURRENT_USER@${HOTFIX_IP[$1]} -p 1046
}

function ssh2Integration() {
    echo "log-- ssh $CURRENT_USER@${INTEGRATION_IP[$1]} -p 1046"
    ssh $CURRENT_USER@${INTEGRATION_IP[$1]} -p 1046
}

function ssh2OnlineHaitao() {
   echo "log-- ssh ${ONLINE_HAITAO_IP[$1]}"
   ssh ${ONLINE_HAITAO_IP[$1]}
}

function ssh2OnlinePay() {
   echo "log-- ssh ${ONLINE_PAY_IP[$1]}"
   ssh ${ONLINE_PAY_IP[$1]}
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
  "-s")
      case ${2} in
          "-t")
              ssh2Test "${3}"
              shift 3
              ;;
          "-p")
              ssh2Pre "${3}"
              shift 3
              ;;
          "-h")
              ssh2Hotfix "1"
              shift 2
              ;;
          "-i")
              ssh2Integration "1"
              shift 2
              ;;
          "-o")
              case ${3} in
                  "-h")
                      ssh2OnlineHaitao "${4}"
                      shift 4
                      ;;
                  "-p")
                      ssh2OnlinePay "${4}"
                      shift 4
                      ;;
                  *)
                      echo "Usage {-s -o -h|-p}"
                      ;;
               esac
              ;;
          *)
              echo "Usage {-s -t|-p|-h|-i|-o}"
              ;;
      esac
      ;;
  *)
      echo "Usage {-r|-c|-s}"
      ;;
esac
