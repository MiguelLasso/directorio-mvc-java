package controlador;

import modelo.BaseDeDatos;
import modelo.Producto;
import vista.InventarioVista;

import java.util.List;
import java.util.Optional;

public class ControladorInventario {
    private final BaseDeDatos baseDatos;
    private final InventarioVista vista;

    public ControladorInventario(BaseDeDatos baseDatos, InventarioVista vista) {
        this.baseDatos = baseDatos;
        this.vista = vista;
    }

   public void iniciar() {
    boolean salir = false;
    while (!salir) {
        int opcion = vista.mostrarMenu();
        switch (opcion) {
            case 1:
                agregar();
                break;
            case 2:
                buscar();
                break;
            case 3:
                listar();
                break;
            case 4:
                eliminar();
                break;
            case 5:
                salir = true;
                vista.mostrarMensaje("Saliendo... ¡Hasta luego!");
                break;
            default:
                vista.mostrarMensaje("Opción inválida. Intenta de nuevo.");
                break;
        }
    }
}

    private void agregar() {
        try {
            Producto p = vista.pedirDatosProducto();
            baseDatos.agregarProducto(p);
            vista.mostrarMensaje("Producto agregado correctamente.");
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error al agregar: " + e.getMessage());
        }
    }

    private void buscar() {
        String sku = vista.pedirSku();
        Optional<Producto> p = baseDatos.buscarProductoSku(sku);
        vista.mostrarProducto(p.orElse(null));
    }

    private void listar() {
        List<Producto> lista = baseDatos.buscarTodos();
        vista.mostrarProductos(lista);
    }

    private void eliminar() {
        String sku = vista.pedirSku();
        boolean ok = baseDatos.eliminarProducto(sku);
        if (ok) {
            vista.mostrarMensaje("Producto eliminado.");
        } else {
            vista.mostrarMensaje("No se encontró producto con ese SKU.");
        }
    }
}