package dominio;

import persistencia.OidManager;

public class TrabajadorBase extends Trabajador{
     
     public TrabajadorBase (){
          
          this.setNombre("sin definir");
          this.setApellido("sin definir");
          this.setCi(0);
          this.setNumTrabajador(0);
          this.setGanancias(0);
          this.setPassword(new char[0]);
          this.setPermisosAdministrativos(false);
          this.setOid(OidManager.obtenerOid());
     }
     
     public TrabajadorBase (String nombre, String apellido, int ci, int numeroTrabajador, double ganancias, char[] passwordP){
          
          this.setNombre(nombre);
          this.setApellido(apellido);
          this.setCi(ci);
          this.setNumTrabajador(numeroTrabajador);
          this.setGanancias(ganancias);
          this.setPassword(passwordP);
          this.setPermisosAdministrativos(false);
          this.setOid(OidManager.obtenerOid());
     }
     
     public TrabajadorBase (Trabajador traba){
          
          this.setNombre(traba.getNombre());
          this.setApellido(traba.getApellido());
          this.setCi(traba.getCi());
          this.setNumTrabajador(traba.getNumTrabajador());
          this.setGanancias(traba.getGanancias());
          this.setPassword(traba.getPassword());
          this.setPermisosAdministrativos(traba.getPermisosAdministrativos());
          this.setOid(OidManager.obtenerOid());
     }
          
     public String toString(){
          return "(B)"+super.toString();
     }
}