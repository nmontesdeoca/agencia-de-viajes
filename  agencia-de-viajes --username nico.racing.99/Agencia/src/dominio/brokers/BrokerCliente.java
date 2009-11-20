package dominio.brokers;
import persistencia.*;

public class BrokerCliente extends Broker{
     public String SQLInsertar( IPersistente o ){
          return "";
     } 
     
     public String SQLActualizar( IPersistente o ){
          return "";
     }    
     
     public String SQLEliminar( IPersistente o ){
          return "";
     }   
     
     public String SQLLeer( IPersistente o ){
          return "";
     }    
     
     public IPersistente readerToObject( IPersistente o ){
          return o;
     }
}