
public class Element {
	
	private String name;
	private String cname;
	private String symbol;
	private int charge;
	private String group;
	
	public Element(String c, String n, String s, int xp, String g)
	{
		cname = c;
		name = n;
		symbol = s;
		group = g;
		if(group.equals("transition metal") && !(symbol.equals("Ag")) && !(symbol.equals("Zn")))
		{
			charge = 999;
		}else if(symbol.equals("Ag"))
		{
			charge = 1;
		}else if(symbol.equals("Zn"))
		{
			charge = 2;
		}else
		{
			if(xp < 3)
			{
				charge = xp;
			}else
			{
				charge = 8 - (xp - 10);
			}
		}
	}
	
	public String getGroup() {return group;}
	
	public String getName() {return name;}
	
	public String getCommonName() {return cname;}
	
	public int getCharge() {return charge;}
	
	public String toString()
	{
		return "Symbol: " + symbol + ", ChargeVal: " + charge + ", Category: " + group; 
	}
	
}
