<?php

include "db.php";

$user_id = $_POST['user_id'];
$total_money = $_POST['total_money'];

$sql = "INSERT INTO expenses(user_id,total_money,spent_money)
VALUES('$user_id','$total_money',0)";

if($conn->query($sql)){
    echo "success";
}
else{
    echo "fail";
}

$conn->close();

?>