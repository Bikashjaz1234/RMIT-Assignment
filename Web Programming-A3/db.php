<?php
session_start();//ʹ��session֮ǰһ��Ҫ��session����
ob_start();//Ҫ��ջ���ͱ���ob_start()
$moviename="{$_POST['moviename']}";
$movieday="{$_POST['movieday']}";
$movietime="{$_POST['movietime']}";
$movierating="{$_POST['movierating']}";
$samovieseat="{$_POST['samovieseat']}";
$spmovieseat="{$_POST['spmovieseat']}";
$scmovieseat="{$_POST['scmovieseat']}";
$famovieseat="{$_POST['famovieseat']}";
$fcmovieseat="{$_POST['fcmovieseat']}";
$b1movieseat="{$_POST['b1movieseat']}";
$b2movieseat="{$_POST['b2movieseat']}";
$b3movieseat="{$_POST['b3movieseat']}";
$username = "{$_POST['username']}";
$email = "{$_POST['email']}";
$phonenumber = "{$_POST['phonenumber']}";
$arr=$_SESSION["mycart"];
$temp=$arr[0];
$seat=$_SESSION["seatList"];

if($_POST['moviename'] == '')
{
    $temp = [
        "username" => $username,
        "email" => $email,
        "phonenumber" => $phonenumber,
        "moviename" => $moviename,
        "movieday" => $movieday,
        "movietime" => $movietime,
        "samovieseat" => $samovieseat,
        "spmovieseat" => $spmovieseat,
        "scmovieseat" => $scmovieseat,
        "famovieseat" => $famovieseat,
        "fcmovieseat" => $fcmovieseat,
        "b1movieseat" => $b1movieseat,
        "b2movieseat" => $b2movieseat,
        "b3movieseat" => $b3movieseat,
        "bookingID" => $bookingID];
    $arr[0]=$temp;
    echo $_POST['moviename'];
    //build seat list
    /*$eachShit=[0,0,0,0,0,0,0,0,0,0];
    $seatOnMovie=['CH','AF','RC','AC'];
    $seatOnDay=['monday','tuesday','wednesday'.'thursday','friday','saturday','sunday'];
    $seatOnTime=['12pm',"1pm","3pm","6pm","9pm"];
    $seatOnSeat=["shitOnSa","shitOnSp","shitOnSc","shitOnFa","shitOnFc","shitOnB1","shitOnB2","shitOnB3"];
    $seatOnSeat['shitOnSa']=$eachShit;
    $seatOnSeat['shitOnSp']=$eachShit;
    $seatOnSeat['shitOnSc']=$eachShit;
    $seatOnSeat['shitOnFa']=$eachShit;
    $seatOnSeat['shitOnFc']=$eachShit;
    $seatOnSeat['shitOnB1']=$eachShit;
    $seatOnSeat['shitOnB2']=$eachShit;
    $seatOnSeat['shitOnB3']=$eachShit;
    $seatOnTime['12pm']=$seatOnSeat;
    $seatOnTime['1pm']=$seatOnSeat;
    $seatOnTime['3pm']=$seatOnSeat;
    $seatOnTime['6pm']=$seatOnSeat;
    $seatOnTime['9pm']=$seatOnSeat;
    $seatOnDay['monday']=$seatOnTime;
    $seatOnDay['tuesday']=$seatOnTime;
    $seatOnDay['wednesday']=$seatOnTime;
    $seatOnDay['thursday']=$seatOnTime;
    $seatOnDay['friday']=$seatOnTime;
    $seatOnDay['saturday']=$seatOnTime;
    $seatOnDay['sunday']=$seatOnTime;
    $seatOnMovie['CH']=$seatOnDay;
    $seatOnMovie['AF']=$seatOnDay;
    $seatOnMovie['RC']=$seatOnDay;
    $seatOnMovie['AC']=$seatOnDay;


    $seat=$seatOnMovie;
    $_SESSION["seatList"]=$seat;
    print_r($seat);
    */
}

