package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListItemTest {

    @Test
    void delete() {
        //Test deleting a list
        ListItem x = new ListItem("This is testing", "1214-23-04", "Yes");
        x.delete();
    }

    @Test
    void setCompleted() {
        //Test marking a list item for incomplete or complete
        ListItem x = new ListItem("This is testing", "1214-23-04", "Yes");
        x.setCompleted("No");
    }

    @Test
    void setDescription() {
        //Test the editing of the description
        ListItem x = new ListItem("This is testing", "1214-23-04", "Yes");
        x.setDescription("This is another test");
    }

    @Test
    void setDate() {
        //Test the editing of the date
        ListItem x = new ListItem("This is testing", "1214-23-04", "Yes");

    }
}