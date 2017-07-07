/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev2_beschwerdemanagement;

import java.io.Serializable;

/**
 *
 * @author Jan
 */
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
