package interfaz;

import dominio.*;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dominio.Alojamiento;
import dominio.Destino;
import dominio.PaqueteTuristico;

@SuppressWarnings("serial")
public class HandlerPaquetesTuristicos extends JPanel implements Observer, ActionListener, ListSelectionListener{
     
     private JList listaPaquetes;
     private JList listaDestinosPaquetes;
     private JList alojamientos;
     private JScrollPane scrollListaPaquetes;
     private JScrollPane scrollListaDestinosPaquetes;
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
     private JButton irDestinos;
     private JButton irAlojamientos;
     private JComboBox comboAlojamiento;
     private Sistema sistema;
     private VentanaGestion vg;
     
     
     public HandlerPaquetesTuristicos(VentanaGestion vn, Sistema sistemaP) {
          
          super();
          this.setSize(900,740);
          this.setLayout(null);
          this.sistema = sistemaP;
          this.vg = vn;
          
          modeloListaPaquetes = new DefaultListModel();
          modeloListaDestinosPaquetes = new DefaultListModel();
          modeloAlojamientos = new DefaultListModel();
          cargarModelo(modeloListaPaquetes, sistema.getEmpresa().getListaPaquetes());
          
          listaPaquetes = new JList(modeloListaPaquetes);
          //listaPaquetes.setSize(200,400);
          //listaPaquetes.setLocation(75,85);
          listaPaquetes.addListSelectionListener(this);
          listaPaquetes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.add(listaPaquetes);
          
          scrollListaPaquetes = new JScrollPane();
          scrollListaPaquetes.setBounds(new Rectangle(75,85,200,400));
          scrollListaPaquetes.setViewportView(listaPaquetes);
          this.add(scrollListaPaquetes);
          
          
          listaDestinosPaquetes = new JList(modeloListaDestinosPaquetes);
          //listaDestinosPaquetes.setSize(200,100);
          //listaDestinosPaquetes.setLocation(500,350);
          //listaDestinosPaquetes.addListSelectionListener(this);
          listaDestinosPaquetes.setEnabled(false);
          listaDestinosPaquetes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
          this.add(listaDestinosPaquetes);
          
          scrollListaDestinosPaquetes = new JScrollPane();
          scrollListaDestinosPaquetes.setBounds(new Rectangle(500,365,200,100));
          scrollListaDestinosPaquetes.setViewportView(listaDestinosPaquetes);
          this.add(scrollListaDestinosPaquetes);
          
          /* alojamientos = new JList(modeloAlojamientos);
           alojamientos.setSize(200,25);
           alojamientos.setLocation(500,300);
           alojamientos.addListSelectionListener(this);
           alojamientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
           this.add(alojamientos);
           */
          
          nombre = new JTextField();
          this.add(nombre);
          nombre.setSize(200,25);
          nombre.setLocation(500,100);
          
          precio = new JTextField();
          this.add(precio);
          precio.setSize(200,25);
          precio.setLocation(500,165);
          
          duracion = new JTextField();
          this.add(duracion);
          duracion.setSize(200,25);
          duracion.setLocation(500,230);         
          
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
          modificar.setLocation(500,490);
          modificar.addActionListener(this);
          
          agregarDestino = new JButton("+");
          this.add(agregarDestino);
          agregarDestino.setSize(45,25);
          agregarDestino.setLocation(720,365);
          agregarDestino.addActionListener(this);
          
          irDestinos = new JButton("Ir a Destinos");
          this.add(irDestinos);
          irDestinos.setSize(130,25);
          irDestinos.setLocation(630,25);
          irDestinos.addActionListener(this);
          
          irAlojamientos = new JButton("Ir a Alojamientos");
          this.add(irAlojamientos);
          irAlojamientos.setSize(130,25);
          irAlojamientos.setLocation(765,25);
          irAlojamientos.addActionListener(this);
          
          listaPaquetesL = new JLabel("Lista de Paquetes");
          this.add(listaPaquetesL);
          listaPaquetesL.setSize(140,45);
          listaPaquetesL.setLocation(75,50);
          
          listaDestinosPaquetesL = new JLabel("Lista de Destinos");
          this.add(listaDestinosPaquetesL);
          listaDestinosPaquetesL.setSize(150,25);
          listaDestinosPaquetesL.setLocation(500,340);                            
          
          nombreL = new JLabel("Nombre");
          this.add(nombreL);
          nombreL.setSize(75,25);
          nombreL.setLocation(500,75);
          
          precioL = new JLabel("Precio");
          this.add(precioL);  
          precioL.setSize(75,25);
          precioL.setLocation(500, 140);
          
          duracionL = new JLabel("Duracion (entre 1 y 100 dias)");
          this.add(duracionL);
          duracionL.setSize(200,25);
          duracionL.setLocation(500,205);
          
          ArrayList <Alojamiento> alojamientos1 = sistema.getEmpresa().getListaAlojamientos();                    
          
          
          comboAlojamiento = new JComboBox(alojamientos1.toArray());
          this.add(comboAlojamiento);
          comboAlojamiento.setSize(150, 25);
          comboAlojamiento.setLocation(500, 300);
          //comboAlojamiento.setSelectedIndex(-1);
          comboAlojamiento.addActionListener(this);
          
          alojamientosL = new JLabel("Alojamiento");
          this.add(alojamientosL);
          alojamientosL.setSize(150,25);
          alojamientosL.setLocation(500,275);
          
          sistema.getEmpresa().addObserver(this);
     }
     
