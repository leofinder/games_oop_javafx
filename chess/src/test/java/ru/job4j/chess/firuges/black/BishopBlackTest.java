package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {
    @Test
    public void whenCreateBishopBlackTestPosition() {
        Cell cell = Cell.C8;
        Figure figure = new BishopBlack(cell);
        Cell position = figure.position();
        assertThat(position).isEqualTo(cell);
    }

    @Test
    public void whenCopyBishopBlackTestPosition() {
        Cell cell = Cell.A1;
        Figure figure = new BishopBlack(cell);
        Cell destination = Cell.G5;
        Figure figureCopy = figure.copy(destination);
        Cell position = figureCopy.position();
        assertThat(position).isEqualTo(destination);
    }

    @Test
    public void whenWay() {
        Cell cell = Cell.C1;
        Figure figure = new BishopBlack(cell);
        Cell destination = Cell.G5;
        Cell[] result = figure.way(destination);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(result).containsExactly(expected);
    }

    @Test
    public void whenWayException() {
        Cell cell = Cell.C1;
        Figure figure = new BishopBlack(cell);
        Cell destination = Cell.G1;
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    figure.way(destination);
                });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from %s to %s", cell, destination);
    }
}