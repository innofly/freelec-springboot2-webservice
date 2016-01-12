package com.example.gura.step08gameview;

/**
 * Created by lee on 2015-10-31.
 */
public class Missile {
    public int x, y; //미사일의 x 좌표 y 좌표
    public boolean isDead; //배열에서 제거 해야할지에 대한 여부
    //생성자에서 초기좌표를 받아서 맴버필드에 저장한다.
    public Missile(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
