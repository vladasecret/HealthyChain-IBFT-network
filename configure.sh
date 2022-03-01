#!/bin/bash

bold=$(tput bold)
red=$(tput setaf 1)
green=$(tput setaf 2)
normal=$(tput sgr0)

OUT_DIR="./Nodes"
CONFIG_FILE="./config/ibftConfigFile.json"

if  [ -f ./configure/genesis.json ]; then
    echo "${red} Genesis.file already exists ${normal}"
    exit 1;
fi

if [[ -f $CONFIG_FILE ]] && ! [[ -d $OUT_DIR ]]; then
besu operator generate-blockchain-config --config-file=$CONFIG_FILE --to=$OUT_DIR --private-key-file-name=key

if [ $? -ne 0 ]; then
echo "${res}${bold}Besu config generation failture${normal}"
exit 1;
fi

echo "${green}${bold} Created new genesis.file ${normal}"
i=1
for dir in `find ${OUT_DIR}/keys -type d -name "0x*"`
do 
    mkdir "${OUT_DIR}/node$i"
    mv "$dir" "${OUT_DIR}/node$i/data"
    $((i++))
done

rmdir $OUT_DIR/keys
mv ${OUT_DIR}/genesis.json ./config/
echo "${green}${bold} Created new nodes keys in ${OUT_DIR} ${normal}" 

else    
echo "${red}${bold} Impossible to create genesis.json: directory \"${OUT_DIR}\" already exists or file \"${CONFIG_FILE}\" not found.${normal}"
fi


