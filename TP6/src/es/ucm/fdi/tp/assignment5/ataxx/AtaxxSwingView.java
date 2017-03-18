package es.ucm.fdi.tp.assignment5.ataxx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.tp.assignment4.ataxx.AtaxxMove;
import es.ucm.fdi.tp.assignment5.SwingView;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public class AtaxxSwingView extends SwingView {

	private static final long serialVersionUID = 1L;
	private BoardComponent boardComponent;
	protected int rowT = -1 ;
	protected int colT = -1;

	public AtaxxSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		
		super (g, c, localPiece, randPlayer, aiPlayer);
		
		if (localPiece != null) {
			this.setTitle("Ataxx  " + localPiece);
		}
		
		else {
			this.setTitle("Ataxx");
		}
	}

	@Override
	protected void initBoardGui() {
		boardComponent = new BoardComponent(7, 7, board);
		boardComponent.setPreferredSize(new Dimension(600, 600));
		mainPanel.add(boardComponent, BorderLayout.CENTER);		
	}

	@Override
	protected void activateBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deActivateBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void redrawBoard() {
		boardComponent.repaint();
		
	}

	@Override
	protected void setColorsandBoard(Map<Piece, Color> pieceColors, Board board) {
		boardComponent.setColors(pieceColors);
		boardComponent.updateBoard(board.getRows(), board.getCols(), board);
		redrawBoard();
	}

	@Override
	protected void clickCell(int row, int col) {
		
		if((rowT != -1) && (colT != -1)) { //Initial position selected
			
			int r = rowT, c = colT;
			
			rowT = -1 ;
			colT = -1;
			
			ctrl.makeMove(new Player(){

				private static final long serialVersionUID = 1L;

				public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
					addMsg("Click on an origin piece" + "\n");
					return new AtaxxMove(c, r, col, row, getTurn());
				}
			});
		}
		
		else { //Select destination
			addMsg( "Click on the destination position" + "\n");
			rowT = row;
			colT = col;	
		}	
	}
}
