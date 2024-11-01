package gt.edu.umg.gestiones;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author fuent
 */
public class Agenda extends javax.swing.JFrame {
private List<EventoModel> listaEventos;
    /**
     * Creates new form Agenda
     */
    public Agenda() {
        initComponents();
        // Inicializa la lista de eventos
        listaEventos = new ArrayList<>();
         // Carga los eventos en el combo box
        cargarEventos();
        
    }
    /**
     * @return void
     * carga los eventos en el combo box
     */
    private void cargarEventos() {
    try {
        URL url = new URL("http://localhost:8080/eventos");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            EventoModel[] eventos = gson.fromJson(new InputStreamReader(conn.getInputStream()), EventoModel[].class);

            listaEventos = new ArrayList<>();
            ComboBoxAgendaEventos.removeAllItems();

            for (EventoModel evento : eventos) {
                listaEventos.add(evento);
                ComboBoxAgendaEventos.addItem(evento.toString());  
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar eventos desde la API.");
    }
}
    /**
     * 
     * @param id_evento 
     * recibe un id (id del evento del combobox)
     * que luego mediante un get lo imprime en la tabla
     */
    private void cargarUsuariosPorEvento(int id_evento) {
    try {
        URL url = new URL("http://localhost:8080/eventos/"+id_evento+"/usuarios");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");
conn.setConnectTimeout(5000); 
conn.setReadTimeout(5000); 

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<UsuarioModel> usuarios = Arrays.asList(
                objectMapper.readValue(conn.getInputStream(), UsuarioModel[].class)
            );

            // Actualizar la tabla con los datos recibidos
            DefaultTableModel model = (DefaultTableModel) TableAgenda.getModel();
            model.setRowCount(0); // Limpiar la tabla

            for (UsuarioModel usuario : usuarios) {
                model.addRow(new Object[]{
                    usuario.getId_persona(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getTelefono(),
                    usuario.getCorreo()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios desde la API.");
        }
    } catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error al cargar eventos desde la API: " + ex.getMessage());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableAgenda = new javax.swing.JTable();
        ComboBoxAgendaEventos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnCargarUsuarios = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(571, 423));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel1.setText("Agenda");

        TableAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Telefono", "Correo"
            }
        ));
        jScrollPane1.setViewportView(TableAgenda);

        ComboBoxAgendaEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxAgendaEventosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setText("Evento:");

        jButton3.setText("Menú");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnCargarUsuarios.setText("Cargar");
        btnCargarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarUsuariosActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar Usuario");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarUsuarios)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ComboBoxAgendaEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(188, 188, 188))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxAgendaEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btnCargarUsuarios)
                    .addComponent(btnEdit))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxAgendaEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxAgendaEventosActionPerformed
        // TODO add your handling code here:
    
    
    }//GEN-LAST:event_ComboBoxAgendaEventosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         Dashboard das = new Dashboard();
            das.setVisible(true);
            das.setLocationRelativeTo(null);
            dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCargarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarUsuariosActionPerformed
        // TODO add your handling code here:
        int index = ComboBoxAgendaEventos.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un evento.");
                return;
            }
            int id_evento = listaEventos.get(index).getIdEvento();
            cargarUsuariosPorEvento(id_evento);
    }//GEN-LAST:event_btnCargarUsuariosActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       CrearPersonas_1 personas = new CrearPersonas_1();
            personas.setVisible(true);
            personas.setLocationRelativeTo(null);
            dispose();
    }//GEN-LAST:event_btnEditActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agenda().setVisible(true);
            }
        });
    }
    
  class EventoModel {
        private int id_evento;
        private String nombreEvento;
        
        public EventoModel() {}
        
        public int getIdEvento() {
            return id_evento;
        }

        public String getNombreEvento() {
            return nombreEvento;
        }
        
        @Override
            public String toString() {
            return nombreEvento; // Esto retorna el nombre del evento
    }
    }
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxAgendaEventos;
    private javax.swing.JTable TableAgenda;
    private javax.swing.JButton btnCargarUsuarios;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}