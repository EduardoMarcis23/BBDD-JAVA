package transacciones;

import java.sql.*;

public class InsertaClientesPedido 	
{

	public static void main(String[] args)
	{
		Connection miConexion=null;
		
		try
		{						
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");				
			
			//Para que trate nusestras instrucciones como 1 solo bloque se hace:
			miConexion.setAutoCommit(false);
			
			Statement miStatement =miConexion.createStatement();
			
		    String instruccionSql_1="INSERT INTO CLIENTES (CÓDIGOCLIENTE, EMPRESA, DIRECCIÓN) VALUES ('CT84', 'EJEMPLO', 'P BOTANICO')";
			    
		    miStatement.executeUpdate(instruccionSql_1);
			    
		    //Si modificamos esta instruccion para que falle. La anterior si se realiza
		    //Para poder realizar las 2 o ninguna se debe de hacer en una transaccion
		    String instruccionSql_2="INSERT INTO PEDIDOS (NÚMERODEPEDIDO, CÓDIGOCLIENTE,FECHADEPEDIDO) VALUES('82', 'CT84', '11/03/2000')";
			    
		    miStatement.executeUpdate(instruccionSql_2);
		    
		    //Le decimos que sera hasta aqui donde se ejecutara
		    miConexion.commit();
		    
		    //CON ESTO GARANZIAMOS QUE SE HAGAN LAS 2 TRANSACCIONES O NINGUNA
		    				    
		    System.out.println("Datos INSERTADOS correctamente");
				
		}
		
		catch(Exception e)
		{
			//EN CASO DE QUE HAYYA UN ERROR HACEMOS ROLLBACK
			try
			{
				miConexion.rollback();
			} 
			
			catch (SQLException e1)
			{
				System.out.println("Se hizo Rollback");
				e1.printStackTrace();
			}
			
			System.out.println("ERROR EN LA INSERCIÓN DE DATOS!!");
			e.printStackTrace();
				
		}

	}

}