moonshot-mount.sh

Very straight forward you will have to change the student number as well as the location of the ssh key.
ie. change network-programming/s3458129 to another folder on Saturn or whatever coreteaching server you want to connect to.



lport-moonshot.sh

This script creates an ssh connection to saturn and binds it to a local port as well 9999 for m1 and 990 for m2
you will need to change the student number.
This script gives no feed back once you successfuly connect you will just see a blinking cursor.

In a new terminal window run

moonshot-mount.sh

This script uses sshfs to mount your home directory on moonshot over ssh to a local directory.
You will need to modify the location of the key file (IdentityFile=), the student number and
the path to the local directory that you want to mount moonshot to.

command line arguments supported for all scripts are m1 and m2
m1 for primary moonshot server and m2 for secondary.
