DELIMITER //

CREATE PROCEDURE calculate_bonus()
BEGIN

   DECLARE empID INT;
   DECLARE sal DECIMAL(8,2);
   DECLARE comm (3,2);

   DECLARE calc_bonus CURSOR FOR SELECT employeeID, salary, commission
                                 FROM employee;

   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

   OPEN calc_bonus;

   BEGIN_calc: LOOP

      FETCH calc_bonus INTO empID, sal, comm;

      IF done THEN
         LEAVE begin_calc;
      END IF;

      IF sal > 60000.00 THEN
         IF comm > 0.05 THEN
            UPDATE employee SET bonus = sal * comm WHERE employeeID=empID;
         ELSEIF comm <= 0.05 THEN
            UPDATE employee SET bonus = sal * 0.03 WHERE employeeID=empID;
         END IF;
      ELSE
         UPDATE employee SET bonus = sal * 0.07 WHERE employeeID=empID;
      END IF;

   END LOOP begin_calc;

   CLOSE calc_bonus;

END//

DELIMITER ;