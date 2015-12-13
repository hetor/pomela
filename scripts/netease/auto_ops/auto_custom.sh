#!/bin/bash
export LANG=zh_CN.UTF-8


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

	ssh $CURRENT_USER@$IP -p 1046 <<-EOF
	    cd /home/appops/haitaoTest/pre3/tomcat-haitao-pay-Ins1/logs
	    tail -$ORDER_NUM PayLog.log
	EOF
}

function custom_order_ids() {
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



case ${1} in
    "-f")
        custom_file "${2}"
        shift 2
        ;;
    "-o")
        custom_order_ids "${2}"
        shift 2
    *)
        echo "Usage {-f filename | -o orderIds}"
        ;;
esac