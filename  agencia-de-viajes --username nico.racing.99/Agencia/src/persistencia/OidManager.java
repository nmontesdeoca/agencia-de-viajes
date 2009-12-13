package persistencia;

public class OidManager{
     
     public static long obtenerOid(){
          long oid = 0;
          String sql = "SELECT * FROM oid";
          HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
          oid = (Long)HandlerPersistencia.GetInstance().leerRegistro("oid");
          HandlerPersistencia.GetInstance().terminarConsulta();
          oid++;
          String sql2 = "UPDATE oid SET oid = @1";
          sql2 = sql2.replace("@1","" + oid);
          HandlerPersistencia.GetInstance().ejecutarSentencia(sql2);
          return oid;
     }
     
}