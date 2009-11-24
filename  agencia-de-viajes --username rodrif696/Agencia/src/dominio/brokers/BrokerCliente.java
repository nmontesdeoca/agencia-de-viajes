package dominio.brokers;

import persistencia.*;
import dominio.Cliente;

public class BrokerCliente extends Broker{
     
     public String SQLInsertar (IPersistente obj){
          
          Cliente cli = (Cliente)obj;
          
          String sql = "INSERT INTO clientes (id_clientes, nombre, apellido, cedula, antiguedad)"
             + "VALUES (@1, @2, @3, @4, @5)";
               
          sql.replace("@1", "" + cli.getOid());
          sql.replace("@2", cli.getNombre());
          sql.replace("@3", cli.getApellido());
          sql.replace("@4", "" + cli.getCedula());
          sql.replace("@5", "" + cli.getAntiguedad());
          
          return sql;
     }          
     
     public String SQLActualizar (IPersistente obj){
          
          Cliente cli = (Cliente)obj;
          
          String sql = "UPDATE clientes SET id_clientes = @1, nombre= @2, apellido = @3, cedula = @4, antiguedad = @5 WHERE id_clientes= @6";
               
          sql.replace("@1", "" + cli.getOid());
          sql.replace("@2", cli.getNombre());
          sql.replace("@3", cli.getApellido());
          sql.replace("@4", "" + cli.getCedula());
          sql.replace("@5", "" + cli.getAntiguedad());
          sql.replace("@6", "" + cli.getOid());
          
          return sql;
     }
     
     public String SQLEliminar (IPersistente obj){
          
          Cliente cli = (Cliente)obj;
          
          String sql = "DELETE FROM clientes WHERE id_clientes= @1";
               
          sql.replace("@1", "" + cli.getOid());
          
          return sql;
     }
     
     public String SQLLeer (IPersistente obj){
          
          Cliente cli= (Cliente)obj;
          
          String sql = "SELECT id_clientes, nombre, apellido, cedula, antiguedad FROM clientes WHERE id_clientes = @1";
               
          sql.replace("@1", "" + cli.getOid());
          
          return sql;
     }
     
     public IPersistente readerToObject (IPersistente obj){
          
          return obj;
     }
}