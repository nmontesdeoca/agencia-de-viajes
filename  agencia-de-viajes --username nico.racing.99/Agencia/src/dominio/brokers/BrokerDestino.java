package dominio.brokers;

import dominio.Destino;
import dominio.Destino.Tipo;
import persistencia.*;

public class BrokerDestino extends Broker{
          
     public String SQLInsertar (IPersistente obj){
       Destino des = (Destino) obj;

       String sql = "INSERT INTO destinos(id_destino, nombre, localidad, pais, tipo)" +
       		"VALUES(@1,@2,@3,@4,@5)";
       sql = sql.replace("@1", "" + des.getOid());
       sql = sql.replace("@2", "'" + des.getNombre() + "'");
       sql = sql.replace("@3", "'" + des.getLocalidad() + "'");
       sql = sql.replace("@4", "'" + des.getPais() + "'");
       sql = sql.replace("@5", "'" + des.getTipo() + "'");       
       
       return sql;     
     }          
     
     public String SQLActualizar (IPersistente obj){
       Destino des = (Destino) obj;
       String sql = "UPDATE  destinos " +
       		"SET nombre = @1," +
       		" localidad = @2," +
       		"pais = @3," +
       		"tipo = @4," +
       		"WHERE id_destino = @5";
       sql = sql.replace("@1", "'" + des.getNombre() + "'");
       sql = sql.replace("@2", "'" + des.getLocalidad() + "'");
       sql = sql.replace("@3", "'" + des.getPais() + "'");
       sql = sql.replace("@4", "'" + des.getTipo() + "'");
       sql = sql.replace("@5", des.getOid() + "");
         
       return sql;     
     }
     
     public String SQLEliminar (IPersistente obj){
       Destino des = (Destino) obj;
       
       String sql = "DELETE FROM destinos WHERE id_destino = " + des.getOid();
          
       return sql;
     }
     
     public String SQLLeer (IPersistente obj){
       Destino des = (Destino) obj;
       
       String sql = "SELECT * FROM destinos WHERE id_destino = @1";
       sql = sql.replace("@1", "" + des.getOid());
          
       return sql;
     }
     
     public IPersistente readerToObject (IPersistente obj){
    	 Destino des = (Destino) obj;
         
    	 HandlerPersistencia persist = HandlerPersistencia.GetInstance();
    	 
    	 String nombre = (String) persist.leerRegistro("nombre");
    	 String localidad = (String) persist.leerRegistro("localidad");
    	 String pais = (String) persist.leerRegistro("pais");
    	 String tipoS = (String) persist.leerRegistro("tipo");
    	 Tipo tipo = Tipo.valueOf(tipoS);
    	 
    	 des.setNombre(nombre);
    	 des.setLocalidad(localidad);
    	 des.setPais(pais);
    	 des.setTipo(tipo);    	 
    	 
          return des;
     }
}
