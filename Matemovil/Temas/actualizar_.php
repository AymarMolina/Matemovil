<?php 

include 'conexion.php';

$id =$_POST['id'];
$nombre =$_POST['nombre'];
$urlImagen =$_POST['urlImagen'];

$query ="UPDATE temas SET nombre ='$nombre' ,urlImagen ='$urlImagen' WHERE id ='$id'";

$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datos actualizados";
}else{
    echo "error en actualizacion";
}


mysqli_close($conexion);

?>