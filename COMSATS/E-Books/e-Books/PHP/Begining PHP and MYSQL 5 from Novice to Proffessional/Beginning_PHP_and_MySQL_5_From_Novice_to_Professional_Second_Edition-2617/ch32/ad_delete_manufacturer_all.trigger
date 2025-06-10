DELIMITER //
CREATE TRIGGER ad_delete_manufacturer_all
AFTER DELETE ON country
FOR EACH ROW
BEGIN
   DELETE FROM manufacturer WHERE countryID=OLD.countryID;
END;//