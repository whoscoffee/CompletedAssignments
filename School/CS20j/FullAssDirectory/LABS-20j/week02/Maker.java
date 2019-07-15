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

public class Maker extends Application {
    Scene scene;
    Group root;
    Group obj;
    int count;
    int timz = 10;// amount of times to calc
    Button btn;
    double height = 1080;
    double width = 1920;
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
    public void Grid(int i) {
        for (int j = 0; j < width; j += width / i)
            root.getChildren().add(new Line(j, 0, j, height));

        for (int j = 0; j < height; j += height / i)
            root.getChildren().add(new Line(0, j, width, j));

    }

    @Override
    public void start(Stage stage) {
        // Creating a Group object
        Random rand = new Random();
        count = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        obj = new Group();
        root = new Group(obj);
        btn = new Button(">");
        btn.setOnAction(event -> {

		int i = rand.nextInt(timz);
		while (arr.contains(i) && count < timz) {
		    i = rand.nextInt(timz);
		}
		if (count < timz) {
		    root.getChildren().clear();
		    root.getChildren().add(btn);
		    arr.add(i);
		    System.out.println(i);
		    count++;
		    Grid(i);
		}
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
