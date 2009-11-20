package dominio;
import persistencia.IPersistente;
import persistencia.Broker;

public abstract class Persistente implements IPersistente{

     public void insertar(){
          
          this.getBroker().insertar(this);
               
     }
     
     public void actualizar(){
     
           this.getBroker().actualizar(this);
          
     }
     
     public void eliminar(){
          
          this.getBroker().eliminar(this);
     
     }
     
     public IPersistente leer(){
          
         return this.getBroker().leer(this);
     
     }
     
     public abstract  Broker getBroker();
     
}