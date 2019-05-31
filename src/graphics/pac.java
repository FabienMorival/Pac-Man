import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import model.Grid;


public class pac extends Application {

    public static void main(String[] args) {
        Application.launch(pac.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800,600,Color.web("#001030"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pac Man 2k19");

        GridPane grille = new GridPane();
        root.getChildren().add(grille);

        Image imPM = new Image("VueControleur/Pacman.png"); // préparation des images
        Image imVide = new Image("VueControleur/vide1.png");

        get

        /*for(int i = 1;i<=10; i++){
            for(int j = 1;j<=10; j++)
            grille.add(newlabel, i,j);
        }*/

        primaryStage.show();
    }
}
