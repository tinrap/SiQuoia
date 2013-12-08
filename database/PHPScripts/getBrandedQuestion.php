<?php
/**
 * File: getBrandedQuestion.php
 * Description: Retrieves the branded questions from the database and sets the 
 *                        relavant User information for game resumption
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
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
    $stmt = $link->prepare("update Users set currentQuiz = ? where email = ?");
    $stmt->bind_param("ss", json_encode($json), $email);
    $stmt->execute();

    // Update current Ans
    $stmt = $link->prepare("update Users set currentAns = ? where email = ?");
    $sillyVar = "";
    $stmt->bind_param("ss", $sillyVar, $email);
    $stmt->execute();

    // Update user's packet type "branded"
    $type = "branded";
    $stmt = $link->prepare("update Users set packetType = ? where email = ?");
    $stmt->bind_param("s", $type, $email);
    $stmt->execute();
}
?>

