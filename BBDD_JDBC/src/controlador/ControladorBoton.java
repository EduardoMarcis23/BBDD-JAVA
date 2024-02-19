package controlador;

import java.awt.event.*;
import java.sql.*;
import modelo.*;
import vista.*;

public class ControladorBoton implements ActionListener
{
	//Se recibe el marco para darle funcionalidad
	public ControladorBoton(MarcoApp elMarco)
	{
		this.elMarco = elMarco;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String seleccionEdad = (String)elMarco.edades.getSelectedItem();
		String seleccionPais = (String)elMarco.paises.getSelectedItem();
		
		//RECIBIMOS EL RESULTSET
		resultadoConsulta = obj.filtraBBDD(seleccionEdad, seleccionPais);
		
		try
		{
			//Para borrar lo anterior se usa
			elMarco.resultado.setText("");
			
			while(resultadoConsulta.next())
			{
				//Imprimimos lo del resultset
				elMarco.resultado.append(resultadoConsulta.getString(1) + " "+ resultadoConsulta.getString(2)+" ");
				elMarco.resultado.append(resultadoConsulta.getString(3) + " "+ resultadoConsulta.getString(4)+" ");
				elMarco.resultado.append(resultadoConsulta.getString(6));
				
				elMarco.resultado.append("\n");
			}
		}
		
		catch (Exception e2)
		{
			System.out.println("ERROR AL RECIBIR CONSULTAS");
			e2.printStackTrace();
		}
	}
	
	private MarcoApp elMarco;
	private EjecutaConsulta obj = new EjecutaConsulta();
	private ResultSet resultadoConsulta;
}
