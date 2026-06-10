<?php

include "db.php";

$user_id = $_POST["user_id"];

$sql = "SELECT category,
SUM(spent_money) AS total
FROM expenses
WHERE user_id='$user_id'
AND spent_money>0
GROUP BY category";

$result = mysqli_query($conn,$sql);

$data = array();

while($row=mysqli_fetch_assoc($result)){
    $data[] = $row;
}

echo json_encode($data);

?>