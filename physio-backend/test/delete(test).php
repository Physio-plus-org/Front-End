<?php
$database = new SQLite3("../databases/appointments.db"); 

if (!$database) {
  die('Failed to connect to the database');
}else{
  echo "sucessfully deleted elements from  the database";
}

//test
$database->exec("DELETE FROM upcomingAppoint WHERE patientName='melipatata'");



$database->close();
unset($database);
?>

