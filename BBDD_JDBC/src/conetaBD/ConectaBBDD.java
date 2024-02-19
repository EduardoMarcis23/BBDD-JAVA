package conetaBD;

import java.sql.*;

public class ConectaBBDD 
{
	public static void main(String[] args) 
	{
		try
		{
			//Creamos la conexion a la BBDD
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//Creamos objeto statement
			Statement miStatement = miConexion.createStatement();
			
			//Ejecutamos la instruccion SQL
			ResultSet miResulset = miStatement.executeQuery("SELECT * FROM DATOSPERSONALES");
			
			//Recorrer el resultset
			while(miResulset.next())//Le decimos que mientras exista un registro adelante haga...
			{
				System.out.println(miResulset.getString("NIF") +" "+ miResulset.getString("NOMBRE")
				+ " " + miResulset.getString("APELLIDO"));
				
				//Podemos usar getInt, getFloat... Para poder operar con los campos
				
				/* PODEMOS SUSTITUIR LA COLUMNA POR EL NUMERO EMPEZANDO POR 1 AUNQUE NO ES RECOMENDABLE
				 * 
				 * 	System.out.println(miResulset.getString(1) +" "+ miResulset.getString(2)
					+ " " + miResulset.getString(3));
				 * 
				 * 
				 * */
				
				//DE IGUAL FORMA PODEMOS USAR getDate para trabajar con fechas
			}
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}

	}
}
