package uce.edu.ec.service.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoTo {
    private Integer id;
    private String codigoBarras;
    private String nombre;
    private String categoria;
    private Integer stock;
    private Double precio;
}