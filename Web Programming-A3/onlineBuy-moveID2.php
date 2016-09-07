<?php
session_start();
?>
<?php include_once ('includes/head.php');?>

<body>
                    <?php 
$handle = fopen("https://titan.csit.rmit.edu.au/~e54061/wp/moviesJSON.php","rb");
$content = ""; 
while (!feof($handle)) { 
    $content = fread($handle, 10000); 
}
fclose($handle);
$content = json_decode($content, true);
?>

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
        <?php include_once ('includes/nav.php');?>
        <!-- ~~~~Navigation area><!-->

        <!--Masthead area><!--
        <div id="big_Head" class="page-header fix_cloudsColor">
            <div class="container">
                <h1><img src="imgs/logo.png">
                    Silverado&nbsp;&nbsp;&nbsp;
                    <small>The best experience for film</small>
                </h1>
            </div>
        </div>
        <!-- ~~~↑ ↑Masthead area><!-->

        <!--big_2><!-->
         <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div class="slide">
                        <object data="<?php echo "{$content['CH']['trailer']}";?>" width="630" height="310"></object><br>
                    </div>
                </div>
            </div>
        </div>

		
        <!--move2text><!-->
        <div class="movie-info-text" class="tile tile-large-x"> 
      <div class="each-col f-right">
                                     <b><h1>movie details</h1></b><br>
                                                                            <div class="each-row">
                                            <div class="col-1"><?php echo "{$content['CH']['summary']}";?> </div>
                                            
                                        </div>
                                                                                                                  <div class="each-row">
                                            <div class="col-1"><img src="<?php echo "{$content['CH']['rating']}";?>"></div>
                                        
                                        </div>
                                                                                                                  
                                    </div>
                                    <div class="each-col f-left">
                                                                
                                                                                                                  <div class="each-row">
                                            <div class="col-1"><?php echo "{$content['CH']['description']['0']}"; ?><br><br>
                                                                <?php echo "{$content['CH']['description']['1']}"; ?><br><br>
                                                                <?php echo "{$content['CH']['description']['2']}"; ?></div>
                                            
                                        </div>
                                                                                                                  <div class="each-row">
                                            <div class="col-1"></div>
                            
                                                                                                                  <div class="each-row">
                                            <a href='movieList.php'><button type="button" class="btn btn-primary">Back to Select Movie</button></a>                                                                         
                                                                        </div>
                                                                        </div>
                                                                    </div>




        <!--footer -->
        <?php include_once ('includes/footer.php');?>
    <!--Main container end  ↑ ↑><!-->
</body>


</html>