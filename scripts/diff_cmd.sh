#!/bin/sh

#diff就是用在比对两个档案之间的差异的，并且是以行为单位来比对的！
#-b ：忽略一行当中，仅有多个空白的差异
#-B ：忽略空白行的差异
#-i ：忽略大小写的不同
diff passwd.old passwd.new


#-l ：将所有的不同点的位元组处都列出来。因为cmp预设仅会输出第一个发现的不同点
cmp [-l] file1 file2


#将旧的档案升级成为新的档案
#先比较先旧版本的差异，并将差异档制作成为补丁档，再由补丁档更新旧档案
diff -Naur passwd.old passwd.new > passwd.patch

patch -pN < patch_file  ##更新   -p ：后面可以接『取消几层目录』的意思
patch -R -pN < patch_file  ##还原   -R ：代表还原，将新的档案还原成原来旧的版本 

patch -p0 < passwd.patch
patch -R -p0 < passwd.patch
