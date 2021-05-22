package Hexagon;

import javafx.scene.Group;

import java.util.Collection;
import java.util.HashMap;

public class HexagonMap {

    final int hexagonSize;
    int graphicsXpadding = 0;
    int graphicsYpadding = 0;
    private final GridDrawer gridDrawer = new GridDrawer(this);
    private final HashMap<GridPosition, Hexagon> hexagons = new HashMap<>();
    IHexagonClickedCallback onHexClickedCallback = hexagon -> {
    };

    public enum Direction {NORTHWEST, NORTHEAST, EAST, SOUTHEAST, SOUTHWEST, WEST}

    public HexagonMap(int hexagonSize) {
        this.hexagonSize = hexagonSize;
    }

    public void addHexagon(Hexagon hexagon) {
        hexagon.setMap(this);
        hexagons.put(hexagon.position, hexagon);
    }

    public Hexagon getHexagon(int q, int r) throws NoHexagonFoundException {
        GridPosition position = new GridPosition(q, r);
        Hexagon result = hexagons.get(position);
        if (result == null) {
            throw new NoHexagonFoundException("There is no Hexagon on q:" + q + " r:" + r);
        }
        return result;
    }

    Hexagon getHexagon(GridPosition position) throws NoHexagonFoundException {
        return getHexagon(position.q, position.r);
    }

    public Collection<Hexagon> getAllHexagons() {
        return hexagons.values();
    }

    public void render(Group group) {
        gridDrawer.draw(group);
    }

    public void setOnHexagonClickedCallback(IHexagonClickedCallback callback) {
        onHexClickedCallback = callback;
    }

}
