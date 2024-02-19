package conetaBD;

import java.sql.*;

public class ModificaBBDD 
{

	public static void main(String[] args) 
	{
		try
		{
			//Creamos la conexion a la BBDD
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//Creamos objeto statement
			Statement miStatement = miConexion.createStatement();
			
			//Consulta INSERT
/*			String instruccionSql = "INSERT INTO DATOSPERSONALES (NIF,NOMBRE,APELLIDO,EDAD) "
					+ "VALUES('JFGG34F53F','FELIX','LOCO',6)";
*/			
			//CONSULTA UPDATE
//			String instruccionSql = "UPDATE DATOSPERSONALES SET APELLIDO='GALLARDO' WHERE NIF='JFGG34F53F'";
			
			//SQL DELETE
			String instruccionSql = "DELETE FROM DATOSPERSONALES WHERE NIF='JFGG34F53F'";
			
			//Ejecutamos la INSERT, UPDATE O DELETE
			miStatement.executeUpdate(instruccionSql);
			
			System.out.println("Datos Actualizados");
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}

	}

}
