package es.ucm.fdi.tp.assignment5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.assignment5.Main5.PlayerMode;
import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.Board;
import es.ucm.fdi.tp.basecode.bgame.model.Game.State;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;

public abstract class SwingView extends JFrame implements GameObserver{
	
	private static final long serialVersionUID = 1L;
	
	protected Controller ctrl;
	protected Piece localPiece;
	protected Piece turn;
	protected Board board;
	protected List<Piece> pieces;
	protected Map<Piece, Color> pieceColors;
	protected Map<Piece, PlayerMode> playerTypes;
	protected HashMap<Piece, Integer> piecesPerPlayer;
	protected Player randPlayer;	
	protected Player aiPlayer;
	protected Color[] defaultColors  = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
	private String[] modes = {"MANUAL", "RANDOM", "INTELLIGENT"};
	protected String[] piecesIds;

	protected JPanel mainPanel;
	protected JPanel controlPanel;
	protected JTextArea outputPanel;
	protected JTable tablePanel;
	protected TableModel tableModel;
	protected JPanel colorPanel;
	protected JComboBox<String> piecesColorComboBox;
	protected JButton chooseColorButton;
	private JPanel modePanel;
	private JButton setButton;
	protected JComboBox<String> piecesModeComboBox1;
	protected JComboBox<String> piecesModeComboBox2;
	private JPanel movePanel;
	private JButton randomButton;
	private JButton intelligentButton;
	private JPanel optionPanel;
	private JButton quitButton;
	private JButton restartButton;
	
	final protected Piece getTurn() { return turn; }
	final protected Board getBoard() { return board; }
	final protected Board getPieces() { return (Board) pieces; }
	final protected Color getPieceColor(Piece p) { return pieceColors.get(p); }
	final protected Color setPieceColor(Piece p, Color c) { return pieceColors.put(p,c); }
	final protected PlayerMode setPieceMode(Piece p, PlayerMode m) { return playerTypes.put(p,m); }
	final protected void addMsg(String msg) {outputPanel.append(msg);}
	
	protected abstract void initBoardGui();
	protected abstract void activateBoard();
	protected abstract void deActivateBoard();
	protected abstract void redrawBoard();
	protected abstract void setColorsandBoard(Map<Piece,Color> pieceColors,Board board);
	protected abstract void clickCell(int row, int col);
	
	final protected void resetPiecesPerPlayer() {		
		for (int i = 0; i < pieces.size(); i++) { 
			piecesPerPlayer.put(pieces.get(i), 0);
		}
	}
	
	public SwingView( Observable< GameObserver > g, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer){
		this.ctrl = c;
		this.localPiece = localPiece;
		this.randPlayer = randPlayer;
		this.aiPlayer = aiPlayer;
		
		initGUI();
		g.addObserver(this);
	}
	
