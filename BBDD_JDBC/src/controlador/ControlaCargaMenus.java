package controlador;

import java.awt.event.*;
import modelo.*;
import vista.*;

public class ControlaCargaMenus extends WindowAdapter
{
	public ControlaCargaMenus(MarcoApp elMarco)
	{
		this.elMarco = elMarco;
	}
	
	
	@Override
	public void windowOpened(WindowEvent e) 
	{
		//Realizamos la consulta
		obj.Consultas();
		
		//Despues debenos de recorrer el recordSet
		try
		{
			while(obj.rs.next())
			{
				elMarco.edades.addItem(obj.rs.getString(1));
			}
			
			while(obj.rs2.next())
			{
				elMarco.paises.addItem(obj.rs2.getString(1));
			}
		}
		
		catch(Exception e2)
		{
			System.out.println("Error en la Consulta");
			e2.printStackTrace();
		}
	}
	
	CargaMenus obj = new CargaMenus(); 
	private MarcoApp elMarco;
}
