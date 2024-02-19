package modelo;

import java.sql.*;

public class EjecutaConsulta 
{
	
	public EjecutaConsulta()
	{
		miConexion = new Conexion();
	}
	public ResultSet filtraBBDD(String edadSelect, String pais)
	{
		
		Connection conecta = miConexion.dameConexion();
		rs=null;
		try
		{
			//Vemos el tipo de filtrado que hizo
			if(!edadSelect.equals("Todos") && pais.equals("Todos"))
			{
				//Se preprara la consulta
				enviaConsultaEdad = conecta.prepareStatement(consultaEdad);
				
				enviaConsultaEdad.setInt(1,Integer.parseInt(edadSelect));
				
				rs = enviaConsultaEdad.executeQuery();
			}
			
			else if(edadSelect.equals("Todos") && !pais.equals("Todos"))
			{
				//Se preprara la consulta
				enviaConsultaPais = conecta.prepareStatement(consultaPais);
				
				enviaConsultaPais.setString(1,pais);
				
				rs = enviaConsultaPais.executeQuery();
			}
			
			else if(!edadSelect.equals("Todos") && !pais.equals("Todos"))
			{
				//Se preprara la consulta
				enviaConsultaAmbos = conecta.prepareStatement(consultaAmbos);
				
				//VEMOS QUE SI LLEVA UN int SE PUEDE PROCESAR COMO STRING O PODEMOS HACERLA CON INT
				//COMO EN LA CONDICION PASADA (LA DE LA EDAD)
				enviaConsultaAmbos.setString(1,edadSelect);
				enviaConsultaAmbos.setString(2,pais);
				
				rs = enviaConsultaAmbos.executeQuery();
			}
			
			else
			{
				//Para cuando te pide todos no es necesario realizar una consulta preparada		
				miStatement = conecta.createStatement();
				
				rs = miStatement.executeQuery("SELECT * FROM DATOSPERSONALES");
			}
			
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error al desplegar");
			e.printStackTrace();
		}
		
		return rs;
	}
	
	private Conexion miConexion;
	private ResultSet rs;
	
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaEdad;
	private PreparedStatement enviaConsultaAmbos;
	private Statement miStatement;
	
	private final String consultaPais = "SELECT * FROM DATOSPERSONALES WHERE PAIS= ?";
	private final String consultaEdad = "SELECT * FROM DATOSPERSONALES WHERE EDAD= ?";
	private final String consultaAmbos = "SELECT * FROM DATOSPERSONALES WHERE EDAD= ? AND PAIS=?";
}
