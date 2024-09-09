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
@ToString(exclude = "facturas")
@EqualsAndHashCode(exclude = "facturas")
@Builder
@Entity
@Audited
public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;

    // Relación cliente - domicilio
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    // Relación cliente - facturas
    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private Set<Factura> facturas = new HashSet<>();
}
