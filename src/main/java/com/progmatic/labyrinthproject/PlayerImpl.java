package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;

public class PlayerImpl implements com.progmatic.labyrinthproject.interfaces.Player {

    @Override
    public Direction nextMove(Labyrinth l) {
        return l.possibleMoves().get(0);
    }
}
