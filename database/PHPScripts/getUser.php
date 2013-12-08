<?php
/**
 * File: getUser.php
 * Description: Gets all fields relavant to a User, except the password.
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['email'])) {
    $email    = $_POST['email'];
    $json     = array();

    // get all the fields except the user's password.
    $query = "SELECT email, currentQuiz, currentAns, siquoiaPoints, packetsBought,";
    $query .= "memorabilia, totalPointsSpent, packetType FROM `Users`";
    $query .= " WHERE email = '" . "$email" . "'";
    $result = $link->query($query);

    // Create a json array that contains the user's informations
    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }

        echo json_encode($json);
    }
}
?>
