<?php

function create_dropdown($identifier, $pairs, $firstentry,$multiple="", $key="")
{
   $dropdown = "<select name=\"$identifier\" multiple=\"$multiple\">";
   $dropdown .= "<option name=\"\">$firstentry</option>";

   foreach($pairs AS $value => $name)
   {
      $dropdown .= ($value == $key) ? 
                 "<option name=\"$value\" selected=\"selected\">$name</option>" :
                 "<option name=\"$value\">$name</option>";
   }
   echo "</select>";
   return $dropdown;
}

?>