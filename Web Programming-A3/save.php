<?php
session_start();
$arr=$_SESSION["mycart"];
?>
<?php
require('fpdf.php');
if (isset($arr)){
        foreach ($arr as $a){
        }
    }

$basicinfo = "{$a['username']}, {$a['email']}, {$a['phonenumber']}, {$a['moviename']}, {$a['movieday']}, {$a['movietime']}, {$a['price']}";

        /* seat information */
        if ($a['samovieseat']!="0") {
            $sainfo = "{$a['samovieseat']}, {$a['samovieseatnum']}, {$a['saprice']}";
            fwrite($myfile, $sainfo);
                        # code...
                    }

$pdf=new FPDF();
$pdf->Open();
$pdf->AliasNbPages();
$pdf->AddPage();
$pdf->SetFont('Arial','B',24);
$pdf->Cell(0,10,$basicinfo); 
$pdf->Ln(); //换行
$pdf->SetFont('Arial','B',24);
$pdf->Cell(0,10,$sainfo); //输出文章作者
$pdf->Ln();
$pdf->Output("123.pdf", true); //输出PDF 文件，文件名为文章标题
?>