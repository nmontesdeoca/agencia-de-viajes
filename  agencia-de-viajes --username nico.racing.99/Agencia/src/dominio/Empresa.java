package dominio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


import dominio.brokers.BrokerEmpresa;

public class Empresa extends Observable{
     
     private String nombre;
     private int ruc;
     @SuppressWarnings("unused")
	private ArrayList<Cliente> listaClientes;
     @SuppressWarnings("unused")
	private ArrayList<PaqueteTuristico> listaPaquetes;
     @SuppressWarnings("unused")
	private ArrayList<Destino> listaDestinos;
     @SuppressWarnings("unused")
	private ArrayList<Trabajador> listaTrabajadores;
     private ArrayList<Trabajador> listaTrabajadoresAux;
     @SuppressWarnings("unused")
	private ArrayList<Alojamiento> listaAlojamientos;
     private ArrayList<Cliente> listaDeEspera;
     private double montoBase;
     private static Empresa EMPRESA = null;
     private BrokerEmpresa be = new BrokerEmpresa();
     
     public boolean agregarCliente(Cliente c){ 
    	 /*
          boolean ret = false;
          if(!this.getListaClientes().contains(c)){
               this.getListaClientes().add(c);
               ret = true;
          }
          */
    	  notificar();
    	  boolean ret = false;
    	  ret = be.agregarCliente(c);
          return ret;
     }
     
     public boolean agregarPaquete(PaqueteTuristico p){
          boolean ret = false;
         /* if(!this.getListaPaquetes().contains(p)){
               this.getListaPaquetes().add(p);
               ret = true;
          }*/
          be.asignarCodigoPaquete(p);
          ret = be.agregarPaqueteTuristico(p);
          notificar();
          return ret;
     }
     
     public boolean agregarDestino(Destino d){
          boolean ret = false;
         /* if(!this.getListaDestinos().contains(d)){
               this.getListaDestinos().add(d);
               ret = true;
          }*/
          ret = be.agregarDestino(d);
          notificar();
          return ret;
     }
     
     public boolean agregarTrabajador(Trabajador t){
          boolean ret = false;
        /*  if(!this.getListaTrabajadores().contains(t)){
               this.getListaTrabajadores().add(t);
               ret = true;
          }*/
          ret = be.agregarTrabajador(t);
          notificar();
          return ret;
     }
     
     public boolean agregarAlojamiento(Alojamiento a){
         /* boolean ret = false;
          if(!this.getListaAlojamientos().contains(a)){
               this.getListaAlojamientos().add(a);
               ret = true;
          }*/
    	  notificar();
    	  boolean ret = be.agregarAlojamiento(a);
          return ret;
     }
     
     public String getNombre(){
          return this.nombre;
     }
     
     public int getRuc(){
          return this.ruc;
     }
     
     public ArrayList<Cliente> getListaClientes(){
          return be.obtenerClientes();
          //this.listaClientes;
     }
     
     public ArrayList<PaqueteTuristico> getListaPaquetes(){
          return be.obtenerPaquetesTuristicos();
          //return this.listaPaquetes;
     }
     
     public ArrayList<Destino> getListaDestinos(){
          return be.obtenerDestinos();
          //return this.listaDestinos;
     }
     
     public ArrayList<Trabajador> getListaTrabajadores(){
          return be.obtenerTrabajadores();
          //return this.listaTrabajadores;
     }
     
     public ArrayList<Alojamiento> getListaAlojamientos(){
          //return this.listaAlojamientos;
    	 return be.obtenerAlojamientos();
     }
     
     public ArrayList<Cliente> getListaDeEspera(){
          //return this.listaDeEspera;
    	 return be.obtenerClientesEspera();
     }
     
     public double getMontoBase(){
          return this.montoBase;
     }
     
     public ArrayList<Trabajador> getListaTrabajadoresAux(){
          return this.listaTrabajadoresAux;
     }
     
     public void setNombre(String nombreP){
          this.nombre = nombreP;
     }
     
     public void setRuc(int rucP){
          if(rucP > 0){
               this.ruc = rucP;
          }else{
               this.ruc = 0;
          }
     }
     
     public void setListaClientes(ArrayList<Cliente> listaClientesP){
          this.listaClientes = listaClientesP;
     }
     
     public void setListaPaquetes(ArrayList<PaqueteTuristico> listaPaquetesP){
          this.listaPaquetes = listaPaquetesP;
     }
     
     public void setListaDestinos(ArrayList<Destino> listaDestinosP){
          this.listaDestinos = listaDestinosP;
     }
     
     public void setListaTrabajadores(ArrayList<Trabajador> listaTrabajadoresP){
          this.listaTrabajadores = listaTrabajadoresP;
     }
     
     public void setListaAlojamientos(ArrayList<Alojamiento> listaAlojamientosP){
          this.listaAlojamientos = listaAlojamientosP;
     }
     
     public void setListaDeEspera(ArrayList<Cliente> listaDeEsperaP){
          this.listaDeEspera = listaDeEsperaP;
     }
     
     public void setMontoBase(double montoBaseP){
          this.montoBase = montoBaseP;   
     }
     
     public void setListaTrabajadoresAux(ArrayList<Trabajador> listaTrabajadoresAuxP){
          this.listaTrabajadoresAux = listaTrabajadoresAuxP;    
     }
     
