<?php

$dbFile = '/databases/appointments.db'; 

try {
   
    $pdo = new PDO("sqlite:$dbFile");

    
    $pdo->exec("CREATE TABLE IF NOT EXISTS upcomingAppoint(
        id	INTEGER PRIMARY KEY AUTOINCREMENT,
        reason	TEXT,
        message	TEXT,
        time	INTEGER NOT NULL,
        date	TEXT,
        patientName	TEXT,
     
    )");

    
    $pdo->exec("CREATE TABLE IF NOT EXISTS acceptedAppoint (
        id	INTEGER PRIMARY KEY AUTOINCREMENT,
        reason	TEXT,
        message	TEXT,
        time	INTEGER NOT NULL,
        date	TEXT,
        patientName	TEXT,
     
    )");

    echo "Database and tables created successfully!";
} catch (PDOException $e) {
    echo "Exception Error detected: The database already exists ";
}
?>
