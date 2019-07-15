import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.application.Application;
import static javafx.scene.input.KeyCode.*;
import static javafx.application.Application.launch;

public class Window extends Application {

    final BuildCam bc = new BuildCam();
    final Group root = new Group(), axisGroup = new Group();
    Xform world = new Xform();
    Xform selected = new Xform();
    double ONE_FRAME = 1.0 / 24.0;
    double DELTA_MULTIPLIER = 200.0;
    double CONTROL_MULTIPLIER = 0.1;
    double SHIFT_MULTIPLIER = 0.1;
    double ALT_MULTIPLIER = 0.5;
    double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY;

    public static void main(String[] args) {
        launch(args);
    }

    private void buildScene() {
        System.out.println("buildScene()");
        world.getChildren().add(selected);
        root.getChildren().add(world);
    }

    private void buildAxes() {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(240.0, .4, .4);
        final Box yAxis = new Box(.4, 240.0, .4);
        final Box zAxis = new Box(.4, .4, 240.0);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        world.getChildren().addAll(axisGroup);
    }

    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
		public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
	    });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
		public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;
                double modifierFactor = 0.1;

                if (me.isControlDown()) {
                    modifier = 0.1;
                }
                if (me.isShiftDown()) {
                    modifier = 10.0;
                }
                if (me.isPrimaryButtonDown()) {
                    bc.cameraXform.ry
			.setAngle(bc.cameraXform.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * 2.0); // +
                    bc.cameraXform.rx
			.setAngle(bc.cameraXform.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * 2.0); // -
                } else if (me.isSecondaryButtonDown()) {
                    double z = bc.camera.getTranslateZ();
                    double newZ = z + mouseDeltaX * modifierFactor * modifier;
                    bc.camera.setTranslateZ(newZ);
                } else if (me.isMiddleButtonDown()) {
                    bc.cameraXform2.t.setX(bc.cameraXform2.t.getX() + mouseDeltaX * modifierFactor * modifier * 0.3); // -
                    bc.cameraXform2.t.setY(bc.cameraXform2.t.getY() + mouseDeltaY * modifierFactor * modifier * 0.3); // -
                }
            }
	    });
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("start()");
        buildScene();
        bc.Build(root);
        buildAxes();
        ToolBar toolb = new ToolBar(world, selected);
        ObjectProperties op = new ObjectProperties(world, selected);

        Scene scene = new Scene(root, 1024, 768, true);
        scene.setFill(Color.GREY);
        handleMouse(scene, world);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setCamera(bc.camera);
    }
}
