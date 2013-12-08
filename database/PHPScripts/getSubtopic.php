<?php
/**
 * File: getSubtopic.php
 * Description: Gets all the subtopics from the database
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['subject'])) {
    $subject = $_POST['subject'];
    $topic = $_POST['topic'];
    $query = "SELECT * FROM `SubTopic` WHERE subject = '". "$subject" . "' AND topic = '" . "$topic" . "'";
    $result = $link->query($query);
    $json = array();

    // Creates a json encoded array that contains all the subtopics
    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }
        echo json_encode($json) . "\n";
    }
}
?>
