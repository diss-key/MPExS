package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ResultCtrl {
    @FXML
    GridPane GridU;
    @FXML
    TableView<Mobile> tab;
    @FXML
    TableColumn<Mobile, String> brand_col, model_col, size_col, battery_col, weight_col, price_col;
    @FXML
    Button BackButton;

    public void initialize() throws FileNotFoundException {
        brand_col.setCellValueFactory(new PropertyValueFactory<Mobile, String>("brand"));
        model_col.setCellValueFactory(new PropertyValueFactory<Mobile, String>("model"));
        size_col.setCellValueFactory(new PropertyValueFactory<Mobile, String>("size"));
        battery_col.setCellValueFactory(new PropertyValueFactory<Mobile, String>("battery"));
        weight_col.setCellValueFactory(new PropertyValueFactory<Mobile, String>("weight"));
        price_col.setCellValueFactory(new PropertyValueFactory<Mobile, String>("price"));
        tab.getItems().setAll(Controller.mobiles_list);

        FileInputStream input = new FileInputStream("src/newphoto.jpg");


        Image image = new Image(input);


        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background background = new Background(backgroundimage);
        GridU.setBackground(background);
    }

    public void Back() throws IOException {

        BackButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("DashBoard.fxml")));

    }
}
