package metaDatos;

import java.sql.*;

public class InfoMetadatos 
{

	public static void main(String[] args) 
	{
		mostrarInfoBBDD();
		System.out.println();
		mostrarInfoTablas();
	}
	
	static void mostrarInfoBBDD()
	{
		Connection miConexion = null;
		
		try
		{					
				
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
		
			//OBTENCION DE METADATOS
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			//MOstramos la informacion de la BBDD
			System.out.println("El nombre del gestor es "+ datosBBDD.getDatabaseProductName());
			System.out.println("La version del gestor es " + datosBBDD.getDatabaseProductVersion());
			System.out.println("El driver  es "+datosBBDD.getDriverName());
			System.out.println("Con la version "+datosBBDD.getDriverVersion());
			
		}	
		
		catch(Exception e)
		{
			
			System.out.println("ERROR AL CONSULTAR METADATOS!!");
			e.printStackTrace();
		}
		
		finally
		{
			try 
			{
				miConexion.close();
			} 
			
			catch (SQLException e) 
			{
				System.out.println("ERROR AL CERRAR LA BASE");
				e.printStackTrace();
			}
		}
	}
	
	static void mostrarInfoTablas() 
	{
		Connection miConexion = null;
		ResultSet miResult = null;
		
		try
		{					
				
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
		
			//OBTENCION DE METADATOS
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			//Mostramos la informacion de las tablas de la BBDD
			System.out.println("Las tablas que existe en la base son: ");
			
			//Para obtener las tablas se recibe un ResultSet y le pasan los parametros
			//(catalog, schemaPattern, tableNamePattern, types))
			miResult = datosBBDD.getTables(null, null, null, null);
			
			//Si en el tercer parametro se pone p% se le dice que busque todas las 
			// tablas que empiezan con p
//			miResult = datosBBDD.getTables(null, null, "p%", null);
			
			
			while(miResult.next())
			{
				System.out.println(miResult.getString("TABLE_NAME"));
			}
			
			//Si queremos obtener las columnas de las tablas son
			//(catalog, schemaPattern, tableNamePattern, columnNamePattern);
			miResult = datosBBDD.getColumns(null, null, "productos", null);
			
			System.out.println();
			System.out.println("La tabla productos contiene las columnas: ");
			
			while(miResult.next())
			{
				System.out.println(miResult.getString("COLUMN_NAME"));
			}
			
			miResult.close();
		}	
		
		catch(Exception e)
		{
			
			System.out.println("ERROR AL CONSULTAR METADATOS!!");
			e.printStackTrace();
		}
		
		finally
		{
			try 
			{
				miConexion.close();
			} 
			
			catch (SQLException e) 
			{
				System.out.println("ERROR AL CERRAR LA BASE");
				e.printStackTrace();
			}
		}
	}
}
