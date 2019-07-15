import javafx.scene.shape.Shape3D;
import javafx.scene.shape.TriangleMesh;

public class Rectangle0 extends TriangleMesh {

    public Rectangle0(float width, float height, float depth) {
        float w = width / 2;
        float h = height / 2;
        float d = depth / 2;
        // Vertices
        float points[] = {
	    // vertices
	    -w, -h, -d, // 0
	    w, -h, -d, // 1
	    w, h, -d, // 2
	    -w, h, -d, // 3
	    -w, -h, d, // 4
	    w, -h, d, // 5
	    w, h, d, // 6
	    -w, h, d // 7
        };

        float texCoords[] = { 0, 0, 1, 0, 1, 1, 0, 1 };

        // Specifies hard edges.
        int faceSmoothingGroups[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        int faces[] = { 0, 0, 2, 2, 1, 1, // 1
			2, 2, 0, 0, 3, 3, //
			1, 0, 6, 2, 5, 1, // 2
			6, 2, 1, 0, 2, 3, //
			5, 0, 7, 2, 4, 1, // 3
			7, 2, 5, 0, 6, 3, //
			4, 0, 3, 2, 0, 1, // 4
			3, 2, 4, 0, 7, 3, //
			3, 0, 6, 2, 2, 1, // 5
			6, 2, 3, 0, 7, 3, //
			4, 0, 1, 2, 5, 1, // 6
			1, 2, 4, 0, 0, 3,//
        };

        this.getPoints().setAll(points);
        this.getTexCoords().setAll(texCoords);
        this.getFaces().setAll(faces);
        this.getFaceSmoothingGroups().setAll(faceSmoothingGroups);
    }
}
