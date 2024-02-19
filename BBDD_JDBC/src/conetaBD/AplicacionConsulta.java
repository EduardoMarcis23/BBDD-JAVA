package conetaBD;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class AplicacionConsulta 
{
	public static void main(String[] args) 
	{
		
		JFrame mimarco=new MarcoAplicacion();
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);

	}
}

class MarcoAplicacion extends JFrame
{
	
	public MarcoAplicacion()
	{
		
		setTitle ("Consulta BBDD");
		
		setBounds(500,300,400,400);
		
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		edad=new JComboBox();
		
		edad.setEditable(false);
		
		edad.addItem("Todos");
		
		paises=new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(edad);
		
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		
		//Ponemos el boton a la escucha
		botonConsulta.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) 
					{
						//Le decimos que hara el boton
						ejecutaConsulta();
						
					}
			
				});
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		try
		{
			//CONEXION CON BBDD
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//Creamos objeto statement
			Statement sentencia = miConexion.createStatement();
			
			//DEBEMOS CONSTRUIR UNA SENTECNCIA PARA RELLENAR LAS COMBO BOX EDAD
			String consulta = "SELECT DISTINCTROW EDAD FROM DATOSPERSONALES ORDER BY EDAD ASC";
			
			ResultSet rs = sentencia.executeQuery(consulta);
					 
			//Agremos cada edad que existe
			while(rs.next())
			{
				edad.addItem(rs.getString(1));
			}
			
			rs.close();
			
			// CARGA DEL COMBO BOX PAISES
			String consulta2 = "SELECT DISTINCTROW PAIS FROM DATOSPERSONALES ORDER BY EDAD ASC";			
		
			rs = sentencia.executeQuery(consulta2);
			 
			//Agremos cada edad que existe
			while(rs.next())
			{
				paises.addItem(rs.getString(1));
			}
			
			
			
		}
		
		catch(Exception e)
		{
			System.out.println("Ocurrio un error");
			e.printStackTrace();
		}
	}
	
	private void ejecutaConsulta()
	{
		ResultSet rs = null;
		
		try
		{
			//Borramos lo que tiene en caso de que ya tenga otras cosas
			resultado.setText("");
			
			//Almacenaremos cual es la edad y pais que selecciono el usuario
			String pais = (String)paises.getSelectedItem();
			String edadSelect = (String)edad.getSelectedItem();
			
			//Vemos si esta filtrando los resultados el usuario
			//Primero si esta filtrando las paises
			if(!pais.equals("Todos") && edadSelect.equals("Todos"))
			{
				//Se preprara la consulta
				enviaConsultaPais = miConexion.prepareStatement(consultaPais);
				
				enviaConsultaPais.setString(1,pais);
				
				rs = enviaConsultaPais.executeQuery();
			}
			
			//Luego vemos si filtro solo por edad
			else if(pais.equals("Todos") && !edadSelect.equals("Todos"))
			{
				//Se preprara la consulta
				enviaConsultaEdad = miConexion.prepareStatement(consultaEdad);
				
				enviaConsultaEdad.setInt(1,Integer.parseInt(edadSelect));
				
				rs = enviaConsultaEdad.executeQuery();
			}
			
			//Al final vemos si filtro por los 2
			else if(!pais.equals("Todos") && !edadSelect.equals("Todos"))
			{
				//Se preprara la consulta
				enviaConsultaAmbos = miConexion.prepareStatement(consultaAmbos);
				
				//VEMOS QUE SI LLEVA UN int SE PUEDE PROCESAR COMO STRING O PODEMOS HACERLA CON INT
				//COMO EN LA CONDICION PASADA (LA DE LA EDAD)
				enviaConsultaAmbos.setString(1,edadSelect);
				enviaConsultaAmbos.setString(2,pais);
				
				rs = enviaConsultaAmbos.executeQuery();
			}
			
			//Luego si se no se ha filtrado y quiere ver todos
			else
			{
				//Para cuando te pide todos no es necesario realizar una consulta preparada		
				miStatement = miConexion.createStatement();
				
				rs = miStatement.executeQuery("SELECT * FROM DATOSPERSONALES");
			}
			
			while(rs.next())
			{
				resultado.append(rs.getString(1) + " "+ rs.getString(2)+" ");
				resultado.append(rs.getString(3) + " "+ rs.getString(4)+" ");
				resultado.append(rs.getString(6));
				
				//Saltamos de linea para dividir
				resultado.append("\n");
			}
		}
		
		catch(Exception e)
		{
			System.out.println("No se pudo llamar el metodo");
		}
	}
		
	private JComboBox edad;
	private JComboBox paises;
	private JTextArea resultado;	
	
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaEdad;
	private PreparedStatement enviaConsultaAmbos;
	private Statement miStatement;
	
	private final String consultaPais = "SELECT * FROM DATOSPERSONALES WHERE PAIS= ?";
	private final String consultaEdad = "SELECT * FROM DATOSPERSONALES WHERE EDAD= ?";
	private final String consultaAmbos = "SELECT * FROM DATOSPERSONALES WHERE EDAD= ? AND PAIS=?";
	private Connection miConexion;
}
