package it.be.epicode.entities;

import it.be.epicode.allEnum.Periodicita;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Rivista")
public class Rivista extends CatalogoBibliotecario{
    @Column
    private Periodicita periodicita;

    public Rivista(){}

    public Rivista(String codiceISBN, String titolo, int annoDiPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoDiPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "," + getTitolo() +
                "periodicita=" + periodicita +
                '}';
    }
}
