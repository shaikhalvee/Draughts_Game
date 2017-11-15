/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draughts;

/**
 *
 * @author SHAIKHALVEE
 */
public class Piece {
	String title;
	int x, y, role;

	public Piece(String tit, int c, int r, int rol) {
		title = tit;
		x = c;
		y = r;
		role = rol;
	}
	
}
