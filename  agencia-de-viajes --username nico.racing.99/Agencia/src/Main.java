import interfaz.VentanaGestion;

import java.sql.SQLException;
import java.util.ArrayList;


//import interfaz.*;
import dominio.*;
import dominio.brokers.BrokerEmpresa;

public class Main{
     
     public static void main (String[] args) throws ClassNotFoundException, SQLException{
          
          /*Trabajador tra = new Trabajador();
           Sistema sis = new Sistema(tra);
           VentanaLogin ventana = new VentanaLogin(sis);
           ventana.setVisible(true);*/
          /*VG2 venta = new VG2(new Sistema(new Trabajador()));
          venta.setVisible(true);
          */
          VentanaGestion venta = new VentanaGestion(new Sistema(new Trabajador()));
          venta.setVisible(true);
          //System.out.println("Menu");
    	 
     
           
    	  //Class.forName("com.mysql.jdbc.Driver");
          
          //creamos la conexion
          //Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/agencia?user=root&password=4724");
         // Statement consulta = conexion.createStatement();          
          
         // int reader = consulta.executeUpdate(sql);
          //consulta.close();
         // System.out.println(sql);
    	/* Cliente cli = new Cliente();
    	 cli.setOid(cli.getOid());
    	 cli.setNombre("Nicolas");
    	 cli.setApellido("Montesdeoca");
    	 cli.setAntiguedad(5);
    	 cli.setCedula(41175222);
    	 cli.insertar();
    	 //cli.leer();
    	 Cliente cli2 = new Cliente();
    	 cli2.setOid(35);
    	 cli2.setNombre("jorgessssjajaActualizando");
    	 cli2.actualizar();
*/
    	 //System.out.println(reader);
    	/* BrokerEmpresa a = new BrokerEmpresa();
    	 ArrayList<Alojamiento> al = a.obtenerAlojamientos();
    	 System.out.println(al.size());
    	 Object[] aaa = (Object[]) al.toArray();
    	 for( int i=0;i<aaa.length;i++){
    		 Alojamiento ala = ((Alojamiento)aaa[i]);
    		 System.out.println(ala.toString());
    	 }
     */
     }
     
}