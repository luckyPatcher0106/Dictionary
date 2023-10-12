package com.example.dictionarydemo;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class AppController  implements Initializable  {

    public ImageView starIcon;
    public ImageView listIcon;
    public ImageView volumeIcon;
    public ImageView swapIcon;
    public ImageView searchIcon;
    public ImageView clockIcon;
    public TextField inputSearch;


    @FXML
    protected void onSearchButtonClick() {
        System.out.println(inputSearch.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image searchIconSource = new Image(String.valueOf(new File("src/main/java/com/example/dictionarydemo/pic/search_icon.png").toURI()));
        Image clockIconSource = new Image(String.valueOf(new File("src/main/java/com/example/dictionarydemo/pic/clock_icon.png").toURI()));
        Image listIconSource = new Image(String.valueOf(new File("src/main/java/com/example/dictionarydemo/pic/list_icon.png").toURI()));
        Image starIconSource = new Image(String.valueOf(new File("src/main/java/com/example/dictionarydemo/pic/star_icon.png").toURI()));
        Image swapIconSource = new Image(String.valueOf(new File("src/main/java/com/example/dictionarydemo/pic/swap_icon.png").toURI()));
        Image volumeIconSource = new Image(String.valueOf(new File("src/main/java/com/example/dictionarydemo/pic/volume_icon.png").toURI()));
        searchIcon.setImage(searchIconSource);
        clockIcon.setImage(clockIconSource);
        listIcon.setImage(listIconSource);
        starIcon.setImage(starIconSource);
        swapIcon.setImage(swapIconSource);
        volumeIcon.setImage(volumeIconSource);
    }
}