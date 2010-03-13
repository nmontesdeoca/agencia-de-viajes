package dominio.brokers;

import java.util.ArrayList;

import persistencia.*;
import dominio.Cliente;
import dominio.Destino;

public class BrokerCliente extends Broker{
     
     public String SQLInsertar (IPersistente obj){
         
          Cliente cli = (Cliente)obj;
         
          String sql = "INSERT INTO clientes (id_cliente, nombre, apellido, cedula, antiguedad)"
             + "VALUES (@1, @2, @3, @4, @5)";
               
          sql = sql.replace("@1", "" + cli.getOid() );
          sql = sql.replace("@2", "'" + cli.getNombre() + "'" );
          sql = sql.replace("@3", "'" + cli.getApellido() + "'" );
          sql = sql.replace("@4", "" + cli.getCedula());
          sql = sql.replace("@5", "" + cli.getAntiguedad());
         
          return sql;
     }          
     
     public String SQLActualizar (IPersistente obj){
         
          Cliente cli = (Cliente)obj;
         
          String sql = "UPDATE clientes " +
          		"SET nombre= @2," +
          		" apellido = @3," +
          		" cedula = @4," +
          		" antiguedad = @5 " +
          		"WHERE id_cliente = @6";
               
          sql = sql.replace("@2", "'" + cli.getNombre() + "'");
          sql = sql.replace("@3", "'" + cli.getApellido() + "'");
          sql = sql.replace("@4", "" + cli.getCedula());
          sql = sql.replace("@5", "" + cli.getAntiguedad());
          sql = sql.replace("@6", "" + cli.getOid());
         
          return sql;
     }
     
     public String SQLEliminar (IPersistente obj){
         
          Cliente cli = (Cliente)obj;
         
          String sql = "DELETE FROM clientes WHERE id_cliente = @1";
               
          sql = sql.replace("@1", "" + cli.getOid());
         
          return sql;
     }
     
     public String SQLLeer (IPersistente obj){
         
          Cliente cli= (Cliente)obj;
         
          String sql = "SELECT * FROM clientes WHERE id_cliente = @1";
               
          sql = sql.replace("@1", "" + cli.getOid());
         
          return sql;
     }
     
     public IPersistente readerToObject (IPersistente obj){
    	 
    	 //creo el objeto Cliente que se va a retornar
         Cliente cliente = (Cliente)obj;
         
         HandlerPersistencia persist = HandlerPersistencia.GetInstance();
         
         //obtengo los datos de la tabla clientes
         String nombre = (String) persist.leerRegistro("nombre");
         String apellido = (String) persist.leerRegistro("apellido");
         Integer cedula = (Integer) persist.leerRegistro("cedula");
         Integer antiguedad = (Integer) persist.leerRegistro("antiguedad");
         
         //le cargo los datos al obtejo Cliente
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setAntiguedad(antiguedad);

        //esto no va aca
        ArrayList<Destino> destinosBuscados = new ArrayList<Destino>();
        ArrayList<Long> listaOids = new ArrayList<Long>();
   	 	String sql = "SELECT * FROM destinos_buscados WHERE id_cliente = @1";
        sql = sql.replace("@1", cliente.getOid() + "");
        HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
        
        while( HandlerPersistencia.GetInstance().hayMasRegistros() ){
        	listaOids.add((Long) HandlerPersistencia.GetInstance().leerRegistro("id_destino"));	
        }
        
        //sql = "SELECT * destinos WHERE id_destino = @1";
        for(Long oid : listaOids){
            //sql = sql.replace("@1", oidDestino + "");
            //HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
        	Destino d = new Destino();
        	d.setOid(oid);
        	d.leer();
        	destinosBuscados.add(d);
        }
        //System.out.println(listaOids);
        cliente.setDestinosBuscados(destinosBuscados);
        
        return cliente;
     }
}

