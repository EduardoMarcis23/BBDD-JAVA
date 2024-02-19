package conetaBD;

import javax.swing.*;
import javax.swing.filechooser.*;

public class Tirar 
{
	public static void main(String[] args)
	{
		Marco miMarco = new Marco();
		miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Clase para mostrar una ventana para buscar un archivo
		 JFileChooser chooser = new JFileChooser();
		 //Con el constructor por defecto
		 //Se parte desde el directorio por defecto: Documents
		 	 
		 //Se construye un filtro
		 //Donde solo se podran ver archivos jpg y gif
		 FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif");
		 
		 chooser.setFileFilter(filter);

		 //El metodo showOpenDialog pide un argumento tipo componente que debe ser el padre
		 int returnVal = chooser.showOpenDialog(miMarco);
		 
		 //Evaluamos si el valor es = a esa cte. Que devuelve 1 valor de yes,ok
		 if(returnVal == JFileChooser.APPROVE_OPTION) 
		 {
			 //Imprimimos 1 mensaje con el elemento selccionado
		     System.out.println("You chose to open this file: " +
		     chooser.getSelectedFile().getName());
		     
		     //El metodo getSelectedFile() devuelve un objeto de tipo File
		     //Con ese objeto ya podemos tener la ruta
		     System.out.println(chooser.getSelectedFile().getAbsolutePath());
		     
		 }
	}
}

class Marco extends JFrame
{
	public Marco()
	{
		setBounds(300,300,300,300);
		setVisible(true);
	}
}
