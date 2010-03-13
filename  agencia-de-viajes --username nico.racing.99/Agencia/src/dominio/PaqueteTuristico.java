package dominio;
import dominio.brokers.*;
import persistencia.*;

import java.util.ArrayList;
import java.util.Iterator;

public class PaqueteTuristico extends Persistente{
     
     private String nombre;
     private ArrayList<Destino> destinos;
     private int duracion;
     private double precio;
     private long codigo;
     private Alojamiento alojamiento;
     //private static int COUNT=0;
     private long oid;
     private BrokerPaqueteTuristico bpt = new BrokerPaqueteTuristico();
     
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
     
     public void setDestinos(ArrayList <Destino> destinosX){
          this.destinos= destinosX;
     }
     public ArrayList<Destino> getDestinos(){
          return this.destinos;
     }
     public void setDuracion(int duracionX){
          if(duracionX>0 && duracionX<100){
               this.duracion= duracionX;
          }
          else{
               this.duracion= 1;
          } 
     }
     public int getDuracion(){
          return this.duracion;
     }
     public void setPrecio(double precioX){
          if(precioX>0){
               this.precio= precioX;
          }
          else{
               this.precio= 1;
          }
     }
     public double getPrecio(){
          return this.precio;
     }
     public void setCodigo(long codigo2){
          if(codigo2>0){
               this.codigo= codigo2;
          }
          else{
               this.codigo= (int) bpt.obtenerCodigo();//COUNT++;
          } 
     }
     public long getCodigo(){
          return this.codigo;
     }
     public void setAlojamiento(Alojamiento alojamientoX){
          this.alojamiento= alojamientoX;
     }
     public Alojamiento getAlojamiento(){
          return this.alojamiento;
     }
     
     public int cantidadDestinos (ArrayList <Destino> destinosP){
          int cant=0;
          Iterator<Destino> iterDestino= destinosP.iterator();
          
          while (iterDestino.hasNext()){
               Destino d= iterDestino.next();
               if(this.getDestinos().contains(d)){
                    cant++;
               }
          }
          return cant;
     }
     
     public boolean agregarDestino(Destino d){
          boolean ret = false;
          if(!this.getDestinos().contains(d)){
               this.getDestinos().add(d);
               ret = true;
          }
         return ret;
     }
     
     public PaqueteTuristico(){
          this.setNombre ("sin definir");
          this.setDestinos (new ArrayList <Destino>());
          this.setDuracion (0);
          this.setPrecio (0);
          //COUNT++;
          //this.setCodigo (0);
          this.setAlojamiento (new Alojamiento());
          this.setOid(OidManager.obtenerOid());
          
     } 
     
     public PaqueteTuristico(String nombre, ArrayList <Destino> destino, int duracion, double precio, Alojamiento alojamiento){
          this.setNombre(nombre);
          this.setDestinos (destino);
          this.setDuracion (duracion);
          this.setPrecio (precio);
          //COUNT++;
          //this.setCodigo (bpt.obtenerCodigo());
          this.setAlojamiento (alojamiento);
          this.setOid(OidManager.obtenerOid());
     }
     
     public PaqueteTuristico(PaqueteTuristico actual){
          this.setNombre (actual.getNombre());
          this.setDestinos (actual.getDestinos());
          this.setDuracion (actual.getDuracion());
          this.setPrecio (actual.getPrecio());
          //COUNT++;
          //this.setCodigo (actual.getCodigo());
          this.setAlojamiento (actual.getAlojamiento());  
          this.setOid(OidManager.obtenerOid());
     }
     
     public String toString(){
          return this.codigo +" "+this.nombre;
     } 
     public boolean equals (Object o){
          return this.codigo==((PaqueteTuristico)o).getCodigo();
     }  
     
     public Broker getBroker(){
          return new BrokerPaqueteTuristico();
     }
}
