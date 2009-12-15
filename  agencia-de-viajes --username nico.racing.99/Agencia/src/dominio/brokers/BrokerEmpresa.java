package dominio.brokers;

import java.util.ArrayList;

import persistencia.HandlerPersistencia;

import dominio.Alojamiento;
import dominio.Cliente;
import dominio.Destino;
import dominio.PaqueteTuristico;
import dominio.Trabajador;

public class BrokerEmpresa {

	public ArrayList<Alojamiento> obtenerAlojamientos()
    {
        ArrayList<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();

        ArrayList<Long> listaOIDs = new ArrayList<Long>();

        String select = "SELECT id_alojamiento FROM alojamientos";

        HandlerPersistencia.GetInstance().ejecutarSentencia(select);
        
        while (HandlerPersistencia.GetInstance().hayMasRegistros())
        {
            listaOIDs.add((Long)(HandlerPersistencia.GetInstance().leerRegistro("id_alojamiento")));
        }

        for (long oid : listaOIDs)
        {
            Alojamiento p = new Alojamiento();
            p.setOid(oid);
            p.leer();  
            listaAlojamientos.add(p);
        }

        HandlerPersistencia.GetInstance().terminarConsulta();

        return listaAlojamientos;
    }
	
	public ArrayList<Cliente> obtenerClientes()
    {
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

        ArrayList<Long> listaOIDs = new ArrayList<Long>();

        String select = "SELECT id_cliente FROM clientes";

        HandlerPersistencia.GetInstance().ejecutarSentencia(select);
        
        while (HandlerPersistencia.GetInstance().hayMasRegistros())
        {
            listaOIDs.add((Long)(HandlerPersistencia.GetInstance().leerRegistro("id_cliente")));
        }

        for (long oid : listaOIDs)
        {
            Cliente p = new Cliente();
            p.setOid(oid);
            p.leer();  
            listaClientes.add(p);
        }

        HandlerPersistencia.GetInstance().terminarConsulta();

        return listaClientes;
    }
	
	public ArrayList<Destino> obtenerDestinos()
    {
        ArrayList<Destino> listaDestinos = new ArrayList<Destino>();

        ArrayList<Long> listaOIDs = new ArrayList<Long>();

        String select = "SELECT id_destino FROM destinos";

        HandlerPersistencia.GetInstance().ejecutarSentencia(select);
        
        while (HandlerPersistencia.GetInstance().hayMasRegistros())
        {
            listaOIDs.add((Long)(HandlerPersistencia.GetInstance().leerRegistro("id_destino")));
        }

        for (long oid : listaOIDs)
        {
            Destino p = new Destino();
            p.setOid(oid);
            p.leer();  
            listaDestinos.add(p);
        }

        HandlerPersistencia.GetInstance().terminarConsulta();

        return listaDestinos;
    }
	
	public ArrayList<PaqueteTuristico> obtenerPaquetesTuristicos()
    {
        ArrayList<PaqueteTuristico> listaPaquetes = new ArrayList<PaqueteTuristico>();

        ArrayList<Long> listaOIDs = new ArrayList<Long>();

        String select = "SELECT id_paquete_turistico FROM paquetes_turisticos";

        HandlerPersistencia.GetInstance().ejecutarSentencia(select);
        
        while (HandlerPersistencia.GetInstance().hayMasRegistros())
        {
            listaOIDs.add((Long)(HandlerPersistencia.GetInstance().leerRegistro("id_paquete_turistico")));
        }

        for (long oid : listaOIDs)
        {
            PaqueteTuristico p = new PaqueteTuristico();
            p.setOid(oid);
            p.leer();  
            listaPaquetes.add(p);
        }

        HandlerPersistencia.GetInstance().terminarConsulta();

        return listaPaquetes;
    }
	
