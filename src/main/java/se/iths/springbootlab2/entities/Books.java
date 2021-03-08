package se.iths.springbootlab2.entities;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Böcker")

public class Books {



    @Id
    String ISBN13;
    String titel;
    String language;
    Double pris;
    Date utgivningsdatum;


    @ManyToOne
    @JoinColumn(name = "Författare_id")
    private Författare författare;




    public Books(String ISBN13, String titel, String language, Double pris, Date utgivningsdatum, Författare författare) {
        this.ISBN13 = ISBN13;
        this.titel = titel;
        this.language = language;
        this.pris = pris;
        this.utgivningsdatum = utgivningsdatum;
        this.författare = författare;


    }


    public Books() {

    }


    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String språk) {
        this.language = språk;
    }

    public Double getPris() {
        return pris;
    }

    public void setPris(Double pris) {
        this.pris = pris;
    }

    public Date getUtgivningsdatum() {
        return utgivningsdatum;
    }

    public void setUtgivningsdatum(Date utgivningsdatum) {
        this.utgivningsdatum = utgivningsdatum;
    }

    public Författare getFörfattare() {
        return författare;
    }


    public void setFörfattare(Författare författare) {
        this.författare = författare;
    }
}
