/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draughts;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author SHAIKHALVEE
 */
public class Play extends Game {
	int[][] board;
	int p1_pcs, p2_pcs;
	Text tx, tx2, tx3, tf1, tf2;
	Button b;
	Group grid;
	int st;
	boolean stuck, bck;
	static int H = 75, W = 80;

	public Play(Agent a, Agent b) {
		super(a, b);
		super.name = "Draughts";
		st = 1;
	}

	@Override
	boolean isFinished() {
		if (bck) {
			return true;
		}
		if (p1_pcs == 0) {
			winner = agent[1];
			return true;
		}
		else if (p2_pcs == 0) {
			winner = agent[0];
			return true;
		}
		List<Move> moves = getMoves(board, turn);
		if (moves.isEmpty()) {
			moves = getMoves(board, 1 - turn);
			if (moves.isEmpty()) {
				stuck = true;
				return true;
			}
			winner = agent[1 - turn];
			return true;
		}
		return false;
	}

	List<Move> getMoves(int[][] state, int rl) {
		List<Move> moves = new ArrayList<>();
		if (rl == 0) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (state[i][j] == 1 || state[i][j] == 2) {
						int c1 = j + 1, c2 = j - 1, r1 = i + 1;
						if (r1 < 8 && c1 < 8) {
							if (state[r1][c1] == 0) {
								Move mv = new Move(i, j, r1, c1);
								moves.add(mv);
							}
							else if (state[r1][c1] == 3 || state[r1][c1] == 4) {
								int dr1 = r1 + 1, dc1 = c1 + 1;
								if (dr1 < 8 && dc1 < 8 && state[dr1][dc1] == 0) {
									Move mv = new Move(i, j, dr1, dc1);
									mv.kx = c1;
									mv.ky = r1;
									moves.add(mv);
								}
							}
						}
						if (r1 < 8 && c2 >= 0) {
							if (state[r1][c2] == 0) {
								Move mv = new Move(i, j, r1, c2);
								moves.add(mv);
							}
							else if (state[r1][c2] == 3 || state[r1][c2] == 4) {
								int dr1 = r1 + 1, dc1 = c2 - 1;
								if (dr1 < 8 && dc1 >= 0 && state[dr1][dc1] == 0) {
									Move mv = new Move(i, j, dr1, dc1);
									mv.kx = c2;
									mv.ky = r1;
									moves.add(mv);
								}
							}
						}
						if (state[i][j] == 2) {
							c1 = j + 1; c2 = j - 1; r1 = i - 1;
							if (r1 >= 0 && c1 < 8) {
								if (state[r1][c1] == 0) {
									Move mv = new Move(i, j, r1, c1);
									moves.add(mv);
								}
								else if (state[r1][c1] == 3 || state[r1][c1] == 4) {
									int dr1 = r1 - 1, dc1 = c1 + 1;
									if (dr1 >= 0 && dc1 < 8 && state[dr1][dc1] == 0) {
										Move mv = new Move(i, j, dr1, dc1);
										mv.kx = c1;
										mv.ky = r1;
										moves.add(mv);
									}
								}
							}
							if (r1 >= 0 && c2 >= 0) {
								if (state[r1][c2] == 0) {
									Move mv = new Move(i, j, r1, c2);
									moves.add(mv);
								}
								else if (state[r1][c2] == 3 || state[r1][c2] == 4) {
									int dr1 = r1 - 1, dc1 = c2 - 1;
									if (dr1 >= 0 && dc1 >= 0 && state[dr1][dc1] == 0) {
										Move mv = new Move(i, j, dr1, dc1);
										mv.kx = c2;
										mv.ky = r1;
										moves.add(mv);
									}
								}
							}
						}
					}
				}
			}
		}
		else {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (state[i][j] == 3 || state[i][j] == 4) {
						int c1 = j + 1, c2 = j - 1, r1 = i - 1;
						if (r1 >= 0 && c1 < 8) {
							if (state[r1][c1] == 0) {
								Move mv = new Move(i, j, r1, c1);
								moves.add(mv);
							}
							else if (state[r1][c1] == 1 || state[r1][c1] == 2) {
								int dr1 = r1 - 1, dc1 = c1 + 1;
								if (dr1 >= 0 && dc1 < 8 && state[dr1][dc1] == 0) {
									Move mv = new Move(i, j, dr1, dc1);
									mv.kx = c1;
									mv.ky = r1;
									moves.add(mv);
								}
							}
						}
						if (r1 >= 0 && c2 >= 0) {
							if (state[r1][c2] == 0) {
								Move mv = new Move(i, j, r1, c2);
								moves.add(mv);
							}
							else if (state[r1][c2] == 1 || state[r1][c2] == 2) {
								int dr1 = r1 - 1, dc1 = c2 - 1;
								if (dr1 >= 0 && dc1 >= 0 && state[dr1][dc1] == 0) {
									Move mv = new Move(i, j, dr1, dc1);
									mv.kx = c2;
									mv.ky = r1;
									moves.add(mv);
								}
							}
						}
						if (state[i][j] == 4) {
							c1 = j + 1; c2 = j - 1; r1 = i + 1;
							if (r1 < 8 && c1 < 8) {
								if (state[r1][c1] == 0) {
									Move mv = new Move(i, j, r1, c1);
									moves.add(mv);
								}
								else if (state[r1][c1] == 1 || state[r1][c1] == 2) {
									int dr1 = r1 + 1, dc1 = c1 + 1;
									if (dr1 < 8 && dc1 < 8 && state[dr1][dc1] == 0) {
										Move mv = new Move(i, j, dr1, dc1);
										mv.kx = c1;
										mv.ky = r1;
										moves.add(mv);
									}
								}
							}
							if (r1 < 8 && c2 >= 0) {
								if (state[r1][c2] == 0) {
									Move mv = new Move(i, j, r1, c2);
									moves.add(mv);
								}
								else if (state[r1][c2] == 1 || state[r1][c2] == 2) {
									int dr1 = r1 + 1, dc1 = c2 - 1;
									if (dr1 < 8 && dc1 >= 0 && state[dr1][dc1] == 0) {
										Move mv = new Move(i, j, dr1, dc1);
										mv.kx = c2;
										mv.ky = r1;
										moves.add(mv);
									}
								}
							}
						}
					}
				}
			}
		}
		return moves;
	}

	@Override
	void initialize(boolean fromFile) {
		st = 0;
		stuck = bck = false;
		board = new int[8][8];
		p1_pcs = p2_pcs = 12;
		for (int i = 0; i < 3; i++) {
			for (int j = i % 2; j < 8; j += 2) {
				board[i][j] = 1;
			}
		}
		for (int i = 5; i < 8; i++) {
			for (int j = i % 2; j < 8; j += 2) {
				board[i][j] = 3;
			}
		}
		init_();
	}

	@Override
	void showGameState() {
		if (bck) {
			return;
		}
		score_();
		drawBoard_();
	}

	@Override
	void updateMessage(String msg) {
		if (bck) {
			return;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
			Platform.runLater(() -> {
				Draughts.init_();
			});
		}
		tx = new Text(msg);
		tx.setId("fancytext3");
		tx.setX(220);
		tx.setY(300);
		tx.setWrappingWidth(610);
		Platform.runLater(() -> {
			Draughts.root.getChildren().add(tx);
		});
		int tm = 50;
		if (stuck || p1_pcs == 0 || p2_pcs == 0) {
			tm = 3000;
		}
		try {
			Thread.sleep(tm);
		} catch (InterruptedException ex) {
			Logger.getLogger(Player2.class.getName()).log(Level.SEVERE, null, ex);
			Platform.runLater(() -> {
				Draughts.init_();
			});
		}
		Platform.runLater(() -> {
			Draughts.root.getChildren().remove(tx);
		});
	}

	private void clear_() {
		Platform.runLater(() -> {
			grid.getChildren().clear();
			Draughts.root.getChildren().removeAll(tx, tx2, tx3, b, tf1, tf2, grid);
		});
	}

	private void score_() {
		Platform.runLater(() -> {
			Draughts.root.getChildren().removeAll(tf1, tf2);

			tf1 = new Text("" + p1_pcs);
			tf1.setId("fancytext4");
			tf1.setLayoutX(90);
			tf1.setLayoutY(250);

			tf2 = new Text("" + p2_pcs);
			tf2.setId("fancytext4");
			tf2.setLayoutX(90);
			tf2.setLayoutY(450);

			Draughts.root.getChildren().add(tf1);
			Draughts.root.getChildren().add(tf2);
		});
	}

	private void init_() {
		b = new Button("<<");
		b.setId("fancybackbutton");
		b.setLayoutX(50);
		b.setLayoutY(50);
		b.setOnAction((ActionEvent e) -> {
			bck = true;
			clear_();
			Draughts.ui.interrupt();
		});

		tx2 = new Text(super.agent[0].name);
		tx2.setId("fancytext3");
		tx2.setLayoutX(50);
		tx2.setLayoutY(200);
		tx2.setWrappingWidth(180);

		tx3 = new Text(super.agent[1].name);
		tx3.setId("fancytext3");
		tx3.setLayoutX(50);
		tx3.setLayoutY(400);
		tx3.setWrappingWidth(180);

		Platform.runLater(() -> {
			Draughts.root.getChildren().add(b);
			Draughts.root.getChildren().add(tx2);
			Draughts.root.getChildren().add(tx3);
		});

		showGameState();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Player2.class.getName()).log(Level.SEVERE, null, ex);
			Platform.runLater(() -> {
				Draughts.init_();
			});
		}
	}

	void drawBoard_() {
		Platform.runLater(() -> {
			Draughts.root.getChildren().remove(grid);
		});
		grid = new Group();
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				int pc_clr = 0;
				try {
					Box B = new Box((x + y) % 2 == 0, x, y, board[y][x], pc_clr);
					Platform.runLater(() -> {
						grid.getChildren().add(B);
						if (B.C != null) {
							grid.getChildren().add(B.C);
						}
						if (B.P != null) {
							grid.getChildren().add(B.P);
						}
					});
				} catch (Exception e) {
					System.out.println(""+x+" "+y);
				}
			}
		}
		Platform.runLater(() -> {
			Draughts.root.getChildren().add(grid);
		});
	}

}