if(isset($_SESSION['bookingID'])&&$temp['moviename']!='') {
    $_SESSION['bookingID'] = $_SESSION['bookingID'] + 1;
    $bookingID = $_SESSION['bookingID'];
}
else {
    $_SESSION['bookingID'] = 0;
    $bookingID = 0;
}
//��session�еı���ȡ����
//�������ж���������Ƿ�������,���Եõ���ǰ�Ƿ��������
/*if(is_array($arr))
{
//��������飬˵����ǰ�������
//�����������ַ����������
    if(array_key_exists($moviename,$arr))
    {
        //1��array_key_exists($pid,$arr)�ж�$arr���Ƿ���ڼ�ֵΪ$pid��һ��һά���飬������ڵĻ�����ѯ�õ�Ӱ��ʱ���Ʊ���Ƿ����
        //
        $temp=$arr[$moviename]; //�Ӷ�ά�������ó���Ӧ��һά�����ݴ�
        if($temp["moviename"]==$moviename && $temp["movietime"]==$movietime && $temp["movieday"] == $movieday){
            $temp["samovieseat"] +=$samovieseat;
            $temp["scmovieseat"] +=$scmovieseat;
            $temp["famovieseat"] +=$famovieseat;
            $temp["fcmovieseat"] +=$fcmovieseat;
            $temp["b1movieseat"] +=$b1movieseat;
            $temp["b2movieseat"] +=$b2movieseat;
            $temp["b3movieseat"] +=$b3movieseat;
            $arr[$moviename]= $temp;//�黹ֵ��bookingID���ֲ���
        }
    //2����ʱ��������ڻ���������һ�������ϣ����½�bookingID
        else if($temp["movietime"]!==$movietime or $temp["movieday"] !== $movieday or $temp["moviename"]!==$moviename){
            $bookingID=0;
            $arr[$moviename]=array($bookingID,
                "moviename"=>$moviename,
                "movieday"=>$movieday,
                "movietime"=>$movietime,
                "samovieseat"=>$samovieseat,
                "spmovieseat"=>$spmovieseat,
                "scmovieseat"=>$scmovieseat,
                "famovieseat"=>$famovieseat,
                "fcmovieseat"=>$fcmovieseat,
                "b1movieseat"=>$b1movieseat,
                "b2movieseat"=>$b2movieseat,
                "b3movieseat"=>$b3movieseat);
        }
    else
    {   //2.����Ʒ��һ�ι��򣬾ͽ��õ�������ֵ���һ��һά����
        $bookingID=0;
        $arr[$bookingID]=array(
            "moviename"=>$moviename,
            "movieday"=>$movieday,
            "movietime"=>$movietime,
            "samovieseat"=>$samovieseat,
            "spmovieseat"=>$spmovieseat,
            "scmovieseat"=>$scmovieseat,
            "famovieseat"=>$famovieseat,
            "fcmovieseat"=>$fcmovieseat,
            "b1movieseat"=>$b1movieseat,
            "b2movieseat"=>$b2movieseat,
            "b3movieseat"=>$b3movieseat
                                            );
    }
}
else
{    $bookingID=0;
    $arr[$bookingID]=array(
        "moviename"=>$moviename,
        "movieday"=>$movieday,
        "movietime"=>$movietime,
        "samovieseat"=>$samovieseat,
        "spmovieseat"=>$spmovieseat,
        "scmovieseat"=>$scmovieseat,
        "famovieseat"=>$famovieseat,
        "fcmovieseat"=>$fcmovieseat,
        "b1movieseat"=>$b1movieseat,
        "b2movieseat"=>$b2movieseat,
        "b3movieseat"=>$b3movieseat
    );
}
*/
//��iѹ������
for($i=0;$i<$samovieseat;$i++)
{$s=$i+1;
    $selectSa[]="SA $s";}
for($i=0;$i<$spmovieseat;$i++)
{$s=$i+1;
    $selectSp[]="SP $s";}
for($i=0;$i<$scmovieseat;$i++)
{$s=$i+1;
    $selectSc[]="SC $s";}
for($i=0;$i<$famovieseat;$i++)
{$s=$i+1;
    $selectFa[]="FA $s";}
for($i=0;$i<$fcmovieseat;$i++)
{$s=$i+1;
    $selectFc[]="FC $s";}
for($i=0;$i<$b1movieseat;$i++)
{$s=$i+1;
    $selectB1[]="B1 $s";}
for($i=0;$i<$b2movieseat;$i++)
{$s=$i+1;
    $selectB2[]="B2 $s";}
for($i=0;$i<$b3movieseat;$i++)
{$s=$i+1;
    $selectB3[]="B3 $s";}





        $arr[$_SESSION['bookingID']] = array(
        "username" => $temp['username'],
        "email" => $temp['email'],
        "phonenumber" => $temp['phonenumber'],
        "moviename" => $moviename,
        "movieday" => $movieday,
        "movietime" => $movietime,
        "samovieseat" => $samovieseat,
        "spmovieseat" => $spmovieseat,
        "scmovieseat" => $scmovieseat,
        "famovieseat" => $famovieseat,
        "fcmovieseat" => $fcmovieseat,
        "b1movieseat" => $b1movieseat,
        "b2movieseat" => $b2movieseat,
        "b3movieseat" => $b3movieseat,
        "bookingID" => $bookingID,
        "selectSa"=>$selectSa,
        "selectSp"=>$selectSp,
        "selectSc"=>$selectSc,
        "selectFa"=>$selectFa,
        "selectFc"=>$selectFc,
        "selectB1"=>$selectB1,
        "selectB2"=>$selectB2,
        "selectB3"=>$selectB3,);







    $_SESSION["mycart"]=$arr;//������󣬽����������·���session�У�������ڸ���ҳ�濴����session
ob_clean();//��ջ���
    header("location:movieList.php");//��ת�����ﳵ����(cart-page.php)���ⲽ���ݻ�
?>
