DELIMITER //
CREATE TRIGGER au_reassign_ticket
AFTER UPDATE ON technician
FOR EACH ROW
BEGIN
   IF NEW.available = 0 THEN
      UPDATE ticket SET technicianID=0 WHERE technicianID=NEW.technicianID;
   END IF;
END;//