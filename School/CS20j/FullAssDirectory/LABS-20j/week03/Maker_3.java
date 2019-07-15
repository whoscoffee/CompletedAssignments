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
import java.lang.Math;

public class Maker_3 extends Application {
    Scene scene;
    Group root, obj;
    Path path;
    int count, timz = 10;
    Button btn;
    double height = 1080, width = 1920;
    int HEIGHT = 1080, WIDTH = 1920;
    int x = WIDTH / 2, y = HEIGHT / 2;
    double mouseStartX, mouseStartY, endX, endY;
    int guessCount;
    int size = 100;
    int leeway = 10;
    int guessLimit = 2000;
    int sizUp = size + leeway;
    int sizDown = size - leeway;

    // X then Y
    public int[] OffCenter(int[] givenvector2) {
        int[] vector2 = new int[2];
        if (x + givenvector2[0] >= 0 && x + givenvector2[0] <= WIDTH)
            vector2[0] = x + givenvector2[0];
        else
            System.out.println("Fail At OffCenter(): Given (X) size too big or too small");
        if (y - givenvector2[1] >= 0 && y - givenvector2[1] <= HEIGHT)
            vector2[1] = y - givenvector2[1];
        else
            System.out.println("Fail At OffCenter(): Given (Y) size too big or too small");
        return vector2;
    }

    public void Quad() {
        Path path = new Path();
        boolean istrue = false;
        int i = 0;
        guessCount = 0;
        outer: do {
            int[] arr = { 0, 0 };// Start at 0,0 or middle because x and y vars
            arr = OffCenter(arr);
            path.getElements().add(new MoveTo(arr[0], arr[1]));
            arr = Palagrium(arr[0], arr[1]);
            path.getElements().add(new LineTo(arr[0], arr[1]));// Line 1
            arr = Palagrium(arr[0], arr[1]);
            path.getElements().add(new LineTo(arr[0], arr[1]));// line 2
            arr = Palagrium(arr[0], arr[1]);
            path.getElements().add(new LineTo(arr[0], arr[1]));// line 3

            if (isPalagrium(arr[0], arr[1], x, y)) {
                path.getElements().add(new LineTo(x, y));// line 4
                System.out.println("but is it");
                istrue = true;
            } else {
                path.getElements().clear();
                i++;
                continue outer;
            }
            System.out.println("it tried : " + i + " times to make a proper object with 4 sides equal in length");
            System.out.println("and " + guessCount + " times to guess all 4 points correctly");

        } while (i < guessLimit && istrue == false);
        root.getChildren().add(path);
    }

    public int[] Palagrium(int startX, int startY) {
        Random rand = new Random();
        int xx = rand.nextInt(WIDTH);
        int yy = rand.nextInt(HEIGHT);
        count = 0;
        while (Math.pow(Math.abs(xx - startX), 2) + Math.pow(Math.abs(yy - startY), 2) <= Math.pow(sizDown, 2)
                || Math.pow(Math.abs(xx - startX), 2) + Math.pow(Math.abs(yy - startY), 2) >= Math.pow(sizUp, 2)
                        && count < guessLimit) {
            xx = rand.nextInt(WIDTH);
            yy = rand.nextInt(HEIGHT);
            count++;
            if (count > guessLimit - 10) {
                System.out.println("Fail");
                break;
            }
        }
        guessCount += count;
        int[] arr = { xx, yy };
        return arr;
    }

    public boolean isPalagrium(int startX, int startY, int endX, int endY) {
        if (Math.pow(Math.abs(endX - startX), 2) + Math.pow(Math.abs(endY - startY), 2) <= Math.pow(sizDown, 2)
                || Math.pow(Math.abs(endX - startX), 2) + Math.pow(Math.abs(endY - startY), 2) >= Math.pow(sizUp, 2)) {
            return false;
        } else
            return true;
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
            root.getChildren().clear();
            root.getChildren().add(btn);
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
