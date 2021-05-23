package Logic;

import Hexagon.Hexagon;
import Hexagon.GridPosition;
import figures.Sides;
import figures.Vertexes;

import java.util.*;

public class GameLogic {

    Map<String,Hexagon> redCache = new HashMap<>();
    public void mkRedHexagon(Hexagon hexagon){
        if (!hasRedHexagon(hexagon)) {
            redCache.put(hexagon.toString(), hexagon);
        }
    }

    public boolean hasRedHexagon(Hexagon hexagon){
        return redCache.containsKey(hexagon.toString());
    }

    Map<String,Hexagon> blueCache = new HashMap<>();
    public void mkBlueHexagon(Hexagon hexagon){
        if (!hasBlueHexagon(hexagon)) {
            blueCache.put(hexagon.toString(), hexagon);
        }
    }

    public boolean hasBlueHexagon(Hexagon hexagon){
        return blueCache.containsKey(hexagon.toString());
    }

    public boolean gameOver() {
        return redCache.size() + blueCache.size() == 169;
    }

    private boolean win(Map<String,Hexagon> cache){
        for (Set<Hexagon> set : splitCombinations(new ArrayList<>(cache.values()))) {
            List<Hexagon> list = new ArrayList<>(set);
            if (bridgeOrFork(list))
                return true;
            if (ring(list))
                return true;
        }
        return false;
    }

    public boolean redWin() {
        return win(redCache);
    }

    public boolean blueWin() {
        System.out.println(splitCombinations(new ArrayList<>(blueCache.values())));
        return win(blueCache);
    }

    public List<Set<Hexagon>> splitCombinations(List<Hexagon> cache) {
        List<Set<Hexagon>> res = new ArrayList<>();
        for (Hexagon hex1 : cache){
            Set<Hexagon> tmpSet = new HashSet<>();
            GridPosition grid1 = new GridPosition(hex1.getQ(),hex1.getR());
            for (Hexagon hex2 : cache) {
                GridPosition grid2 = new GridPosition(hex2.getQ(),hex2.getR());
                if (grid1.isAdjacent(grid2)) {
                    tmpSet.add(hex1);
                    tmpSet.add(hex2);
                } else tmpSet.add(hex1);
            }
            boolean entry = false;
            for (Set<Hexagon> set1 : res){
                for (Hexagon hex : tmpSet)
                    if (set1.contains(hex)){
                        set1.addAll(tmpSet);
                        entry = true;
                    }
            }

            if (!entry) res.add(tmpSet);

            entry = true;
            while (entry) {
                entry = false;
                for (int i = 0; i < res.size() - 1; i++) {
                    for (int j = 1; j + i < res.size(); j++) {
                        for (Hexagon hex : res.get(i + j)) {
                            if (res.get(i).contains(hex)) {
                                res.get(i).addAll(res.get(i + j));
                                res.remove(res.get(i + j));
                                entry = true;
                                break;
                            }
                        }
                        if (entry)
                            break;
                    }
                    if (entry)
                        break;
                }
            }
        }
        return res;
    }

    public boolean bridgeOrFork(List<Hexagon> list){
        Set<Vertexes> vertexes = new HashSet<>();
        Set<Sides> sides = new HashSet<>();
        for (Hexagon hex : list) {
            for (Vertexes vertex : Vertexes.values()) {
                if (vertex.equals(hex))
                    vertexes.add(vertex);
            }
            if (vertexes.size() > 1)
                return true;
            for (Sides side : Sides.values()) {
                if (side.contains(hex))
                    sides.add(side);
            }
            if (sides.size() > 2)
                return true;
        }
        return false;
    }

    public boolean ring(List<Hexagon> list) {
        boolean isChanged = true;
        List<Hexagon> tmpList = new ArrayList<>(list);
        Map<Hexagon,Set<Hexagon>> hexNeighbors = new HashMap<>();
        while (isChanged) {
        for (Hexagon hex1 : tmpList) {
            Set<Hexagon> tmpSet = new HashSet<>();
            GridPosition grid1 = new GridPosition(hex1.getQ(), hex1.getR());
            for (Hexagon hex2 : tmpList) {
                GridPosition grid2 = new GridPosition(hex2.getQ(), hex2.getR());
                if (grid1.isAdjacent(grid2)){
                    tmpSet.add(hex2);
                    hexNeighbors.put(hex1,tmpSet);
                }
            }
        }
            isChanged = false;
            for (Hexagon hex : hexNeighbors.keySet()) {
                if (hexNeighbors.get(hex).size() > 4) {
                    hexNeighbors.remove(hex);
                    tmpList.remove(hex);
                    isChanged = true;
                    break;
                }
                if (hexNeighbors.get(hex).size() < 2) {
                    hexNeighbors.remove(hex);
                    tmpList.remove(hex);
                    isChanged = true;
                    break;
                }
                for (Hexagon hex2 : hexNeighbors.keySet()){
                    GridPosition grid1 = new GridPosition(hex.getQ(), hex.getR());
                    GridPosition grid2 = new GridPosition(hex2.getQ(), hex2.getR());
                    if (grid1.isAdjacent(grid2) && hexNeighbors.get(hex).size() > 1 && hexNeighbors.get(hex2).size() > 1) {
                        List<Hexagon> retain = new ArrayList<>(hexNeighbors.get(hex2));
                        retain.retainAll(hexNeighbors.get(hex));
                        if (retain.size() == 1) {
                            if (hexNeighbors.get(retain.get(0)).size() == 2) {
                                hexNeighbors.remove(retain.get(0));
                                tmpList.remove(retain.get(0));
                                isChanged = true;
                                break;
                            }
                        }
                    }
                }
                if (isChanged)
                    break;
            }
        }
        if (hexNeighbors.keySet().size() > 5) {
            if (hexNeighbors.keySet().size() == 6)
                for (Set<Hexagon> set : hexNeighbors.values()){
                    if (set.size() != 2)
                        return false;
                }
            for (Set<Hexagon> set : hexNeighbors.values()){
                if (set.size() < 1)
                    return false;
            }
            return true;
        }
        return false;
    }
}
