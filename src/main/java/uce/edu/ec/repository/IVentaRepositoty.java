package uce.edu.ec.repository;

import uce.edu.ec.repository.modelo.Venta;

import java.util.List;

public interface IVentaRepositoty {
    void insertar(Venta venta);
    Venta selecionarPorId(Integer id);
    List<Venta> selecionarTodos();
    void actualizar(Venta venta);
    void eliminar(Integer id);
}

