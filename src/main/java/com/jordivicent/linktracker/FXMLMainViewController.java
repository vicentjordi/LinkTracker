package com.jordivicent.linktracker;

import com.jordivicent.linktracker.Model.WebPage;
import com.jordivicent.linktracker.Utils.FileUtils;
import com.jordivicent.linktracker.Utils.LinkReader;
import com.jordivicent.linktracker.Utils.MessageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
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
    public ListView<String> lvCargarEnlaces;
    List<WebPage> webPages;

    public void LoadFile(ActionEvent actionEvent) {
        buscarFichero();
    }//end_LoadFile
    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }//end_Exit
    public void Start(ActionEvent actionEvent) throws MalformedURLException {

        //Crear lista donde almacenar hilos
        List<Callable<WebPage>> listURL = new ArrayList<>();

        webPages.forEach(webPage -> {
            listURL.add(getURLcallable(webPage));
        });

        //Iniciar executor
        ExecutorService executor = Executors.newWorkStealingPool();
        //Guardar lista de enlaces.
        List<Future<WebPage>> enlacesURL;

        try{
            //Ejecutar hilo
            enlacesURL = executor.invokeAll(listURL);

            executor.shutdown();

            System.out.println(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
            enlacesURL.forEach(future -> {
                try {
                    lvCargarWeb.getItems().add(future.get().getNombreWeb());
                    lblTotalProcessed.setText(Integer.toString(enlacesURL.size()));
                } catch (InterruptedException | ExecutionException e) {
                    throw new IllegalStateException(e);
                }
            });
        }catch(Exception e){
            MessageUtils.processError();
        }
    }//End_Start
    public void selectPagWeb(){
        //Cuando se seleccione un item compara en la lista y llena lvCargarEnlaces
        try{
            //Vacia el listView lvCargarWeb antes de mostrar los nuevos datos
            lvCargarEnlaces.getItems().clear();

            String titulo = lvCargarWeb.getSelectionModel().getSelectedItem().toString();
            for(WebPage page : webPages){
                if(titulo == page.getNombreWeb().toString()){
                    //Recorrer la lista con los enlaces y añadir a lvCargarEnlaces
                    page.getEnlaces().forEach(enlace -> {
                        try{
                            lvCargarEnlaces.getItems().add(enlace.toString());
                        }catch (Exception e){}
                    });
                }//end_if
            }//end_for
        }catch (Exception e){}

    }
    public static Callable<WebPage> getURLcallable(WebPage webPage){
        return () -> {
            try {
                System.out.println("Callable webPage");
                //Guardar todos los enlaces de cada página
                webPage.setEnlaces(LinkReader.getLinks(webPage.getUrlWeb()));
                return webPage;
            } catch (Exception e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
    }

    public void ClearAll(ActionEvent actionEvent) {
        //Vaciar listView y reiniciar Contadores
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