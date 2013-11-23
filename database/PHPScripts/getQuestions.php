<?php
require_once('database_info.php');

if (isset($_POST['subject']) || isset($_POST['topic'])) {
    $subject  = $POST['subject'];
    $topic    = $POST['topic'];
    $subtopic = $POST['subtopic'];
    $query = "";

    // Subject not specified in request.
    if ($subject === "Any") {
        $query = "select * from `Question` order by RAND() limit 20";
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

