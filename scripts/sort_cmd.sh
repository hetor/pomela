#!/bin/bash
sort [-fbMnrtuk] [file or stdin] 
#选项与参数：
#-f ：忽略大小写的差异，例如A 与a 视为编码相同；
#-b ：忽略最前面的空白字元部分；
#-M ：以月份的名字来排序，例如JAN, DEC 等等的排序方法；
#-n ：使用『纯数字』进行排序(预设是以文字型态来排序的)；
#-r ：反向排序；
#-u ：就是uniq ，相同的资料中，仅出现一行代表；
#-t ：分隔符号，预设是用[tab] 键来分隔；
#-k ：以那个区间(field) 来进行排序的意思

#个人帐号都记录在/etc/passwd下，请将帐号进行排序
cat /etc/passwd | sort

#/etc/passwd内容是以:来分隔的，我想以第三栏来排序，该如何？
cat /etc/passwd | sort -t ':' -k 3

#利用last ，将输出的资料仅取帐号，并加以排序
last | cut -d ' ' -f1 | sort
