package appdev2_beschwerdemanagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Kunde implements Serializable {
    public int id;
    public String name;
    public ArrayList<Ticket> tickets = new ArrayList<>();
    
    public Kunde(String name) {        
        this.name = name;
        System.out.println(this.name+" wurde als neuer Kunde im Beschwerdemanagement angelegt mit der ID: "+this.id);
    }
    
    public Kunde(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
