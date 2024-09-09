package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();
        System.out.println("Factory y Entity Manager creados ... ");

        try {
            em.getTransaction().begin();

            // Creación categorías
            Categoria maquinas = Categoria.builder()
                    .denominacion("Máquinas")
                    .build();
            Categoria repuestos = Categoria.builder()
                    .denominacion("Repuestos")
                    .build();

            // Creación artículos
            Articulo cultivadora = Articulo.builder()
                    .denominacion("Cultivadora")
                    .cantidad(4)
                    .precio(2500)
                    .build();
            Articulo pua = Articulo.builder()
                    .denominacion("Pua")
                    .cantidad(100)
                    .precio(30)
                    .build();

            // Relaciones Categorías-Artículos
            maquinas.getArticulos().add(cultivadora);
            repuestos.getArticulos().add(pua);

            cultivadora.getCategorias().add(maquinas);
            pua.getCategorias().add(repuestos);

            // Creación clientes
            Cliente cliente1 = Cliente.builder()
                    .nombre("Rodrigo")
                    .apellido("Vargas")
                    .dni(41443045)
                    .build();

            // Creación domicilios
            Domicilio domicilio1 = Domicilio.builder()
                    .numero(4785)
                    .nombreCalle("Tomás Godoy Cruz")
                    .build();

            // Relaciones Cliente-Domicilio
            cliente1.setDomicilio(domicilio1);

            domicilio1.setCliente(cliente1);

            // Creación detalles factura
            DetalleFactura detallePuasFactura1 = DetalleFactura.builder()
                    .cantidad(25)
                    .subtotal(750)
                    .build();
            DetalleFactura detalleCultivadoraFactura1 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(2500)
                    .build();

            // Creación Facturas
            Factura factura1 = Factura.builder()
                    .numero(500)
                    .fecha("12-08-2024")
                    .total(3450)
                    .build();

            // Relaciones factura-detalles

            // Detalles factura 1
            detalleCultivadoraFactura1.setFactura(factura1);
            detallePuasFactura1.setFactura(factura1);

            // Factura 1
            factura1.getDetalles().add(detalleCultivadoraFactura1);
            factura1.getDetalles().add(detallePuasFactura1);

            // Relaciones artículo-detalles

            // Cultivadoras
            detalleCultivadoraFactura1.setArticulo(cultivadora);
            cultivadora.getDetalles().add(detalleCultivadoraFactura1);

            // Púas
            detallePuasFactura1.setArticulo(pua);
            pua.getDetalles().add(detallePuasFactura1);

            // Relación factura-cliente

            // Cliente 1
            cliente1.getFacturas().add(factura1);
            factura1.setCliente(cliente1);

            // Persistir facturas
            em.persist(factura1);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo concretar la operación ... ");

        }

        em.close();
        emf.close();

    }
}
