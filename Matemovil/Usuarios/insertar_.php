<?php 

include 'conexion.php';

$nombre =$_POST['nombre'];
$edad =$_POST['edad'];
$grado =$_POST['grado'];
$correo =$_POST['correo'];


// aqui escribimos codigo sql
$query ="INSERT INTO usuario(nombre,edad,grado, correo) values('$nombre' ,'$edad', '$grado', '$correo') ";
$resultado =mysqli_query($conexion,$query);

if($resultado){
    echo "datas insertados";
}else{
    echo "datas error";
}
mysqli_close($conexion);

?>