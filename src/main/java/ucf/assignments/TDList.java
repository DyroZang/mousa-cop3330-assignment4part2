/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Gabriel Mousa
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TDList {
    private String title;
    ObservableList<ListItem> list = FXCollections.observableArrayList(
            new ListItem("Words", "2001-12-20", false)
    );


    public TDList(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void delItem(String itemDesc){
        //Delete the item with the description given using ListItem destructor

    }

    public void addItem(String itemDesc, String date, Boolean completed){
        //Create a new ListItem with given parameters and ListItem Constructor and add it to the current list
    }

    public void editDesc(ListItem item, String newText){
        //Replace item's description with newText

    }

    public void editDate(ListItem item, String newDate){
        //Replace item's date with newDate
    }

    public void editComplete(ListItem item, Boolean newCompleted){
        //Replace item's completed with newCompleted
    }

    public void saveList(String filepath){
        //Saves all of the listItems and this list title into a CSV

        //Loop through each of the list items and store into CSV file at given filepath
    }

    public void loadList(String filepath){
        //Loads one or multiple lists from the filepath and imports all the items in
    }

}
