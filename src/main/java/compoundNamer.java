import java.util.*;
import org.json.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
public class compoundNamer {
	
	public static void run()
	{
		String first = "C:\\\\Users\\\\uravt\\\\Downloads\\\\BinaryCompoundNamer\\\\src\\\\main\\\\java\\\\PeriodicTableJSON.json";
		try {
			String contents = new String(Files.readAllBytes(Paths.get(first)));
			JSONObject o = new JSONObject(contents);
			JSONArray elements = o.getJSONArray("elements");
			JSONObject temp;
			Element[] elementsArray = new Element[119];
			Map<String, Element> hm = new HashMap<String, Element>();
			for(int x = 0; x < elements.length(); x++)
			{
				temp = elements.getJSONObject(x);
				hm.put(temp.getString("symbol"), new Element(temp.getString("common_name"), temp.getString("name"), temp.getString("symbol"), temp.getInt("xpos"), temp.getString("category")));
			}
			
			String[] prefixes = {"mono", "di", "tri", "tetra", "penta", "hexa", "hepta", "octo", "nona", "deca"};
			String[] numerals = {"(I)", "(II)", "(III)", "(IV)", "(V)", "(VI)", "(VII)", "(VIII)"};
			Scanner s = new Scanner(System.in);
			System.out.println("Format as such: H1F1");
			System.out.print("Enter Compound Name: ");
			String raw = s.nextLine();
			
			String firstElement, secondElement;
			int firstVal, secondVal;
			
			String rawVal = raw.replaceAll("[A-Za-z\\s]+", " ").trim();
			String[] rawVals = rawVal.split(" ");
			firstVal = Integer.parseInt(rawVals[0]);
			secondVal = Integer.parseInt(rawVals[1]);
			String rawElement = raw.replaceAll("\\P{L}", " ").trim();
			firstElement = rawElement.split(" ")[0];
			if(firstVal > 9)
			{
				secondElement = rawElement.split("  ")[1];
			}else
			{
				secondElement = rawElement.split(" ")[1];
			}
			
			String vowels = "aeiou";
			if(hm.get(firstElement).getGroup().contains("nonmetal"))
			{
				String ans = "";
				if(firstVal > 1)
				{
					ans += prefixes[firstVal - 1];
				}
				ans += hm.get(firstElement).getName().toLowerCase();
				ans += " ";
				ans += prefixes[secondVal - 1];
				ans += hm.get(secondElement).getCommonName();
				System.out.println(ans);
			}
			else if(hm.get(firstElement).getGroup().equals("alkali metal") || hm.get(firstElement).getGroup().equals("alkaline earth metal"))
			{
				String ans = "";
				ans += hm.get(firstElement).getName().toLowerCase();
				ans += " ";
				ans += hm.get(secondElement).getCommonName();
				System.out.println(ans);				
			}
			else if(hm.get(firstElement).getGroup().contains("transition metal"))
			{
				String ans = "";
				ans += hm.get(firstElement).getName().toLowerCase();
				int charge = secondVal;
				while(firstVal < hm.get(secondElement).getCharge())
				{
					secondVal += charge;
					firstVal++;
				}
				while(firstVal > hm.get(secondElement).getCharge())
				{
					secondVal -= charge;
					firstVal--;
				}
				ans += numerals[secondVal -1];
				ans += " ";
				ans += hm.get(secondElement).getCommonName();
				System.out.println(ans);
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
		}	
	}
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		boolean again = true;
		while(again == true)
		{
			run();
			System.out.print("Again?(y/n): ");
			if(s.nextLine().toLowerCase().equals("n"))
			{
				again = false;
			}
		}
	}
		
}
