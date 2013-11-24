<?php
require_once('database_info.php');

if (isset($_POST['subject'])) {
    $subject  = $POST['subject'];
    $topic    = $POST['topic'];
    $subtopic = $POST['subtopic'];
    $query = "";

    // Subject not specified in request.
    if ($subject === "Any") {
        $query = "select * from `Question` order by rand() limit 20";
    }
    // A subject is specified
    else if ($subject !== "Any" && $topic === "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' order by rand() limit 20";
    }
    // Subject and Topic are specified
    else if ($subject !== "Any" && $topic !== "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= " order by rand() limit 20";
    }
    // All fields are specified
    else {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= "and subtopic = '" . "$subtopic" . "'";
        $query .= " order by rand() limit 20";
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

