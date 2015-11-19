#!/bin/sh

#第一行 #!/bin/bash 在宣告這個 script 使用的 shell 名稱

alias grep='grep --color=auto'

grep -A 5 'ifconfig' sed.sh

grep -B 5 'ifconfig' sed.sh

sudo dmesg | grep -n -A3 -B2 'Google'

#反向选择
grep -vn 'the' ./data/regular_express.txt

#忽略大小写
grep -in 'the' data/regular_express.txt

#[]里选一个
grep -n 't[ae]st' data/regular_express.txt

#反向选择[^]
grep -n '[^g]oo' data/regular_express.txt
grep -n '[^a-z]oo' data/regular_express.txt
grep -n '[0-9]' data/regular_express.txt

#行首
grep -n '^the' data/regular_express.txt
grep -n '^[a-z]' data/regular_express.txt
grep -n '^[^a-zA-Z]' data/regular_express.txt

#行尾 .
grep -n '\.$' data/regular_express.txt 

#空行
grep -n '^$' data/regular_express.txt

#除去空行和注释
grep -v '^$' data/pay.properties | grep -v '^#'

# . (小数点)：代表『一定有一个任意字元』的意思
grep -n 'g..d' data/regular_express.txt

# * (星星号)：代表『重复前一个字元， 0 到无穷多次』的意思，为组合形态
grep -n 'ooo*' data/regular_express.txt
grep -n 'goo*g' data/regular_express.txt
grep -n 'g*g' data/regular_express.txt

#任意数字
grep -n '[0-9][0-9]*' data/regular_express.txt 

# .*就代表零个或多个任意字元
rep -n 'g.*g' data/regular_express.txt

# {与}的符号在shell是有特殊意义的，因此，我们必须要使用跳脱字符\来让他失去特殊意义才行
grep -n 'o\{2\}' regular_express.txt
grep -n 'go\{2,5\}g' data/regular_express.txt
grep -n 'go\{2,\}g' data/regular_express.txt
