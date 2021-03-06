#!/bin/bash

#選項與參數：
#    ：不加任何選項與參數時，type 會顯示出 name 是外部指令還是 bash 內建指令
#-t  ：當加入 -t 參數時，type 會將 name 以底下這些字眼顯示出他的意義：
#     file    ：表示為外部指令；
#     alias   ：表示該指令為命令別名所設定的名稱；
#     builtin ：表示該指令為 bash 內建的指令功能；
#-p  ：如果後面接的 name 為外部指令時，才會顯示完整檔名
#-a  ：會由 PATH 變數定義的路徑中，將所有含 name 的指令都列出來，包含 alias

type -a grep
