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

import java.util.Random;

public class FragmentAquaticPets extends Fragment {
    @Nullable
    private Button btn_aquaticSearch,btn_auqaticHuDieYu,btn_auqaticDiao,btn_aquaticRand;
    private EditText edit_petKeyWord;
    private int page=1;
    private int pagesize=20;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_aquatic_pets,container,false);
        return  view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_aquaticSearch=getActivity().findViewById(R.id.btn_aquaticsearch);
        btn_auqaticHuDieYu=getActivity().findViewById(R.id.btn_aquatichudieyu);
        btn_auqaticDiao=getActivity().findViewById(R.id.btn_aquaticdiao);
        btn_aquaticRand=getActivity().findViewById(R.id.btn_aquaticrand);
        edit_petKeyWord=getActivity().findViewById(R.id.edt_aquaticPetsName);
        btn_aquaticSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord",edit_petKeyWord.getText().toString());
                intent.putExtra("adress","https://api.apishop.net/common/aquariumFamily/queryAquariumListByKeyword");
                intent.putExtra("category","aquatic");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        btn_auqaticHuDieYu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord","蝴蝶鱼");
                intent.putExtra("adress","https://api.apishop.net/common/aquariumFamily/queryAquariumListByKeyword");
                intent.putExtra("category","aquatic");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        btn_auqaticDiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord","鲷");
                intent.putExtra("adress","https://api.apishop.net/common/aquariumFamily/queryAquariumListByKeyword");
                intent.putExtra("category","aquatic");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
        btn_aquaticRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=(int)(Math.random()*10)+1;
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord","");
                intent.putExtra("adress","https://api.apishop.net/common/aquariumFamily/queryAquariumListByKeyword");
                intent.putExtra("category","aquatic");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
    }



}
