package es.ucm.fdi.tp.assignment5.attt;

import es.ucm.fdi.tp.basecode.attt.AdvancedTTTFactory;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AdvancedTTTFactoryExt extends AdvancedTTTFactory {
	
	@Override
	public void createSwingView(final Observable<GameObserver> g, final Controller c, final Piece viewPiece,
								Player random, Player ai) {
		new AdvanceTTTSwingView(g, c, viewPiece, random, ai); 
	}
	
	public AdvancedTTTFactoryExt() {
		super();
	}
}
