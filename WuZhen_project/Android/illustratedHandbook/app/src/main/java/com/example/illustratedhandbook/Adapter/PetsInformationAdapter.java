package com.example.illustratedhandbook.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.illustratedhandbook.PetDetails;
import com.example.illustratedhandbook.R;
import com.example.illustratedhandbook.db.MyDbFavorites;

import java.util.List;

public class PetsInformationAdapter extends RecyclerView.Adapter<PetsInformationAdapter.ViewHolder> {
    Context mcontext;
    String petcategoty;
    String userName;
    List<JsonToBean.ResultBean.PetFamilyListBean> petlist;
    MyDbFavorites dbFavorites;
    SQLiteDatabase db;

    public PetsInformationAdapter(Context context,String category,String userName,List<JsonToBean.ResultBean.PetFamilyListBean> petlist) {
        mcontext=context;
        petcategoty=category;
        this.petlist=petlist;
        this.userName=userName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_pet,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        dbFavorites=new MyDbFavorites(view.getContext());
        db=dbFavorites.getWritableDatabase();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                switch (petcategoty){
                    case "aquatic":
                        Intent intent=new Intent(v.getContext(), PetDetails.class);
                        intent.putExtra("adress","https://api.apishop.net/common/aquariumFamily/queryAquariumInfo");
                        intent.putExtra("petid",petlist.get(position).getPetID());
                        intent.putExtra("imgurl",petlist.get(position).getCoverURL());
                        intent.putExtra("category",petcategoty);
                        v.getContext().startActivity(intent);
                        break;
                    case "small":
                        Intent intents=new Intent(v.getContext(), PetDetails.class);
                        intents.putExtra("adress","https://api.apishop.net/common/smallPetFamily/querySmallPetInfo");
                        intents.putExtra("petid",petlist.get(position).getPetID());
                        intents.putExtra("imgurl",petlist.get(position).getCoverURL());
                        intents.putExtra("category",petcategoty);
                        v.getContext().startActivity(intents);
                        break;
                    case "retiles":
                        Intent intentr=new Intent(v.getContext(), PetDetails.class);
                        intentr.putExtra("adress","https://api.apishop.net/common/reptileFamily/queryReptileInfo");
                        intentr.putExtra("petid",petlist.get(position).getPetID());
                        intentr.putExtra("imgurl",petlist.get(position).getCoverURL());
                        intentr.putExtra("category",petcategoty);
                        v.getContext().startActivity(intentr);
                        break;
                    default:
                        return ;
                }
            }
        });
        viewHolder.btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Cursor cursor=db.rawQuery("select * from tb_UserFavorites where UserName=? and category=? and petId=? ",new String[]{userName,petcategoty,Integer.toString(petlist.get(position).getPetID())});
                if(cursor.getCount()==0){
                    ContentValues values=new ContentValues();
                    values.put("petID",petlist.get(position).getPetID());
                    values.put("petName",petlist.get(position).getName());
                    values.put("petEngName",petlist.get(position).getEngName());
                    values.put("petPrice",petlist.get(position).getPrice());
                    values.put("petUrl",petlist.get(position).getCoverURL());
                    values.put("category",petcategoty);
                    values.put("userName",userName);
                    db.insert("tb_UserFavorites",null,values);
                    Toast.makeText(parent.getContext(),"收藏成功",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(parent.getContext(),"您已收藏该条目，可前往我的收藏查看",Toast.LENGTH_LONG).show();
                }

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mcontext).load(petlist.get(position).getCoverURL()).into(holder.image_Pet);
        holder.text_ChiName.setText("中文名："+petlist.get(position).getName());
        holder.text_EngName.setText("英文名:"+petlist.get(position).getEngName());
        holder.text_Price.setText("价格:"+petlist.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
       return petlist.size();
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
