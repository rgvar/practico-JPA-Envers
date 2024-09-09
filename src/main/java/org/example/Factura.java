package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Audited
public class Factura implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private String fecha;
    private int total;

    // Relación cliente - facturas
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    // Relación factura - detalles
    @OneToMany(mappedBy ="factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<DetalleFactura> detalles = new HashSet<>();

}
