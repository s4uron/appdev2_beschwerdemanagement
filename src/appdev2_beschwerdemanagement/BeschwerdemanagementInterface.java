/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev2_beschwerdemanagement;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jan
 */
public interface BeschwerdemanagementInterface extends Remote {
    public void datenbankLeeren() throws RemoteException;
    
    public Ticket createTicket(String text, Kunde kunde) throws RemoteException;
    
    public Ticket setNewWorker(Ticket ticket) throws RemoteException;
    
    public Antwort addAnswer(String text, Ticket ticket, Mitarbeiter ma) throws RemoteException;
    
    public Ticket closeTicket(Ticket ticket) throws RemoteException;
    
    public Mitarbeiter zufallsMitarbeiter() throws RemoteException;
    
    public Kunde zufallsKunde() throws RemoteException;
    
    public Mitarbeiter getMitarbeiter(int id) throws RemoteException;
    
    public Kunde getKunde(int id) throws RemoteException;
    
    public void serverPrint(String text) throws RemoteException;
}
