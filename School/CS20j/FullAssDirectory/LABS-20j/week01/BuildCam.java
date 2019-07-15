import javafx.scene.PerspectiveCamera;
import javafx.scene.Camera;
import javafx.scene.Group;

public class BuildCam {

    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform(), cameraXform2 = new Xform(), cameraXform3 = new Xform();
    final double cameraDistance = 450;

    public void Build(Group root) {
        System.out.println("buildCamera");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
        cameraXform.ry.setAngle(320.0);
        cameraXform.rx.setAngle(40);
    }

}
