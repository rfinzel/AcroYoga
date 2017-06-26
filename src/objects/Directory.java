package objects;

import java.util.List;

public class Directory {
	private String name;
	private String[] files;
	
	public Directory(String name) {
		super();
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String[] getFiles()
	{
		return files;
	}
	
	public void setList(String[] files)
	{
		this.files = files;
	}
}
