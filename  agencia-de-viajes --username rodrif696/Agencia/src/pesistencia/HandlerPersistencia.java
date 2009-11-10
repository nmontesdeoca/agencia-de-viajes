public class HandlerPersistencia{
     
     //cargar driver
     Class.forName("com.mysql.jdbc.Driver");
     
     //crear conexion con la base de datos
     Connection conectar = DriverManager.getConnection("jdbc:mysql:\\localhost\nombreDB?user= x&password = x");
     
     //crear objeto tipo Statement para hacer consultas sql
     Statement consulta = conectar.createStatement();
     
     //hacer consulta y guardarla como ResultSet
     ResultSet respuesta = consulta.executeQuery("Select * From nombreDB Where x = y ");
     
     //iterar en el ResultSet y pedir datos (ej: getString) de una columna x (ej: 2)
     while(respuesta.next()){
          
          respuesta.getString(2);
     }
}