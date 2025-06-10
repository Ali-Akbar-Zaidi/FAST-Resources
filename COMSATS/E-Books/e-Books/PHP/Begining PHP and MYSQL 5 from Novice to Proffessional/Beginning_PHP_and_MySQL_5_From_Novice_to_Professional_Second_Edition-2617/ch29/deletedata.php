<?php
   // Connect to the server and select the database
   mysql_connect("localhost","webuser","secret");
   mysql_select_db("company");

   // Has the form been submitted?
   if (isset($_POST['submit']))
   {
      // Loop through each product with an enabled checkbox
      foreach($count=0; $count < count($_POST['rowID']); $count++)
      {
         $rowID = $_POST['rowID'][$count];
         $query = "DELETE FROM product WHERE rowID='$rowID'";

         $result = mysql_query($query);

         // Should have one affected row
         if ((mysql_affected_rows() == 0) || mysql_affected_rows() == -1) {
           echo "<p>There was a problem deleting some of the selected items.</p>";
           exit;
         }
      }
         echo "<p>The selected items were successfully deleted.</p>";
   }
?>