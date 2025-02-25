package uce.edu.ec.repository.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @SequenceGenerator(name ="sec_venta",sequenceName = "sec_venta",allocationSize = 1 )
    @GeneratedValue(generator = "sec_venta", strategy = GenerationType.SEQUENCE)
    @Column(name = "venta_id") 
    private Integer id;

    @Column(name = "numero_venta", nullable = false)
    private String numeroVenta;

    @Column(name = "cedula_cliente", nullable = false)
    private String cedulaCliente;

    @Column(name = "total_venta", nullable = false)
    private BigDecimal totalVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleVenta> detallesVenta;
}