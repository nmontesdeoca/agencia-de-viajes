package dominio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Empresa extends Observable{

    private String nombre;
    private int ruc;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<PaqueteTuristico> listaPaquetes;
    private ArrayList<Destino> listaDestinos;
    private ArrayList<Trabajador> listaTrabajadores;
    private ArrayList<Trabajador> listaTrabajadoresAux;
    private ArrayList<Alojamiento> listaAlojamientos;
    private ArrayList<Cliente> listaDeEspera;
    private double montoBase;
    private static Empresa EMPRESA = null;

    public boolean agregarCliente(Cliente c){  
 boolean ret = false;
 if(!this.getListaClientes().contains(c)){
     this.getListaClientes().add(c);
     ret = true;
 }
 notificar();
 return ret;
    }

    public boolean agregarPaquete(PaqueteTuristico p){
 boolean ret = false;
 if(!this.getListaPaquetes().contains(p)){
     this.getListaPaquetes().add(p);
     ret = true;
 }
 notificar();
 return ret;
    }

    public boolean agregarDestino(Destino d){
 boolean ret = false;
 if(!this.getListaDestinos().contains(d)){
     this.getListaDestinos().add(d);
     ret = true;
 }
 notificar();
 return ret;
    }

    public boolean agregarTrabajador(Trabajador t){
 boolean ret = false;
 if(!this.getListaTrabajadores().contains(t)){
     this.getListaTrabajadores().add(t);
     ret = true;
 }
 notificar();
 return ret;
    }

    public boolean agregarAlojamiento(Alojamiento a){
 boolean ret = false;
 if(!this.getListaAlojamientos().contains(a)){
     this.getListaAlojamientos().add(a);
     ret = true;
 }
 notificar();
 return ret;
    }

    public String getNombre(){
 return this.nombre;
    }

    public int getRuc(){
 return this.ruc;
    }

    public ArrayList<Cliente> getListaClientes(){
 return this.listaClientes;
    }

    public ArrayList<PaqueteTuristico> getListaPaquetes(){
 return this.listaPaquetes;
    }

    public ArrayList<Destino> getListaDestinos(){
 return this.listaDestinos;
    }

    public ArrayList<Trabajador> getListaTrabajadores(){
 return this.listaTrabajadores;
    }

    public ArrayList<Alojamiento> getListaAlojamientos(){
 return this.listaAlojamientos;
    }

    public ArrayList<Cliente> getListaDeEspera(){
 return this.listaDeEspera;
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

    public boolean eliminarDestino(Destino d){

 boolean res = this.getListaDestinos().remove(d);
 Iterator<PaqueteTuristico> iter = this.getListaPaquetes().iterator();
 while(iter.hasNext()){
     PaqueteTuristico p = iter.next();
     p.getDestinos().remove(d);
 }
 notificar();
 return res;
    }

    public boolean eliminarAlojamiento(Alojamiento a){
 boolean res = this.getListaAlojamientos().remove(a);
 Iterator<PaqueteTuristico> iter1 = this.getListaPaquetes().iterator();
 while(iter1.hasNext()){
     PaqueteTuristico p = iter1.next();
     if(p.getAlojamiento().equals(a)){
  this.getListaPaquetes().remove(p);
     }
 }
 notificar();
 return res;
    }

    public boolean eliminarTrabajador(Trabajador t){
 boolean b =  this.getListaTrabajadores().remove(t);
 notificar();
 return b;
    }

    public boolean eliminarPaquete(PaqueteTuristico p){
 boolean b = this.getListaPaquetes().remove(p);
 notificar();
 return b;
    }

    public boolean eliminarCliente(Cliente c){
 
 boolean b = this.listaClientes.remove(c);
      listaDeEspera.remove(c);
 
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

 int minimo = (int)Math.ceil(destino.size()/2);
 Iterator<PaqueteTuristico> iterPaquetes = this.getListaPaquetes().iterator();
 PaqueteTuristico retorno = null;
 boolean encontre = false;
 while(iterPaquetes.hasNext() && !encontre){
     PaqueteTuristico p = iterPaquetes.next();
     if(p.cantidadDestinos(destino) >= minimo){
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
 this.listaTrabajadoresAux = new ArrayList<Trabajador>() ;

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