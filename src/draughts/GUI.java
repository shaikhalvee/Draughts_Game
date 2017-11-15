/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draughts;

import javafx.application.Platform;

/**
 *
 * @author SHAIKHALVEE
 */
public class GUI extends Thread {
	String name;

	public GUI() {
		setDaemon(true);
	}
	
	@Override
	public void run() {
		if (name.equals("SASUKE")) {
			Player2 p1 = new Player2(name);
			p1.setRole(0);
			Player2 p2 = new Player2("NARUTO");
			p2.setRole(1);
			Play game = new Play(p1, p2);
			game.play();
		}
		else {
			Player1 p1 = new Player1(name);
			p1.setRole(0);
			Player2 p2 = new Player2("NARUTO");
			p2.setRole(1);
			Play game = new Play(p1, p2);
			game.play();
		}
		Platform.runLater(() -> {
			Draughts.init_();
		});
	}
	
}
