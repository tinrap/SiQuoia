<?php
require_once('database_info.php');

if (isset($_POST['points'])) {
    $points = $_POST['points'];
    $email  = $_POST['email'];

    $stmt = $link->prepare("update Users set siquoiaPoints = ? where email = ?");
    $stmt->bind_param("ds", $points, $email);
    $stmt->execute();
}
?>
