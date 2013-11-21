<?php
require_once('database_info.php');

if (isset($_POST['txtQuestion'])) {
  $questionText = $_POST['txtQuestion'];
  $choice1 = $_POST['txtAnswerOne'];
  $choice2 = $_POST['txtAnswerTwo'];
  $choice3 = $_POST['txtAnswerThree'];
  $choice4 = $_POST['txtAnswerFour'];
  $correctAnswer = $_POST['correctAns'];
  $subject = $_POST['txtSubject'];
  $topic = $_POST['txtTopic'];
  $subTopic = $_POST['txtSubTopic'];
  $rank = $_POST['txtRank'];

  $stmt = $link->prepare("insert into `Question`
    (questionText, answerOne, answerTwo, answerThree, answerFour, correctAns, topic, subTopic, rank)
     values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
  $stmt->bind_param('sssssdssd', $questionText, $choice1, $choice2, $choice3, $choice4, $correctAnswer, $topic, $subTopic, $rank);
  $stmt->execute();
  if ($link->affected_rows === 1) {
    echo "Row inserted into Question table.";
  }
  else {
    echo "ERROR: Row NOT entered! Double check if you're not submitting the same form twice!";
  }
}
?>
