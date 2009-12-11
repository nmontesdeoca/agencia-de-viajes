package dominio.brokers;
import dominio.PaqueteTuristico;
import dominio.brokers.*;
import persistencia.*;

public class BrokerPaqueteTuristico extends Broker{
     public String SQLInsertar( IPersistente o ){
          PaqueteTuristico paq = (PaqueteTuristico)o;
          String sql = "INSERT INTO paquetes_turisticos"
              + " (id_paquete_turistico,nombre,duracion,precio,codigo)"
              + " VALUES (@1,@2,@3,@4,@5)"; 
          sql.replace("@1",paq.getOid() + "");
          sql.replace("@2",paq.getNombre()); 
          sql.replace("@3",paq.getDuracion() + ""); 
          sql.replace("@4",paq.getPrecio() + ""); 
          sql.replace("@5",paq.getCodigo() + "");  
          
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
          sql.replace("@1",paq.getNombre()); 
          sql.replace("@2",paq.getDuracion() + ""); 
          sql.replace("@3",paq.getPrecio() + ""); 
          sql.replace("@4",paq.getCodigo() + "");   
          sql.replace("@5",paq.getOid() + "");   
        
          return sql;
     }    
     
     public String SQLEliminar( IPersistente o ){
          PaqueteTuristico paq = (PaqueteTuristico)o;
          String sql = "DELETE FROM paquetes_turisticos"
               + " WHERE id_paquete_turistico = @1";
          sql.replace("@1",paq.getOid() + ""); 
          
          return sql;
     }   
     
     public String SQLLeer( IPersistente o ){
          PaqueteTuristico paq = (PaqueteTuristico)o;
          String sql = "SELECT * FROM paquetes_turisticos"
               + " WHERE id_paquete_turistico = @1";          
          sql.replace("@1",paq.getOid() + "");   
          
          return sql;
     }    
     
     /*
             public override void Leer(IPersistente o)
        {
            Copiador accion = (Copiador)o;

            String sql = "SELECT * FROM Copiador WHERE oid=%1";
            sql = sql.Replace("%1", "" + accion.Oid);

            DB.Instancia().EjecutarConsultaSQL(sql);
            if (DB.Instancia().HayMasRegistros())
            {
                Carpeta destino = new Carpeta();
                destino.Oid = (int)(DB.Instancia().LeerRegistro("oiddestino"));

                accion.Destino = destino;
            }
        }
     */
     public IPersistente readerToObject( IPersistente o ){
          
          return o;
     }
}
