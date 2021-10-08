package com.example.illustratedhandbook.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.illustratedhandbook.JsonToBean;
import com.example.illustratedhandbook.R;
import com.example.illustratedhandbook.db.MyDbFavorites;

import java.util.ArrayList;
import java.util.List;

public class MyFavoriteAdapter extends RecyclerView.Adapter<MyFavoriteAdapter.ViewHolder> {
    List <JsonToBean.ResultBean.PetFamilyListBean>myFavoritesList=new ArrayList<>();
    Context mcontext;
    String userName;
    MyDbFavorites myDbFavorites;
    SQLiteDatabase db;
    public MyFavoriteAdapter(Context context,String userName,List <JsonToBean.ResultBean.PetFamilyListBean>myfavoriteslist) {
        myFavoritesList=myfavoriteslist;
        mcontext=context;
        this.userName=userName;
    }

    @NonNull
    @Override
    public MyFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_pet,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        myDbFavorites=new MyDbFavorites(parent.getContext());
        db=myDbFavorites.getWritableDatabase();
        viewHolder.btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                myFavoritesList.remove(position);
                notifyItemRemoved(position);//提醒item删除指定数据，有动画效果。
                db.delete("tb_UserFavorites","UserName=?and petName=?",new String[]{userName,myFavoritesList.get(position).getName()});
                Toast.makeText(parent.getContext(),"取消收藏成功",Toast.LENGTH_LONG).show();
            }
        });
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyFavoriteAdapter.ViewHolder holder, int position) {
        holder.btn_favorite.setText("取消收藏");
        Glide.with(mcontext).load(myFavoritesList.get(position).getCoverURL()).into(holder.image_Pet);
        holder.text_ChiName.setText("中文名："+myFavoritesList.get(position).getName());
        holder.text_EngName.setText("英文名:"+myFavoritesList.get(position).getEngName());
        holder.text_Price.setText("价格:"+myFavoritesList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return myFavoritesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_Pet;
        TextView text_ChiName,text_EngName,text_Price;
        Button btn_favorite;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_Pet=itemView.findViewById(R.id.img_pet);
            text_ChiName=itemView.findViewById(R.id.txt_chiname);
            text_EngName=itemView.findViewById(R.id.txt_engname);
            text_Price=itemView.findViewById(R.id.txt_price);
            btn_favorite=itemView.findViewById(R.id.btn_favorites);
        }
    }
}
