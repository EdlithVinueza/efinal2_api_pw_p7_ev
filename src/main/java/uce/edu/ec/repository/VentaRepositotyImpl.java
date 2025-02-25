package uce.edu.ec.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.modelo.Venta;

import java.util.List;

@ApplicationScoped

public class VentaRepositotyImpl implements IVentaRepositoty {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void insertar(Venta venta) {
        em.persist(venta);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Venta selecionarPorId(Integer id) {
        return em.find(Venta.class, id);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Venta> selecionarTodos() {
        return em.createQuery("SELECT v FROM Venta v", Venta.class).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void actualizar(Venta venta) {
        em.merge(venta);
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void eliminar(Integer id) {
        Venta venta = selecionarPorId(id);
        if (venta != null) {
            em.remove(venta);
        }
    }
}