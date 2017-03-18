package es.ucm.fdi.tp.assignment5.ataxx;

import es.ucm.fdi.tp.assignment4.ataxx.AtaxxFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxFactoryExt extends AtaxxFactory {

	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
								Player random, Player ai) {
		
		new AtaxxSwingView(g, c,viewPiece,random, ai); 
	}
	
	public AtaxxFactoryExt() {
		super();
	}
	
	public AtaxxFactoryExt(int dim, int obstaclesNumber) { 
		super(dim, obstaclesNumber);
	}

}
