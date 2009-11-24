public abstract class  Persistente implements IPersistente
{
     
  public  void SQLInsertar()
  {
    this.getBroker().insertar(this);
  }
  public  void SQLEliminar()
  {
    
  }
  public  void SQLActualizar()
  {
    
  }
  public  IPersistente SQLLeer()
  {
    
  }    
  public Broker getBroker()
  {
    
  } 
  
}