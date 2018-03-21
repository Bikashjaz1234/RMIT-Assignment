#!/bin/bash

#Student Name: Siyu Zang (Harold)
#Student ID: s3534987
#Course ID: COSC1133

#Please follow the format to run the script.
#Format:
#cat password.txt | ./s3534987

#I finished the whole function, and working verywell. Enjoy Test!
#Have a great day
#:D

#declared binaries by using the way that assignment provided.
AWK='/usr/bin/awk'
SHA256SUM='/usr/bin/sha256sum'
KILL='/bin/kill'
SLEEP='/bin/sleep'
SED='/usr/bin/sed'

#Define multiple variables for script. 
#I asked the Ke.Deng, and he said that using your own linux.words (100 records) rather than using lecture's linux.words file. So I use my own file.
LINUXWORES="./linux.words"
#declared some arraies that to store the username and password.
declare -A passfileuser
declare -A passfilepass
PID=$$

#Test the linux.words File exist or not! If the file not exist, quit.
if [ ! -e "$LINUXWORES" ] ; then
	echo "Does not find Linux.words file! Please make sure it is exist." 
	exit 1
fi

#Part 1 Begin
#The function for read password.txt file.
#set a variable for the number, which how many passwords will be tested.
pflength=0
while read -r line ;
	do
		#save the username and password to an array.
    username=$(echo "$line" | $AWK -F ":" '{print $1}')
    password=$(echo "$line" | $AWK -F ":" '{print $2}')
    passfileuser["$pflength"]=$username
    passfilepass["$pflength"]=$password
    ((pflength=pflength+1))
  done
#Part 1 End

#Part2a Begin
#The function for crack common password
commonPass(){
	echo "Begin Common password attack!"
	#set up the common password
	Plaintext=("test" "password" "123456" "passwd" "admin" "rmit" "000000" "12345678" "654321")
	#Before run this function, check the whole password is creck finished or not.
	if [[ "${#passfilepass[@]}" -gt 0 ]]; then
		#using double for loop to test the password
		for ((i=0; i<=pflength; i++));
			do
				for var in "${Plaintext[@]}";
					do
						#Checking function, in the for loop, everytime check the crack password is finished or not. (check the array length)
						checkFinish
						#calculate the SHA-256 password, and save it to a variable
						commonpass=$(echo -n "$var" | $SHA256SUM | $SED 's/\W//g')
						#compare the password
						if [ "$commonpass" == "${passfilepass[$i]}" ] ; then
    					echo -n "The Username is" "${passfileuser[$i]}"
    					echo ""
          		echo "This user has a weak password, the weak password is" "$var"
          		#if find the password, unset it form the array.
          		unset passfileuser[$i]
          		unset passfilepass[$i]
      			fi
      		done
      done
    fi
}
#Part2a End

#Part2b Begin
#The function for crack password by using dictionary (linux.words file)
LinuxWords(){
echo "Begin Linux.words File attack!"

#Before run this function, check the whole password is creck finished or not.
#The function code is similar with commonPass function.
if [[ "${#passfilepass[@]}" -gt 0 ]]; then
#read the linux.words file line by line, and then do SHA-256 for each line.
while read -r linuxwords ;
	do
   dirpass=$(echo -n "$linuxwords" | $SHA256SUM | $SED 's/\W//g')
#using for loop to compare the password.
   for ((i=0; i<=pflength; i++));
   do
   		#Checking function
			checkFinish
   		if [ "$dirpass" == "${passfilepass[$i]}" ] ; then
    			echo -n "The Username is" "${passfileuser[$i]}"
    			echo ""
          echo "This user has a weak password, the weak password is" "$linuxwords"
          #if find the password, unset the username and password form the array.
          unset passfileuser[$i]
          unset passfilepass[$i]
      fi
    done
	done < "$LINUXWORES"
fi
}
#Part2b End

#Part2c Begin
#The function for brute force attack
bruteForce(){
echo "Begin Brute Force attack!"
	if [[ "${#passfilepass[@]}" -gt 0 ]]; then
		#Create the password (maximum length 5 characters), then store it to an array.
		bfword=($(echo {a..z} {a..z}{a..z} {a..z}{a..z}{a..z} {a..z}{a..z}{a..z}{a..z} {a..z}{a..z}{a..z}{a..z}{a..z}))
		#If more than 4mins, it should stop (kill the process).
		($SLEEP 240 ; $KILL -9 $PID) &
	
		#using double for loop to test the password
		for ((i=0; i<=pflength; i++));
			do
				for bf in "${bfword[@]}";
					do
						#Checking function
						checkFinish
						#calculate the SHA-256 password, and save it to a variable
						bfpass=$(echo -n "$bf" | $SHA256SUM | $SED 's/\W//g')
						#compare the password
						if [ "$bfpass" == "${passfilepass[$i]}" ] ; then
    					echo -n "The Username is" "${passfileuser[$i]}"
    					echo ""
          		echo "This user has a weak password, the weak password is" "$bf"
          		#if find the password, unset it form the array.
          		unset passfileuser[$i]
          		unset passfilepass[$i]
          		echo "${passfilepass[@]}"
      			fi
      		done
      		#sleep 0.1 to reduce CPU
					sleep 0.1
      done
    fi
}
#Part2c End

#The function that to check the crack is finished or not
checkFinish(){
#if the array length is 0, this means that all apssword already be creaked. Then, kill the process.
	if [[ "${#passfilepass[@]}" -eq 0 ]]; then
		echo "Finish the cracking, found all passwords. Quit the program"
		$KILL -9 $PID
	fi
}

commonPass
LinuxWords
bruteForce
