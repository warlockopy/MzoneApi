package mainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Boundary;
import de.micromata.opengis.kml.v_2_2_0.ColorMode;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineStyle;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.PolyStyle;
import de.micromata.opengis.kml.v_2_2_0.Polygon;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.gx.Track;
import kmlutils.*;
import apiClasses.TripWithPositions;
import apiClasses.TripsForVehicleGroupWithPositionsSinceDate;
import apiClasses.TripsWithPositions;
import place.Place;
import place.PlaceReader;

public class Main {
	public static void main (String [] args) throws Exception {
		
		//testHugo()
		//createKml();
		//createTest();
		
		TripsForVehicleGroupWithPositionsSinceDate trips;
		
		trips = ScopeAPI.retrieveTripsSinceDate
				("8d20d8f3-5147-4279-ac43-5c086f50e399", "20150804T120000", "PYLS", "PYLS");
		
		
		/*
		String fileName = "target/lagaleria.csv";
		String message = "";
		
		//ScopeAPI.DeletePLaceGroup("c477aea7-83ca-4cdb-ae67-bdc9d081ec0e","administradoraguassanas","admin2015");
		int cnt = 0;
		
		
		PlaceReader reader = new PlaceReader ();
		ArrayList <Place> places = reader.readPlaces(fileName);
		
		if (places.size () > 0){
			
			for (Place place : places){
				place.addPlaceGroup(null, null, "696cc1ad-245d-4715-bdd9-d131e9c971ef");
				message = place.toJson ();
				++cnt;
				echo (message);
				
				HttpsClient.httpsClientAddPlaces ("administradorlagaleria", "admin2015", message);
				echo ("Contador de mensajes: " + cnt);
			}
			
			echo (cnt + " mensajes");
		}
		*/
		
	}
	
	
	public static void echo (Object obj){
		System.out.println (obj);
	}
	
	public static void testHugo() throws FileNotFoundException{
		TripsWithPositions trips = ScopeAPI.retrieveTripsWithPositions("1333006204", "PYLS", "PYLS");
		
		echo ("********");
		echo (trips);
		echo ("********");
		
		final Kml kml = new Kml();
		
		Style style = kml.createAndSetPlacemark().createAndAddStyle();
		
		PolyStyle polystyle = style.createAndSetPolyStyle();
		LineStyle linestyle = style.createAndSetLineStyle();
		
		linestyle.setColor("7f00ffff");
		linestyle.setWidth(4);
		
		polystyle.setColor("7f00ff00");
		
		style.setLineStyle(linestyle);
		style.setPolyStyle(polystyle);
		style.setId("LineadeTrip");
		
		Track track = 
		kml.createAndSetPlacemark()
		   .withName("Prueba").withOpen(Boolean.TRUE)
		   .withStyleUrl("LineadeTrip")
		   .createAndSetTrack();
		
		if (trips.countTrips() > 0){
			TripWithPositions trip = trips.getTripAt(0);
			
			for (int i = 0; i < trip.countPositions (); ++i)
				track.addToCoord(trip.getPositionAt(i));
		}
				
		kml.marshal(new File("HelloKml.kml"));
		
	}
	
	public static void createKml() throws FileNotFoundException{
		
		TripsWithPositions trips = ScopeAPI.retrieveTripsWithPositions("1333006204", "PYLS", "PYLS");
		
		final Kml kml = new Kml();
		Document document = new Document().withName("Prueba"); 
		
		document.createAndAddStyle().withId("Lineas")
		.createAndSetLineStyle().withWidth(3).withColor("7f00ffff");
		Track track = 
				document.createAndAddPlacemark()
				   .withName("Prueba").withOpen(Boolean.TRUE)
				   .withStyleUrl("#Lineas")
				   .createAndSetTrack();
		
		if (trips.countTrips() > 0){
			TripWithPositions trip = trips.getTripAt(0);
			
			for (int i = 0; i < trip.countPositions (); ++i)
				track.addToCoord(trip.getPositionAt(i));
		}
	
		kml.marshal(new File("TripConEstilo.kml"));
		
	}
	
	public static void createTest() throws FileNotFoundException{
		TripsWithPositions trips = ScopeAPI.retrieveTripsWithPositions("1333006204", "PYLS", "PYLS");
		
		final Kml kml = new Kml();
		Document document = kml.createAndSetDocument().withName("test:Recorrido n Style.");
		
		Placemark placemark = kml.createAndSetPlacemark()
				.withName("prueba").withStyleUrl("#Recorrido");
		Track track = placemark.createAndSetTrack()
				.withId("Prueba");
		
		int colorValue = 0;
		String color = Utils.getHexColor(0, (255 - colorValue), 255, true); // KML color format: inverse order of RGB
		Utils.setPolyStyleAndLineStyle(placemark, "FF" + color, ColorMode.NORMAL, 4, "FF000000", ColorMode.NORMAL);
		
		if (trips.countTrips() > 0){
			TripWithPositions trip = trips.getTripAt(0);
			
			for (int i = 0; i < trip.countPositions (); ++i)
				track.addToCoord(trip.getPositionAt(i));
		}
		
		kml.marshal(new File("TripConEstilo.kml"));
		
	}
	
}
