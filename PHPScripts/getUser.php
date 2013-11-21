<?php
require_once('database_info.php');

if (isset($_POST['email'])) {
    $email     = $_POST['email'];
    $password = $_POST['password'];
    $json     = array();

    // TODO: Will prepare the query to prevent injection later. 
    $query = "SELECT email, password, currentQuiz, currentAns, siquoiaPoints, packetsBought,";
    $query .= "memorabilia, totalPointsSpent FROM `Users`";
    $query .= " WHERE email = '" . "$email" . "'AND password = '" . "$password" . "'";

    $result = $link->query($query);

    if ($result->num_rows !== false) {
        while ($row = $result->fetch_array()) {
            array_push($json, $row);
        }

        echo json_encode($json);
        echo "\n";
    }
}
?>
