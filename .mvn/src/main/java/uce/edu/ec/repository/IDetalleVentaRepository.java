package uce.edu.ec.repository;

import uce.edu.ec.repository.modelo.DetalleVenta;

import java.util.List;

public interface IDetalleVentaRepository {
    void insertar(DetalleVenta detalleVenta);
    DetalleVenta selecionarPorId(Integer id);
    List<DetalleVenta> selecionarTodos();
    void actualziar(DetalleVenta detalleVenta);
    void eliminar(Integer id);
}