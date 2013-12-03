<?php
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['email'])) {
    $email    = $_POST['email'];
    $json     = array();

    // TODO: Will prepare the query to prevent injection later. 
    $query = "SELECT email, currentQuiz, currentAns, siquoiaPoints, packetsBought,";
    $query .= "memorabilia, totalPointsSpent, packetType FROM `Users`";
    $query .= " WHERE email = '" . "$email" . "'";
    $result = $link->query($query);

    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }

        echo json_encode($json);
    }
}
?>
