#!/bin/bash

#Author: tao.he
#Date: 2016-08-27
#Description: hosts切换
#Example:
    #./hosts_switch.sh <env>


HOSTS_STORE_DIR='/etc/hosts_store' # hosts文件存放的目录
HOSTS_FILE_NAME="${1}" # hosts文件名
is_invalid_param=false

test -z ${HOSTS_FILE_NAME} && is_invalid_param=true #不传参数默认使用online
if [ $is_invalid_param == true ]; then
   echo -n "usage hosts: "
   echo `basename ${HOSTS_STORE_DIR}/*`
   #for file in ${HOSTS_STORE_DIR}/*; do
   #    file_name=`basename $file`
   #    echo $file_name
   #done 
   exit 1
fi

test ! -e "${HOSTS_STORE_DIR}/${HOSTS_FILE_NAME}" && echo "file '${HOSTS_STORE_DIR}/${HOSTS_FILE_NAME}' is not exist" && exit 0

sudo cp ${HOSTS_STORE_DIR}/${HOSTS_FILE_NAME} /etc/hosts && echo "hosts has switched to '${HOSTS_FILE_NAME}'" && exit 0
