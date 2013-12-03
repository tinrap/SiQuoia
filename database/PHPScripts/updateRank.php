<?php
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['questionText'])) {
    $questionText = $_POST['questionText'];

    $stmt = $link->prepare("update Question set rank = rank + ? where questionText = ?");
    $number = 1;
    $stmt->bind_param('ds', $number, $questionText);
    $stmt->execute();
}
?>
