package presentation.interfaces;

import data.CategoriaDAO;
import data.MarcaDAO;
import data.ProductoDAO;
import database.Conexion;
import presentation.FrmMenuPrincipal;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import data.interfaces.CrudProductoInterface;
import java.sql.SQLException;

public class FrmCategoria extends javax.swing.JPanel {

    private String accion;
    private String nombreAnt;
    private Conexion con;

    public FrmCategoria() {
        initComponents();
        InitStyles();
        CargarProductos();
    }

    private void InitStyles() {
        title.putClientProperty("FlatLaf.styleClass", "h1");
        title.setForeground(Color.black);
        txtBuscar.putClientProperty("JTextField.placeholderText", "Ingrese el nombre del producto a buscar.");
    }

    private void CargarProductos() {
        try {
            ProductoDAO dao = new ProductoDAO(); // Cambiado aquí
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            MarcaDAO marcaDAO = new MarcaDAO();
            DefaultTableModel model = (DefaultTableModel) tablaListado.getModel();
            model.setRowCount(0); // Limpiar tabla antes de cargar datos

            dao.listar("").forEach((producto) -> {
                try {
                    String nombreCategoria = categoriaDAO.getNombrePorID(producto.getIdCategoria());
                    String nombreMarca = marcaDAO.getNombrePorID(producto.getIdMarca());
                    model.addRow(new Object[]{
                        producto.getIdProducto(),
                        producto.getNombre(),
                        producto.getStock(),
                        producto.getPrecioCompra(),
                        producto.getPrecioVenta(),
                        producto.getDescripcion(),
                        nombreCategoria,
                        nombreMarca,
                        producto.getFechaUltimaActualizacion(),
                        producto.isActivo()
                    });
                } catch (SQLException e) {
                    // Manejo del error específico para la obtención de nombres
                    System.err.println("Error al obtener nombre de categoría o marca: " + e.getMessage());
                }
            });

            lbl_totalRegistrados.setText("Total Registrados: " + model.getRowCount());
        } catch (SQLException ex) {
            // Manejo del error general en la carga de productos
            System.err.println("Error al cargar productos: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btn_Buscar = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        title1 = new javax.swing.JLabel();
        lbl_totalRegistrados = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(948, 444));
        setPreferredSize(new java.awt.Dimension(948, 444));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setMinimumSize(new java.awt.Dimension(948, 444));
        bg.setPreferredSize(new java.awt.Dimension(948, 444));

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(51, 51, 51));
        title.setText("Categoria");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btn_Buscar.setBackground(new java.awt.Color(255, 102, 0));
        btn_Buscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Buscar.setText("Buscar");
        btn_Buscar.setBorderPainted(false);
        btn_Buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(255, 102, 0));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Borrar");
        deleteButton.setBorderPainted(false);
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(255, 102, 0));
        editButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Editar");
        editButton.setBorderPainted(false);
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(255, 102, 0));
        addButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Nuevo");
        addButton.setBorderPainted(false);
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        tablaListado.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripción", "Categoría", "Marca"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaListado.getTableHeader().setReorderingAllowed(false);
        tablaListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaListadoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaListado);
        if (tablaListado.getColumnModel().getColumnCount() > 0) {
            tablaListado.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaListado.getColumnModel().getColumn(2).setPreferredWidth(40);
            tablaListado.getColumnModel().getColumn(3).setPreferredWidth(30);
            tablaListado.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        title1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        title1.setForeground(new java.awt.Color(51, 51, 51));
        title1.setText("Nombre:");
        title1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lbl_totalRegistrados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_totalRegistrados.setForeground(new java.awt.Color(0, 0, 0));
        lbl_totalRegistrados.setText("Registrados");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/categorizacion.png"))); // NOI18N

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(lbl_totalRegistrados, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_Buscar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
                .addGap(109, 109, 109)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_totalRegistrados, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(deleteButton))
                .addGap(12, 12, 12))
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addComponent(editButton)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaListadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaListadoMousePressed

    }//GEN-LAST:event_tablaListadoMousePressed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        FrmMenuPrincipal.ShowJPanel(new FrmAddProducto());
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        CrudProductoInterface dao = new ProductoDAO();
        DefaultTableModel model = (DefaultTableModel) tablaListado.getModel();
        if (tablaListado.getSelectedRows().length < 1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debes seleccionar uno o más libros a eliminar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i : tablaListado.getSelectedRows()) {
                try {
                    dao.eliminar((int) tablaListado.getValueAt(i, 0));
                    model.removeRow(i);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (tablaListado.getSelectedRow() > -1) {
            try {
                int idProducto = (int) tablaListado.getValueAt(tablaListado.getSelectedRow(), 0);
                CrudProductoInterface dao = new ProductoDAO();
                FrmMenuPrincipal.ShowJPanel(new FrmAddProducto(dao.getProductoById(idProducto)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Debes seleccionar el libro a editar.\n", "AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarActionPerformed
        try {
            CrudProductoInterface dao = new ProductoDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            MarcaDAO marcaDAO = new MarcaDAO();
            DefaultTableModel model = (DefaultTableModel) tablaListado.getModel();
            model.setRowCount(0); // Limpiar la tabla

            dao.listar(txtBuscar.getText()).forEach((u) -> {
                try {
                    String nombreCategoria = categoriaDAO.getNombrePorID(u.getIdCategoria());
                    String nombreMarca = marcaDAO.getNombrePorID(u.getIdMarca());
                    model.addRow(new Object[]{
                        u.getIdProducto(),
                        u.getNombre(),
                        u.getStock(),
                        u.getPrecioCompra(),
                        u.getPrecioVenta(),
                        u.getDescripcion(),
                        nombreCategoria,
                        nombreMarca,
                        u.getFechaUltimaActualizacion(),
                        u.isActivo()
                    });
                } catch (SQLException e) {
                    // Manejar el error en la obtención del nombre de la categoría o marca
                    System.err.println("Error al obtener nombre de categoría o marca: " + e.getMessage());
                }
            });

        } catch (Exception e) {
            // Manejar cualquier otro error
            System.err.println("Error en la búsqueda: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_BuscarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel bg;
    private javax.swing.JButton btn_Buscar;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_totalRegistrados;
    private javax.swing.JTable tablaListado;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
