<?php
session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
//���͸�ҳ�������Է����û���ȡ�����鱨
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
Please Enter Your Deal Voucher��<input type="text" name="Voucher1">
    -<input type="text" name="Voucher2">
    -<input type="text" name="Voucher3">
    <input type="submit" name="submit" value="submit">
</form>
/*�������
���յ�post����ȡ1��2��3��4��5λ���㣬�Աȵ�������list����
�ɹ�������true��
*/
<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
$Voucher1 = $_POST["Voucher1"];
$Voucher2 = $_POST["Voucher2"];
$Voucher3 = $_POST["Voucher3"];
$CHK1 = (($Voucher1[0] * $Voucher1[1] + $Voucher1[2]) * $Voucher1[3] + $Voucher1[4]) % 26;
$CHK2 = (($Voucher2[0] * $Voucher2[1] + $Voucher2[2]) * $Voucher2[3] + $Voucher2[4]) % 26;
$CHKList ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
if($CHKList[$CHK1]==$Voucher3[0]&&$CHKList[$CHK2]==$Voucher3[1])
{
    $Voucher='true';
}
    else{
        $Voucher='false';
    }
}
?>
<?php
echo $Voucher1;
echo "<br>";
echo $Voucher2;
echo "<br>";
echo $Voucher3;
echo $Voucher1[0];
echo $Voucher;
?>
</body>
</html>
