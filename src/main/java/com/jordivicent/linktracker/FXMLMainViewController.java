package com.jordivicent.linktracker;

import com.jordivicent.linktracker.Model.WebPage;
import com.jordivicent.linktracker.Utils.FileUtils;
import com.jordivicent.linktracker.Utils.MessageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

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
    public ListView<String> lvCargarWeb;
    @FXML
    public ListView lvCargarEnlaces;

    List<WebPage> webPages;
    public void LoadFile(ActionEvent actionEvent) {
        buscarFichero();
    }//end_LoadFile

    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }//end_Exit

    public void Start(ActionEvent actionEvent) throws MalformedURLException {
        //Crear lista donde almacenar hilos
        List<Callable<WebPage>> listURL = Arrays.asList();
        //Crear hilos por cada enlace en fichero
        for (WebPage page : webPages){
           // callURL(new URL(page.getUrlWeb()));
        }
        //Iniciar executor
        ExecutorService executor = Executors.newWorkStealingPool();
        //Guardar lista de enlaces.
        List<Future<WebPage>> enlacesURL;

        try{
            //Ejecutar hilo
            for(int i=0; i<=listURL.size();i++){
                //enlacesURL = executor.submit(listURL);
            }
            executor.shutdown();

        }catch(Exception e){
            MessageUtils.processError();
            //webPages.removeAll(webPages);
        }
    }//End_Start

    public void ClearAll(ActionEvent actionEvent) {
        lvCargarEnlaces.getItems().clear();
        lvCargarWeb.getItems().clear();
        webPages.removeAll(webPages);
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
           webPages = FileUtils.loadPages(Path.of(selectedFile.getPath()));
            MessageUtils.fileLoaded(webPages.size());

            lblTotalPages.setText(Integer.toString(webPages.size()));
        }else{
            MessageUtils.errorFichero();
        }//end_if/else

    }//end_BuscarFichero

}