<?php

include "db.php";

$user_id = $_POST['user_id'];
$spent_money = $_POST['spent_money'];
$category = $_POST['category'];

$sql = "INSERT INTO expenses(user_id,total_money,spent_money,category)
VALUES('$user_id',0,'$spent_money','$category')";

if($conn->query($sql)){
    echo "success";
}
else{
    echo "fail";
}

$conn->close();

?>