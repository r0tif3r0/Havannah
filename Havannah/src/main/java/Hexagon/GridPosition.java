package Hexagon;

public class GridPosition {

    int q;
    int r;

    public GridPosition(int q, int r) {
        this.q = q;
        this.r = r;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            GridPosition gridPositionObj = (GridPosition) obj;
            return gridPositionObj.q == this.q && gridPositionObj.r == this.r;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.q;
        hash = 97 * hash + this.r;
        return hash;
    }

    private static int getNumberFromDirection(HexagonMap.Direction direction) {
        return switch (direction) {
            case NORTHWEST -> 0;
            case NORTHEAST -> 1;
            case EAST -> 2;
            case SOUTHEAST -> 3;
            case SOUTHWEST -> 4;
            case WEST -> 5;
        };
    }

    static HexagonMap.Direction getDirectionFromNumber(int i) {
        switch (i) {
            case 0:
                return HexagonMap.Direction.NORTHWEST;
            case 1:
                return HexagonMap.Direction.NORTHEAST;
            case 2:
                return HexagonMap.Direction.EAST;
            case 3:
                return HexagonMap.Direction.SOUTHEAST;
            case 4:
                return HexagonMap.Direction.SOUTHWEST;
            case 5:
                return HexagonMap.Direction.WEST;
        }
        throw new RuntimeException();
    }


    public GridPosition getNeighborPosition(HexagonMap.Direction direction) {
        int i = getNumberFromDirection(direction);
        int[][] neighbors = new int[][]{
                {0, -1}, {+1, -1}, {+1, 0}, {0, +1}, {-1, +1}, {-1, 0}
        };
        int[] d = neighbors[i];
        return new GridPosition(q + d[0], r + d[1]);
    }

    public boolean isAdjacent(GridPosition otherPosition) {
        GridPosition neighbor;
        for (int i = 0; i < 6; i++) {
            neighbor = getNeighborPosition(getDirectionFromNumber(i));
            if (otherPosition.equals(neighbor)) {
                return true;
            }
        }
        return false;
    }
}
