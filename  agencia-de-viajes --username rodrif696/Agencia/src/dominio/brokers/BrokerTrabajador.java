package dominio.brokers;

import persistencia.*;
import dominio.Trabajador;
import dominio.TrabajadorComision;

public class BrokerTrabajador extends Broker{
     
     public String SQLInsertar (IPersistente obj){
          
          Trabajador tra = (Trabajador)obj;
          String p= "";
          
          for(int i=0; i<tra.getPassword().length; i++){
               
               p= p + "" + tra.getPassword()[i];
          }
          
          String tipo= "TrabajadorBase";
          
          if(tra instanceof TrabajadorComision){
               
               tipo= "TrabajadorComision";
          }
          
          String sql = "INSERT INTO trabajadores (id_trabajador, nombre, apellido, cedula, numero_trabajador, ganancias, password, permisos_administrativos, tipo)"
             + "VALUES (@1, @2, @3, @4, @5, @6, @7, @8, @9)";
               
          sql.replace("@1", "" + tra.getOid());
          sql.replace("@2", tra.getNombre());
          sql.replace("@3", tra.getApellido());
          sql.replace("@4", "" + tra.getCi());
          sql.replace("@5", "" + tra.getNumTrabajador());
          sql.replace("@6", "" + tra.getGanancias());
          sql.replace("@7", p);
          sql.replace("@8", "" + tra.getPermisosAdministrativos());
          sql.replace("@9", tipo);
          
          return sql;
     }          
     
     public String SQLActualizar (IPersistente obj){
          
          Trabajador tra = (Trabajador)obj;
          String p= "";
          
          for(int i=0; i<tra.getPassword().length; i++){
               
               p= p + "" + tra.getPassword()[i];
          }
          
          String tipo= "TrabajadorBase";
          
          if(tra instanceof TrabajadorComision){
               
               tipo= "TrabajadorComision";
          }
          
          String sql= "UPDATE trabajadores SET id_trabajador = @1, nombre= @2, apellido = @3, cedula = @4, numero_trabajador = @5, ganancias = @6, password= @7, permisos_administrativos= @8, tipo= @9 WHERE id_clientes= @0";
               
          sql.replace("@1", "" + tra.getOid());
          sql.replace("@2", tra.getNombre());
          sql.replace("@3", tra.getApellido());
          sql.replace("@4", "" + tra.getCi());
          sql.replace("@5", "" + tra.getNumTrabajador());
          sql.replace("@6", "" + tra.getGanancias());
          sql.replace("@7", p);
          sql.replace("@8", "" + tra.getPermisosAdministrativos());
          sql.replace("@9", tipo);
          sql.replace("@0", "" + tra.getOid());
          
          return sql;
     }
     
     public String SQLEliminar (IPersistente obj){
          
          Trabajador tra= (Trabajador)obj;
          
          String sql = "DELETE FROM trabajadores WHERE id_trabajador= @1";
               
          sql.replace("@1", "" + tra.getOid());
          
          return sql;
     }
     
     public String SQLLeer (IPersistente obj){
          
          Trabajador tra= (Trabajador)obj;
          
          String sql = "SELECT id_trabajadores, nombre, apellido, cedula, numero_trabajador, ganancias, password, permisos_administrativos, tipo FROM clientes WHERE id_trabajadores = @1";
               
          sql.replace("@1", "" + tra.getOid());
          
          return sql;
     }
     
     public IPersistente readerToObject (IPersistente obj){
          
          return obj;
     }
}

