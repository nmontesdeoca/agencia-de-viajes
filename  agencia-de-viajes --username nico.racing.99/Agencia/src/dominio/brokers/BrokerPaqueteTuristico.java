package dominio.brokers;
import dominio.PaqueteTuristico;
//import dominio.brokers.*;
import persistencia.*;

public class BrokerPaqueteTuristico extends Broker{
     public String SQLInsertar( IPersistente o ){
          PaqueteTuristico paq = (PaqueteTuristico)o;
          String sql = "INSERT INTO paquetes_turisticos"
              + " (id_paquete_turistico,nombre,duracion,precio,codigo)"
              + " VALUES (@1,@2,@3,@4,@5)"; 
          sql = sql.replace("@1",paq.getOid() + "");
          sql = sql.replace("@2","'" + paq.getNombre() + "'"); 
          sql = sql.replace("@3",paq.getDuracion() + ""); 
          sql = sql.replace("@4",paq.getPrecio() + ""); 
          sql = sql.replace("@5",paq.getCodigo() + "");  
          
          return sql;
     } 
     
     public String SQLActualizar( IPersistente o ){
          PaqueteTuristico paq = (PaqueteTuristico)o;
          String sql = "UPDATE paquetes_turisticos"
               + " SET nombre = @1,"
               + " duracion = @2,"
               + " precio = @3,"
               + " codigo = @4"
               + " WHERE id_paquete_turistico = @5";
          sql = sql.replace("@1","'" + paq.getNombre() + "'"); 
          sql = sql.replace("@2",paq.getDuracion() + ""); 
          sql = sql.replace("@3",paq.getPrecio() + ""); 
          sql = sql.replace("@4",paq.getCodigo() + "");   
          sql = sql.replace("@5",paq.getOid() + "");   
        
          return sql;
     }    
     
     public String SQLEliminar( IPersistente o ){
          PaqueteTuristico paq = (PaqueteTuristico)o;
          String sql = "DELETE FROM paquetes_turisticos"
               + " WHERE id_paquete_turistico = @1";
          sql = sql.replace("@1",paq.getOid() + ""); 
          
          return sql;
     }   
     
     public String SQLLeer( IPersistente o ){
          PaqueteTuristico paq = (PaqueteTuristico)o;
          String sql = "SELECT * FROM paquetes_turisticos"
               + " WHERE id_paquete_turistico = @1";          
          sql = sql.replace("@1",paq.getOid() + "");   
          
          return sql;
     }    
     
     public IPersistente readerToObject( IPersistente o ){
    	  PaqueteTuristico paquete = (PaqueteTuristico) o;
    	  
    	  HandlerPersistencia persist = HandlerPersistencia.GetInstance();
    	  
    	  String nombre = (String) persist.leerRegistro("nombre");
    	  Integer duracion = (Integer) persist.leerRegistro("duracion");
    	  Integer precio = (Integer) persist.leerRegistro("precio");
    	  //Integer codigo = (Integer) persist.leerRegistro("codigo");
    	  
    	  paquete.setNombre(nombre);
    	  paquete.setDuracion(duracion);
    	  paquete.setPrecio(precio);
    	  //paquete.setCodigo(codigo);
    	  
          return paquete;
     }
}