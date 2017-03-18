package es.ucm.fdi.tp.assignment5.attt;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.GameRules;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.assignment5.SwingView;
import es.ucm.fdi.tp.basecode.attt.AdvancedTTTMove;
import es.ucm.fdi.tp.basecode.bgame.model.GameMove;

public class AdvanceTTTSwingView extends SwingView {
	
	private static final long serialVersionUID = 1L;

	public int auxrow=-1 ;
	public int auxcol=-1;
	public BoardComponent boardComponent;
	
	public AdvanceTTTSwingView(Observable<GameObserver> g, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		super(g, c, localPiece, randPlayer, aiPlayer);
		
		if (localPiece != null) {
			this.setTitle("Advanced TicTacToe   " + localPiece);
		}
		else {
			this.setTitle("Advanced TicTacToe");
		}
	}



	@Override
	protected void initBoardGui() {
		boardComponent = new BoardComponent(5, 5, board);
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
	protected void setColorsandBoard(Map<Piece, Color> pieceColors,Board board) {	
		boardComponent.setColors(pieceColors);
		boardComponent.updateBoard(board.getRows(), board.getCols(), board);
		redrawBoard();
	}

	@Override
	protected void clickCell(int row, int col) {
		
		ctrl.makeMove(new Player(){

			private static final long serialVersionUID = 1L;

			public GameMove requestMove(Piece p, Board board, List<Piece> pieces, GameRules rules) {
				addMsg("Click on a empty cell" + "\n");
				return new AdvancedTTTMove(col, row, getTurn());
			}
		});
	}
}
