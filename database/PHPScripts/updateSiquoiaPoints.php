<?php
/**
 * File: updateSiquoiaPoints.php
 * Description: Updates the user's siquoia points after the completion of a quiz
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['points'])) {
    $points = $_POST['points'];
    $email  = $_POST['email'];

    // Adds +points to a user's siquoia Points.
    // Gives the user more siquoia points depending on the number of correct 
    // answers they got in the quiz.
    $stmt = $link->prepare("update Users set siquoiaPoints = siquoiaPoints + ? where email = ?");
    $number = (int) $points;
    $stmt->bind_param('ds', $number, $email);
    $stmt->execute();
}
?>
