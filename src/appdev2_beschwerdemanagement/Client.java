package appdev2_beschwerdemanagement;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            BeschwerdemanagementInterface beschwerdemanagement = (BeschwerdemanagementInterface) Naming.lookup("rmi://localhost:1099/beschwerdemanagement");
            System.out.println("Starte Fall 1....");
            beschwerdemanagement.serverPrint("\n\n                                    Fall 1:");
            beschwerdemanagement.serverPrint("-------------------------------------------------------------------------------------");
            
            Kunde kunde = beschwerdemanagement.zufallsKunde();
            // Kunde erstellt Ticket
            Ticket ticket = beschwerdemanagement.createTicket("Mein Computer funktioniert nicht.", kunde);
            // Ticket wird nicht gelöst und einem neuen Mitarbeiter zugeordnet
            ticket = beschwerdemanagement.setNewWorker(ticket);
            // Neuer Mitarbeiter schreibt eine Antwort
            Mitarbeiter ma = beschwerdemanagement.getMitarbeiter(ticket.mitarbeiter_id);
            beschwerdemanagement.addAnswer("Haben Sie den Computer schon einmal neu gestartet?", ticket,ma);
            // Antwort wird nicht akzeptiert, deshalb wird ein neuer Mitarbeiter zugewiesen
            ticket = beschwerdemanagement.setNewWorker(ticket);
            ma = beschwerdemanagement.getMitarbeiter(ticket.mitarbeiter_id);
            // Neuer Mitarbeiter schreibt eine Antwort
            beschwerdemanagement.addAnswer("Sind alle Stecker am Computer angeschlossen? :)", ticket,ma);
            // Antwort wird akzeptiert und Ticket geschlossen
            beschwerdemanagement.closeTicket(ticket);
            System.out.println("Fall 1 abgeschlossen. \n\n");
            
            System.out.println("Starte Fall 2....");
            beschwerdemanagement.serverPrint("\n\n\n");
            beschwerdemanagement.serverPrint("                                    Fall 2:");
            beschwerdemanagement.serverPrint("-------------------------------------------------------------------------------------");
            
            kunde = beschwerdemanagement.zufallsKunde();
            // Kunde erstellt Ticket
            ticket = beschwerdemanagement.createTicket("Mein Internet wurde gelöscht", kunde);
            // Mitarbeiter schreibt eine Antwort
            ma = beschwerdemanagement.getMitarbeiter(ticket.mitarbeiter_id);
            beschwerdemanagement.addAnswer("Ich habe ihr Internet neu installiert.", ticket,ma);
            // Antwort wird akzeptiert und Ticket geschlossen
            beschwerdemanagement.closeTicket(ticket);
            System.out.println("Fall 2 abgeschlossen. Ausgabe siehe Server \n\n");
              
        } catch (NotBoundException ex) {
            System.out.println("Fehler: "+ex.getMessage()+" nicht als RMI gebunden!");
        } catch (MalformedURLException ex) {
            System.out.println("Fehler in der rmi Adresse: "+ex.getMessage());
        } catch (RemoteException ex) {
            System.out.println("Ein Remotefehler ist aufgetreten: "+ex.getMessage());
        }           
    }
}
