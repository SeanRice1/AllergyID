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
    public boolean isChecked(){
        if(checkedValue == 0)
            return false;
        else
            return true;
    }
    public void switchedCheckedValue(){
        if(checkedValue == 0)
            this.checkedValue = 1;
        else
            this.checkedValue = 0;

    }

}
