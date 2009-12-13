package dominio.brokers;
import dominio.Alojamiento;
import dominio.Alojamiento.Pension;
import dominio.Alojamiento.Tipo;
import persistencia.*;

public class BrokerAlojamiento extends Broker{
     
     public String SQLInsertar( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "INSERT INTO alojamientos"
               + " (id_alojamiento,nombre,tipo,estrellas,pension)"
               + " VALUES (@1,@2,@3,@4,@5)";          
          sql = sql.replace("@1",aloja.getOid() + "");
          sql = sql.replace("@2","'" + aloja.getNombre() + "'"); 
          sql = sql.replace("@3","'" + aloja.getTipo().toString() + "'"); 
          sql = sql.replace("@4",aloja.getEstrellas() + ""); 
          sql = sql.replace("@5","'" + aloja.getPension().toString() + "'");    
          
          return sql;
     } 
     
     public String SQLActualizar( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "UPDATE alojamientos"
               + " SET nombre = @1,"
               + " tipo = @2,"
               + " estrellas = @3,"
               + " pension = @4"
               + " WHERE id_alojamiento = @5";
          sql = sql.replace("@1","'" + aloja.getNombre() + "'"); 
          sql = sql.replace("@2","'" + aloja.getTipo().toString() + "'"); 
          sql = sql.replace("@3",aloja.getEstrellas() + ""); 
          sql = sql.replace("@4","'" + aloja.getPension().toString() + "'");   
          sql = sql.replace("@5",aloja.getOid() + "");   
        
          return sql;
     }    
     
     public String SQLEliminar( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "DELETE FROM alojamientos"
               + " WHERE id_alojamiento = @1";          
          sql = sql.replace("@1",aloja.getOid() + ""); 
          
          return sql;
     }   
     
     public String SQLLeer( IPersistente o ){
          Alojamiento aloja = (Alojamiento)o;
          String sql = "SELECT * FROM alojamientos"
               + " WHERE id_alojamiento = @1";
          sql = sql.replace("@1",aloja.getOid() + "");
          
          return sql;
     }    
     
     public IPersistente readerToObject( IPersistente o ){
    	 //creo el objeto Alojamiento que se va a retornar
          Alojamiento aloja = (Alojamiento)o;
          
          HandlerPersistencia persist = HandlerPersistencia.GetInstance();
          
          //obtengo los datos de la tabla alojamientos
          String nombre = (String)persist.leerRegistro("nombre");
          Tipo tipo = (Tipo)persist.leerRegistro("tipo");
          Integer estrellas = (Integer)persist.leerRegistro("estrellas");
          Pension pension = (Pension)persist.leerRegistro("pension");
          
          //le cargo los datos al obtejo Alojamiento
          aloja.setNombre(nombre);
          aloja.setTipo(tipo);
          aloja.setEstrellas(estrellas);
          aloja.setPension(pension);
          
          return aloja;
     }

}