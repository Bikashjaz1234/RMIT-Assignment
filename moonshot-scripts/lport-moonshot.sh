#!/bin/bash

echo $1
if [ $1 = "m1" ]; then
	ssh s3458129@saturn.csit.rmit.edu.au -L 9999:m1-c13n1.csit.rmit.edu.au:22 -N
elif [ $1 = "m2" ]; then
	ssh s3458129@saturn.csit.rmit.edu.au -L 9990:m1-c14n1.csit.rmit.edu.au:22 -N
fi




