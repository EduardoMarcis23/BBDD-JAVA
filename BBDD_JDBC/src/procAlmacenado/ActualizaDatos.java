package procAlmacenado;

import java.sql.*;

import javax.swing.JOptionPane;

public class ActualizaDatos 
{

	public static void main(String[] args) 
	{
		String nPais = JOptionPane.showInputDialog("Intoduce el pais");
		int nEdad = Integer.parseInt(JOptionPane.showInputDialog("Introduce Edad"));
		
		try 
		{
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
		
			//Para manejar procedimientos almacenados con parametros se usa:
			CallableStatement miSentencia = miConexion.prepareCall("{call ACTUALIZA_DATOS(?,?)}");
			
			//Debemos recordar el orden de los parametros que recibe
			miSentencia.setInt(1, nEdad);
			miSentencia.setString(2, nPais);
			
			//Ahora no usamos un resultSet ya que solo necesitamos ejecutar
			//por ello usamos la funcion exute() ya que solo devuelve un boolean
			miSentencia.execute();
			
			System.out.println("Actualizado");
		} 
		
		catch (SQLException e) 
		{
			System.out.println("Ocurrio un error al conec	tar");
			e.printStackTrace();
		}

	}

}
