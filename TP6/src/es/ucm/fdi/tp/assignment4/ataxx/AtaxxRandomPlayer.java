package es.ucm.fdi.tp.assignment4.ataxx;

import java.util.List;

import es.ucm.fdi.tp.basecode.bgame.Utils;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameError;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxRandomPlayer extends Player {

	private static final long serialVersionUID = 1L;

	@Override
	public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
		if (board.isFull()) {
			throw new GameError("The board is full, cannot make a random move!!");
		}

		List<GameMove> validMoves = rules.validMoves(board, pieces, p);
	    
		if (validMoves.size() == 0){ //There are no valid moves
	        throw new GameError("Can't Move!");
	    }
		
		int rand = Utils.randomInt(validMoves.size());
		
		return validMoves.get(rand);
	}
}
