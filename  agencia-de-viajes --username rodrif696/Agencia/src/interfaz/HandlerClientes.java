package interfaz;
import dominio.*;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
@SuppressWarnings("serial")
public class HandlerClientes extends JPanel implements Observer, ActionListener, ListSelectionListener{
     
     private Sistema sistema;
     private JList listaClientes;
     private JList listaBuscados;
     private JList listaRealizados;
     private JScrollPane scrollListaRealizados;
     private JScrollPane scrollListaBuscados;
     private JScrollPane scrollListaClientes;
     private JButton agregaListaBuscados;
     private JButton quitaListaBuscados;
     private JTextField nombre;
     private JTextField apellido;
     private JTextField ci;
     private JButton agregar;
     private JButton eliminar;
     private JButton modificar;
     private JButton activos;
     private JButton espera;
     private JButton realizarVenta;
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
     
     public HandlerClientes(Sistema sistemaP) {
          
          super();
          this.setSize(900,740);
          this.setLayout(null);
          this.sistema=sistemaP;
          modeloListaClientes = new DefaultListModel();
          modeloBuscados = new DefaultListModel();
          modeloRealizados = new DefaultListModel();
          modeloListaEnEspera = new DefaultListModel();
          cargarModelo(modeloListaClientes, sistema.getEmpresa().getListaClientes());
          cargarModelo(modeloListaEnEspera, sistema.getEmpresa().getListaDeEspera());
          
          listaClientes = new JList(modeloListaClientes);
          //listaClientes.setSize(200,400);
          //listaClientes.setLocation(75,85);
          listaClientes.addListSelectionListener(this);
          listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.add(listaClientes);
          
          listaRealizados = new JList(modeloRealizados);
          //listaRealizados.setSize(200,100);
          //listaRealizados.setLocation(500,280);
          this.add(listaRealizados);
          
          listaBuscados = new JList(modeloBuscados);
          //listaBuscados.setSize(200,100);
          //listaBuscados.setLocation(500,430);
          listaBuscados.setEnabled(false);
          this.add(listaBuscados);
          
          scrollListaBuscados = new JScrollPane();
          scrollListaBuscados.setBounds(new Rectangle(500,470,200,100));
          scrollListaBuscados.setViewportView(listaBuscados);
          this.add(scrollListaBuscados);
          
          scrollListaRealizados = new JScrollPane();
          scrollListaRealizados.setBounds(new Rectangle(500,320,200,100));
          scrollListaRealizados.setViewportView(listaRealizados);
          this.add(scrollListaRealizados);
          
          scrollListaClientes = new JScrollPane();
          scrollListaClientes.setBounds(new Rectangle(75,85,200,400));
          scrollListaClientes.setViewportView(listaClientes);
          this.add(scrollListaClientes);
          
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
          modificar.setLocation(500,590);
          modificar.addActionListener(this);
          
          activos = new JButton("Ver Activos");
          this.add(activos);
          activos.setSize(100,25);
          activos.setLocation(75,500);
          activos.addActionListener(this);
          activos.setEnabled(false);
          
          espera = new JButton("Ver Espera");
          this.add(espera);
          espera.setSize(100,25);
          espera.setLocation(175,500);
          espera.addActionListener(this);
          
          realizarVenta = new JButton("Realizar venta");
          this.add(realizarVenta);
          realizarVenta.setSize(115,25);
          realizarVenta.setLocation(120,590);
          realizarVenta.addActionListener(this);
          
          agregaListaBuscados = new JButton("+");
          this.add(agregaListaBuscados);
          agregaListaBuscados.setSize(45,25);
          agregaListaBuscados.setLocation(720,470);
          agregaListaBuscados.addActionListener(this);
          
          quitaListaBuscados = new JButton("-");
          this.add(quitaListaBuscados);
          quitaListaBuscados.setSize(45,25);
          quitaListaBuscados.setLocation(720,500);
          quitaListaBuscados.addActionListener(this);
          
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
          apellidoL.setLocation(500,140);
          
          ciL = new JLabel("Cedula");
          this.add(ciL);
          ciL.setSize(75,25);
          ciL.setLocation(500, 205);
          
          buscadosL = new JLabel("Destinos Buscados");
          this.add(buscadosL);
          buscadosL.setSize(200,25);
          buscadosL.setLocation(500,440);
          
          realizadosL = new JLabel("Viajes Realizados");
          this.add(realizadosL);
          realizadosL.setSize(100,25);
          realizadosL.setLocation(500,290);
          
          sistema.getEmpresa().addObserver(this);
     }
     
