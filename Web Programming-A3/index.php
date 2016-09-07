<?php 
session_start();
$validPages = array ('', 'onlineBuy-moveID1', 'onlineBuy-moveID2', 'onlineBuy-moveID3',
'onlineBuy-moveID4', 'onlineBuy-moveID5', 'onlineBuy-moveID6', 'movieList', 'movietimetable','newsList',
'news-1', 'news-2');
$pageTitle = "Silverado Cinema";
$pageContent= 'index.php';
$page = "";
if (isset ($_GET['page'])){
$page = $_GET['page'];
}

if (in_array($page, $validPages)){
$pageContent = 'a3/'.$page.'.php';
	switch ($page){
	case '':
		$pageContent = "index.php";
		$pageTitle ="Silverado Cinema";
	break;
	
	case 'onlineBuy-moveID1':
	$pageTitle = "Silverado - movie1";
	break;
	
	case 'onlineBuy-moveID2' :
	$pageTitle = "Silverado - movie2";
	break;
	
	case 'onlineBuy-moveID3':
	$pageTitle = "Silverado - movie3";
	break;
	
	case 'onlineBuy-moveID4':
	$pageTitle = "Silverado - movie4";
	break;
	
	case 'onlineBuy-moveID5':
	$pageTitle = "Silverado - movie5";
	break;
	
	case 'onlineBuy-moveID6':
	$pageTitle ="Silverado - movie6";
	break;
	
	case 'movielist':
	$pageTitle = "Silverado -movielist";
	break;
	
	case 'movietimetable':
	$pageTitle = "Silverado -movietimetable";
	break;
	
	case 'news-1':
	$pageTitle = "Silverado - news1";
	break;
	
	case 'news-2':
	$pageTitle = "Silverado - news2";
	break;
	case 'newsList':
	$pageTitle = "Silverado - newList";
	break;
	
}
}
?>

<?php include_once ('includes/head.php');?>


<body>
    <!--Import the scripts>
    <script src="js/jquery-2.1.4.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/teninedialog1.0.0.js"></script>
    <script src="js/metro.min.js"></script>
    <script src="js/SilveradoMain.js"></script>
    <!-Import the scripts><!-->

    <!--Main container here ↓ ↓><!-->
    <div class="container">
        <div id="big_Head" class="page-header fix_cloudsColor">
            <div class="container">
				<div class="left">
                <a href= "index.php"><h1><img src="imgs/logo.png" width="150" height="150"></a>
                    Silverado&nbsp;&nbsp;&nbsp;
                    <small>The best experience for film</small>
                </h1>
				</div>
            </div>
        </div>
        <!--Navigation area><!-->
      <?php include_once "includes/nav.php";?>
        <!-- ~~~~Navigation area><!-->

        <!--big_1><!-->
        <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div class="slide">
                        <a href='movieList.php#The Bourne Legacy' target="_blank"><img src="imgs/bigRectRollingImgs/BRRI_1.png"></a>
						<span class="tile-label tileLabelFixed_L">The Bourne Legacy</span>
                    </div>
                    <div class="slide">
                        <a href='movieList.php#The Godfather' target="_blank"><img src="imgs/bigRectRollingImgs/BRRI_2.png"></a>
						<span class="tile-label tileLabelFixed_L">The Godfather</span>
                    </div>
                    <div class="slide">
                        <a href='movieList.php#Despicable Me 2' target="_blank"><img src="imgs/bigRectRollingImgs/BRRI_6.png"></a>
						<span class="tile-label tileLabelFixed_L">Case Closed: Sunflowers of Inferno</span>
                    </div>
                    <div class="slide">
                        <a href='movieList.php#Titanic' target="_blank"><img src="imgs/bigRectRollingImgs/BRRI_5.png"></a>
						<span class="tile-label tileLabelFixed_L">Titanic</span>
                    </div>
                </div>
            </div>
        </div>

        <!--new seats><!-->
        <div id="newSeats" class="tile tile-large-x">
            <a href='news-1.php' target="_blank"><img src="imgs/310wide/seats_TilePic.png"/></a>
            <span class="tile-label tileLabelFixed_L">The Cinema Reopen</span>
        </div>

        <!--Membership><!-->
        <div id="Membership" class="tile tile-large-x">
			<a href='news-2.php' target="_blank"><img src="imgs/310wide/membership.jpg"/></a>
			<span class="tile-label tileLabelFixed_L">Membership</span>
        </div>

        <!--Dolby><!-->
        <div id="DolbySystem" class="tile tile-large-x">
            <a href='movieList.php#Case Closed: Sunflowers of Inferno' target="_blank"><img src="imgs/310wide/Japaness.png"/ ></a>
            <span class="tile-label tileLabelFixed_L">Japaness Moive</span>
        </div>

        <!--ContentUS><!-->
        <div id="ContentUS" class="tile tile-large-x fixContent" data-toggle="modal" data-target="#myModal">
            <span class="tile-label tileLabelFixed">Content us</span>
            <span class="mif-contacts-dialer mif-4x fix_cloudsColor fixIconInWideTitle"></span>
        </div>

        <!--TICKETS><!-->
        <div id="ticketsNow"  class="tile tile-large-x">
             <a href='movieList.php' target="_blank"><img src="imgs/310wide/sell-tickets.jpg"/ ></a>
            <span class="tile-label tileLabelFixed_L">Booking Now</span>
        </div>
</div>
</div>
</div>
</br>
</br>
</br>
</br>

        <!--footer -->
<?php include_once"includes/footer.php";?>
    <!--Main container end  ↑ ↑><!-->


    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Contact Form</h4>
                </div>
                <div class="modal-body">
					<div class="inp">
						<form name="contact" action="http://titan.csit.rmit.edu.au/~e54061/wp/testcontact.php" method="post">
							<select size="1" name="subject" >
								<option value="generalenquiry">General Enquiry</option>
								<option value="book">Group and Corporate Bookings</option>
								<option value="suggestion">Suggestions & Complaints</option>
							</select>
							<input type="text" placeholder="Name" required=" ">
							<input type="email" placeholder="Email" name="email" required=" ">
							<textarea placeholder="Message" name="message" required=" "></textarea>
							<input type="submit" value="submit">
							<input type="reset"  value="RESET">
						</form>
					</div>
            </div>
        </div>
    </div>
</div>
</body>


</html>
