<?php
require_once('database_info.php');

if (isset($_POST['questionText'])) {

  $questionText = $_POST['questionText'];
  $choice1 = $_POST['choice1'];
  $choice2 = $_POST['choice2'];
  $choice3 = $_POST['choice3'];
  $choice4 = $_POST['choice4'];
  $correctAnswer = $_POST['correctAnswer'];
  $topic = $_POST['topic'];
  $subTopic = $_POST['subTopic'];

  $stmt = $link->prepare("insert into Questions 
    (questionText, choice1, choice2, choice3, choice4, correctAnswer, topic, subTopic)
     values (?, ?, ?, ?, ?, ?, ?, ?);");
  $stmt->bind('sssssdss', $questionText, $choice1, $choice2, $choice3, $choice4, $correctAnswer, $topic, $subTopic);
  $stmt->execute();
}
?>
