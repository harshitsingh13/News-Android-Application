package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> list=new ArrayList<String>();
    private static final String TAG = "msg";
    String aurl="https://petwear.in/mc2022/news/news_1.json";
    String head="";
    public static String title="Title: ";
    public static String msg="Internet Connectivity: ";
    public static String body="Body: ";
    public static Bitmap image=null;
    TextView t1;
    //String msg="";
    JSONArray jsonObject;
    Button b1;
    Button b2;
    Button b3;
    //start_service f1=new start_service();
    stop_service f2=new stop_service();
    news_list nf=new news_list();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //t1.setText(msg);
        //fragmentTransaction.replace(R.id.frameLayout,f1);
        //fragmentTransaction.commit();

        b1=(Button) findViewById(R.id.fragment1btn);//defining button for 'start service' button functionality
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,nf);
                fragmentTransaction.commit();
                Intent intent=new Intent(MainActivity.this,Service_1.class);
                //intent.putStringArrayListExtra("newlist",list);
                startService(intent);
            }
        });

        /*b1=(Button) findViewById(R.id.fragment1btn);//defining button for 'start service' button functionality
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,f1);
                fragmentTransaction.commit();
                Intent intent=new Intent(MainActivity.this,Service_1.class);
                //intent.putStringArrayListExtra("newlist",list);
                startService(intent);
            }
        });*/

        b2=(Button) findViewById(R.id.fragment2btn);//defining button for 'stop service' button functionality
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,nf);
                fragmentTransaction.commit();
                Intent intent=new Intent(MainActivity.this,Service_1.class);
                //intent.putStringArrayListExtra("newlist",list);
                stopService(intent);
            }
        });


        /*b3=(Button) findViewById(R.id.fragment3btn);//defining button for 'top 5' button functionality
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,f2);
                fragmentTransaction.commit();
                Intent intent=new Intent(MainActivity.this,Service_1.class);
                intent.putStringArrayListExtra("newlist",list);
                stopService(intent);
            }
        });*/
        /*Iterator iter=jsonObject.keys();
        while(iter.hasNext()) {
            String key = (String)iter.next();
            try {
                if(key.equals("title"))
                    value = (String) jsonObject.get(key);

            } catch (JSONException e) {
                Log.d( "Json", "hasNext error");
            }
        }*/

        //t2=(TextView) findViewById(R.id.textView2);//textview for displaying question for checking symptoms of user


    }
    Toast Toast;
    @Override
    protected  void onResume(){// for onresume activity cycle
        super.onResume();
        Log.i(TAG,"Currently on onResume");
        Toast.makeText(getApplicationContext(),"Currently on onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected  void onStart(){// for onstart activity cycle
        super.onStart();
        Log.i(TAG,"Currently on onStart");
        Toast.makeText(getApplicationContext(),"Currently on onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected  void onStop(){// for onstop activity cycle
        super.onStop();
        Log.i(TAG,"Currently on onStop");
        Toast.makeText(getApplicationContext(),"Currently on onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected  void onDestroy(){// for ondestroy activity cycle
        super.onDestroy();
        Log.i(TAG,"Currently on onDestroy");
        Toast.makeText(getApplicationContext(),"Currently on onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected  void onPause(){// for onpause activity cycle
        super.onPause();
        Log.i(TAG,"Currently on onPause");
        Toast.makeText(getApplicationContext(),"Currently on onResume", Toast.LENGTH_SHORT).show();
    }

        //for extracting
        /*JSONParser jParser = new JSONParser();
        JSONObject jsons = jParser.getJSONFromUrl("http://ap2.finance.xcom.tw:8080/app/?m=z8Ga37UPPQbEDMxR&mode=get_list&user_id=1");

        try {
            JSONObject status = jsons.getJSONObject("list_path");
            Iterator iter = status.keys();

            while(iter.hasNext()) {
                String key = (String)iter.next();
                try {
                    Object value = jsons.get(key);
                } catch (JSONException e) {
                    Log.d( "Json", "hasNext error");
                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        //end here
        public ArrayList<String> getMyData() {
            return list;
        }

}