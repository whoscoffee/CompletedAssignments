import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import static javafx.scene.input.KeyCode.*;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.control.Button;

public class Maker_1 extends Application {
    Scene scene;
    Group root;
    Group obj;
    Path path;
    int count;
    int timz = 10;// amount of times to calc
    Button btn;
    double height = 1080;
    double width = 1920;
    int HEIGHT = 1080;
    int WIDTH = 1920;
    double mouseStartX;
    double mouseStartY;
    double endX;
    double endY;

    // This (method[Grid])is In otherwords, the parameters of which
    // the programs can run more than once
    // For more than one outcome
    //
    // so it makes a grid with a random amount of lines
    // although equivilant amount of rows and columns
    public void Quad() {
        Random rand = new Random();
        Path path = new Path();
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(HEIGHT);
        path.getElements().add(new MoveTo(x, y));
        path.getElements().add(new LineTo(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)));
        path.getElements().add(new LineTo(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)));
        path.getElements().add(new LineTo(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)));
        path.getElements().add(new LineTo(x, y));
        root.getChildren().add(path);
    }

    @Override
    public void start(Stage stage) {
        // Creating a Group object

        count = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        obj = new Group();
        root = new Group(obj);
        btn = new Button(">");
        btn.setOnAction(event -> {
            Quad();
        });
        btn.setLayoutX(width - 30);
        btn.setLayoutY(height / 2);
        root.getChildren().add(btn);
        // Creating a scene object
        scene = new Scene(root, width, height);
        // Setting title to the Stage
        stage.setTitle("Drawing an arc through a path");
        // Adding scene to the stage
        stage.setScene(scene);
        // Displaying the contents of the stage
        stage.show();

    }

    public static void main(String args[]) {
        launch(args);
    }
}
