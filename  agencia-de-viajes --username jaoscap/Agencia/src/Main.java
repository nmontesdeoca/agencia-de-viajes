import interfaz.*;
import dominio.*;

public class Main{
     
     public static void main (String[] args){
          
          /*Trabajador tra = new Trabajador();
           Sistema sis = new Sistema(tra);
           VentanaLogin ventana = new VentanaLogin(sis);
           ventana.setVisible(true);*/
          /*VG2 venta = new VG2(new Sistema(new Trabajador()));
          venta.setVisible(true);
          */VentanaGestion venta = new VentanaGestion(new Sistema(new Trabajador()));
          venta.setVisible(true);
          //System.out.println("Menu");
          
     }
     
}