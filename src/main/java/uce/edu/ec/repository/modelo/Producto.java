package uce.edu.ec.repository.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @SequenceGenerator(name ="sec_producto",sequenceName = "sec_producto",allocationSize = 1 )
    @GeneratedValue(generator = "sec_producto", strategy = GenerationType.SEQUENCE)
    @Column(name = "prod_id") 
    private Integer id;

    @Column(name = "codigo_barras", unique = true, nullable = false)
    private String codigoBarras;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "precio", nullable = false)
    private Double precio;
}