import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

class Group0 extends Group {
    Translate t = new Translate();
    Translate p = new Translate();
    Translate ip = new Translate();
    Rotate rx = new Rotate();
    {
        rx.setAxis(Rotate.X_AXIS);
    }
    Rotate ry = new Rotate();
    {
        ry.setAxis(Rotate.Y_AXIS);
    }
    Rotate rz = new Rotate();
    {
        rz.setAxis(Rotate.Z_AXIS);
    }

    public Group0() {
        super();
        getTransforms().addAll(t, p, rx, ry, rz, ip);
    }

    public double getPivX() {
        return rx.getAngle();
    }

    public double getPivY() {
        return ry.getAngle();
    }

    public double getPivZ() {
        return rz.getAngle();
    }
}
