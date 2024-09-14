package edu.qc.seclass.grocery_list_team6;

public class Grocery {
    private int id;

    private String name;
    private String qty;
    private String dateAdded;

    private boolean checked;

    public Grocery(String name, String qty, String dateAdded, int id, String user) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.dateAdded = dateAdded;
        this.checked = false;
    }


    public Grocery() {
        this.checked = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public boolean getChecked(){return checked;}

    public void setChecked(boolean status){this.checked = status;}
    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Grocery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty='" + qty + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }
}
