<?php
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['points'])) {
    $points = $_POST['points'];
    $email  = $_POST['email'];

    $stmt = $link->prepare("update Users set siquoiaPoints = siquoiaPoints + ? where email = ?");
    $number = (int) $points;
    $stmt->bind_param('ds', $number, $email);
    $stmt->execute();
}
?>
