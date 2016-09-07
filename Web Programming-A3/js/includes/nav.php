<!--Navigation area><!-->
<?php 
$homename= "Home";

$movieinfo = "Movie Information";

$movienews ="News"; 
?>
        <nav class="nav nav-pills navbar-static-top fix_UpAndDownForZero">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href= "index.php"><button class="button button-small button-plain button-border" >
                            <b><?php echo "$homename"; ?></b>
                            
                        </button></a>
                    </li>
                    <li>
                        <a href="movieList.php"><button class="button button-small button-plain button-border" >
                            <b><?php echo "$movieinfo"; ?></b>
                        </button></a>
                    </li>
                    <li>
                        <a href="newsList.php"><button class="button button-small button-plain button-border" href="News.html">
                            <b><?php echo "$movienews " ; ?></b>
                        </button></a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- ~~~~Navigation area><!-->