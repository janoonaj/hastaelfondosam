package com.testapps.testLibGDX;


import com.badlogic.gdx.Gdx;

import java.awt.Point;
import java.util.HashMap;

public class PositionsOnScreen {

    private static HashMap<Integer, Point> positions;

    public static void writeBoardCoordenades() {
        positions = new HashMap<Integer, Point>();
        createCoordenades();
    }

    public static Point getScreenPos(Integer pos){
        return positions.get(pos);
    }

    private static void createCoordenades() {
        int height20Percent = Gdx.graphics.getHeight() / 5;
        int width25Percent = Gdx.graphics.getWidth() / 4;

        positions.put(1, new Point(width25Percent * 2,Gdx.graphics.getHeight()  - height20Percent));
        positions.put(2, new Point(width25Percent * 3,Gdx.graphics.getHeight() - height20Percent*2));
        positions.put(3, new Point(width25Percent * 3,Gdx.graphics.getHeight() - height20Percent*3));
        positions.put(4, new Point(width25Percent * 2,height20Percent));
        positions.put(5, new Point(width25Percent, Gdx.graphics.getHeight() - height20Percent*3));
        positions.put(6, new Point(width25Percent, Gdx.graphics.getHeight() - height20Percent*2));
    }
}
