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
public class Categoria implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String denominacion;

    // Relación artículos - categorías
    @ManyToMany(mappedBy = "categorias", cascade = CascadeType.PERSIST)
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

}
