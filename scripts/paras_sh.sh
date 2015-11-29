#!/bin/bash
# Program:
#   Program shows the script name, parameters...
# History:
# 2015/07/16    VBird   First release

#$# ：代表後接的參數『個數』，以上表為例這裡顯示為『 4 』；
#$@ ：代表『 "$1" "$2" "$3" "$4" 』之意，每個變數是獨立的(用雙引號括起來), 代表数组；
#$* ：代表『 "$1c$2c$3c$4" 』，其中 c 為分隔字元，預設為空白鍵， 所以本例中代表『 "$1 $2 $3 $4" 』之意, 代表字符串。

PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

echo "The script name is        ==> ${0}"
echo "The 1st parameter         ==> ${1}"
echo "The 2nd parameter         ==> ${2}"
echo "Total parameter number is ==> $#"
[ "$#" -lt 2 ] && echo "The number of parameter is less than 2.  Stop here." && exit 0
echo "Your whole parameter is   ==> '$@'"
shift   # 進行第一次『一個變數的 shift 』
echo "Total parameter number is ==> $#"
echo "Your whole parameter is   ==> '$@'"
shift 3 # 進行第二次『三個變數的 shift 』
echo "Total parameter number is ==> $#"
echo "Your whole parameter is   ==> '$@'"

for arg in "$*"
do
    echo "arg: $arg" # 双引号内$arg是变量,单引号内$arg是字符串
done

echo

for arg in "$@"
do 
    echo "arg: $arg"
done
