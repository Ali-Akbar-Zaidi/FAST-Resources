<?php

function create_crumbs($crumb_site, $home_label, $crumb_labels) { 

   // Start the crumb trail
   $crumb_trail = "<a href=\"$crumb_site\">$home_label</a>";

   // Parse the requested URL path
   $crumb_tree = explode('/', $_SERVER['PHP_SELF']);

   // Start the URL path used within the trail
   $crumb_path = $crumb_site.'/';

   // Assemble the crumb trail
   for ($x = 1; $x < count($crumb_tree) - 2; $x++) {
      $crumb_path .= $crumb_tree[$x].'/';
      $crumb_trail .= ' &gt; <a href="'.$crumb_path.'">'.
                      $crumb_labels[$crumb_tree[$x]].'</a>';
   }
   
   return $crumb_trail;
}

?>