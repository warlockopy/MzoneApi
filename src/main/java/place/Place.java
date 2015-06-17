package place;

import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

public class Place {
	
	private String Id;
	private String Description = "null";
	private String UtcLastModified = "null";
	private double BufferValue;
	private String Color = "null";
	private String Geometry = "null";
	private ArrayList <PlaceGroup> PlaceGroups;
	private int PlaceType;
	private int Speed;
	
	public Place (){
		PlaceGroups = new ArrayList ();
	}
	
	public void setDescription (String description) { Description = description; }
	public void setUtcLastModified (String utcLastModified) { UtcLastModified = utcLastModified; }
	public void setBufferValue (double bufferValue) { BufferValue = bufferValue; }
	public void setColor (String color) { Color = color; }
	public void setGeometry (String geometry) { Geometry = geometry; }
	public void setPlaceType (int placeType) { PlaceType = placeType; }
	public void setSpeed (int speed) { Speed = speed; }
	
	public String getId() {return this.Id;}
	
	public void addPlaceGroup (String description, String utcLastModified, String groupId){
		PlaceGroups.add (new PlaceGroup (description, utcLastModified, groupId));
	}
	
	public String toJson (){
		Gson gson = new Gson ();
		String ans = gson.toJson(this).replaceAll ("\"null\"", "null");
		
		return ans;
	}
}
