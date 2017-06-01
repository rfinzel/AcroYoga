package objects;

public class VeranstaltungObj {
	private int id;
	private String name;
	
	public VeranstaltungObj()
	{
		
	}
	
	public VeranstaltungObj(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

}
