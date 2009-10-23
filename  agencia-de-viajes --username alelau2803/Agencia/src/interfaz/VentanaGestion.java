package interfaz;

import dominio.*;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class VentanaGestion extends JFrame implements ActionListener{
     
     private JPanel panelInicio = null;
     private HandlerClientes panelClientes = null;
     private HandlerViajes panelViajes = null;
     private JPanel panelTrabajadores = null;
     private JMenu menuArchivo = null;
     private JMenu menuOpciones = null;
     private JMenu menuAyuda = null;
     private JMenuBar barra = null;
     private JMenu subMenuGestion = null;
     private Sistema sistema = null;
     
     
     public VentanaGestion(Sistema sistemaP){
          
          super();
          this.setTitle("Gestion");
          this.setSize(900,750);
          this.setContentPane(getPanelInicio());
          this.setResizable(false);
          this.setJMenuBar(getMenuBarra());
          this.sistema = sistemaP;
          this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          //uso Adapter para no implementar todos los métodos del Listener
          this.addWindowListener(new java.awt.event.WindowAdapter(){
               public void windowClosing(WindowEvent evento){
                    int respuesta = JOptionPane.showConfirmDialog(null, " ¿Desea salir?", "Confirmación", JOptionPane.WARNING_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                         System.exit(0);
                    }
               }
               
          });
     }
     
     private JPanel getPanelInicio(){
          if(panelInicio == null){
               panelInicio = new JPanel();
               panelInicio.setSize(900,750); 
               panelInicio.setLayout(null); 
          }
          return panelInicio;
     }
     
     private HandlerClientes getPanelClientes(){
          if(panelClientes == null){
               panelClientes = new HandlerClientes(sistema);
          }
          return panelClientes;
     }
     
     private JPanel getPanelTrabajadores(){
          if(panelTrabajadores == null){
                panelTrabajadores = new HandlerTrabajadores(sistema);     
          }
          return panelTrabajadores;
     }
     
     private HandlerViajes getPanelViajes(){
          if(panelViajes == null){
               panelViajes = new HandlerViajes();
          }
          return panelViajes;
     }
     
     private JMenu getMenuArchivo(){
          if(this.menuArchivo == null){
               menuArchivo = new JMenu("Archivo");
               menuArchivo.add(getSubMenuGestion());
               JMenuItem salir = new JMenuItem("Salir");
               salir.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed (ActionEvent evento){
                         int respuesta = JOptionPane.showConfirmDialog(null, " ¿Desea salir?", "Confirmación", JOptionPane.WARNING_MESSAGE);
                         if (respuesta == JOptionPane.YES_OPTION){
                              System.exit(0);
                         }
                    }
               });
               menuArchivo.add(salir);     
               
          }
          return this.menuArchivo;
     }
     
     private JMenu getMenuOpciones(){
          if(this.menuOpciones == null){
               menuOpciones = new JMenu("Opciones");
               menuOpciones.add(new JMenuItem("Preferencias"));
               
          }
          return this.menuOpciones;
     }
     
     private JMenu getMenuAyuda(){
          if(this.menuAyuda == null){
               menuAyuda = new JMenu("Ayuda");
               menuAyuda.add(new JMenuItem("Manual"));
               menuAyuda.add(new JMenuItem("Acerca de..."));
               
          }
          return this.menuAyuda;
     }
     
     private JMenu getSubMenuGestion(){
          if(this.subMenuGestion == null){
               subMenuGestion = new JMenu("Gestion");
               poblarSubMenuGestion(subMenuGestion);
          }
          return this.subMenuGestion;
     }
     
     private void poblarSubMenuGestion(JMenu subMenu){
          JMenuItem clientes = new JMenuItem("Clientes");
          JMenuItem trabajadores = new JMenuItem("Trabajadores");
          JMenuItem viajes = new JMenuItem("Viajes");
          
          subMenu.add(clientes);
          subMenu.add(trabajadores);
          subMenu.add(viajes);
          
          clientes.addActionListener(this);
          trabajadores.addActionListener(this);
          viajes.addActionListener(this);
          
     }
     
     private JMenuBar getMenuBarra(){
          if(this.barra == null){
               barra = new JMenuBar();
               barra.add(getMenuArchivo());
               barra.add(getMenuOpciones());
               barra.add(getMenuAyuda());
          }
          return this.barra;
     }
     
     public void actionPerformed(ActionEvent e){
          
          JMenuItem j = (JMenuItem)e.getSource();
          if(j.getText().equals("Clientes")){
               this.setContentPane(getPanelClientes());
          }
          if(j.getText().equals("Trabajadores")){
               this.setContentPane(getPanelTrabajadores());
          }
          if(j.getText().equals("Viajes")){
               this.setContentPane(getPanelViajes()); 
          } 
     }
     
    
