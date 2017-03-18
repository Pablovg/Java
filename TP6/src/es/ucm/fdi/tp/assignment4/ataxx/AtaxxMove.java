  package es.ucm.fdi.tp.assignment4.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxMove extends GameMove{

	private static final long serialVersionUID = 1L;
	
	protected int row;
	protected int col;
	protected int newRow;
	protected int newCol;
	
	private int dim;
	
	public AtaxxMove(){}
	
	public AtaxxMove(int row, int col, int newRow, int newCol, Piece p){
		super(p);
		this.row = row;
		this.col = col;
		this.newRow = newRow;
		this.newCol = newCol;
	}
	
	@Override
	public void execute(Board board, List<Piece> pieces) {
		if (!getPiece().equals(board.getPosition(row, col)) ){ //si la pieza que quiere mover no es tuya
			throw new GameError("position (" + row + "," + col + ") is not your piece, select another one!");
		}
		
		if (getPiece() == null){ //si la pieza que quiere mover no es tuya
			throw new GameError("position (" + row + "," + col + ") is empty, select another one!");
		}
		
		if (Math.max(Math.abs(newRow-row), Math.abs(newCol-col)) > 2){ //si se mueve mas de dos casillas de distancia error
			throw new GameError("position (" + newRow + "," + newCol + ") is too far! (Max cells to move are 2)");
		}
		
		if (board.getPosition(newRow, newCol) == null) {
			board.setPosition(newRow, newCol, board.getPosition(row, col)); //mueve la pieza a la nueva posicion
		
			
			this.dim = board.getRows();
			Piece p = board.getPosition(newRow, newCol);
			
			if (Math.max(Math.abs(newRow-row), Math.abs(newCol-col)) == 2){
				board.setPosition(row, col, null); //si se mueve dos casillas la casilla de origen se borra
			}
			
			//Modify pieces around the new piece if not out of bounds, ids are different and not null
			//|0|1|2|
			//|3|x|4|
			//|5|6|7|
			
			for (int i = -1; i < 2; i++) {
	            for (int j = -1; j < 2; j++) {
	            	if ((newRow-1 != -1) && (newRow+1 != dim) && (newCol-1 != -1) && (newCol+1 != dim) && (board.getPosition(newRow+i, newCol+j) != null) && (board.getPosition(newRow+i, newCol+j) != getPiece()) && (board.getPosition(newRow+i, newCol+j).getId() != "*")){
	            		board.setPosition(newRow+i, newCol+j, p);
	            	}
	            }
			}
		}
		else {
			throw new GameError("position (" + newRow + "," + newCol + ") is already occupied!");
		}
	}
	
	@Override
	public GameMove fromString(Piece p, String str) {
		String[] words = str.split(" ");
		if (words.length != 4) {
			return null;
		}

		try {
			int row, col, newRow, newCol;
			row = Integer.parseInt(words[0]);
			col = Integer.parseInt(words[1]);
			newRow = Integer.parseInt(words[2]);
			newCol = Integer.parseInt(words[3]);
			return createMove(row, col, newRow, newCol, p);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public String help() {
		return "'row column newRow newCol', to place the selected piece at the corresponding position.";
	}
	
	public String toString() {
		if (getPiece() == null) {
			return help();
		} else {
			return "Place a piece '" + getPiece() + "' from (" + row + "," + col + ") to (" + newRow + "," + newCol + ")";
		}
	}
	
	protected GameMove createMove(int row, int col, int newRow, int newCol, Piece p) {
		return new AtaxxMove(row, col, newRow, newCol, p);
	}
}
