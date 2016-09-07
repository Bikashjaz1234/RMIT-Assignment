<?php
session_start();
?>
<?php 
include_once ('includes/head.php');?>

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
    <?php include_once "includes/nav.php";?>
        <!-- ~~~~Navigation area><!-->


        <!--big_1><!-->
        <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div id="new cinema" class="slide">
                        <a href="news-1.html"><img src="imgs/bigRectRollingImgs/New Seats.jpg"></a>
                    </div>
                </div>
                <span class="tile-label tileLabelFixed_L">New Cinema Detail</span>
            </div>
        </div>


		
        <!--moveinf1><!-->
      <div class="movie-info-text" class="tile tile-large-x">
                                	<b><h1>Information</h1></b>
										<br>                                    
                                     <div class="each-col f-right">
                                                                         	<div class="each-row">
                                        	<div class="col-3">The cinema reopen! The new cinema installed a lot of new equipments and new seats.</div>
																				</div>
											<a href="news-1.html"><button type="button" class="btn btn-success">More Information</button></a>
																			</div>
																		</div>
																

		<div id="big_Head" class="page-header fix_cloudsColor">
			<H1>Other News</H1>
		</div>
		
        <!--big_2><!-->
        <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div id="membership" class="slide">
                        <a href="news-2.html"><img src="imgs/bigRectRollingImgs/membership.jpg"></a>
                    </div>
                </div>
                <span class="tile-label tileLabelFixed_L">Membership</span>
            </div>
        </div>

		
		             <!--move2inf><!-->
      <div class="movie-info-text" class="tile tile-large-x">
                                	<b><h1>Information</h1></b>
										<br>                                    
                                     <div class="each-col f-right">
                                                                         	<div class="each-row">
                                        	<div class="col-3">If you become to the membership, you will have a big discount.</div>
																			</div>
											<a href="news-2.html"><button type="button" class="btn btn-success">More Information</button></a>
																		</div>
																	</div>
																	</div>
																	</div>


		</br>
		</br>
		</br>


        <!--footer -->
<?php include_once"includes/footer.php";?>
    <!--Main container end  ↑ ↑><!-->        

	

	
</body>


</html>