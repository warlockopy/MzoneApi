package mainPackage;

import apiClasses.TripsForVehicleGroupWithPositionsSinceDate;
import apiClasses.TripsWithPositions;

import com.google.gson.Gson;

import place.*;

public class ScopeAPI {
	//public static AddPlaces();
	public static void deletePLaceGroup(String idgroup, String user, String password){
		String listurl  = "https://us.mzoneweb.net/api/v2/placeGroups/" +  idgroup + "/places.json";
		String deleteurl =  "https://us.mzoneweb.net/api/v2/places/";
		String output;
		
		try {
			output = HttpsClient.HttpsClient(listurl, user, password);
			PlaceGroupList Lugares = PlaceGroupList.Json2Obj(output);
			
			
			for (int i = 0; i < Lugares.getTotalResults();i++){
				Place uno = Lugares.getItemN(i);
				HttpsClient.httpsClientDelete(deleteurl + uno.getId() + ".json", "PYLS","PYLS");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TripsWithPositions retrieveTripsWithPositions (final String unitId, final String user, final String pass){
		String response = "";
		String url = "https://us.mzoneweb.net/api/v2/units/" + unitId + "/tripswithpositions.json?ps=1";
		
		try {
			response = HttpsClient.httpsClientGet (url, user, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println (response);
		
		return new Gson ().fromJson(response, TripsWithPositions.class);
	}
	
	public static TripsForVehicleGroupWithPositionsSinceDate 
	retrieveTripsSinceDate (final String vehicleGroupId, final String date, final String user, final String pass){
		String response = "";
		String url = "https://us.mzoneweb.net/api/v2/vehiclegroups/"
		+ vehicleGroupId + "/tripsupdatedsince/" 
		+ date + ".json?ps=1&i=E&i=P&i=PA&i=T&i=S&i=R&i=D&i=A";
		
		try {
			response = HttpsClient.httpsClientGet (url, user, pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println (response);
		
		return new Gson ().fromJson(response, TripsForVehicleGroupWithPositionsSinceDate.class);
	}
}
