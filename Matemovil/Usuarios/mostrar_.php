<?php 
include 'conexion.php';


$result= array();
$result['datos'] =array();
$query ="SELECT *FROM usuario";
$responce = mysqli_query($conexion,$query);

while($row = mysqli_fetch_array($responce))
{
    $index['id'] =$row['0'];
    $index['nombre'] =$row['1'];
    $index['edad'] =$row['2'];
    $index['grado'] =$row['3'];
	$index['correo'] =$row['4'];
    

    array_push($result['datos'], $index);

}
$result["exito"]="1";
echo json_encode($result);

?>