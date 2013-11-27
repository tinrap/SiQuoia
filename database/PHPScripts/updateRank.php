<?php
require_once('database_info.php');

if (isset($_POST['questionText'])) {
    $questionText = $_POST['questionText'];

    $stmt = $link->prepare("update Question set rank = ? where questionText = ?");
    $number = 1;
    $stmt->bind_param('ds', $number, $questionText);
    $stmt->execute();
}
?>
