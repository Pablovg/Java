package es.ucm.fdi.tp.assignment4.ataxx;

import java.util.ArrayList;
import java.util.List;
import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.FiniteRectBoard;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Pair;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;

public class AtaxxRules implements GameRules {

	protected final Pair<State, Piece> gameInPlayResult = new Pair<State, Piece>(State.InPlay, null);
	private int obstacles;
	private int dim;
	
	public AtaxxRules(int dim, int obstacles) { //Establece la dimension del tablero
		if (dim < 5) {
			throw new GameError("Dimension must be at least 5: " + dim); //el minimo es 5
		} else {
			this.dim = dim;
		}
		this.obstacles = obstacles;
	}
	
	@Override
	public String gameDesc() {
		return "Ataxx " + dim + "x" + dim;
	}
	
	@Override
	public Board createBoard(List<Piece> pieces) {
		int count = 0;
		
		Board board = new FiniteRectBoard(dim, dim); //crea un tablero cuadrado con la dimesion dada
		
		board.setPosition(0, 0, pieces.get(0)); //X
		board.setPosition(board.getRows() - 1, board.getCols() - 1, pieces.get(0)); //X
		board.setPosition(board.getRows() - 1, 0, pieces.get(1));	//O
		board.setPosition(0, board.getCols() - 1, pieces.get(1));	//O
		
		if (pieces.size() == 3) {
			board.setPosition(board.getRows() / 2, 0, pieces.get(2));	//R
			board.setPosition(board.getRows() / 2,  board.getCols() - 1, pieces.get(2));	//R
		}
		
		if (pieces.size() == 4) {
			board.setPosition(board.getRows() / 2, 0, pieces.get(2));	//R
			board.setPosition(board.getRows() / 2,  board.getCols() - 1, pieces.get(2));	//R
			board.setPosition(0, board.getCols() / 2, pieces.get(3));	//B
			board.setPosition(board.getRows() - 1, board.getCols() / 2, pieces.get(3));	//B
		}
		
		while (count < obstacles){ //Place a given number of obstacles randomly (5 by default)
			int row = Utils.randomInt(dim);
			int col = Utils.randomInt(dim);
			
			if (board.getPosition(row, col) == null){
				board.setPosition(row, col, new Piece("*"));
				count++;
			}
		}
		
		return board;
	}
	
	
	Piece pickObstPiecs(List<Piece> pieces) {
		int i=0;
		while (true) {
			Piece p = new Piece("*#"+i);
			if ( !pieces.contains(p) ) return p;
			i++;
		}
	}
	
	@Override
	public Piece initialPlayer(Board board, List<Piece> playersPieces) {
		return nextPlayer(board, playersPieces, playersPieces.get(playersPieces.size()-1));
	}

	@Override
	public int minPlayers() {
		return 2;
	}

	@Override
	public int maxPlayers() {
		return 4;
	}

	@Override
	public Pair<State, Piece> updateState(Board board, List<Piece> pieces, Piece turn) {
		
		Pair<State, Piece> pair = new Pair<State, Piece>(State.InPlay, turn);
		int pos = 0, numPieces = 0, max = -1;
		boolean draw = false;
		
		
		int[] count = new int[pieces.size()];
		
		for (int t = 0; t < pieces.size(); t++){ //initialize array to cero
			count[t] = 0;
		}
		
		for (int i = 0; i < dim; i++){ //count the number of pieces of each piece
			for (int j = 0; j < dim; j++){
				for (int h = 0; h < pieces.size(); h++){
					if (board.getPosition(i, j) != null && board.getPosition(i, j).equals(pieces.get(h))){
						count[h]++;
					}
				}	
			}
		}
		
		for (int k = 0; k < pieces.size(); k++){ 
			if (count[k] == max){ //in case there is a draw
				draw = true;
			}
			if (count[k] >  max){ //get highest position
				pos = k;
				max = count[k];
			}
			if (count[k] != 0){
				numPieces++;
			}
		}
		
		if (board.isFull() || numPieces == 1){
			if (draw){
				pair = new Pair<State, Piece>(State.Draw, null);
			}
			else{
				pair = new Pair<State, Piece>(State.Won, pieces.get(pos));
			}
		}
		
		return pair;
	}

	@Override
	public Piece nextPlayer(Board board, List<Piece> pieces, Piece lastPlayer) {
		Piece nextPlayer = null;
		int count = 0;
		
		if ((pieces.indexOf(lastPlayer)) == pieces.size()-1){
			nextPlayer = pieces.get(0);
		}
		else{
			nextPlayer = pieces.get((pieces.indexOf(lastPlayer) + 1));
		}
				
		while (validMoves(board, pieces, nextPlayer).size() == 0 && count < pieces.size()-1){ //nextPlayer can't move
			
			if ((pieces.indexOf(nextPlayer)) == pieces.size()-1){
				nextPlayer = pieces.get(0);
			}
			else{
				nextPlayer = pieces.get((pieces.indexOf(nextPlayer) + 1));
			}
				
			count++;
		}
		
		if (count == pieces.size()-1){
			nextPlayer = null;
		}
		
		return nextPlayer;
	}
	
	@Override
	public double evaluate(Board board, List<Piece> pieces, Piece turn) {
		return 0;
	}

	@Override
	public List<GameMove> validMoves(Board board, List<Piece> playersPieces, Piece turn) {
		List<GameMove> moves = new ArrayList<GameMove>();

		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {	//loop throw all board
				if ( turn.equals(board.getPosition(i, j)) ){
					for (int h = -2; h <= 2; h++){
						for (int k = -2; k <= 2; k++) { //loop throw possible positions
							if (i + h >= 0 && i + h < dim && j + k >= 0 && j + k < dim){
								if (board.getPosition(i + h, j + k) == null){
									moves.add(new AtaxxMove(i, j, h, k, turn));
								}
							}
						}
					}	
				}
			}
		}
		
		return moves;
	}
	
}
