package interfaz;
import dominio.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame implements ActionListener{
     
     private JTextField numeroTrabajador = null;
     private JPasswordField password = null;
     private JButton aceptar = null;
     private JButton cancelar = null;
     private JPanel panel = null;
     private Sistema sistema;
     
     public VentanaLogin(Sistema sistemaP){
          
          super();
          this.setTitle("Login");
          this.setSize(300,200);
          this.setContentPane(getPanel());
          this.setResizable(false);
          this.sistema = sistemaP;          
     }
     
     private JPanel getPanel(){
          
          if(panel == null){
               panel = new JPanel();
               panel.setSize(300,200); 
               panel.setLayout(null);   
               panel.add(getNumeroTrabajador(),null);
               panel.add(getPassword(),null);
               panel.add(getAceptar(),null);
               panel.add(getCancelar(),null);
          }
          return panel;
     }
     
     private JTextField getNumeroTrabajador(){
          
          if(this.numeroTrabajador == null){
               numeroTrabajador = new JTextField();
               numeroTrabajador.setSize(190,25);
               numeroTrabajador.setText("Ingrese su numero");
               numeroTrabajador.setLocation(new Point(50,50));
          }
          return this.numeroTrabajador;
     }
     
     private JPasswordField getPassword(){
          
          if(this.password == null){
               password = new JPasswordField();
               password.setSize(190,25);
               password.setText("");   
               password.setLocation(new Point(50,80));
          }
          return this.password;
     }
     
     private JButton getAceptar(){
         
          if(this.aceptar == null){
               aceptar = new JButton("Ingresar");
               aceptar.setSize(90,25);
               aceptar.setLocation(new Point(50,110));
               aceptar.addActionListener(this);
          }
          return aceptar; 
     }
     
     private JButton getCancelar(){
         
          if(this.cancelar == null){
               cancelar = new JButton("Salir");
               cancelar.setSize(90,25);
               cancelar.setLocation(new Point(150,110));
               cancelar.addActionListener(this);
          }
          return cancelar; 
     }
     
     public void actionPerformed(ActionEvent e){
          
          if(e.getSource() == aceptar){
               try{
                    int num = Integer.parseInt(this.getNumeroTrabajador().getText());
                    Trabajador trabajador = new Trabajador();
                    trabajador.setNumTrabajador(num);
                    trabajador.setPassword(this.getPassword().getPassword());
                    if(sistema.validarUsuario(trabajador)){
                         VentanaGestion v = new VentanaGestion(sistema);
                         v.setVisible(true);
                         this.dispose();
                    }else{
                         JOptionPane.showMessageDialog(null, "ERROR: Verifique numero de trabajador o su contrase�a" , "Usuario Invalido", JOptionPane.ERROR_MESSAGE);
                    }
               }catch(Exception exception){
                    JOptionPane.showMessageDialog(null, "ERROR: Ingrese un numero de usuario valido" , "ERROR", JOptionPane.ERROR_MESSAGE);
                    this.getNumeroTrabajador().setText("");
                    this.getPassword().setText("");
               }  
               //System.out.println("Presiona Ingresar");
          }else{
               this.dispose();
          }
     }   
}