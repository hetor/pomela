#!/bin/bash

CURRENT_USER='tao.he'

ssh $CURRENT_USER@10.120.152.130 -p 1046 <<-!
cd /home/appops/
cd /home/appops
sh omni -T 200
!


