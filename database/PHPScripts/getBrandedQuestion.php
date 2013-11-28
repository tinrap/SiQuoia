<?php
require_once('database_info.php');

if (isset($_POST['code'])) {
    $code = $_POST['code'];
    $email = $_POST['email'];
    $json = array();

    $query = "select * from BrandedQuestion where code = '" . "$code" . "'";
    $result = $link->query($query);

    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }
        echo json_encode($json) . "\n";
    }

    // Update current Quiz
    $query = "update `Users` set currentQuiz = '" . json_encode($json);
    $query .= "'" . "where email = '" . "$email" . "'";
    $link->query($query);

    // Update current Ans
    $query = "update `Users` set currentAns = '" . "'";
    $query .= "where email = '" . "$email" . "'";
    $link->query($query);

    // Update user's packet type "branded"
    $type = "branded";
    $query = "update `Users` set packetType = '" . "$type";
    $query .= "'" . "where email = '" . "$email" . "'";
    $link->query($query);
}
?>

