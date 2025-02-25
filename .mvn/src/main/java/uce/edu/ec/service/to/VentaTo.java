package uce.edu.ec.service.to;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uce.edu.ec.repository.modelo.DetalleVenta;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaTo {

    private String numeroVenta;
    private String cedulaCliente;
    private BigDecimal totalVenta;
    private List<DetalleVenta> detallesVenta;



}
