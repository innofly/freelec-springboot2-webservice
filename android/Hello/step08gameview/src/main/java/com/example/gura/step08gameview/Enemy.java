package com.example.gura.step08gameview;

/**
 * Created by lee on 2015-10-31.
 */
public class Enemy {
    public int imgIndex;
    public int x, y;
    public int energy;
    public boolean isDead;

    public Enemy(int imgIndex, int x, int y, int energy) {
        this.imgIndex = imgIndex;
        this.x = x;
        this.y = y;
        this.energy = energy;
    }
}
