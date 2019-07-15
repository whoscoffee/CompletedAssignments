
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class ToolBar {

    ToolBar(final Xform world, final Xform selected) {
        Stage tb = new Stage();
        tb.setTitle("ToolBar");

        Button Box = new Button("Box");
        Button Cylinder = new Button("Cylinder");
        Button Sphere = new Button("Sphere");

        Box.setOnAction(new EventHandler<ActionEvent>() {

            @Override
		public void handle(ActionEvent event) {
                System.out.println("new Box");
                Box box = new Box(10.0, 10.0, 10.0);
                box.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
			public void handle(MouseEvent event) {
                        System.out.println("Selected box");
                        selected.getChildren().add(box);
                    }
		    });
                world.getChildren().add(box);
            }
	    });
        Cylinder.setOnAction(new EventHandler<ActionEvent>() {

            @Override
		public void handle(ActionEvent event) {
                System.out.println("new Cylinder");
                Cylinder cylinder = new Cylinder(10.0, 10.0);
                cylinder.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
			public void handle(MouseEvent event) {
                        System.out.println("Selected cylinder");
                        selected.getChildren().add(cylinder);
                    }
		    });
                world.getChildren().add(cylinder);
            }
	    });
        Sphere.setOnAction(new EventHandler<ActionEvent>() {

            @Override
		public void handle(ActionEvent event) {
                System.out.println(" new Sphere");
                Sphere sphere = new Sphere(10.0);
                sphere.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
			public void handle(MouseEvent event) {
                        System.out.println("Selected sphere");
                        selected.getChildren().add(sphere);
                    }
		    });
                world.getChildren().add(sphere);
            }
	    });

        GridPane root = new GridPane();
        Box.setTranslateX(10);
        Box.setTranslateY(10);
        root.getChildren().add(Box);
        Cylinder.setTranslateX(10);
        Cylinder.setTranslateY(40);
        root.getChildren().add(Cylinder);
        Sphere.setTranslateX(10);
        Sphere.setTranslateY(70);
        root.getChildren().add(Sphere);

        tb.setScene(new Scene(root, 300, 250));
        tb.show();
    }
}
