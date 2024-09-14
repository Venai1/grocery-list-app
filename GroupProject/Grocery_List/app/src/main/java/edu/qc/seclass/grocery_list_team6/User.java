package edu.qc.seclass.grocery_list_team6;

import java.util.ArrayList;

public class User {

    private String name;
    public ArrayList<Grocery> groceryList = new ArrayList<>();


    public User(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name){
        this.name = name;
    }

    public void emptyList(){
        this.groceryList.clear();
    }
    public String getName(){return name;}

    public void addToList(Grocery item){
        groceryList.add(item);
    }

    public ArrayList<Grocery> getGroceryList(){return groceryList;}
}
