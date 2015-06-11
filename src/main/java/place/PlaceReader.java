package place;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import utilities.Tokenizer;

public class PlaceReader {
	
	public ArrayList <Place> readPlaces (String fileName){
		ArrayList <Place> places = new ArrayList ();
		
		FileReader fReader = null;
		BufferedReader reader = null;
		
		try {
			fReader = new FileReader (fileName);
			reader = new BufferedReader (fReader);
			
			int lineNo = 0;
			String line;
			
			while ((line = reader.readLine()) != null)
				if (++lineNo > 1){
					Tokenizer tok = new Tokenizer (line);
					Place place = new Place ();
					
					String POIName = tok.nextToken(); //Se carga en la Description del Place
					String Description = tok.nextToken();
					double longitude = tok.nextDouble();
					double latitude = tok.nextDouble();
					String address1 = tok.nextToken();
					String address2 = tok.nextToken();
					String city = tok.nextToken();
					String state = tok.nextToken();
					int zipCode = tok.nextInt();
					String country = tok.nextToken();
					double POIBuffer = tok.nextDouble() / 100.0;
					String actionType = tok.nextToken();
					int generateAlert = tok.nextInt();
					
					String geometry = "POINT(" + longitude + " " + latitude + ")";
					
					place.setBufferValue(POIBuffer);
					place.setDescription(POIName);
					place.setGeometry(geometry);
					place.setPlaceType(1);
					
					places.add(place);
					
				}
			
			reader.close ();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return places;
	}
	
}
