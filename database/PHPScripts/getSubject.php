<?php
/**
 * File: getSubject.php
 * Description: Gets all the subjects from the database.
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

$result = $link->query("SELECT * FROM `Subject`");
$json = array();

if ($result->num_rows !== false) {
    while ($row = $result->fetch_assoc()) {
        array_push($json, $row);
    }

    echo json_encode($json) . "\n";
}
?>
