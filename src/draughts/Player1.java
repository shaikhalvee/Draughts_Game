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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author SHAIKHALVEE
 */
public class Player1 extends Agent{
	List<Piece> pcs;
	boolean clicked;
	int fx, fy, mx, my, tx, ty;
	
	public Player1(String name) {
		super(name);
		pcs = new ArrayList<>();
		String tit = "Man";
		for (int i = 0, j; i < 3; i++) {
			for (j = i % 2; j < 8; j += 2) {
				Piece pc = new Piece(tit, j, i, 0);
				pcs.add(pc);
			}
		}
	}

	@Override
	public void makeMove(Game game) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Player1.class.getName()).log(Level.SEVERE, null, ex);
			Draughts.init_();
		}
		Play drghts = (Play)game;
		if (drghts.bck) {
			return;
		}
		Platform.runLater(() -> {
			drghts.grid.getChildren().clear();
			Draughts.root.getChildren().remove(drghts.grid);
		});
		
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if (drghts.board[y][x] == 1 || drghts.board[y][x] == 2)
					continue;
				Box B = new Box((x + y) % 2 == 0, x, y, drghts.board[y][x], 1);
				Platform.runLater(() -> {
					drghts.grid.getChildren().add(B);
					if (B.C != null) {
						drghts.grid.getChildren().add(B.C);
					}
					if (B.P != null) {
						drghts.grid.getChildren().add(B.P);
					}
				});
			}
		}
		
		for (Piece pc : pcs) {
			int y = pc.y, x = pc.x;
			Box B = new Box((x + y) % 2 == 0, x, y, drghts.board[y][x], 0);
			if (B.C != null && B.P == null) {
				B.C.setOnMouseClicked((MouseEvent me) -> {
					//if (pc.title.equals("Man")) {
						int r1 = y + 1, c1 = x - 1, c2 = x + 1;
						if (r1 < 8 && c1 >= 0) {
							if (drghts.board[r1][c1] == 0) {
								Circle C1 = new Circle(200 + c1 * Play.W + Play.W / 2.0, r1 * Play.H + Play.H / 2.0, 
									(Play.H - 5) / 2.0);
								C1.setStroke(Color.YELLOW);
								C1.setStrokeWidth(5);
								Platform.runLater(() -> {
									C1.setFill(Color.SADDLEBROWN);
									drghts.grid.getChildren().add(C1);
								});
								C1.setOnMouseClicked((MouseEvent me1) -> {
									fx = y;
									fy = x;
									pc.y = ty = r1;
									pc.x = tx = c1;
									drghts.board[fx][fy] = 0;
									drghts.board[ty][tx] = 1 + (ty == 7 ? 1 : 0);
									if (ty == 7) { 
										pc.title = "King";
									}
									Platform.runLater(() -> {
										drghts.grid.getChildren().remove(C1);
									});
									clicked = true;
								});
							} 
							else if (drghts.board[r1][c1] == 3 || drghts.board[r1][c1] == 4) {
								int dr1 = r1 + 1, dc1 = c1 - 1;
								if (dr1 < 8 && dc1 >= 0 && drghts.board[dr1][dc1] == 0) {
									Circle C2 = new Circle(200 + dc1 * Play.W + Play.W / 2.0, dr1 * Play.H + Play.H / 2.0, 
											(Play.H - 5) / 2.0);
									C2.setStroke(Color.YELLOW);
									C2.setStrokeWidth(5);
									Platform.runLater(() -> {
										C2.setFill(Color.SADDLEBROWN);
										drghts.grid.getChildren().add(C2);
									});
									C2.setOnMouseClicked((MouseEvent me2) -> {
										fx = y;
										fy = x;
										mx = r1;
										my = c1;
										pc.y = ty = dr1;
										pc.x = tx = dc1;
										drghts.board[fx][fy] = 0;
										drghts.board[mx][my] = 0;
										drghts.board[ty][tx] = 1 + (ty == 7 ? 1 : 0);
										if (ty == 7) {
											pc.title = "King";
										}
										drghts.p2_pcs -= 1;
										Platform.runLater(() -> {
											drghts.grid.getChildren().remove(C2);
										});
										clicked = true;
									});
								}
							}
						}
						if (r1 < 8 && c2 <8) {
							if (drghts.board[r1][c2] == 0) {
								Circle C3 = new Circle(200 + c2 * Play.W + Play.W / 2.0, r1 * Play.H + Play.H / 2.0, 
									(Play.H - 5) / 2.0);
								C3.setStroke(Color.YELLOW);
								C3.setStrokeWidth(5);
								Platform.runLater(() -> {
									C3.setFill(Color.SADDLEBROWN);
									drghts.grid.getChildren().add(C3);
								});
								C3.setOnMouseClicked((MouseEvent me3) -> {
									fx = y;
									fy = x;
									pc.y = ty = r1;
									pc.x = tx = c2;
									drghts.board[fx][fy] = 0;
									drghts.board[ty][tx] = 1 + (ty == 7 ? 1 : 0);
									if (ty == 7) { 
										pc.title = "King";
									}
									Platform.runLater(() -> {
										drghts.grid.getChildren().remove(C3);
									});
									clicked = true;
								});
							} 
							else if (drghts.board[r1][c2] == 3 || drghts.board[r1][c2] == 4) {
								int dr1 = r1 + 1, dc1 = c2 + 1;
								if (dr1 < 8 && dc1 < 8 && drghts.board[dr1][dc1] == 0) {
									Circle C4 = new Circle(200 + dc1 * Play.W + Play.W / 2.0, dr1 * Play.H + Play.H / 2.0, 
											(Play.H - 5) / 2.0);
									C4.setStroke(Color.YELLOW);
									C4.setStrokeWidth(5);
									Platform.runLater(() -> {
										C4.setFill(Color.SADDLEBROWN);
										drghts.grid.getChildren().add(C4);
									});
									C4.setOnMouseClicked((MouseEvent me4) -> {
										fx = y;
										fy = x;
										mx = r1;
										my = c2;
										pc.y = ty = dr1;
										pc.x = tx = dc1;
										drghts.board[fx][fy] = 0;
										drghts.board[mx][my] = 0;
										drghts.board[ty][tx] = 1 + (ty == 7 ? 1 : 0);
										if (ty == 7) {
											pc.title = "King";
										}
										drghts.p2_pcs -= 1;
										Platform.runLater(() -> {
											drghts.grid.getChildren().remove(C4);
										});
										clicked = true;
									});
								}
							}
						}
					//}
				});
			}
			else if (B.P != null) {
				B.P.setOnMouseClicked((MouseEvent men) -> {
					//if (pc.title.equals("King")) {
						int r1 = y + 1, r2 = y - 1, c1 = x - 1, c2 = x + 1;
						if (r1 < 8 && c1 >= 0) {
							if (drghts.board[r1][c1] == 0) {
								Circle C1 = new Circle(200 + c1 * Play.W + Play.W / 2.0, r1 * Play.H + Play.H / 2.0, 
									(Play.H - 5) / 2.0);
								C1.setStroke(Color.YELLOW);
								C1.setStrokeWidth(5);
								Platform.runLater(() -> {
									C1.setFill(Color.SADDLEBROWN);
									drghts.grid.getChildren().add(C1);
								});
								C1.setOnMouseClicked((MouseEvent me1) -> {
									fx = y;
									fy = x;
									pc.y = ty = r1;
									pc.x = tx = c1;
									drghts.board[fx][fy] = 0;
									drghts.board[ty][tx] = 2;
									Platform.runLater(() -> {
										drghts.grid.getChildren().remove(C1);
									});
									clicked = true;
								});
							} 
							else if (drghts.board[r1][c1] == 3 || drghts.board[r1][c1] == 4) {
								int dr1 = r1 + 1, dc1 = c1 - 1;
								if (dr1 < 8 && dc1 >= 0 && drghts.board[dr1][dc1] == 0) {
									Circle C2 = new Circle(200 + dc1 * Play.W + Play.W / 2.0, dr1 * Play.H + Play.H / 2.0, 
											(Play.H - 5) / 2.0);
									C2.setStroke(Color.YELLOW);
									C2.setStrokeWidth(5);
									Platform.runLater(() -> {
										C2.setFill(Color.SADDLEBROWN);
										drghts.grid.getChildren().add(C2);
									});
									C2.setOnMouseClicked((MouseEvent me2) -> {
										fx = y;
										fy = x;
										mx = r1;
										my = c1;
										pc.y = ty = dr1;
										pc.x = tx = dc1;
										drghts.board[fx][fy] = 0;
										drghts.board[mx][my] = 0;
										drghts.board[ty][tx] = 2;
										drghts.p2_pcs -= 1;
										Platform.runLater(() -> {
											drghts.grid.getChildren().remove(C2);
										});
										clicked = true;
									});
								}
							}
						}
						if (r1 < 8 && c2 <8) {
							if (drghts.board[r1][c2] == 0) {
								Circle C3 = new Circle(200 + c2 * Play.W + Play.W / 2.0, r1 * Play.H + Play.H / 2.0, 
									(Play.H - 5) / 2.0);
								C3.setStroke(Color.YELLOW);
								C3.setStrokeWidth(5);
								Platform.runLater(() -> {
									C3.setFill(Color.SADDLEBROWN);
									drghts.grid.getChildren().add(C3);
								});
								C3.setOnMouseClicked((MouseEvent me3) -> {
									fx = y;
									fy = x;
									pc.y = ty = r1;
									pc.x = tx = c2;
									drghts.board[fx][fy] = 0;
									drghts.board[ty][tx] = 2;
									Platform.runLater(() -> {
										drghts.grid.getChildren().remove(C3);
									});
									clicked = true;
								});
							} 
							else if (drghts.board[r1][c2] == 3 || drghts.board[r1][c2] == 4) {
								int dr1 = r1 + 1, dc1 = c2 + 1;
								if (dr1 < 8 && dc1 < 8 && drghts.board[dr1][dc1] == 0) {
									Circle C4 = new Circle(200 + dc1 * Play.W + Play.W / 2.0, dr1 * Play.H + Play.H / 2.0, 
											(Play.H - 5) / 2.0);
									C4.setStroke(Color.YELLOW);
									C4.setStrokeWidth(5);
									Platform.runLater(() -> {
										C4.setFill(Color.SADDLEBROWN);
										drghts.grid.getChildren().add(C4);
									});
									C4.setOnMouseClicked((MouseEvent me4) -> {
										fx = y;
										fy = x;
										mx = r1;
										my = c2;
										pc.y = ty = dr1;
										pc.x = tx = dc1;
										drghts.board[fx][fy] = 0;
										drghts.board[mx][my] = 0;
										drghts.board[ty][tx] = 2;
										drghts.p2_pcs -= 1;
										Platform.runLater(() -> {
											drghts.grid.getChildren().remove(C4);
										});
										clicked = true;
									});
								}
							}
						}
						if (r2 >= 0 && c1 >= 0) {
							if (drghts.board[r2][c1] == 0) {
								Circle C1 = new Circle(200 + c1 * Play.W + Play.W / 2.0, r2 * Play.H + Play.H / 2.0, 
									(Play.H - 5) / 2.0);
								C1.setStroke(Color.YELLOW);
								C1.setStrokeWidth(5);
								Platform.runLater(() -> {
									C1.setFill(Color.SADDLEBROWN);
									drghts.grid.getChildren().add(C1);
								});
								C1.setOnMouseClicked((MouseEvent me1) -> {
									fx = y;
									fy = x;
									pc.y = ty = r2;
									pc.x = tx = c1;
									drghts.board[fx][fy] = 0;
									drghts.board[ty][tx] = 2;
									Platform.runLater(() -> {
										drghts.grid.getChildren().remove(C1);
									});
									clicked = true;
								});
							} 
							else if (drghts.board[r2][c1] == 3 || drghts.board[r2][c1] == 4) {
								int dr1 = r2 - 1, dc1 = c1 - 1;
								if (dr1 >= 0 && dc1 >= 0 && drghts.board[dr1][dc1] == 0) {
									Circle C2 = new Circle(200 + dc1 * Play.W + Play.W / 2.0, dr1 * Play.H + Play.H / 2.0, 
											(Play.H - 5) / 2.0);
									C2.setStroke(Color.YELLOW);
									C2.setStrokeWidth(5);
									Platform.runLater(() -> {
										C2.setFill(Color.SADDLEBROWN);
										drghts.grid.getChildren().add(C2);
									});
									C2.setOnMouseClicked((MouseEvent me2) -> {
										fx = y;
										fy = x;
										mx = r2;
										my = c1;
										pc.y = ty = dr1;
										pc.x = tx = dc1;
										drghts.board[fx][fy] = 0;
										drghts.board[mx][my] = 0;
										drghts.board[ty][tx] = 2;
										drghts.p2_pcs -= 1;
										Platform.runLater(() -> {
											drghts.grid.getChildren().remove(C2);
										});
										clicked = true;
									});
								}
							}
						}
						if (r2 >= 0 && c2 <8) {
							if (drghts.board[r2][c2] == 0) {
								Circle C3 = new Circle(200 + c2 * Play.W + Play.W / 2.0, r2 * Play.H + Play.H / 2.0, 
									(Play.H - 5) / 2.0);
								C3.setStroke(Color.YELLOW);
								C3.setStrokeWidth(5);
								Platform.runLater(() -> {
									C3.setFill(Color.SADDLEBROWN);
									drghts.grid.getChildren().add(C3);
								});
								C3.setOnMouseClicked((MouseEvent me3) -> {
									fx = y;
									fy = x;
									pc.y = ty = r2;
									pc.x = tx = c2;
									drghts.board[fx][fy] = 0;
									drghts.board[ty][tx] = 2;
									Platform.runLater(() -> {
										drghts.grid.getChildren().remove(C3);
									});
									clicked = true;
								});
							} 
							else if (drghts.board[r2][c2] == 3 || drghts.board[r2][c2] == 4) {
								int dr1 = r2 - 1, dc1 = c2 + 1;
								if (dr1 >= 0 && dc1 < 8 && drghts.board[dr1][dc1] == 0) {
									Circle C4 = new Circle(200 + dc1 * Play.W + Play.W / 2.0, dr1 * Play.H + Play.H / 2.0, 
											(Play.H - 5) / 2.0);
									C4.setStroke(Color.YELLOW);
									C4.setStrokeWidth(5);
									Platform.runLater(() -> {
										C4.setFill(Color.SADDLEBROWN);
										drghts.grid.getChildren().add(C4);
									});
									C4.setOnMouseClicked((MouseEvent me4) -> {
										fx = y;
										fy = x;
										mx = r2;
										my = c2;
										pc.y = ty = dr1;
										pc.x = tx = dc1;
										drghts.board[fx][fy] = 0;
										drghts.board[mx][my] = 0;
										drghts.board[ty][tx] = 2;
										drghts.p2_pcs -= 1;
										Platform.runLater(() -> {
											drghts.grid.getChildren().remove(C4);
										});
										clicked = true;
									});
								}
							}
						}
					//}
				});
			}
			Platform.runLater(() -> {
				drghts.grid.getChildren().add(B);
				if (B.C != null) {
					drghts.grid.getChildren().add(B.C);
				}
				if (B.P != null) {
					drghts.grid.getChildren().add(B.P);
				}
			});
		}
		Platform.runLater(() -> {
			Draughts.root.getChildren().add(drghts.grid);
		});
		while (!clicked) {            
			try {
				Thread.sleep(5);
			} catch (InterruptedException ex) {
				Logger.getLogger(Player1.class.getName()).log(Level.SEVERE, null, ex);
				Platform.runLater(() ->{
					Draughts.init_();
				});
			}
		}
		clicked = false;
	}

}
