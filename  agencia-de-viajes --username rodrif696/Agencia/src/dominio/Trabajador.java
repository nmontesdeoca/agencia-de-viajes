package dominio;

import persistencia.*;
import dominio.brokers.*;

public class Trabajador extends Persistente{
     
     private String nombre;
     private String apellido;
     private int ci;
     private int numeroTrabajador;
     private double ganancias;
     public char[] password;
     public boolean permisosAdministrativos;
     private long oId;
     
     public void setNombre (String nomTrabajador){
          this.nombre=nomTrabajador;
     }
     
     public void setPassword (char[] passwordP){
          if(passwordP.length > 6){
               this.password=passwordP;
          }else{
               String aux = "invalido";
               this.password = aux.toCharArray();
          } 
     }
     
     public void setPermisosAdministrativos(boolean permisos){
          this.permisosAdministrativos = permisos;
     }
     
     public String getNombre(){
          return this.nombre;
     }
     
     public boolean getPermisosAdministrativos(){
          return this.permisosAdministrativos;
     }
     
     public char[] getPassword(){
          return this.password;
     }
     
     public void setApellido (String apeTrabajador){
          this.apellido=apeTrabajador;
     }
     
     public String getApellido(){
          return this.apellido;
     }
     
     public void setCi (int ciTrabajador){
          if(ciTrabajador>0){
               this.ci=ciTrabajador;
          }
          else{
               this.ci=0;
          }
     } 
     
     public int getCi(){
          return this.ci;
     }
     
     public void setNumTrabajador (int numTrabajador){
          if(numTrabajador>0){
               this.numeroTrabajador=numTrabajador;
          }
          else{
               this.numeroTrabajador=0;
          }
     } 
     
     public int getNumTrabajador(){
          return this.numeroTrabajador;
     }
     
     public void setGanancias (double trabGanancias){
          if(trabGanancias>0){
               this.ganancias=trabGanancias;
          }
          else{
               this.ganancias=0;
          }
     } 
     
     public double getGanancias(){
          return this.ganancias;
     }
     
     public Trabajador (){
          
          this.setNombre("sin definir");
          this.setApellido("sin definir");
          this.setCi(0);
          this.setNumTrabajador(0);
          this.setGanancias(0);
          this.setPassword(new char[0]);
          this.setPermisosAdministrativos(false);
     }
     
     public String toString(){
          return this.getNombre() +" "+this.getApellido();
     }
     
     public boolean equals (Object o){
          return this.ci==((Trabajador)o).getCi();
     }
     
     public double calcularGanancias (double montoBase){
          return (this.getGanancias()*15)/100+montoBase;
     }
     
     public Broker getBroker(){
          
          return new BrokerTrabajador();
     }
}