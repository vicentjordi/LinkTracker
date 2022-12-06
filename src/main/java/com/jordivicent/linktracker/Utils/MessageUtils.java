package com.jordivicent.linktracker.Utils;

import javafx.scene.control.Alert;

import java.nio.file.Path;

public class MessageUtils {
    public static void errorFichero(){
        Alert dialog = new Alert(Alert.AlertType.ERROR);

        dialog.setTitle("Fichero");
        dialog.setHeaderText("Sin fichero seleccionado");
        dialog.setContentText("No se ha seleccionado ningún archivo.");
        dialog.showAndWait();
    }//end_errorFichero
    public static void errorFormato(Path path){
        Alert dialog = new Alert(Alert.AlertType.ERROR);

        dialog.setTitle("Error de Formato");
        dialog.setHeaderText("Formato incorrecto");
        dialog.setContentText("El formato del archivo "+path+ " es incorrecto. \n Verifica que siga el siguiente formato(page_name; url).");
        dialog.showAndWait();
    }//end_errorFormato

    public static void fileLoaded(int count){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);

        dialog.setTitle("Archivo Cargado");
        dialog.setHeaderText("Archivo Cargado");
        dialog.setContentText(count+" páginas encontradas.");
        dialog.showAndWait();
    }
    public static void processError(){
        Alert dialog = new Alert(Alert.AlertType.ERROR);

        dialog.setTitle("Error al Procesar");
        dialog.setHeaderText("Error al Procesar");
        dialog.setContentText("No hay ninguna Lista URL cargada.");
        dialog.showAndWait();
    }

    public static void timeOut(){
        Alert dialog = new Alert(Alert.AlertType.ERROR);

        dialog.setTitle("Tiempo Limite");
        dialog.setHeaderText("Se ha llegado al tiempo límite");
        dialog.setContentText("El hilo ha superado el tiempo esperado.");
        dialog.showAndWait();
    }
}
