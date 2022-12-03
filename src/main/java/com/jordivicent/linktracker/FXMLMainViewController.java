package com.jordivicent.linktracker;

import com.jordivicent.linktracker.Utils.FileUtils;
import com.jordivicent.linktracker.Utils.MessageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.tools.Utils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;

public class FXMLMainViewController {
    @FXML
    public Label lblTotalLinks;
    @FXML
    public Label lblTotalProcessed;
    @FXML
    public Label lblTotalPages;
    @FXML
    public MenuItem miLoad;
    @FXML
    public MenuItem miExit;
    @FXML
    public MenuItem miStart;
    @FXML
    public MenuItem clearProcess;
    @FXML
    public ListView lvCargarWeb;
    @FXML
    public ListView lvCargarEnlaces;

    public void LoadFile(ActionEvent actionEvent) {
        buscarFichero();
    }//end_LoadFile

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }//end_Exit

    public void Start(ActionEvent actionEvent) {
    }//End_Start

    public void ClearAll(ActionEvent actionEvent) {
        lvCargarEnlaces.getItems().clear();
        lvCargarWeb.getItems().clear();
        lblTotalLinks.setText("0");
        lblTotalProcessed.setText("0");
        lblTotalPages.setText("0");
    }//end_ClearAll

    private void buscarFichero(){
        FileChooser fc = new FileChooser();
        //Crear filtro de extension .txt
        FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Documentos de texto", "*.txt");
        //Assignar Titulo al FileChooser.
        fc.setTitle("Selecciona el archivo: ");
        //Añade el filtro para la extension de .txt
        fc.getExtensionFilters().add(ex1);
        //Obtenemos el directorio home, del usuario actual
        String user = System.getProperty("user.home");
        //Poner Escritorio como directorio Inicial.
        fc.setInitialDirectory(new File(user+"\\OneDrive\\Escritorio"));

        File selectedFile = fc.showOpenDialog(new Stage());

        if (selectedFile != null){
            FileUtils.loadPages(Path.of(selectedFile.getPath()));
        }else{
            MessageUtils.errorFichero();
        }//end_if/else
    }//end_BuscarFichero
}