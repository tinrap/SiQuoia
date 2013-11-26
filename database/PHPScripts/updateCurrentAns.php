<?php
require_once('database_info.php');

if (isset($_POST['email'])) {
    $email = $_POST['email'];
    $currentAns = $_POST['currentAnswers'];

    $stmt = $link->prepare("update Users set currentAns = ? where email = ?");
    $stmt->bind_param('ss', $currentAns, $email);
    $stmt->execute();
}
?>
