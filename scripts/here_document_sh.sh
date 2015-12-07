#!/bin/bash

#https://en.wikipedia.org/wiki/Here_document

#Here Document 是在Linux Shell 中的一种特殊的重定向方式，它的基本的形式如下
#cmd << delimiter
#  Here Document Content
#delimiter
#其作用是将两个 delimiter 之间的内容(Here Document Content 部分) 传递给cmd 作为输入参数;



#EOF只是一个标识而已,可以替换成任意的合法字符(约定大于配置);
#作为结尾的delimiter一定要顶格写,前面不能有任何字符;
#作为结尾的delimiter后面也不能有任何的字符(包括空格!!!);
#作为起始的delimiter前后的空格会被省略掉;


#delimiter与变量 

#在Here Document 的内容中,不仅可以包括普通的字符,还可以在里面使用变量;
#例如将上面的here.sh 改为
#cat << EOF > output.sh
#echo "This is output"
#echo $1
#EOF
#使用sh here.sh HereDocument 运行脚本得到output.sh的内容
#
#echo "This is output"
#echo HereDocument
#在这里 $1 被展开成为了脚本的参数 HereDocument
# 
#但是有时候不想展开这个变量怎么办呢，可以通过在起始的 delimiter的前后添加 " 来实现，例如将上面的here.sh 改为
#cat << "EOF" > output.sh  #注意引号
#echo "This is output"
#echo $1
#EOF
#得到的output.sh 的内容为
#echo "This is output"
#echo $1


#<< 变为 <<- 支持缩进（回车符，非空格符）



