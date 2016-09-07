<?php
session_start();
/*if ($_POST['username'] == '' || $_POST['email'] == '' || $_POST['phonenumber'] == '') {
	# code...
	header("location:basicinfo.php");
}*/
ob_start();//要清空缓存就必须ob_start()
$arr=$_SESSION["mycart"];
$temp=$arr[0];

if($temp['username']=="" ) {//未收到注册信息并且session里未查询到已登录
    header("location:basicinfo.php");//跳转到注册，这步可暂缓
    echo $temp['username'];
}
?>
<!DOCTYPE html>
<html>
<?php include_once ('includes/head.php');?>
<body>
    <!--Import the scripts><!-->
    
   <!-- <script>
   $(document).ready(function() {
      $.post("https://titan.csit.rmit.edu.au/~e54061/wp/moviesJSON.php",
      {
        'formaction':'#Onlinebuy'  
      },
      function(data) {
// Put all raw data in a <div> to see what is returned      
        $("#allmymovie").html(data);
    });
  });
  
    </script>-->
    <!-- begin movie detail -->
    <?php 
$handle = fopen("https://titan.csit.rmit.edu.au/~e54061/wp/moviesJSON.php","rb");
$content = ""; 
while (!feof($handle)) { 
    $content = fread($handle, 10000); 
}
fclose($handle);
?>


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
        <?php include_once ('includes/nav.php');?>
        <!-- ~~~~Navigation area><!-->

	<div id="allmymovie"></div>
       <div id="allmymovie"></div>
       <div id="allmymovie"></div>
       <div id="allmymovie"><div>

        <!--big_1><!-->
    <?php
    $content = json_decode($content, true);
        echo "
        </div>
        <div class=\"tile tile-super-x tile-large-y fixBigRect\">
            <div id=\"The Bourne Legacy\" class=\"tile-content\">
                <div class=\"carousel\" data-role=\"carousel\">
                    <div class=\"slide\">
                        <a href=\"onlineBuy-moveID1.php\"><img src=\"{$content['AF']['poster']}\" width=\"630\" height=\"310\"></a>
                    </div>
                </div>
                <span class=\"tile-label tileLabelFixed_L\">{$content['AF']['title']}</span>
            </div>
        </div>
        <!--moveinf1><!-->
      <div id=\"bigRect\" class=\"movie-info-text\" class=\"tile tile-large-x\">
                                	<b><h1>Story</h1></b>
										<br/>                                    
                                     <div class=\"each-col f-right\">
                                                                         	<div class=\"each-row\">
                                        	<div class=\"col-3\">{$content['AF']['summary']}</div>
		<button type=\"button\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#Onlinebuy\">Book Your Tickets</button>
		<a href=\"onlineBuy-moveID1.php\"><button type=\"button\" class=\"btn btn-primary\">Detail</button></a>
																			</div>
																		</div>
																

		<div id=\"big_Head\" class=\"page-header fix_cloudsColor\">
			<H1>NOW SHOWING</H1>
		</div>";
		?>
<!--end of  ModalOnlinebuy ><!-->	

 <!--big_2><!-->
        <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div class="slide">
                        <a href="onlineBuy-moveID2.php"><img src="<?php echo "{$content['CH']['poster']}"; ?>" width="630" height="310"></a>
                    </div>
                </div>
                <span class="tile-label tileLabelFixed_L"><?php echo "{$content['CH']['title']}"; ?></span>
            </div>
        </div>

        
                     <!--move2inf><!-->
      <div id="The Godfather" class="movie-info-text" class="tile tile-large-x">
                                    <b><h1>Story</h1></b>
                                        <br/>                                    
                                     <div class="each-col f-right">
                                                                            <div class="each-row">
                                            <div class="col-3"><?php echo "{$content['CH']['summary']}";?></div>
                                                                            </div>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#Onlinebuy">Book Your Tickets</button>
        <a href="onlineBuy-moveID2.php"><button type="button" class="btn btn-primary">Detail</button></a>
                                                                        </div>
                                                                    </div>


       <div id="big_Head" class="page-header fix_cloudsColor">
            <H1>COMING SOON</H1>
        </div>
                        <!--big_5><!-->
        <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div class="slide">
                        <a href="onlineBuy-moveID3.php"><img <img src="<?php echo "{$content['AC']['poster']}";?>" width="630" height="310"></a>
                    </div>
                </div>
                <span class="tile-label tileLabelFixed_L"><?php echo "{$content['AC']['title']}";?> </span>
            </div>
        </div>
                             <!--move5inf><!-->
      <div id="Titanic" class="movie-info-text" class="tile tile-large-x">
                                    <b><h1>Story</h1></b>
                                        <br/>                                    
                                     <div class="each-col f-right">
                                                                            <div class="each-row">
                                            <div class="col-3"><?php echo "{$content ['AC'] ['summary']}";?></div>
                                                                            </div>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#Onlinebuy">Book Your Tickets</button>
        <a href="onlineBuy-moveID3.php"><button type="button" class="btn btn-primary">Detail</button></a>
                                                                        </div>
                                                                    </div>



        <div id="big_Head" class="page-header fix_cloudsColor">
            <H1>NOW SHOWING</H1>
        </div>
                        <!--big_4><!-->
        <div id="bigRect" class="tile tile-super-x tile-large-y fixBigRect">
            <div class="tile-content">
                <div class="carousel" data-role="carousel">
                    <div class="slide">
                        <a href="onlineBuy-moveID4.php"><img src="<?php echo "{$content['RC']['poster']}";?>" width="630" height="310"></a>
                    </div>
                </div>
                <span class="tile-label tileLabelFixed_L"><?php echo "{$content['RC']['title']}";?></span>
            </div>
        </div>
                             <!--move4inf><!-->
      <div id="Case Closed: Sunflowers of Inferno" class="movie-info-text" class="tile tile-large-x">
                                    <b><h1>Story</h1></b>
                                        <br/>                                    
                                     <div class="each-col f-right">
                                                                            <div class="each-row">
                                            <div class="col-3"><?php echo "{$content ['RC']['summary']}";?></div>
                                                                            </div>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#Onlinebuy">Book Your Tickets</button>
        <a href="onlineBuy-moveID4.php"><button type="button" class="btn btn-primary">Detail</button></a>
                                                                        </div>
                                                                    </div>



        <div id="big_Head" class="page-header fix_cloudsColor">
            <H1>NOW SHOWING</H1>
        </div>
        
        <br/>
        <br/>
        <br/>


        <!--footer -->
