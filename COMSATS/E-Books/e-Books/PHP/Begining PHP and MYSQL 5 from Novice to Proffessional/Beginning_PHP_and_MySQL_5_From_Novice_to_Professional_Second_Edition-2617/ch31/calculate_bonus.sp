DELIMITER //
CREATE FUNCTION calculate_bonus
(employee_id CHAR(8)) RETURNS DECIMAL(10,2)
COMMENT 'Calculate employee bonus'
BEGIN
   DECLARE total DECIMAL(10,2);
   DECLARE bonus DECIMAL(10,2);
   SELECT SUM(price) INTO total FROM sales WHERE employee_id = employee_id;
   SET bonus = total * .05;
   RETURN bonus;
END;
//
DELIMITER ;