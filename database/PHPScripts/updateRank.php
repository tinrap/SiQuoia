<?php
require_once('database_info.php');

if (isset($_POST['rank'])) {
    $rank = $_POST['rank'];
    $questionText = $_POST['questionText'];

    $stmt = $link->prepare("update Question set rank = ? where questionText = ?");
    $stmt->bind_param('ds', $rank, $questionText);
    $stmt->execute();
}
?>