<?php include_once ('includes/footer.php');?>
    <!--Main container end  ↑ ↑><!-->        

<!-- ModalOnlinebuy -->
    <div class="modal fade" id="Onlinebuy" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" background-color="black">
        <div class="modal-dialog" role="document">
            <div class="modal-onlineBuy">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Online buy</h4>
                </div>
                <div class="modal-body">
                    <div class="inp">
</br>
        </br>
        </br>
              <div class="select-etix-cont" id="div-select-etix-cont">
                <H1>Book your tickets here!</H1>
                    <div class="clearboth"></div>
                    </br>           <div class="each-row title-row">
                    <div class="col-1 fut-font" style="width:200px">Type</div>
                    <div class="col-2 fut-font" style="width:190px">Amount</div>
                    <div class="col-3 fut-font">Price</div>
                    <div class="clearboth"></div>
                    </br>
                    <!-- begin booking -->
                      <form name='book'  method='post'action="db.php">
                    <input type="hidden" id="username" name="username" value="<?php echo "{$temp['username']}"; ?>">
                    <input type="hidden" id="email" name="email" value="<?php echo "{$temp['email']}"; ?>">
                    <input type="hidden" id="phonenumber" name="phonenumber" value="<?php echo "{$temp['phonenumber']}"; ?>">

                    <div class="col-1 fut-font" style="width:200px" id='ass'>Film: </div>
                    <div class="col-2" style="width:190px">
                      <div class="selectWrap172">
                    <select name='moviename' id ='moviename'  required='required' onchange="filter()" >
                        <option value='' selected='selected' disabled class='firstchild'>Please Select a Film</option>
                        <option value='<?php echo "{$content['CH']['title']}"; ?>' id='CH'><?php echo "{$content['CH']['title']}"; ?></option>
                        <option value='<?php echo "{$content['AF']['title']}"; ?>' id='AF'><?php echo "{$content['AF']['title']}"; ?></option>
                        <option value='<?php echo "{$content['RC']['title']}"; ?>' id='RC'><?php echo "{$content['RC']['title']}"; ?></option>
                        <option value='<?php echo "{$content['AC']['title']}"; ?>' id='AC'><?php echo "{$content['AC']['title']}"; ?></option>
                    </select>
                    </div>
                    </div>
                    <div class="clearboth"></div>
                    </br>
                <!--Select Head  ↑ ↑><!-->

                    <div class="each-row ticket-row">
                        <div class="col-1 text" style="width:200px">Day: </div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='movieday' id='movieday' required='required' onchange="filter()">
                        <option value='' selected='selected' disabled>Please Select a Day</option>
                        <option value='monday' id='monday'>Monday</option>
                        <option value='tuesday' id='tuesday'>Tuesday</option>
                        <option value='wednesday' id='wednesday'>Wednesday</option>
                        <option value='thursday' id='thursday'>Thursday</option>
                        <option value='friday' id='friday'>Friday</option>
                        <option value='saturday' id='saturday'>Saturday</option>
                        <option value='sunday' id='sunday'>Sunday</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="clearboth"></div>
                    </br>
                <!--Select day  ↑ ↑><!-->        
                    
                    <div class="each-row ticket-row">
                        <div class="col-1 text" style="width:200px">Time: </div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='movietime' id='movietime' required='required' onchange="filter()" >
                        <option value='' selected='selected' disabled>Please Select a Time</option>
                        <option value='12pm' id='12pm'>12pm</option>
                        <option value='1pm' id='1pm'>1pm</option>
                        <option value='3pm' id='3pm'>3pm</option>
                        <option value='6pm' id='6pm'>6pm</option>
                        <option value='9pm' id='9pm'>9pm</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="clearboth"></div>
                    </br>
                <!--Select time  ↑ ↑><!-->        
                    
                    <div class="each-row ticket-row">                   
                        <div class="col-1 text" style="width:200px"> Adult seats:</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='samovieseat' id='samovieseat' required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id='saPrice'> &#36;0</span></div>
                    <div class="clearboth"></div>
                    <br/>
                <!--Select Adult  ↑ ↑><!-->        
                    <div class="each-row ticket-row">                                       
                        <div class="col-1 text" style="width:200px"> Concession seats:</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='spmovieseat' id='spmovieseat'required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id='spPrice'> &#36;0</span></div>
                    <div class="clearboth"></div>
                    <br/>
                <!--Select Concession  ↑ ↑><!-->        
                    <div class="each-row ticket-row">                                       
                        <div class="col-1 text" style="width:200px"> Child seats:</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='scmovieseat' id='scmovieseat' required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id='scPrice'> &#36;0</span></div>
                    <div class="clearboth"></div>
                    <br/>
                <!--Select Child  ↑ ↑><!-->        
                    
                    <div class="each-row ticket-row">                                                   
                        <div class="col-1 text" style="width:200px"> First Class Adult seats:</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='famovieseat' id='famovieseat' required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id='faPrice'> &#36;0</span></div>         
                    <div class="clearboth"></div>
                    <br/>
                <!--Select FCA  ↑ ↑><!-->        
                    <div class="each-row ticket-row">                                       
                        <div class="col-1 text" style="width:200px"> First Class Child seats:</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='fcmovieseat' id='fcmovieseat' required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id='fcPrice'> &#36;0</span></div>
                    <div class="clearboth"></div>
                    <br/>
                <!--Select FCC  ↑ ↑><!-->        
                    <div class="each-row ticket-row">                                       
                        <div class="col-1 text" style="width:200px"> Beanbag seats (single person):</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='b1movieseat' id='b1movieseat' required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id='b1Price'> &#36;0</span></div>
                    <div class="clearboth"></div>
                    <br/>
                <!--Select B1P  ↑ ↑><!-->        
                    <div class="each-row ticket-row">                                       
                        <div class="col-1 text" style="width:200px"> Beanbag seats (Up to 2 people):</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='b2movieseat' id='b2movieseat' required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                    </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id='b2Price'> &#36;0</span></div>
                    <div class="clearboth"></div>
                    </br>
                <!--Select B2P  ↑ ↑><!-->        
                    <div class="each-row ticket-row">                                       
                        <div class="col-1 text" style="width:200px"> Beanbag seats (Up to 3 children):</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='b3movieseat' id='b3movieseat' data-style="btn-primary" required='required' onchange="filter()">
                        <option value='0' selected='selected'>0</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                        <option value='8'>8</option>
                        <option value='9'>9</option>
                        <option value='10'>10</option>
                       </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-1 text"><span id="b3Price"> &#36;0</span></div>
                    <div class="clearboth"></div>
                    <!--Select B3P  ↑ ↑><!-->    
                    <br/>
                        <!--Membership-->
                        <!--<div class="each-row ticket-row">                                       
                        <div class="col-1 text" style="width:200px"> Membership:</div>
                        <div class="col-2" style="width:190px">
                            <div class="selectWrap172">
                    <select name='discount' id='discount' data-style="btn-primary" required='required' onchange="filter()">
                        <option id='Normal' value='Normal' selected='Normal'>Normal</option>
                        <option id='Golden' value='Golden'>Golden(50% discount)</option>
                        <option id='Silver' value='Silver'>Silver(70% discount)</option>
                        <option id='Student' value='Student'>Student(85% discount)</option>
                       </select>
                            </div>
                        </div>
                    </div>
                    </div>
                    </div>
                    </br>-->
                    <div class="col-1 text" style="width:200px">Total Price: <span id="totalPrice"> &#36;0</span></div>
                    <br/>
                    <button  type='submit' class="btn btn-success" name='addToCart' value='Add to Cart'>Add to Cart</button>
                    <a href= "cart-page.php"><button class="btn btn-primary" name='viewCart'>viewcart</button></a>

                  <!--end of .gray-box-inner-->
                  
             <!--begin discount -->
<!-- end discount-->
                <!--end of inner-->
                      </form>

                  </div>

              </div>
                    </div>
            </div>
        </div>
    </div>
</div>  
<!--end of  ModalOnlinebuy ><!-->   

<script src="js/myjs2forPHP.js"></script>

</body>


</html>