	private void initGUI() { 
		
		//-----------------------MAIN PANEL-----------------------//
		mainPanel = new JPanel(new BorderLayout());
		
		//-----------------------CONTROL PANEL-----------------------//
		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		
		//-----------------------OUTPUT PANEL-----------------------//
		outputPanel = new JTextArea(350, 100);
		outputPanel.setEditable(false);
		outputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		outputPanel.setLineWrap(true);
		outputPanel.setWrapStyleWord(true);

		JScrollPane scrollOutput = new JScrollPane(outputPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollOutput.setPreferredSize(new Dimension(350, 200));
		scrollOutput.setBorder(BorderFactory.createTitledBorder("Status Messages"));
		
		controlPanel.add(scrollOutput);
		//-----------------------OUTPUT PANEL-----------------------//
		
		//-----------------------PLAYER TABLE PANEL-----------------------//
		tableModel = new TableModel();
		
		tablePanel = new JTable(tableModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
		        
				Component comp = super.prepareRenderer(renderer, row, col);
		    	
		        comp.setBackground(pieceColors.get(pieces.get(row)));
		        return comp;
		    }
		};
		
		JScrollPane scrollTable = new JScrollPane(tablePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollTable.setPreferredSize(new Dimension(350, 100));
		scrollTable.setBorder(BorderFactory.createTitledBorder("Player Information"));
		
		controlPanel.add(scrollTable);
		//-----------------------PLAYER TABLE PANEL-----------------------//
		
		//-----------------------PIECE COLOR PANEL-----------------------//
		colorPanel = new JPanel();
		piecesColorComboBox = new JComboBox<String>();
		
		colorPanel.add(piecesColorComboBox);
		
		chooseColorButton = new JButton("Choose color");
		chooseColorButton.addActionListener(new ButtonHandler());
		
		colorPanel.add(chooseColorButton);
		colorPanel.setPreferredSize(new Dimension(350, 100));
		colorPanel.setBorder(BorderFactory.createTitledBorder("Piece Colors"));
		controlPanel.add(colorPanel);
		//-----------------------PIECE COLOR PANEL-----------------------//
		
		//-----------------------PLAYER MODE PANEL-----------------------//
		modePanel = new JPanel();
		piecesModeComboBox1 = new JComboBox<String>();
		piecesModeComboBox2 = new JComboBox<String>();
		
		modePanel.add(piecesModeComboBox1);
		modePanel.add(piecesModeComboBox2);
		
		setButton = new JButton("Set");
		setButton.addActionListener(new ButtonHandler());
		
		modePanel.add(setButton);
		modePanel.setPreferredSize(new Dimension(350, 100));
		modePanel.setBorder(BorderFactory.createTitledBorder("Player Modes"));
		controlPanel.add(modePanel);
		//-----------------------PLAYER MODE PANEL-----------------------//
		
		//------------------------AUTO MOVE PANEL------------------------//
		movePanel = new JPanel();
		
		randomButton = new JButton("Random");
		intelligentButton = new JButton("Intelligent");
		
		randomButton.addActionListener(new ButtonHandler());
		intelligentButton.addActionListener(new ButtonHandler());
		
		movePanel.add(randomButton);
		movePanel.add(intelligentButton);
		movePanel.setPreferredSize(new Dimension(350, 50));
		movePanel.setBorder(BorderFactory.createTitledBorder("Automatic Moves"));
		controlPanel.add(movePanel);
		//------------------------AUTO MOVE PANEL------------------------//
		
		//------------------------QUIT/RESTART PANEL------------------------//
		optionPanel = new JPanel();
		
		quitButton = new JButton("Quit");
		restartButton = new JButton("Restart");
		
		quitButton.addActionListener(new ButtonHandler());
		restartButton.addActionListener(new ButtonHandler());
		
		optionPanel.add(quitButton);
		
		if (localPiece == null) {
			optionPanel.add(restartButton); //Multiview
		}
		
		optionPanel.setPreferredSize(new Dimension(350, 50));
		optionPanel.setBorder(BorderFactory.createTitledBorder("Options"));
		controlPanel.add(optionPanel);
		//------------------------QUIT/RESTART PANEL------------------------//
		
		mainPanel.add(controlPanel, BorderLayout.LINE_END);
		//-----------------------CONTROL PANEL-----------------------//
		
		//------------------------BOARD PANEL------------------------//
		initBoardGui();
		//------------------------BOARD PANEL------------------------//
		
		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 650);
		this.setVisible(true);
	}
	
	private void decideMakeAutomaticMove(){
		ctrl.makeMove(aiPlayer);
	}
	
	private void decideMakeRandomMove() {
		ctrl.makeMove(randPlayer);
	}
	
	@Override
	public void onGameStart(Board board2, String gameDesc, List<Piece> pieces2, Piece turn2) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 	
				board = board2;
				pieces = pieces2;
				turn = turn2;
				
				addMsg("Turn for " + turn + "\n");
			
