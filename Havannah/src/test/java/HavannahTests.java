import Hexagon.Hexagon;
import Hexagon.GridPosition;
import Logic.GameLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HavannahTests {

    GameLogic gl = new GameLogic();
    GridPosition gp = new GridPosition(7,7);

    List<Hexagon> bridgeList = new ArrayList<>(Arrays.asList(new Hexagon(9,1),new Hexagon(10,0),new Hexagon(7,1),
            new Hexagon(13,1),new Hexagon(12,1),new Hexagon(14,1),new Hexagon(11,1),
            new Hexagon(8,1),new Hexagon(10,1),new Hexagon(6,1)));
    List<Hexagon> forkList = new ArrayList<>(Arrays.asList(new Hexagon(12,8),new Hexagon(7,14),new Hexagon(11,9),
            new Hexagon(8,12),new Hexagon(7,13),new Hexagon(13,7),new Hexagon(10,10),new Hexagon(9,11),
            new Hexagon(14,7)));
    List<Hexagon> ringList = new ArrayList<>(Arrays.asList(new Hexagon(5,8),new Hexagon(7,6),new Hexagon(6,8),
            new Hexagon(7,7),new Hexagon(6,5),new Hexagon(7,5),new Hexagon(5,7),new Hexagon(5,6)));
    List<Hexagon> splitList = new ArrayList<>(Arrays.asList(new Hexagon(7,5),new Hexagon(9,4),new Hexagon(8,4),
            new Hexagon(8,8),new Hexagon(7,8),new Hexagon(11,6)));

    @Test
    public void bridgeTrue(){
        Assert.assertTrue(gl.bridgeOrFork(bridgeList));
    }

    @Test
    public void forkTrue(){
        Assert.assertTrue(gl.bridgeOrFork(forkList));
    }

    @Test
    public void bridgeOrForkFalse(){
        Assert.assertFalse(gl.bridgeOrFork(ringList));
    }

    @Test
    public void ringTrue(){
        Assert.assertTrue(gl.ring(ringList));
    }

    @Test
    public void ringFalse(){
        Assert.assertFalse(gl.ring(forkList));
        Assert.assertFalse(gl.ring(bridgeList));
    }

    @Test
    public void splitCombinations(){
        Assert.assertEquals(3,gl.splitCombinations(splitList).size());
    }

    @Test
    public void isAdjacent(){
        Assert.assertTrue(gp.isAdjacent(new GridPosition(6,8)));
    }

    @Test
    public  void isNotAdjacent(){
        Assert.assertFalse(gp.isAdjacent(new GridPosition(10,10)));
    }
}
