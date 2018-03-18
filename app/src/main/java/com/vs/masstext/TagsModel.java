package com.vs.masstext;

/**
 * Created by mint on 2/10/18.
 */

public class TagsModel {
    String name;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    TagsModel(String name, int value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }
    public void changeValue(){
        if (this.value == 1){
            this.value = 0;
        } else {
            this.value = 1;
        }
    }

}