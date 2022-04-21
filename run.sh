. ./.fonts.sh

docker-compose up -d

if [ $? -eq 0 ]; then
echo "${bold}${green}*******************************************************************"
echo "Wait for information about placed blocks, then press Ctrl+C to exit"
echo "${bold}${green}*******************************************************************${normal}"

docker-compose logs -f node1

echo "${bold}${green}*******************************************************************${normal}"
echo "${green}Nodes RPS ip:port"
echo "Node1 127.0.0.1:8545 localhost"

echo "${bold}${green}*******************************************************************${normal}"
fi