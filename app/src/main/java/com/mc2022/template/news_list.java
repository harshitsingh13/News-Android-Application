package com.mc2022.template;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link news_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class news_list extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerview;
    public static ArrayList<model> data=new ArrayList<model>();;

    public news_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment news_list.
     */
    // TODO: Rename and change types and number of parameters
    public static news_list newInstance(String param1, String param2) {
        news_list fragment = new news_list();
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
        View view= inflater.inflate(R.layout.fragment_news_list, container, false);
        recyclerview=view.findViewById(R.id.recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        TextView t4=view.findViewById(R.id.textView3);
        IntentFilter intf=new IntentFilter("start_this");
        IntentFilter intf1=new IntentFilter("internet");
        Brec rec=new Brec(){
            @Override
            public void onReceive(Context context, Intent intent){
                {
                    String dothis=intent.getAction();
                    if(dothis=="start_this"){

                        model ob1=new model(MainActivity.image, MainActivity.title, MainActivity.body);
                        data.add(ob1);
                        recyclerview.setAdapter(new adapter(data, getActivity()));
                    }
                    else if(dothis=="internet"){
                        t4.setTextColor(Color.BLUE);
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
    }
}