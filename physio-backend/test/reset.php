<?php
$database = new SQLite3("../databases/appointments.db"); 

if (!$database) {
  die('Failed to connect to the database');
}else{
  echo "sucessfully resseted to the database";
}

//test
$database->exec("DELETE FROM upcomingAppoint");
$database->exec("DELETE FROM acceptedAppoint");






$database->close();
unset($database);
?>
