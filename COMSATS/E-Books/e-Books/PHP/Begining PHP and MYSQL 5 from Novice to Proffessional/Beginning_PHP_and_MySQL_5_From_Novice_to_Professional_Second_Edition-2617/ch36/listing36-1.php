<?php
   session_start();

   /* Retrieve the participant's primary key using some fictitious
      class that refers to some sort of user session table, 
      mapping a session ID back to a specific user. 
   */

   /* Give the POSTed item ID a friendly variable name */
   $itemid = (int) $_POST['itemid'];

   $participant = new participant();
   $buyerid = $participant->getParticipantKey();

   /* Retrieve the item seller and price using some fictitious item class. */
   $item = new item();
   $sellerid = $item->getItemOwner($itemid);
   $price = $item->getPrice($itemid);

   // Instantiate the mysql class
   $mysqldb = new mysqli("localhost","root","jason","corporate");

   // Start by assuming the transaction operations will all succeed
   $success = TRUE;

   // Disable the autocommit feature
   $mysqldb->autocommit(FALSE);

   /* Debit buyer's account. */

   $query = "UPDATE employee SET cash=cash-$price WHERE rowID='$buyerid'";
   $result = $mysqldb->query($query);  

   if (!$result OR $mysqldb->affected_rows != 1 ) 
      $success = FALSE;

   /* Credit seller's account. */
   $query = "UPDATE employee SET cash=cash+$price WHERE rowID='$sellerid'";
   $result = $mysqldb->query($query);  

   if (!$result OR $mysqldb->affected_rows != 1 ) 
      $success = FALSE;

   /* Update trunk item ownership. If it fails, set $success to FALSE. */
   $query = "UPDATE trunk SET owner='$buyerid' WHERE rowid='$itemid'";
   $result = $mysqldb->query($query);

   if (!$result OR $mysqldb->affected_rows != 1 ) 
      $success = FALSE;

   /* If $success is TRUE, commit transaction, otherwise roll back changes. */
   if ($success) {
      $mysqldb->commit();
      echo "The swap took place! Congratulations!";
   } else {
      $mysqldb->rollback();
      echo "There was a problem with the swap!";
   }

   // Enable the autocommit feature
   $mysqldb->autocommit(TRUE);

?>