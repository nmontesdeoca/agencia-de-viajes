public abstract class Broken
{
  
  private static Broken BROKEN = null;
  
  
  
  public void insertar(IPersistente o)
  {
    String consulta = SQLInsertar(0);
    HandlerPersistencia.GetInstance().ejecutar(consulta);    
  }  
  
  public void eliminar(IPersistente o)
  {
    String consulta = SQLEliminar(0);
    HandlerPersistencia.GetInstance().ejecutar(consulta);    
  }  
  public void actualizar(IPersistente o)
  {
    String consulta = SQLActualizar(0);
    HandlerPersistencia.GetInstance().ejecutar(consulta);  
    
  }  
  public IPersistente leer(IPersistente o)
  {
    IPersistente I = readerToObject(0);
    String consulta = SQLLeer(0);
    HandlerPersistencia.GetInstance().ejecutar(consulta);
  }  
  
  public abstract String SQLInsertar(IPersistente o);
    
  public abstract String SQLEliminar(IPersistente o);
    
  public abstract String SQLActualizar(IPersistente o);
  
  public abstract String SQLLeer(IPersistente o);
  
  
  public abstract IPersidtente readerToObject(IPersistente o);
    
    
}
