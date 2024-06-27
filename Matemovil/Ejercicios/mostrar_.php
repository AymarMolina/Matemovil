<?php
include 'conexion.php';

$idTema = $_GET["idTema"]; 

$result = array();
$result['datos'] = array();

$query_ejercicios = "SELECT id, enunciado, respuesta_correcta, urlImagen FROM ejercicios WHERE tema_id = '$idTema'";
$res_ejercicios = mysqli_query($conexion, $query_ejercicios);

if ($res_ejercicios) {
    while ($row = mysqli_fetch_assoc($res_ejercicios)) {
        $ejercicio = array(
            'id' => $row['id'],
            'enunciado' => $row['enunciado'],
            'correcta' => $row['respuesta_correcta'],
            'urlimagen' => $row['urlImagen']
        );
        array_push($result['datos'], $ejercicio);
    }
    $result["exito"] = "1";
} else {
    $result["exito"] = "0";
}

echo json_encode($result);

?>

