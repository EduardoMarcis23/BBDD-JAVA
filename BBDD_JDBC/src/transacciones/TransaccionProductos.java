package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class TransaccionProductos 
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
			
		    String instruccionSql_1="DELETE FROM PRODUCTOS WHERE PAISORIGEN = 'ITALIA'";	    
		    String instruccionSql_2="DELETE FROM PRODUCTOS WHERE = PRECIO>300'";
		    String instruccionSql_3="UPDATE PRODUCTO SET PRECIO = PRECIO*1.15";
		    
		    boolean ejecutar = ejecutarTransaccion();
		    
		    if(ejecutar==true)
		    {
		    	miStatement.executeUpdate(instruccionSql_1);
			    miStatement.executeUpdate(instruccionSql_2);
			    miStatement.executeUpdate(instruccionSql_3);
			    
			    System.out.println("Datos ACTUALIZADOS correctamente");
			    
			    miConexion.commit();
		    }
		    
		    else
		    {
		    	System.out.println("No se realizo cambios");
		    }

		    //CON ESTO GARANZIAMOS QUE SE HAGAN LAS 2 TRANSACCIONES O NINGUNA
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
	
	//Debe ser estatico ya que como se llama desde el main y este es estatico.
	static boolean ejecutarTransaccion()
	{
		String ejecucion = JOptionPane.showInputDialog("Ejecutamos?");
		
		if(ejecucion.equals("SI"))
		{
			return true;
		}
		
		else 
			return false;
	}
}
