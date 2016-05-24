/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core;

import avalam_s6.Core.File_IO.Level_Parser;
import avalam_s6.Core.File_IO.SaveParser_Writer;
import avalam_s6.Core.Globals.AvalamColor;
import avalam_s6.Core.Globals.Input;
import avalam_s6.Core.Globals.SetupManager;
import avalam_s6.Exceptions.GridCharException;
import avalam_s6.Exceptions.GridSizeException;
import avalam_s6.Player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.Timer;
import avalam_s6.GUI.GuiManager_INTERFACE;
import avalam_s6.GUI.Main_Frame;
import avalam_s6.Player.AIPlayer;
import avalam_s6.Player.AIPlayerEasy;
import avalam_s6.Player.ControlledPlayer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sazeratj
 */
public class Local_Avalam_Game implements Game_INTERFACE, ActionListener {
    private static final int NB_PLAYERS = 2;
    private final GuiManager_INTERFACE gui;
    private Grid grid;
    private Player[] players;
    private Stack<Move> history;
    private Stack<Move> cancelled_moves;
    private int current_player;
    private int nbTurns;
    /* -- */
    private boolean isTurnFinished;
    private Timer t;
    
    public Local_Avalam_Game(GuiManager_INTERFACE pGui) throws GridSizeException, GridCharException, IOException {
        this(pGui , new Grid( new Level_Parser("default").readLevel(), "default" ) , new ControlledPlayer("Jon Doe", AvalamColor.WHITE, Owner.PLAYER_1) , new AIPlayerEasy("Bot_Frank", AvalamColor.BLACK, Owner.PLAYER_2) ,new Stack<>(),new Stack<>(),0,0);
    }
    
    public Local_Avalam_Game(GuiManager_INTERFACE pGui, Grid pGrid, Player pPlayer1, Player pPlayer2, Stack<Move> pUndo, Stack<Move> pRedo, int pCurrent, int pTurns) throws GridSizeException {
        this.gui = pGui;
        this.grid = pGrid;
        this.players = new Player[2];
        this.players[0] = pPlayer1;
        this.players[1] = pPlayer2;
        this.history = pUndo;
        this.cancelled_moves = pRedo;
        this.current_player = pCurrent;
        this.nbTurns = pTurns;
        
        this.initGame();
    }
    
    private void initGame() {
        this.t = new Timer(100, (ActionListener) this);
        this.isTurnFinished = false;
        Input.resetClick();
        Input.setInputGame(this);
    }
    
    //TODO: Check user is able to undo (GUI check if history is empty and call or not this function)
    @Override
    public void undo() {
        if(! this.history.isEmpty()) {
            this.cancelled_moves.add(this.history.pop());
            this.grid.undoMove(this.cancelled_moves.lastElement());
        }
    }
        
    //TODO: Check user is able to redo (GUI check if cancelled_moves is empty and call or not this function)
    @Override
    public void redo() {
        if(! this.cancelled_moves.isEmpty()){
            this.history.add(this.cancelled_moves.pop());
            this.grid.moveCell(this.history.lastElement().getC_src(), this.history.lastElement().getC_dst());
        }
    }

    @Override
    public Timer getTimer() {
        return this.t;
    }

    @Override
    public Grid getGrid() {
        return this.grid;
    }
    
    @Override
    public void setGrid(Grid g) {
        this.grid = g;
    }

    @Override
    public Player getCurrentPlayer() {
        return this.players[this.current_player];
    }
    
    /**
     * Getter
     * @return history
     */
    public Stack<Move> getHistory() {
        return this.history;
    }
    
    /**
     * Getter
     * @return cancelled_moves
     */
    public Stack<Move> getCancelled_moves() {
        return this.cancelled_moves;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.playATurn();
    }
    
