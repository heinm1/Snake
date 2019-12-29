package controler;

import view.View;
import ai.Simulator;
import model.Board;

public class GameLoop implements Runnable {
    private static long delayInterval = 100;
    private View view;
    private Board board;
    private Simulator sim;
    private long delayTimer;

    public GameLoop(Board board, Simulator sim, View view) {
        this.view = view;
        this.board = board;
        this.sim = sim;
        this.delayTimer = System.currentTimeMillis();
    }

    @Override
    public void run() {
        view.paint(board, view.getGraphicsContext2D());
        while (board.getSnake().isSafe()) {

            long lastExecutionDelay = System.currentTimeMillis() - delayTimer;
            if (lastExecutionDelay > delayInterval) {

                if (sim != null) {
                    sim.getMoveSearch();
                }
                board.update();
                view.paint(board, view.getGraphicsContext2D());

                delayTimer = System.currentTimeMillis();
                delayInterval = calculateDelayInterval();
            }
        }
        view.paintEndGame(view.getGraphicsContext2D());
    }

    private int calculateDelayInterval() {
        int score = board.getScore();
        if (score >= 10 && score < 15) {
            return 90;
        } else if (score >= 15 && score < 20) {
            return 80;
        } else if (score >= 20 && score < 25) {
            return 70;
        } else if (score >= 25 && score < 30) {
            return 60;
        } else if (score >= 30 && score < 40) {
            return 50;
        } else if (score >= 40) {
            return 40;
        }
        return 100;
    }
}