package interfaz;

import dominio.*;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;

public class VentanaGestion extends JFrame implements ActionListener{
     
     private JPanel panelInicio = null;
     private HandlerClientes panelClientes = null;
     private HandlerTrabajadores panelTrabajadores = null;
     private HandlerPaquetesTuristicos panelPaquetesTuristicos = null;
     private HandlerDestinos panelDestinos = null;
     private HandlerAlojamientos panelAlojamientos = null;
     private JMenu menuArchivo = null;
     private JMenu menuOpciones = null;
     private JMenu menuAyuda = null;
     private JMenuBar barra = null;
     private JMenu subMenuGestion = null;     
     private JMenu subMenuViajes = null;
     private Sistema sistema = null;     
     
     public VentanaGestion(Sistema sistemaP){
          
          super();
          this.setTitle("Gestion");
          this.setSize(1024,750);
          //this.setContentPane(getPanelInicio());
          this.setResizable(false);
          this.setJMenuBar(getMenuBarra());
          this.sistema = sistemaP;
          this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          //uso Adapter para no implementar todos los metodos del Listener
          this.addWindowListener(new java.awt.event.WindowAdapter(){
               public void windowClosing(WindowEvent evento){
                    int respuesta = JOptionPane.showConfirmDialog(null, " ¿Desea salir?", "Confirmación", JOptionPane.WARNING_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                         System.exit(0);
                    }
               }
               
          });
     }
     
     /*private JPanel getPanelInicio(){
          if(panelInicio == null){
               panelInicio = new JPanel();
               panelInicio.setSize(1024,750);
               panelInicio.setLayout(null);
               
               
               ImageIcon iconViajes = new ImageIcon(getClass().getResource("imagenes/icono_travel.png"));
               ImageIcon iconTrabajador = new ImageIcon(getClass().getResource("imagenes/icono_trabajador.png"));
               ImageIcon iconCliente = new ImageIcon(getClass().getResource("imagenes/icono_cliente.png"));
               final VentanaGestion vn = this;
               
               JLabel gestionCliente = new JLabel("Gestion Cliente", iconCliente, JLabel.CENTER);
               gestionCliente.setVerticalTextPosition(JLabel.BOTTOM);
               gestionCliente.setHorizontalTextPosition(JLabel.CENTER);
               
               panelInicio.add(gestionCliente);
               gestionCliente.setSize(120,120);
               gestionCliente.setLocation(302,275);
               
               gestionCliente.addMouseListener(new java.awt.event.MouseAdapter(){
                    
                    public void mouseClicked(MouseEvent evento){
                         vn.setContentPane(vn.getPanelClientes());
                    }
               }                                            
                                               
               );
               
               JLabel gestionTrabajador = new JLabel("Gestion Trabajador", iconTrabajador, JLabel.CENTER);          
               gestionTrabajador.setVerticalTextPosition(JLabel.BOTTOM);
               gestionTrabajador.setHorizontalTextPosition(JLabel.CENTER);
               
               
               panelInicio.add(gestionTrabajador);
               gestionTrabajador.setSize(120,120);
               gestionTrabajador.setLocation(452,275);
               gestionTrabajador.addMouseListener(new java.awt.event.MouseAdapter(){
                    
                    public void mouseClicked(MouseEvent evento){
                         vn.setContentPane(vn.getPanelTrabajadores());
                    }
               }                                            
                                                  
               );
               
               JLabel gestionViajes = new JLabel("Gestion Viajes", iconViajes, JLabel.CENTER);
               gestionViajes.setVerticalTextPosition(JLabel.BOTTOM);
               gestionViajes.setHorizontalTextPosition(JLabel.CENTER);
               
               
               panelInicio.add(gestionViajes);
               gestionViajes.setSize(120,120);
               gestionViajes.setLocation(602,275);
               
               gestionViajes.addMouseListener(new java.awt.event.MouseAdapter(){
                    
                    public void mouseClicked(MouseEvent evento){
                         vn.setContentPane(vn.getPanelViajes());
                    }
               }                                            
                                              
               );
               
          }
          return panelInicio;
     }*/
     
     private HandlerClientes getPanelClientes(){
          if(panelClientes == null){
               panelClientes = new HandlerClientes(sistema);
          }
          return panelClientes;
     }
     
     private HandlerTrabajadores getPanelTrabajadores(){
          if(panelTrabajadores == null){
               panelTrabajadores = new HandlerTrabajadores(sistema);    
          }
          return panelTrabajadores;
     }
     
     private HandlerPaquetesTuristicos getPanelPaquetesTuristicos(){
          if(panelPaquetesTuristicos == null){
               panelPaquetesTuristicos = new HandlerPaquetesTuristicos(this,sistema);              
          }
          return panelPaquetesTuristicos;
     }
     
     private HandlerDestinos getPanelDestinos(){
          if(panelDestinos == null){
               panelDestinos = new HandlerDestinos(this,sistema);              
          }
          return panelDestinos;
     }
      
     private HandlerAlojamientos getPanelAlojamientos(){
          if(panelAlojamientos == null){
               panelAlojamientos = 
                    new HandlerAlojamientos(this,sistema);              
          }
          return panelAlojamientos;
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
     
     private JMenu getSubMenuViajes(){
          if(this.subMenuViajes == null){
               subMenuViajes = new JMenu("Viajes");
               poblarSubMenuViajes(subMenuViajes);
          }
          return this.subMenuViajes;
     }
     
     private void poblarSubMenuGestion(JMenu subMenu){
          JMenuItem clientes = new JMenuItem("Clientes");
          JMenuItem trabajadores = new JMenuItem("Trabajadores");
          
          subMenu.add(clientes);
          subMenu.add(trabajadores);
          subMenu.add(getSubMenuViajes());
          
          clientes.addActionListener(this);
          trabajadores.addActionListener(this);
          
     }
     
     private void poblarSubMenuViajes(JMenu subMenu){
          JMenuItem paquetesTuristicos = new JMenuItem("Paquetes Turisticos");
          JMenuItem alojamientos = new JMenuItem("Alojamientos");
          JMenuItem destinos = new JMenuItem("Destinos");
          
          subMenu.add(paquetesTuristicos);
          subMenu.add(alojamientos);
          subMenu.add(destinos);
          
          paquetesTuristicos.addActionListener(this);
          alojamientos.addActionListener(this);
          destinos.addActionListener(this);
          
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
          if(j.getText().equals("Paquetes Turisticos")){
               this.setContentPane(getPanelPaquetesTuristicos());
          }
          if(j.getText().equals("Destinos")){
               this.setContentPane(getPanelDestinos());
          }
          if(j.getText().equals("Alojamientos")){
               this.setContentPane(getPanelAlojamientos());
          }
     }    
}
