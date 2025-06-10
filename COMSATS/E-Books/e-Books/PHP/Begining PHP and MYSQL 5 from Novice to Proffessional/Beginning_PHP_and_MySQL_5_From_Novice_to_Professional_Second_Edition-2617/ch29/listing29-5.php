<?php
   mysql_connect("localhost","webuser","secret");

   // The view_db_properties() function retrieves table information for
   // the database defined by the input parameter $db, and invokes 
   // view_table_properties() for each table instance located within 
   // that database. 

   function view_db_properties($db)
   {

      mysql_select_db($db);

      $tables = mysql_list_tables($db);

      while (list($tableName) = mysql_fetch_row($tables))
      {
         echo "<p>Table: <b>$tableName</b></p>";
         echo "<table border='1'>";
         echo "<tr><th>Field</th><th>Type</th><th>Length</th><th>Flags</th>";
         echo view_table_properties($tableName);
         echo "</table>";
      }

   }

   // The view_table_properties() function retrieves 
   // field properties for the table defined by the input parameter $table. */

   function view_table_properties($table)
   {

      $tableRows = "";

      // Retrieve a single row from the table, 
      // giving us enough field information to determine field properties.

      $result = mysql_query("SELECT * FROM $table LIMIT 1");
      $fields = mysql_num_fields($result);
      for($count=0; $count < $fields; $count++)
      {
         // Retrieve field properties
         $name = mysql_field_name($result,$count);
         $type = mysql_field_type($result,$count);
         $length = mysql_field_len($result,$count);
         $flags = mysql_field_flags($result,$count);

         $tableRows .= "<tr><td>$name</td>
                            <td>$type</td>
                            <td>$length</td>
                            <td>$flags</td></tr>";
      }

      return $tableRows;

   }

   view_db_properties("company");

?>