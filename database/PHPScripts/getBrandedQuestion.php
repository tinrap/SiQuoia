<?php
require_once('database_info.php');

if (isset($_POST['code'])) {
    $code = $_POST['code'];
    $json = array();

    $query = "select * from BrandedQuestion where code = '" . "$code" . "'";
    $result = $link->query($query);

    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }
        echo json_encode($json) . "\n";
    }
}
?>

