package com.example.illustratedhandbook.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.illustratedhandbook.PetInformation;
import com.example.illustratedhandbook.R;

public class FragmentSmallPets extends Fragment {
    @Nullable
    private Button btn_smallSearch,btn_smallRabbit,btn_smallYingWu,btn_smallRand;
    private EditText edt_smallPetsName;
    private int page=1;
    private int pagesize=20;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_small_pets,container,false);
        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_smallSearch=getActivity().findViewById(R.id.btn_smallsearch);
        edt_smallPetsName=getActivity().findViewById(R.id.edt_smallPetsName);
        btn_smallRabbit=getActivity().findViewById(R.id.btn_smallrabbit);
        btn_smallYingWu=getActivity().findViewById(R.id.btn_smallyingwu);
        btn_smallRand=getActivity().findViewById(R.id.btn_smallrand);
        btn_smallSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord",edt_smallPetsName.getText().toString());
                intent.putExtra("adress","https://api.apishop.net/common/smallPetFamily/querySmallPetListByKeyword");
                intent.putExtra("category","small");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        btn_smallRabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord","兔");
                intent.putExtra("adress","https://api.apishop.net/common/smallPetFamily/querySmallPetListByKeyword");
                intent.putExtra("category","small");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        btn_smallYingWu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord","鹦鹉");
                intent.putExtra("adress","https://api.apishop.net/common/smallPetFamily/querySmallPetListByKeyword");
                intent.putExtra("category","small");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        btn_smallRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=(int)(Math.random()*10)+1;
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord","");
                intent.putExtra("adress","https://api.apishop.net/common/smallPetFamily/querySmallPetListByKeyword");
                intent.putExtra("category","small");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
    }
}
