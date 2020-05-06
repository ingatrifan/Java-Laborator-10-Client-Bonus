package sample;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    List<Boolean> tiles = new ArrayList<>();
    GameClient gc;
    public int getPlayersMove(List<Gomoku.Tile> tilesList){
        for (int i=0;i<225;i++){
            if (tiles.get(i)!=tilesList.get(i).selected)return i;
        }
        return -1;
    }
    public void setTable( List<Gomoku.Tile> tiles){
        for (Gomoku.Tile tile : tiles)
            this.tiles.add(tile.selected);
    }
    public void startGame(){
        gc = new GameClient();
    }
    public void makeMove(int pos){
        gc.sendMakeMove(pos);
    }
    public int receiveMove(){
        return gc.receiveMove();
    }
    public int getColor(){
        return gc.getColor();
    }
}
