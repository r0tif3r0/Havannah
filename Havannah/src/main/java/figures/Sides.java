package figures;

import Hexagon.Hexagon;

import java.util.Arrays;
import java.util.List;

public enum Sides {
    side1(Arrays.asList(new Hexagon(0,13), new Hexagon(0,12), new Hexagon(0,11), new Hexagon(0,10), new Hexagon(0,9), new Hexagon(0,8))),
    side2(Arrays.asList(new Hexagon(1,6), new Hexagon(2,5), new Hexagon(3,4), new Hexagon(4,3), new Hexagon(5,2), new Hexagon(6,1))),
    side3(Arrays.asList(new Hexagon(8,0), new Hexagon(9,0), new Hexagon(10,0), new Hexagon(11,0), new Hexagon(12,0), new Hexagon(13,0))),
    side4(Arrays.asList(new Hexagon(14,1), new Hexagon(14,2), new Hexagon(14,3), new Hexagon(14,4), new Hexagon(14,5), new Hexagon(14,6))),
    side5(Arrays.asList(new Hexagon(8,13), new Hexagon(9,12), new Hexagon(10,11), new Hexagon(11,10), new Hexagon(12,9), new Hexagon(13,8))),
    side6(Arrays.asList(new Hexagon(1,14), new Hexagon(2,14), new Hexagon(3,14), new Hexagon(4,14), new Hexagon(5,14), new Hexagon(6,14)));

    private final List<Hexagon> side;

    Sides(List<Hexagon> side) {
        this.side = side;
    }

    public boolean contains(Hexagon hex) {
        return side.contains(hex);
    }
}
