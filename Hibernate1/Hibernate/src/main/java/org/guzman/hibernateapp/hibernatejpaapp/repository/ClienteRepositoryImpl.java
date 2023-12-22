package org.guzman.hibernateapp.hibernatejpaapp.repository;

import org.guzman.hibernateapp.hibernatejpaapp.model.Cliente;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
    @Autowired
    private SessionFactory sessionFactory;


    public Cliente getClienteById(Integer id){
        try    (Session session = sessionFactory.openSession() ){
            return session.get(Cliente.class, id);
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public List<Cliente> getAllClientes() {
        try (Session session = sessionFactory.openSession()) {
           return session.createQuery("FROM Cliente", Cliente.class).getResultList();
        } catch (HibernateException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    public void createCliente(Cliente cliente) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(cliente);
            transaction.commit();

        } catch (HibernateException exception) {
            exception.printStackTrace();

        }
    }

    @Override
    public List<Cliente> getClientesByNombre(String nombre) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Cliente c where c.nombre=:nombre", Cliente.class)
                    .setParameter("nombre", nombre)
                    .getResultList();

        } catch (HibernateException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    public void updateNombreCliente(Integer clienteId, Cliente cliente) {

        try(Session session = sessionFactory.openSession()){
            Cliente updateCliente = session.get(Cliente.class, clienteId);
            updateCliente.setNombre(cliente.getNombre());
            updateCliente.setApellido(cliente.getApellido());
            updateCliente.setForma_pago(cliente.getForma_pago());


            Transaction transaction = session.beginTransaction();
            session.merge(updateCliente);
            transaction.commit();

        } catch (HibernateException exception) {
            exception.printStackTrace();

        }
    }

    public void deleteCliente(Integer clienteId) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, clienteId);
            session.remove(cliente);
            transaction.commit();
           // return "Cliente " + cliente.getNombre() + " fue eliminado";
        } catch (HibernateException exception) {
            exception.printStackTrace();

        }
    }
}