     public void actionPerformed(ActionEvent evento) {
          
          if(evento.getSource() == irDestinos){
               vg.setContentPane(new HandlerDestinos(vg,sistema));
          }
          
          if(evento.getSource() == irAlojamientos){
               vg.setContentPane(new HandlerAlojamientos(vg,sistema));
          }
          
          if((evento.getSource() == agregar) || (evento.getSource() == modificar)){
               
               String nombreP = nombre.getText();
               
               if(nombreP.length() > 0){
                    
                    try{
                         int duracionP = Integer.parseInt(this.duracion.getText());
                         double precioP = Double.parseDouble(this.precio.getText());
                         Alojamiento alojaP = (Alojamiento)comboAlojamiento.getSelectedItem();
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
               
               new Destinos();                                  
          }
     }
     
     
     
     
     public void valueChanged(ListSelectionEvent evento) {
          if (!listaPaquetes.isSelectionEmpty()){
               PaqueteTuristico paquete = (PaqueteTuristico)listaPaquetes.getSelectedValue();
               nombre.setText(paquete.getNombre());
               precio.setText(""+paquete.getPrecio());
               duracion.setText(""+paquete.getDuracion());
               // int max = paquete.getDestinos().size();// - 1;
               //listaDestinosPaquetes.setListData(paquete.getDestinos().toArray());              
               //  listaDestinosPaquetes.setSelectionInterval(0,max);
               cargarModelo(modeloListaDestinosPaquetes, paquete.getDestinos());
               comboAlojamiento.setSelectedItem(paquete.getAlojamiento());
               int max = paquete.getDestinos().size() - 1;
               //listaDestinosPaquetes.setListData(dest.toArray());                            
               listaDestinosPaquetes.setSelectionInterval(0,max);
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
     
     private <E> void cargarLista (DefaultListModel modelo, ArrayList<E> datos){
          
          for(Object objeto:modelo.toArray()){
               if (objeto != null){
                    datos.add((E)objeto);
               }
          }    
     }
     
     
     
     public void update(Observable o, Object ar){
          cargarModelo(modeloListaPaquetes, sistema.getEmpresa().getListaPaquetes());
          listaPaquetes.setSelectedIndex(-1);
          listaPaquetes.setModel(modeloListaPaquetes);
     }
     
     private class Destinos extends JDialog implements ActionListener {
          
          JPanel destinos;
          DefaultListModel modeloDestinos;
          JList listaDestinos;
          JLabel textoDestinos;
          JButton cancelar;
          JButton aceptar;
          JScrollPane scrollListaDestinos;
          
          
          public Destinos(){
               
               this.setTitle("Agregar");
               this.setSize(450,500);
               this.setVisible(true);
               
               destinos=new JPanel();
               destinos.setSize(450,500);
               destinos.setLayout(null);
               this.setContentPane(destinos);
               
               modeloDestinos = new DefaultListModel();
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
                         for(int i=0;i<des.length;i++){
                              dest.add((Destino)des[i]);
                         }
                         cargarModelo(modeloListaDestinosPaquetes,dest);
                         int max = dest.size() - 1;
                         //listaDestinosPaquetes.setListData(dest.toArray());                            
                         listaDestinosPaquetes.setSelectionInterval(0,max);
                         this.dispose();
                    }
                    else{
                         JOptionPane.showMessageDialog(null, "No hay destino seleccionado" , "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    }
               }
               else if(evento.getSource() == cancelar){
                    this.dispose();
                    
               }
               
               
          }
     }
}
