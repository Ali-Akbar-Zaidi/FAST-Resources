DELIMITER //

DROP PROCEDURE IF EXISTS `corporate`.`calc_bonus`$$

CREATE PROCEDURE `corporate`.`calc_bonus` ()
BEGIN

DECLARE empID INT;
DECLARE emp_cat INT;
DECLARE sal DECIMAL(8,2);
DECLARE finished INTEGER DEFAULT 0;

DECLARE emp_cur CURSOR FOR
   SELECT employeeID, emp_category, salary FROM employee ORDER BY employeeID;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished=1;

OPEN emp_cur;

calcloop: LOOP

   FETCH emp_cur INTO empID, emp_cat, sal;

   IF finished=1 THEN
      LEAVE calcloop;
   END IF;

   IF emp_cat=0 THEN
      ITERATE calcloop;
   END IF;

   UPDATE employee SET salary = sal + sal * 0.05 WHERE employeeID=empID;

END LOOP calcloop;

CLOSE emp_cur;

END$$

DELIMITER ;