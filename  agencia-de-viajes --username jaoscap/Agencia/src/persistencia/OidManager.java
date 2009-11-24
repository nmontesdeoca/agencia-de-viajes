package persistencia;
Pulic Class OidManager
{
  public static Long ObtenerOid()
  {
    Long oid;
    String sql = "Select * From oid";
    oid = HandlerPersistencia.getInstance().ejecutarSentencia(sql);
    return oid;
    
  }  
}