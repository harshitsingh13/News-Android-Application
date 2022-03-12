package com.mc2022.template;

import static android.content.Intent.getIntent;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link stop_service#newInstance} factory method to
 * create an instance of this fragment.
 */
public class stop_service extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView t2;
    ArrayList<String> tp=new ArrayList<String>();
    public stop_service() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment stop_service.
     */
    // TODO: Rename and change types and number of parameters
    public static stop_service newInstance(String param1, String param2) {
        stop_service fragment = new stop_service();
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
        MainActivity activity = (MainActivity) getActivity();
        tp = activity.getMyData();
        View view=inflater.inflate(R.layout.fragment_stop_service, container, false);
        t2=view.findViewById(R.id.textView);
        IntentFilter intf=new IntentFilter("stop_this");
        ArrayList<String> headline=new ArrayList<>();


        t2.setText("TOP FIVE NEWS ARE: "+tp.get(0)+", "+tp.get(1)+", "+tp.get(2)+", "+tp.get(3)+", "+tp.get(4));
        t2.setTextColor(Color.RED);
        Log.d("top is",tp.get(2));
        /*Brec rec=new Brec(){
            @Override
            public void onReceive(Context context, Intent intent){
                {
                    String dothis=intent.getAction();

                    if(dothis=="stop_this"){
                        //Log.d("msg","Service destroyed");

                        t2.setText("TOP FIVE NEWS ARE:");
                    }
                }
            }
        };*/
        intf.addAction("stop_this");
        //getActivity().registerReceiver(rec,intf);
        return view;
        //return inflater.inflate(R.layout.fragment_start_service, container, false);
    }


}