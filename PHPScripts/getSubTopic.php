<?php
require_once('database_info.php');

if (isset($_POST['subject'])) {
    $subject = $_POST['subject'];
    $subtopic = $_POST['subtopic'];
    $query = "SELECT * FROM `SubTopic` WHERE subject = '". "$subject" . "' AND WHERE subtopic = '" . "$subtopic" . "'";
    $result = $link->query($query);
    $json = array();

    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }
        echo json_encode($json) . "\n";
    }
}
?>
