#!/bin/bash

CURRENT_USER="tao.he"

TEST_IP=(19 223.252.220.20 223.252.220.185 223.252.220.187 223.252.220.184 223.252.220.70 223.252.220.125 106.2.44.22 223.252.220.212 223.252.220.188 223.252.220.208 106.2.44.21 106.2.44.36 106.2.44.37 106.2.44.38 106.2.44.39 106.2.44.40 106.2.44.41 106.2.44.42 106.2.44.43)
INTEGRATION_IP=(1 223.252.220.198)
HOTFIX_IP=(1 223.252.220.183)
PRE_IP=(4 binjiang-haitao5 binjiang-haitao13 106.2.33.38 106.2.33.4)
ONLINE_HAITAO_IP=(9 binjiang-haitao1 binjiang-haitao2 hzabj-haitao-haitao1 hzabj-haitao-haitao2 hzaxs-haitao-haitao1 hzaxs-haitao-haitao2 classa-haitao1 classa-haitao2 classa-haitao11)
ONLINE_PAY_IP=(4 hzabj-haitao-pay1 hzabj-haitao-pay2 hzaxs-haitao-pay1 hzaxs-haitao-pay2)
ONLINE_DW_IP=(2 classa-haitao24 classa-haitao25)


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

function ssh2OnlineDw() {
    echo "log-- ssh ${ONLINE_DW_IP[$1]}"
    ssh ${ONLINE_DW_IP[$1]}
}

case ${1} in
    "-t")
        ssh2Test "${2}"
        ;;
    "-p")
        ssh2Pre "${2}"
        ;;
    "-h")
        ssh2Hotfix "1"
        ;;
    "-i")
        ssh2Integration "1"
        ;;
    "-o")
        case ${2} in
            "-h")
                ssh2OnlineHaitao "${3}"
                ;;
            "-p")
                ssh2OnlinePay "${3}"
                ;;
            "-d")
                ssh2OnlineDw "${3}"
                ;;
            *)
                echo "Usage {-o -h|-p|-d}"
                ;;
         esac
        ;;
    *)
        echo "Usage {-t|-p|-h|-i|-o}"
        ;;
esac
