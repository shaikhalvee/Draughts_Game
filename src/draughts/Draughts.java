/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draughts;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author SHAIKHALVEE
 */
public class Draughts extends Application {
	
	static Pane root;
	static Stage stage;
	static Scene scene;
	static final String path="/draughts/Style.css";
	static String pl;
	static Text t1,t2;
	static Button but1;
	static TextField tf;
	static GUI ui;
	static ImageView back;
	
	@Override
	public void start(Stage primaryStage) {
		root = new Pane();
		
		init_();
		scene = new Scene(root, 840, 600);
		scene.getStylesheets().add(path);
		
		stage=primaryStage;
		primaryStage.setTitle("Draughts");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	static void init_() {
		back = new ImageView(new Image("backgrnd.jpg"));
		back.setFitHeight(600);
		back.setFitWidth(840);
		back.setCache(true);
		root.getChildren().add(back);
		
		t1 = new Text("Draughts");
		t1.setX(320);
		t1.setY(100);
		t1.setId("fancytext");
		root.getChildren().add(t1);
		
		t2 = new Text("You are ...");
		t2.setX(370);
		t2.setY(220);
		t2.setId("fancytext2");
		root.getChildren().add(t2); 
		
		but1 = new Button("SASUKE");
		but1.getStylesheets().add(path);
		but1.setId("fancybutton");
		but1.setLayoutX(370);
		but1.setLayoutY(280);
		root.getChildren().add(but1);
		
		tf = new TextField();
		tf.setPromptText("Your name");
		tf.setFont(Font.font("Forte", 28));
		tf.setLayoutX(290);
		tf.setLayoutY(400);
		root.getChildren().add(tf);
		
		but1.setOnAction((ActionEvent event) -> {
			pl = "SASUKE";
			clear_();
			root.getChildren().add(back);
			ui = new GUI();
			ui.name = pl;
			ui.start();
		});
		
		tf.setOnKeyPressed((KeyEvent ke) -> {
			if (ke.getCode().equals(KeyCode.ENTER)) {
				pl = tf.getText();
				clear_();
				root.getChildren().add(back);
				ui = new GUI();
				ui.name = pl;
				ui.start();
			}
		});
	}
	
	static void clear_() {
		root.getChildren().clear();
	}
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
