public class HandlerPersistencia{
     
     //cargamos driver
     Class.forName("com.mysql.jdbc.Driver");
     
     //creamos la conexion
     Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/dbViajes?user=&password=");
     
     //creamos objeto Statement para hacer consulta
     Statement consulta = conexion.createStatement();
     
     //consulta sql
     String sql = "";
     ResultSet result = consulta.excecuteQuery(sql);
     
     //iterar en el result
     while( result.next() ){
          
          //obtener datos del result, sabiendo la posicion de la columna( ej: 2 )
          result.getString(2);
          
     }
     
     
     
}