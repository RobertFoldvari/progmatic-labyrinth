package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;

/**
 * A simple immutable class for encapsulating a row and a column index.
 * 
 * @author pappgergely
 */
public class Coordinate {
    
    private final int row, col;

    public Coordinate(int col, int row) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Coordinate getNewCoordinateByDirection(Direction d) {
        return new Coordinate(this.getCol() + d.getColOffset(), this.getRow() + d.getRowOffset());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.col != other.col) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.row;
        hash = 83 * hash + this.col;
        return hash;
    }
}
