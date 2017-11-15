/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draughts;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author SHAIKHALVEE
 */
public class Box extends Rectangle{
	Circle C;
	Polygon P;
	int X1, Y1;
	double X, Y;

	public Box(boolean bc, int x, int y, int p, int pc) {
		setWidth(Play.W);
		setHeight(Play.H);
		X = X1 = 200 + x * Play.W;
		Y = Y1 = y * Play.H;
		relocate(X1, Y1);
		setFill(bc ? Color.SADDLEBROWN : Color.KHAKI);
		
		if (p != 0) {
			C = new Circle((Play.H - 5) / 2.0);
			C.setCenterX(X1 + Play.W / 2.0);
			C.setCenterY(Y1 + Play.H / 2.0);
			C.setFill(p < 3 ? Color.RED : Color.WHITE);
			if (p == 2 || p == 4) {
				P = new Polygon();
				P.getPoints().addAll(new Double[]{X + 25, Y + 15, X + 16, Y + 60, X + 28, Y + 40, X + 40, Y + 60, X + 52, Y + 40,
				X + 64, Y + 60, X + 55, Y + 15});
				P.setFill(Color.GOLD);
				P.setRotate(180);
			}
		}
	}
	
}
