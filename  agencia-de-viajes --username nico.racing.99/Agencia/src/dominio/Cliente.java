package dominio;
import dominio.brokers.*;
import persistencia.*;
import java.util.ArrayList;

public class Cliente extends Persistente{
     
     private String nombre;
     private String apellido;
     private int cedula;
     private int antiguedad;
     private ArrayList <Destino> viajesRealizados;
     private ArrayList <Destino> destinosBuscados;
     private long oid;
     
     public long getOid(){
          return this.oid;
     }
     
     public void setOid(long o){
    	 this.oid = o;
     }
     
     public void setNombre(String nombreX){
          this.nombre= nombreX;
          
     }
     public String getNombre(){
          return this.nombre; 
     }
     public void setApellido(String apellidoX){
          this.apellido= apellidoX;
          
     }
     public String getApellido(){
          return this.apellido;
     }
     public void setCedula(int cedulaX){
          if(cedulaX>0){
               this.cedula= cedulaX;
          }
          else{
               this.cedula=5;
          } 
          
     }
     public int getCedula(){
          return this.cedula;
     }
     public void setAntiguedad(int antiguedadX){
          this.antiguedad= antiguedadX;
          
     }
     public int getAntiguedad(){
          return this.antiguedad;
     }
     public void setViajesRealizados(ArrayList <Destino> viajes){
          this.viajesRealizados= viajes;
          
     }
     public ArrayList <Destino> getViajesRealizados(){
          return this.viajesRealizados;
     }
     public void setDestinosBuscados(ArrayList <Destino> destinos){
          this.destinosBuscados = destinos;
          
     }
     public ArrayList <Destino> getDestinosBuscados(){
          return this.destinosBuscados;
     }
     
     public Cliente(){
          this.setNombre ("sin definir");
          this.setApellido ("sin definir");
          this.setCedula (0);
          this.setAntiguedad (0);
          this.setViajesRealizados (new ArrayList <Destino>());
          this.setDestinosBuscados (new ArrayList <Destino>());
          this.setOid(OidManager.obtenerOid());
     }
     
     public Cliente(String nombre, String apellido, int cedula, int antiguedad, ArrayList <Destino> realizados, ArrayList <Destino> buscados){
          this.setNombre(nombre);
          this.setApellido (apellido);
          this.setCedula (cedula);
          this.setAntiguedad (antiguedad);
          this.setViajesRealizados (realizados);
          this.setDestinosBuscados (buscados);
          this.setOid(OidManager.obtenerOid());
     }
     
     public Cliente (Cliente actual){
          this.setNombre (actual.getNombre());
          this.setApellido (actual.getApellido());
          this.setCedula (actual.getCedula());
          this.setAntiguedad (actual.getAntiguedad());
          this.setViajesRealizados (actual.getViajesRealizados());
          this.setDestinosBuscados (actual.getDestinosBuscados());
          this.setOid(OidManager.obtenerOid());
     }
     
     public String toString(){
          return this.nombre + " " + this.apellido;
     } 
     
     public boolean equals (Object o){
          return this.cedula==((Cliente)o).getCedula();
     }
     
     public Broker getBroker(){
          return new BrokerCliente();
     }
}