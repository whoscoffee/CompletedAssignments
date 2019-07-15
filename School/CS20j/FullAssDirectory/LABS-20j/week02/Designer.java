import static javafx.application.Application.launch;

import static javafx.scene.input.KeyCode.*;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.CullFace;
import javafx.scene.transform.Rotate;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Node;

import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.util.Duration;

/**
 * MoleculeSampleApp
 */
public class Designer extends Application {

    final Group root = new Group();
    final Group0 axisGroup = new Group0();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Group0 world = new Group0();
    final Group0 cameraXform = new Group0();
    private Timeline timeline;
    int count;
    int index;
    final double cameraDistance = 450;
    boolean timelinePlaying = false;
    double ONE_FRAME = 1.0 / 24.0;
    double DELTA_MULTIPLIER = 200.0;
    double CONTROL_MULTIPLIER = 0.1;
    double SHIFT_MULTIPLIER = 0.1;
    double ALT_MULTIPLIER = 0.5;
    double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;

    private void buildScene() {
        System.out.println("buildScene");
        root.getChildren().add(world);
    }

    private void buildCamera() {
        System.out.println("buildCamera");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(camera);

        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
    }

    private void buildAxes() {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(240.0, 1, 1);
        final Box yAxis = new Box(1, 240.0, 1);
        final Box zAxis = new Box(1, 1, 240.0);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        world.getChildren().addAll(axisGroup);
    }

    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(me -> {
		mousePosX = me.getSceneX();
		mousePosY = me.getSceneY();
	    });
        scene.setOnMouseDragged(me -> {
		mouseOldX = mousePosX;
		mouseOldY = mousePosY;
		mousePosX = me.getSceneX();
		mousePosY = me.getSceneY();
		mouseDeltaX = (mousePosX - mouseOldX);
		mouseDeltaY = (mousePosY - mouseOldY);

		double modifier = 1.0;
		double modifierFactor = 0.1;

		if (me.isControlDown())
		    modifier = 0.1;

		if (me.isShiftDown())
		    modifier = 10.0;

		if (me.isPrimaryButtonDown()) {
		    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0); // +
		    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0); // -
		} else if (me.isSecondaryButtonDown())
		    camera.setTranslateZ(camera.getTranslateZ() + mouseDeltaX * modifierFactor * modifier);
	    });
    }

    public void ToolBarScene() {
        Stage ToolBar = new Stage();
        ToolBar.setTitle("ToolBar");

        Button Box = new Button("Box");
        Box.setOnAction(event -> {
		System.out.println("new Box");
		Box box = new Box(10.0, 10.0, 10.0);
		Group0 Obj = new Group0();
		Obj.getChildren().add(box);
		int inde = count;
		box.setOnMouseClicked(me -> {
			System.out.printf("Selected box (%d)%n", inde);
			index = inde;
		    });
		world.getChildren().add(count, Obj);
		count++;
	    });
        Button Rectangle = new Button("Rectangle");
        Rectangle.setOnAction(event -> {
		System.out.println("new Rectangle");
		final MeshView rectangle = new MeshView(new Rectangle0(10, 10, 10));
		rectangle.setMaterial(new PhongMaterial(Color.DARKGREEN));
		rectangle.setCullFace(CullFace.NONE);
		Group0 Obj = new Group0();
		Obj.getChildren().add(rectangle);
		int inde = count;
		rectangle.setOnMouseClicked(me -> {
			System.out.println("Selected Rectangle");
			index = inde;
		    });
		world.getChildren().add(count, Obj);
		count++;
	    });
        Button Point = new Button("Point");

        Point.setOnAction(event -> {
		System.out.println("new Point");
		Sphere point = new Sphere(1);
		Group0 Obj = new Group0();
		Obj.getChildren().add(point);
		int inde = count;
		point.setOnMouseClicked(me -> {
			System.out.printf("Selected point (%d)%n", inde);
			index = inde;
		    });
		world.getChildren().add(count, Obj);
		count++;
	    });
        GridPane root = new GridPane();

        Box.setTranslateX(35);
        Box.setTranslateY(30);
        root.getChildren().add(Box);

        Rectangle.setTranslateX(35);
        Rectangle.setTranslateY(120);
        root.getChildren().add(Rectangle);

        Point.setTranslateX(35);
        Point.setTranslateY(150);
        root.getChildren().add(Point);

        ToolBar.setScene(new Scene(root, 100, 300));
        ToolBar.setX(477);
        ToolBar.setY(300);
        ToolBar.show();
    }

    public void ObjPropertyScene() {
        Stage op = new Stage();
        op.setTitle("Object Properties?");

        // Translate : X
        TextField0 tfX = new TextField0(String.valueOf(world.getChildren().get(index).getTranslateX()));
        tfX.setOnAction(event -> {
		world.getChildren().get(index).setTranslateX(Double.valueOf(tfX.getCharacters().toString()));
	    });
        tfX.setTranslateX(5);
        tfX.setTranslateY(5);
        // Translate : Y
        TextField0 tfY = new TextField0(String.valueOf(world.getChildren().get(index).getTranslateY()));
        tfY.setOnAction(event -> {
		world.getChildren().get(index).setTranslateY(Double.valueOf(tfY.getCharacters().toString()));
	    });
        tfY.setTranslateX(5);
        tfY.setTranslateY(30);
        // Translate : Z
        TextField0 tfZ = new TextField0(String.valueOf(world.getChildren().get(index).getTranslateZ()));
        tfZ.setOnAction(event -> {
		world.getChildren().get(index).setTranslateZ(Double.valueOf(tfZ.getCharacters().toString()));
	    });
        tfZ.setTranslateX(5);
        tfZ.setTranslateY(55);
        // Scale : X
        TextField0 sfX = new TextField0(String.valueOf(world.getChildren().get(index).getScaleX()));
        sfX.setOnAction(event -> {
		world.getChildren().get(index).setScaleX(Double.valueOf(sfX.getCharacters().toString()));
	    });
        sfX.setTranslateX(5);
        sfX.setTranslateY(155);
        // Scale : Y
        TextField0 sfY = new TextField0(String.valueOf(world.getChildren().get(index).getScaleY()));
        sfY.setOnAction(event -> {
		world.getChildren().get(index).setScaleY(Double.valueOf(sfY.getCharacters().toString()));
	    });
        sfY.setTranslateX(5);
        sfY.setTranslateY(180);
        // Scale : Z
        TextField0 sfZ = new TextField0(String.valueOf(world.getChildren().get(index).getScaleZ()));
        sfZ.setOnAction(event -> {
		world.getChildren().get(index).setScaleZ(Double.valueOf(sfZ.getCharacters().toString()));
	    });
        sfZ.setTranslateX(5);
        sfZ.setTranslateY(205);

        System.out.println(tfX.getOnAction());
        GridPane root = new GridPane();

        root.getChildren().add(tfX);
        root.getChildren().add(tfY);
        root.getChildren().add(tfZ);
        root.getChildren().add(sfX);
        root.getChildren().add(sfY);
        root.getChildren().add(sfZ);

        op.setScene(new Scene(root, 300, 250));
        op.setX(1603);
        op.setY(300);
        op.show();
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("start");
        buildScene();
        buildCamera();
        buildAxes();
        // buildMolecule();
        ToolBarScene();
        ObjPropertyScene();

        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.GREY);
        handleMouse(scene, world);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setCamera(camera);
    }

    public static void main(String[] args) {
        System.setProperty("prism.dirtyopts", "false");
        launch(args);
    }
}
