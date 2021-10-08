package com.example.illustratedhandbook.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.illustratedhandbook.PetInformation;
import com.example.illustratedhandbook.R;

public class FragmentReptilePets extends Fragment {
    Button btn_retilesSearch;
    EditText edt_retilesPetsName;
    private int page=1;
    private int pagesize=20;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_reptiles_pets,container,false);
        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_retilesSearch=getActivity().findViewById(R.id.btn_retilessearch);
        edt_retilesPetsName=getActivity().findViewById(R.id.edt_retilesPetsName);
        btn_retilesSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PetInformation.class);
                intent.putExtra("keyWord",edt_retilesPetsName.getText().toString());
                intent.putExtra("adress","https://api.apishop.net/common/reptileFamily/queryReptileListByKeyword");
                intent.putExtra("category","retiles");
                intent.putExtra("page",page);
                intent.putExtra("pageSize",pagesize);
                intent.putExtra("userName",getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
    }
}
