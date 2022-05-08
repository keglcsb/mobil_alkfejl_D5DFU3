package hu.kotprog.mobilalkfejlkotprog.Model;

public class Jarat {
    String id;
    String nev;
    String indulasperc;
    boolean unnep;
    String indulas;
    String erkezes;
    int foglalasCount;
    public Jarat() {

    }

    public Jarat(String nev, String indulasperc, boolean unnep, String indulas, String erkezes) {
        this.nev = nev;
        this.indulasperc = indulasperc;
        this.unnep = unnep;
        this.indulas = indulas;
        this.erkezes = erkezes;
        this.foglalasCount=0;
    }

    public String getNev() {
        return nev;
    }

    public String getIndulasperc() {
        return indulasperc;
    }

    public boolean isUnnep() {
        return unnep;
    }

    public String getIndulas() {
        return indulas;
    }

    public String getErkezes() {
        return erkezes;
    }

    public int getFoglalasCount(){
        return this.foglalasCount;
    }

    public String _getId() {
        return id;
    }

    public void setId(String id){
        this.id=id;
    }
}
