package se.iths.springbootlab2.dtos;

import se.iths.springbootlab2.entities.Författare;

import java.sql.Date;

public class BooksDto {

    private String ISBN13;
    private String titel;
    private String language;
    private Double Pris;
    private Date utgivningsdatum;

    private Författare författare;


    public BooksDto(String ISBN13, String titel, String language, Double pris, Date utgivningsdatum, Författare författare) {
        this.ISBN13 = ISBN13;
        this.titel = titel;
        this.language = language;
        Pris = pris;
        this.utgivningsdatum = utgivningsdatum;
        this.författare = författare;
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

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getPris() {
        return Pris;
    }

    public void setPris(Double pris) {
        Pris = pris;
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
