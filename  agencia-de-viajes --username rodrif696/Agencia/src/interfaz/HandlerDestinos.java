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
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class HandlerDestinos extends JPanel implements Observer, ActionListener, ListSelectionListener{
     
     private Sistema sistema;
     private VentanaGestion vg;
     private JList listaDestinos;
     private JList listaTipos;
     private JScrollPane scrollListaDestinos;
     private JTextField nombre;
     private JTextField localidad;
     private JTextField pais;
     private JButton agregar;
     private JButton eliminar;
     private JButton modificar;
     private JButton paquetes;
     private JButton alojamientos;
     private JLabel listaDestinosL;
     private JLabel nombreL;
     private JLabel localidadL;
     private JLabel paisL;
     private JLabel tipoDestinoL;
     private JComboBox comboTipo;
     private DefaultListModel modeloListaDestinos;
     private DefaultListModel modeloListaTipos;
     
     public HandlerDestinos (VentanaGestion vn, Sistema sistemaP) {
          
          super();
          this.setSize(900, 740);
          this.setLayout(null);
          this.sistema= sistemaP;
          this.vg = vn;
          
          ArrayList <Destino.Tipo> valores = new ArrayList <Destino.Tipo>();
          for(Destino.Tipo tipoAux : Destino.Tipo.values()){
               
               valores.add(tipoAux);                  
          }
          
          modeloListaDestinos = new DefaultListModel();  
          modeloListaTipos = new DefaultListModel();
          cargarModelo(modeloListaDestinos, sistema.getEmpresa().getListaDestinos());
          
          
          listaDestinos = new JList(modeloListaDestinos);
          //listaDestinos.setSize(200,400);
          //listaDestinos.setLocation(75,85);
          listaDestinos.addListSelectionListener(this);
          listaDestinos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.add(listaDestinos);
          
          scrollListaDestinos = new JScrollPane();
          scrollListaDestinos.setBounds(new Rectangle(75,85,200,400));
          scrollListaDestinos.setViewportView(listaDestinos);
          this.add(scrollListaDestinos);
          
          listaTipos = new JList(valores.toArray());
          listaTipos.setSize(200, 90);
          listaTipos.setLocation(500, 305);
          listaTipos.addListSelectionListener(this);
          listaTipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          this.add(listaTipos);
          
          nombre = new JTextField();
          this.add(nombre);
          nombre.setSize(200,25);
          nombre.setLocation(500,100);
          
          localidad = new JTextField();
          this.add(localidad);
          localidad.setSize(200,25);
          localidad.setLocation(500,165);
          
          pais = new JTextField();
          this.add(pais);
          pais.setSize(200,25);
          pais.setLocation(500,230);
          
          paquetes= new JButton("Ir a Paquetes");
          this.add(paquetes);
          paquetes.setSize(130,25);
          paquetes.setLocation(630, 25);
          paquetes.addActionListener(this);
          
          alojamientos= new JButton("Ir a Alojamientos");
          this.add(alojamientos);
          alojamientos.setSize(130,25);
          alojamientos.setLocation(765, 25);
          alojamientos.addActionListener(this);
          
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
          modificar.setLocation(500, 420);
          modificar.addActionListener(this);
          
          listaDestinosL = new JLabel("Lista de Destinos");
          this.add(listaDestinosL);
          listaDestinosL.setSize(150,25);
          listaDestinosL.setLocation(75,50);
          
          nombreL = new JLabel("Nombre");
          this.add(nombreL);
          nombreL.setSize(75,25);
          nombreL.setLocation(500,75);
          
          localidadL = new JLabel("Localidad");
          this.add(localidadL);  
          localidadL.setSize(75,25);
          localidadL.setLocation(500,140);
          
          paisL = new JLabel("Pais");
          this.add(paisL);
          paisL.setSize(75,25);
          paisL.setLocation(500, 205);
          
          tipoDestinoL = new JLabel("Tipo");
          this.add(tipoDestinoL);
          tipoDestinoL.setSize(150,25);
          tipoDestinoL.setLocation(500,270);          
          
          sistema.getEmpresa().addObserver(this);
     }
     
     public void actionPerformed(ActionEvent evento) {
          
          if((evento.getSource() == agregar) || (evento.getSource() == modificar) ){
               
               String nombreP = nombre.getText();
               String localidadP = localidad.getText();
               String paisP = pais.getText();
               Destino.Tipo tipoP = (Destino.Tipo)listaTipos.getSelectedValue();
               
               if(nombreP.length() > 0 && localidadP.length() > 0 && paisP.length() >0){                              
                    
                    if(evento.getSource() == agregar){                                    
                         
                         Destino dest = new Destino(nombreP, localidadP, paisP, tipoP);
                         
                         if(!sistema.getEmpresa().agregarDestino(dest)){
                              JOptionPane.showMessageDialog(null, "   Ese Destino ya existe" , "ERROR", JOptionPane.ERROR_MESSAGE);
                         }        
                    }
                    
                    else if(evento.getSource() == modificar){
                         
                         if (!listaDestinos.isSelectionEmpty()){
                              
                              Destino dest2 = (Destino)listaDestinos.getSelectedValue();                                                                                      
                              dest2.setNombre(nombreP);
                              dest2.setLocalidad(localidadP);
                              dest2.setPais(paisP);    
                              dest2.setTipo(tipoP);
                         }
                         else{
                              JOptionPane.showMessageDialog(null, "   No hay destino seleccionado" , "ERROR", JOptionPane.ERROR_MESSAGE);
                         }
                    }
               }      
               else{
                    JOptionPane.showMessageDialog(null, "   Faltan los datos de nombre, localidad o pais" , "ERROR", JOptionPane.ERROR_MESSAGE);
               }
          }
          
          else if(evento.getSource() == eliminar){
               
               if (!listaDestinos.isSelectionEmpty()){
                    int respuesta = JOptionPane.showConfirmDialog(null, "   ¿Eliminar este destino?", "CONFIRMACION", JOptionPane.WARNING_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                         Destino dest2 = (Destino)listaDestinos.getSelectedValue();
                         sistema.getEmpresa().eliminarDestino(dest2);
                    }
               }else{
                    JOptionPane.showMessageDialog(null, "   No hay destino seleccionado" , "ERROR", JOptionPane.ERROR_MESSAGE);
               }
          }
          
          else if(evento.getSource() == paquetes){
               
               vg.setContentPane(new HandlerPaquetesTuristicos(vg,sistema));
          }
          
          else if(evento.getSource() == alojamientos){
               
               vg.setContentPane(new HandlerAlojamientos(vg,sistema));
          }
          
     }
     
     public void valueChanged(ListSelectionEvent evento) {
          
          if (!listaDestinos.isSelectionEmpty()){
               
               if(evento.getSource() == listaDestinos){
                    
                    Destino dest2 = (Destino)listaDestinos.getSelectedValue();
                    nombre.setText(dest2.getNombre());
                    localidad.setText(dest2.getLocalidad());
                    pais.setText(dest2.getPais());
                    listaTipos.setSelectedValue(dest2.getTipo(), false);
               }
               else if(evento.getSource() == listaTipos){
                    
                    Destino dest2 = (Destino)listaDestinos.getSelectedValue(); 
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
          
          cargarModelo(modeloListaDestinos, sistema.getEmpresa().getListaDestinos());
          listaDestinos.setSelectedIndex(-1);
          listaDestinos.setModel(modeloListaDestinos);
     }
}