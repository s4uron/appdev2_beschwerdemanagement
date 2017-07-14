package appdev2_beschwerdemanagement;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

public class AppDev2_Beschwerdemanagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try {
            Beschwerdemanagement beschwerdemanagement = new Beschwerdemanagement();
            beschwerdemanagement.datenbankLeeren(); // Bereits verhandene Tickets & Antworten werden gelöscht
            
            System.out.println("Server gestartet: Warte auf Clients...");
            //Der Server wird gestartet und unter benutzerverwaltung "veröffentlicht"
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://localhost:1099/beschwerdemanagement", beschwerdemanagement);
            // nun wartet der server auf anfragen vom client...
 
            
        } catch (RemoteException ex) {
            System.out.println("Ein Remotefehler ist aufgetreten: "+ex.getMessage());
        } catch (AlreadyBoundException ex) {
            System.out.println("Fehler: "+ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der rmi Adresse: "+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Fehler: "+ex.getMessage());
        }   
    }
    
}
