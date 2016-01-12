package com.example.gura.step08gameview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lee on 2015-10-31.
 */
public class DragonView extends View{
    //필요한 맴버필드 정의하기
    Context context;
    int viewWidth, viewHeight; //View 의 폭과 높이
    Bitmap backImg; //배경이미지
    int back1Y, back2Y; //배경이미지의 y 좌표
    Bitmap[] ships=new Bitmap[2]; //드레곤 이미지 2개를 저장할 배열
    int shipIndex; //드레곤 이미지 인덱스
    int shipX, shipY; //드레곤의 좌표
    int shipW, shipH; //드레곤의 폭과 높이
    int count;//카운트를 셀 변수
    int lastX; //이전의 x 좌표

    List<Missile> missList=new ArrayList<Missile>(); //미사일 객체를 담을 배열
    Bitmap missImg; //미사일 이미지
    int missW, missH; //미사일의 폭과 높이
    int missSpeed; //미사일의 속도

    //적기 이미지를 담을 배열 객체 생성하기
    Bitmap[] enemyImages=new Bitmap[2];
    //적기 객체를 담을 가변 배열 객체 생성하기
    List<Enemy> enemyList=new ArrayList<Enemy>();
    //적기 5개의 x 좌표를 저장할 배열
    int[] enemyX=new int[5];
    //적기의 크기 관련값
    int enemyW, enemyH, enemyHalfW, enemyHalfH;
    //적기의 속도
    int enemySpeed;
    //효과음을 재생하기 위한 SoundManager 객체
    MyUtil.SoundManager soundManager;

    //랜덤한 수를 발생시키기 위한 객체
    Random ran=new Random();

    //현재 게임진행중 여부
    boolean isGamming=false;

    public DragonView(Context context) {
        super(context);
    }

    public DragonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //초기화 메소드
    public void init(){
        //배경이미지 로딩하기
        backImg= BitmapFactory.decodeResource(getResources(), R.drawable.backbg);
        //배경이미지를 View 의 크기에 맞게 스케일링한다.
        backImg=Bitmap.createScaledBitmap(backImg, viewWidth, viewHeight, false);
        //배경이미지 좌표 초기화
        back1Y=0;
        back2Y=-viewHeight;

        //드레곤의 초기 위치 지정
        shipX=viewWidth/2;
        shipY=viewHeight-viewHeight/7;
        //드레곤의 폭과 높이 지정
        shipW=viewWidth/5;
        shipH=viewWidth/5;

        //드레곤 이미지 읽어들이기
        Bitmap ship1=BitmapFactory.decodeResource(getResources(), R.drawable.unit1);
        Bitmap ship2=BitmapFactory.decodeResource(getResources(), R.drawable.unit2);
        //원하는 크기로 스케일링하기
        ship1=Bitmap.createScaledBitmap(ship1, shipW, shipH, false);
        ship2=Bitmap.createScaledBitmap(ship2, shipW, shipH, false);
        //Bitmap 배열에 저장하기
        ships[0]=ship1;
        ships[1]=ship2;

        //미사일의 크기 정하기
        missW=shipW/2;
        missH=shipW/2;

        //미사일의 속도
        missSpeed=viewHeight/100;
        //미사일 이미지 읽어들이고 크기 스케일링하기
        missImg=BitmapFactory.decodeResource(getResources(), R.drawable.unsuk);
        missImg=Bitmap.createScaledBitmap(missImg, missW, missH, false);

        //화면의 폭을 5등분한 크기를 적기의 폭으로 지정한다.
        enemyW=viewWidth/5;
        enemyH=enemyW;
        //반지름 계산
        enemyHalfW=enemyW/2;
        enemyHalfH=enemyH/2;
        //적기를 배치하기 위한 x 좌표 정하기
        for(int i=0; i<5; i++){
            int x=enemyHalfW+enemyW*i;
            //계산된 x 좌표를 배열에 저장한다.
            enemyX[i]=x;
        }
        //적기의 속도 부여
        enemySpeed=viewHeight/100;

        //적기 이미지 읽어들이기
        Bitmap yellowE=BitmapFactory.decodeResource(getResources(), R.drawable.juck1);
        Bitmap whiteE=BitmapFactory.decodeResource(getResources(), R.drawable.juck2);
        //크기 스케일링하기
        yellowE=Bitmap.createScaledBitmap(yellowE, enemyW, enemyH, false);
        whiteE=Bitmap.createScaledBitmap(whiteE, enemyW, enemyH, false);
        //Bitmap 배열 객체에 저장하기
        enemyImages[0]=whiteE;
        enemyImages[1]=yellowE;
        //SoundManager 객체의 참조값 얻어오기
        soundManager= MyUtil.SoundManager.getInstance();
    }

