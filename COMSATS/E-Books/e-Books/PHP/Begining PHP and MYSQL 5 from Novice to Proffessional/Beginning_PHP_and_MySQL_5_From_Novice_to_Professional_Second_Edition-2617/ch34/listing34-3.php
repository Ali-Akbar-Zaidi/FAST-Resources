class corporate_mysqli extends mysqli
{

   // The tabular_output method accepts a query
   // and formats its results using HTML_Table

   function tabular_output($query)
   {

      // Create the table object

      $table = new HTML_Table();

      $result = $this->query($query);

      // Retrieve the field attributes

      $fieldInfo = $result->fetch_fields();

      // Begin column offset at 0

      $colnum = 0;

      // Cycle through each field, outputting its name

      foreach($fieldInfo as $field) {

         $table->setHeaderContents(0, $colnum, $field->name);

         $colnum++;

      }

      // Cycle through the array to produce the table data

      // Begin at row 1 so don't overwrite the header

      $rownum = 1;

      // Reset column offset

      $colnum = 0;

      // Cycle through each row in the result set

      while ($row = $result->fetch_row()) {

         // Cycle through each column in the row

         while ($colnum < mysqli_field_count($this)) {

            $table->setCellContents($rownum, $colnum, $row[$colnum]);
            $colnum++;

         }

         $rownum++;
         $colnum = 0;

      }

      // Output the data

      echo $table->toHTML();

   }

} // end class