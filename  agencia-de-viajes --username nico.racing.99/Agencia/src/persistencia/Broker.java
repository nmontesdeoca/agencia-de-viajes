package persistencia;

public abstract class Broker{
     
     public void insertar( IPersistente o ){
          String consulta = SQLInsertar( o );
          HandlerPersistencia.GetInstance().ejecutarSentencia(consulta);
     }  
     
     public void actualizar( IPersistente o ){
          String consulta = SQLActualizar( o );
          HandlerPersistencia.GetInstance().ejecutarSentencia(consulta);
     } 
     
     public void eliminar( IPersistente o ){          
          String consulta = SQLEliminar( o );
          HandlerPersistencia.GetInstance().ejecutarSentencia(consulta);
     } 
     
     public IPersistente leer( IPersistente o ){          
          String consulta = SQLLeer( o );
          HandlerPersistencia.GetInstance().ejecutarSentencia(consulta);
          IPersistente ip = readerToObject( o );
          
          return ip;
     }  
     
     public abstract String SQLInsertar( IPersistente o );  
     
     public abstract String SQLActualizar( IPersistente o );    
     
     public abstract String SQLEliminar( IPersistente o );    
     
     public abstract String SQLLeer( IPersistente o );    
     
     public abstract IPersistente readerToObject( IPersistente o );
     
}