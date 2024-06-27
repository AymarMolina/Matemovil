<?php 

include 'conexion.php';

$nombre =$_POST['nombre'];
$urlImagen =$_POST['urlImagen'];


// aqui escribimos codigo sql
$query ="INSERT INTO temas(nombre,urlImagen) values('$nombre' ,'$urlImagen') ";
$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datas insertados";
}else{
    echo "datas error";
}
mysqli_close($conexion);

?>