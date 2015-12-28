#!/bin/bash

#Author: tao.he
#Date: 2015-12-27
#Desciption: 支付宝手动推送支付流
#Example: 
	# ./auto_custom.sh -o <order_out_id>

export LANG=zh_CN.UTF-8

CURRENT_USER="tao.he"
IP="106.2.33.38"
ENV="pre3"
TOMCAT_PATH="tomcat-haitao-pay-Ins1"

PAY_PORT="8191"
HAITAO_PORT="8182"

function custom_file() {
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

	ssh $CURRENT_USER@$IP -p 1046 <<-EOF cd /home/appops/haitaoTest/pre3/tomcat-haitao-pay-Ins1/logs tail -$ORDER_NUM PayLog.log
	EOF
}

function custom_order_ids() {
	ORDER_OUT_ID="${1}"

	mysql -uhaitao_read -pOfX5LtGcI3Yad1 -h 10.120.154.154 -P 6000 haitao-mirror <<-EOF 1>result.tmp 2>err.tmp
		select is_merge_payment from trade where order_id = '$ORDER_OUT_ID';
	EOF

	IS_MERGE_PAYMENT=`cat result.tmp | awk 'NR==2{print $1}'`
	
	if [ "${IS_MERGE_PAYMENT}" == "0" ]; then
		TOMCAT_PATH="tomcat-haitao-Ins1"
		echo "http://$IP:$HAITAO_PORT/manualinvoke/acquireCustoms?orderId=$ORDER_OUT_ID"
		curl "http://$IP:$HAITAO_PORT/manualinvoke/acquireCustoms?orderId=$ORDER_OUT_ID"
	else
		TOMCAT_PATH="tomcat-haitao-pay-Ins1"
		echo "http://$IP:$PAY_PORT/manualinvoke/acquireCustoms?orderId=$ORDER_OUT_ID"
		curl "http://$IP:$PAY_PORT/manualinvoke/acquireCustoms?orderId=$ORDER_OUT_ID"
	fi

	ssh $CURRENT_USER@$IP -p 1046 <<-EOF
		cd /home/appops/haitaoTest/${ENV}/${TOMCAT_PATH}/logs
		cat PayLog.log | grep --color=auto '$ORDER_OUT_ID'
	EOF
}



case ${1} in
    "-f")
	    custom_file "${2}" 
	    #shift 2
	    ;;
    "-o")
	    custom_order_ids "${2}"
	    #shift 2
	    ;;
    *)
	    echo "Usage {-f filename | -o orderIds}"
	    ;;
esac
