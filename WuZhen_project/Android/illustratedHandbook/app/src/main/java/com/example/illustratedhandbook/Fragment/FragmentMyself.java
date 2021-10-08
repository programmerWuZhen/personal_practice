package com.example.illustratedhandbook.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.illustratedhandbook.Adapter.MyFavoriteAdapter;
import com.example.illustratedhandbook.JsonToBean;
import com.example.illustratedhandbook.R;
import com.example.illustratedhandbook.db.MyDbFavorites;

import java.util.ArrayList;
import java.util.List;

public class FragmentMyself extends Fragment {
    @Nullable
    private TextView textUserName;
    private RecyclerView recycler_myFavorites;
    private MyDbFavorites dbFavorites;
    private SQLiteDatabase db;
    private String userName;
    private List <JsonToBean.ResultBean.PetFamilyListBean>myFavoritePetList=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1.创建view 视图
        View view=inflater.inflate(R.layout.fragment_myself,container,false);
        //2.返回view视图
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textUserName=getActivity().findViewById(R.id.text_view_username);
        recycler_myFavorites=getActivity().findViewById(R.id.recycler_usersfavorite);

        userName=getActivity().getIntent().getStringExtra("username");
        textUserName.setText(userName);
        dbFavorites=new MyDbFavorites(getActivity());
        db=dbFavorites.getWritableDatabase();
        initData();
        MyFavoriteAdapter adapter=new MyFavoriteAdapter(getActivity(),userName,myFavoritePetList);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recycler_myFavorites.setAdapter(adapter);
        recycler_myFavorites.setLayoutManager(manager);

    }

    private void initData() {
        Cursor cursor=db.rawQuery("select * from tb_UserFavorites where userName=?",new String[]{getActivity().getIntent().getStringExtra("username")});
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                JsonToBean.ResultBean.PetFamilyListBean bean=new JsonToBean.ResultBean.PetFamilyListBean();
                bean.setPetID(cursor.getInt(cursor.getColumnIndex("petId")));
                bean.setName(cursor.getString(cursor.getColumnIndex("petName")));
                bean.setEngName(cursor.getString(cursor.getColumnIndex("petEngName")));
                bean.setPrice(cursor.getString(cursor.getColumnIndex("petPrice")));
                bean.setCoverURL(cursor.getString(cursor.getColumnIndex("petUrl")));
                myFavoritePetList.add(bean);
            }

        }
        else{
            Toast.makeText(getActivity(),"您还没有收藏宠物哦，可以前去搜索并收藏",Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }
}
