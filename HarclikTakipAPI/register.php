<?php

include "db.php";

$fullname = $_POST['fullname'];
$username = $_POST['username'];
$email = $_POST['email'];
$password = $_POST['password'];

$sql = "INSERT INTO users(username,password,fullname,email)
VALUES('$username','$password','$fullname','$email')";

if($conn->query($sql) === TRUE){
    echo "success";
}
else{
    echo "fail";
}

$conn->close();

?>