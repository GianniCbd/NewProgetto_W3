package it.be.epicode.DAO;

import it.be.epicode.entities.CatalogoBibliotecario;
import it.be.epicode.entities.Libro;
import it.be.epicode.entities.Rivista;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CatalogoDao {

    private static EntityManager em;

    public CatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void save (CatalogoBibliotecario object){
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(object);
            transaction.commit();
            System.out.println( object + "salvato correttamente");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




    public void findByIdAndDelete(long id) {
        try {
            EntityTransaction transaction = em.getTransaction();
            CatalogoBibliotecario found = em.find(CatalogoBibliotecario.class, id);
            if (found != null) {
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("Catalogo eliminato");
            } else System.out.println("Catalogo non trovato");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void findByIsbn(String isbn) {
        TypedQuery<CatalogoBibliotecario> query = em.createQuery("SELECT c FROM CatalogoBibliotecario c WHERE c.codiceISBN = :isbn", CatalogoBibliotecario.class);
        query.setParameter("isbn", isbn);
        CatalogoBibliotecario catalogoBibliotecario = query.getSingleResult();

        if (catalogoBibliotecario != null) {
            if (catalogoBibliotecario instanceof Libro) {
                Libro libro = (Libro) catalogoBibliotecario;
                System.out.println("Libro trovato: " + libro);
            } else if (catalogoBibliotecario instanceof Rivista) {
                Rivista rivista = (Rivista) catalogoBibliotecario;
                System.out.println("Rivista trovata: " + rivista);
            } else {
                System.out.println("Codice ISBN non trovato");
            }
        } else {
            System.out.println("Codice ISBN non trovato");
        }
    }


    public void findLibriByTitlePattern(String pattern) {
        TypedQuery<Libro> query = em.createQuery("SELECT l FROM Libro l WHERE LOWER(l.titolo) LIKE LOWER(:pattern)", Libro.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Libro> libri = query.getResultList();
        System.out.println("Libri trovati: " + libri);
    }

    public void findRivisteByTitlePattern(String pattern) {
        TypedQuery<Rivista> query = em.createQuery("SELECT r FROM Rivista r WHERE LOWER(r.titolo) LIKE LOWER(:pattern)", Rivista.class);
        query.setParameter("pattern", "%" + pattern + "%");
        List<Rivista> riviste = query.getResultList();
        System.out.println("Riviste trovate: " + riviste);
    }

    public void findByAnnoPubblicazione(int anno) {
        TypedQuery<CatalogoBibliotecario> query = em.createQuery("SELECT c FROM CatalogoBibliotecario c WHERE c.annoDiPubblicazione = :anno", CatalogoBibliotecario.class);
        query.setParameter("anno", anno);
        List<CatalogoBibliotecario> catalogoBibliotecario = query.getResultList();

        System.out.println("Oggetti trovati: " + catalogoBibliotecario);
    }

    public void findByAutore(String autore) {
        TypedQuery<CatalogoBibliotecario> query = em.createQuery("SELECT c FROM CatalogoBibliotecario c WHERE c.autore LIKE :autore", CatalogoBibliotecario.class);
        query.setParameter("autore", "%" + autore + "%");
        List<CatalogoBibliotecario> catalogoBibliotecario = query.getResultList();

        System.out.println("Oggetti trovati: " + catalogoBibliotecario);
    }




}
