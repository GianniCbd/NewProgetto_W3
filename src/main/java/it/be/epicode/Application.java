package it.be.epicode;

import com.github.javafaker.Faker;
import it.be.epicode.DAO.CatalogoDao;
import it.be.epicode.DAO.PrestitoDAO;
import it.be.epicode.DAO.UtenteDAO;
import it.be.epicode.allEnum.Genere;
import it.be.epicode.allEnum.Periodicita;
import it.be.epicode.entities.Libro;
import it.be.epicode.entities.Prestito;
import it.be.epicode.entities.Rivista;
import it.be.epicode.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("New_progetto_w3");

    public static void main(String[] args){

        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(Locale.ITALY);
        CatalogoDao catalogoDao = new CatalogoDao(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);

        System.out.println("*****aggiungi al catalogo*****");
        Libro libro1 = new Libro(faker.code().isbn13(),faker.book().title(),faker.number().numberBetween(1990,2024),faker.number().numberBetween(50,200),faker.book().author(), Genere.FANTASY);
        catalogoDao.save(libro1);
        Rivista rivista1 = new Rivista(faker.code().isbn13(),faker.book().title(),2020,faker.number().numberBetween(40,100), Periodicita.SEMESTRALE);
        catalogoDao.save(rivista1);
        System.out.println(" ");
        System.out.println("*****trovate tramite nome*****");
        catalogoDao.findLibriByTitlePattern("The Needle's Eye");
        catalogoDao.findRivisteByTitlePattern("Let Us Now Praise Famous Men");
        System.out.println(" ");

        //elimina tramite ID
//        catalogoDao.findByIdAndDelete(1); (funziona!)
        System.out.println(" ");
        System.out.println("*****trovate tramite ISBN*****");
        catalogoDao.findByIsbn("9780896257559");
        System.out.println(" ");
        System.out.println("*****Trova per anno pubblicazione*****");
        catalogoDao.findByAnnoPubblicazione(2020);
        System.out.println(" ");
        System.out.println("*****Libri trovati tramite autore*****");
        catalogoDao.findByAutore("Tosca Ferraro");

        //Utente
        Set<Prestito> prestiti = new HashSet<>();

        System.out.println(" ");
        System.out.println("*****Utente*****");
        Utente utente1 = new Utente(faker.name().firstName(), faker.name().lastName(), faker.date().birthday(), faker.number().numberBetween(200, 300));
        utenteDAO.save(utente1);


        System.out.println(" ");
        System.out.println("*****Prestito*****");

        Prestito prestito1 = new Prestito(utente1, libro1, new Date(), null, null);

        int giorniPrestito = faker.random().nextInt(1, 30);
        Date dataInizio = new Date();
        dataInizio.setTime(dataInizio.getTime() - giorniPrestito * 86400000); //(86400000) sono i millisecondi in un giorno.
        prestito1.setDataInizioPrestito(dataInizio);
        // data di restituzione prevista
        Date dataRestituzionePrevista = new Date(dataInizio.getTime() + 7 * 86400000);
        prestito1.setDataRestituzionePrevista(dataRestituzionePrevista);
        // data di restituzione effettiva
        boolean restituitoInTempo = faker.random().nextBoolean();
        if (restituitoInTempo) {
            prestito1.setDataRestituzioneEffettiva(dataRestituzionePrevista);
        } else {
            Date dataRestituzioneEffettiva = new Date(dataRestituzionePrevista.getTime() + faker.random().nextInt(1, 30) * 86400000);
            prestito1.setDataRestituzioneEffettiva(dataRestituzioneEffettiva);
        }
        prestiti.add(prestito1);
        prestitoDAO.save(prestito1);

        System.out.println(" ");
        System.out.println("*****Elementi trovati tramite IdCard*****");
        prestitoDAO.findElementiInPrestitoByIdCard(296);

        System.out.println(" ");
        System.out.println("*****Elementi non restituiti*****");
        prestitoDAO.findElementiNonrestituiti();
    }


}
