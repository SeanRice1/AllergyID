package info.seanrice.allergyID.Models;

import java.io.IOException;
import java.util.ArrayList;

import info.seanrice.allergyID.Data.RemoteData;
import info.seanrice.allergyID.Data.RemoteDataI;
import info.seanrice.allergyID.Data.USDAndbnoPOJO.USDAndbno;
import info.seanrice.allergyID.Data.USDApojo.USDApojoFull;

public class FoodResults implements FoodResultsI{

    private RemoteDataI remoteData;
    private AllergyDataI allergyData;

    public FoodResults(AllergyDataI allergyData){
        remoteData = new RemoteData();
        this.allergyData = allergyData;
    }
    //Get allergy information with current allergies in the AllergyData. Returns an array containing
    //An array of ingredients the user is allergic to (index 0) and an array of ingredients
    //the item may contain that the user is allergic to (index 1)
    @Override
    public ArrayList<ArrayList<String>> getFoodInfo(String upc) throws IOException {
        //Get the ndbno
        USDAndbno ndbno = remoteData.getUSDAndbno(upc);
        //Get the full POJO using the ndbno
        USDApojoFull usdApojoFull =
                remoteData.getUSDApojoFull(ndbno.getList().getItem()[0].getNdbno());
        String[] ingredients =
                usdApojoFull.getReport().getFood().getIng().getDesc().split(",");

        ArrayList<String> contains  = new ArrayList<>();

        ArrayList<String> userAllergies = allergyData.getAllergies();

        for(int j = 0; j <userAllergies.size(); j++){
            for(int i = 0; i < ingredients.length; i++){
                if(ingredients[i].equals(userAllergies.get(j)))
                    contains.add(ingredients[i]);
            }
        }


        //TODO: Need to create may contain look

        ArrayList<ArrayList<String>> finalResult = new ArrayList<>();
        finalResult.add(contains);
        //Temporary
        finalResult.add(new ArrayList<String>());
        return finalResult;
    }
}
