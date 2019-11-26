package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    int width;
    int height;

    CellType[][] field;

    Coordinate playerPosition;

    public LabyrinthImpl() {
        
    }

    @Override
    public int getWidth() {
        return this.width == 0 ? -1 : this.width;
    }

    @Override
    public int getHeight() {
        return this.height == 0 ? -1: this.height;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());

            setSize(width, height);

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    Coordinate c = new Coordinate(ww, hh);
                    switch (line.charAt(ww)) {
                        case 'W':
                            setCellType(c, CellType.WALL);
                            break;
                        case 'E':
                            setCellType(c, CellType.END);
                            break;
                        case 'S':
                            setCellType(c, CellType.START);
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        } catch (CellException cex) {
            System.out.println("Hibás labirintus definíció.");
            System.out.println(cex.getMessage());
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        validateCoordinates(c);
        return field[c.getRow()][c.getCol()];
    }

    void validateCoordinates(Coordinate c) throws CellException {
        int rowIdx = c.getRow();
        int colIdx = c.getCol();
        if (rowIdx < 0 || rowIdx >= height || colIdx < 0 || colIdx >= width) {
            throw new CellException(c, "Hibás koordináták");
        }
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;

        field = new CellType[height][width];
        for(int r = 0; r < height; r++) {
            for(int c = 0; c < width; c++) {
                field[r][c] = CellType.EMPTY;
            }
        }
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        validateCoordinates(c);
        field[c.getRow()][c.getCol()] = type;

        if (type == CellType.START) {
            playerPosition = new Coordinate(c.getCol(), c.getRow());
        }
    }

    @Override
    public Coordinate getPlayerPosition() {
        return this.playerPosition;
    }

    @Override
    public boolean hasPlayerFinished() {
        return field[playerPosition.getRow()][playerPosition.getCol()] == CellType.END;
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> possibleDirections = new ArrayList<>();

        int rowPos = playerPosition.getRow();
        int colPos = playerPosition.getCol();

        if (isPossibleMove(playerPosition, Direction.NORTH)) {
            possibleDirections.add(Direction.NORTH);
        }
        if (isPossibleMove(playerPosition, Direction.EAST)) {
            possibleDirections.add(Direction.EAST);
        }
        if (isPossibleMove(playerPosition, Direction.SOUTH)) {
            possibleDirections.add(Direction.SOUTH);
        }
        if (isPossibleMove(playerPosition, Direction.WEST)) {
            possibleDirections.add(Direction.WEST);
        }

        return possibleDirections;
    }

    boolean isPossibleMove(Coordinate c, Direction d) {
        Coordinate targetCoord = c.getNewCoordinateByDirection(d);
        int rowPos = targetCoord.getRow();
        int colPos = targetCoord.getCol();

        if (rowPos < 0 || rowPos >= height || colPos < 0 || colPos >= width) {
            return false;
        }

        CellType targetCellType = field[rowPos][colPos];

        if (targetCellType == CellType.WALL || targetCellType == CellType.START) {
            return false;
        }

        return true;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        if (!isPossibleMove(playerPosition, direction)) {
            throw new InvalidMoveException();
        }

        playerPosition = playerPosition.getNewCoordinateByDirection(direction);
    }

}
