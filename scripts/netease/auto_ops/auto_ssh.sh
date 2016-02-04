#!/bin/bash

#Author: tao.he
#Date: 2015-12-27
#Desciption: ssh to test/hotfix/integration/pre/online(haitao, haitao-pay, haitao-dw) environment.

CURRENT_USER="tao.he"
TEST_IP=(19 223.252.220.20 223.252.220.185 223.252.220.187 223.252.220.184 223.252.220.70 223.252.220.125 106.2.44.22 223.252.220.212 223.252.220.188 223.252.220.208 106.2.44.21 106.2.44.36 106.2.44.37 106.2.44.38 106.2.44.39 106.2.44.40 106.2.44.41 106.2.44.42 106.2.44.43)
INTEGRATION_IP=(1 223.252.220.198)
HOTFIX_IP=(1 223.252.220.183)
PRE_IP=(5 binjiang-haitao5 classa-haitao44 106.2.33.38 106.2.33.4 binjiang-haitao13) #binjiang-haitao13 is also pre2
ONLINE_HAITAO_IP=(9 binjiang-haitao1 binjiang-haitao2 hzabj-haitao-haitao1 hzabj-haitao-haitao2 hzaxs-haitao-haitao1 hzaxs-haitao-haitao2 classa-haitao1 classa-haitao2 classa-haitao11)
ONLINE_PAY_IP=(4 hzabj-haitao-pay1 hzabj-haitao-pay2 hzaxs-haitao-pay1 hzaxs-haitao-pay2)
ONLINE_DW_IP=(2 classa-haitao24 classa-haitao25)
DDB_IP=(1 hzbxs-haitao33)


function ssh_test() {
    echo "log-- ssh $CURRENT_USER@${TEST_IP[${1}]} -p 1046"
    test ${#TEST_IP[@]} -gt ${1} && ssh $CURRENT_USER@${TEST_IP[${1}]} -p 1046
}

function ssh_pre() {
    echo "log-- ssh $CURRENT_USER@${PRE_IP[${1}]} -p 1046"
    test ${#PRE_IP[@]} -gt ${1} && ssh $CURRENT_USER@${PRE_IP[${1}]} -p 1046
}

function ssh_hotfix() {
    echo "log-- ssh $CURRENT_USER@${HOTFIX_IP[${1}]} -p 1046"
    test ${#HOTFIX_IP[@]} -gt ${1} && ssh $CURRENT_USER@${HOTFIX_IP[${1}]} -p 1046
}

function ssh_integration() {
    echo "log-- ssh $CURRENT_USER@${INTEGRATION_IP[${1}]} -p 1046"
    test ${#INTEGRATION_IP[@]} -gt ${1} && ssh $CURRENT_USER@${INTEGRATION_IP[${1}]} -p 1046
}

function ssh_online_haitao() {
    echo "log-- ssh ${ONLINE_HAITAO_IP[${1}]}"
    test ${#ONLINE_HAITAO_IP[@]} -gt ${1} && ssh ${ONLINE_HAITAO_IP[${1}]}
}

function ssh_online_pay() {
    echo "log-- ssh ${ONLINE_PAY_IP[${1}]}"
    test ${#ONLINE_PAY_IP[@]} -gt ${1} && ssh ${ONLINE_PAY_IP[${1}]}
}

function ssh_online_dw() {
    echo "log-- ssh ${ONLINE_DW_IP[${1}]}"
    test ${#ONLINE_DW_IP[@]} -gt ${1} && ssh ${ONLINE_DW_IP[${1}]}
}

function ssh_online_ddb() {
    echo "log-- ssh ${DDB_IP[1]}"
    test ${#DDB_IP[@]} -gt 1 && ssh ${DDB_IP[1]}
}


ENV='' #test,pre,hotfix,integration,online
MODULE='' #haitao,pay,dw,ddb
INDEX=0 #1,2,3...
ERR_MSG=`basename ${0}`
TIP="Usage: `basename ${0}` -e|--env env_params(t|p|h|i|o) [-m|--module module_params(ht|pay|dw|ddb)] [-i|--index index_params(1|2|3|...)]"
ARGS=`getopt -o e:m:i: --long env:,module:,index: -n "${ERR_MSG}" -- "$@"`

if [ $? != 0 ]; then
    echo ${TIP}
    exit 1
fi

eval `set -- "${ARGS}"`

#echo '$ARGS: '$ARGS
#echo '$@: '$@

while true
do
    case "${1}" in
        -e|--env)
            if [ "${2}" == "t" ] || [ "${2}" == "test" ]; then
                ENV='test'
            elif [ "${2}" == "p" ] || [ "${2}" == "pre" ]; then
                ENV='pre'
            elif [ "${2}" == "h" ] || [ "${2}" == "hotfix" ]; then
                ENV='hotfix'
            elif [ "${2}" == "i" ] || [ "${2}" == "integration" ]; then
                ENV='integration'
            elif [ "${2}" == "o" ] || [ "${2}" == "online" ]; then
                ENV='online'
            else
                echo ${TIP}
                exit 1
            fi
            shift 2
            ;;
        -m|--module)
            if [ "${2}" == "ht" ] || [ "${2}" == "haitao" ]; then
                MODULE='haitao'
            elif [ "${2}" == "pay" ] || [ "${2}" == "haitao-pay" ]; then
                MODULE='pay'
            elif [ "${2}" == "dw" ] || [ "${2}" == "haitao-dw" ]; then
                MODULE='dw'
            elif [ "${2}" == "ddb" ] || [ "${2}" == "DDB" ]; then
                MODULE='ddb'
            else
                echo ${TIP}
                exit 1
             fi
            shift 2
            ;;
        -i|--index)
            INDEX=${2}
            shift 2
            ;;
        --)
            shift
            break
            ;;
        *)
            shift
            break
            ;;
    esac
done

if [ "${ENV}" == "test" ]; then
    ssh_${ENV} ${INDEX}
elif [ "${ENV}" == "pre" ]; then
    ssh_${ENV} ${INDEX}
elif [ "${ENV}" == "hotfix" ]; then
    ssh_${ENV} 1
elif [ "${ENV}" == "integration" ]; then
    ssh_${ENV} 1
elif [ "${ENV}" == "online" ]; then
    ssh_${ENV}_${MODULE} ${INDEX}
else
    echo ${TIP}
    exit 1
fi
