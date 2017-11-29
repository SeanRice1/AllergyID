package info.seanrice.allergyID.Models;

import java.io.IOException;
import java.util.ArrayList;

public interface FoodResultsI {
    //Get allergy information with current allergies in the AllergyData. Returns an array containing
    //An array of ingredients the user is allergic to (index 0) and an array of ingredients
    //the item may contain that the user is allergic to (index 1)
    ArrayList<ArrayList<String>> getFoodInfo(String upc) throws IOException;
}
