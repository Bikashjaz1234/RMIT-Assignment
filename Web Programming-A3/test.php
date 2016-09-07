/*<?php
$handle = fopen("https://titan.csit.rmit.edu.au/~e54061/wp/moviesJSON.php","rb");
$content = ""; 
while (!feof($handle)) { 
    $content = fread($handle, 10000); 
}
fclose($handle);
?>
<?php
    $content = json_decode($content, true);
    print_r($content);
    echo $content['AF']['title'];
    /*foreach ($content as $j) {
    	# code...
    }*/
		?>

<?php
$eachShit=[0,0,0,0,0,0,0,0,0,0];

$seat=["shitOnSa","shitOnSp","shitOnSc","shitOnFa","shitOnFc","shitOnB1","shitOnB2","shitOnB3"];
$seat['shitOnSa']=$eachShit;
$seat['shitOnSp']=$eachShit;
$seat['shitOnSc']=$eachShit;
$seat['shitOnFa']=$eachShit;
$seat['shitOnFc']=$eachShit;
$seat['shitOnB1']=$eachShit;
$seat['shitOnB2']=$eachShit;
$seat['shitOnB3']=$eachShit;
$_SESSION["seatList"]=$seat;
print_r($seat);
?>
