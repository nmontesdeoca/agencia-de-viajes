package dominio;

import persistencia.OidManager;

public class Administrativo extends Trabajador{
     
     public Administrativo(){
          
          this.setPermisosAdministrativos(true);
          
     }
     
     public Administrativo (String nombre, String apellido, int ci, int numeroTrabajador, int ganancias, char[] passwordP){
          
          this.setNombre(nombre);
          this.setApellido(apellido);
          this.setCi(ci);
          this.setNumTrabajador(numeroTrabajador);
          this.setGanancias(ganancias);
          this.setPassword(passwordP);
          this.setPermisosAdministrativos(true);
          this.setOid(OidManager.obtenerOid());
          
     }
     
     public Administrativo(Administrativo admin){
          
          this.setNombre(admin.getNombre());
          this.setApellido(admin.getApellido());
          this.setCi(admin.getCi());
          this.setNumTrabajador(admin.getNumTrabajador());
          this.setGanancias(admin.getGanancias());
          this.setPassword(admin.getPassword());
          this.setPermisosAdministrativos(admin.getPermisosAdministrativos());
          this.setOid(OidManager.obtenerOid());
          
     } 
     
     public double calcularGanancias (double montoBase){
          return montoBase * 2;
     }
     
     public String toString(){
          return "Administrativo: "+super.toString();
     }
     
}