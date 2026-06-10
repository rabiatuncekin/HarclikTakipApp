<?php

include "db.php";

$user_id = $_GET['user_id'];

$sql = "SELECT
COALESCE(SUM(total_money),0) AS total_money,
COALESCE(SUM(spent_money),0) AS spent_money
FROM expenses
WHERE user_id='$user_id'";

$result = $conn->query($sql);

if($row = $result->fetch_assoc()){

    echo $row['total_money'] . "," .
         $row['spent_money'];

}

$conn->close();

?>