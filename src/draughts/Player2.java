/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draughts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.util.Pair;

/**
 *
 * @author SHAIKHALVEE
 */
public class Player2 extends Agent{
	int mn = Integer.MIN_VALUE, mx = Integer.MAX_VALUE;
	Play drghts;

	public Player2(String name) {
		super(name);
	}

	@Override
	public void makeMove(Game game) {
		drghts = (Play)game;
		if (drghts.bck) {
			return;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Player2.class.getName()).log(Level.SEVERE, null, ex);
			Platform.runLater(() -> {
				Draughts.init_();
			});
		}
		
		int [][] state = cpy2D(drghts.board);

		int dep = 7;
		Pair<int [][], Integer> p;
		p = maxVal(state, mn, mx, dep);
		if (p.getKey()!= null) {
			drghts.board = p.getKey();
			for (int i = 0; i < 8; i++) {
				if (drghts.board[0][i] == 3) {
					drghts.board[0][i] = 4;
				}
				if (drghts.board[7][i] == 1) {
					drghts.board[7][i] = 2;
				}
			}
		}
		if (role == 0) {
			drghts.p2_pcs = left(drghts.board, 1);
		} 
		else {
			drghts.p1_pcs = left(drghts.board, 0);
		}
	}
	
	int left(int[][] st, int role) {
		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (role == 0) {
					if (st[i][j] == 1 || st[i][j] == 2) {
						cnt++;
					}
				}
				else if (role == 1) {
					if (st[i][j] == 3 || st[i][j] == 4) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}
	
	int[][] cpy2D(int[][] arr) {
		int[][] cpy = new int[8][8];
		for (int i = 0; i < 8; i++) {
			System.arraycopy(arr[i], 0, cpy[i], 0, 8);
		}
		return cpy;
	}
	
	int[][] mkMove(int[][] state, Move mv) {
		int[][] arr = cpy2D(state);
		arr[mv.ty][mv.tx] = arr[mv.fy][mv.fx];
		arr[mv.fy][mv.fx] = 0;
		if (mv.kx != -1) {
			arr[mv.ky][mv.kx] = 0;
		}
		return arr;
	}
	
	Pair<int[][], Integer> maxVal(int[][] state, int alpha, int beta, int dep) {
		List<Move> moves = drghts.getMoves(state, role);
		if (moves.isEmpty()) {
			return new Pair<>(state, mn);
		}
		if (dep == 0) {
			return new Pair<>(state, hr(state));
		}
		int v = mn;
		int[][] moved = null;
		Collections.shuffle(moves);
		for (Move move : moves) {
			int[][] temp = mkMove(state, move);
			Pair<int[][], Integer> P = minVal(temp, alpha, beta, dep-1);
			if (P.getValue() > v) {
				v = P.getValue();
				moved = temp;
			}
			if (v >= beta) {
				return new Pair<>(moved, v);
			}
			alpha = Integer.max(v, alpha);
		}
		return new Pair<>(moved, v);
	}
	
	Pair<int[][], Integer> minVal(int[][] state, int alpha, int beta, int dep) {
		List<Move> moves = drghts.getMoves(state, 1 - role);
		if (moves.isEmpty()) {
			return new Pair<>(state, mx);
		}
		if (dep == 0) {
			return new Pair<>(state, hr(state));
		}
		int v = mx;
		int[][] moved = null;
		Collections.shuffle(moves);
		for (Move move : moves) {
			int[][] temp = mkMove(state, move);
			Pair<int[][], Integer> P = maxVal(temp, alpha, beta, dep-1);
			if (P.getValue() < v) {
				v = P.getValue();
				moved = temp;
			}
			if (v <= alpha) {
				return new Pair<>(moved, v);
			}
			beta = Integer.min(v, beta);
		}
		return new Pair<>(moved, v);
	}
	
	int hr(int[][] state) {
		int v = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (state[i][j] == 1) {
					v++;
				}
				else if (state[i][j] == 3) {
					v--;
				}
				else if (state[i][j] == 2) {
					v += 2;
				}
				else if (state[i][j] == 4) {
					v -= 2;
				}
			}
		}
		if (role == 1) {
			v *= -1;
		}
		return v;
	}
	
}

class Move {
	int fx, fy, tx, ty, kx, ky;

	public Move(int fr, int fc, int tr, int tc) {
		fx = fc;
		fy = fr;
		tx = tc;
		ty = tr;
		kx = ky = -1;
	}
	
}
