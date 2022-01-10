dateDir=$(date --date= +%Y%m%d)
cd /
cd /home/teacher 
mkdir $dateDir
cd $dateDir
echo $(dirname $(readlink -f $0))
for dir in $(ls /home/)
do
  cp -R "/home/"$dir"/homework/" $(dirname $(readlink -f $0))
done
dataAndName="list.md"
touch $dataAndName
i=0
for file in $(ls homework/)
do
  ((i+=1))
  array=(${file//-/ }) 
  line=""
  for var in ${array[@]}
  do
    temp=(${var//./ }) 
    line=$line" "$temp
  done
  echo $line>>list.md
done
echo "">>list.md
echo $i>>list.md
mv list.md $dateDir"-list.md"
cd /
cd /home/teacher 
chown -R teacher $dateDir
