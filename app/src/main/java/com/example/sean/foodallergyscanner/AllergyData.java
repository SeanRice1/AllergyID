package com.example.sean.foodallergyscanner;


public class AllergyData {


    //temporary for AllergySettings creation
    public static String[] names = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o"};
    public static int[]values = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


    public static AllergyModel[] allergyArr = new AllergyModel[15];

    public static void createArr(){

        for(int x = 0;x<names.length;x++)
            allergyArr[x]= new AllergyModel(names[x],values[x]);

    }

}
