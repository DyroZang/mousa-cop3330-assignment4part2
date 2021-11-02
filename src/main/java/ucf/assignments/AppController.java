/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Gabriel Mousa
 */


package ucf.assignments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    //Class variables
    int filter; //0 = no filter, 1 = display only incomplete, 2= display only complete

    public void saveAll(){
        //Saves all the items in all the lists into CSV's into external storage
        //Use TDList saveList
    }

    public void newList(String title){
        //Creates a new to-do list to display using the TDList class constructor
        //Uses given title

    }

    @FXML
    private TableView<ListItem> table;

    @FXML
    private TableColumn<ListItem, Boolean> completed;

    @FXML
    private TableColumn<ListItem, String> description;

    @FXML
    private TableColumn<ListItem, String> date;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TDList list = new TDList("mainList");
        completed.setCellValueFactory(new PropertyValueFactory<ListItem, Boolean>("isState"));
        description.setCellValueFactory(new PropertyValueFactory<ListItem, String>("getDescription"));
        date.setCellValueFactory(new PropertyValueFactory<ListItem, String>("getDate"));
        table.setItems(list.list);
    }
}
