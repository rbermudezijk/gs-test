#1. Commands used in day-day work
ls
cat
echo
touch
mv
rm
less
vi
head
tail
grep
exit
mkdir
pwd
clear
who

#2. Using ‘find’ cmd,find the files>1G and remove it
find $dirr -type f -size +1G

#3. Have you written any script, if yes,describe a sample 

#4. Field separator in awk – to find/extract 12th row and 4th column from a file

#5. Cut command

#6. Write a script to find first 20 fibonacci series(1 1 2 3 5 8 13...until 20th series)

#7. Write a unix script to query a table,store the output in a file and mail it

#8. How to find the open files for a process id
ps -aef | grep $process_name

#9. How to find top consuming memory processes
top -o MEM

#10. What will you do if a job that takes 1 min daily for its completion
#    but its running yet in unix server(its connecting to db too)
#    - What will be the approach to check

#11. Difference between the tar and gzip commands.
"Tar is a way to creates one file from various files, by other hand gzip is a tool to compress information,
So common, this two tools are applied to create software distributions"

#13. What steps will you take if an issue arises indicating that the host is unavailable?

#14. Print the last ten records by the second field in ascending order.
tail -n 10 file1.txt | sort -k 2 -r

#15. what are steps do you follow if 90%disk space usage alert is triggered?

#16. how to find largest file /directories and also inline directory upto 3 level ?
find /path -maxdepth $n -mindepth $n -type d -exec basename {} \;

#17. what if a daily job is taking much time than usual, how do we solve the issue?

#18. what is inode?
"inode is a map table between numbers and files references, each number corresponds to a file,
each register in inode tables contains a metadata for a file"
"To print inode number for a file you can use ls -i"
"To check the inodes max number you can use df -i"

#19.how to execute bash script in debug mode?
bash -x script.sh

#20. How do you handle file transfer to a remote host?
scp file.txt user@x.x.x.x:/path/to/save/file-name.txt -i key

sed -i '' '/^\s*$/d' "file1.txt"