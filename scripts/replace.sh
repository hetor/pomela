#!/bin/sh

#作者：zxx
#日期：2012.04.19
#功能描述：在FIND_PATH路径下所有名为FILE_NAME的文件内容中，将SOURCE串替换为DEST串
#比如下例：将/home/zxx/文档/1路径下所有名为makefile的文件，凡是文件内容中含有-Werror的，都替换为空

#---------------------下面是你可以修改的参数------------------
#替换的文件都在这个路径下面
FIND_PATH="."
#替换的文件，替换所有文件指定为""
FILE_NAME="note"
#要替换的串
SOURCE="-WERROR"
#目的串
DEST=""
#--------------------上面是你可以修改的参数--------------------

makefilepath=$(find $FIND_PATH -name $FILE_NAME)
for way in $makefilepath
do
echo $way
#-!代表下面内容是输入，而不从键盘输入
vim -e $way<<-!
#要注意下面的$前面要加上\，否则会被解析为变量
:1,\$s/$SOURCE/$DEST/g
:wq
!
done


ssh tao.he@223.252.220.187 -p 1046 <<-!
cd /home/tao.he/ddb4.5.7.2/scripts
./haitaoMirror.sh
select * from trade where order_id = '2015112319282011000405166';
exit;
!
