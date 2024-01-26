package it.be.epicode.entities;

import it.be.epicode.allEnum.Genere;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Libro")
public class Libro extends CatalogoBibliotecario{

    private String autore;

    private Genere genere;


    public Libro(){}

    public Libro(String codiceISBN, String titolo, int annoDiPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(codiceISBN, titolo, annoDiPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                getTitolo() +
                ", genere=" + genere +
                '}';
    }
}