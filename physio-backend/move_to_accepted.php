<?php
$database = new SQLite3("./databases/appointments.db");

if (!$database) {
    die('Failed to connect to the database');
} else {
    try {
        
        
        // $patientName = $_POST['patientName'];
        
        //test purposes
        
        $patientName = "melipatata";
        
        
        $selectStmt = $database->prepare("SELECT * FROM upcomingAppoint WHERE patientName = :patientName");
        $selectStmt->bindParam(':patientName', $patientName);
        $result = $selectStmt->execute();

        $row = $result->fetchArray(SQLITE3_ASSOC);

        if ($row) {
            $id = $row['id'];
            $reason = $row['reason'];
            $message = $row['message'];
            $date = $row['date'];
            $time = $row['time'];

            
            // $database->exec("SET TRANSACTION ISOLATION LEVEL SERIALIZABLE"); SQLLITE 3 EXEI IDI SERIALIZABLE APO DEFAULT

            $database->exec("BEGIN TRANSACTION");

            try {
                $insertStmt = $database->prepare("INSERT INTO acceptedAppoint (reason, message, time, date, patientName) VALUES (:reason, :message, :time, :date, :patientName)");
                $insertStmt->bindValue(':reason', $reason, SQLITE3_TEXT);
                $insertStmt->bindValue(':message', $message, SQLITE3_TEXT);
                $insertStmt->bindValue(':time', $time, SQLITE3_INTEGER);
                $insertStmt->bindValue(':date', $date, SQLITE3_TEXT);
                $insertStmt->bindValue(':patientName', $patientName, SQLITE3_TEXT);
                $insertStmt->execute();

                $deleteStmt = $database->prepare("DELETE FROM upcomingAppoint WHERE patientName = :patientName");
                $deleteStmt->bindValue(':patientName', $patientName, SQLITE3_INTEGER);
                $deleteStmt->execute();

                $database->exec("COMMIT");
                echo "Transaction completed sucessfully";
            } catch (Exception $e) {
                $database->exec("ROLLBACK");
                echo "Error: " . $e->getMessage();
            }
        } else {
            echo "No record found for the given name.";
        }
    } catch (Exception $e) {
        echo "Exception Error detected: " . $e->getMessage();
    }
}

$database->close();
unset($database);
?>
