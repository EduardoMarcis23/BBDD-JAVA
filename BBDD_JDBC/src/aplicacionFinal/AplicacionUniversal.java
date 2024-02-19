package aplicacionFinal;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class AplicacionUniversal 
{

	public static void main(String[] args)
	{
		MarcoBBDD mimarco=new MarcoBBDD();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);
	}

}

class MarcoBBDD extends JFrame
{
	public MarcoBBDD()
	{
		setBounds(300,300,700,700);
		
		LaminaBBDD milamina=new LaminaBBDD();
		
		add(milamina);
		
	}		
}

class LaminaBBDD extends JPanel
{
	public LaminaBBDD()
	{
		setLayout(new BorderLayout());
		
		comboTablas=new JComboBox();
		
		areaInformacion=new JTextArea();
		
		add(areaInformacion,BorderLayout.CENTER);		
		
		add(comboTablas, BorderLayout.NORTH);
		
		conectarBBDD();
		
		obtenerTablas();
		
		comboTablas.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				//Guardamos el elemento seleccionado
				String nombreTabla= (String)comboTablas.getSelectedItem();
				
				mostrarInfoTabla(nombreTabla);
				
			}
				
		});
	}
	
	public void conectarBBDD()
	{
		
		miConexion = null;
		
		//Se crea para almacenar los 3 datos que se piden para la conexion con la BBDD 
		String datos[] = new String[3];
		
		try
		{	
			entrada = new FileReader("C:\\Users\\mazac\\Desktop\\datosConfig.txt");
			
		}
		
		catch(IOException eio)
		{
			JOptionPane.showMessageDialog(this,"Ocurrio un error con el archivo. Verifica su existencia");
			
			//EN CASO DE NO ENCONTRAR EL ARCHIVO, NOS DEJARA BUSCARLO
			
			JFileChooser chooser = new JFileChooser();
			 //Con el constructor por defecto
			 //Se parte desde el directorio por defecto: Documents
			 	 
			 //Se construye un filtro
			 //Donde solo se podran ver archivos jpg y gif
			 FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Archivos txt", "txt");
			 
			 chooser.setFileFilter(filter);

			 //como se llama en la lamina el metodo. se pone this para indicar que es el padre
			 int returnVal = chooser.showOpenDialog(this);
			 
			 //Evaluamos si el valor es = a esa cte. Que devuelve 1 valor de yes,ok
			 if(returnVal == JFileChooser.APPROVE_OPTION) 
			 {
				 try 
				 {
					//Guaramos la ruta al archivo que se escojio
					entrada = new FileReader(chooser.getSelectedFile().getAbsolutePath());
				 } 
				 
				 catch (FileNotFoundException e)
				 {
					System.out.println("ERROR AL CARGAR EL ARCHIVO");
					e.printStackTrace();
				}
			 }
		}
		
		try
		{
			BufferedReader miBuffer = new BufferedReader(entrada);
			
			for(int i=0; i<=2; i++)
			{
				//Leemos cada linea
				datos[i] = miBuffer.readLine();
			}
			
			miConexion=DriverManager.getConnection(datos[0], datos[1], datos[2]);
			
			entrada.close();
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR AL CONEXTAR");
			e.printStackTrace();
		}
		
	}
	
	//METODO PARA CARGAR EL JComboBox con las tablas
	public void obtenerTablas()
	{
		ResultSet miResult = null;
		
		try 
		{
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			//Almacenos todas las tablas
			miResult = datosBBDD.getTables(null, null, null, null);
			
			while(miResult.next())
			{
				//Se cargan el menu con las tablas existentes
				comboTablas.addItem(miResult.getString("TABLE_NAME"));
			}
				
		} 
		
		catch (SQLException e) 
		{
			System.out.println("ERROR AL OBTENER METADATOS");
			e.printStackTrace();
		}
	}
	
	public void mostrarInfoTabla(String tabla)
	{
		//Necitamos crear campos dinamicos ya que las tablas suelen
		//tener numero de columnas distinto
		ArrayList<String> campos = new ArrayList<String>();
		
		String consulta = "SELECT * FROM " +tabla;
		
		try
		{
			//Debemos limpiar el area cada vez que se selcciona otro campo
			areaInformacion.setText("");
			
			Statement miStatement = miConexion.createStatement();
			
			ResultSet miRS = miStatement.executeQuery(consulta);
			
			//Debemos acceder a los metaDatos de la tabla
			//para saber el numero de columnas
			ResultSetMetaData rsBBDD = miRS.getMetaData();
			
			//Obtenemos el numero de colmnas con el metodo: getColumnCount();
			for(int i=1; i<=rsBBDD.getColumnCount(); i++)
			{
				//Obtenemos los nombres de las columnas
				campos.add(rsBBDD.getColumnLabel(i));
			}
			
			//Luego debemos recorrer cada registo 
			while(miRS.next())
			{
				for(String nombreCampo: campos)
				{
					//y la columna de cada tabla
					areaInformacion.append(miRS.getString(nombreCampo));
				}
				
				//Salto de linea para separa cada registro
				areaInformacion.append("\n");
			}
		}
		
		catch(Exception e)
		{
			System.out.println("ERROR AL CONSULTAR TABLA");
			e.printStackTrace();
		}
	}
	
	private JComboBox comboTablas;
	private JTextArea areaInformacion;
	private Connection miConexion;
	private FileReader entrada;
}