				pieceColors = new HashMap<Piece, Color>();
				piecesPerPlayer = new HashMap<Piece,Integer>(); 
				playerTypes = new HashMap<Piece, PlayerMode>();
		    
				
				for (int i = 0; i < pieces.size(); i++) {
					pieceColors.put(pieces.get(i), defaultColors[i]);
					piecesPerPlayer.put(pieces.get(i), 0);
					playerTypes.put(pieces.get(i), PlayerMode.MANUAL);
				}
				
				piecesIds = new String[pieces.size()];
				setColorsandBoard(pieceColors, board);
				tableModel.refresh();
				redrawBoard();
				
				piecesIds = new String[pieces.size()];
		
				for (int i = 0; i < pieces.size(); i++) {
					piecesIds[i] = pieces.get(i).toString();
					piecesColorComboBox.addItem(piecesIds[i]);
					piecesModeComboBox1.addItem(piecesIds[i]);
					piecesModeComboBox2.addItem(modes[i]);
				}
			}
		});
	}

	@Override
	public void onGameOver(Board board, State state, Piece winner) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() {  
				redrawBoard();
				addMsg("Game Over! " + "\n");
			
				if(winner != null) {
					addMsg("WINNER "+ winner + "\n");
				}
			
				else {
					addMsg("DRAW" + "\n");
				}
			}
		});
	}

	@Override
	public void onMoveStart(Board board, Piece turn) {
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				redrawBoard();
				tableModel.refresh();
				piecesColorComboBox.repaint();
			}
		});
		
	}

	@Override
	public void onMoveEnd(Board board, Piece turn, boolean success) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				redrawBoard();
				tableModel.refresh();
			}
		});
	}

	@Override
	public void onChangeTurn(Board board, Piece turn2) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {  
				turn = turn2;
				
				if (playerTypes.get(turn) == PlayerMode.AI || playerTypes.get(turn) == PlayerMode.RANDOM) {
					decideMakeAutomaticMove();
				}
				
				addMsg("Turn for " + turn);
				
				if (turn == localPiece){ //Multiview
					addMsg(" (<---)");
				}
				
				addMsg("\n");
				redrawBoard();
				tableModel.refresh();
			}
		});
		
	}

	@Override
	public void onError(String msg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				addMsg(msg + "\n");	
			}
		});
		
	}
	
	
	public class BoardComponent extends JComponent {
		
		private static final long serialVersionUID = 1L;
		
		private int cellH = 50;
		private int  cellW = 50;

		private int rows;
		private int cols;
		private Board board;
		private Map<Piece,Color> piecesColor;

		public BoardComponent(int rows, int cols,Board board) {
			this.board = board;
			updateBoard(rows, cols, board);
			initGUI();
		}

		public void updateBoard(int rows, int cols, Board board) {
			this.rows = rows;
			this.cols = cols;
			this.board = board;			
		}

		private void initGUI() {
			MouseControl mouseListener = new MouseControl();
			addMouseListener(mouseListener);
			repaint();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			cellW = this.getWidth() / cols;
			cellH = this.getHeight() / rows;
			
			if (pieces!= null){
				resetPiecesPerPlayer();
			}
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					drawCell(i, j, g);
				}
			}
		}

		private void drawCell(int row, int col, Graphics g) {
			int x = col * cellW;
			int y = row * cellH;


			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x + 2, y + 2, cellW - 4, cellH - 4);

			if (board == null) return;
			
			if (board.getPosition(row, col) == null){
				g.setColor(Color.GRAY);
				return;
			}
			
			else if (!piecesColor.containsKey(board.getPosition(row, col))){
				g.setColor(Color.BLACK);
			}
			
			else {
				g.setColor(piecesColor.get(board.getPosition(row, col)));
				piecesPerPlayer.put((board.getPosition(row, col)), ((piecesPerPlayer.get(board.getPosition(row, col)))+1));
			}

			g.fillOval(x + 4, y + 4, cellW - 8, cellH - 8);
			g.setColor(Color.black);
			g.drawOval(x + 4, y + 4, cellW - 8, cellH - 8);
		}

		public void setColors(Map<Piece, Color> pieceColors) {
			this.piecesColor = pieceColors;
		}
		
		
		public class MouseControl implements MouseListener {
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse Pressed: " + "(" + e.getX() + "," + e.getY() + ")");	
			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Mouse Exited Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Mouse Entered Component: " + "(" + e.getX() + "," + e.getY() + ")");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse Button "+ e.getButton() + " Clicked at " + "(" + e.getX() + "," + e.getY() + ")");
				
				if ((localPiece == null) || (localPiece == turn)) { 
					clickCell(e.getX() / cellW, e.getY() / cellH);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse Released: " + "(" + e.getX() + "," + e.getY() + ")");
			}
		}
	}
	
	public class TableModel extends AbstractTableModel { //Model for the player table panel
		
		private static final long serialVersionUID = 1L;

		private String[] header = { "PLAYER", "MODE", "#PIECES" };
		int row;

		TableModel(){}
		
		@Override
		public String getColumnName(int col) {
			return header[col];
		}

		@Override
		public int getColumnCount() {
			return header.length;
		}

		@Override
		public int getRowCount() {
			return pieces != null ? pieces.size(): 0;
		}

		@Override
		public Object getValueAt(int rowI, int columnI) {
			
			if (columnI == 0) {
				return pieces.get(rowI);
			}
			
			if (columnI == 1){	
				
				if (playerTypes.get(pieces.get(rowI)) == PlayerMode.MANUAL ) {
					return "MANUAL";
				}
				
				if (playerTypes.get(pieces.get(rowI)) == PlayerMode.RANDOM ) {
					return "RANDOM";
				}
				
				if(playerTypes.get(pieces.get(rowI)) == PlayerMode.AI ) {
					return "AI";
				}
			}
			
			if (columnI == 2) {
				return getBoard().getPieceCount(pieces.get(rowI));
			}
			
			return null;
		}
	 
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
		public void refresh() {			
			fireTableDataChanged();
			fireTableStructureChanged();
			repaint();
		}
	}
	
	public class ButtonHandler implements ActionListener { //Handler for all buttons

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == chooseColorButton) {
				String id = (String)piecesColorComboBox.getSelectedItem();
				Piece choice = null;
				
				for (int i = 0; i < pieces.size(); i++) {
					if (pieces.get(i).getId() == id) {
						choice = pieces.get(i);
					}
				}
				
				Color color = JColorChooser.showDialog(null, "Choose new color", getPieceColor(choice));
				setPieceColor(choice, color);
				repaint();
			}
			
			else if (e.getSource() == setButton) {
				String id = (String)piecesModeComboBox1.getSelectedItem();
				String modeId = (String)piecesModeComboBox2.getSelectedItem();
				Piece choice = null;
				
				for (int i = 0; i < pieces.size(); i++) {
					if (pieces.get(i).getId() == id) {
						choice = pieces.get(i);
					}
				}
				 
				if (modeId == "MANUAL") {
					setPieceMode(choice, PlayerMode.MANUAL);
				}
				
				else if (modeId == "RANDOM") {
					setPieceMode(choice, PlayerMode.RANDOM);
				}
				
				else {
					setPieceMode(choice, PlayerMode.AI);
				}
				
				repaint();
				
				if ((choice == turn) && ((PlayerMode)playerTypes.get(choice) != PlayerMode.MANUAL)){ //If u change the actual move piece its automatically move.
					decideMakeRandomMove();
				}
			}
			
			else if (e.getSource() == randomButton) {
				decideMakeRandomMove();
			}
			
			else if (e.getSource() == intelligentButton) {
				decideMakeAutomaticMove();
			}
			
			else if (e.getSource() == quitButton) {
				ctrl.stop();
				System.exit(ABORT);
			}
			
			else if (e.getSource() == restartButton) {
				ctrl.restart();
				piecesColorComboBox.removeAllItems();
				piecesModeComboBox1.removeAllItems();
				piecesModeComboBox2.removeAllItems();
			}
		}
	}
}
