/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapifood.vistas;

import java.util.Calendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import rapifood.modelo.Conexion;
import rapifood.modelo.Mesa;
import rapifood.modelo.MesaData;
import rapifood.modelo.ReservaData;
import rapifood.modelo.Reserva;


public class SeleccionarMesa extends javax.swing.JFrame {
    
    private Calendar fechaElegida;
    private ReservaData reservaData;
    private Conexion con;
    private MesaData mesaData;
    private boolean isModificar;
    private int idAModificar;
    
    public void setFechaElegida(Calendar fechaElegida){
        this.fechaElegida = fechaElegida;
    }

    public void setIsModificar(boolean isModificar) {
        this.isModificar = isModificar;
    }

    public void setIdAModificar(int idAModificar) {
        this.idAModificar = idAModificar;
    }
    
    public SeleccionarMesa() {
        initComponents();
        inicializarComponentes();
        this.setLocationRelativeTo(null);
    }
    
    private void inicializarComponentes(){
        con = new Conexion();
        reservaData = new ReservaData(con);
        mesaData = new MesaData(con);
        isModificar = false;
    }
    
    public void inicializar(){
        JButton[] arrBotones = {jbMesa1,jbMesa2,jbMesa3,jbMesa4,jbMesa5,jbMesa6,jbMesa7,jbMesa8,jbMesa9,jbMesa10,jbMesa11,jbMesa12,jbMesa13,jbMesa14,jbMesa15};
        if(isModificar){
            for(Mesa mesa : mesaData.obtenerMesas()){
                if(!mesa.isEstado()){
                    arrBotones[mesa.getId()-1].setEnabled(false);
                    arrBotones[mesa.getId()-1].setIcon(new ImageIcon(getClass().getResource("../../RSC/MesasIconos/MesaS"+(mesa.getId())+".png")));
                }
            }
            Calendar fechaId = Calendar.getInstance();
            fechaId.setTime(reservaData.buscarReserva(idAModificar).getTurno_reserva());
            for(Reserva reserva : reservaData.obtenerReservas()){
                Calendar fechaRecibida = Calendar.getInstance();
                fechaRecibida.setTime(reserva.getTurno_reserva());
                if(fechaId.getTimeInMillis() == fechaElegida.getTimeInMillis()){
                    if(reservaData.buscarReserva(idAModificar).getMesa().getId() == reserva.getMesa().getId()){
                        arrBotones[reserva.getMesa().getId()-1].setEnabled(true);
                        arrBotones[reserva.getMesa().getId()-1].setIcon(new ImageIcon(getClass().getResource("../../RSC/MesasIconos/MesaN"+(reserva.getMesa().getId())+".png")));
                    }
                    else{
                        arrBotones[reserva.getMesa().getId()-1].setEnabled(false);
                        arrBotones[reserva.getMesa().getId()-1].setIcon(new ImageIcon(getClass().getResource("../../RSC/MesasIconos/MesaS"+(reserva.getMesa().getId())+".png")));
                    }
                }
                else{
                    if(fechaRecibida.getTimeInMillis() == fechaElegida.getTimeInMillis()){
                        arrBotones[reserva.getMesa().getId()-1].setEnabled(false);
                        arrBotones[reserva.getMesa().getId()-1].setIcon(new ImageIcon(getClass().getResource("../../RSC/MesasIconos/MesaS"+(reserva.getMesa().getId())+".png")));
                    } 
                }
            }
        }
        else{
            for(Mesa mesa : mesaData.obtenerMesas()){
                if(!mesa.isEstado()){
                    arrBotones[mesa.getId()-1].setEnabled(false);
                    arrBotones[mesa.getId()-1].setIcon(new ImageIcon(getClass().getResource("../../RSC/MesasIconos/MesaS"+(mesa.getId())+".png")));
                }
            }
            for(Reserva reserva : reservaData.obtenerReservas()){
                Calendar fechaRecibida = Calendar.getInstance();
                fechaRecibida.setTime(reserva.getTurno_reserva());
                if(fechaRecibida.getTimeInMillis() == fechaElegida.getTimeInMillis()){
                    arrBotones[reserva.getMesa().getId()-1].setEnabled(false);
                    arrBotones[reserva.getMesa().getId()-1].setIcon(new ImageIcon(getClass().getResource("../../RSC/MesasIconos/MesaS"+(reserva.getMesa().getId())+".png")));
                }
            }  
        }
    }
    
