package com.mc2022.template;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;

public class Service_1<k> extends Service {
    ArrayList<String> fv=new ArrayList<String>();
    String aurl = "https://petwear.in/mc2022/news/news_0.json";
    int k = 0;
    ArrayList<String> store=new ArrayList<String>();
    Thread thread=new Thread();
    boolean check=true;
    //public static int k=0;
    public Service_1() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return null;
        //throw new UnsupportedOperationException("Not yet implemented");
    }
    public boolean checkInternet()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            MainActivity.msg = "Internet Connectivity: Your Internet is Connected";
            Intent it = new Intent();
            it.setAction("internet");
            sendBroadcast(it);
            return true;
            //Toast.makeText(this,"Internet is Connected",Toast.LENGTH_SHORT).show();
        }
        else{
            MainActivity.msg = "Internet Connectivity: Your Internet is Not Connected";
            Intent it = new Intent();
            it.setAction("internet");
            sendBroadcast(it);
            return false;
            //Toast.makeText(this,"Internet is Not-Connected",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy(){
        Log.d("Current","service_is_destroyed");
        Toast.makeText(this,"Service destroyed",Toast.LENGTH_LONG).show();
        check=false;
        Intent i=new Intent();
        //i.setAction("stop_this");
        i.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
    }


    @Override
    public int onStartCommand(Intent intent, int flags,int startid){
        Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
        //checkInternet();
        //MainActivity.t="start service";

        new syncdata().execute();
        return Service.START_STICKY;
    }
    public class syncdata extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //head=s;

            //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            Log.d("Value",s);

            //return s;
        }

        @Override
        protected String doInBackground(String... strings){
            String builder="";
            checkInternet();

            //if(checkInternet()) {
                try {



                    String aurl1 = aurl.substring(0, 36);
                    String aurl2 = aurl.substring(37, 42);
                    boolean con=checkInternet();
                    while (k<100) {//how many news we want to download
                        if(k==22)
                            continue;
                        checkInternet();
                        builder = "";
                        //String aurl="https://petwear.in/mc2022/news/news_1.json";
                        aurl = aurl1 + Integer.valueOf(k) + aurl2;
                        URL url = new URL(aurl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.connect();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while (true) {
                            if(k==22)
                                continue;
                            String rdln = rd.readLine();
                            String data = rdln;
                            if (data == null) {
                                break;
                            }
                            JSONObject j = new JSONObject(data);
                            String headline = j.getString("title");
                            String body=j.getString("body");
                            String img=j.getString("image-url");
                            Log.d("headline", headline);
                            Log.d("description", body);
                            MainActivity.title = "Title: "+headline;
                            MainActivity.body="Body: "+body;
                            URL imurl = new URL(img);
                            HttpURLConnection conn = (HttpURLConnection) imurl.openConnection();
                            conn.connect();
                            InputStream is = conn.getInputStream();
                            Bitmap map = BitmapFactory.decodeStream(is);
                            MainActivity.image=map;
                            Intent it = new Intent();
                            it.setAction("start_this");
                            sendBroadcast(it);
                            fv.add(headline);
                            MainActivity.list=fv;
                            builder += (data);
                            store.add(data);
                            //head+=data;
                        }
                        /*try {
                            thread.sleep(50000);//define pause time
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        //Print a message
                        //System.out.println(importantInfo[i]);
                        k++;
                    }
                }
                catch (FileNotFoundException e) {
                    k++;
                    //continue;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            //}
            //head=builder.toString();
            //MainActivity.list=fv;
            return builder;
            //return head;
        }
    }
}