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
