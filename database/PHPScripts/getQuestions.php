<?php
/**
 * File: getQuestions.php
 * Description: Gets the normal (read: not branded) questions from the database 
 * and sets the relavant user information for the current quiz.
 * Author: Akshay Hegde
 * Last Modified: 7 December 2013
 */
header('X-Content-Type-Options: nosniff');
header('X-Frame-Options: SAMEORIGIN');
require_once('database_info.php');

if (isset($_POST['subject'])) {
    $subject  = $_POST['subject'];
    $topic    = $_POST['topic'];
    $subtopic = $_POST['subtopic'];
    $email = $_POST['email'];
    $query = "";
    $json = array();

    // All fields specified
    if ($subtopic !== "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= "and subtopic = '" . "$subtopic" . "'";
        $query .= " order by rand() limit 20";
    }
    // Subject and Topic are specified
    else if ($topic !== "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' and topic = '" . "$topic" . "'";
        $query .= " order by rand() limit 20";
    }
    // Topic is specified.
    else if ($subject !== "Any") {
        $query = "select * from `Question` where subject = '";
        $query .= "$subject" . "' order by rand() limit 20";
    }
    // No fields specified
    else {
        $query = "select * from `Question` order by rand() limit 20";
    }

    // Update the User's siquoia Points
    $stmt = $link->prepare("update Users set siquoiaPoints = siquoiaPoints - ? where email = ?");
    // php is dumb
    $sillyVar = 5;
    $stmt->bind_param("ds", $sillyVar, $email);
    $stmt->execute();

    // Update the user's total packets bought
    $stmt = $link->prepare("update Users set packetsBought = packetsBought + ? where email = ?");
    $sillyVar = 1;
    $stmt->bind_param("ds", $sillyVar, $email);
    $stmt->execute();

    // Update the User's total Points Spent
    $stmt = $link->prepare("update Users set totalPointsSpent = totalPointsSpent + ? where email = ?");
    $sillyVar = 5;
    $stmt->bind_param("ds", $sillyVar, $email);
    $stmt->execute();

    // Update the user's packet type to normal
    $stmt = $link->prepare("update Users set packetType = ? where email = ?");
    $type = "normal";
    $stmt->bind_param("ss", $type, $email);
    $stmt->execute();

    $result = $link->query($query);
    if ($result->num_rows !== false) {
        while ($row = $result->fetch_assoc()) {
            array_push($json, $row);
        }
        echo json_encode($json) . "\n";
    }

    // Update current Quiz
    $stmt = $link->prepare("update Users set currentQuiz = ? where email = ?");
    $stmt->bind_param("ss", json_encode($json), $email);
    $stmt->execute();

    // Finally, update current Ans
    $stmt = $link->prepare("update Users set currentAns = ? where email = ?");
    $sillyVar = "";
    $stmt->bind_param("ss", $sillyVar, $email);
    $stmt->execute();
}
?>

