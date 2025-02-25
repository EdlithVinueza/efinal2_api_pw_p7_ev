package uce.edu.ec.service.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaTo {
    private String numeroVenta;
    private String cedulaCliente;
    private BigDecimal totalVenta;
    private List<DetalleVentaTo> detallesVenta;
}