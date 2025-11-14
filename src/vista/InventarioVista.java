package vista;

import modelo.Producto;

import java.util.List;
import java.util.Scanner;

public class InventarioVista {
    private final Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=== MENÚ INVENTARIO ===");
        System.out.println("1) Agregar producto");
        System.out.println("2) Buscar producto por SKU");
        System.out.println("3) Listar todos los productos");
        System.out.println("4) Eliminar producto por SKU");
        System.out.println("5) Salir");
        System.out.print("Elige una opción: ");
        String linea = scanner.nextLine();
        try {
            return Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Producto pedirDatosProducto() {
        System.out.println("\n--- Agregar producto ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("SKU: ");
        String sku = scanner.nextLine().trim();

        int cantidad = pedirEntero("Cantidad: ");
        double precio = pedirDouble("Precio: ");

        return new Producto(nombre, sku, cantidad, precio);
    }

    private int pedirEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Escribe un número entero.");
            }
        }
    }

    private double pedirDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Escribe un número (ej: 12.50).");
            }
        }
    }

    public String pedirSku() {
        System.out.print("Escribe el SKU: ");
        return scanner.nextLine().trim();
    }

    public void mostrarProducto(Producto p) {
    if (p == null) {
        System.out.println("Producto no encontrado.");
    } else {
        System.out.println("-> " + p);
    }
}

public void mostrarProductos(List<Producto> lista) {
    System.out.println("\n--- Lista de productos ---");
    if (lista.isEmpty()) {
        System.out.println("No hay productos registrados.");
        return;
    }
    for (Producto p : lista) {
        System.out.println(p);
    }
}

public void mostrarMensaje(String mensaje) {
    System.out.println(mensaje);
}
}