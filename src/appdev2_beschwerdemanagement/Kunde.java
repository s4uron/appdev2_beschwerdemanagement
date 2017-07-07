/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdev2_beschwerdemanagement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jan
 */
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
