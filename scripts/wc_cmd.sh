#!/bin/bash

#如果我想要知道/etc/man_db.conf 这个档案里面有多少字？多少行？多少字元的话， 可以怎么做呢？其实可以利用wc 这个指令来达成喔！他可以帮我们计算输出的讯息的整体资料！

#wc [-lwm]
#选项与参数：
#-l ：仅列出行；
#-w ：仅列出多少字(英文单字)；
#-m ：多少字元；

#那个/etc/man_db.conf里面到底有多少相关字、行、字元数？
cat /etc/man_db.conf | wc
#输出的三个数字中，分别代表： 『行、字数、字元数』


#我知道使用last 可以输出登入者，但是last 最后两行并非帐号内容，那么请问，我该如何以一行指令串取得登入系统的总人次？
last | grep [a-zA-Z] | grep -v 'wtmp' | grep -v 'reboot' | grep -v 'unknown' |wc -l 
#由于last会输出空白行, wtmp, unknown, reboot等无关帐号登入的资讯，因此，我利用
#grep 取出非空白行，以及去除上述关键字那几行，再计算行数，就能够了解啰！


