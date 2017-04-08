package com.example.administrator.myapplication;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    Activity activity=this;
    List<Person> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv= (ListView) findViewById(R.id.lv);
        for(int i=0;i<10;i++){
            Person p=new Person();
            p.setSelect(-1);
            list.add(p);
        }
        lv.setAdapter(new MyAdapter());
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Title title;
            if(convertView==null){
                title=new Title();
                convertView = LayoutInflater.from(activity).inflate(R.layout.listview_item, null);
                title.tv= (TextView) convertView.findViewById(R.id.tv);
                title.rg= (RadioGroup) convertView.findViewById(R.id.rg);
                convertView.setTag(title);
            }else {
                title=(Title)convertView.getTag();
            }
            title.rg.setOnCheckedChangeListener(null);

            final Person p=list.get(position);
            Log.i("sunyh", position + "person" + p.getSelect());
            switch (p.getSelect()){
                case -1:title.rg.clearCheck();break;
                case 0:title.rg.check(R.id.a);break;
                case 1:title.rg.check(R.id.b);break;
                case 2:title.rg.check(R.id.c);break;
                case 3:title.rg.check(R.id.d);break;

            }
            title.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Log.i("sunyh", position+"check"+checkedId);
                    switch (checkedId){
                        case R.id.a:
                            p.setSelect(0);
                            break;

                        case R.id.b:
                            p.setSelect(1);
                            break;
                        case R.id.c:
                            p.setSelect(2);
                            break;
                        case R.id.d:
                            p.setSelect(3);
                            break;
                    }
                }
            });
            return convertView;
        }
        class Title{
            TextView tv;
            RadioGroup rg;
        }
    }

}
