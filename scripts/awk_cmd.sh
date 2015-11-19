#!/bin/sh

last -5

# $0 代表一整行
last -5 | awk '{print $1 "\t" $3 }'


# NF 每一行($0) 拥有的栏位总数
# NR 目前awk 所处理的是『第几行』资料
# FS 目前的分隔字元，预设是空白键
last -5 | awk '{print $1 "\t lines: " NR "\t columns: " NF "\t split: " FS}'


#设置分隔字符为:, 输出$3列值小于10的行
cat /etc/passwd | awk 'BEGIN {FS=":"} $3 < 10 {print $1 "\t " $3}'


#awk动作{}, 也支持条件判断if
cat /etc/passwd | awk 'NR==1{printf "%10s %10s %10s %10s %10s\n",$1,$2,$3,$4,"Total" } NR>=2{total = $2 + $3 + $4;printf "%10s %10d %10d %10d %10.2f\n", $1, $2, $3, $4, total}' 
