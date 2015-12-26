#!/bin/bash
export LANG=zh_CN.UTF-8

PAY_HOME="/c/Users/tao.he/Code/haitao-pay"
DW_HOME="/c/Users/tao.he/Code/haitao-dw"
HAITAO_HOME="/c/Users/tao.he/Code/haitao"


#function pros_add() {
#    PROJECT_HOME=$1
#    for file in $(find $PROJECT_HOME/ -name 'pay.properties')
#    do
#        echo "File: ${file}"
#        #echo `grep 'wyb.alipay.account=neteasehkhqg@service.netease.com' ${file}`
#        #sed -i -n '/^wyb\.alipay\.account/d' ${file}
#        sed -i '/^wyb\.pay\.query\.pay\.realNameCheck\.url/a\
#\
#wyb.identify.pc.quick.identify=http://epay.163.com/quickpay_identify/view.htm\
#wyb.identify.wap.quick.identify=https://epay.163.com/wap/quickpay_identify/view.htm' ${file}
#    done
#
#}

function pros_add() {
    PROJECT_HOME=$1
    for file in $(find $PROJECT_HOME/ -name 'pay.properties')
    do
        echo "File: ${file}"
        sed -i 's/wyb.identify.pc.quick.identify=http/wyb.identify.pc.quick.identify=https/g' ${file}
    done

}

#function pros_add() {
#    PROJECT_HOME=$1
#    for file in $(find $PROJECT_HOME/ -name 'pay.properties')
#    do
#        echo "File: ${file}"
#        #echo `grep 'wyb.alipay.account=neteasehkhqg@service.netease.com' ${file}`
#        #sed -i -n '/^wyb\.alipay\.account/d' ${file}
#        sed -i '/2088101153102215/a\
#\
#alipay.domestic.hangzhou.youmai.pf.name.str=\\u676d\\u5dde\\u4f18\\u4e70\\u79d1\\u6280\\u6709\\u9650\\u516c\\u53f8\
#alipay.domestic.hangzhou.youmai.pf.account=neteaseyoumaipf@service.netease.com\
#alipay.domestic.hangzhou.youmai.pf.account.id=2088121421423585' ${file}
#    done
#
#}

case ${1} in
    "dw")
	pros_add "${DW_HOME}"
	shift 2
	;;
    "pay")
	pros_add "${PAY_HOME}"
	shift 2
	;;
    "ht") 
	pros_add "${HAITAO_HOME}"
	shift 2
	;;
    *)
	echo "Usage {pay|dw|ht}"
	;;
esac
