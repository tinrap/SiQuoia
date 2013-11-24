<?php
require_once('database_info.php');

if (isset($_POST['subject'])) {
    $subject  = $POST['subject'];
    $topic    = $POST['topic'];
    $subtopic = $POST['subtopic'];
    $query    = "";
    $json     = array();

    // All fields specified
    if (strcmp($sbtopic, "Any") !== 0) {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= "and subtopic = '" . "$subtopic" . "'";
        $query .= " order by rand() limit 20";
    }
    // Subject and Topic are specified
    else if (strcmp($topic, "Any") !== 0) {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= " order by rand() limit 20";
    }
    // Topic is specified.
    else if (strcmp($subject, "Any") !== 0) {
        $query = "select * from `Question` where topic = '";
        $query .= "$topic" . "' order by rand() limit 20";
    }
    // No fields specified
    else {
        $query = "select * from `Question` order by rand() limit 20";
    }

    $result = $link->query($query);
    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }
        echo json_encode($json) . "\n";
    }
}
?>

