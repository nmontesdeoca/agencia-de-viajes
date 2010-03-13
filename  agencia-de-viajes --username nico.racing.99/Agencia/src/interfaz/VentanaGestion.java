package interfaz;

import dominio.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


@SuppressWarnings("serial")
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
          this.setTitle("Gestion de Viajes");
          this.setSize(1024,750);
          this.setContentPane(getPanelInicio());
          this.setResizable(true);
          this.setJMenuBar(getMenuBarra());
          this.sistema = sistemaP;
          this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          //uso Adapter para no implementar todos los métodos del Listener
          this.addWindowListener(new java.awt.event.WindowAdapter(){
               public void windowClosing(WindowEvent evento){
                    int respuesta = JOptionPane.showConfirmDialog(null, " �Desea salir?", "Confirmación", JOptionPane.WARNING_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                         System.exit(0);
                    }
               }
               
          });
          BufferedImage image = null;
          try {
        	  image = ImageIO.read(getClass().getResource("imagenes/icono_travel_win.png"));
          } catch (IOException e) {
              e.printStackTrace();
          }
          this.setIconImage(image);
     }
     
     private JPanel getPanelInicio(){
          if(panelInicio == null){
               panelInicio = new JPanel();
               panelInicio.setSize(1024,750);
               panelInicio.setLayout(null);               
               
               ImageIcon iconTrabajador = new ImageIcon(getClass().getResource("imagenes/icono_trabajador.PNG"));
               ImageIcon iconCliente = new ImageIcon(getClass().getResource("imagenes/icono_cliente.PNG"));
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
               
              /*
               *   JLabel gestionViajes = new JLabel("Gestion Viajes", iconViajes, JLabel.CENTER);
               
                gestionViajes.setVerticalTextPosition(JLabel.BOTTOM);
                gestionViajes.setHorizontalTextPosition(JLabel.CENTER);
                
                
                panelInicio.add(gestionViajes);
                gestionViajes.setSize(120,120);
                gestionViajes.setLocation(602,275);
                
                gestionViajes.addMouseListener(new java.awt.event.MouseAdapter(){
                
                public void mouseClicked(MouseEvent evento){
               // vn.setContentPane(vn.getPanelViajes());
                }
                }                                            
                
                );*/
               
          }
          return panelInicio;
     }
     
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
                         int respuesta = JOptionPane.showConfirmDialog(null, " �Desea salir?", "Confirmaci�n", JOptionPane.WARNING_MESSAGE);
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
        	  JMenuItem manual = new JMenuItem("Manual");
        	  JMenuItem acercaDe = new JMenuItem("Acerca de...");
               menuAyuda = new JMenu("Ayuda");
               menuAyuda.add(manual);
               menuAyuda.add(acercaDe);
               
               manual.addActionListener(this);
               acercaDe.addActionListener(this);
               
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
          if(j.getText().equals("Acerca de...")){
        	  new AcercaDe();
          }
     }
     
     private class AcercaDe extends JDialog implements ActionListener{
         
         JPanel acerca;
         JLabel tituloL;
         JLabel programadoresL;
         JLabel programador1L;
         JLabel programador2L;
         JLabel programador3L;
         JLabel programador4L;         
         JLabel versionL;
         JLabel directorL;
         JButton cerrar;
         Font titulo;
         Font texto;
         
         public AcercaDe(){
              
              this.setTitle("Acerca De...");
              this.setSize(400,300);
              this.setVisible(true);
              
              acerca = new JPanel();
              acerca.setSize(450,500);
              acerca.setLayout(null);
              this.setContentPane(acerca);
                          
              titulo = new Font( "titulo", 4, 20 );
              tituloL = new JLabel("Agencia de Viajes");
              tituloL.setFont(titulo);
              tituloL.setSize(200,25);
              tituloL.setLocation(100,10);
              acerca.add(tituloL);
              
              texto = new Font( "texto", 2, 12 );
              versionL = new JLabel("Version: 1.0(2009)");
              versionL.setFont(texto);
              versionL.setSize(200,25);
              versionL.setLocation(200,40);
              acerca.add(versionL);
              
              directorL = new JLabel("Director: Gastón Nola");
              directorL.setFont(texto);
              directorL.setSize(200,25);
              directorL.setLocation(200,65);
              acerca.add(directorL);
              
              programadoresL = new JLabel("Programadores: ");
              programadoresL.setFont(texto);
              programadoresL.setSize(500,75);
              programadoresL.setLocation(200,80);
              acerca.add(programadoresL);
              
              programador1L = new JLabel("Nicolas Montesdeoca");
              programador1L.setFont(texto);
              programador1L.setSize(500,100);
              programador1L.setLocation(200,150);
              acerca.add(programador1L);
              
              programador2L = new JLabel("Rodrigo Fernández");
              programador2L.setFont(texto);
              programador2L.setSize(500,100);
              programador2L.setLocation(200,175);
              acerca.add(programador2L);
              
              programador3L = new JLabel("Javier Capobianco");
              programador3L.setFont(texto);
              programador3L.setSize(500,100);
              programador3L.setLocation(200,125);
              acerca.add(programador3L);
              
              programador4L = new JLabel("Alejandro Aguirre");
              programador4L.setFont(texto);
              programador4L.setSize(500,100);
              programador4L.setLocation(200,100);
              acerca.add(programador4L);
              
              BufferedImage image = null;
              try {
            	  image = ImageIO.read(getClass().getResource("imagenes/icono_travel_win.png"));
              } catch (IOException e) {
                  e.printStackTrace();
              }
              this.setIconImage(image);

              
              ImageIcon iconAcerca = new ImageIcon(getClass().getResource("imagenes/icono_travel.png"));
              
              JLabel acercaL = new JLabel("", iconAcerca, JLabel.CENTER);
              acercaL.setSize(90,90);
              acercaL.setLocation(50,50);
              acercaL.setVerticalTextPosition(JLabel.BOTTOM);
              acercaL.setHorizontalTextPosition(JLabel.CENTER);
              acerca.add(acercaL);
              
              
              cerrar = new JButton("Cerrar");
              cerrar.setSize(100,25);
              cerrar.setLocation(50,180);
              acerca.add(cerrar);
              cerrar.addActionListener(this);
         }

		public void actionPerformed(ActionEvent e) {
			this.dispose();
			
		}
    }
     
}
