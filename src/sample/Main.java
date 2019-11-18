package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jess.JessException;
import sample.module.Jess;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
        primaryStage.setTitle("MPExS");
        primaryStage.setScene(new Scene(root, 1200, 975));
        primaryStage.show();
    }


    public static void main(String[] args) throws JessException {
        Jess jess = new Jess();
        if(!jess.open()) {
            System.out.println("Can't initialise rete engine");
            return;
        }
        launch(args);
        jess.end();
    }

}
