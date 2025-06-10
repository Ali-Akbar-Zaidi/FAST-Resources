<?php
   /*
   * mysql_session_open()
   * Opens a persistent server connection and selects the database.
   */

   function mysql_session_open($session_path, $session_name) {

       $conn = mysql_connect("localhost", "website", "secret");

       $db = mysql_select_db("corporate");


   } // end mysql_session_open()

   /*
   * mysql_session_close()
   * Doesn't actually do anything since the server connection is 
   * persistent. Keep in mind that although this function 
   * doesn't do anything in my particular implementation, it 
   * must nonetheless be defined.
   */

   function mysql_session_close() {

      return 1;

   } // end mysql_session_close()

   /*
   * mysql_session_select()
   * Reads the session data from the database
   */

   function mysql_session_select($SID) {

      $query = "SELECT value FROM sessioninfo
                WHERE SID = '$SID' AND
                expiration > ". time();

      $result = mysql_query($query);

      if (mysql_num_rows($result)) {

         $row = mysql_fetch_assoc($result);
         $value = $row['value'];
         return $value;

      } else {

         return "";

      }

   } // end mysql_session_select()

   /*
   * mysql_session_write()
   * This function writes the session data to the database. 
   * If that SID already exists, then the existing data will be updated.
   */

   function mysql_session_write($SID, $value) {

      $lifetime = get_cfg_var("session.gc_maxlifetime");
      $expiration = time() + $lifetime;

      $query = "INSERT INTO sessioninfo 
                VALUES('$SID', '$expiration', '$value')";
      $result = mysql_query($query);

      if (! $result) {

         $query = "UPDATE sessioninfo SET 
                   expiration = '$expiration',
                   value = '$value' WHERE
                   SID = '$SID' AND expiration >". time();

        $result = mysql_query($query);

     }

   } // end mysql_session_write()

   /*
   * mysql_session_destroy()
   * Deletes all session information having input SID (only one row)
   */

   function mysql_session_destroy($SID) {

      $query = "DELETE FROM sessioninfo 
                WHERE SID = '$SID'";

      $result = mysql_query($conn, $query);

   } // end mysql_session_destroy()

   /*
   * mysql_session_garbage_collect()
   * Deletes all sessions that have expired.
   */

   function mysql_session_garbage_collect($lifetime) {

      $query = "DELETE FROM sessioninfo 
                WHERE sess_expiration < ". time() - $lifetime;

      $result = mysql_query($query);

      return mysql_affected_rows($result);

   } // end mysql_session_garbage_collect()

?>