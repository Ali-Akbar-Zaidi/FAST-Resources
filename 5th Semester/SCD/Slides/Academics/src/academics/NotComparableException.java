/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academics;

/**
 *
 * @author farooq
 */
public class NotComparableException extends Exception {
    
    public String getMessage() {
        return "Incompatible types detected that are not comparable";
    }
    
}
