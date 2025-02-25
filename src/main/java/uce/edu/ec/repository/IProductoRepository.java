package uce.edu.ec.repository;

import uce.edu.ec.repository.modelo.Producto;

import java.util.List;

public interface IProductoRepository {
    void insertar(Producto producto);
    Producto selecionarPorId(Integer id);
    Producto selecionarPorCodigoBarras(String codigoBarras);
    List<Producto> selecionarTodos();
    void actualizar(Producto producto);
    void eliminar(Integer id);
}