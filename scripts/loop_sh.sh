#!/bin/bash
# Program:
#   Repeat question until user input correct answer.
# History:
# 2015/07/17    VBird   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

#注意${yn}外面必须要加""
while [ "${yn}" != "yes" -a "${yn}" != "YES" ]
do
    read -p "Please input yes/YES to stop this program: " yn
done
echo "OK! you input the correct answer."


until [ "${yn}" == "yes" -o "${yn}" == "YES" ]
do
    read -p "Please input yes/YES to stop this program: " yn
done
echo "OK! you input the correct answer."


s=0  # 這是加總的數值變數
i=0  # 這是累計的數值，亦即是 1, 2, 3....
while [ "${i}" != "100" ]
do
    i=$(($i+1))   # 每次 i 都會增加 1 
    s=$(($s+$i))  # 每次都會加總一次！
done
echo "The result of '1+2+3+...+100' is ==> $s"


network="192.168.1"              # 先定義一個網域的前面部分！
for sitenu in $(seq 1 100)       # seq 為 sequence(連續) 的縮寫之意
do
    # 底下的程式在取得 ping 的回傳值是正確的還是失敗的！
    ping -c 1 -w 1 ${network}.${sitenu} &> /dev/null && result=0 || result=1
    # 開始顯示結果是正確的啟動 (UP) 還是錯誤的沒有連通 (DOWN)
    if [ "${result}" == 0 ]; then
        echo "Server ${network}.${sitenu} is UP."
    else
        echo "Server ${network}.${sitenu} is DOWN."
    fi
done


read -p "Please input a number, I will count for 1+2+...+your_input: " nu

s=0
for (( i=1; i<=${nu}; i=i+1 ))
do
    s=$((${s}+${i}))
    echo $((${s}*${i}))
done
echo "The result of '1+2+3+...+${nu}' is ==> ${s}"

