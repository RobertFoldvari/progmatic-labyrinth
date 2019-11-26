package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WallFollowerPlayer implements Player {

    private Direction prevDirection = Direction.NORTH;

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> moves = l.possibleMoves();
        Direction direction = null;
        if (moves.size() == 1) { // covering start and deadend situations
            direction = moves.get(0);

        } else {
            Set<Direction> movesSet = new HashSet<>(moves);

            if (movesSet.contains(prevDirection.getRight())) {
                direction = prevDirection.getRight();
            }
            if (movesSet.contains(prevDirection)) {
                direction = prevDirection;
            }
            if (movesSet.contains(prevDirection.getLeft())) {
                direction = prevDirection.getLeft();
            }
        }

        prevDirection = direction;
        return direction;
    }
}
