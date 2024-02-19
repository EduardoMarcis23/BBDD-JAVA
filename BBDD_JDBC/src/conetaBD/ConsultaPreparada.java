package conetaBD;

import java.sql.*;
public class ConsultaPreparada
{

	public static void main(String[] args) 
	{
		try
		{
			//Creamos la conexion a la BBDD
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//Creamos nuestra consulta preparada
			PreparedStatement miSentencia = miConexion.prepareStatement("SELECT * FROM DATOSPERSONALES WHERE EDAD=? AND NOMBRE LIKE ?");
			
			//Establecemos los parametros de la consulta
			miSentencia.setInt(1, 23); //Nos pide el numero de parametro en la consulta y el parametro
			miSentencia.setString(2, "R%");
			
			//Ejecutamos la consulta
			ResultSet rs = miSentencia.executeQuery();
			
			//Recorremos la consulta
			while(rs.next())
			{
				System.out.println(rs.getString(1)+ " "+ rs.getString(2)+ " "+ rs.getString(3)+ " "+ rs.getString(4));
			}
			
			//Liberamos la memoria
			rs.close();
			
			//PODEMOS REUTILIZAR CONSULTA
			System.out.println();
			System.out.println("SEGUNDA CONSULTA");

			
			miSentencia.setInt(1, 21); //Nos pide el numero de parametro en la consulta y el parametro
			miSentencia.setString(2, "V%");
			
			rs = miSentencia.executeQuery();
			
			//Recorremos la consulta
			while(rs.next())
			{
				System.out.println(rs.getString(1)+ " "+ rs.getString(2)+ " "+ rs.getString(3)+ " "+ rs.getString(4));
			}
			
			//Liberamos la memoria
			rs.close();
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}

	}

}
