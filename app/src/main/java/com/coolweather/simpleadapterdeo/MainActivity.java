package com.coolweather.simpleadapterdeo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String ANIME_NAME = "anime_name";
    private static final String ANIME_AUTHOR = "anime_author";
    private static final String ANIME_COVERIMG = "anime_coverimg";
    private static final String ANIME_LIKECOLOR = "anime_likecolor";
    private static final String ANIME_CHECKBOX = "anime_checkbox";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.simple_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Log.d( TAG, "onItemClick: position="+ position + ";text=" + parent.getItemAtPosition(position).toString()
                        +";CheckBox changed="+((CheckBox)view.findViewById(R.id.anime_checkbox)).isChecked());
            }
        });

        List<Map<String,Object>> datas=new ArrayList<>();
        Resources resources = this.getResources();
        String[] anime_names = resources.getStringArray(R.array.anime_name);
        String[] anime_authors = resources.getStringArray(R.array.anime_author);
        int[] coverImgs = {R.drawable.hzw, R.drawable.jjdjr, R.drawable.hyrz,
                R.drawable.zchzt, R.drawable.qsmy, R.drawable.xyj, R.drawable.hlw};
        int[] senvenColors = {R.color.love_red, R.color.love_orange, R.color.love_yellow,R.color.love_green,
                R.color.love_blue_green, R.color.love_blue,R.color.love_purple};
        boolean[] anime_checkbox = {true, true, false, false, true, false, true};
        for (int i=0;i<anime_names.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put(ANIME_NAME,anime_names[i]);
            map.put(ANIME_COVERIMG,coverImgs[i]);
            map.put(ANIME_AUTHOR,anime_authors[i]);
            map.put(ANIME_LIKECOLOR,senvenColors[i]);
            map.put(ANIME_CHECKBOX,anime_checkbox[i]);
            datas.add(map);
        }
        String[] from ={ANIME_COVERIMG,ANIME_NAME,ANIME_AUTHOR,ANIME_LIKECOLOR,ANIME_CHECKBOX};
        int[] to = {R.id.anime_cover_img,R.id.anime_name_txt,R.id.anime_author_txt,R.id.anime_likecolor_img,R.id.anime_checkbox};
        SimpleAdapter simpleAdapter =new SimpleAdapter(this,datas,R.layout.simple_adapter_item,from,to);
        listView.setAdapter(simpleAdapter);
    }
}