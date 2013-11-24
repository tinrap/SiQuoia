<?php
require_once('database_info.php');

if (isset($_POST['subject'])) {
    $subject  = $_POST['subject'];
    $topic    = $_POST['topic'];
    $subtopic = $_POST['subtopic'];
    $query = "";
    $json = array();

    // All fields specified
    if ($subtopic !== "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= "and subtopic = '" . "$subtopic" . "'";
        $query .= " order by rand() limit 20";
    }
    // Subject and Topic are specified
    else if ($topic !== "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= " order by rand() limit 20";
    }
    // Topic is specified.
    else if ($subject !== "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' order by rand() limit 20";
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

