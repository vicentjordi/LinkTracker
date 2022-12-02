module com.jordivicent.linktracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.jordivicent.linktracker to javafx.fxml;
    exports com.jordivicent.linktracker;
    //opens com.jordivicent.linktracker.model to javafx.base;
}