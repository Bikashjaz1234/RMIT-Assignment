#!/bin/bash

echo $1
if [ $1 = "m1" ]; then
	sshfs -o IdentityFile=/home/andrew/moonshot-key.txt -p 9999 s3458129@127.0.0.1:/home/s3458129 /home/andrew/RMIT/Network\ Programming/moonshot
elif [ $1 = "m2" ]; then
	sshfs -o IdentityFile=/home/andrew/moonshot-key.txt -p 9990 s3458129@127.0.0.1:/home/s3458129 /home/andrew/RMIT/Network\ Programming/moonshot2
fi


