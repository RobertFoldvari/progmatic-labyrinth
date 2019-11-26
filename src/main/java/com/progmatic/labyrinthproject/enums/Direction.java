package com.progmatic.labyrinthproject.enums;

/**
 *
 * @author pappgergely
 */
public enum Direction {
    NORTH(-1, 0), EAST(0, 1), SOUTH(1, 0), WEST(0, -1);

    private final int rowOffset;
    private final int colOffset;

    Direction(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColOffset() {
        return colOffset;
    }

    public Direction getRight() {
        if (this == NORTH) return EAST;
        if (this == EAST) return SOUTH;
        if (this == SOUTH) return WEST;
        if (this == WEST) return NORTH;

        return null;
    }

    public Direction getLeft() {
        if (this == NORTH) return WEST;
        if (this == EAST) return NORTH;
        if (this == SOUTH) return EAST;
        if (this == WEST) return SOUTH;

        return null;
    }
}
