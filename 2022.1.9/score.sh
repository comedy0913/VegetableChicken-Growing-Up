list=*.md
for file in $(ls homework/)
do
  score=0
  for ((i=1;i<=10;i++))
  do
    answer=`awk -F. 'NR=='"$i"'{print $2}' homework/$file`
    correct_answer=`awk -F. 'NR=='"$i"'{print $2}' answer.txt`
    if test "$answer" = "$correct_answer"
    then
        ((score+=10))
    fi
  done
  str1="$score"
  str2="/${file%-*}/a\\"
  str=$str2$str1
  echo $str
  sed -i $str $list
done
