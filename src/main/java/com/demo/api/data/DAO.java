package com.demo.api.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class DAO<T> {
    private static final EntityManager entityManager = EntityManagerSingleton.getEntityManager();
    private final Class<T> entityClass;

    public DAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void save(T t) {
        EntityTransaction tx = entityManager.getTransaction();

        try{
            tx.begin();
            entityManager.persist(t);
            tx.commit();
            entityManager.refresh(t);
        }
        catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement !");
            tx.rollback();
        }
    }

    public <entityClass> T findById(Integer id){
        return entityManager.find(entityClass, id);
    }

    public List<T> findAll(){
        Query query  = entityManager.createQuery("SELECT t FROM " + entityClass.getSimpleName() + " t");
        return query.getResultList();
    }


    public void delete(T t) {
        EntityTransaction tx = entityManager.getTransaction();

        try{
            tx.begin();
            entityManager.remove(t);
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la suppression !");
            tx.rollback();
        }
    }

    public void deleteById(Integer id) {
        EntityTransaction tx = entityManager.getTransaction();

        try{
            tx.begin();
            Query query = entityManager.createQuery("DELETE FROM " + entityClass.getSimpleName() + " t WHERE t.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la suppression avec params !");
            tx.rollback();
        }
    }

    public void update(T t){
        EntityTransaction tx = entityManager.getTransaction();

        try{
            tx.begin();
            entityManager.merge(t);
            tx.commit();
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la modification !");
            tx.rollback();
        }
    }


    public void patch(T t){
        EntityTransaction tx = entityManager.getTransaction();

        try{
            tx.begin();
            entityManager.merge(t);
            tx.commit();
            entityManager.refresh(t);
        }
        catch (Exception e) {
            System.out.println("Erreur lors de la modification !");
            tx.rollback();
        }
    }

}