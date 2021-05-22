package Hexagon;


import javafx.scene.shape.Polygon;


import static java.lang.Math.*;

public class Hexagon extends Polygon {

    final GridPosition position;
    private HexagonMap map;

    public Hexagon(int q, int r) {
        this.position = new GridPosition(q, r);
    }

    void init() {
        for (double p : calculatePolygonPoints()) {
            this.getPoints().add(p);
        }
    }

    public int getQ() {
        return position.q;
    }

    public int getR() {
        return position.r;
    }

    // --------------------- Graphics --------------------------------------------
    private double[] calculatePolygonPoints() {
        int graphicsHeight = map.hexagonSize * 2;
        double graphicsWidth = sqrt(3) / 2 * graphicsHeight;
        int graphicsXoffset = (int) (graphicsWidth * (double) position.q + 0.5 * graphicsWidth * (double) position.r);
        int graphicsYoffset = (int) (3.0 / 4.0 * graphicsHeight * position.r);
        graphicsXoffset = graphicsXoffset + map.graphicsXpadding;
        graphicsYoffset = graphicsYoffset + map.graphicsYpadding;

        double[] polyPoints = new double[12];
        double angle;
        for (int i = 0; i < 6; i++) {
            angle = 2 * PI / 6 * (i + 0.5);
            polyPoints[(i * 2)] = (graphicsXoffset + map.hexagonSize * cos(angle));
            polyPoints[(i * 2 + 1)] = (graphicsYoffset + map.hexagonSize * sin(angle));
        }
        return polyPoints;
    }

    @Override
    public String toString() {
        return "Hexagon q:" + position.q + " r:" + position.r;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
        Hexagon hexagonObj = (Hexagon) obj;
        return (hexagonObj.getQ() == this.getQ() && hexagonObj.getR() == this.getR());
    }

    void setMap(HexagonMap map) {
        this.map = map;
        if (map != null) {
            init();
        }
    }
}
