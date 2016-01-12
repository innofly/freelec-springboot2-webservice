package com.example.gura.step08gameview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    DragonView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //화면이 계속 꺼지지 않도록 유지
        MyUtil.keepScreenOn(this);
        //SoundManager 관련 작업
        MyUtil.SoundManager soundManager=MyUtil.SoundManager.getInstance();
        //SoundManager 객체 초기화
        soundManager.init(this);
        //효과음 등록
        soundManager.addSound(Constants.DIE_SOUND, R.raw.birddie);
        soundManager.addSound(Constants.BOOM_SOUND, R.raw.coin01);
        //DragonView 의 참조값을 맴버필드에 저장한다.
        view=new DragonView(this);
        setContentView(view);
    }
    //옵션 메뉴 구성하는 메소드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //res/menu/main.xml 문서 전개해서 옵션 메뉴 구성하기
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }
    //옵션 아이템을 선택했을때 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.startMenu: //시작 메뉴를 선택했을때
                view.startGame();
                break;
            case R.id.pauseMenu: //일시 중지 메뉴를 선택했을때
                view.pauseGame();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        view.pauseGame();//게임 일시정지
        super.onPause();
    }
}









