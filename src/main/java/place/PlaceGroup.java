package place;

import java.util.ArrayList;

public class PlaceGroup {
	private String Description;
	private String UtcLastModified;
	private String Id;
	
	public PlaceGroup (String description, String utcLastModified){
		Description = description;
		UtcLastModified = utcLastModified;
		
		Id = "c477aea7-83ca-4cdb-ae67-bdc9d081ec0e";
		/*final String tabla = "0123456789abcdef";
		
		
		for (int i = 0; i < 32; ++i){
			
			if (i == 8 || i == 12 || i == 16 || i == 20) Id += "-";
			
			int pos = (int) (Math.random() * 100000) % 16;
			Id += tabla.substring(pos, pos + 1);
		}*/
	}
}
