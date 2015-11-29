#!/bin/bash

#tr可以用来删除一段讯息当中的文字，或者是进行文字讯息的替换！

#itr[-ds]SET1...
#选项与参数：
#-d：删除讯息当中的SET1这个字串；
#-s：取代掉重复的字元！

#范例一：将last输出的讯息中，所有的小写变成大写字元：
last | tr -s [a-z] [A-Z]
#事实上，没有加上单引号也是可以执行的，如：『last|tr[az][AZ]』

#范例二：将/etc/passwd输出的讯息中，将冒号(:)删除
cat /etc/passwd | tr -d ':'

#范例三：将/etc/passwd转存成dos断行到/root/passwd中，再将^M符号删除
cp /etc/passwd/passwd && unix2dos /passw
file /etc/passwd/passw
cat /passwd | tr -d '\r' > /passwd.linux
#那个\r指的是DOS的断行字元，关于更多的字符，请参考mantr
ll /etc/passwd/passwd*
#处理过后，发现档案大小与原本的/etc/passwd就一致了！

