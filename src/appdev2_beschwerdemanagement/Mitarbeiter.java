package appdev2_beschwerdemanagement;

import java.io.Serializable;
import java.util.ArrayList;

public class Mitarbeiter implements Serializable {
    public int id;
    public String name;
    public ArrayList<Ticket> tickets = new ArrayList<>();
    
    public Mitarbeiter(String name){        
        this.name = name;        
        System.out.println(this.name+" wurde als neuer Mitarbeiter im Beschwerdemanagement aufgenommen mit der ID: "+this.id);
    }
    
    public Mitarbeiter(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
