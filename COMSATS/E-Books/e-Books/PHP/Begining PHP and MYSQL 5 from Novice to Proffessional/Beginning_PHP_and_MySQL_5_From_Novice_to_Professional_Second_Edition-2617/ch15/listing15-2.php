<form action="homework.php" enctype="multipart/form-data" method="post">
     Last Name:<br /> <input type="text" name="name" value="" /><br />
     Homework:<br /> <input type="file" name="homework" value="" /><br />
     <p><input type="submit" name="submit" value="Submit Notes" /></p>
</form>

<?php
# Set a constant
define ("FILEREPOSITORY","/home/www/htdocs/class/homework/");

if (isset($_FILES['homework'])) {

   if (is_uploaded_file($_FILES['homework']['tmp_name'])) {

      if ($_FILES['homework']['type'] != "application/pdf") {
         echo "<p>Homework must be uploaded in PDF format.</p>";
      } else {

         /* Format date and create daily directory, if necessary. */
         $today = date("m-d-Y");
         if (! is_dir(FILEREPOSITORY.$today)) mkdir(FILEREPOSITORY.$today);

         /* Assign name and move uploaded file to final destination. */
         $name = $_POST['name'];
         $result = move_uploaded_file($_FILES['homework']['tmp_name'], 
                   FILEREPOSITORY.$today."/"."$name.pdf");

         /* Provide user with feedback. */
         if ($result == 1) echo "<p>File successfully uploaded.</p>";
            else echo "<p>There was a problem uploading the homework.</p>";

      }

   }
}
?>