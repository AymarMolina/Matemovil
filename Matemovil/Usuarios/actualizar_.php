<?php 

include 'conexion.php';

$id =$_POST['id'];
$nombre =$_POST['nombre'];
$edad =$_POST['edad'];
$grado =$_POST['grado'];
$correo =$_POST['correo'];


$query ="UPDATE usuario SET nombre ='$nombre' ,edad ='$edad', grado ='$grado', correo ='$correo' WHERE id ='$id'";

$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datos actualizados";
}else{
    echo "error en actualizacion";
}


mysqli_close($conexion);

?>