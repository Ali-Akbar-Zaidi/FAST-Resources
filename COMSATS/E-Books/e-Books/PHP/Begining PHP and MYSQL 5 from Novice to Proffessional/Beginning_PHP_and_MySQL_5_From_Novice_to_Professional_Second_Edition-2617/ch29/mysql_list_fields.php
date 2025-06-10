$fields = mysql_list_fields("company","product");
echo "Total number of fields returned: ".mysql_num_fields($fields).".<br />";