package modelo;

import java.sql.*;

public class Conexion 
{
	private Connection miConexion;
	
	public Conexion()
	{
		
	}
	
	public Connection dameConexion()
	{
		try
		{
			//CONEXION CON BBDD
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
		
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
		
		return miConexion;
	}
}
