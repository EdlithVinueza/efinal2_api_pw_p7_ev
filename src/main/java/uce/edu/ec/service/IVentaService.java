package uce.edu.ec.service;

import java.util.List;

import uce.edu.ec.service.to.VentaTo;

public interface IVentaService {

    void crearVenta(VentaTo ventaTo);
    
    VentaTo buscarVentaPorId(Integer id);

    List<VentaTo> todasLasVentas();

    

}
