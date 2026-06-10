<?php

include "db.php";

$user_id = $_GET['user_id'];

$sql = "SELECT fullname, username, email
        FROM users
        WHERE id='$user_id'";

$result = $conn->query($sql);

if($row = $result->fetch_assoc()){

    echo json_encode($row);

}

$conn->close();

?>