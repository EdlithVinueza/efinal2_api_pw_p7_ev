package uce.edu.ec.service;

import java.util.List;

import uce.edu.ec.service.to.ProductoTo;

public interface IProductoService {

    void crearProducto(ProductoTo productoTo) ;
    ProductoTo buscarProductoPorId(Integer id) ;
    ProductoTo buscarProductoPorCodigoBarras(String codigoBarras);
    List<ProductoTo> todosLosProductos();
    void actualizarProducto(ProductoTo productoTo) ;
    void eliminarProducto(Integer id);
    

}
