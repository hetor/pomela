#!/bin/bash
# Program:
#   User input a filename, program will check the flowing:
#   1.) exist? 2.) file/directory? 3.) file permissions 
# History:
# 2015/07/16    VBird   First release
# 其它选项查询test相关资料
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

# 1. 讓使用者輸入檔名，並且判斷使用者是否真的有輸入字串？
echo -e "Please input a filename, I will check the filename's type and permission. \n\n"
read -p "Input a filename : " filename
test -z ${filename} && echo "You MUST input a filename." && exit 0
# 2. 判斷檔案是否存在？若不存在則顯示訊息並結束腳本
test ! -e ${filename} && echo "The filename '${filename}' DO NOT exist" && exit 0
# 3. 開始判斷檔案類型與屬性
test -f ${filename} && filetype="regulare file"
test -d ${filename} && filetype="directory"
test -r ${filename} && perm="readable"
test -w ${filename} && perm="${perm} writable"
test -x ${filename} && perm="${perm} executable"
# 4. 開始輸出資訊！
echo "The filename: ${filename} is a ${filetype}"
echo "And the permissions for you are : ${perm}"

read -p "please input (Y/N):" yn

# ["${yn}" == "Y"] && ["${yn1}" == "Y"]
if [ "${yn}" == "Y" ] || [ "${yn}" == "y" ]; then
    echo "OK, continue"
    exit 0
fi

# [ "${yn}" == "N" -a "$[yn1]" == "N"]
if [ "${yn}" == "N" -o "${yn}" == "n" ]; then
    echo "Oh, interrupt!"
    exit 0
fi

read -p "Please input (Y/N): " yn

if [ "${yn}" == "Y" ] || [ "${yn}" == "y" ]; then
    echo "OK, continue"
elif [ "${yn}" == "N" ] || [ "${yn}" == "n" ]; then
    echo "Oh, interrupt!"
else
    echo "I don't know what your choice is"
fi
            
