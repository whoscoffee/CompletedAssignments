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
import javafx.scene.control.Button;

public class example extends Application {
    Scene scene;
    Group root;
    Group obj;
    int count;
    double mouseStartX;
    double mouseStartY;
    double endX;
    double endY;

    public void Toolbar() {
        Stage stage = new Stage();
        stage.setTitle("toolbar");
        Group layout = new Group();
        Button pen = new Button("*");
        Button line = new Button("|");
        Button cascade = new Button("/|\\");
        pen.setOnAction(event1 -> {
		System.out.println("Pen");
		scene.setOnMousePressed(event -> {
			endX = event.getSceneX();
			endY = event.getSceneY();
		    });
		scene.setOnMouseDragged(event -> {
			mouseStartX = event.getSceneX();
			mouseStartY = event.getSceneY();
			Line ink = new Line(mouseStartX, mouseStartY, endX, endY);
			endX = event.getSceneX();
			endY = event.getSceneY();
			root.getChildren().add(count, ink);
			count++;
		    });

	    });
        pen.setLayoutY(10);
        line.setOnAction(event1 -> {
		System.out.println("Line");
		scene.setOnMousePressed(event -> {
			mouseStartX = event.getSceneX();
			mouseStartY = event.getSceneY();
			obj.getChildren().clear();
		    });
		count = 0;
		scene.setOnMouseDragged(event -> {
			endX = event.getSceneX();
			endY = event.getSceneY();
			Line line0 = new Line(mouseStartX, mouseStartY, endX, endY);
			line0.setStroke(Color.HOTPINK);
			obj.getChildren().clear();
			obj.getChildren().add(line0);
		    });

		scene.setOnMouseReleased(event -> {
			endX = event.getSceneX();
			endY = event.getSceneY();
			Line line0 = new Line(mouseStartX, mouseStartY, endX, endY);
			root.getChildren().add(count, line0);
			obj.getChildren().clear();
			count++;
		    });
	    });
        line.setLayoutY(35);
        cascade.setOnAction(event1 -> {
		System.out.println("Cascade");
		scene.setOnMousePressed(event -> {
			endX = event.getSceneX();
			endY = event.getSceneY();
		    });
		scene.setOnMouseDragged(event -> {
			mouseStartX = event.getSceneX();
			mouseStartY = event.getSceneY();
			Line ink = new Line(mouseStartX, mouseStartY, endX, endY);
			root.getChildren().add(count, ink);
			count++;
		    });
	    });
        cascade.setLayoutY(60);
        layout.getChildren().addAll(pen, line, cascade);
        Scene scene = new Scene(layout, 50, 1080);
        stage.setScene(scene);
        stage.show();
    }

    public void Make() {
        // TR
        for (int y = 0; y < 1080; y += 9) {
            Line line = new Line(1920, 0, 0, y);
            line.setStroke(Color.DARKBLUE);
            root.getChildren().add(line);
        }

        for (int x = 0; x < 1920; x += 16) {
            Line line = new Line(1920, 0, x, 1080);
            line.setStroke(Color.DARKBLUE);
            root.getChildren().add(line);
        }

        // TL
        for (int y = 0; y < 1080; y += 9) {
            Line line = new Line(0, 0, 1920, y);
            line.setStroke(Color.DARKGREEN);
            root.getChildren().add(line);
        }

        for (int x = 1920; x > 0; x -= 16) {
            Line line = new Line(0, 0, x, 1080);
            line.setStroke(Color.DARKGREEN);
            root.getChildren().add(line);
        }

        // BL
        for (int x = 0; x < 1920; x += 16) {
            Line line = new Line(0, 1080, x, 0);
            line.setStroke(Color.DARKRED);
            root.getChildren().add(line);
        }

        for (int y = 0; y < 1080; y += 9) {
            Line line = new Line(0, 1080, 1920, y);
            line.setStroke(Color.DARKRED);
            root.getChildren().add(line);
        }

        // BR
        for (int x = 1920; x > 0; x -= 16) {
            Line line = new Line(1920, 1080, x, 0);
            root.getChildren().add(line);
        }

        for (int y = 0; y < 1080; y += 9) {
            Line line = new Line(1920, 1080, 0, y);
            root.getChildren().add(line);
        }

    }

    public void Make0() {
        // BL
        for (int y = 1071, x = 1904; y > 0; y -= 9, x -= 16) {
            Line line = new Line(x, 1080, 0, y);
            line.setStroke(Color.DARKRED);
            root.getChildren().add(line);
        }
        // TL
        for (int y = 1071, x = 16; y > 0; y -= 9, x += 16) {
            Line line = new Line(x, 0, 0, y);
            line.setStroke(Color.DARKGREEN);
            root.getChildren().add(line);
        }
        // TR
        for (int y = 9, x = 16; y < 1080; y += 9, x += 16) {
            Line line = new Line(x, 0, 1920, y);
            line.setStroke(Color.DARKBLUE);
            root.getChildren().add(line);
        }
        // BR
        for (int y = 9, x = 1904; x > 0; y += 9, x -= 16) {
            Line line = new Line(x, 1080, 1920, y);
            root.getChildren().add(line);
        }
    }

    @Override
    public void start(Stage stage) {
        // Creating a Group object
        obj = new Group();
        root = new Group(obj);
        // Creating a scene object

        // Toolbar();
        Make();
        Make0();
        scene = new Scene(root, 1920, 1080);
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
