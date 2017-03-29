#!/bin/bash
echo $1
if [ $1 = "m1" ]; then
	ssh -A -t s3458129@saturn.csit.rmit.edu.au ssh -i network-programming/s3458129 -t s3458129@m1-c13n1.csit.rmit.edu.au
elif [ $1 = "m2" ]; then
	ssh -A -t s3458129@saturn.csit.rmit.edu.au ssh -i network-programming/s3458129 -t s3458129@m1-c14n1.csit.rmit.edu.au
fi
