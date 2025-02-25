package uce.edu.ec.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.modelo.Producto;

import java.util.List;

@ApplicationScoped
public class ProductoRepositoryImpl implements IProductoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void insertar(Producto producto) {
        em.persist(producto);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Producto selecionarPorId(Integer id) {
        return em.find(Producto.class, id);
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Producto selecionarPorCodigoBarras(String codigoBarras) {
        try {
            return em.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras", Producto.class)
                 .setParameter("codigoBarras", codigoBarras)
                 .getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<Producto> selecionarTodos() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void actualizar(Producto producto) {
        em.merge(producto);
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void eliminar(Integer id) {
        Producto producto = selecionarPorId(id);
        if (producto != null) {
            em.remove(producto);
        }
    }
}