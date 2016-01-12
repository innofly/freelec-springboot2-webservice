package com.example.gura.step08gameview;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.HashMap;

/**
 * Created by lee on 2015-10-31.
 */
public class MyUtil {
    //효과음을 재생하기 위한 클래스
    public static class SoundManager {
        //필요한 필드 정의하기
        private static SoundManager sManager;
        private SoundPool soundPool;
        private HashMap<Integer,Integer> map;
        private Context context;
        //싱글톤 페턴
        private SoundManager(){}
        public static SoundManager getInstance(){
            if(sManager==null){
                sManager=new SoundManager();
            }
            return sManager;
        }
        //초기화 하기
        public  void init(Context context){
            this.context=context;
            //필요한 객체를 전달해서 SoundPool 객체를 생성한다.
            soundPool=new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
            //사운드 리소스 id 값을 저장할 HashMap 객체 생성하기
            map=new HashMap<Integer, Integer>();
        }
        //음원을 추가하는 메소드
        public void addSound(int index, int resId){
            //인자로 전달된 resId 값을 이용해서 사운드를 로딩 시키고 재생할 준비를 한다.
            int id=soundPool.load(context, resId, 1);
            //등록하고 리턴되는 아이디를 맵에 저장한다.
            map.put(index, id);
        }
        //음원을 재생하는 메소드
        public void play(int index){
            //인자로 전달된 인덱스 값을 이용해서 해당 음원을 재생하도록한다.
            soundPool.play(map.get(index), 1, 1, 1, 0, 1);
        }
        //음원 재생을 중지하는 메소드
        public void stopSound(int index){
            //인자로 전달된 인덱스 값을 이용해서 해당 음원을 정지 시킨다.
            soundPool.stop(map.get(index));
        }
    }

    //화면 꺼지지 않고 유지 되도록 하는 메소드
    public static void keepScreenOn(Activity activity){
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    //키보드 숨기는 메소드
    public static void hideKeyboard(Activity activity){

        InputMethodManager iManager=(InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(activity.getCurrentFocus()==null)return;
        iManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    //메소드의 인자로 전달되는 View 객체의 포커스 해제하는 메소드
    public static void releaseFocus(View view) {
        ViewParent parent = view.getParent();
        ViewGroup group = null;
        View child = null;
        while (parent != null) {
            if (parent instanceof ViewGroup) {
                group = (ViewGroup) parent;
                for (int i = 0; i < group.getChildCount(); i++) {
                    child = group.getChildAt(i);
                    if(child != view && child.isFocusable())
                        child.requestFocus();
                }
            }
            parent = parent.getParent();
        }
    }
}
