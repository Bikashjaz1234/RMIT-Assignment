<?php
$primes = explode(",", file_get_contents('gs://s3534987-storage/data.txt')
);
$arrlength=count($primes);
for($x=0;$x<$arrlength;$x++)
{
echo "The ". $x ."-th prime number is: ".$primes[$x]."<br>"; 
}