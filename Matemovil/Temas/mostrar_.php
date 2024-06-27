<?php 
include 'conexion.php';

$userEmail = $_GET["userEmail"]; // Obtener el email del usuario desde la aplicación Android

$result = array();
$result['datos'] = array();

// Consulta para obtener el grado del usuario
$query_grado = "SELECT grado FROM usuario WHERE correo = '$userEmail'";
$res_grado = mysqli_query($conexion, $query_grado);

if ($res_grado) {
    $row_grado = mysqli_fetch_assoc($res_grado);
    $grado_usuario = $row_grado['grado'];

    // Consulta para obtener los temas según el grado del usuario
    $query_temas = "SELECT * FROM temas WHERE grado_id = '$grado_usuario'";
    $res_temas = mysqli_query($conexion, $query_temas);

    while ($row = mysqli_fetch_array($res_temas)) {
        $index['id'] = $row['idTema']; // Ajusta el nombre según tu base de datos
        $index['nombre'] = $row['nombre'];
        $index['urlimagen'] = $row['urlImagen']; // Ajusta el nombre según tu base de datos
        $index['grado'] = $row['grado_id']; // Ajusta el nombre según tu base de datos

        array_push($result['datos'], $index);
    }

    $result["exito"] = "1";
} else {
    $result["exito"] = "0"; // Manejar el caso de que no se encuentre el grado del usuario
}

echo json_encode($result);

?>