     public void actualizarDestino( Destino d ){
    	 be.actualizarDestino(d);
     }
     
     public boolean eliminarDestino(Destino d){
          /*
          boolean res = this.getListaDestinos().remove(d);
          Iterator<PaqueteTuristico> iter = this.getListaPaquetes().iterator();
          while(iter.hasNext()){
               PaqueteTuristico p = iter.next();
               p.getDestinos().remove(d);
          }
          notificar();
          return res;*/
    	 notificar();
    	 return be.eliminarDestino(d);
     }
     
     public void actualizarAlojamiento( Alojamiento a ){
    	 be.actualizarAlojamiento(a);
     }
     
     public boolean eliminarAlojamiento(Alojamiento a){
         // boolean res = this.getListaAlojamientos().remove(a);
         
    	 boolean res = be.eliminarAlojamiento(a); 
    	 
    	/* Iterator<PaqueteTuristico> iter1 = this.getListaPaquetes().iterator();
          while(iter1.hasNext()){
               PaqueteTuristico p = iter1.next();
               if(p.getAlojamiento().equals(a)){
                    this.getListaPaquetes().remove(p);
               }
          }*/
    	 
          notificar();
          return res;
     }
     
     public void actualizarTrabajador( Trabajador t ){
    	 be.actualizarTrabajador(t);
     }
     
     public boolean eliminarTrabajador(Trabajador t){
         // boolean b =  this.getListaTrabajadores().remove(t);
         
    	 boolean b = be.eliminarTrabajador(t);
    	 
    	 notificar();
          return b;
     }
     
     public void actualizarPaquete( PaqueteTuristico p ){
    	 be.actualizarPaquete(p);
     }
     
     public boolean eliminarPaquete(PaqueteTuristico p){
         // boolean b = this.getListaPaquetes().remove(p);
          
    	 boolean b = be.eliminarPaqueteTuristico(p);
    	 
    	 notificar();
          return b;
     }
     
     public void actualizarCliente( Cliente c ){
    	 be.actualizarCliente(c);
     }
     
     public boolean eliminarCliente(Cliente c){
          
          // b = this.listaClientes.remove(c);
          //listaDeEspera.remove(c);
          boolean b = be.eliminarCliente(c);
    	  notificar();
          return b;
     }
     
     public boolean ventaViaje(Cliente c, Trabajador t){
          boolean ret = false;
          PaqueteTuristico p = asignarPaquete(c);
          if(p != null){
               t.setGanancias(t.getGanancias() + p.getPrecio());
               ret = true;
          }else{
               if(!this.getListaDeEspera().contains(c))
                    this.getListaDeEspera().add(c);
               this.getListaTrabajadoresAux().add(t);
          }
          return ret;
     }
     
     public PaqueteTuristico asignarPaquete(Cliente c){
          
          PaqueteTuristico p = chequearPaquetesCon(c.getDestinosBuscados());
          
          if(p != null){
               c.getDestinosBuscados().clear();
               c.getViajesRealizados().addAll(p.getDestinos());
               
          }
          return p;
     }
     
     public PaqueteTuristico chequearPaquetesCon(ArrayList<Destino> destino){
          double minimo = Math.ceil(((double)destino.size()/2));
          Iterator<PaqueteTuristico> iterPaquetes = this.getListaPaquetes().iterator();
          PaqueteTuristico retorno = null;
          boolean encontre = false;
          while(iterPaquetes.hasNext() && !encontre){
               PaqueteTuristico p = iterPaquetes.next();
               if(p.cantidadDestinos(destino) >= minimo && minimo != 0){
                    retorno = p;
                    encontre = true;
               }
          }
          return retorno;
     }
     
     public void chequearDisponibilidad(){
          
          Iterator<Cliente> iter = listaDeEspera.iterator();
          while(iter.hasNext()){
               Cliente c = iter.next();
               if(chequearPaquetesCon(c.getDestinosBuscados()) != null){
                    int i = this.getListaDeEspera().indexOf(c);
                    listaDeEspera.remove(c);
                    ventaViaje(c,listaTrabajadoresAux.remove(i));
               }
          }
          
     }
     
     public double calcularSueldo(Trabajador t){
          
          return t.calcularGanancias(this.getMontoBase());
          
     }
     
     private Empresa(){
          
          this.listaClientes = new ArrayList<Cliente>();
          this.listaDeEspera = new ArrayList<Cliente>();
          this.listaPaquetes = new ArrayList<PaqueteTuristico>();
          this.listaDestinos = new ArrayList<Destino>();
          this.listaTrabajadores = new ArrayList<Trabajador>() ;
          this.listaAlojamientos = new ArrayList<Alojamiento>();
          this.listaTrabajadoresAux = new ArrayList<Trabajador>();
     }
     
     public static Empresa GetInstance(){
          
          if(EMPRESA == null){
               EMPRESA = new Empresa();
          }
          return EMPRESA;
          
     }
     
     public String toString(){
          
          return "Empresa: " + this.nombre + "\nRuc: " + this.ruc;
          
     }    
     
     public void notificar() {
          this.setChanged();
          this.notifyObservers(); 
     }
     
     public boolean equals(Object o){
          
          boolean esIgual = false;
          if(this.ruc == ((Empresa)o).getRuc()){
               esIgual = true;
          }
          
          return esIgual;
     }
}