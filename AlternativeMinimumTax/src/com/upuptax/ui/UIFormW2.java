package com.upuptax.ui;
import javafx.application.Application;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.text.TextBuilder;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UIFormW2 extends Application {
	public static void main(String[] args){
		launch(args);
	}


	public void start(Stage stage) throws Exception {
		stage.setTitle("W-2 Form");
		Group root = new Group();
        Scene scene = new Scene(root, 500, 250, Color.WHITE);
		SplitPane splitPane = new SplitPane();
		splitPane.prefWidthProperty().bind(scene.widthProperty());
		splitPane.prefHeightProperty().bind(scene.heightProperty());

		VBox leftArea = new VBox(10);

		
		leftArea.setAlignment(Pos.CENTER);

		// Upper and lower split pane
		HBox rightArea = new HBox();
		
		rightArea.setAlignment(Pos.CENTER);

		// add left area
		splitPane.getItems().add(leftArea);
		splitPane.getItems().add(rightArea);


		// evenly position divider
		ObservableList<SplitPane.Divider> dividers = splitPane.getDividers();
		for (int i = 0; i < dividers.size(); i++) {
		    dividers.get(i).setPosition((i + 1.0) / 3);
		}

		HBox hbox = new HBox();
		hbox.getChildren().add(splitPane);
		root.getChildren().add(hbox);
		stage.setScene(scene);
		stage.show();
		
	}

}
