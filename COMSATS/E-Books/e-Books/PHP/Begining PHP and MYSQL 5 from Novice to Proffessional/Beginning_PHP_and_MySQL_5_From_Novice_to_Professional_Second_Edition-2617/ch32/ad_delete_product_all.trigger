DELIMITER //
CREATE TRIGGER ad_delete_product_all
AFTER DELETE ON manufacturer
FOR EACH ROW
BEGIN
   DELETE FROM product WHERE manufacturerID=OLD.manufacturerID;
END;//