package uce.edu.ec.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.modelo.DetalleVenta;

import java.util.List;

@ApplicationScoped

public class DetalleVentaRepositoryImpl implements IDetalleVentaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void insertar(DetalleVenta detalleVenta) {
        em.persist(detalleVenta);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public DetalleVenta selecionarPorId(Integer id) {
        return em.find(DetalleVenta.class, id);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<DetalleVenta> selecionarTodos() {
        return em.createQuery("SELECT d FROM DetalleVenta d", DetalleVenta.class).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void actualziar(DetalleVenta detalleVenta) {
        em.merge(detalleVenta);
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void eliminar(Integer id) {
        DetalleVenta detalleVenta = selecionarPorId(id);
        if (detalleVenta != null) {
            em.remove(detalleVenta);
        }
    }
}