package dominio;

public class Destino{
     
     
     public enum Tipo{CIUDAD, PUEBLO, VILLA, BALNEARIO, NATURALEZA};
     private String nombre;
     private String localidad;
     private String pais;
     private Tipo tipo;
     
     public String getNombre(){
          return this.nombre;
     }
     
     public String getLocalidad(){
          return this.localidad;
     }
     
     public String getPais(){
          return this.pais;
     }
     
     public Tipo getTipo(){
          return this.tipo;
     }
     
     public void setNombre(String nombreP){
          this.nombre = nombreP;
     }
     
     public void setLocalidad(String localidadP){
          this.localidad = localidadP;
     }
     
     public void setPais(String paisP){
          this.pais = paisP;
     }
     
     public void setTipo(Tipo tipoP){
          boolean encontre = false;
          if(!encontre){
               for(Tipo elemento: Tipo.values()){
                    if(elemento == tipoP){
                         this.tipo = tipoP; 
                         encontre = true;
                    }
               }
          }
          else if(!encontre){
               this.tipo = Tipo.CIUDAD;
          }
     } 
     
     public Destino(){
          
          this.setNombre("sinNombre");
          this.setLocalidad("sinLocalidad");
          this.setPais("sinPais");
          this.setTipo(Tipo.CIUDAD);
          
     }
     
     public Destino(String nombreP, String localidadP, String paisP, Tipo tipoP){
          
          this.setNombre(nombreP);
          this.setLocalidad(localidadP);
          this.setPais(paisP);
          this.setTipo(tipoP);
     }
     
     public Destino(Destino d){
          
          this.setNombre(d.getNombre());
          this.setLocalidad(d.getLocalidad());
          this.setPais(d.getPais());
          this.setTipo(d.getTipo());
          
     }
     
     public String toString(){
          
          return "Destino: " + this.nombre + "\nLocalidad: " + this.localidad + "\nPais: " + this.pais;
          
     }
     
     public boolean equals(Object o){
          
          boolean esIgual = false;
          if(this.getNombre().equals(((Destino)o).getNombre()) && this.localidad.equals(((Destino)o).getLocalidad()) && this.pais.equals(((Destino)o).getPais())){
               esIgual = true;
          }
          
          return esIgual;
          
     }
     
}