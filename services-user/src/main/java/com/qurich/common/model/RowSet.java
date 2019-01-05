package com.qurich.common.model;

public class RowSet {

	private int row;
	private int cell;
	private Object value;
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCell() {
		return cell;
	}
	public void setCell(int cell) {
		this.cell = cell;
	}
	@Override
	public String toString() {
		return "RowSet [行=" + row + ", 列=" + cell + ", 值=" + value + "]";
	}
	
}
