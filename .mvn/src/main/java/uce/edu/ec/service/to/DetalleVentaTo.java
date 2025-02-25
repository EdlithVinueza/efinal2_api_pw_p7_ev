package uce.edu.ec.service.to;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uce.edu.ec.repository.modelo.Producto;
import uce.edu.ec.repository.modelo.Venta;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaTo {

    private Integer id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subtotal;

    private Venta venta;

    private Producto producto;
}
