<?php
require_once('database_info.php');

if (isset($_POST['email'])) {
    $email    = $_POST['email'];
    $password = $_POST['password'];

    // TODO: Will prepare the query to prevent injection later. 
    $query = "SELECT email, currentQuiz, currentAns, siquoiaPoints, packetsBought,";
    $query .= "memorabilia, totalPointsSpent FROM `Users`";
    $query .= " WHERE email = '" . "$email" . "'AND password = '" . "$password" . "'";
    $result = $link->query($query);

    if ($result->num_rows !== false) {
        echo "true";
    }
    else {
        echo "false";
    }
}
?>
