<?php
/**
 * File: updateCurrentAns.php
 * Description: Update the user's current answer. Helpful for identifying which 
 * questions the user got correct or wrong, and to aid in quiz resumption.
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['email'])) {
    $email = $_POST['email'];
    $currentAns = $_POST['currentAnswers'];

    // Just set the current answer to the given currentAns
    $stmt = $link->prepare("update Users set currentAns = ? where email = ?");
    $stmt->bind_param('ss', $currentAns, $email);
    $stmt->execute();
}
?>
