package uce.edu.ec.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.repository.IVentaRepositoty;
import uce.edu.ec.repository.IProductoRepository;
import uce.edu.ec.repository.IDetalleVentaRepository;
import uce.edu.ec.repository.modelo.Venta;
import uce.edu.ec.repository.modelo.Producto;
import uce.edu.ec.repository.modelo.DetalleVenta;
import uce.edu.ec.service.to.VentaTo;
import uce.edu.ec.service.to.DetalleVentaTo;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VentaServiceImpl  implements IVentaService{

    @Inject
    IVentaRepositoty ventaRepository;

    @Inject
    IProductoRepository productoRepository;

    @Inject
    IDetalleVentaRepository detalleVentaRepository;

    @Transactional
    public void crearVenta(VentaTo ventaTo) {
        Venta venta = new Venta();
        venta.setNumeroVenta(ventaTo.getNumeroVenta());
        venta.setCedulaCliente(ventaTo.getCedulaCliente());
        venta.setTotalVenta(ventaTo.getTotalVenta());

        List<DetalleVenta> detallesVenta = ventaTo.getDetallesVenta().stream().map(detalleTo -> {
            Producto producto = productoRepository.selecionarPorId(detalleTo.getProductoId());
            if (producto.getStock() < detalleTo.getCantidad()) {
                throw new RuntimeException("Stock no disponible para el producto: " + producto.getNombre());
            }
            producto.setStock(producto.getStock() - detalleTo.getCantidad());
            productoRepository.actualizar(producto);

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setCantidad(detalleTo.getCantidad());
            detalleVenta.setPrecioUnitario(detalleTo.getPrecioUnitario());
            detalleVenta.setSubtotal(detalleTo.getSubtotal());
            detalleVenta.setProducto(producto);
            detalleVenta.setVenta(venta);
            return detalleVenta;
        }).collect(Collectors.toList());

        venta.setDetallesVenta(detallesVenta);
        ventaRepository.insertar(venta);
    }

    public VentaTo buscarVentaPorId(Integer id) {
        Venta venta = ventaRepository.selecionarPorId(id);
        List<DetalleVentaTo> detallesVentaTo = venta.getDetallesVenta().stream().map(detalle -> {
            DetalleVentaTo detalleTo = new DetalleVentaTo();
            detalleTo.setCantidad(detalle.getCantidad());
            detalleTo.setPrecioUnitario(detalle.getPrecioUnitario());
            detalleTo.setSubtotal(detalle.getSubtotal());
            detalleTo.setProductoId(detalle.getProducto().getId());
            return detalleTo;
        }).collect(Collectors.toList());

        VentaTo ventaTo = new VentaTo();
        ventaTo.setNumeroVenta(venta.getNumeroVenta());
        ventaTo.setCedulaCliente(venta.getCedulaCliente());
        ventaTo.setTotalVenta(venta.getTotalVenta());
        ventaTo.setDetallesVenta(detallesVentaTo);
        return ventaTo;
    }

    public List<VentaTo> todasLasVentas() {
        return ventaRepository.selecionarTodos().stream().map(venta -> {
            List<DetalleVentaTo> detallesVentaTo = venta.getDetallesVenta().stream().map(detalle -> {
                DetalleVentaTo detalleTo = new DetalleVentaTo();
                detalleTo.setCantidad(detalle.getCantidad());
                detalleTo.setPrecioUnitario(detalle.getPrecioUnitario());
                detalleTo.setSubtotal(detalle.getSubtotal());
                detalleTo.setProductoId(detalle.getProducto().getId());
                return detalleTo;
            }).collect(Collectors.toList());

            VentaTo ventaTo = new VentaTo();
            ventaTo.setNumeroVenta(venta.getNumeroVenta());
            ventaTo.setCedulaCliente(venta.getCedulaCliente());
            ventaTo.setTotalVenta(venta.getTotalVenta());
            ventaTo.setDetallesVenta(detallesVentaTo);
            return ventaTo;
        }).collect(Collectors.toList());
    }
}


/*
 * En el controller debemos teneh phatparama, queryparamoam produce y comsume json
 */