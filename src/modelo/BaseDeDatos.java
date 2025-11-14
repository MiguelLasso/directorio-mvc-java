package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseDeDatos {
    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        // Evitar duplicados por SKU
        if (buscarProductoSku(p.getSku()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con SKU: " + p.getSku());
        }
        productos.add(p);
    }

    public Optional<Producto> buscarProductoSku(String sku) {
        return productos.stream()
                .filter(p -> p.getSku().equalsIgnoreCase(sku))
                .findFirst();
    }

    public List<Producto> buscarTodos() {
        return new ArrayList<>(productos); // devolver copia para seguridad
    }

    public boolean eliminarProducto(String sku) {
        Optional<Producto> prod = buscarProductoSku(sku);
        if (prod.isPresent()) {
            productos.remove(prod.get());
            return true;
        }
        return false;
    }
}