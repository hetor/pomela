#!/bin/bash
export LANG=zh_CN.UTF-8

PAY_HOME="/c/Users/tao.he/Code/haitao-pay"
DW_HOME="/c/Users/tao.he/Code/haitao-dw"
HAITAO_HOME="/c/Users/tao.he/Code/haitao"


#function pros_add() {
#    project_home=$1
#    for file in $(find $project_home/ -name 'pay.properties')
#    do
#        echo "file: ${file}"
#        sed -i '/^#---------- wyb configuation$/a\
#wyb.pay.url.domain.name=https://epay.163.com' ${file}
#    done
#
#}

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

#function pros_add() {
#    PROJECT_HOME=$1
#    for file in $(find $PROJECT_HOME/ -name 'pay.properties')
#    do
#        echo "File: ${file}"
#        sed -i 's/wyb.identify.pc.quick.identify=http/wyb.identify.pc.quick.identify=https/g' ${file}
#    done
#
#}

function pros_add() {
    PROJECT_HOME=$1
    for file in $(find $PROJECT_HOME/ -name 'pay.properties')
    do
        echo "File: ${file}"
	sed -i -n '/^hessian\.mm\.online.\url/d' ${file}
    done
}

#function pros_add() {
#    PROJECT_HOME=$1
#    for file in $(find $PROJECT_HOME/ -name 'pay.properties')
#    do
#        echo "File: ${file}"
#        sed -i '/wyb\.pay\.query\.pay\.realNameCheck\.url/a\
#wyb.pay.query.account.identity.status.url=https://epay.163.com/user_api/queryAccountIdentityStatus.htm' ${file}
#    done
#}

#function pros_add() {
#    PROJECT_HOME=$1
#    for file in $(find $PROJECT_HOME/ -name 'privateSettings.properties')
#    do
#        echo "File: ${file}"
#        sed -i '/ddb\.pass=/a\
#ddb.min_poolsize=5\
#ddb.max_poolsize=5\
#ddb.initial_poolsize=5\
#\
#ddb.url2=jdbc:mysql://10.120.152.132:6000/haitao-test?autoReconnect=true&failOverReadOnly=true\
#ddb.user2=haitao_read\
#ddb.pass2=haitao_read\
#ddb.min_poolsize_readonly=5\
#ddb.max_poolsize_readonly=5\
#ddb.initial_poolsize_readonly=5' ${file}
#    done
#}


function usage() {
    echo "Invalid option: -$OPTARG"
    echo "Usage: `basename $0` -m dw|pay|ht"
    exit 1
}

while getopts ":m:" arg
do
    case $arg in
        m)
	    if [ "$OPTARG" == "dw" ] || [ "$OPTARG" == "haitao-dw" ];then
	        pros_add "${DW_HOME}"
            elif [ "$OPTARG" == "pay" ] || [ "$OPTARG" == "haitao-pay" ];then
	        pros_add "${PAY_HOME}"
	    elif [ "$OPTARG" == "ht" ] || [ "$OPTARG" == "haitao" ];then
	        pros_add "${HAITAO_HOME}"
	    else
                usage
	    fi
	    ;;
	\?)
	    usage
	    ;;
	:)
	    echo "Miss option argument -$OPTARG dw|pay|ht"
	    ;;
    esac
done

#case ${1} in
#    "dw"|"haitao-dw")
#	pros_add "${DW_HOME}"
#	shift 2
#	;;
#    "pay"|"haitao-pay")
#	pros_add "${PAY_HOME}"
#	shift 2
#	;;
#    "ht"|"haitao") 
#	pros_add "${HAITAO_HOME}"
#	shift 2
#	;;
#    *)
#	echo "Usage {pay|haitao-pay|dw|haitao-dw|ht|haitao}"
#	;;
#esac
