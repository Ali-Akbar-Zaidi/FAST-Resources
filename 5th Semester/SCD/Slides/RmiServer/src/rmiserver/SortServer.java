/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiserver;

import sorter.ISort;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author farooq
 */
public class SortServer extends UnicastRemoteObject implements ISort {
    
    public SortServer() throws RemoteException{
        super(0);
    }

    @Override
    public List<Integer> sort(List<Integer> data) throws RemoteException {
        System.out.println("Initiating sorting");
        for(int i=0; i < data.size()-1; i++){
            for(int j=0; j < data.size() - 1; j++){
                if(data.get(i) > data.get(i+1)){
                    Integer temp = data.get(i);
                    data.set(i, data.get(i+1));
                    data.set(i+1, temp);
                }
            }
        }
        System.out.println("Done sorting");
        return data;
    }
    
}
