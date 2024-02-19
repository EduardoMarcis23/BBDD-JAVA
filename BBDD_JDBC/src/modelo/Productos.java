package modelo;

public class Productos
{
	public Productos()
	{
		nif="";
		nombre="";
		apellido="";
		edad="";
		pais="";
		
	}
	
	private String nif, nombre, apellido, edad, pais;

	public String getNif() 
	{
		return nif;
	}

	public void setNif(String nif) 
	{
		this.nif = nif;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getApellido() 
	{
		return apellido;
	}

	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}

	public String getEdad()
	{
		return edad;
	}

	public void setEdad(String edad) 
	{
		this.edad = edad;
	}

	public String getPais()
	{
		return pais;
	}

	public void setPais(String pais) 
	{
		this.pais = pais;
	}
	
}