    public void cargarReserva(JButton boton){
        if(isModificar){
            IngresoComensal ingreso = new IngresoComensal();
            ingreso.setFechaElegida(fechaElegida);
            ingreso.setId_mesa(Integer.parseInt(boton.getName()));
            ingreso.inicializar();
            ingreso.setIsModificar(true);
            ingreso.setIdAModificar(idAModificar);
            this.setVisible(false);
            ingreso.setVisible(true);
        }
        else{
            IngresoComensal ingreso = new IngresoComensal();
            ingreso.setFechaElegida(fechaElegida);
            ingreso.setId_mesa(Integer.parseInt(boton.getName()));
            ingreso.inicializar();
            this.setVisible(false);
            ingreso.setVisible(true); 
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbMesa10 = new javax.swing.JButton();
        jbMesa9 = new javax.swing.JButton();
        jbMesa7 = new javax.swing.JButton();
        jbMesa11 = new javax.swing.JButton();
        jbMesa14 = new javax.swing.JButton();
        jbMesa12 = new javax.swing.JButton();
        jbMesa15 = new javax.swing.JButton();
        jbMesa13 = new javax.swing.JButton();
        jbMesa2 = new javax.swing.JButton();
        jbMesa1 = new javax.swing.JButton();
        jbMesa4 = new javax.swing.JButton();
        jbMesa5 = new javax.swing.JButton();
        jbMesa3 = new javax.swing.JButton();
        jbMesa8 = new javax.swing.JButton();
        jbMesa6 = new javax.swing.JButton();
        jbBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Seleccionar Mesa");
        setResizable(false);

        jbMesa10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN10.png"))); // NOI18N
        jbMesa10.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa10.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa10.setName("10"); // NOI18N
        jbMesa10.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa10ActionPerformed(evt);
            }
        });

        jbMesa9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN9.png"))); // NOI18N
        jbMesa9.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa9.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa9.setName("9"); // NOI18N
        jbMesa9.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa9ActionPerformed(evt);
            }
        });

        jbMesa7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN7.png"))); // NOI18N
        jbMesa7.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa7.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa7.setName("7"); // NOI18N
        jbMesa7.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa7ActionPerformed(evt);
            }
        });

        jbMesa11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN11.png"))); // NOI18N
        jbMesa11.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa11.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa11.setName("11"); // NOI18N
        jbMesa11.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa11ActionPerformed(evt);
            }
        });

        jbMesa14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN14.png"))); // NOI18N
        jbMesa14.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa14.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa14.setName("14"); // NOI18N
        jbMesa14.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa14ActionPerformed(evt);
            }
        });

        jbMesa12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN12.png"))); // NOI18N
        jbMesa12.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa12.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa12.setName("12"); // NOI18N
        jbMesa12.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa12ActionPerformed(evt);
            }
        });

        jbMesa15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN15.png"))); // NOI18N
        jbMesa15.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa15.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa15.setName("15"); // NOI18N
        jbMesa15.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa15ActionPerformed(evt);
            }
        });

        jbMesa13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN13.png"))); // NOI18N
        jbMesa13.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa13.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa13.setName("13"); // NOI18N
        jbMesa13.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa13ActionPerformed(evt);
            }
        });

        jbMesa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN2.png"))); // NOI18N
        jbMesa2.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa2.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa2.setName("2"); // NOI18N
        jbMesa2.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa2ActionPerformed(evt);
            }
        });

        jbMesa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN1.png"))); // NOI18N
        jbMesa1.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa1.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa1.setName("1"); // NOI18N
        jbMesa1.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa1ActionPerformed(evt);
            }
        });

        jbMesa4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN4.png"))); // NOI18N
        jbMesa4.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa4.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa4.setName("4"); // NOI18N
        jbMesa4.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa4ActionPerformed(evt);
            }
        });

        jbMesa5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN5.png"))); // NOI18N
        jbMesa5.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa5.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa5.setName("5"); // NOI18N
        jbMesa5.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa5ActionPerformed(evt);
            }
        });

        jbMesa3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN3.png"))); // NOI18N
        jbMesa3.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa3.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa3.setName("3"); // NOI18N
        jbMesa3.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa3ActionPerformed(evt);
            }
        });

        jbMesa8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN8.png"))); // NOI18N
        jbMesa8.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa8.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa8.setName("8"); // NOI18N
        jbMesa8.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa8ActionPerformed(evt);
            }
        });

        jbMesa6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RSC/MesasIconos/MesaN6.png"))); // NOI18N
        jbMesa6.setMaximumSize(new java.awt.Dimension(200, 204));
        jbMesa6.setMinimumSize(new java.awt.Dimension(200, 204));
        jbMesa6.setName("6"); // NOI18N
        jbMesa6.setPreferredSize(new java.awt.Dimension(204, 200));
        jbMesa6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbMesa6ActionPerformed(evt);
            }
        });

        jbBack.setText("Regresar al menu");
        jbBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbMesa6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa9, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa10, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbMesa1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa5, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbMesa11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa12, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa13, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa14, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbMesa15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbBack)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbMesa5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbMesa7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbMesa11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa12, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa13, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMesa15, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jbBack)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbMesa10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa10ActionPerformed
        cargarReserva(jbMesa10);
    }//GEN-LAST:event_jbMesa10ActionPerformed

    private void jbMesa9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa9ActionPerformed
        cargarReserva(jbMesa9);
    }//GEN-LAST:event_jbMesa9ActionPerformed

    private void jbMesa7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa7ActionPerformed
        cargarReserva(jbMesa7);
    }//GEN-LAST:event_jbMesa7ActionPerformed

    private void jbMesa11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa11ActionPerformed
        cargarReserva(jbMesa11);
    }//GEN-LAST:event_jbMesa11ActionPerformed

    private void jbMesa14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa14ActionPerformed
        cargarReserva(jbMesa14);
    }//GEN-LAST:event_jbMesa14ActionPerformed

    private void jbMesa12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa12ActionPerformed
        cargarReserva(jbMesa12);
    }//GEN-LAST:event_jbMesa12ActionPerformed

    private void jbMesa15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa15ActionPerformed
        cargarReserva(jbMesa15);
    }//GEN-LAST:event_jbMesa15ActionPerformed

    private void jbMesa13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa13ActionPerformed
        cargarReserva(jbMesa13);
    }//GEN-LAST:event_jbMesa13ActionPerformed

    private void jbMesa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa2ActionPerformed
        cargarReserva(jbMesa2);
    }//GEN-LAST:event_jbMesa2ActionPerformed

    private void jbMesa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa1ActionPerformed
        cargarReserva(jbMesa1);
    }//GEN-LAST:event_jbMesa1ActionPerformed

    private void jbMesa4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa4ActionPerformed
        cargarReserva(jbMesa4);
    }//GEN-LAST:event_jbMesa4ActionPerformed

    private void jbMesa5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa5ActionPerformed
        cargarReserva(jbMesa5);
    }//GEN-LAST:event_jbMesa5ActionPerformed

    private void jbMesa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa3ActionPerformed
        cargarReserva(jbMesa3);
    }//GEN-LAST:event_jbMesa3ActionPerformed

    private void jbMesa8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa8ActionPerformed
        cargarReserva(jbMesa8);
    }//GEN-LAST:event_jbMesa8ActionPerformed

    private void jbMesa6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbMesa6ActionPerformed
        cargarReserva(jbMesa6);
    }//GEN-LAST:event_jbMesa6ActionPerformed

    private void jbBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBackActionPerformed
        this.setVisible(false);
        new AdministradorLogueado().setVisible(true);
    }//GEN-LAST:event_jbBackActionPerformed

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
            java.util.logging.Logger.getLogger(SeleccionarMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionarMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionarMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionarMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeleccionarMesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbBack;
    private javax.swing.JButton jbMesa1;
    private javax.swing.JButton jbMesa10;
    private javax.swing.JButton jbMesa11;
    private javax.swing.JButton jbMesa12;
    private javax.swing.JButton jbMesa13;
    private javax.swing.JButton jbMesa14;
    private javax.swing.JButton jbMesa15;
    private javax.swing.JButton jbMesa2;
    private javax.swing.JButton jbMesa3;
    private javax.swing.JButton jbMesa4;
    private javax.swing.JButton jbMesa5;
    private javax.swing.JButton jbMesa6;
    private javax.swing.JButton jbMesa7;
    private javax.swing.JButton jbMesa8;
    private javax.swing.JButton jbMesa9;
    // End of variables declaration//GEN-END:variables
}
