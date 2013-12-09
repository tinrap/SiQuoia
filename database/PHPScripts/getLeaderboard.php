<?php 
/**
 * File: getLeaderboard.php
 * Description: Gets the leaderboards of questions
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once 'database_info.php';

// Query only gets 20 questions from the Questions that have the highest rank.
$query = "select questionText, rank from Question order by rank desc limit 20";
$result = $link->query($query);
$json = array();

// Create a json array that holds 20 questions that have the highest rank.
if ($result->num_rows !== false) {
    while ($row = $result->fetch_assoc()) {
        array_push($json, $row);
    }
    echo json_encode($json) . "\n";
}
?>
