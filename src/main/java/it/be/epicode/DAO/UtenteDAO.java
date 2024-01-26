package it.be.epicode.DAO;

import it.be.epicode.entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UtenteDAO {

    private static EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save (Utente utente){
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(utente);
            transaction.commit();
            System.out.println( utente + "salvato correttamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
