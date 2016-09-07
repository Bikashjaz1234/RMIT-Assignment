<?php
session_start();
$arr=$_SESSION["mycart"];
?>
<?php include_once ('includes/head.php');?>

<body ondblclick="window.scrollTo(0,0);">
<!--Import the scripts><!-->

<!--Import the scripts><!-->
<!--Main container here ↓ ↓><!-->
<div class="container">
    <div id="big_Head" class="page-header fix_cloudsColor">
        <div class="container">
            <div class="left">
                <a href= "index.html"><h1><img src="imgs/logo.png" width="150" height="150"></a>
                Silverado&nbsp;&nbsp;&nbsp;
                <small>The best experience for film</small>
                </h1>
            </div>
        </div>
    </div>
    <!--Navigation area><!-->
    <?php include_once("includes/nav.php"); ?>
    <!-- ~~~~Navigation area><!-->
    <!-- Ticket Detail -->
    <form name='checkout' action='check-out.php' method='post'>
            <table style="width:100%">
            <style>
                table, th, td {
                    border: 0px solid #FFF;
                    border-collapse: collapse;
                }
                th, td {
                    padding: 5px;
                    text-align: left;
                }
            </style>

    <?php
    if (is_array($arr))
    	foreach ($arr as $a) {
            /* caculate the price */
        if ($a['movieday'] == 'monday' || $a['movieday']=='tuesday') {
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
        elseif ($a['movietime'] == '1pm') {
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
    /* caculate price end */
    /* movie ticket detail */
    		echo "{$a['moviename']}";
    		echo "<br>";
    		echo "Showing at {$a['movieday']}, {$a['movietime']}";
    		echo "<br>
    			<table>
    		    <tr class=\"table-background-first\">
                <th>Ticket Type</th>
                <th>Cost</th>
                <th>Qty</th>
                <th>Seats</th>
                <th>Subtotal</th>
            	</tr>
    		";


            if ($a['samovieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>Adult seats</td>
            <td>$sa</td>
            <td>{$a['samovieseat']}</td><td>";
            for($i=0;$i<$a['samovieseat'];$i++)
            {$s=$i+1;
                echo "sa $s,";
                $samovieseatnum = "sa $s,";}
            echo"</td><td>";
            $saprice = $sa * $a['samovieseat'];
            echo "
            $saprice
            </td>
            </tr>";}

            if ($a['spmovieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>Concession seats</td>
            <td>$sp</td>
            <td>{$a['spmovieseat']}</td><td>";
            for($i=0;$i<$a['spmovieseat'];$i++)
            {$s=$i+1;
                echo "sp $s,";}
            echo"</td>
            <td>";
            $spprice = $sp * $a['spmovieseat'];
            echo "
            $spprice
            </td>
            </tr>";}

            if ($a['scmovieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>Child seats</td>
            <td>$sc</td>
            <td>{$a['scmovieseat']}</td><td>";
            for($i=0;$i<$a['scmovieseat'];$i++)
            {$s=$i+1;
                echo "sc $s,";}
            echo"</td>
            <td>";
            $scprice = $sc * $a['scmovieseat'];
            echo "
            $scprice
            </td>
            </tr>";}


            if ($a['famovieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>First Class Adult seats</td>
            <td>$fa</td>
            <td>{$a['famovieseat']}</td><td>";
            for($i=0;$i<$a['famovieseat'];$i++)
            {$s=$i+1;
                echo "fa $s,";}
            echo"</td>
            <td>";
            $faprice = $fa * $a['famovieseat'];
            echo "
            $faprice
            </td>
            </tr>";}

            if ($a['fcmovieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>First Class Child seats</td>
            <td>$fc</td>
            <td>{$a['fcmovieseat']}</td><td>";
            for($i=0;$i<$a['fcmovieseat'];$i++)
            {$s=$i+1;
                echo "fc $s,";}
            echo"</td>
            <td>";
            $fcprice = $fc * $a['fcmovieseat'];
            echo "
            $fcprice
            </td>
            </tr>";}

            if ($a['b1movieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>Beanbag seats (single person)</td>
            <td>$b1</td>
            <td>{$a['b1movieseat']}</td><td>";
            for($i=0;$i<$a['b1movieseat'];$i++)
            {$s=$i+1;
                echo "b1 $s,";}
            echo"</td>
            <td>";
            $b1price = $b1 * $a['b1movieseat'];
            echo "
            $b1price
            </td>
            </tr>";}


            if ($a['b2movieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>Beanbag seats (Up to 2 people)</td>
            <td>$b2</td>
            <td>{$a['b2movieseat']}</td><td>";
            for($i=0;$i<$a['b2movieseat'];$i++)
            {$s=$i+1;
                echo "b2 $s,";}
            echo"</td><td>";
            $b2price = $b2 * $a['b2movieseat'];
            echo "
            $b2price
            </td>
            </tr>";}

            if ($a['b3movieseat']!="0"){
                echo "
    		<tr class='table-background-second'>
            <td>Beanbag seats (Up to 3 children)</td>
            <td>$b3</td>
            <td>{$a['b3movieseat']}</td><td>";
            for($i=0;$i<$a['b3movieseat'];$i++)
            {$s=$i+1;
                echo "b3 $s,";}
            echo"</td>
            <td>";
            $b3price = $b3 * $a['b3movieseat'];
            echo "
            $b3price
            </td>
            </tr>";}
            echo "</form>";
            echo "</table>";
            echo "<br>";
            echo "<br>";
            echo "<td> <a id=\"bookingID\" href=\"delete.php?bookingID={$a['bookingID']}\">Delete from Cart</a> </td>";
            echo "<br>";

    	}
    ?>
        <br>
        <br>
        <br>
        <div>
     <?php

     function emptycart() {
        header("location:23.php");
     }

     if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $Voucher1 = $_POST["Voucher1"];
        $Voucher2 = $_POST["Voucher2"];
$Voucher3 = $_POST["Voucher3"];
$CHK1 = (($Voucher1[0] * $Voucher1[1] + $Voucher1[2]) * $Voucher1[3] + $Voucher1[4]) % 26;
$CHK2 = (($Voucher2[0] * $Voucher2[1] + $Voucher2[2]) * $Voucher2[3] + $Voucher2[4]) % 26;
$CHKList ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
if($CHKList[$CHK1]==$Voucher3[0]&&$CHKList[$CHK2]==$Voucher3[1])
{
    $Voucher='true';
    $myfile = fopen("moviefile.txt", "a") or die("Unable to open file!");
    $voucinfo = "\n$Voucher1 - $Voucher2 - $Voucher3 \n";
    fwrite($myfile, $voucinfo);
    fclose($myfile);
}
    else{
        $Voucher='false';
    }
}
     if (empty($arr)){
     	# code...
        echo
            "Your cart is empty!!";
        }
     else{
            echo
            "
    <br>
    <br>
    Meal and Movie Deal Voucher: {$_POST['Voucher1']}-{$_POST['Voucher2']}-{$_POST['Voucher3']}
    <br>
    <br>";
    if ($Voucher != 'false') {
        # code...
        $grandprice = $grandprice * 0.8;
        echo "Grand Total: $grandprice (You already use Voucher Code!)";
    }
    else
        echo "Grand Total: $grandprice (The Voucher Code is incorrect!)";

    echo "<br>
    <br>
    <br>
    <form action=\"{$_SERVER["PHP_SELF"]}\" method=\"post\">
    Voucher Code: 
    <input type=\"text\" name=\"Voucher1\" id=\"Voucher1\">-
    <input type=\"text\" name=\"Voucher2\" id=\"Voucher2\">-
    <input type=\"text\" name=\"Voucher3\" id=\"Voucher3\">
    <input type='submit' value='Apply'></form>
    <form action=\"destory.php\" method=\"post\">
    <input type='submit' value=\"Empty Cart\">
    </form>
    <input type='submit' value='Check out'>";
}
            ?>
    <?php
        if ($Voucher != 'false') {
            # code...
        echo "<input type=\"hidden\" name=\"vresult\" id=\"vresult\" value=\"1\">";
        echo "<input type=\"hidden\" name=\"Voucher1\" id=\"Voucher1\" value=\"$Voucher1\">";
        echo "<input type=\"hidden\" name=\"Voucher2\" id=\"Voucher2\" value=\"$Voucher2\">";
        echo "<input type=\"hidden\" name=\"Voucher3\" id=\"Voucher3\" value=\"$Voucher3\">";
        }
    ?>
    </form>
</div>

<!-- POST Tester Form (ie this page has a form that "self submits")  -->
<!--
    <h1>Extra features</h2>
        <h2>Test this script using the post method</h2>
        <form method="post" action="<?php /*echo $_SERVER['PHP_SELF']; */?>">
            <div>&lt;form method='post' ... &gt;
                <p>This input has the name 'test-input' <br/>
                    <input type='text' name='test-input' value='' /><br/>
                    <input type='submit' name='submit' value='submit'/><br/>
                </p>
                &lt;/form&gt;
            </div>
        </form> -->

<!-- PHP Comments Below -->
<?php
/*
<h2>COOKIE Variables:</h2>
<?php
if (count($_COOKIE) > 0)
{
  ?><h4>The following variables are SERVER SIDE and were detected as COOKIEs in your browser:</h4><?php  foreach ($_COOKIE as $name => $value)
  { echo '<p>The value of <span>'.$name."</span> is <span>".$value."</span></p>"; }
} else { ?>
<p>There were no variables found SERVER SIDE lurking as COOKIEs in your browser.</p>
<?php } ?>
<h4>These are the cookies that are on your machine, detected CLIENT SIDE using javascript:</h4>
<p><span><script>document.write(document.cookie);</script></span></p>
<hr/><h4>Click <a href="<?php echo $_SERVER['HTTP_REFERER'];?>">here</a> to go back to your form, or hit the backspace key.</h4>
*/
?>
<br>
<br>
<br>
</div>
<?php include_once("includes/footer.php"); ?>
</body>
</html>