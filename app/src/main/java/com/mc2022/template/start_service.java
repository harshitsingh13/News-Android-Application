package com.mc2022.template;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link start_service#newInstance} factory method to
 * create an instance of this fragment.
 */
public class start_service extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public start_service() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment start_service.
     */
    // TODO: Rename and change types and number of parameters
    TextView t1,t4,tsv;
    ImageView ig;
    adapter obf;
    //InputStream inp=null;
    //Bitmap bitmap=null;
    public static start_service newInstance(String param1, String param2) {
        start_service fragment = new start_service();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_start_service, container, false);
        t1=view.findViewById(R.id.text1);
        t4=view.findViewById(R.id.textView7);
        tsv=view.findViewById(R.id.textsv);
        ig=view.findViewById(R.id.imageView);
        IntentFilter intf=new IntentFilter("start_this");
        IntentFilter intf1=new IntentFilter("internet");
        //t1.setText("MainActivity.title");
        /*Bundle arguments = getArguments();
        String t= arguments.get("title").toString();
        String d= arguments.get("desc").toString();*/
        Brec rec=new Brec(){
            @Override
            public void onReceive(Context context, Intent intent){
                {

                    int idx=adapter.idx;
                    String dothis=intent.getAction();
                    if(dothis=="start_this"){
                        t1.setText(adapter.arr.get(idx).getTitle());
                        //t1.setText(MainActivity.title);
                        //tsv.setText(MainActivity.body);
                        tsv.setText(adapter.arr.get(idx).getDesc());
                        tsv.setMovementMethod(new ScrollingMovementMethod());
                        //ig.setImageBitmap(MainActivity.image);
                        ig.setImageBitmap(adapter.arr.get(idx).getImg());
                        //Load load=new Load(ig);
                        //load.execute(MainActivity.image);
                        /*try {
                            inp = new java.net.URL(MainActivity.image).openStream();
                            bitmap= BitmapFactory.decodeStream(inp);
                            ig.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/

                    }
                    else if(dothis=="internet"){
                        t4.setText(MainActivity.msg);
                    }
                }
            }
        };
        intf.addAction("start_this");
        intf1.addAction("internet");
        getActivity().registerReceiver(rec,intf);
        getActivity().registerReceiver(rec,intf1);
        return view;
        //return inflater.inflate(R.layout.fragment_start_service, container, false);
    }
    public class  Load extends AsyncTask<String,Void,Bitmap> {
        ImageView ig;
        public Load(ImageView res){
            this.ig=res;
        }

        @Override
        protected Bitmap doInBackground(String... strings){
            String url=strings[0];
            Bitmap bitmap=null;
            try {
                InputStream inp=new java.net.URL(url).openStream();
                bitmap= BitmapFactory.decodeStream(inp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected  void onPostExecute(Bitmap bitmap){
            ig.setImageBitmap(bitmap);
        }
    }
}