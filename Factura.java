import java.util.LinkedList;

public class Factura {
    private final LinkedList<Producto> productos;  // Lista enlazada de productos
    private static final double IVA = 0.19;  // IVA del 19%

    public Factura() {
        productos = new LinkedList<>();  // Inicializa la lista enlazada
    }

    // Agregar producto a la lista
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Modificar el precio de un producto por código, aumentando un 5%
    public void modificarProducto(String codigo, double precio) {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            double nuevoPrecio = producto.getPrecio() * 1.05;  // Aumenta en 5%
            producto.setPrecio(nuevoPrecio);
        }
    }

    // Eliminar producto por código
    public void eliminarProducto(String codigo) {
        productos.removeIf(producto -> producto.getCodigo().equals(codigo));  // Elimina si coincide el código
    }

    // Buscar producto por código
    public Producto buscarProducto(String codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;  // Retorna el producto si lo encuentra
            }
        }
        return null;  // Si no lo encuentra, retorna null
    }

    // Mostrar todos los productos
    public String mostrarProductos() {
        StringBuilder builder = new StringBuilder();
        for (Producto producto : productos) {
            builder.append(producto.toString()).append("\n");
        }
        return builder.toString();
    }

    // Generar factura (con IVA y precio final)
    public String generarFactura() {
        double precioFinal = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Código - Nombre - Cantidad - Precio - Total\n");
        for (Producto producto : productos) {
            builder.append(producto).append("\n");
            precioFinal += producto.getTotal();
        }
        double iva = precioFinal * IVA;
        double precioConIVA = precioFinal + iva;
        builder.append("\nPrecio Final: $").append(precioFinal);
        builder.append("\nIVA (19%): $").append(iva);
        builder.append("\nTotal con IVA: $").append(precioConIVA);
        return builder.toString();
    }
}

