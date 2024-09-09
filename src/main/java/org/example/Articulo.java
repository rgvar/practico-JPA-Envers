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
@ToString(exclude = {"categorias", "detalles"})
@EqualsAndHashCode(exclude = {"categorias", "detalles"})
@Builder
@Entity
@Audited
public class Articulo implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;
    private int cantidad;
    private int precio;

    // Relación artículo - detalles
    @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
    @Builder.Default
    private Set<DetalleFactura> detalles = new HashSet<>();

    // Relación artículos - categorías
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "articulo_categoria",
            joinColumns = @JoinColumn(name = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();
}
