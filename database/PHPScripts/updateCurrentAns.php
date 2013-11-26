<?php
require_once('database_info.php');

if (isset($_POST['email'])) {
    $email = $_POST['email'];
    $currentAns = $_POST['currentAns'];

    $stmt = $link->prepare("update Users currentAns = ? where email = email = ?");
    $stmt->bind_param('ss', $currentAns, $email);
    $stmt->execute();
}
?>
