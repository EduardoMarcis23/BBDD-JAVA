package modelo;

import java.sql.*;

public class CargaMenus 
{
	public CargaMenus()
	{
		miConexion = new Conexion();
	}
	
	public String Consultas()
	{
		Productos miProducto=null;
		
		//Conectamos con la base de datos
		Connection accesoBBDD = miConexion.dameConexion();
		
		try
		{
			Statement edades = accesoBBDD.createStatement();
			Statement paises = accesoBBDD.createStatement();
			
			 rs = edades.executeQuery("SELECT DISTINCTROW EDAD FROM DATOSPERSONALES ORDER BY EDAD ASC");
		     rs2 = paises.executeQuery("SELECT DISTINCTROW PAIS FROM DATOSPERSONALES ORDER BY PAIS ASC");
			
		  //No es necesario el while ya que lo recorremos en la clase ControlaCargarSecciones
//			while(rs.next())
//			{
				//Devido a que comieza el cursor y nos adelantamos un registro
				//debemos volver 1 para poder ver todos
//				rs.previous();
				
				miProducto = new Productos();
				
				//Almacenamos el NIF en un producto
				miProducto.setEdad(rs.getString(1));
				miProducto.setPais(rs2.getString(1));
				
				//Devuelde el NIF
//				return miProducto.getNif();
	//		}
			
			rs.close();
			rs2.close();
			
			accesoBBDD.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
		//SE DEVE SIEMPRE DEVOLVER AL FINAL YA QUE NO SABE SI ENTRARA EN TRY O CATCH
		return miProducto.getEdad();
	}

	
	
	//IGUAL PUEDE SER DE ESTA MANERA
/*	public ResultSet ejecutaConsulta()
	{
		Connection accesoBBDD = miConexion.dameConexion();
		
		try
		{
			Statement edades = accesoBBDD.createStatement();
			
			return rs = edades.executeQuery("SELECT DISTINCTROW EDAD FROM DATOSPERSONALES ORDER BY EDAD ASC");
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
		
		return null;
	}
	*/
	public Conexion miConexion;
	
	//Debe ser public para que pueda acceder el controlador
	public ResultSet rs;
	public ResultSet rs2;
	
	
}
