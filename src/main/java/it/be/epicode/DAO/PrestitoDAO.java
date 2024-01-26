package it.be.epicode.DAO;

import it.be.epicode.entities.Prestito;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrestitoDAO {
    private static EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void save (Prestito prestito){
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(prestito);
            transaction.commit();
            System.out.println( prestito + "salvato correttamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



    public void findElementiInPrestitoByIdCard (int numeroTessera){
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroDiTessera = :numeroTessera", Prestito.class );
        query.setParameter("numeroTessera",numeroTessera);
        List<Prestito> prestiti = query.getResultList();
        for (Prestito prestito : prestiti) {
            System.out.println("Elemento in prestito: " + prestito);
        }
    }

    public void findElementiNonrestituiti() {
        TypedQuery<Prestito> query = em.createNamedQuery("Prestito.getDatePr", Prestito.class);

        List<Prestito> risultati = query.getResultList();
        for (Prestito prestito : risultati) {
            System.out.println("Elemento in prestito: " + prestito);
        }
    }
}
