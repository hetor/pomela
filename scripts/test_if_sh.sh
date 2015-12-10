#!/bin/bash
# Program:
#   User input a filename, program will check the flowing:
#   1.) exist? 2.) file/directory? 3.) file permissions 
# History:
# 2015/07/16    VBird   First release
# 其它选项查询test相关资料
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

# test
echo -e "Please input a filename, I will check the filename's type and permission. \n\n"
read -p "Input a filename : " filename
read -p "Input a another filename : " filename1

#文件是否存在
test ! -e ${filename} && echo "The filename '${filename}' DO NOT exist" && exit 0
test ! -e ${filename1} && echo "The filename '${filename1}' DO NOT exist" && exit 0
#文件是否存在且类型侦测
test -f ${filename} && filetype="exist and regular file"
test -d ${filename} && filetype="directory"
test -L ${filename} && filetype="link file"
#文件是否存在且权限侦测
test -r ${filename} && perm="readable"
test -w ${filename} && perm="${perm} writable"
test -x ${filename} && perm="${perm} executable"
test -s ${filename} && perm="${perm} not empty"
#两个文件的比较
test ${filename} -nt ${filename1} && echo "${filename} newer than ${filename1}"
test ${filename} -ot ${filename1} && echo "${filename} older than ${filename1}"
test ${filename} -ef ${filename1} && echo "${filename} ${filename1} 是同一文件，可用在hard link的判断，主要意义在判断两个文件是否均指向同一个inode"

#两个整数的比较
echo -e "Please input two num. \n\n"
read -p "Input num1: " num1
read -p "Input num2: " num2

test ${num1} -eq ${num2} && echo "${num1} == ${num2}"
test ${num1} -ne ${num2} && echo "${num1} != ${num2}"
test ${num1} -gt ${num2} && echo "${num1} > ${num2}"
test ${num1} -lt ${num2} && echo "${num1} < ${num2}"
test ${num1} -ge ${num2} && echo "${num1} >= ${num2}"
test ${num1} -le ${num2} && echo "${num1} <= ${num2}"

#字符串判断
echo -e "Please input two str. \n\n"
read -p "Input str1: " str1
read -p "Input str2: " str2

test -z ${str1} && echo "string is empty"
test -n ${str1} && echo "string is not empty"
test ${str1} == ${str2} && echo "${str1} == ${str2}"
test ${str1} != ${str2} && echo "${str1} != ${str2}"

#多重判断
test -r ${filename} -a -x ${filename} && echo "${filename} is readable and executable"
test -r ${filename} -o -x ${filename} && echo "${filename} is readable or executable"
test ! -x ${filename} && echo "${filename} is not executable"

echo "The filename: ${filename} is a ${filetype}"
echo "And the permissions for you are : ${perm}"

#可以用[ ]代替test


# if
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
