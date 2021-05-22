package figures;

import Hexagon.Hexagon;

public enum Vertexes {
    v1(new Hexagon(7,0)),
    v2(new Hexagon(14,0)),
    v3(new Hexagon(14,7)),
    v4(new Hexagon(7,14)),
    v5(new Hexagon(0,14)),
    v6(new Hexagon(0,7));

    private final Hexagon vertex;

    Vertexes(Hexagon vertex) {
        this.vertex = vertex;
    }

    public boolean equals(Hexagon hex) {
        return vertex.equals(hex);
    }
}
