. ./.fonts.sh

docker-compose up -d

if [ $? -eq 0 ]; then
echo "${bold}${green}*******************************************************************"
echo "Wait for information about placed blocks, then press Ctrl+C to exit"
echo "${bold}${green}*******************************************************************${normal}"
sleep 5;

docker-compose logs -f node1

echo "${bold}${green}*******************************************************************${normal}"
echo "${green}Nodes ip:port"
echo "Node1 172.16.239.11:8545"
echo "Node2 172.16.239.12:8546"
echo "Node3 172.16.239.13:8547"
echo "Node4 172.16.239.14:8548"
echo "${bold}${green}*******************************************************************${normal}"
fi