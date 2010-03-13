package interfaz;

import dominio.*;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
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
     private DefaultListModel modeloListaTrabajadores;
     
     public HandlerTrabajadores(Sistema sistemaP) {
          
          super();
          this.setSize(900, 740); 
          this.setLayout(null);
          this.sistema= sistemaP;
          
          modeloListaTrabajadores = new DefaultListModel();
          
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
          nombre.setLocation(500,85);
          
          apellido = new JTextField();
          this.add(apellido);
          apellido.setSize(200,25);
          apellido.setLocation(500,150);
          
          ci = new JTextField();
          this.add(ci);
          ci.setSize(200,25);
          ci.setLocation(500,215);
          
          numeroTrabajador = new JTextField();
          this.add(numeroTrabajador);
          numeroTrabajador.setSize(200,25);
          numeroTrabajador.setLocation(500,280);
          
          ganancias = new JTextField();
          this.add(ganancias);
          ganancias.setSize(200,25);
          ganancias.setLocation(500,345);
          
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
          nombreL.setLocation(500,60);
          
          apellidoL = new JLabel("Apellido");
          this.add(apellidoL);  
          apellidoL.setSize(75,25);
          apellidoL.setLocation(500,125);
          
          ciL = new JLabel("Cedula");
          this.add(ciL);
          ciL.setSize(75,25);
          ciL.setLocation(500, 190);
          
          numeroTrabajadorL = new JLabel("Numero del trabajador");
          this.add(numeroTrabajadorL);
          numeroTrabajadorL.setSize(150,25);
          numeroTrabajadorL.setLocation(500,255);
          
          gananciasL = new JLabel("Ganancias");
          this.add(gananciasL);
          gananciasL.setSize(100,25);
          gananciasL.setLocation(500,320);
          
          tipoL = new JLabel("Tipo de Trabajador");
          this.add(tipoL);
          tipoL.setSize(150, 25);
          tipoL.setLocation(500, 385);
          
          botonSueldo = new JRadioButton("Sueldo fijo", true);
          botonSueldo.setLocation(500, 420);
          botonSueldo.setSize(130, 20);
          botonSueldo.addActionListener(this);
          this.add(botonSueldo);
          botonComision = new JRadioButton ("Por Comision", false);
          botonComision.setLocation(500, 445);
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
                                   long oid = trab.getOid();
                                   
                                   if(botonSueldo.isSelected()){                                                                                          
                                        trab = new TrabajadorBase(nombreP, apellidoP, cedulaP, numeroP, gananciasP, new char[0]);
                                        trab.setOid(oid);
                                   }
                                   else if(botonComision.isSelected()){
                                        trab = new TrabajadorComision(nombreP, apellidoP, cedulaP, numeroP, gananciasP, new char[0]);                                             
                                        trab.setOid(oid);
                                   }     
                                   
                                   if(!sistema.getEmpresa().agregarTrabajador(trab)){
                                        JOptionPane.showMessageDialog(null, "ERROR: Ese Trabajador ya existe" , "Trabajador existente", JOptionPane.ERROR_MESSAGE);
                                   }         
                                   sistema.getEmpresa().notificar();
                              }
                              
                              else if(evento.getSource() == modificar){
                                   
                                   if (!listaTrabajadores.isSelectionEmpty()){
                                        
                                        Trabajador trab = (Trabajador)listaTrabajadores.getSelectedValue();
                                        
                                         nombreP = trab.getNombre();
	                                   	 apellidoP = trab.getApellido();
	                                   	 cedulaP = trab.getCi();
	                                   	 numeroP = trab.getNumTrabajador();
	                                   	 gananciasP = trab.getGanancias();
	                                   	 char[] pass = new char[0];
	                                   	 long oid = trab.getOid();
                                        
                                        if(botonSueldo.isSelected() && trab instanceof TrabajadorComision){     
                                        	 
                                            if( sistema.getEmpresa().eliminarTrabajador(trab) ){     
	                                             trab = new TrabajadorBase();
	                                             trab.setNombre(nombreP);
	                                             trab.setApellido(apellidoP);
	                                             trab.setCi(cedulaP);
	                                             trab.setNumTrabajador(numeroP);
	                                             trab.setGanancias(gananciasP);
	                                             trab.setPassword(pass);
	                                             trab.setOid(oid);
	                                             sistema.getEmpresa().agregarTrabajador(trab);
                                            }else
                                            	JOptionPane.showMessageDialog(null, "No se pudo hacer la modificación, intente mas tarde." , "Atencion", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        else if (botonComision.isSelected() && trab instanceof TrabajadorBase){                                                  
                                        	
                                             if( sistema.getEmpresa().eliminarTrabajador(trab) ){     
	                                             trab = new TrabajadorComision();
	                                             trab.setNombre(nombreP);
	                                             trab.setApellido(apellidoP);
	                                             trab.setCi(cedulaP);
	                                             trab.setNumTrabajador(numeroP);
	                                             trab.setGanancias(gananciasP);
	                                             trab.setPassword(pass);
	                                             trab.setOid(oid);
	                                             sistema.getEmpresa().agregarTrabajador(trab);
                                             }else
                                            	 JOptionPane.showMessageDialog(null, "No se pudo hacer la modificación, intente mas tarde." , "Atencion", JOptionPane.INFORMATION_MESSAGE); 
                                        }                                             
                                        /*
                                        trab.setNombre(nombreP);
                                        trab.setApellido(apellidoP);
                                        trab.setCi(cedulaP);
                                        trab.setNumTrabajador(numeroP);
                                        trab.setGanancias(gananciasP);
                                        */
                                   }
                                   else{
                                        JOptionPane.showMessageDialog(null, "No hay trabajador seleccionado" , "Atenci�n", JOptionPane.INFORMATION_MESSAGE);
                                   }
                                   sistema.getEmpresa().notificar();
                              }
                         }
                         catch(NumberFormatException e){
                              JOptionPane.showMessageDialog(null, "ERROR: Ingrese un numero v�lido en los campos numericos" , "ERROR", JOptionPane.ERROR_MESSAGE);
                              this.ci.setText("");
                         }    
                    }
                    
                    else{
                         JOptionPane.showMessageDialog(null, "ERROR: Faltan los datos de nombre o apellido " , "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
               }
               
               else if(evento.getSource() == eliminar){
                    if (!listaTrabajadores.isSelectionEmpty()){
                         int respuesta = JOptionPane.showConfirmDialog(null, " �Eliminar este trabajador?", "Confirmaci�n", JOptionPane.WARNING_MESSAGE);
                         if (respuesta == JOptionPane.YES_OPTION){
                              Trabajador trab = (Trabajador)listaTrabajadores.getSelectedValue();
                              sistema.getEmpresa().eliminarTrabajador(trab);
                              sistema.getEmpresa().notificar();
                         }
                    }else{
                         JOptionPane.showMessageDialog(null, "No hay trabajador seleccionado" , "Atenci�n", JOptionPane.INFORMATION_MESSAGE);
                    }
               }
          }                           
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
