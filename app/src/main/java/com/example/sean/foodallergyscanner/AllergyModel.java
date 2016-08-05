package com.example.sean.foodallergyscanner;


public class AllergyModel {

    private String name;
    // 0 = not checked, 1 = checked
    private int checkedValue;

    public AllergyModel(String name, int value){
        this.name=name;
        checkedValue=value;
    }

    public String getName(){
        return name;
    }
    public void setChecked(){
        if(checkedValue == 0)
            checkedValue = 1;
        else
            checkedValue = 0;
    }
    
}
