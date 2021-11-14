/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Gabriel Mousa
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class app extends Application {

    //Creating starting variables and list items for the application
    private TableView<ListItem> table = new TableView<ListItem>();
    private final ObservableList<ListItem> data = //the main list being used for the app
            FXCollections.observableArrayList(
                    new ListItem("Trash", "2021-10-11", "Yes"),
                    new ListItem("Cleaning", "2021-10-15", "No"),
                    new ListItem("Homework", "2021-11-23", "No")
            );

    private final ObservableList<ListItem> holder = FXCollections.observableArrayList(); // a temporary holder list for filtering items

    final HBox hb = new HBox();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){

        //Setting up the basic scene
        Scene scene = new Scene(new Group());
        stage.setTitle("To-Do List");
        stage.setWidth(850);
        stage.setHeight(500);


        table.setEditable(true);


        //Setting up the first table column for due date
        TableColumn dueDateCol = new TableColumn("Due Date");
        dueDateCol.setMinWidth(100);
        dueDateCol.setCellValueFactory(
                new PropertyValueFactory<ListItem, String>("date"));
        dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateCol.setOnEditCommit(
                new EventHandler<CellEditEvent<ListItem, String>>() {
                    @Override
                    public void handle(CellEditEvent<ListItem, String> t) {
                        ((ListItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                                ).setDate(t.getNewValue());

                    }
                }
        );

        //Setting up the second table column for the completion factor
        TableColumn completedCol = new TableColumn("Completed");
        completedCol.setMinWidth(100);
        completedCol.setCellValueFactory(
                new PropertyValueFactory<ListItem, String>("completed"));
        completedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        completedCol.setOnEditCommit(
                new EventHandler<CellEditEvent<ListItem, String>>() {
                    @Override
                    public void handle(CellEditEvent<ListItem, String> t) {
                        ((ListItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCompleted(t.getNewValue());

                    }
                }
        );

        //Setting up the third table column for the description
        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setMinWidth(600);
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<ListItem, String>("description"));
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(
                new EventHandler<CellEditEvent<ListItem, String>>() {
                    @Override
                    public void handle(CellEditEvent<ListItem, String> t) {
                        ((ListItem) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDescription(t.getNewValue());
                    }
                }
        );

        //Putting all the items in the table
        table.setItems(data);
        table.getColumns().addAll(dueDateCol, completedCol, descriptionCol);

        //Setting up all the text fields for adding a new item in the boxes
        final TextField addDueDate = new TextField();
        addDueDate.setPromptText("Due Date");
        addDueDate.setMaxWidth(dueDateCol.getPrefWidth());
        final TextField addCompleted = new TextField();
        addCompleted.setMaxWidth(completedCol.getPrefWidth());
        addCompleted.setPromptText("Completed");
        final TextField addDescription = new TextField();
        addDescription.setMaxWidth(3000);
        addDescription.setPromptText("Description");

        //Creating the add button along with all the events
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                if(validateDate(addDueDate.getText())) {
                    data.add(new ListItem(
                            addDescription.getText(),
                            addDueDate.getText(),
                            addCompleted.getText()));
                    holder.add(new ListItem(
                            addDescription.getText(),
                            addDueDate.getText(),
                            addCompleted.getText()));
                }
                addDueDate.clear();
                addCompleted.clear();
                addDescription.clear();
            }
        });

        //Creating the delete button along with all the events
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                ListItem myItem = table.getSelectionModel().getSelectedItem();
                table.getItems().remove(myItem);
                holder.clear();
                holder.addAll(data);
            }
        });

        //Creating the clear button along with the event
        final Button clearButton = new Button("Clear");
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                table.getItems().clear();
                holder.clear();
                holder.addAll(data);
            }
        });
//
//        //Creating the invalid pop-up text
//        final Text invalid = new Text("Invalid Entry");
//

        //Adding the choice box for filtering
        ChoiceBox filterBox = new ChoiceBox(FXCollections.observableArrayList(
                "All", "Completed", "Incompleted"
        ));
        filterBox.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue ov,Number value, Number new_value) {
                        //Do whatever needs to happen when something is changed here.
                        int switcher = new_value.intValue();
                        //The code for selecting items in the dropdown menu to filter items
                        switch(switcher){
                            case 0:
                                //"All" selection
                                data.clear();
                                data.addAll(holder);
                                break;
                            case 1:
                                //"Completed" selection
                                data.clear();
                                data.addAll(holder);
                                data.removeIf(item -> !item.isCompleted().equalsIgnoreCase("yes"));
                                break;
                            case 2:
                                //"Incomplete" selection
                                data.clear();
                                data.addAll(holder);
                                data.removeIf(item -> !item.isCompleted().equalsIgnoreCase("no"));
                                break;
                        }

                    }
                }
        );

        //Creates the button for importing items from external storage
        final Button importButton = new Button("Import");
        importButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Put all the items into a .txt using comma separated values on a line by line basis

                try {
                    File file = new File("src/main/resources/ucf.assignments/list.txt");
                    Scanner input = null;
                    input = new Scanner(file);
                    data.clear();
                    holder.clear();
                    while(input.hasNextLine()) {
                        String[] temp = input.nextLine().split(",");
                        data.add(new ListItem(temp[2], temp[0], temp[1]));
                    }
                    holder.addAll(data);
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                }

                }

        });

        //Creates the button for exporting items to external storage
        final Button exportButton = new Button("Export");
        exportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //Export all the items into external storage
                File file = new File("src/main/resources/ucf.assignments/list.txt");
                try {
                    PrintStream fileOut = new PrintStream(file);
                    System.setOut(fileOut);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    System.out.println("Could not find the file");
                }

                //Writing to all the items to file
                for(ListItem item : holder) {
                    System.out.println(item.getDate() + "," + item.isCompleted() + "," + item.getDescription());
                }
                PrintStream consoleStream = new PrintStream(new FileOutputStream(FileDescriptor.out));
            }
        });


        //Adds all the data into a holder variable for filtering the list
        holder.addAll(data);

        //Adding all the add items into the box at the bottom of the screen
        hb.getChildren().addAll(
                addDueDate,
                addCompleted,
                addDescription,
                addButton,
                deleteButton,
                clearButton,
                importButton,
                exportButton,
                filterBox
                );
        hb.setSpacing(3);

        //Adding all of the items into the whole box
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, hb);


        //Grouping together the final scene with everything
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public boolean validateDate(String date){

    if(date.isBlank()){
        return false;
    }
        try{
            for(int i = 0; i < 10; i++){
                switch(i){
                    case 0, 1, 2, 3, 5, 6, 8,9:
                        if(!Character.isDigit(date.charAt(i))){
                            return false;
                        }
                        break;
                    case 4, 7:
                        if(!(date.charAt(i) == '-')){
                            return false;
                        }
                }
            }
        } catch (Exception e) {
            System.out.println("Something didn't work.");
        }

        return true;
    }
}