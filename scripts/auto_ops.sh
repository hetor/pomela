#!/bin/sh

CURRENT_USER="tao.he"
ORDER_OUT_ID="2015102315183211024878901"

ssh $CURRENT_USER@223.252.220.187 -p 1046 <<-!
cd /home/$CURRENT_USER/ddb4.5.7.2/scripts
./haitaoMirror.sh
select id, pay_method, state, type, from_type, amount, transfer_time, note, * from trade_refund_transfer where order_out_id='$ORDER_OUT_ID';
select pay_method, state from orders where out_id = '$ORDER_OUT_ID';
select real_pay_method_order_id, pay_method, state, is_merge_payment from trade where order_id = '$ORDER_OUT_ID';
exit;
!


# grep -e '[0-9]\{25\}' orderIds.l | sed 's/[^0-9]/|/g' | sed 's/|[0-9]\{,24\}|/|/g' | sed 's/|[0-9]\{26,\}|/|/g' | sed 's/|//g' > orders.tmp
