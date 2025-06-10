<form action="<?php echo $_SERVER['PHP_SELF'];?>" method="post">
   <p>
      Product ID:<br />
      <input type="text" name="productid" size="8" maxlength="8" value="" />
    </p>
    <p>
      Name:<br />
      <input type="text" name="name" size="25" maxlength="25" value="" />
    </p>
    <p>
      Price:<br />
      <input type="text" name="price" size="6" maxlength="6" value="" />
    </p>
    <p>
      Description:<br />
      <textarea name="description" rows="5" cols="30"></textarea>
    </p>
    <p>
      <input type="submit" name="submit" value="Submit!" />
    </p>
</form>