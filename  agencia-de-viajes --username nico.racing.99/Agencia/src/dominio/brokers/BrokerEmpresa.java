package dominio.brokers;

import java.util.ArrayList;

import persistencia.HandlerPersistencia;
import persistencia.OidManager;

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

        String select = "SELECT id_cliente FROM clientes WHERE estado = 'ACTIVO'";

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
	
	public ArrayList<Cliente> obtenerClientesEspera()
    {
        ArrayList<Cliente> listaClientesEspera = new ArrayList<Cliente>();

        ArrayList<Long> listaOIDs = new ArrayList<Long>();

        String select = "SELECT id_cliente FROM clientes WHERE estado = 'ESPERA'";

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
            listaClientesEspera.add(p);
        }

        HandlerPersistencia.GetInstance().terminarConsulta();

        return listaClientesEspera;
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
            p = (Trabajador)p.leer();  
            listaTrabajadores.add(p);
        }

        HandlerPersistencia.GetInstance().terminarConsulta();

        return listaTrabajadores;
    }
	
	public boolean agregarAlojamiento( Alojamiento a ){
		boolean ret = false;
		
		String sql = "SELECT nombre,tipo FROM alojamientos WHERE nombre = @1 AND tipo = @2";
		sql = sql.replace("@1", "'" + a.getNombre() + "'");
		sql = sql.replace("@2", "'" + a.getTipo() + "'");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		HandlerPersistencia.GetInstance().leerRegistro("nombre");
		boolean hay = HandlerPersistencia.GetInstance().hayMasRegistros();
		
		if( !hay ){
			long cantA = this.contarRegistros("alojamientos");
			a.insertar();
			long cantD = this.contarRegistros("alojamientos");
			if( cantA < cantD )
				ret = true;
		}
		HandlerPersistencia.GetInstance().terminarConsulta();
		return ret;
	}
	
	public void actualizarAlojamiento( Alojamiento a ){
		a.actualizar();		
	}
	
	public boolean eliminarAlojamiento( Alojamiento a ){
		boolean ret = false;
		
		String sql = "DELETE FROM paquetes_turisticos WHERE id_alojamiento = @1";
		sql = sql.replace("@1", a.getOid() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);

		long cantA = this.contarRegistros("alojamientos");
		a.eliminar();
		long cantD = this.contarRegistros("alojamientos");;
		if( cantA > cantD )
			ret = true;
		
		HandlerPersistencia.GetInstance().terminarConsulta();
		return ret;
	}
	
	public boolean agregarCliente( Cliente c ){
		boolean ret = false;
		
		String sql = "SELECT cedula FROM clientes WHERE cedula = @1";
		sql = sql.replace("@1", c.getCedula() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		HandlerPersistencia.GetInstance().leerRegistro("cedula");
		boolean hay = HandlerPersistencia.GetInstance().hayMasRegistros();
		
		if( !hay ){
					
			long cantA = this.contarRegistros("clientes");
			c.insertar();
			long cantD = this.contarRegistros("clientes");
			if( cantA < cantD ){
				ret = true;
				ArrayList<Destino> desBus = c.getDestinosBuscados();
				for( Destino d : desBus ){
					sql = "INSERT INTO destinos_buscados VALUES(@1, @2, @3)";
					sql = sql.replace("@1", OidManager.obtenerOid() + "");
					sql = sql.replace("@2", d.getOid() + "");
					sql = sql.replace("@3", c.getOid() + "");
					HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
				}
			}
		}
		HandlerPersistencia.GetInstance().terminarConsulta();
		return ret;
	}
	
	public void actualizarCliente( Cliente c ){
		String sql = "DELETE FROM destinos_buscados WHERE id_cliente = @1";
		sql = sql.replace("@1",c.getOid() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		
		c.actualizar();		
		
		ArrayList<Destino> desBus = c.getDestinosBuscados();
		for( Destino d:desBus ){
			sql = "INSERT INTO destinos_buscados VALUES(@1, @2, @3)";
			sql = sql.replace("@1", OidManager.obtenerOid() + "");
			sql = sql.replace("@2", d.getOid() + "");
			sql = sql.replace("@3", c.getOid() + "");
			HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		}
		
	}
	
	public boolean eliminarCliente( Cliente c ){
		boolean ret = false;
		
		String sql = "DELETE FROM destinos_buscados WHERE id_cliente = @1";
		sql = sql.replace("@1",c.getOid() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		sql = "DELETE FROM viajes_realizados WHERE id_cliente = @1";
		sql = sql.replace("@1",c.getOid() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		
		long cantA = this.contarRegistros("clientes");
		c.eliminar();
		long cantD = this.contarRegistros("clientes");
		if( cantA > cantD ){
			ret = true;
		}
		
		HandlerPersistencia.GetInstance().terminarConsulta();
		return ret;
	}
	
	public boolean agregarDestino ( Destino d ){
		boolean ret = false;
		
		String sql = "SELECT nombre,localidad,pais FROM destinos WHERE nombre = @1 AND localidad = @2 AND pais = @3";
		sql = sql.replace("@1", "'" + d.getNombre() + "'");
		sql = sql.replace("@2", "'" + d.getLocalidad() + "'");
		sql = sql.replace("@3", "'" + d.getPais() + "'");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		HandlerPersistencia.GetInstance().leerRegistro("nombre");
		boolean hay = HandlerPersistencia.GetInstance().hayMasRegistros();
		
		if( !hay ){
		
		long cantA = this.contarRegistros("destinos");
		d.insertar();
		long cantD = this.contarRegistros("destinos");
		if( cantA < cantD )
			ret = true;
		}		
		
		HandlerPersistencia.GetInstance().terminarConsulta();
		return ret;
	}
	
	public void actualizarDestino( Destino d ){
		d.actualizar();
	}
	
	public boolean eliminarDestino( Destino d ){
		boolean ret = false;
		
		String sql = "DELETE FROM destinos_paquetes WHERE id_destino = @1";
    	sql = sql.replace("@1", d.getOid() + "");
    	HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		
		long cantA = this.contarRegistros("destinos");
		d.eliminar();
		long cantD = this.contarRegistros("destinos");
		if( cantA > cantD )
			ret = true;
		
		HandlerPersistencia.GetInstance().terminarConsulta();
		return ret;
	}
	
	public boolean agregarPaqueteTuristico( PaqueteTuristico p ){
		boolean ret = false;
		
		String sql = "SELECT codigo FROM paquetes_turisticos WHERE codigo = @1";
		sql = sql.replace("@1", p.getCodigo() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		HandlerPersistencia.GetInstance().leerRegistro("codigo");
		boolean hay = HandlerPersistencia.GetInstance().hayMasRegistros();
		
		if( !hay ){
		
		long cantA = this.contarRegistros("paquetes_turisticos");
		p.insertar();
		long cantD = this.contarRegistros("paquetes_turisticos");
		if( cantA < cantD )
			ret = true;
			ArrayList<Destino> destinos = p.getDestinos();
			for( Destino d : destinos ){
				sql = "INSERT INTO destinos_paquetes VALUES(@1, @2, @3)";
				sql = sql.replace("@1", OidManager.obtenerOid() + "");
				sql = sql.replace("@2", d.getOid() + "");
				sql = sql.replace("@3", p.getOid() + "");
				HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
			}	
		}
		HandlerPersistencia.GetInstance().terminarConsulta();
		return ret;
	}
	
	public void actualizarPaquete( PaqueteTuristico p ){
		p.actualizar();
	}
	
	public boolean eliminarPaqueteTuristico( PaqueteTuristico p ){
		
		String sql = "DELETE FROM destinos_paquetes WHERE id_paquete_turistico = @1";
    	sql = sql.replace("@1", p.getOid() + "");
    	HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		
		boolean ret = false;
		long cantA = this.contarRegistros("paquetes_turisticos");
		p.eliminar();
		long cantD = this.contarRegistros("paquetes_turisticos");
		if( cantA > cantD )
			ret = true;
		
		return ret;	
	}
	
	public boolean agregarTrabajador( Trabajador t ){
		boolean ret = false;
		
		String sql = "SELECT cedula FROM trabajadores WHERE cedula = @1";
		sql = sql.replace("@1", t.getCi() + "");
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		HandlerPersistencia.GetInstance().leerRegistro("cedula");
		boolean hay = HandlerPersistencia.GetInstance().hayMasRegistros();
		
		if( !hay ){					
		
		long cantA = this.contarRegistros("trabajadores");
		t.insertar();
		long cantD = this.contarRegistros("trabajadores");
		if( cantA < cantD )
			ret = true;
		}
		return ret;
	}
	
	public void actualizarTrabajador( Trabajador t ){
		t.actualizar();
	}
	
	public boolean eliminarTrabajador( Trabajador t ){
		boolean ret = false;
		long cantA = this.contarRegistros("trabajadores");
		t.eliminar();
		long cantD = this.contarRegistros("trabajadores");
		if( cantA > cantD )
			ret = true;
		
		return ret;
		}
	
	public Long contarRegistros( String tabla ){
		String sql = "SELECT COUNT(*) FROM " + tabla;
		HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
		Long a = (Long) HandlerPersistencia.GetInstance().leerRegistroNumCol(1);
		return a;
	}
	
	public void asignarCodigoPaquete( PaqueteTuristico p ){
		p.setCodigo( this.obtenerCodigoPaquete() );
	}
	
	public int obtenerCodigoPaquete( ){
    	 int codigo = 0;
    	 String sql = "SELECT codigo FROM codigo_paquetes";
    	 HandlerPersistencia.GetInstance().ejecutarSentencia(sql);
    	 codigo = (Integer)HandlerPersistencia.GetInstance().leerRegistro("codigo");
    	 HandlerPersistencia.GetInstance().terminarConsulta();
    	 codigo++;
         String sql2 = "UPDATE codigo_paquetes SET codigo = @1";
         sql2 = sql2.replace("@1","" + codigo);
         HandlerPersistencia.GetInstance().ejecutarSentencia(sql2);
         HandlerPersistencia.GetInstance().terminarConsulta();         
    	 return codigo;
     }
	
}
