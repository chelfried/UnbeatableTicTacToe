package org.helfried.tictactoeunb.controllers;

import org.helfried.tictactoeunb.core.GameBoard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import static org.helfried.tictactoeunb.core.AI.*;
import static org.helfried.tictactoeunb.core.GameActions.*;
import static org.helfried.tictactoeunb.core.GameBoard.*;

@Controller

public class MainController {

    private MainController() {
    }

    @GetMapping("/")
    public ModelAndView selectPlayer (){
        int gameID = createNewGameBoard();
        resetGameBoard(gameID);
        return new ModelAndView("redirect:/" + gameID);
    }

    @GetMapping("/{gameID}")
    public ModelAndView reset(@PathVariable Integer gameID) {
        resetGameBoard(gameID);
        return new ModelAndView("selectPlayer");
    }

    @PostMapping("/{gameID}/{player}")
    public ModelAndView selectPlayer(@PathVariable Integer gameID, @PathVariable Integer player) {
        setPlayerHuman(gameID, player);
        if (getPlayerHuman(gameID) == 1) {
            setPlayerTurn(gameID,1);
        } else {
            setPlayerTurn(gameID,2);
            actionByAI(gameID);
        }
        return new ModelAndView("redirect:/" + gameID + "/tictactoe");
    }

    @GetMapping("/{gameID}/{gameID}")
        public ModelAndView reset2(@PathVariable Integer gameID) {
        resetGameBoard(gameID);
        return new ModelAndView("redirect:/" + gameID);
    }

    @GetMapping("/{gameID}/tictactoe")
    public ModelAndView playGame(@PathVariable Integer gameID) {
        if (getPlayerTurn(gameID) == getPlayerAI(gameID) && checkForGameEnd(gameID) == null) {
            actionByAI(gameID);
        }
        int[] board = GameBoard.getActiveGameBoard(gameID);
        ModelAndView modelAndView = new ModelAndView("tictactoe");
        String[] field = {"field0", "field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8"};
        for (int i = 0; i < 9; i++) {
            if (board[i] == 1) {
                modelAndView.addObject(field[i], "X");
            } else if (board[i] == 2) {
                modelAndView.addObject(field[i], "O");
            }
        }
        modelAndView.addObject("gameEnded", checkForGameEnd(gameID));
        return modelAndView;
    }

    @PostMapping("{gameID}/tictactoe/{field}")
    public ModelAndView playerMakesMove(@PathVariable Integer gameID, @PathVariable Integer field) {
        if (getField(gameID, field) == 0 && getPlayerTurn(gameID) != 0) {
            humanPlayerChoice(gameID, field);
            setPlayerTurn(gameID, getPlayerAI(gameID));
        }
        return new ModelAndView("redirect:/" + gameID + "/tictactoe");
    }

}
