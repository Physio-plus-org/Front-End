<?php
$database = new SQLite3("./databases/appointments.db"); 

if (!$database) {
  die('Failed to connect to the database');
} else {
    $result = "success";
    echo $result;

  

  $database->close();
  unset($database);
}
?>
