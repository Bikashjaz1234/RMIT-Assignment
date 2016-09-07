<?php
$handle = fopen('gs://s3534987-storage/data.txt','w');
fwrite($handle, "2");
for($i = 3; $i <= 30; $i = $i + 2) {
  $j = 2;
  while($i % $j != 0) {
    if($j > sqrt($i)) {
      fwrite($handle, ",".$i);
break; }
$j++; }
}
fclose($handle);

$primes = explode(",", file_get_contents('gs://s3534987-storage/data.txt')
);
$arrlength=count($primes);
$sum = 0;
for($x=0;$x<$arrlength;$x++)
{
	$sum = $sum + $primes[$x]; 
}
?>
<html>
<body>
	<?php
	if (array_key_exists('x', $_POST) && array_key_exists('y', $_POST)) {
		$sum = $sum + $_POST['x'] + $_POST['y'];
		$avg = $sum / 12;
		echo "Sum: $sum";
		echo "<br>";
		echo "Average: $avg";
		//echo htmlspecialchars($_POST['content']); echo "\n</pre>";
		$result = fopen('gs://s3534987-storage/result.txt','w');
		fwrite($result, "Sum: ");
		fwrite($result, "$sum\n");
		fwrite($result, "Average: ");
		fwrite($result, "$avg");
		fclose($result);
		}
		
		?>
		
		<form action="/sign" method="post">
			<div>x<textarea name="x" rows="3" cols="8"></textarea></div>
			<div>y<textarea name="y" rows="3" cols="8"></textarea></div>
			<div><input type="submit" value="Submit"></div>
		</form>
</body>
</html>
echo 'Prime number file created in GCS Bucket';