	public ArrayList<Trabajador> obtenerTrabajadores()
    {
        ArrayList<Trabajador> listaTrabajadores = new ArrayList<Trabajador>();

        ArrayList<Long> listaOIDs = new ArrayList<Long>();

        String select = "SELECT id_trabajador FROM trabajadores";

        HandlerPersistencia.GetInstance().ejecutarSentencia(select);
        
        while (HandlerPersistencia.GetInstance().hayMasRegistros())
        {
            listaOIDs.add((Long)(HandlerPersistencia.GetInstance().leerRegistro("id_trabajador")));
        }

        for (long oid : listaOIDs)
        {
            Trabajador p = new Trabajador();
            p.setOid(oid);
            p.leer();  
            listaTrabajadores.add(p);
        }

        HandlerPersistencia.GetInstance().terminarConsulta();

        return listaTrabajadores;
    }
	
	public boolean agregarAlojamiento( Alojamiento a ){
		boolean ret = false;
		int cantA = this.obtenerAlojamientos().size();
		a.insertar();
		int cantD = this.obtenerAlojamientos().size();
		if( cantA < cantD )
			ret = true;
		
		return ret;
	}
	
	public boolean eliminarAlojamiento( Alojamiento a ){
		boolean ret = false;
		int cantA = this.obtenerAlojamientos().size();
		a.eliminar();
		int cantD = this.obtenerAlojamientos().size();
		if( cantA > cantD )
			ret = true;
		
		return ret;
	}
	
	public boolean agregarCliente( Cliente c ){
		boolean ret = false;
		
		String sql = "SELECT nombre FROM clientes WHERE cedula = @1";
		sql = sql.replace("@1", c.getCedula() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		HandlerPersistencia.GetInstance().leerRegistro("nombre");
		boolean hay = HandlerPersistencia.GetInstance().hayMasRegistros();
		
		if( !hay ){
					
		//int cantA = this.obtenerClientes().size();
		long cantA = this.contarClientes();
		c.insertar();
		long cantD = this.contarClientes();
		//int cantD = this.obtenerClientes().size();
		if( cantA < cantD )
			ret = true;
		}
		
		return ret;
	}
	
	public Long contarClientes(  ){
		String sql = "SELECT COUNT(*) FROM clientes";
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		Long a = (Long) HandlerPersistencia.GetInstance().leerRegistroNumCol(1);
		return a;
	}
	
	public boolean eliminarCliente( Cliente c ){
		boolean ret = false;
		int cantA = this.obtenerClientes().size();
		c.eliminar();
		int cantD = this.obtenerClientes().size();
		if( cantA > cantD )
			ret = true;
		
		return ret;
	}
	
	public boolean agregarDestino ( Destino d ){
		boolean ret = false;
		int cantA = this.obtenerDestinos().size();
		d.insertar();
		int cantD = this.obtenerDestinos().size();
		if( cantA < cantD )
			ret = true;
		
		return ret;
	}
	
	public boolean eliminarDestino( Destino d ){
		boolean ret = false;
		int cantA = this.obtenerDestinos().size();
		d.eliminar();
		int cantD = this.obtenerDestinos().size();
		if( cantA > cantD )
			ret = true;
		
		return ret;
	}
	
	public boolean agregarPaqueteTuristico( PaqueteTuristico p ){
		boolean ret = false;
		int cantA = this.obtenerPaquetesTuristicos().size();
		p.insertar();
		int cantD = this.obtenerPaquetesTuristicos().size();
		if( cantA < cantD )
			ret = true;
		
		return ret;
	}
	
	public boolean eliminarPaqueteTuristico( PaqueteTuristico p ){
		boolean ret = false;
		int cantA = this.obtenerPaquetesTuristicos().size();
		p.eliminar();
		int cantD = this.obtenerPaquetesTuristicos().size();
		if( cantA > cantD )
			ret = true;
		
		return ret;	
	}
	
	public boolean insertarTrabajador( Trabajador t ){
		boolean ret = false;
		int cantA = this.obtenerTrabajadores().size();
		t.insertar();
		int cantD = this.obtenerTrabajadores().size();
		if( cantA < cantD )
			ret = true;
		
		return ret;
	}
	
	public boolean eliminarTrabajador( Trabajador t ){
		boolean ret = false;
		int cantA = this.obtenerTrabajadores().size();
		t.eliminar();
		int cantD = this.obtenerTrabajadores().size();
		if( cantA > cantD )
			ret = true;
		
		return ret;
		}
	
}
