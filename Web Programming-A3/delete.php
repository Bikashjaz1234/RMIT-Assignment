<?php
session_start();//启动session
ob_start();//清空缓存必须启动的项
$bookingID=$_GET["bookingID"];//得到通过get方式传过来的id
$arr=$_SESSION["mycart"];//拿出session里的二维数组
foreach($arr as $key => $mid)//遍历该二维数组中的键值，这里也就是商品的id
{
     if($key==$bookingID)//判断键值等于传过来的商品id
     {
          unset($arr[$key]);//清除该一维数组
     }
}
$_SESSION["mycart"]=$arr;//将清除之后的二维数组重新放到session里
ob_clean();//清除缓存
header("location:cart-page.php");
?>