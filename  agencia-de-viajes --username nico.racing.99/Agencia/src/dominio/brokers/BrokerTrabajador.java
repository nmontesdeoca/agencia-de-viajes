package dominio.brokers;

import persistencia.*;
import dominio.Trabajador;
import dominio.TrabajadorBase;
import dominio.TrabajadorComision;

public class BrokerTrabajador extends Broker{
     
     public String SQLInsertar (IPersistente obj){
          
          Trabajador tra = (Trabajador)obj;
          String p= "";
          char[] pass = tra.getPassword();
          
          for(int i=0; i<pass.length; i++){
               
               p = p + "" + pass[i];
          }
          
          String tipo= "TrabajadorBase";
          
          if(tra instanceof TrabajadorComision){
               
               tipo= "TrabajadorComision";
          }
          
          String sql = "INSERT INTO trabajadores (id_trabajador, nombre, apellido, cedula, numero_trabajador, ganancias, password, permisos_administrativos, tipo)"
             + "VALUES (@1, @2, @3, @4, @5, @6, @7, @8, @9)";
               
          sql = sql.replace("@1", "" + tra.getOid());
          sql = sql.replace("@2", "'" + tra.getNombre() + "'");
          sql = sql.replace("@3", "'" + tra.getApellido() + "'");
          sql = sql.replace("@4", "" + tra.getCi());
          sql = sql.replace("@5", "" + tra.getNumTrabajador());
          sql = sql.replace("@6", "" + tra.getGanancias());
          sql = sql.replace("@7", "'" + p + "'");
          sql = sql.replace("@8", "" + tra.getPermisosAdministrativos());
          sql = sql.replace("@9", "'" + tipo + "'");
          
          return sql;
     }          
     
     public String SQLActualizar (IPersistente obj){
          
          Trabajador tra = (Trabajador)obj;
          String p= "";
          char[] pass = tra.getPassword();
          
          for(int i=0; i<pass.length; i++){
               
               p = p + "" + pass[i];
          }
          
          String tipo= "TrabajadorBase";
          
          if(tra instanceof TrabajadorComision){
               
               tipo= "TrabajadorComision";
          }
          
          String sql= "UPDATE trabajadores SET nombre= @2, apellido = @3, cedula = @4, numero_trabajador = @5, ganancias = @6, password= @7, permisos_administrativos= @8, tipo= @9 WHERE id_trabajador= @0";
               
          sql = sql.replace("@2", "'" + tra.getNombre() + "'");
          sql = sql.replace("@3", "'" + tra.getApellido() + "'");
          sql = sql.replace("@4", "" + tra.getCi());
          sql = sql.replace("@5", "" + tra.getNumTrabajador());
          sql = sql.replace("@6", "" + tra.getGanancias());
          sql = sql.replace("@7", "'" + p + "'");
          sql = sql.replace("@8", "" + tra.getPermisosAdministrativos());
          sql = sql.replace("@9", "'" + tipo + "'");
          sql = sql.replace("@0", "" + tra.getOid());
          
          return sql;
     }
     
     public String SQLEliminar (IPersistente obj){
          
          Trabajador tra= (Trabajador)obj;
          
          String sql = "DELETE FROM trabajadores WHERE id_trabajador = @1";
               
          sql = sql.replace("@1", "" + tra.getOid());
          
          return sql;
     }
     
     public String SQLLeer (IPersistente obj){
          
          Trabajador tra= (Trabajador)obj;
          
          String sql = "SELECT * FROM trabajadores WHERE id_trabajador = @1";
               
          sql = sql.replace("@1", "" + tra.getOid());
          
          return sql;
     }
     
     public IPersistente readerToObject (IPersistente obj){
          Trabajador trabajador = (Trabajador) obj;
          
          HandlerPersistencia persist = HandlerPersistencia.GetInstance();
          Long oid = trabajador.getOid();
          String nombre = (String) persist.leerRegistro("nombre");
          String apellido = (String) persist.leerRegistro("apellido");
          Integer cedula = (Integer) persist.leerRegistro("cedula");
          Long numeroTrabajador = (Long) persist.leerRegistro("numero_trabajador");
          Double ganancias = (Double) persist.leerRegistro("ganancias");
          String password = (String) persist.leerRegistro("password");
          char[] pass = new char[ (password.length()) ];
          for( int i=0; i<password.length();i++ ){
        	  pass[i] = password.charAt(i);
          }          
          Boolean permisosAdmin = (Boolean) persist.leerRegistro("permisos_administrativos");
          String tipo = (String) persist.leerRegistro("tipo");
          
          if( tipo.equals( "TrabajadorComision" ) ){
        	  trabajador = new TrabajadorComision();
        	  trabajador.setOid(oid);
        	  trabajador.setNombre(nombre);
              trabajador.setApellido(apellido);
              trabajador.setCi(cedula);
              trabajador.setNumTrabajador(Integer.parseInt(numeroTrabajador + ""));
              trabajador.setGanancias(ganancias);
              trabajador.setPassword(pass);
              trabajador.setPermisosAdministrativos(permisosAdmin);  
          }else if( tipo.equals( "TrabajadorBase" ) ){
        	  trabajador = new TrabajadorBase();
        	  trabajador.setOid(oid);
        	  trabajador.setNombre(nombre);
              trabajador.setApellido(apellido);
              trabajador.setCi(cedula);
              trabajador.setNumTrabajador(Integer.parseInt(numeroTrabajador + ""));
              trabajador.setGanancias(ganancias);
              trabajador.setPassword(pass);
              trabajador.setPermisosAdministrativos(permisosAdmin);
          }          
          
          return trabajador;
     }
}
