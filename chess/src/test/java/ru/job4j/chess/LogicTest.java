package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.PawnBlack;
import ru.job4j.chess.firuges.black.BishopBlack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void move()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        Cell cell = Cell.C1;
        Cell destination = Cell.H6;
        logic.add(new BishopBlack(cell));
        logic.add(new PawnBlack(Cell.A2));
        logic.add(new PawnBlack(Cell.B2));
        logic.add(new PawnBlack(Cell.C2));
        logic.add(new PawnBlack(Cell.E2));
        logic.add(new PawnBlack(Cell.F2));
        logic.add(new PawnBlack(Cell.G2));
        logic.add(new PawnBlack(Cell.H2));
        logic.move(cell, destination);
    }

    @Test
    public void whenMoveThenFigureNotFoundException() {
        Logic logic = new Logic();
        Cell cell = Cell.C1;
        FigureNotFoundException exception = assertThrows(
                FigureNotFoundException.class,
                () -> {
                    logic.move(cell, Cell.H6);
                });
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Could not find figure by %s", cell));
    }

    @Test
    public void whenMoveThenImpossibleMoveException() {
        Logic logic = new Logic();
        Cell cell = Cell.C8;
        Cell destination = Cell.H8;
        logic.add(new BishopBlack(cell));
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    logic.move(cell, destination);
                });
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Could not move by diagonal from %s to %s", cell, destination));
    }

    @Test
    public void whenMoveThenOccupiedCellException() {
        Logic logic = new Logic();
        Cell cell = Cell.C8;
        Cell occupied = Cell.D7;
        Cell destination = Cell.F5;
        logic.add(new BishopBlack(cell));
        logic.add(new PawnBlack(occupied));
        OccupiedCellException exception = assertThrows(
                OccupiedCellException.class,
                () -> {
                    logic.move(cell, destination);
                });
        assertThat(exception.getMessage()).isEqualTo(
                String.format("Cell %s is occupied", occupied));
    }
}