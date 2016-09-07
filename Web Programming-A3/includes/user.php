<?php
session_start();//使用session之前一定要将session开启
ob_start();//要清空缓存就必须ob_start()
$arr=$_SESSION["mycart"];
$temp=$arr[0];
if($_POST['username'] == '' ) {
    header("location:basicinfo.php");//跳转到注册，这步可暂缓
}
else {
    echo "Welcome，Customer";
    header("location:movieList.php");
}
//$userID="{$_POST['username']}";
//$userEmail="{$_POST['email']}";
//$userPhone="{$_POST['phonenumber']}";

/*if(isset($_SESSION['user'])) {
$user[0] = ['login'=>0, "username"=>'tempuser', "email"=>'tempmail', "phonenumber"=>'tempphone'];//初始化
if ($_POST['username'] == '' || $_POST['email'] == '') {//未得值保持原样
$user[0] = ['login'=>0, "username"=>'tempuser', "email"=>'tempmail', "phonenumber"=>'tempphone'];
} else {
for ($i=1;$i++;$i<100){//寻找匹配
if($user[$i]['username']==$userID){
$user[0] = ['login'=>1, "username"=>$userID, "email"=>$userEmail, "phonenumber"=>$userPhone];
}
}
}
}
else {//初始化
$user[0] = ['login'=>0, "username"=>'tempuser', "email"=>'tempmail', "phonenumber"=>'tempphone'];
}
*/
?>


