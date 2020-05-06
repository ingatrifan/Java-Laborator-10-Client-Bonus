package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class Gomoku extends Application {
    private static final int NUM_OF_CELLS = 225;
    private static final int NUM_PER_ROW = 15;
    private Boolean isPlayersOrder;
    Color playersColor;
    Color otherPlayer;
    Controller controller;
    List<Tile> tiles = new ArrayList<>();

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(600, 600);

        for (int i = 0; i < NUM_OF_CELLS; i++) {
            tiles.add(new Tile());
        }

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(30 * (i % NUM_PER_ROW));
            tile.setTranslateY(30 * (i / NUM_PER_ROW));
            tile.setColor(playersColor);
            root.getChildren().add(tile);
        }
        controller.setTable(tiles);
        return root;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        controller.startGame();
        System.out.println("Started the game");
        if (controller.getColor()==1){
            isPlayersOrder = true;
            playersColor = Color.RED;
            otherPlayer = Color.BLACK;
        } else {
            isPlayersOrder = false;
            playersColor = Color.BLACK;
            otherPlayer = Color.RED;
        }
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
//        while (true){
//            if (isPlayersOrder == false){
//                int pos=controller.receiveMove();
//                tiles.get(pos).setColor(otherPlayer);
//                tiles.get(pos).selected = true;
//                isPlayersOrder = true;
//            }
//        }

    }
    public static void main(String[] args) {
        launch(args);
    }
    public class Tile extends StackPane {
        boolean selected;
        private Color color;
        private Rectangle border;
        public Tile() {
            border = new Rectangle(30, 30);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(border);
            selected = false;
            setOnMouseClicked(this::handleMouseClick);
        }

        public void handleMouseClick(MouseEvent event) {
            if (!selected && isPlayersOrder){
                this.border.setFill(color);
                int pos = controller.getPlayersMove(tiles);
                controller.makeMove(pos);
                isPlayersOrder = false;
                controller.setTable(tiles);
                int newPos=controller.receiveMove();
                tiles.get(newPos).setColor(otherPlayer);
                tiles.get(newPos).selected = true;
                isPlayersOrder = true;
            }
        }
        public void fillTileColor(Color color){
            this.border.setFill(color);
        }
        public void setColor(Color color) {
            this.color = color;
        }

    }

}
