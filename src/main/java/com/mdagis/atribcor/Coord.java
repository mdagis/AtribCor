package com.mdagis.atribcor;

import java.util.Objects;

/**
 *
 * @author agis
 */
public class Coord {

    private Integer row;
    private Integer col;

    public Coord(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public boolean hasElement(Integer el) {
        return this.row.equals(el) || this.col.equals(el);
    }

    public Integer getOtherCoord(Integer coord) {

        if (this.col.equals(coord)) {
            return this.row;
        }
        if (this.row.equals(coord)) {
            return this.col;
        }

        return null;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Coord)) {
            return false;
        }

        Coord test = (Coord) obj;

        return Objects.equals(test.col, this.col) && Objects.equals(test.row, this.row);
    }

    @Override
    public int hashCode() {
        return (this.col * 1_000_000) + (this.row);
    }

}
