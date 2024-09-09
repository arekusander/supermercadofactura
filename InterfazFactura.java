import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazFactura extends JFrame {
    private final Factura factura;
    private final JTextArea areaTexto;
    private final JTextField campoCodigo;
    private final JTextField campoNombre;
    private final JTextField campoCantidad;
    private final JTextField campoPrecio;
    
    public InterfazFactura() {
        factura = new Factura();

        // Configuración básica de la ventana
        setTitle("Supermercado - Factura");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para agregar productos
        JPanel panelAgregar = new JPanel(new GridLayout(5, 2));
        panelAgregar.add(new JLabel("Código:"));
        campoCodigo = new JTextField();
        panelAgregar.add(campoCodigo);
        
        panelAgregar.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panelAgregar.add(campoNombre);
        
        panelAgregar.add(new JLabel("Cantidad:"));
        campoCantidad = new JTextField();
        panelAgregar.add(campoCantidad);
        
        panelAgregar.add(new JLabel("Precio:"));
        campoPrecio = new JTextField();
        panelAgregar.add(campoPrecio);
        
        JButton botonAgregar = new JButton("Agregar Producto");
        botonAgregar.addActionListener(new ActionListenerImpl());
        panelAgregar.add(botonAgregar);

        JButton botonModificar = new JButton("Modificar Producto");
        botonModificar.addActionListener(new ActionListenerImpl1());
        panelAgregar.add(botonModificar);

        // Área de texto para mostrar productos y factura
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);

        // Panel inferior para acciones adicionales
        JPanel panelInferior = new JPanel(new GridLayout(1, 4));
        
        JButton botonEliminar = new JButton("Eliminar Producto");
        botonEliminar.addActionListener(new ActionListenerImpl2());
        panelInferior.add(botonEliminar);

        JButton botonBuscar = new JButton("Buscar Producto");
        botonBuscar.addActionListener(new ActionListenerImpl3());
        panelInferior.add(botonBuscar);
        
        JButton botonFactura = new JButton("Generar Factura");
        botonFactura.addActionListener(new ActionListenerImpl4());
        panelInferior.add(botonFactura);

        JButton botonLimpiar = new JButton("Limpiar Pantalla");
        botonLimpiar.addActionListener(new ActionListenerImpl5());
        panelInferior.add(botonLimpiar);

        // Agregar los componentes a la ventana
        add(panelAgregar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Métodos para manejar las acciones
    private void agregarProducto() {
        String codigo = campoCodigo.getText();
        String nombre = campoNombre.getText();
        int cantidad = Integer.parseInt(campoCantidad.getText());
        double precio = Double.parseDouble(campoPrecio.getText());
        factura.agregarProducto(new Producto(codigo, nombre, cantidad, precio));
        actualizarAreaTexto();
    }

    private void modificarProducto() {
        String codigo = campoCodigo.getText();
        double precio = Double.parseDouble(campoPrecio.getText());
        int cantidad = Integer.parseInt(campoCantidad.getText()); // Se agrega la modificación de la cantidad
        Producto producto = factura.buscarProducto(codigo);
        if (producto != null) {
            producto.setCantidad(cantidad); // Modificar la cantidad del producto
            factura.modificarProducto(codigo, precio); // Modificar el precio del producto
        }
        actualizarAreaTexto();
    }

    private void eliminarProducto() {
        String codigo = campoCodigo.getText();
        factura.eliminarProducto(codigo);
        actualizarAreaTexto();
    }

    private void buscarProducto() {
        String codigo = campoCodigo.getText();
        Producto producto = factura.buscarProducto(codigo);
        if (producto != null) {
            areaTexto.setText(producto.toString());
        } else {
            areaTexto.setText("Producto no encontrado");
        }
    }

    private void generarFactura() {
        areaTexto.setText(factura.generarFactura());
    }

    // Método para limpiar el área de texto
    private void limpiarPantalla() {
        areaTexto.setText("");
    }

    private void actualizarAreaTexto() {
        areaTexto.setText(factura.mostrarProductos());
    }

    public static void main(String[] args) {
        InterfazFactura interfazfactura = new InterfazFactura();
    }

    private class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            agregarProducto();
        }
    }

    private class ActionListenerImpl1 implements ActionListener {

        public ActionListenerImpl1() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            modificarProducto();
        }
    }

    private class ActionListenerImpl2 implements ActionListener {

        public ActionListenerImpl2() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            eliminarProducto();
        }
    }

    private class ActionListenerImpl3 implements ActionListener {

        public ActionListenerImpl3() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buscarProducto();
        }
    }

    private class ActionListenerImpl4 implements ActionListener {

        public ActionListenerImpl4() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            generarFactura();
        }
    }

    private class ActionListenerImpl5 implements ActionListener {

        public ActionListenerImpl5() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            limpiarPantalla();
        }
    }
}
