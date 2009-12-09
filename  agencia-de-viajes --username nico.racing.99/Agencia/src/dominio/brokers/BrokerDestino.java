package dominio.brokers;

import dominio.Destino;
import persistencia.*;

public class BrokerDestino extends Broker{
     String sql;
     
     public String SQLInsertar (IPersistente obj){
       Destino des = (Destino) obj;
       
       sql = "Insert Into destinos(id_destino,nombre,localidad,pais,tipo)";
       sql = sql + " values (" + des.getOid()  + ",'" + des.getNombre() + "',' " + des.getLocalidad() + "',' " + des.getPais() + "',' " + des.getTipo() + "' )";
       return sql;     
     }          
     
     public String SQLActualizar (IPersistente obj){
       Destino des = (Destino) obj;
       sql = "Update  destinos set nombre = '" + des.getNombre() + "', localidad = '" + des.getLocalidad() + "', pais = '" + des.getPais() + "' tipo = '" + des.getTipo()+"'";
       sql = sql + " Where id_destino = " + des.getOid(); 
         
       return sql;     
          
          
     }
     
     public String SQLEliminar (IPersistente obj){
       Destino des = (Destino) obj;
       
       sql = "Delete  From destinos Where id_destino = " + des.getOid();
          
       return sql;
     }
     
     public String SQLLeer (IPersistente obj){
       Destino des = (Destino) obj;
       
       sql = "Select * From destinos Where = " + des.getOid();
          
       return sql;
     }
     
     public IPersistente readerToObject (IPersistente obj){
        //Destino des = (Destino) obj;
          
          return obj;
     }
}
