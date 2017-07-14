package appdev2_beschwerdemanagement;

import java.rmi.Remote;
import java.rmi.RemoteException;

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
