<?php
 header('Access-Control-Allow-Origin: *');
    try {
        require_once("db_handler.php"); //lenyegeben linkeles, hasonloak: require, include, include_once
        $query = "INSERT INTO `library`.`user` (`username`,`password`,`email`)VALUES('{$_POST['username']}', '{$_POST['password']}','{$_POST['email']}');";  
        $stmt = $pdo -> prepare($query);
        
        $stmt -> execute();
        
        // echo json_encode("ok");

        $pdo = null;
        $stmt = null;
    } catch (\Throwable $th) {
        echo json_encode("nemok");
    }
  

