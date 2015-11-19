#!/bin/sh

printf '%s\t %s\t %s\ t %s\t %s\t \n' $(cat printf.txt)

printf '%10s %5i %5i %5i %8.2f \n' $(cat printf.txt | grep -v Name)


#关于格式方面的几个特殊样式：
#       \a警告声音输出
#       \b倒退键(backspace)
#       \f清除萤幕(form feed)
#       \n输出新的一行
#       \r亦即Enter按键
#       \t水平的[tab]按键
#       \v垂直的[tab]按键
#       \xNN NN为两位数的数字，可以转换数字成为字元。
#关于C程式语言内，常见的变数格式
#       %ns那个n是数字， s代表string ，亦即多少个字元；
#       %ni那个n是数字， i代表integer ，亦即多少整数位数；
#       %N.nf那个n与N都是数字， f代表floating (浮点)，如果有小数位数，假设我共要十个位数，但小数点有两位，即为%10.2f啰！
