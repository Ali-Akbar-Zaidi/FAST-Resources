<form action="multiplehomework.php" enctype="multipart/form-data" method="post">
     Last Name:<br /> <input type="text" name="name" value="" /><br />
     Homework #1:<br /> <input type="file" name="homework1" value="" /><br />
     Homework #2:<br /> <input type="file" name="homework2" value="" /><br />
     <p><input type="submit" name="submit" value="Submit Notes" /></p>
</form>

<?php
/* Set a constant */
define ("FILEREPOSITORY","/home/www/htdocs/class/homework/");
if (isset($_FILES['homework'])) {
   if (is_uploaded_file($_FILES['homework1']['tmp_name']) && 
       is_uploaded_file($_FILES['homework2']['tmp_name'])) {

      if (($_FILES['homework1']['type'] != "application/pdf") || 
          ($_FILES['homework2']['type'] != "application/pdf")) {

         echo "<p>All homework must be uploaded in PDF format.</p>";

      } else {
         /* Format date and create daily directory, if necessary. */
         $today = date("m-d-Y");

         if (! is_dir(FILEREPOSITORY.$today))
               mkdir(FILEREPOSITORY.$today);

            /* Name and move homework #1 */
            $filename1 = $_POST['name']."1";

            $result = move_uploaded_file($_FILES['homework1']['tmp_name'], 
                      FILEREPOSITORY.$today."/"."$filename1.pdf");

            if ($result == 1) echo "<p>Homework #1 successfully uploaded.</p>";
            else echo "<p>There was a problem uploading homework #1.</p>";

            /* Name and move homework #2 */
            $filename2 = $_POST['name']."2";

            $result = move_uploaded_file($_FILES['homework2']['tmp_name'], 
                      FILEREPOSITORY.$today."/"."$filename2.pdf");

            if ($result == 1) echo "<p>Homework #2 successfully uploaded.</p>";
            else echo "<p>There was a problem uploading homework #2.</p>";

          } #endif
     } #endif
} #endif
?>