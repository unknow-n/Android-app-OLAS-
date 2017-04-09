<?php
    $con = mysqli_connect("my_host", "my_user", "my_password", "my_database");
    
    $com_nam = $_POST["com_nam"];
	$owner_nam = $_POST["owner_nam"];
	$mob_n = $_POST["mob_n"];
    

    $statement = mysqli_prepare($con, "INSERT INTO provider_details (company,owner,number) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $com_nam, $owner_nam, $mob_n);
	
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
