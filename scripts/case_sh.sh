#!/bin/bash
# Program:
#   Show "Hello" from $1.... by using case .... esac
# History:
# 2015/07/16    VBird   First release
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH

echo "This program will print your selection !"
# read -p "Input your choice: " choice   # 暫時取消，可以替換！
# case ${choice} in                      # 暫時取消，可以替換！
case ${1} in                             # 現在使用，可以用上面兩行替換！
  "one")
       echo "Your choice is ONE"
       ;;
  "two")
       echo "Your choice is TWO"
       ;;
  "three")
       echo "Your choice is THREE"
       ;;
  *)
       echo "Usage ${0} {one|two|three}"
       ;;
esac


#shell script 的執行方式是由上而下，由左而右， 因此在 shell script 當中的 function 的設定一定要在程式的最前面
#function 也是擁有內建變數的～他的內建變數與 shell script 很類似， 函數名稱代表示 $0 ，而後續接的變數也是以 $1, $2... 來取代的
function printit(){
    echo "Your choice is ${1}"   # 這個 $1 必須要參考底下指令的下達, 加上 -n 可以不斷行繼續在同一行顯示
}

echo "This program will print your selection !"
case ${1} in
  "one")
       printit 1 | tr 'a-z' 'A-Z'  # 請注意， printit 指令後面還有接參數！
       ;;
  "two")
       printit 2 | tr 'a-z' 'A-Z'
       ;;
  "three")
       printit 3 | tr 'a-z' 'A-Z'
       ;;
  *)
       echo "Usage ${0} {one|two|three}"
       ;;
esac

