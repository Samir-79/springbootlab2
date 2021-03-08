package se.iths.springbootlab2.entities;


import javax.persistence.*;


@Entity
@Table(name = "Författare")
public class Författare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    String Förnamn;
    String Efternamn;

    public Författare( String förnamn, String efternamn) {

        Förnamn = förnamn;
        Efternamn = efternamn;
    }

    public Författare() {
    }






    public String getFörnamn() {
        return Förnamn;
    }

    public void setFörnamn(String förnamn) {
        this.Förnamn = förnamn;
    }

    public String getEfternamn() {
        return Efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.Efternamn = efternamn;
    }


   public String getFörfattarNamn() {
      return Förnamn + " " + Efternamn;
    }


}
