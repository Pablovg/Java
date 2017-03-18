package es.ucm.fdi.tp.assignment5.ttt;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.basecode.ttt.TicTacToeFactory;

public class TicTacToeFactoryExt extends TicTacToeFactory {

	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
								Player random, Player ai) {
		new TicTacToeSwingView(g, c,viewPiece,random, ai); 
	}
	
	public TicTacToeFactoryExt() {
		super();
	}
}
