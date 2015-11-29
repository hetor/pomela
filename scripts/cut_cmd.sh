#!/bin/bash

#cut -d'分隔字元' -f fields 
#cut -c字元区间

#选项与参数：
#-d ：后面接分隔字元。与-f 一起使用；
#-f ：依据-d 的分隔字元将一段讯息分割成为数段，用-f 取出第几段的意思；
#-c ：以字元(characters) 的单位取出固定字元区间
echo ${PATH}
echo ${PATH} | cut -d ':' -f 5
#如同上面的数字显示，我们是以『 : 』作为分隔，因此会出现/home/dmtsai/.local/bin
#那么如果想要列出第3与第5呢？，就是这样：
echo ${PATH} | cut -d ':' -f 3,5

export
# 注意看，每个资料都是排列整齐的输出！如果我们不想要『 declare -x 』时，就得这么做：
export | cut -c 12-
# 知道怎么回事了吧？用-c 可以处理比较具有格式的输出资料！
# 我们还可以指定某个范围的值，例如第12-20 的字元，就是cut -c 12-20 等等！

last
last | cut -d ' ' -f 1
#由输出的结果我们可以发现第一个空白分隔的栏位代表帐号，所以使用如上指令：
# 但是因为root pts/1 之间空格有好几个，并非仅有一个，所以，如果要找出 
# pts/1 其实不能以cut -d ' ' -f 1,2 喔！输出的结果会不是我们想要的。
