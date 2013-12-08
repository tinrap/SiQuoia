<?php
/**
 * File: updateRank.php
 * Description: Updates the rank of a question
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['questionText'])) {
    $questionText = $_POST['questionText'];

    // Adds +1 to a question's rank.
    $stmt = $link->prepare("update Question set rank = rank + ? where questionText = ?");
    $number = 1;
    $stmt->bind_param('ds', $number, $questionText);
    $stmt->execute();
}
?>
