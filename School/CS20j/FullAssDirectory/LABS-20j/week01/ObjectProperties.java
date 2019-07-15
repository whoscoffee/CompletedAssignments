import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

class ObjectProperties {

    ObjectProperties(Xform world, Xform selected) {
        Stage op = new Stage();
        op.setTitle("Object Properties?");

        // Translate : X
        TextField0 tfX = new TextField0(String.valueOf(selected.t.getX()));
        tfX.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.t.setX(Double.valueOf(tfX.getCharacters().toString()));
		}
	    });
        tfX.setTranslateX(5);
        tfX.setTranslateY(5);
        // Translate : Y
        TextField0 tfY = new TextField0(String.valueOf(selected.t.getY()));
        tfY.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.t.setY(Double.valueOf(tfY.getCharacters().toString()));
		}
	    });
        tfY.setTranslateX(5);
        tfY.setTranslateY(30);
        // Translate : Z
        TextField0 tfZ = new TextField0(String.valueOf(selected.t.getZ()));
        tfZ.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.t.setZ(Double.valueOf(tfZ.getCharacters().toString()));
		}
	    });
        tfZ.setTranslateX(5);
        tfZ.setTranslateY(55);
        // Rotation : X
        TextField0 rfX = new TextField0(String.valueOf(selected.rx.getPivotX()));
        rfX.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.setRx(Double.valueOf(rfX.getCharacters().toString()));
		}
	    });
        rfX.setTranslateX(5);
        rfX.setTranslateY(80);
        // Rotation : Y
        TextField0 rfY = new TextField0(String.valueOf(selected.ry.getPivotY()));
        rfY.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.setRy(Double.valueOf(rfY.getCharacters().toString()));
		}
	    });
        rfY.setTranslateX(5);
        rfY.setTranslateY(105);
        // Rotation : Z
        TextField0 rfZ = new TextField0(String.valueOf(selected.rz.getPivotZ()));
        rfZ.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.setRz(Double.valueOf(rfZ.getCharacters().toString()));
		}
	    });
        rfZ.setTranslateX(5);
        rfZ.setTranslateY(130);
        // Scale : X
        TextField0 sfX = new TextField0(String.valueOf(selected.s.getX()));
        sfX.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.setSx(Double.valueOf(sfX.getCharacters().toString()));
		}
	    });
        sfX.setTranslateX(5);
        sfX.setTranslateY(155);
        // Scale : Y
        TextField0 sfY = new TextField0(String.valueOf(selected.s.getY()));
        sfY.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.setSy(Double.valueOf(sfY.getCharacters().toString()));
		}
	    });
        sfY.setTranslateX(5);
        sfY.setTranslateY(180);
        // Scale : Z
        TextField0 sfZ = new TextField0(String.valueOf(selected.s.getZ()));
        sfZ.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
		    selected.setSz(Double.valueOf(sfZ.getCharacters().toString()));
		}
	    });
        sfZ.setTranslateX(5);
        sfZ.setTranslateY(205);

        System.out.println(tfX.getOnAction());
        GridPane root = new GridPane();

        root.getChildren().add(tfX);
        root.getChildren().add(tfY);
        root.getChildren().add(tfZ);
        root.getChildren().add(rfX);
        root.getChildren().add(rfY);
        root.getChildren().add(rfZ);
        root.getChildren().add(sfX);
        root.getChildren().add(sfY);
        root.getChildren().add(sfZ);
        op.setScene(new Scene(root, 300, 250));
        op.show();

    }
}
