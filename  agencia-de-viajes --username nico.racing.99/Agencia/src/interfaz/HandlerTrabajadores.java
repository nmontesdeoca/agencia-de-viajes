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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class HandlerTrabajadores extends JPanel implements Observer, ActionListener, ListSelectionListener{
     
     private Sistema sistema;
     private JList listaTrabajadores;
     private JScrollPane scrollListaTrabajadores;
     private JTextField nombre;
     private JTextField apellido;
     private JTextField ci;
     private JTextField numeroTrabajador;
     private JTextField ganancias;
     private JButton agregar;
     private JButton eliminar;
     private JButton modificar;
     private JButton sueldo;
     private JButton comision;
     private JLabel listaTrabajadoresL;
     private JLabel nombreL;
     private JLabel apellidoL;
     private JLabel ciL;
     private JLabel numeroTrabajadorL;
     private JLabel gananciasL;
     private JLabel tipoL;
     private JRadioButton botonSueldo;
     private JRadioButton botonComision;
     private ButtonGroup botonGrupo;
     private JPanel radioPanel;
     private DefaultListModel modeloListaTrabajadores;
     private DefaultListModel modeloListaComision;
     
     public HandlerTrabajadores(Sistema sistemaP) {
          
          super();
          this.setSize(900, 750); 
          this.setLayout(null);
          this.sistema= sistemaP;
          
          modeloListaTrabajadores = new DefaultListModel();
          modeloListaComision = new DefaultListModel();
          
          cargarModelo(modeloListaTrabajadores, sistema.getEmpresa().getListaTrabajadores());
          
          listaTrabajadores = new JList(modeloListaTrabajadores);
          listaTrabajadores.setSize(200,400);
          listaTrabajadores.setLocation(75,85);
          listaTrabajadores.addListSelectionListener(this);
          listaTrabajadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.add(listaTrabajadores);
          
          scrollListaTrabajadores = new JScrollPane();
          scrollListaTrabajadores.setBounds(new Rectangle(75,85,200,400));
          scrollListaTrabajadores.setViewportView(listaTrabajadores);
          this.add(scrollListaTrabajadores);
          
          nombre = new JTextField();
          this.add(nombre);
          nombre.setSize(200,25);
          nombre.setLocation(500,100);
          
          apellido = new JTextField();
          this.add(apellido);
          apellido.setSize(200,25);
          apellido.setLocation(500,165);
          
          ci = new JTextField();
          this.add(ci);
          ci.setSize(200,25);
          ci.setLocation(500,230);
          
          numeroTrabajador = new JTextField();
          this.add(numeroTrabajador);
          numeroTrabajador.setSize(200,25);
          numeroTrabajador.setLocation(500,295);
          
          ganancias = new JTextField();
          this.add(ganancias);
          ganancias.setSize(200,25);
          ganancias.setLocation(500,360);
          
          agregar = new JButton("Agregar");
          this.add(agregar);
          agregar.setSize(120,25);
          agregar.setLocation(325,250);
          agregar.addActionListener(this);
          
          eliminar = new JButton("Eliminar");
          this.add(eliminar);
          eliminar.setSize(120,25);
          eliminar.setLocation(325,300);
          eliminar.addActionListener(this);
          
          modificar = new JButton("Modificar");
          this.add(modificar);
          modificar.setSize(120,25);
          modificar.setLocation(500, 505);
          modificar.addActionListener(this);
          
          listaTrabajadoresL = new JLabel("Lista de Trabajadores");
          this.add(listaTrabajadoresL);
          listaTrabajadoresL.setSize(150,25);
          listaTrabajadoresL.setLocation(75,50);
          
          nombreL = new JLabel("Nombre");
          this.add(nombreL);
          nombreL.setSize(75,25);
          nombreL.setLocation(500,75);
          
          apellidoL = new JLabel("Apellido");
          this.add(apellidoL);  
          apellidoL.setSize(75,25);
          apellidoL.setLocation(500,140);
          
          ciL = new JLabel("Cedula");
          this.add(ciL);
          ciL.setSize(75,25);
          ciL.setLocation(500, 205);
          
          numeroTrabajadorL = new JLabel("Numero del trabajador");
          this.add(numeroTrabajadorL);
          numeroTrabajadorL.setSize(150,25);
          numeroTrabajadorL.setLocation(500,270);
          
          gananciasL = new JLabel("Ganancias");
          this.add(gananciasL);
          gananciasL.setSize(100,25);
          gananciasL.setLocation(500,335);
          
          tipoL = new JLabel("Tipo de Trabajador");
          this.add(tipoL);
          tipoL.setSize(150, 25);
          tipoL.setLocation(500, 400);
          
          botonSueldo = new JRadioButton("Sueldo fijo", true);
          botonSueldo.setLocation(500, 435);
          botonSueldo.setSize(130, 20);
          botonSueldo.addActionListener(this);
          this.add(botonSueldo);
          botonComision = new JRadioButton ("Por Comision", false);
          botonComision.setLocation(500, 460);
          botonComision.setSize(130, 20);
          botonComision.addActionListener(this);
          this.add(botonComision);
          botonGrupo = new ButtonGroup();               
          botonGrupo.add(botonSueldo);
          botonGrupo.add(botonComision);
          
          sistema.getEmpresa().addObserver(this);
     }
     
     public void actionPerformed(ActionEvent evento) {
          
          if(evento.getSource().getClass().getName().equals("javax.swing.JButton")){
               
               if((evento.getSource() == agregar) || (evento.getSource() == modificar) ){
                    
                    String nombreP = nombre.getText();
                    String apellidoP = apellido.getText();
                    
                    if(nombreP.length() > 0 && apellidoP.length() > 0){
                         
                         try{
                              int cedulaP = Integer.parseInt(this.ci.getText());
                              int numeroP = Integer.parseInt(this.numeroTrabajador.getText());
                              double gananciasP = Double.parseDouble(this.ganancias.getText());
                              
                              if(evento.getSource() == agregar){
                                   Trabajador trab = new Trabajador();
                                   
                                   if(botonSueldo.isSelected()){                                                                                          
                                        trab = new TrabajadorBase(nombreP, apellidoP, cedulaP, numeroP, gananciasP, new char[0]);
                                   }
                                   else if(botonComision.isSelected()){
                                        trab = new TrabajadorComision(nombreP, apellidoP, cedulaP, numeroP, gananciasP, new char[0]);                                             
                                   }     
                                   
                                   if(!sistema.getEmpresa().agregarTrabajador(trab)){
                                        JOptionPane.showMessageDialog(null, "ERROR: Ese Trabajador ya existe" , "Trabajador existente", JOptionPane.ERROR_MESSAGE);
                                   }         
                              }
                              
                              else if(evento.getSource() == modificar){
                                   
                                   if (!listaTrabajadores.isSelectionEmpty()){
                                        
                                        Trabajador trab = (Trabajador)listaTrabajadores.getSelectedValue();
                                        
                                        if(botonSueldo.isSelected() && trab instanceof TrabajadorComision){                                                  
                                             sistema.getEmpresa().eliminarTrabajador(trab);     
                                             trab = new TrabajadorBase();
                                             sistema.getEmpresa().agregarTrabajador(trab);
                                        }
                                        else if (botonComision.isSelected() && trab instanceof TrabajadorBase){                                                  
                                             sistema.getEmpresa().eliminarTrabajador(trab);       
                                             trab = new TrabajadorComision();
                                             sistema.getEmpresa().agregarTrabajador(trab);
                                        }                                             
                                        
                                        trab.setNombre(nombreP);
                                        trab.setApellido(apellidoP);
                                        trab.setCi(cedulaP);
                                        trab.setNumTrabajador(numeroP);
                                        trab.setGanancias(gananciasP);
                                   }
                                   else{
                                        JOptionPane.showMessageDialog(null, "No hay trabajador seleccionado" , "Atención", JOptionPane.INFORMATION_MESSAGE);
                                   }
                              }
                         }
                         catch(NumberFormatException e){
                              JOptionPane.showMessageDialog(null, "ERROR: Ingrese un numero válido en los campos numericos" , "ERROR", JOptionPane.ERROR_MESSAGE);
                              this.ci.setText("");
                         }    
                    }
                    
                    else{
                         JOptionPane.showMessageDialog(null, "ERROR: Faltan los datos de nombre o apellido " , "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
               }
               
               else if(evento.getSource() == eliminar){
                    if (!listaTrabajadores.isSelectionEmpty()){
                         int respuesta = JOptionPane.showConfirmDialog(null, " ¿Eliminar este trabajador?", "Confirmación", JOptionPane.WARNING_MESSAGE);
                         if (respuesta == JOptionPane.YES_OPTION){
                              Trabajador trab = (Trabajador)listaTrabajadores.getSelectedValue();
                              sistema.getEmpresa().eliminarTrabajador(trab);
                         }
                    }else{
                         JOptionPane.showMessageDialog(null, "No hay trabajador seleccionado" , "Atención", JOptionPane.INFORMATION_MESSAGE);
                    }
               }
          }                           
     }
     
     private void cambiarEstadoBotones(boolean estaHabilitado){
          
          sueldo.setEnabled(!estaHabilitado);
          comision.setEnabled(estaHabilitado);
     }
     
     public void valueChanged(ListSelectionEvent evento) {
          
          if (!listaTrabajadores.isSelectionEmpty()){
               Trabajador trab = (Trabajador)listaTrabajadores.getSelectedValue();
               nombre.setText(trab.getNombre());
               apellido.setText(trab.getApellido());
               ci.setText(""+trab.getCi());
               numeroTrabajador.setText(""+trab.getNumTrabajador());
               ganancias.setText(""+trab.getGanancias());
               if(trab instanceof TrabajadorComision){
                    botonComision.setSelected(true);
               }
               else if(trab instanceof TrabajadorBase){
                    botonSueldo.setSelected(true);
               }
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
          
          cargarModelo(modeloListaTrabajadores, sistema.getEmpresa().getListaTrabajadores());
          listaTrabajadores.setSelectedIndex(-1);
          listaTrabajadores.setModel(modeloListaTrabajadores);
     }
}