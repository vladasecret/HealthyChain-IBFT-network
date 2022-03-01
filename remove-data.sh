for dir in `find "./Nodes/" -maxdepth 1 -type d -name "*"`
do 
if [ -d "${dir}/data/caches" ]; then
rm -rf "${dir}/data/caches"
fi
if [ -d "${dir}/data/database" ]; then
rm -rf "${dir}/data/database"
fi
if [ -d "${dir}/data" ]; then
for file in `find "${dir}/data" -maxdepth 1 -type f -name "besu.*"`
do 
rm  "${file}"
done
fi
done