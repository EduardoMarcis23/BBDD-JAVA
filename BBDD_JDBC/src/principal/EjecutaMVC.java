package principal;

import vista.*;
import javax.swing.*;

public class EjecutaMVC 
{
	public static void main(String[] args)
	{
		//Como esta en diferente paquete la clase usada, debemos importar el paquete
		MarcoApp miMarco = new MarcoApp();
		
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		miMarco.setVisible(true);
	}
	
}
