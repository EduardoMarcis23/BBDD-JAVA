package procAlmacenado;

import java.sql.*;

public class ConsultaClientes 
{

	public static void main(String[] args) 
	{
		try 
		{
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
		
			//Para manejar procedimientos almacenados se usa la clase CallableStatement
			CallableStatement miSentencia = miConexion.prepareCall("{call MUESTRA_CLIENTES}");
			
			ResultSet rs = miSentencia.executeQuery();
			
			while(rs.next())
			{
				//Recordemos que el procedimiento devuleve los de 23 años
				System.out.println(rs.getString(1)+ " "+ rs.getString(2)+ " "+ rs.getString(3)+ " "+ rs.getInt(4));
			}	
			rs.close();
		} 
		
		catch (SQLException e) 
		{
			System.out.println("Ocurrio un error al conec	tar");
			e.printStackTrace();
		}
	}
}
