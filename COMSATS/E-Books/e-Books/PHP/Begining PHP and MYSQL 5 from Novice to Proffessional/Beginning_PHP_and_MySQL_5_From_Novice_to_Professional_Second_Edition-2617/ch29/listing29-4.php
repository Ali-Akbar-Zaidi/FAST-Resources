// If the form has been submitted
if (isset($_POST['submit']))
{
   // Retrieve the posted rowID
   $rowID = $_POST['rowID'];

   // Select the product data based on the rowID
   $query = "SELECT name, productid, price, description FROM product 
             WHERE rowID='$rowID'";

   $result = mysql_query($query);

   // Assign the product information to variables
   list($name,$productid,$price,$description) = mysql_fetch_row($result);

   // Include the form where the product data will be populated
   include "modifyform.php";
}