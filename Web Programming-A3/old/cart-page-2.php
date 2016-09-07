<?php
session_start();
$arr=$_SESSION["mycart"];
print_r($arr);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head lang="en">
    <meta charset="UTF-8">
    <title>Welcome to Silverado</title>
    <!-- import the css -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/metro.min.css" rel="stylesheet">
    <link href="css/metro-responsive.min.css" rel="stylesheet">
    <link href="css/metro-icons.min.css" rel="stylesheet">
    <link href="css/ul.css" rel="stylesheet">
    <link href="css/buttons.css" rel="stylesheet">
    <link href="css/_fix.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>
        table {
            width:100%;
        }
        table, th, td {
            border: 1px solid #fff;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
        table#t01 tr:nth-child(even) {
            background-color: #eee;
        }
        table#t01 tr:nth-child(odd) {
            background-color:#fff;
        }
        table#t01 th	{
            background-color: red;
            color: white;
        }
    </style>
</head>

<body ondblclick="window.scrollTo(0,0);">
<!--Import the scripts><!-->
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/teninedialog1.0.0.js"></script>
<script src="js/metro.min.js"></script>
<script src="js/SilveradoMain.js"></script>
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
    <nav class="nav nav-pills navbar-static-top fix_UpAndDownForZero">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href= "index.html"><button class="button button-small button-plain button-border" >
                            <b>Home</b>
                        </button></a>
                </li>
                <li>
                    <a href="movieList.html"><button class="button button-small button-plain button-border" >
                            <b>Movie Information</b>
                        </button></a>
                </li>
                <li>
                    <a href="newsList.html"><button class="button button-small button-plain button-border" href="News.html">
                            <b>News</b>
                        </button></a>
                </li>
            </ul>
        </div>
    </nav>
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
    	foreach ($arr as $a) {

    		echo "{$a['moviename']}";
    		/*echo {$a['movierank']};*/
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
    		if ($a['samovieseat']!="0")
    		echo "
    		<tr class='table-background-second'>
            <td>Adult seats</td>
            <td>$12.00</td>
            <td>{$a['samovieseat']}</td>
            <td>{$_POST['samovieseatnum']}</td>
            <td>{$_POST['saprice']}</td>
            </tr>";

            if ($a['spmovieseat']!="0")
            echo "
    		<tr class='table-background-second'>
            <td>Concession seats</td>
            <td>$12.00</td>
            <td>{$a['spmovieseat']}</td>
            <td>{$_POST['spmovieseatnum']}</td>
            <td>{$_POST['spprice']}</td>
            </tr>";

            if ($a['scmovieseat']!="0")
    		echo "
    		<tr class='table-background-second'>
            <td>Child seats</td>
            <td>$12.00</td>
            <td>{$a['scmovieseat']}</td>
            <td>{$_POST['scmovieseatnum']}</td>
            <td>{$_POST['scprice']}</td>
            </tr>";

            if ($a['famovieseat']!="0")
            echo "
    		<tr class='table-background-second'>
            <td>First Class Adult seats</td>
            <td>$12.00</td>
            <td>{$a['famovieseat']}</td>
            <td>{$_POST['famovieseatnum']}</td>
            <td>{$_POST['faprice']}</td>
            </tr>";

            if ($a['fcmovieseat']!="0")
            echo "
    		<tr class='table-background-second'>
            <td>First Class Child seats</td>
            <td>$12.00</td>
            <td>{$a['fcmovieseat']}</td>
            <td>{$_POST['fcmovieseatnum']}</td>
            <td>{$_POST['fcprice']}</td>
            </tr>";

            if ($a['b1movieseat']!="0")
            echo "
    		<tr class='table-background-second'>
            <td>Beanbag seats (single person)</td>
            <td>$12.00</td>
            <td>{$a['b1movieseat']}</td>
            <td>{$_POST['b1movieseatnum']}</td>
            <td>{$_POST['b1price']}</td>
            </tr>";

            if ($a['b2movieseat']!="0")
            echo "
    		<tr class='table-background-second'>
            <td>Beanbag seats (Up to 2 people)</td>
            <td>$12.00</td>
            <td>{$a['b2movieseat']}</td>
            <td>{$_POST['b2movieseatnum']}</td>
            <td>{$_POST['b2price']}</td>
            </tr>";

            if ($a['b3movieseat']!="0")
            echo "
    		<tr class='table-background-second'>
            <td>Beanbag seats (Up to 3 children)</td>
            <td>$12.00</td>
            <td>{$a['b3movieseat']}</td>
            <td>{$_POST['b3movieseatnum']}</td>
            <td>{$_POST['b3price']}</td>
            </tr>";
            echo "</form>";
            echo "</table>";
            echo "<br>";
            echo "<br>";
            echo "<td> <a id=\"moviename\" href=\"delete.php?moviename={$a['moviename']}\">Delete from Cart</a> </td>";
            echo "<br>";

    	}
    ?>
        <br>
        <br>
        <br>
        <div>
     <?php
            echo
            "Total: {$_POST['price']}
    <br>
    <br>
    Meal and Movie Deal Voucher: {$_POST['moviedealvoucher']}
    <br>
    <br>
    Grand Total: {$_POST['grandprice']}
    <br>
    <br>
    <br>
    Voucher Code: <input type='text' name='moviedealvoucher' id='moviedealvoucher'> <input type='submit' value='Apply'> <input type='submit' value='empty cart'>
    <input type='submit' value='Check out'>";
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
</div>
</body>
</html>