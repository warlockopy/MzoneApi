package mainPackage;

import place.*;

public class ScopeAPI {
	//public static AddPlaces();
	public static void DeletePLaceGroup(String idgroup, String user, String password){
		String listurl  = "https://us.mzoneweb.net/api/v2/placeGroups/" +  idgroup +"/places.json";
		String deleteurl =  "https://us.mzoneweb.net/api/v2/places/";
		String output;
		try {
			output = HttpsClient.HttpsClient(listurl, user, password);
			PlaceGroupList Lugares = PlaceGroupList.Json2Obj(output);
			
			
			for (int i = 0; i < Lugares.getTotalResults();i++){
				Place uno = Lugares.getItemN(i);
				HttpsClient.HttpsClientDelete(deleteurl + uno.getId()+".json", "PYLS","PYLS");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
