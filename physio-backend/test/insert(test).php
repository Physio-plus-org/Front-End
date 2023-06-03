<?php
$database = new SQLite3("../databases/appointments.db"); 

if (!$database) {
  die('Failed to connect to the database');
}else{
  echo "sucessfully inserted to the database";
  $selectStmt = $database->prepare("SELECT * FROM upcomingAppoint WHERE patientName = :patientName");
  $selectStmt->bindValue(':patientName', 'melipatata');
  $result = $selectStmt->execute();

  if ($result->fetchArray()) {
    echo "Error: Patient with the same name already exists.";
    exit;
}
}

//test
$database->exec("INSERT OR REPLACE INTO upcomingAppoint (time, date,patientName,reason) VALUES (9, '20/05/2023','melipatata','ponaei i kilia mou')");
$database->exec("INSERT OR REPLACE INTO upcomingAppoint (time, date,patientName,reason) VALUES (12, '20/05/2023','kourkoumas','ponaei i glwssa mou')");
$database->exec("INSERT OR REPLACE INTO upcomingAppoint (time, date,patientName,reason) VALUES (12, '20/05/2024','krepas','ponaei to podi mou')");




$database->close();
unset($database);
?>

