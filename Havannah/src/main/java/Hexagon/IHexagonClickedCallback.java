package Hexagon;

public interface IHexagonClickedCallback {
    void onClicked(Hexagon hexagon) throws NoHexagonFoundException;
}
