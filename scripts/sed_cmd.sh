#!/bin/sh

#删除行
nl /etc/passwd | sed '50,90d'

#插入行
#append after 90
#nl /etc/passwd | sed '90a drink tea'  #centos
nl /etc/passwd | sed '90a\
    drink tea\
    '
# insert before 90
nl /etc/passwd | sed '90i\
    drink tea\
    '

#替换行
#nl /etc/passwd | sed '85,90c No 85-90 number'  #centos
nl /etc/passwd | sed '85,90c\
    No 85-90 number\
    '

#列出某几行
nl /etc/passwd | sed -n '85,90p'

#除了整行的处理模式之外， sed 还可以用行为单位进行部分资料的搜寻并取代的功能喔！基本上sed 的搜寻与取代的与vi 相当的类似！
#sed 's/要被取代的字串/新的字串/g'
/sbin/ifconfig en0 | grep 'inet\s' | sed 's/^.*inet //g' | sed 's/ netmask.*$//g'
cat /etc/man.conf | grep "MAN" | sed 's/#.*$//g' | sed '/^$/d'

#-i 操作直接修改文件（高危操作）
#sed -i 's/\.$/\!/g' regular_express.txt    #centos
#sed -i '$a #add to the last line' regular_express.txt    #centos
sed -i.bak '$a\
    #just comment\
    ' data/pay.properties

sed -i.bak '$i\
    #just comment\
    ' data/pay.properties


#http://blog.csdn.net/leexide/article/details/17199167
