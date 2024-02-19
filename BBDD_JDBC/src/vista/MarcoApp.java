package vista;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import controlador.*;

public class MarcoApp extends JFrame
{
	public MarcoApp()
	{
		
		setTitle("Consulta BBDD");
		
		setBounds(500,300,400,400);
		
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		edades=new JComboBox();
		
		edades.setEditable(false);
		
		edades.addItem("Todos");
		
		paises=new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(edades);
		
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		//Ponemos el boton a la escucha, pasando el marco por parametro
		botonConsulta.addActionListener(new ControladorBoton(this));
		
		//Le decimos que este a la escucha de lo que hace el controlador
		addWindowListener(new ControlaCargaMenus(this));
	}
	
	//El primero debe ser public para que pueda acceder el controlador
	public JComboBox edades;
	public JComboBox paises;
	public JTextArea resultado;	
	
}
	
