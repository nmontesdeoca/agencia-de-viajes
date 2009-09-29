package dominio;

public class Sistema{
     
     private Empresa empresa;
     private Trabajador trabajadorActual;
     
     public Trabajador getTrabajadorActual(){
          return this.trabajadorActual;
     }
     
     public Empresa getEmpresa(){
          return this.empresa; 
     }
     
     private void setTrabajadorActual(Trabajador trabajadorActualP){
          if(validarUsuario(trabajadorActualP)){
               this.trabajadorActual = trabajadorActualP;
          }else{
               System.out.println("Usuario invalido");
          }
     }
     
     public void setEmpresa(Empresa empresaP){
          this.empresa = empresaP;
     }
     
     public Sistema(Trabajador trabajadorP){
          
          empresa = Empresa.GetInstance();
          this.setTrabajadorActual(trabajadorP);
          
     }
     
     public boolean validarUsuario(Trabajador trabajadorP){
          
          boolean retorno = false;
          Trabajador aux = new Trabajador();
          if(Empresa.GetInstance().getListaTrabajadores().contains(trabajadorP)){
               int indice = Empresa.GetInstance().getListaTrabajadores().indexOf(trabajadorP);
               aux = Empresa.GetInstance().getListaTrabajadores().get(indice);
               if(aux.getPassword().equals(trabajadorP.getPassword())){
                    retorno = true;
               } 
          }
          return retorno;
          
     }
     
}