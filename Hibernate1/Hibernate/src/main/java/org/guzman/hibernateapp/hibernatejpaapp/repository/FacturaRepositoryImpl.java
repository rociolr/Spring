package org.guzman.hibernateapp.hibernatejpaapp.repository;

import org.guzman.hibernateapp.hibernatejpaapp.model.Factura;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class FacturaRepositoryImpl {
    @Autowired
    private SessionFactory sessionFactory;


    public List<Factura> getAllFacturas() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Factura", Factura.class).getResultList();
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    public Factura createFactura(Factura factura) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(factura);
            transaction.commit();
            return factura;
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}