     public void actionPerformed(ActionEvent evento) {
          
          if(evento.getSource().getClass().getName().equals("javax.swing.JButton")){
               
               if((evento.getSource() == agregar) || (evento.getSource() == modificar) ){
                    
                    String nombreP = nombre.getText(); String apellidoP = apellido.getText();
                    Object[] desBusc = (Object[])listaBuscados.getSelectedValues();
                    ArrayList<Destino> desBus = new ArrayList<Destino>();
                    for(int i=0;i<desBusc.length;i++){
                         desBus.add((Destino)desBusc[i]);
                    }
                    if(nombreP.length() > 0 && apellidoP.length() > 0){
                         
                         try{
                              int cedulaP = Integer.parseInt(this.ci.getText());
                              
                              if(evento.getSource() == agregar){
                                   Cliente cli = new Cliente(nombreP, apellidoP, cedulaP, 0, new ArrayList <Destino>(), desBus);
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
                                        cli.setDestinosBuscados(desBus);
                                   }
                                   else{
                                        JOptionPane.showMessageDialog(null, "No hay cliente seleccionado" , "Atención", JOptionPane.INFORMATION_MESSAGE);
                                   }
                              }
                         }
                         catch(NumberFormatException e){
                              JOptionPane.showMessageDialog(null, "ERROR: Ingrese un número valido en la cédula" , "ERROR", JOptionPane.ERROR_MESSAGE);
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
               else if(evento.getSource()== agregaListaBuscados){
                    
                    new DestinosBuscados();
                    
                    
               }
               else if (evento.getSource()== quitaListaBuscados){
                    if (!listaBuscados.isSelectionEmpty()){
                         int respuesta = JOptionPane.showConfirmDialog(null, "�Eliminar este destino?", "Confirmacion", JOptionPane.WARNING_MESSAGE);
                         if (respuesta == JOptionPane.YES_OPTION){
                              Destino des = (Destino)listaBuscados.getSelectedValue();
                              sistema.getEmpresa().eliminarDestino(des);
                         }
                    }else{
                         JOptionPane.showMessageDialog(null, "No hay destino seleccionado" , "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    }
               }
               else if(evento.getSource() == realizarVenta){
                    if(!listaClientes.isSelectionEmpty()){
                         Cliente c = (Cliente)listaClientes.getSelectedValue();
                         Trabajador t = (Trabajador)sistema.getTrabajadorActual();
                         if(sistema.getEmpresa().ventaViaje(c,t)){
                              JOptionPane.showMessageDialog(null, "Venta exitosa" , "Success!", JOptionPane.INFORMATION_MESSAGE);
                         }
                    }else{
                         JOptionPane.showMessageDialog(null, "No hay cliente seleccionado" , "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    }                        
               }
          }
     }
          
     private void cambiarEstadoBotones(boolean estaHabilitado){
          agregar.setEnabled(estaHabilitado);
          eliminar.setEnabled(estaHabilitado);
          modificar.setEnabled(estaHabilitado);
          activos.setEnabled(!estaHabilitado);
          espera.setEnabled(estaHabilitado);//si está en espera, los demás botones se desactivan
     }
     
     public void valueChanged(ListSelectionEvent evento) {
          if (!listaClientes.isSelectionEmpty()){
               Cliente cli = (Cliente)listaClientes.getSelectedValue();
               nombre.setText(cli.getNombre());
               apellido.setText(cli.getApellido());
               ci.setText(""+cli.getCedula());
               cargarModelo(modeloRealizados, cli.getViajesRealizados());
               cargarModelo(modeloBuscados, cli.getDestinosBuscados());
               int max = cli.getDestinosBuscados().size() - 1;
               listaBuscados.setSelectionInterval(0,max);
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
     
     private class DestinosBuscados extends JDialog implements ActionListener{
                   
          JPanel destinos;
          DefaultListModel modeloDestinos;
          JList listaDestinos;
          JLabel textoDestinos;
          JButton aceptar;
          JButton cancelar;
          JScrollPane scrollListaDestinos;
          
          public DestinosBuscados(){
               
               this.setTitle("Agregar1");
               this.setSize(450,500);
               this.setVisible(true);
               
               destinos=new JPanel();
               destinos.setSize(450,500);
               destinos.setLayout(null);
               this.setContentPane(destinos);
                            
               modeloDestinos=new DefaultListModel();
               cargarModelo(modeloDestinos, sistema.getEmpresa().getListaDestinos());
               
               listaDestinos = new JList(modeloDestinos);
               //listaDestinos.setSize(200,300);
               //listaDestinos.setLocation(100,75);
               destinos.add(listaDestinos);
               //listaDestinos.addListSelectionListener(this);
               listaDestinos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
               
               scrollListaDestinos = new JScrollPane();
               scrollListaDestinos.setBounds(new Rectangle(100,75,200,300));
               scrollListaDestinos.setViewportView(listaDestinos);
               destinos.add(scrollListaDestinos);
               
               textoDestinos = new JLabel("Lista de Destinos");
               destinos.add(textoDestinos);
               textoDestinos.setSize(100,25);
               textoDestinos.setLocation(100,25);
               
               aceptar=new JButton("Aceptar");
               aceptar.setSize(100,25);
               aceptar.setLocation(100,405);
               destinos.add(aceptar);
               aceptar.addActionListener(this);
           
               cancelar=new JButton("Cancelar");
               cancelar.setSize(100,25);
               cancelar.setLocation(230,405);
               destinos.add(cancelar);
               cancelar.addActionListener(this);              
          }
          
          public void actionPerformed (ActionEvent evento){
               if(evento.getSource() == aceptar){
                    if (!listaDestinos.isSelectionEmpty()){
                         Object[] des = (Object[])listaDestinos.getSelectedValues();
                         ArrayList<Destino> dest = new ArrayList<Destino>();
                         for(int i=0; i<des.length; i++){
                              dest.add((Destino)des[i]);
                         }
                         cargarModelo(modeloBuscados,dest);
                         this.dispose();
                         int max = dest.size() - 1;                                                  
                         
                         listaBuscados.setSelectionInterval(0,max);
                    }
                    else{
                         JOptionPane.showMessageDialog(null, "No hay destino seleccionado" , "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
               }else if(evento.getSource() == cancelar){
                    this.dispose();
               }            
          }
     }
}