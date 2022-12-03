package com.jordivicent.linktracker.Utils;

import javafx.scene.control.Alert;

import java.nio.file.Path;

public class MessageUtils {
    public static void errorFichero(){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);

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
}
