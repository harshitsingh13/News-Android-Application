package com.mc2022.template;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    public static ArrayList<model> arr=new ArrayList<model>();
    public static int idx=0;

    Context context;
    start_service f1=new start_service();

    public adapter(ArrayList<model> arr, Context context) {
        this.arr = arr;
        this.context=context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rows,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position)
    {
        model temp= arr.get(position);
        idx=position;
        holder.ig.setImageBitmap(arr.get(position).getImg());
        holder.t1.setText(arr.get(position).getTitle());

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Service_1.class);
                //Bundle bundle=new Bundle();
                //bundle.putString("title",temp.getTitle());
                //bundle.putString("desc",temp.getDesc());
                //intent.putExtra("image",temp.getImg());
                //bundle.put
                //f1.setArguments(bundle);
                //intent.putExtra("title",temp.getTitle());
                /*intent.putExtra("image",temp.getImg());
                intent.putExtra("title",temp.getTitle());
                intent.putExtra("description",temp.getDesc());*/
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,f1);
                fragmentTransaction.commit();

                //intent.putStringArrayListExtra("newlist",list);
                context.startService(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return arr.size();
    }

    //////////////////////////////////////


    class viewholder extends RecyclerView.ViewHolder {
        ImageView ig;
        TextView t1;
        public viewholder(View itemView) {
            super(itemView);
            ig=itemView.findViewById(R.id.imageView3);
            t1=itemView.findViewById(R.id.textView5);

        }

        /*@Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }*/
    }
}
