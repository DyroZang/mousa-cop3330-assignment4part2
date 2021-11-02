/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Gabriel Mousa
 */

package ucf.assignments;

public class ListItem{
    //Object variables
    //File path for internal and external storage
    private Boolean completed = false;
    private String description;
    private String date = "YYYY-MM-DD";

    String[] months = {
            "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"
    };

    public ListItem(String text, String duedate, boolean completed){
        //Constructor function
        //Creates the object vars
        this.completed = completed;
        this.description = text;
        this.date = duedate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public void delete(){
        //Destructor function
        //Free all the memory

    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
