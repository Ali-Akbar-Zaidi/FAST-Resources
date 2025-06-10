<?php
   $input = "I <td>really</td> love <i>PHP</i>!";
   $input = strip_tags($input,"<i></i>");
   // $input now equals "I really love <i>PHP</i>!"
?>