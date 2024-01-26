package it.be.epicode.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "Prestito.getDatePr", query = "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista <= CURRENT_DATE AND p.dataRestituzioneEffettiva IS NULL")
public class Prestito {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "catalogo_id")
    private CatalogoBibliotecario catalogoBibliotecario;
    private Date dataInizioPrestito;
    private Date dataRestituzionePrevista;
    private Date dataRestituzioneEffettiva;


    public Prestito() {
    }

    public Prestito(Utente utente, CatalogoBibliotecario catalogoBibliotecario, Date dataInizioPrestito, Date dataRestituzionePrevista, Date dataRestituzioneEffettiva) {
        this.utente = utente;
        this.catalogoBibliotecario = catalogoBibliotecario;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataRestituzionePrevista;
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public CatalogoBibliotecario getCatalogoBibliotecario() {
        return catalogoBibliotecario;
    }

    public void setCatalogoBibliotecario(CatalogoBibliotecario catalogoBibliotecario) {
        this.catalogoBibliotecario = catalogoBibliotecario;
    }

    public Date getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(Date dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public Date getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(Date dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public Date getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(Date dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", catalogoBibliotecario=" + catalogoBibliotecario +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }
}
