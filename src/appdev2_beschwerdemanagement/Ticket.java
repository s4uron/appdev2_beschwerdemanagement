package appdev2_beschwerdemanagement;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Ticket implements Serializable{
    public int id;
    public String text;
    public Timestamp erstellung;
    public Timestamp letzte_bearbeitung;
    public int mitarbeiter_id;
    public int kunde_id;
    public boolean geschlossen;
    public ArrayList<Antwort> antworten = new ArrayList(); 
    
    public Ticket(String text, int mitarbeiter_id, int kunde_id) {
        this.text = text;
        this.erstellung = new Timestamp(System.currentTimeMillis());
        this.letzte_bearbeitung = new Timestamp(System.currentTimeMillis());
        this.mitarbeiter_id = mitarbeiter_id;
        this.kunde_id = kunde_id;
        this.geschlossen = false;
    }
    
    public Ticket(int id, String text, Timestamp erstellung, Timestamp letzte_bearbeitung, int mitarbeiter_id, int kunde_id, boolean geschlossen) {
        this.id = id;
        this.text = text;
        this.erstellung = erstellung;
        this.letzte_bearbeitung = letzte_bearbeitung;
        this.mitarbeiter_id = mitarbeiter_id;
        this.kunde_id = kunde_id;
        this.geschlossen = geschlossen;
   }
    
    public Ticket assignNewWorker(int mitarbeiter_id) {
        this.letzte_bearbeitung = new Timestamp(System.currentTimeMillis());
        this.mitarbeiter_id = mitarbeiter_id;
        return this;
    }
    
    public Ticket addAnswer(int mitarbeiter_id, String text) {
        Antwort antwort = new Antwort(this, mitarbeiter_id, text);
        this.antworten.add(antwort);
        return this;
    }
    
    public Ticket close() {
        this.geschlossen = true;
        return this;
    }
    
}
