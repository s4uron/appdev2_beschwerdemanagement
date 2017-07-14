package appdev2_beschwerdemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Datenbank {
    String url = "jdbc:mysql://localhost:3306/beschwerdemanagement";
    String username = "root";
    String password = "test1234";
    Connection connection;
    
    public Datenbank() throws SQLException {
        System.out.println("Verbinde zur Datenbank...");
        try {
            // Eine verbindung wird versucht herzustellen
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung erfolgreich!");   
        } catch (SQLException ex) {
            throw new SQLException("Kann nicht zur Datenbank verbinden! "+ex.getMessage());
        }
    }
    
    public void close() {
        try {
            // verbindung kann ggf. geschlossen werden
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Datenbankverbindung kann nicht geschlossen werden! "+ex.getMessage());
        }
    }
    
    /**
     * Methode um alle Kunden aus der Datenbank zu laden und als ArrayList voller Kunden Objekte zurückzugeben
     * @return ArrayList<Benutzer> benutzerliste
     */
    public ArrayList<Kunde> ladeKunden() {
        System.out.println("Lade Kunden:");
        System.out.println("---------------------------------------");
        ArrayList<Kunde> kundenliste = new ArrayList<>();
        try {            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM kunden");
            while (rs.next()) {
                Kunde kunde = new Kunde(rs.getInt("id"),rs.getString("name"));
                kunde.tickets = ladeTicketsVomKunden(kunde);
                kundenliste.add(kunde);
                System.out.println(kunde.name+" ["+kunde.id+"] mit "+kunde.tickets.size()+" Tickets");
            }
            st.close();
            rs.close();   
        } catch (SQLException ex) {
             System.out.println("Fehler beim Laden der Kunden! "+ex.getMessage());
        }
        System.out.println("---------------------------------------");
        return kundenliste;
    }
    
    /**
     * Methode um alle Kunden aus der Datenbank zu laden und als ArrayList voller Kunden Objekte zurückzugeben
     * @return ArrayList<Benutzer> benutzerliste
     */
    public ArrayList<Mitarbeiter> ladeMitarbeiter() {
        System.out.println("Lade Mitarbeiter:");
        System.out.println("---------------------------------------");
        ArrayList<Mitarbeiter> mitarbeiterliste = new ArrayList<>();
        try {            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM mitarbeiter");
            while (rs.next()) {
                Mitarbeiter mitarbeiter = new Mitarbeiter(rs.getInt("id"),rs.getString("name"));
                mitarbeiter.tickets = ladeTicketsVomMitarbeiter(mitarbeiter);
                mitarbeiterliste.add(mitarbeiter);
                System.out.println(mitarbeiter.name+" ["+mitarbeiter.id+"] mit "+mitarbeiter.tickets.size()+" Tickets");
            }
            st.close();
            rs.close();   
        } catch (SQLException ex) {
             System.out.println("Fehler beim Laden der Mitarbeiter! "+ex.getMessage());
        }
        System.out.println("---------------------------------------");
        return mitarbeiterliste;
    }
    
        public ArrayList<Ticket> ladeTicketsVomKunden(Kunde kunde) {
        ArrayList<Ticket> ticketliste = new ArrayList<>();
        try {            
            PreparedStatement st = connection.prepareStatement("SELECT * FROM tickets WHERE `k_id` = ?");
            st.setInt(1, kunde.id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("id"),rs.getString("text"),rs.getTimestamp("erstellung"),rs.getTimestamp("letzte_bearbeitung"),rs.getInt("ma_id"),rs.getInt("k_id"),rs.getBoolean("geschlossen"));
                ticket.antworten = ladeAntworten(ticket);
                ticketliste.add(ticket);
            }
            st.close();
            rs.close();   
        } catch (SQLException ex) {
             System.out.println("Fehler beim Laden der Mitarbeiter! "+ex.getMessage());
        }
        return ticketliste;
    }
    
    public ArrayList<Ticket> ladeTicketsVomMitarbeiter(Mitarbeiter mitarbeiter) {
        ArrayList<Ticket> ticketliste = new ArrayList<>();
        try {            
            PreparedStatement st = connection.prepareStatement("SELECT * FROM tickets WHERE `ma_id` = ?");
            st.setInt(1, mitarbeiter.id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("id"),rs.getString("text"),rs.getTimestamp("erstellung"),rs.getTimestamp("letzte_bearbeitung"),rs.getInt("ma_id"),rs.getInt("k_id"),rs.getBoolean("geschlossen"));
                ticket.antworten = ladeAntworten(ticket);
                ticketliste.add(ticket);
            }
            st.close();
            rs.close();   
        } catch (SQLException ex) {
             System.out.println("Fehler beim Laden der Mitarbeiter! "+ex.getMessage());
        }
        return ticketliste;
    }
    
    public ArrayList<Antwort> ladeAntworten(Ticket ticket) {
        ArrayList<Antwort> antwortliste = new ArrayList<>();
        try {            
            PreparedStatement st = connection.prepareStatement("SELECT * FROM antworten WHERE `t_id` = ?");
            st.setInt(1, ticket.id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Antwort antwort = new Antwort(rs.getInt("id"),ticket,ticket.mitarbeiter_id,rs.getString("text"));
                antwortliste.add(antwort);
            }
            st.close();
            rs.close();   
        } catch (SQLException ex) {
             System.out.println("Fehler beim Laden der Mitarbeiter! "+ex.getMessage());
        }
        return antwortliste;
    }
    
    public Kunde ladeKunde(int kunden_id) {
        Kunde kunde = null;
        try {            
            PreparedStatement st = connection.prepareStatement("SELECT * FROM kunden WHERE id = ?");
            st.setInt(1, kunden_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                kunde = new Kunde(rs.getInt("id"),rs.getString("name"));
                kunde.tickets = ladeTicketsVomKunden(kunde);
            }
            st.close();
            rs.close();   
        } catch (SQLException ex) {
             System.out.println("Fehler beim Laden des Kunden mit der ID: "+kunden_id+"! "+ex.getMessage());
        }
        return kunde;
    }
    
     public Mitarbeiter ladeMitarbeiter(int mitarbeiter_id) {
        Mitarbeiter mitarbeiter = null;
        try {            
            PreparedStatement st = connection.prepareStatement("SELECT * FROM mitarbeiter WHERE id = ?");
            st.setInt(1, mitarbeiter_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                mitarbeiter = new Mitarbeiter(rs.getInt("id"),rs.getString("name"));
                mitarbeiter.tickets = ladeTicketsVomMitarbeiter(mitarbeiter);
            }
            st.close();
            rs.close();   
        } catch (SQLException ex) {
             System.out.println("Fehler beim Laden des Kunden mit der ID: "+mitarbeiter_id+"! "+ex.getMessage());
        }
        return mitarbeiter;
    }
    
    /**
     * Methode um ein Ticket zu speichern.
     * @param ticket Ein Ticket wird übergeben
     */
    public int speicherTicket(Ticket ticket) {
        int id = -1;
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO `tickets` (`id`, `text`, `erstellung`, `letzte_bearbeitung`, `ma_id`, `k_id`, `geschlossen`) VALUES (NULL, ?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1, ticket.text);
            st.setTimestamp(2, ticket.erstellung);
            st.setTimestamp(3, ticket.letzte_bearbeitung);
            st.setInt(4, ticket.mitarbeiter_id);
            st.setInt(5, ticket.kunde_id);
            st.setBoolean(6, ticket.geschlossen);
            st.executeUpdate();
                        ResultSet rs = st.getGeneratedKeys();
            if (rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim speichern des Tickets ("+ticket.text+")! "+ex.getMessage());
        }
        return id;
    }
    
        /**
     * Methode um eine Antwort zu speichern.
     * @param antwort Eine Antwort wird übergeben
     */
    public int speicherAntwort(Antwort antwort) {
        int id = -1;
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO `antworten` (`id`, `text`, `t_id`, `ma_id`) VALUES (NULL, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1, antwort.text);
            st.setInt(2, antwort.ticket.id);
            st.setInt(3, antwort.mitarbeiter_id);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim speichern der Antwort ("+antwort.text+")! "+ex.getMessage());
        }
        return id;
    }
    
    public void aktualisiereTicket(Ticket ticket) {
        try {
            PreparedStatement st = connection.prepareStatement("UPDATE `tickets` SET `ma_id` = ?, `letzte_bearbeitung` = ?, `geschlossen` = ? WHERE `id` = ?;");
            st.setInt(1, ticket.mitarbeiter_id);
            st.setTimestamp(2, ticket.letzte_bearbeitung);
            st.setBoolean(3, ticket.geschlossen);
            st.setInt(4, ticket.id);
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim aktualisieren des Tickets ("+ticket.text+")! "+ex.getMessage());
        }
    }
    
    /**
     * Methode um alle Benutzer zu löschen, sodass die Tabelle leer ist
     */
    public void resetDB() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("TRUNCATE `tickets`");
            st.executeUpdate("TRUNCATE `antworten`");
            st.close();
        } catch (SQLException ex) {
            System.out.println("Fehler beim löschen aller Tickets & Antworten! "+ex.getMessage());
        }
    }
}