    /**
     * Turn Logic
     */
    private void playATurn() {
        if(this.isTurnFinished){
            this.changeNbTurns(1);
            int w = winCheck();
            switch (w) {
                case 1:
                case 2:
                case 3:
                    t.stop();
                    this.winningProcedure(w);
//                    this.gui.render();
                    return;
                case 0:
                default:
                    this.isTurnFinished = false;
                    break;
            }
        }
        if(this.players[this.current_player].isAI()) {
            ((AIPlayer)this.players[this.current_player]).setGame(this);
        }
        //System.out.println("Joueur : "+this.current_player);
        Move m = this.players[this.current_player].play();
        if(m != null){
            if(this.players[this.current_player].isAI()) { // IA
                this.grid.moveCell(m.getC_src(), m.getC_dst());
                this.history.add(m);
                this.isTurnFinished = true;
            } else { // JOUEUR
                if(this.grid.canStack(this.grid.getCellAt(m.getC_src()), this.grid.getCellAt(m.getC_dst()))) { // MOVE OK
                    this.grid.moveCell(m.getC_src(), m.getC_dst());
                    this.history.add(m);
                    this.isTurnFinished = true;
                }  else {
 //TODO                   /* Afficher warning de deplacement */
                }
            }
            this.cancelled_moves.clear();
        }        
        this.gui.render();
    }

    
    /**
     * Tells if a game has been won.
     * @return 1 or 2 if player 1 or 2 won, 3 in case of a null match, 0 if game isn't finished.
     */
    private int winCheck() {
        Coordinate[] c = new Coordinate[9];
        for(int i=0;i<9;i++) {
            c[i] = new Coordinate();
        }
        int score_p1 = 0;
        
        for(int x=0;x<9;x++) {
            for(int y=0;y<9;y++) {
                c[0].setX(x);   c[0].setY(y);
                c[1].setX(x-1); c[1].setY(y-1);
                c[2].setX(x-1); c[2].setY(y);
                c[3].setX(x-1); c[3].setY(y+1);
                c[4].setX(x);   c[4].setY(y-1);
                c[5].setX(x);   c[5].setY(y+1);
                c[6].setX(x+1); c[6].setY(y-1);
                c[7].setX(x+1); c[7].setY(y);
                c[8].setX(x+1); c[8].setY(y+1);
                for (int i=1;i<9;i++) {
                    if(this.grid.getCellAt(c[0]).getState().getValue() != CellState.RESTRICTED.getValue() || this.grid.getCellAt(c[0]).getState().getValue() != CellState.EMPTY.getValue()){
                        if(c[i].isValid() && this.grid.getCellAt(c[i]).getState().getValue() != CellState.RESTRICTED.getValue() && this.grid.getCellAt(c[i]).getState().getValue() != CellState.EMPTY.getValue()) {
                            if(this.grid.canStack(this.grid.getCellAt(c[0]),this.grid.getCellAt(c[i]))) {                      
//                                System.out.println("x = "+ x+ ", y = "+y+", c[0] = "+ c[0]+", cell = "+this.grid.getCellAt(c[0]).getState().getValue()+"c["+i+"] = "+c[i]+", cell = "+this.grid.getCellAt(c[i]).getState().getValue()+".");
                                return 0;
                            }
                        }
                    }                     
                }
                if(this.grid.getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_1.getValue()) {
                    score_p1++;
                } else if(this.grid.getCellAt(c[0]).getOwner().getValue() == Owner.PLAYER_2.getValue()) {
                    score_p1--;
                }
            }
        }
        if (score_p1 > 0) {
            return 1;
        } else if (score_p1 == 0) {
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * Takes care of what happens when someone wins.
     * @param i the id of the winner (3 if null match).
     */
    private void winningProcedure(int i) {
        /* Appel à GUI */
        if(i<3) {
            ((Main_Frame)this.gui).setVictoryScreen(this.players[i-1].getName()+" "+SetupManager.getElement("Fin"),this.grid);
        } else {
            ((Main_Frame)this.gui).setVictoryScreen(SetupManager.getElement("Egalité"),this.grid);
        }
    }

    @Override
    public void addMoveToHistory(Move m) {
        this.history.add(m);
    }
    
    public void changeNbTurns(int n){
        this.nbTurns += n;
        this.current_player = this.nbTurns%NB_PLAYERS;
    }

    public Player[] getPlayers() {
        return this.players;
    }
    
    public int getTurns() {
        return this.nbTurns;
    }
}