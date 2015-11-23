#!/bin/sh

CURRENT_USER="tao.he"
ORDER_OUT_ID="2015112319282011000405166"

ssh $CURRENT_USER@223.252.220.187 -p 1046 <<-!
cd /home/$CURRENT_USER/ddb4.5.7.2/scripts
./haitaoMirror.sh
select pay_method, state, transfer_time from trade_refund_transfer where order_out_id='$ORDER_OUT_ID';
select out_id, pay_method, state from orders where out_id = '$ORDER_OUT_ID';
select real_pay_method_order_id, pay_method, state, * from trade where order_id = '$ORDER_OUT_ID';
exit;
!

