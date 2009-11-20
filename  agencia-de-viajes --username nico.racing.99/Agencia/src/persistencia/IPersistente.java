package persistencia;

public interface IPersistente{

     public void insertar();
     
     public void actualizar();
     
     public void eliminar();
     
     public IPersistente leer();
     
     public Broker getBroker();
     
}