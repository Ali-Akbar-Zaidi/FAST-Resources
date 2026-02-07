/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author farooq
 */
public interface ISort extends Remote{
    
    public List<Integer> sort(List<Integer> data) throws RemoteException;
    
}
