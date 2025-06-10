<?php
   // The revised create_crumbs() function. Note that this version is
   // much simpler, as it's customized specifically for use with the book catalog.
   function create_crumbs($siteURL, $categoryID, $categoryName, $title) {

      $crumb = "<a href = \"$siteURL\">Home</a> &gt; 
                <a href = \"$siteURL/category/$categoryID/\">
                               $categoryName</a> &gt; $title";

       print $crumb;

   } # end create_crumbs definition

   $siteURL = "http://www.example.com";

   $conn = mysql_connect("localhost", "jason", "secret");

   $db = mysql_select_db("corporate");

   // assume that this would be parsed from the user-friendly URL
   $isbn = "1590595475";

   $query = "SELECT b.category_id, c.name, b.isbn, b.author, b.title, b.description 
             FROM books b, categories c 
             WHERE b.isbn = $isbn AND b.category_id = c.category_id";

   $result = mysql_query($conn, $query);

   $row = mysql_fetch_assoc($result);

   // Retrieve the query values
   $categoryID = $row["category_id"];
   $categoryName = $row["name"];
   $isbn = $row["isbn"];
   $authorID = $row["author"];
   $title = $row["title"];

   // Execute the function
   create_crumbs($siteURL, $categoryID, $categoryName, $title);

?>