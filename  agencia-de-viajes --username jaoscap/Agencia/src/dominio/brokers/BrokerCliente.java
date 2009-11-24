package dominio.brokers;

import persistencia.*;

public class BrokerCliente extends Broker{
     
     public String SQLInsertar (IPersistente obj){
          
          return "";
     }          
     
     public String SQLActualizar (IPersistente obj){
          
          return "";
     }
     
     public String SQLEliminar (IPersistente obj){
          
          return "";
     }
     
     public String SQLLeer (IPersistente obj){
          
          return "";
     }
     
     public IPersistente readerToObject (IPersistente obj){
          
          return obj;
     }
}