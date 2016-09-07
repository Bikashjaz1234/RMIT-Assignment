<?php
session_start();
$arr=$_SESSION["mycart"];
?>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
 </head>
 <body>
    <!-- show the ditile of the tickets -->
 	<table>
        <tr border="1" style="width:100%">
            <td>
    <!-- variables -->
 	<?php
    if (isset($arr))
        foreach ($arr as $a) {
            /* caculate the price */
            if ($a['movieday']=='monday' || $a['movieday']=='tuesday') {
            # code...
            $sa = 12;
            $sp = 10;
            $sc = 8;
            $fa = 25;
            $fc = 20;
            $b1 = 20;
            $b2 = 20;
            $b3 = 20;
        }
        elseif ($a['movieday'] == 'saturday' || $a['movieday'] == 'sunday') {
            # code...
            $sa = 18;
            $sp = 15;
            $sc = 12;
            $fa = 30;
            $fc = 25;
            $b1 = 30;
            $b2 = 30;
            $b3 = 30;
        }
        elseif ($a['movietime']=='1pm') {
            # code...
            $sa = 12;
            $sp = 10;
            $sc = 8;
            $fa = 25;
            $fc = 20;
            $b1 = 20;
            $b2 = 20;
            $b3 = 20;
        }
        else
            $sa = 18;
            $sp = 15;
            $sc = 12;
            $fa = 30;
            $fc = 25;
            $b1 = 30;
            $b2 = 30;
            $b3 = 30;

    

    $saprice = $sa * $a['samovieseat'];
    $spprice = $sp * $a['spmovieseat'];
    $scprice = $sc * $a['scmovieseat'];
    $faprice = $fa * $a['famovieseat'];
    $fcprice = $fc * $a['fcmovieseat'];
    $b1price = $b1 * $a['b1movieseat'];
    $b2price = $b2 * $a['b2movieseat'];
    $b3price = $b3 * $a['b3movieseat'];
    if(isset($grandprice)) {
                $grandprice = $grandprice + $saprice + $spprice + $scprice + $faprice + $fcprice + $b1price + $b2price + $b3price;
            }
            else {
                $grandprice = $saprice + $spprice + $scprice + $faprice + $fcprice + $b1price + $b2price + $b3price;
            }
    if ($_POST['vresult'] == '1') {
        # code...
        $grandprice = $grandprice * 0.8;
        echo
            "<br>You Use the Voucher Code: {$_POST['Voucher1']}-{$_POST['Voucher2']}-{$_POST['Voucher3']}
    <br>";
    }
    /* caculate price end */
 		echo "{$a['username']}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Silverado";
 		echo "<br>";
 		echo "{$a['email']}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Inside Out";
 		echo "<br>";
 		echo "{$a['phonenumber']}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{$a['movieday']},&nbsp;{$a['movietime']}";
 		echo "<br>";
 		echo "<br>";
        echo "Total Price is: $grandprice";
        echo "<br><br>";
 		         if ($a['samovieseat']!="0")
                    echo
                    "{$a['samovieseat']}&nbsp;*&nbsp;Adult seats&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$saprice";

                if ($a['spmovieseat']!="0")
                    echo
                    "<br>
                    {$a['spmovieseat']}&nbsp;*&nbsp;Concession seats&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$spprice";

                if ($a['scmovieseat']!="0")
                    echo
                    "<br>
                    {$a['scmovieseat']}&nbsp;*&nbsp;Child seats&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$scprice";

                if ($a['famovieseat']!="0")
                    echo
                    "<br>
                    {$a['famovieseat']}&nbsp;*&nbsp;First Class Adult seats&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$faprice";

                if ($a['fcmovieseat']!="0")
                    echo
                    "<br>
                    {$a['fcmovieseat']}&nbsp;*&nbsp;First Class Child seats&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$fcprice";

                if ($a['b1movieseat']!="0")
                    echo
                    "<br>
                    {$a['b1movieseat']}&nbsp;*&nbsp;Beanbag seats (single person)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$b1price";

                if ($a['b2movieseat']!="0")
                    echo
                    "<br>
                    {$a['b2movieseat']}&nbsp;*&nbsp;Beanbag seats (Up to 2 people)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$b2price";

                if ($a['b3movieseat']!="0")
                    echo
                    "<br>
                    {$a['b3movieseat']}&nbsp;*&nbsp;Beanbag seats (Up to 3 children)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$b3price";
                
                
                echo "
                <br>
                <br>
                <br>
            </td>
        </tr>
        <tr>
            <td>";
        
                    if ($a['samovieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        Adult seats
                        <br>";
                       for($i=0;$i<$a['samovieseat'];$i++){
                            $s=$i+1;
                            echo "sa $s,";
                            $samovieseatnum = "sa $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $samovieseatnum);
                            fclose($myfile);
                            }
                        $saeachinfo = "Each Adult seat price is: $sa \n";
                        $sapriceinfo = "Total Adult seats Price is: $saprice \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $saeachinfo);
                        fwrite($myfile, $sapriceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                    if ($a['spmovieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        Concession seats
                        <br>";
                        for($i=0;$i<$a['spmovieseat'];$i++){
                            $s=$i+1;
                            echo "sp $s,";
                            $spmovieseatnum = "sp $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $spmovieseatnum);
                            fclose($myfile);
                        }
                        $speachinfo = "Each Concession seat price is: $sp \n";
                        $sppriceinfo = "Total Concession seats Price is: $spprice \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $speachinfo);
                        fwrite($myfile, $sppriceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                    if ($a['scmovieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        Child seats
                        <br>";
                        for($i=0;$i<$a['scmovieseat'];$i++){
                            $s=$i+1;
                            echo "sc $s,";
                            $scmovieseatnum = "sc $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $scmovieseatnum);
                            fclose($myfile);
                        }
                        $sceachinfo = "Each Child seat price is: $sc \n";
                        $scpriceinfo = "Total Child seats Price is: $scprice \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $sceachinfo);
                        fwrite($myfile, $scpriceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                    if ($a['famovieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        First Class Adult seats
                        <br>";
                        for($i=0;$i<$a['famovieseat'];$i++){
                            $s=$i+1;
                            echo "fa $s,";
                            $famovieseatnum = "fa $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $famovieseatnum);
                            fclose($myfile);
                        }
                        $faeachinfo = "Each First Class Adult seat price is: $fa \n";
                        $fapriceinfo = "Total First Class Adult seats Price is: $faprice \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $faeachinfo);
                        fwrite($myfile, $fapriceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                    if ($a['fcmovieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        First Class Child seats
                        <br>";
                        for($i=0;$i<$a['fcmovieseat'];$i++){
                            $s=$i+1;
                            echo "fc $s,";
                            $fcmovieseatnum = "fc $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $fcmovieseatnum);
                            fclose($myfile);
                        }
                        $fceachinfo = "Each First Class Child seat price is: $fc \n";
                        $fcpriceinfo = "Total First Class Child seats Price is: $fcprice \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $fceachinfo);
                        fwrite($myfile, $fcpriceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                    if ($a['b1movieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        Beanbag seats (single person)
                        <br>";
                        for($i=0;$i<$a['b1movieseat'];$i++){
                            $s=$i+1;
                            echo "b1 $s,";
                            $b1movieseatnum = "b1 $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $b1movieseatnum);
                            fclose($myfile);
                        }
                        $b1eachinfo = "Each Beanbag seat (single person) price is: $b1 \n";
                        $b1priceinfo = "Total Beanbag seats (single person) Price is: $b1price \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $b1eachinfo);
                        fwrite($myfile, $b1priceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                    if ($a['b2movieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        Beanbag seats (Up to 2 people)
                        <br>";
                        for($i=0;$i<$a['b2movieseat'];$i++){
                            $s=$i+1;
                            echo "b2 $s,";
                            $b2movieseatnum = "b2 $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $b2movieseatnum);
                            fclose($myfile);
                        }
                        $b2eachinfo = "Each Beanbag seat (Up to 2 people) price is: $b2 \n";
                        $b2priceinfo = "Total Beanbag seats (Up to 2 people) Price is: $b2price \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $b2eachinfo);
                        fwrite($myfile, $b2priceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                    if ($a['b3movieseat']!="0") {
                        echo "
                        Silverado
                        <br>
                        Inside Out
                        <br>";
                        echo "
                        Beanbag seats (Up to 3 children)
                        <br>";
                        for($i=0;$i<$a['b3movieseat'];$i++){
                            $s=$i+1;
                            echo "b3 $s,";
                            $b3movieseatnum = "b3 $s \n";
                            $seatinfo = "Seat Number is: ";
                            $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                            fwrite($myfile, $seatinfo);
                            fwrite($myfile, $b3movieseatnum);
                            fclose($myfile);
                        }
                        $b3eachinfo = "Each Beanbag seat (Up to 3 children) price is: $b3 \n";
                        $b3priceinfo = "Total Beanbag seats (Up to 3 children) Price is: $b3price \n\n";
                        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
                        fwrite($myfile, $b3eachinfo);
                        fwrite($myfile, $b3priceinfo);
                        fclose($myfile);
                        echo "
                        <br>
                        <br>";
                        # code...
                    }
                }
                    ?>
                <br>
                <br>
            </td>
        </tr>
        <?php
        $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
        $basicinfo = "\nBasic Information: {$a['username']}, {$a['email']}, {$a['phonenumber']}, {$a['moviename']}, {$a['movieday']}, {$a['movietime']} \n";
        $unid = rand();
        $unidinfo = "Your order ID is: ";
        $grandpriceinfo = "\nThe total price is: $grandprice\n";

        fwrite($myfile, $basicinfo);
        fwrite($myfile, $grandpriceinfo);
        fwrite($myfile, $unidinfo);
        fwrite($myfile, $unid);
        fclose($myfile);
        
        ?>
    </table>
    <?php
function generateQRfromGoogle($data,$widhtHeight='150',$EC_level='L',$margin='0'){
    $url=urlencode($data);
    echo '<img src="http://chart.apis.google.com/chart?chs='.$widhtHeight.'x'.$widhtHeight.'&cht=qr&chld='.$EC_level.'|'.$margin.'&chl='.$data.'" widhtHeight="'.$widhtHeight.'" widhtHeight="'.$widhtHeight.'"/>';


}
$my_qr = file_get_contents("moviefile.txt");
generateQRfromGoogle($my_qr);
?>
</body>
</html>

