package uce.edu.ec.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.IProductoRepository;
import uce.edu.ec.repository.modelo.Producto;
import uce.edu.ec.service.to.ProductoTo;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductoServiceImpl implements IProductoService{

    @Inject
    IProductoRepository productoRepository;

    @Transactional
    public void crearProducto(ProductoTo productoTo) {
        Producto producto = productoRepository.selecionarPorCodigoBarras(productoTo.getCodigoBarras());
        if (producto != null) {
            producto.setStock(producto.getStock() + productoTo.getStock());
            productoRepository.actualizar(producto);
        } else {
            producto = new Producto();
            producto.setCodigoBarras(productoTo.getCodigoBarras());
            producto.setNombre(productoTo.getNombre());
            producto.setCategoria(productoTo.getCategoria());
            producto.setStock(productoTo.getStock());
            producto.setPrecio(productoTo.getPrecio());
            productoRepository.insertar(producto);
        }
    }

    public ProductoTo buscarProductoPorId(Integer id) {
        Producto producto = productoRepository.selecionarPorId(id);
        return new ProductoTo(
                producto.getId(),
                producto.getCodigoBarras(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getStock(),
                producto.getPrecio()
        );
    }

    public ProductoTo buscarProductoPorCodigoBarras(String codigoBarras) {
        Producto producto = productoRepository.selecionarPorCodigoBarras(codigoBarras);
        return new ProductoTo(
                producto.getId(),
                producto.getCodigoBarras(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getStock(),
                producto.getPrecio()
        );
    }

    public List<ProductoTo> todosLosProductos() {
        return productoRepository.selecionarTodos().stream().map(producto -> new ProductoTo(
                producto.getId(),
                producto.getCodigoBarras(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getStock(),
                producto.getPrecio()
        )).collect(Collectors.toList());
    }

    @Transactional
    public void actualizarProducto(ProductoTo productoTo) {
        Producto producto = productoRepository.selecionarPorId(productoTo.getId());
        if (producto != null) {
            producto.setCodigoBarras(productoTo.getCodigoBarras());
            producto.setNombre(productoTo.getNombre());
            producto.setCategoria(productoTo.getCategoria());
            producto.setStock(productoTo.getStock());
            producto.setPrecio(productoTo.getPrecio());
            productoRepository.actualizar(producto);
        }
    }

    @Transactional
    public void eliminarProducto(Integer id) {
        productoRepository.eliminar(id);
    }
}