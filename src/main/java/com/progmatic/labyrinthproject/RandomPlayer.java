package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.List;
import java.util.Random;

public class RandomPlayer implements Player {
    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> moves = l.possibleMoves();
        return moves.get(new Random().nextInt(moves.size()));
    }
}
