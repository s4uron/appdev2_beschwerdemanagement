package appdev2_beschwerdemanagement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Random;

public class Beschwerdemanagement extends UnicastRemoteObject implements BeschwerdemanagementInterface {
    private ArrayList<Kunde> kunden = new ArrayList();
    private ArrayList<Mitarbeiter> mitarbeiter = new ArrayList();
    Datenbank db;
            
    public Beschwerdemanagement() throws RemoteException, SQLException {
        db = new Datenbank();
        mitarbeiter = db.ladeMitarbeiter();
        kunden = db.ladeKunden();
    }
    
    @Override
    public void datenbankLeeren() {
        db.resetDB();
        System.out.println("Datenbank wurde geleert"); 
    }
    
    @Override
    public Ticket createTicket(String text, Kunde kunde) {
        Mitarbeiter ma = zufallsMitarbeiter();
        Ticket ticket = new Ticket(text, ma.id, kunde.id);
        int id = db.speicherTicket(ticket);
        ticket.id = id;
        kunde.tickets.add(ticket);
        ma.tickets.add(ticket);
        System.out.println("Ein neues Ticket wurde im Beschwerdemanagement angelegt:\nID: "+ticket.id+" Kunde: '"+kunde.name+"'["+kunde.id+"] Mitarbeiter: "+ma.name+"["+ma.id+"]\nText: '"+ticket.text+"'\n");        
        return ticket;
    }
    
    @Override
    public Ticket setNewWorker(Ticket ticket) {
        Mitarbeiter ma = null;
        do {
            ma = zufallsMitarbeiter();
            
        } while(ma.id == ticket.mitarbeiter_id);
        getMitarbeiter(ticket.mitarbeiter_id).tickets.remove(ticket);
        ticket.mitarbeiter_id = ma.id;
        ticket.letzte_bearbeitung = new Timestamp(System.currentTimeMillis());
        ma.tickets.add(ticket);
        db.aktualisiereTicket(ticket);
        System.out.println("Ein Ticket wurde innerhalb von 3 Tagen nicht bearbeitet oder der Kunde war unzufrieden mit der Antwort.\nDas Ticket wird einem neuen Mitarbeiter zugewiesen:\nTicket: '"+ticket.text+"'["+ticket.id+"] von '"+getKunde(ticket.kunde_id).name+"' => Mitarbeiter: "+ma.name+"["+ma.id+"]\n");
        return ticket;
    }
    
    
    @Override
    public Antwort addAnswer(String text, Ticket ticket, Mitarbeiter ma) {
        Antwort a = new Antwort(ticket, ma.id, text);      
        int id = db.speicherAntwort(a);
        a.id = id;
        ticket.antworten.add(a);
        System.out.println("Die Antwort: '"+text+"' wurde dem Ticket: '"+ticket.text+"'["+ticket.id+"] \nvon Mitarbeiter: '"+ma.name+"'["+ma.id+"] hinzugefügt!\n");
        return a;
    }
    
    @Override
    public Ticket closeTicket(Ticket ticket) {
        getMitarbeiter(ticket.mitarbeiter_id).tickets.remove(ticket);
        getKunde(ticket.kunde_id).tickets.remove(ticket);
        ticket.geschlossen = true;
        db.aktualisiereTicket(ticket);
        System.out.println("Ticket: '"+ticket.text+"'["+ticket.id+"] wurde vom Kunden: '"+getKunde(ticket.kunde_id).name+"'["+ticket.kunde_id+"] geschlossen!\n");
        return ticket;
    }
    /*
    
    public String searchForAnswer(String keyword) {
        String feedback = "";
        for(Ticket t : tickets) {
            for(String answer : t.getAnswers()) {
                if(answer.toLowerCase().contains(keyword.toLowerCase())) {
                    feedback += "Ticket [ID: "+t.getId()+"] Antwort:"+answer + System.lineSeparator();
                }
            }
        }
        return feedback;
    }
    
    
    public void checkTicket() {
        for(Ticket t : tickets) {
            if(t.checkAssignment()) {
                int id = getRandomWorker().getId();
                while(t.getMitarbeiterId() == id) {
                    // soloange bis der neue Mitarbeiter nicht der selbe ist
                    id = getRandomWorker().getId();
                }
                t.assignNewWorker(id);
            }          
        }      
    }
*/
    
    @Override
    public Mitarbeiter zufallsMitarbeiter() {
        int index = new Random().nextInt(mitarbeiter.size());
        return mitarbeiter.get(index);
    }
    
    @Override
    public Kunde zufallsKunde() {
        int index = new Random().nextInt(kunden.size());
        return kunden.get(index);
    }
    
    @Override
    public Mitarbeiter getMitarbeiter(int id) {
        for(Mitarbeiter m : mitarbeiter) {
            if(m.id == id) {
                return m;
            }
        }
        return null;
    }
    
    @Override
    public Kunde getKunde(int id) {
        for(Kunde k : kunden) {
            if(k.id == id) {
                return k;
            }
        }
        return null;
    }
    
    @Override
    public void serverPrint(String text) {
        System.out.println(text);
    }
}
