package appdev2_beschwerdemanagement;

import java.io.Serializable;


public class Antwort implements Serializable{
    public int id;
    public Ticket ticket;
    public int mitarbeiter_id;
    public String text;
    
    public Antwort(Ticket ticket, int mitarbeiter_id, String text){        
        this.ticket = ticket;
        this.mitarbeiter_id = mitarbeiter_id;
        this.text = text;
    }
    
    public Antwort(int id,Ticket ticket, int mitarbeiter_id, String text) {
        this.id = id;
        this.ticket = ticket;
        this.mitarbeiter_id = mitarbeiter_id;
        this.text = text; 
    }
}
