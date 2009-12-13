package dominio.brokers;

import java.util.ArrayList;

import persistencia.HandlerPersistencia;

import dominio.Alojamiento;

public class BrokerEmpresa {

	public ArrayList<Alojamiento> obtenerAlojamientos()
    {
        ArrayList<Alojamiento> listaAlojamientos = new ArrayList<Alojamiento>();

        ArrayList<Integer> listaOIDs = new ArrayList<Integer>();

        String select = "SELECT id_alojamiento FROM alojamientos";

        HandlerPersistencia.GetInstance().ejecutarSentencia(select);

        while (HandlerPersistencia.GetInstance().hayMasRegistros())
        {
            listaOIDs.add((Integer)(HandlerPersistencia.GetInstance().leerRegistro("oid")));
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
	
}
