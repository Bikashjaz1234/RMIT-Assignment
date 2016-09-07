<?php
session_start();
include_once ('includes/head.php');
echo $temp['username'];

?>
    <body>
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


        <!--big_1><!-->
        <div id="basicinfo" class="basicinfo" style="width:600px;margin-left:auto;margin-right:auto;">
						    <form action="db.php" method="post"><br><br>
                            Username:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="username" name="username" type="text" required="required" pattern="^[a-zA-Z_]*$" title="You can only write characters" /><label id="error"></label><br><br>
                            EMAIL:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="email" required="required" name="email" type="email"></br><br>
                            Phonenumber:&nbsp;<input id="phonenumber" required="required" name="phonenumber" type="text" pattern="^((\+614)|(04)|\(04\))\d{8}" title="Phone number is +614 or 04 or (04) + 8 numbers"><label id="error"></label></br><br>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset">&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" onclick="f_check_letter(document.all.text.value)">
                        </form>
        </div>
        <!--move1text><!-->
        <!--footer -->
<?php include_once("includes/footer.php"); ?>
<!--Main container end  ↑ ↑><!-->        
</body>


</html>