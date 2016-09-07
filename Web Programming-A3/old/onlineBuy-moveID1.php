<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Welcome to Silverado</title>
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
</head>

<body>
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


        <!--big_1><!-->
        <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div class="slide">
						<a href='movieList.html#The Bourne Legacy' target="_blank"><img src="imgs/bigRectRollingImgs/BRRI_1.png"></a>
					</div>
                </div>
                <span class="tile-label tileLabelFixed_L">The Bourne Legacy</span>
            </div>
        </div>


		
        <!--move1text><!-->
      <div class="movie-info-text" class="tile tile-large-x">                                   
                                     <div class="each-col f-right">
									 <b><h1>movie details</h1></b><br>
                                                                         	<div class="each-row">
                                        	<div class="col-1">Director:</div>
                                            <div class="col-2">Tony Gilroy</div>
                                        </div>
                                                                                                                  <div class="each-row">
                                        	<div class="col-1">Cast:</div>
                                            <div class="col-2"><p><span>Jeremy Renner</span>,&nbsp;<span>Rachel Weisz</span>,&nbsp;<span>Edward Norton</span></p></div>
                                        </div>
                                                                                                                  
                                    </div>
                                    <div class="each-col f-left">
                                                                         	<div class="each-row">
                                        	<div class="col-1">Release:</div>
                                            <div class="col-2">03/09/2015</div>
                                        </div>
                                                                                                                  <div class="each-row">
                                        	<div class="col-1">Length:</div>
                                            <div class="col-2">135 Mins</div>
                                        </div>
                                                                             
                                        <div class="each-row">
                                        	<div class="col-1">Genre:</div>
                                            <div class="col-2">Action/Comedy</div>
                                        </div>
                                                                                                                  <div class="each-row">
                                        	<div class="col-1">Distributor:</div>
                                            <div class="col-2">Universal Pictures</div>
                                        </div>
                                                                                                                  <div class="each-row">
                                        	<div class="col-1">Rating:</div>
                                            <div class="col-2 movie_rating">
                                                                                            <img src="imgs/rate/PG.png" alt="" width="20" align="absmiddle" title="" class="tooltip-sw"/>
                                            											<div> (PG-13 Parents Strongly Cautioned)</div>
																				</div>
																			</div>
                                                                                                                  <div class="each-row">
											<a href='movieList.html#The Bourne Legacy'><button type="button" class="btn btn-primary">Back to Select Movie</button></a>																			
																		</div>
																		</div>
																	</div>





        <!--footer -->
        <nav class="nav nav-pills navbar-fixed-bottom tileLabelFixed_L fixBottom">
   	<div class="footer">
		<div class="container">
			<div class="footer-left">
				<p>Copyright &copy; 2015.Company Silverado All rights reserved.<a target="_blank" href="#"></a></p>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
		</nav>
<!--Main container end  ↑ ↑><!-->        
</body>


</html>