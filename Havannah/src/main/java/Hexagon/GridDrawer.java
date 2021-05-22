package Hexagon;


import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

import java.util.Collection;

class GridDrawer {

    private final HexagonMap map;

    GridDrawer(HexagonMap map) {
        this.map = map;
    }

    void draw(Group root) {
        Collection<Hexagon> hexagons = map.getAllHexagons();
        for (Hexagon hexagon : hexagons) {
            hexagon.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    me -> {
                        GridPosition pos = ((Hexagon) me.getSource()).position;
                        try {
                            map.onHexClickedCallback.onClicked(map.getHexagon(pos));
                        } catch (NoHexagonFoundException ignored) {
                        }
                    });
            root.getChildren().add(hexagon);
        }
    }

}
