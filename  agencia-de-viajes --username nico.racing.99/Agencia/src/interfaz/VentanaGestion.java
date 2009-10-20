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
     private JPanel panelTrabajadores = null;
     private JPanel panelViajes = null;
     private JMenu menuArchivo = null;
     private JMenu menuOpciones = null;
     private JMenu menuAyuda = null;
     private JMenuBar barra = null;
     private JMenu subMenuGestion = null;
     private Sistema sistema = null;
     
     
     public VentanaGestion(Sistema sistemaP){
          
          super();
          this.setTitle("Gestion");
          this.setSize(1024,750);
          this.setContentPane(getPanelInicio());
          this.setResizable(false);
          this.setJMenuBar(getMenuBarra());
          this.sistema = sistemaP;
          this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          //uso Adapter para no implementar todos los mÃ©todos del Listener
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
     }
     
     private HandlerClientes getPanelClientes(){
          if(panelClientes == null){
               panelClientes = new HandlerClientes();
          }
          return panelClientes;
     }
     
     private JPanel getPanelTrabajadores(){
          if(panelTrabajadores == null){
               panelTrabajadores = new JPanel();
               panelTrabajadores.setSize(1024,750);
               panelTrabajadores.setLayout(null);    
          }
          return panelTrabajadores;
     }
     
     private JPanel getPanelViajes(){
          if(panelViajes == null){
               panelViajes = new HandlerViajes();
               //panelViajes = new JPanel();
               //panelViajes.setSize(1024,750);
               //panelViajes.setLayout(null);
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
     
     @SuppressWarnings("serial")
     private class HandlerClientes extends JPanel implements Observer, ActionListener, ListSelectionListener{
          
          
          private JList listaClientes;
          private JList listaBuscados;
          private JList listaRealizados;
          private JTextField nombre;
          private JTextField apellido;
          private JTextField ci;
          private JButton agregar;
          private JButton eliminar;
          private JButton modificar;
          private JButton activos;
          private JButton espera;
          private JLabel listaClientesL;
          private JLabel nombreL;
          private JLabel apellidoL;
          private JLabel ciL;
          private JLabel buscadosL;
          private JLabel realizadosL;
          private DefaultListModel modeloListaClientes;
          private DefaultListModel modeloListaEnEspera;
          private DefaultListModel modeloBuscados;
          private DefaultListModel modeloRealizados;
          
          public HandlerClientes() {
               
               super();
               this.setSize(1024,750);
               this.setLayout(null);
               
               modeloListaClientes = new DefaultListModel();
               modeloBuscados = new DefaultListModel();
               modeloRealizados = new DefaultListModel();
               modeloListaEnEspera = new DefaultListModel();
               cargarModelo(modeloListaClientes, sistema.getEmpresa().getListaClientes());
               cargarModelo(modeloListaEnEspera, sistema.getEmpresa().getListaDeEspera());
               listaClientes = new JList(modeloListaClientes);
               listaClientes.setSize(200,400);
               listaClientes.setLocation(75,85);
               listaClientes.addListSelectionListener(this);
               listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               this.add(listaClientes);
               
               
               listaBuscados = new JList();
               listaBuscados.setSize(200,100);
               listaBuscados.setLocation(500,300);
               this.add(listaBuscados);
               
               listaRealizados = new JList();
               listaRealizados.setSize(200,100);
               listaRealizados.setLocation(500,450);
               this.add(listaRealizados);
               
               nombre = new JTextField();
               this.add(nombre);
               nombre.setSize(200,25);
               nombre.setLocation(500,100);
               
               apellido = new JTextField();
               this.add(apellido);
               apellido.setSize(200,25);
               apellido.setLocation(500,150);
               
               ci = new JTextField();
               this.add(ci);
               ci.setSize(200,25);
               ci.setLocation(500,200);
               
               agregar = new JButton("Agregar");
               this.add(agregar);
               agregar.setSize(120,25);
               agregar.setLocation(300,175);
               agregar.addActionListener(this);
               
               eliminar = new JButton("Eliminar");
               this.add(eliminar);
               eliminar.setSize(120,25);
               eliminar.setLocation(300,275);
               eliminar.addActionListener(this);
               
               modificar = new JButton("Modificar");
               this.add(modificar);
               modificar.setSize(120,25);
               modificar.setLocation(500,415);
               modificar.addActionListener(this);
               
               activos = new JButton("Ver Activos");
               this.add(activos);
               activos.setSize(110,25);
               activos.setLocation(75,500);
               activos.addActionListener(this);
               activos.setEnabled(false);
               
               espera = new JButton("Ver Espera");
               this.add(espera);
               espera.setSize(110,25);
               espera.setLocation(175,500);
               espera.addActionListener(this);
               
               listaClientesL = new JLabel("Lista de Clientes");
               this.add(listaClientesL);
               listaClientesL.setSize(150,25);
               listaClientesL.setLocation(75,50);
               
               nombreL = new JLabel("Nombre");
               this.add(nombreL);
               nombreL.setSize(75,25);
               nombreL.setLocation(500,75);
               
               apellidoL = new JLabel("Apellido");
               this.add(apellidoL);  
               apellidoL.setSize(75,25);
               apellidoL.setLocation(500,125);
               
               ciL = new JLabel("Cedula");
               this.add(ciL);
               ciL.setSize(75,25);
               ciL.setLocation(500,175);
               
               buscadosL = new JLabel("Destinos Buscados");
               this.add(buscadosL);
               buscadosL.setSize(100,25);
               buscadosL.setLocation(500,275);
               
               realizadosL = new JLabel("Viajes Realizados");
               this.add(realizadosL);
               realizadosL.setSize(100,25);
               realizadosL.setLocation(500,250);
               
               sistema.getEmpresa().addObserver(this);
          }
          
          public void actionPerformed(ActionEvent evento) {
               
               if(evento.getSource().getClass().getName().equals("javax.swing.JButton")){
                    
                    if((evento.getSource() == agregar) || (evento.getSource() == modificar) ){
                         
                         String nombreP = nombre.getText(); String apellidoP = apellido.getText();
                         
                         if(nombreP.length() > 0 && apellidoP.length() > 0){
                              
                              try{
                                   int cedulaP = Integer.parseInt(this.ci.getText());
                                   
                                   if(evento.getSource() == agregar){
                                        Cliente cli = new Cliente(nombreP, apellidoP, cedulaP, 0, new ArrayList <Destino>(), new ArrayList <Destino>());
                                        if(!sistema.getEmpresa().agregarCliente(cli)){
                                             JOptionPane.showMessageDialog(null, "ERROR: Ese Cliente ya existe" , "Cliente existente", JOptionPane.ERROR_MESSAGE);
                                        }            
                                        
                                   }
                                   else if(evento.getSource() == modificar){
                                        if (!listaClientes.isSelectionEmpty()){
                                             Cliente cli = (Cliente)listaClientes.getSelectedValue();
                                             cli.setNombre(nombreP);
                                             cli.setApellido(apellidoP);
                                             cli.setCedula(cedulaP);
                                        }
                                        else{
                                             JOptionPane.showMessageDialog(null, "No hay cliente seleccionado" , "Atención", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                   }
                              }
                              catch(NumberFormatException e){
                                   JOptionPane.showMessageDialog(null, "ERROR: Ingrese un numero válido en la cedula" , "ERROR", JOptionPane.ERROR_MESSAGE);
                                   this.ci.setText("");
                              }
                         }else{
                              JOptionPane.showMessageDialog(null, "ERROR: Faltan los datos de nombre o apellido" , "ERROR", JOptionPane.ERROR_MESSAGE);
                         }
                    }
                    else if(evento.getSource() == eliminar){
                         if (!listaClientes.isSelectionEmpty()){
                              int respuesta = JOptionPane.showConfirmDialog(null, " ¿Eliminar este cliente?", "Confirmación", JOptionPane.WARNING_MESSAGE);
                              if (respuesta == JOptionPane.YES_OPTION){
                                   Cliente cli = (Cliente)listaClientes.getSelectedValue();
                                   sistema.getEmpresa().eliminarCliente(cli);
                              }
                         }else{
                              JOptionPane.showMessageDialog(null, "No hay cliente seleccionado" , "Atención", JOptionPane.INFORMATION_MESSAGE);
                         }
                    }
                    else if(evento.getSource() == activos ){
                         cargarModelo(modeloListaClientes, sistema.getEmpresa().getListaClientes());
                         listaClientes.setModel(modeloListaClientes);
                         cambiarEstadoBotones(true);
                    }
                    else if(evento.getSource() == espera ){
                         cargarModelo(modeloListaEnEspera, sistema.getEmpresa().getListaDeEspera());
                         listaClientes.setModel(modeloListaEnEspera);
                         cambiarEstadoBotones(false);
                    }
               }
               
               
          }
          
          private void cambiarEstadoBotones(boolean estaHabilitado){
               agregar.setEnabled(estaHabilitado);
               eliminar.setEnabled(estaHabilitado);
               modificar.setEnabled(estaHabilitado);
               activos.setEnabled(!estaHabilitado);
               espera.setEnabled(estaHabilitado);//si estÃ¡ en espera, los demÃ¡s botones se desactivan
          }
          
          
          
          public void valueChanged(ListSelectionEvent evento) {
               if (!listaClientes.isSelectionEmpty()){
                    Cliente cli = (Cliente)listaClientes.getSelectedValue();
                    nombre.setText(cli.getNombre());
                    apellido.setText(cli.getApellido());
                    ci.setText(""+cli.getCedula());
                    cargarModelo(modeloRealizados, cli.getViajesRealizados());
                    cargarModelo(modeloBuscados, cli.getDestinosBuscados());
               }
               
          }
          
          private <E> void cargarModelo (DefaultListModel modelo, ArrayList<E> datos){
               
               modelo.clear();
               for(E objeto:datos){
                    if (objeto != null){
                         modelo.addElement(objeto);
                    }
               }    
          }
          
          public void update(Observable o, Object ar){
               cargarModelo(modeloListaClientes, sistema.getEmpresa().getListaClientes());
               listaClientes.setSelectedIndex(-1);
               listaClientes.setModel(modeloListaClientes);
          }
     }
     
     @SuppressWarnings("serial")
     private class HandlerViajes extends JPanel implements Observer, ActionListener, ListSelectionListener{
          
          private JList listaPaquetes;
          private JList listaDestinosPaquetes; 
          private JList alojamientos;
          private DefaultListModel modeloListaPaquetes;
          private DefaultListModel modeloListaDestinosPaquetes;
          private DefaultListModel modeloAlojamientos;
          private JTextField nombre;
          private JTextField precio;
          private JTextField duracion;
          private JLabel listaPaquetesL;
          private JLabel listaDestinosPaquetesL;
          private JLabel nombreL;
          private JLabel precioL;
          private JLabel duracionL;
          private JLabel alojamientosL;
          private JButton agregar;
          private JButton eliminar;
          private JButton modificar;
          private JButton agregarDestino;
          private JComboBox comboAlojamiento;
          
          public HandlerViajes() {
               
               super();
               this.setSize(1024,750);
               this.setLayout(null);
               
               modeloListaPaquetes = new DefaultListModel();
               modeloListaDestinosPaquetes = new DefaultListModel();
               modeloAlojamientos = new DefaultListModel();
               cargarModelo(modeloListaPaquetes, sistema.getEmpresa().getListaPaquetes());
               
               listaPaquetes = new JList(modeloListaPaquetes);
               listaPaquetes.setSize(200,400);
               listaPaquetes.setLocation(75,85);
               listaPaquetes.addListSelectionListener(this);
               listaPaquetes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               this.add(listaPaquetes);
               
               
               listaDestinosPaquetes = new JList(modeloListaDestinosPaquetes);
               listaDestinosPaquetes.setSize(200,100);
               listaDestinosPaquetes.setLocation(500,350);
               listaDestinosPaquetes.addListSelectionListener(this);
               listaDestinosPaquetes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
               this.add(listaDestinosPaquetes);
               
               alojamientos = new JList(modeloAlojamientos);
               alojamientos.setSize(200,25);
               alojamientos.setLocation(500,300);
               alojamientos.addListSelectionListener(this);
               alojamientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               this.add(alojamientos);
               
               
               nombre = new JTextField();
               this.add(nombre);
               nombre.setSize(200,25);
               nombre.setLocation(500,100);
               
               precio = new JTextField();
               this.add(precio);
               precio.setSize(200,25);
               precio.setLocation(500,150);
               
               duracion = new JTextField();
               this.add(duracion);
               duracion.setSize(200,25);
               duracion.setLocation(500,200);
               
               
               agregar = new JButton("Agregar");
               this.add(agregar);
               agregar.setSize(120,25);
               agregar.setLocation(300,175);
               agregar.addActionListener(this);
               
               eliminar = new JButton("Eliminar");
               this.add(eliminar);
               eliminar.setSize(120,25);
               eliminar.setLocation(300,275);
               eliminar.addActionListener(this);
               
               modificar = new JButton("Modificar");
               this.add(modificar);
               modificar.setSize(120,25);
               modificar.setLocation(500,455);
               modificar.addActionListener(this);
               
               agregarDestino = new JButton("+");
               this.add(agregarDestino);
               agregarDestino.setSize(45,25);
               agregarDestino.setLocation(720,350);
               agregarDestino.addActionListener(this); 
               
               listaPaquetesL = new JLabel("Lista de Paquetes");
               this.add(listaPaquetesL);
               listaPaquetesL.setSize(150,25);
               listaPaquetesL.setLocation(75,50);
               
               listaDestinosPaquetesL = new JLabel("Lista de Destinos");
               this.add(listaDestinosPaquetesL);
               listaDestinosPaquetesL.setSize(150,25);
               listaDestinosPaquetesL.setLocation(500,325);                             
               
               nombreL = new JLabel("Nombre");
               this.add(nombreL);
               nombreL.setSize(75,25);
               nombreL.setLocation(500,75);
               
               precioL = new JLabel("Precio");
               this.add(precioL);  
               precioL.setSize(75,25);
               precioL.setLocation(500,125);
               
               duracionL = new JLabel("Duracion (entre 1 y 100 dias)");
               this.add(duracionL);
               duracionL.setSize(200,25);
               duracionL.setLocation(500,175);
               
               ArrayList <Alojamiento> alojamientos = sistema.getEmpresa().getListaAlojamientos();                    

               
               JComboBox comboAlojamiento = new JComboBox(alojamientos.toArray());
               this.add(comboAlojamiento);
               comboAlojamiento.setSize(150, 25);
               comboAlojamiento.setLocation(720, 300);
               comboAlojamiento.setSelectedIndex(-1);
               comboAlojamiento.addActionListener(this);
               
               alojamientosL = new JLabel("Alojamiento");
               this.add(alojamientosL);
               alojamientosL.setSize(150,25);
               alojamientosL.setLocation(500,275);
               
               sistema.getEmpresa().addObserver(this);
          }
          
          public void actionPerformed(ActionEvent evento) {
               
               if((evento.getSource() == agregar) || (evento.getSource() == modificar) ){
                    
                    String nombreP = nombre.getText();
                    
                    if(nombreP.length() > 0){
                         
                         try{
                              int duracionP = Integer.parseInt(this.duracion.getText());
                              double precioP = Double.parseDouble(this.precio.getText());
                              Alojamiento alojaP = (Alojamiento)alojamientos.getSelectedValue();
                              Object[] destinoP = listaDestinosPaquetes.getSelectedValues();
                              ArrayList<Destino> destinoPP = new ArrayList<Destino>();
                              for(int i=0; i<destinoP.length; i++){
                                   destinoPP.add((Destino)destinoP[i]);
                              }
                              
                              if(evento.getSource() == agregar){
                                   PaqueteTuristico paq = new PaqueteTuristico(nombreP, destinoPP, duracionP, precioP, alojaP);
                                   sistema.getEmpresa().agregarPaquete(paq);                                  
                              }
                              else if(evento.getSource() == modificar){
                                   if (!listaPaquetes.isSelectionEmpty()){
                                        PaqueteTuristico paquete = (PaqueteTuristico)listaPaquetes.getSelectedValue();
                                        paquete.setNombre(nombreP);
                                        paquete.setDestinos(destinoPP);
                                        paquete.setPrecio(precioP);
                                        paquete.setDuracion(duracionP);                                       
                                        paquete.setAlojamiento(alojaP);
                                   }
                                   else{
                                        JOptionPane.showMessageDialog(null, "No hay paquete seleccionado" , "Atención", JOptionPane.INFORMATION_MESSAGE);
                                   }
                              }
                         }
                         catch(NumberFormatException e){
                              JOptionPane.showMessageDialog(null, "ERROR: Ingrese un numero válido en los campos precio y duracion" , "ERROR", JOptionPane.ERROR_MESSAGE);
                              
                              this.precio.setText("");
                              this.duracion.setText("");
                              
                         }
                    }else{
                         JOptionPane.showMessageDialog(null, "ERROR: Falta el dato nombre" , "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
               }
               else if(evento.getSource() == eliminar){
                    if (!listaPaquetes.isSelectionEmpty()){
                         int respuesta = JOptionPane.showConfirmDialog(null, " ¿Eliminar este paquete?", "Confirmación", JOptionPane.WARNING_MESSAGE);
                         if (respuesta == JOptionPane.YES_OPTION){
                              PaqueteTuristico paquete = (PaqueteTuristico)listaPaquetes.getSelectedValue();
                              sistema.getEmpresa().eliminarPaquete(paquete);
                         }
                    }else{
                         JOptionPane.showMessageDialog(null, "No hay paquete seleccionado" , "Atención", JOptionPane.INFORMATION_MESSAGE);
                    }
               }
               else if(evento.getSource() == agregarDestino){
       
       final JDialog seleccion=new JDialog();
       seleccion.setTitle("Agregar");
       seleccion.setSize(450,500);
       seleccion.setVisible(true);
              
       JPanel destinos=new JPanel();
       destinos.setSize(450,500);
       destinos.setLayout(null);
       seleccion.setContentPane(destinos);
       
       
       DefaultListModel modeloDestinos=new DefaultListModel();
       cargarModelo(modeloDestinos, sistema.getEmpresa().getListaDestinos());
       
       final JList listaDestinos = new JList(modeloDestinos);
       listaDestinos.setSize(200,300);
       listaDestinos.setLocation(100,75);
       destinos.add(listaDestinos);
       listaDestinos.addListSelectionListener(this);
       listaDestinos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
              
       JLabel textoDestinos = new JLabel("Lista de Destinos");
       destinos.add(textoDestinos);
       textoDestinos.setSize(100,25);
       textoDestinos.setLocation(100,25);
       
       JButton aceptar=new JButton("Aceptar");
       aceptar.setSize(100,25);
       aceptar.setLocation(100,405);
       destinos.add(aceptar);
       aceptar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed (ActionEvent evento){
                 if (!listaDestinos.isSelectionEmpty()){
                     Destino des = (Destino)listaDestinos.getSelectedValue();
                     PaqueteTuristico paq = (PaqueteTuristico)listaPaquetes.getSelectedValue();
                     paq.agregarDestino(des);
                 }
                 else{
                      JOptionPane.showMessageDialog(null, "No hay destino seleccionado" , "Atencion", JOptionPane.INFORMATION_MESSAGE);
                 }
                 
                 
            }
       });
       
       JButton cancelar=new JButton("Cancelar");
       cancelar.setSize(100,25);
       cancelar.setLocation(230,405);
       destinos.add(cancelar);
       cancelar.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed (ActionEvent evento){
                 seleccion.dispose();
                 
            }
       });

                    
                    
               }
              

               
               
          }
          
          
          
          
          public void valueChanged(ListSelectionEvent evento) {
               if (!listaPaquetes.isSelectionEmpty()){
                    PaqueteTuristico paquete = (PaqueteTuristico)listaPaquetes.getSelectedValue();
                    nombre.setText(paquete.getNombre());
                    precio.setText(""+paquete.getPrecio());
                    duracion.setText(""+paquete.getDuracion());
                    cargarModelo(modeloListaDestinosPaquetes, paquete.getDestinos());
                    ArrayList<Alojamiento> alojas = new ArrayList<Alojamiento>();
                    alojas.add(paquete.getAlojamiento());
                    cargarModelo(modeloAlojamientos, alojas);
               }
          }
          
          
          
          private <E> void cargarModelo (DefaultListModel modelo, ArrayList<E> datos){
               
               modelo.clear();
               for(E objeto:datos){
                    if (objeto != null){
                         modelo.addElement(objeto);
                    }
               }    
          }
          
          
          
          public void update(Observable o, Object ar){
               cargarModelo(modeloListaPaquetes, sistema.getEmpresa().getListaPaquetes());
               listaPaquetes.setSelectedIndex(-1);
               listaPaquetes.setModel(modeloListaPaquetes);
          }
     }
     
}
