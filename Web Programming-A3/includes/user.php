<?php
session_start();//ʹ��session֮ǰһ��Ҫ��session����
ob_start();//Ҫ��ջ���ͱ���ob_start()
$arr=$_SESSION["mycart"];
$temp=$arr[0];
if($_POST['username'] == '' ) {
    header("location:basicinfo.php");//��ת��ע�ᣬ�ⲽ���ݻ�
}
else {
    echo "Welcome��Customer";
    header("location:movieList.php");
}
//$userID="{$_POST['username']}";
//$userEmail="{$_POST['email']}";
//$userPhone="{$_POST['phonenumber']}";

/*if(isset($_SESSION['user'])) {
$user[0] = ['login'=>0, "username"=>'tempuser', "email"=>'tempmail', "phonenumber"=>'tempphone'];//��ʼ��
if ($_POST['username'] == '' || $_POST['email'] == '') {//δ��ֵ����ԭ��
$user[0] = ['login'=>0, "username"=>'tempuser', "email"=>'tempmail', "phonenumber"=>'tempphone'];
} else {
for ($i=1;$i++;$i<100){//Ѱ��ƥ��
if($user[$i]['username']==$userID){
$user[0] = ['login'=>1, "username"=>$userID, "email"=>$userEmail, "phonenumber"=>$userPhone];
}
}
}
}
else {//��ʼ��
$user[0] = ['login'=>0, "username"=>'tempuser', "email"=>'tempmail', "phonenumber"=>'tempphone'];
}
*/
?>