    //게임 시작하는 메소드
    public void startGame(){
        if(isGamming)return;//현재 게임 중일때는 무시
        handler.sendEmptyMessage(0);
        isGamming=true;
    }
    //게임 일시 정지하는 메소드
    public void pauseGame(){
        if(!isGamming)return;//현재 게임중이 아닐때는 무시
        handler.removeMessages(0);
        isGamming=false;
    }
    //맴버필드의 내용 초기화 하는 메소드
    public void resetGame(){
        isGamming=false;
        missList.clear();
        enemyList.clear();
        shipX=viewWidth/2;
        back1Y=0;
        back2Y=-viewHeight;
    }

    //View 의 크기를 감지하기 위한 메소드 오버라이드
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //전달되는 View 의 폭과 높이를 맴버필드에 저장한다.
        viewWidth=w;
        viewHeight=h;
        init();//초기화 메소드 호출
    }

    //View 화면 구성하는 메소드 오버라이드
    @Override
    protected void onDraw(Canvas canvas) {
        //배경이미지 그리기
        canvas.drawBitmap(backImg, 0, back1Y, null);
        canvas.drawBitmap(backImg, 0, back2Y, null);
        //반복문 돌면서 미사일 그리기
        for(Missile tmp: missList){
            canvas.drawBitmap(missImg, tmp.x-missW/2, tmp.y-missH/2, null);
        }
        //반복문 돌면서 적기 그리기
        for(Enemy tmp: enemyList){
            canvas.drawBitmap(enemyImages[tmp.imgIndex],
                    tmp.x-enemyHalfW,
                    tmp.y-enemyHalfH,
                    null);
        }

        //드레곤의 이미지 그리기
        canvas.drawBitmap(ships[shipIndex], shipX-shipW/2, shipY-shipH/2, null);

        backScroll();
        shipAnimation();
        makeMissile();
        moveMissile();
        checkMissile();
        makeEnemy();
        moveEnemy();
        checkEnemy();
        checkMissEnemyCollusion();
        checkDragon();
        count++;
    }//onDraw()
    //드레곤과 적기의 충돌검사
    public void checkDragon(){
        for(Enemy tmp:enemyList){
            boolean isDieDragon=shipX > tmp.x-enemyHalfW &&
                    shipX < tmp.x+enemyHalfW &&
                    shipY > tmp.y-enemyHalfH &&
                    shipY < tmp.y+enemyHalfH;
            if(isDieDragon){
                //효과음 재생
                soundManager.play(Constants.DIE_SOUND);
                //핸들러 중지
                handler.removeMessages(0);
                //게임 리셋
                resetGame();
            }
        }
    }

    //미사일과 적기의 충돌검사
    public void checkMissEnemyCollusion(){
        for(int i=0; i<missList.size(); i++){
            Missile m=missList.get(i);//i번째 미사일 객체를 불러온다
            for(int j=0; j<enemyList.size(); j++){
                Enemy e=enemyList.get(j);//j번째 적기 객체를 불러온다.
                boolean isShooted= m.x > e.x-enemyHalfW &&
                        m.x < e.x+enemyHalfW &&
                        m.y > e.y-enemyHalfH &&
                        m.y < e.y+enemyHalfH;
                if(isShooted){
                    //효과음 재생
                    soundManager.play(Constants.BOOM_SOUND);
                    //여기가 수행된다면 i 번째 미사일은 j번째 적기와 충돌한것이다.
                    e.energy -= 50;//에너지를 50 줄이고
                    if(e.energy <= 0){ //에너지가 0 이하가 되면
                        e.isDead=true;//제거될수 있도록 표시
                    }
                    m.isDead=true;//적기를 맞춘 미사일도 제거 될수 있도록
                }

            }
        }
    }

    public void makeEnemy(){
        //랜덤한 시점에 적기 5개가 만들어져서 배열에 저장되도록 한다.
        int ranNum=ran.nextInt(30);// 0~29 사이의 랜덤한 정수 발생
        if(ranNum != 10)return;
        for(int i=0; i<5; i++){
            //적기의 종류 랜덤하게 부여
            int imgIndex=ran.nextInt(2); // 0~1 사이의 랜덤한 정수
            int energy=0;
            if(imgIndex==0){//0 Type 적기 => 하얀색
                energy=50;
            }else if(imgIndex==1){//1 Type 적기 => 노란색
                energy=100;
            }
            //적기 객체를 생성해서
            Enemy e=new Enemy(imgIndex, enemyX[i], 0, energy);
            //배열에 저장한다.
            enemyList.add(e);
        }
    }
    public void moveEnemy(){
        for(Enemy tmp:enemyList){
            tmp.y = tmp.y + enemySpeed;
            if(tmp.y >= viewHeight + enemyHalfH){//아래쪽 화면을 벗어났다라면
                //배열에서 제거될수 있도록 표시한다.
                tmp.isDead=true;
            }
        }
    }
    public void checkEnemy() {
        for(int i=enemyList.size()-1; i>=0; i--){
            Enemy tmp=enemyList.get(i);
            if(tmp.isDead){//배열에서 제거 해야한다면
                enemyList.remove(i); // 제거한다.
            }
        }
    }
    //배열에서 제거할 미사일은 제거하는 메소드
    public void checkMissile(){
        for(int i=missList.size()-1; i>=0; i--){
            //i번째 미사일 객체를 불러온다.
            Missile tmp=missList.get(i);
            if(tmp.isDead){//배열에서 제거해야할 미사일 객체라면
                //배열에서 i 번째 미사일 객체를 제거한다.
                missList.remove(i);
            }
        }
    }

    //미사일 움직이는 메소드
    public void moveMissile(){
        for(Missile tmp:missList){
            //미사일의 속도 만큼 y 좌표를 감소 시킨다.
            tmp.y = tmp.y - missSpeed;
            if(tmp.y <= -missH/2){ //미사일이 위쪽으로 벗어났다라면
                //배열에서 제거 될수 있도록 표시한다.
                tmp.isDead=true;
            }
        }
    }

    //미사일 객체 만드는 메소드
    public void makeMissile(){
        if(count%10 != 0 ){
            return;
        }
        //미사일 객체를 생성해서 배열에 저장한다.
        Missile m=new Missile(shipX, shipY);
        missList.add(m);
    }
    public void shipAnimation(){
        if(count%20 != 0 ){
            return;
        }
        shipIndex++; //드레곤 이미지 인덱스를 증가 시킨다.
        if(shipIndex==2){//없는 이미지 인덱스라면
            shipIndex=0; //다시 처음으로 되돌린다.
        }
    }

    //배경이미지 스크롤 시키는 메소드
    public void backScroll(){
        back1Y += 5;
        back2Y += 5;
        if(back1Y >= viewHeight){ //배경1이 한계점에 다다랐을때
            back1Y = -viewHeight;
            back2Y = 0;
        }
        if(back2Y >= viewHeight){ //배경2가 한계점에 다다랐을때
            back2Y = -viewHeight;
            back1Y = 0;
        }
    }

    //터치 입력을 받기 위한 메소드
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //이벤트가 일어난곳의 x 좌표를 얻어온다.
        int eventX=(int)event.getX();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX=eventX;
                break;
            case MotionEvent.ACTION_MOVE:
                //현재 x 좌표와 저장된 x 좌표의 차이를 구한다.
                int deltaX=lastX-eventX;
                //드레곤의 x 좌표에 반영한다.
                shipX = shipX - deltaX;
                ///현재 x 좌표는 다음번 액션 무브 될때 과거의 좌표가 된다.
                lastX = eventX;
                //드레곤이 집을 나가지 않도록 처리
                if(shipX<=0)shipX=0;
                if(shipX>=viewWidth)shipX=viewWidth;
                break;
        }
        return true;
    }
    //화면을 주기적으로 갱신 하기 위한 핸들러 객체의 참조값 얻어내기
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            invalidate();//화면 갱신하기
            handler.sendEmptyMessageDelayed(0, 10);
        }
    };
}









