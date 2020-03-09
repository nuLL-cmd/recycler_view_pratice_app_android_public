package com.example.recyclerviewpratice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     private RecyclerView recyclerView;
     private RecyclerView.LayoutManager layoutManager;
     private AdapterCustom adapter;
     private FloatingActionButton fb_main_add;
     private FloatingActionButton fb_main_remove;
     private  ArrayList<DataProvider> dataProviderArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fb_main_add = findViewById(R.id.fb_main_add);
        fb_main_remove = findViewById(R.id.fb_main_remove);

        createData();
        createRecycle();
        testClick();

        fb_main_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               remove();
            }
        });

        fb_main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               add();
            }
        });

    }

    public void remove(){
        View dialog = getLayoutInflater().inflate(R.layout.layout_input,null);
        final EditText edt_valor = dialog.findViewById(R.id.edt_valor);
        final Button btn_ok = dialog.findViewById(R.id.btn_ok);
        final AlertDialog alerta = new AlertDialog.Builder(MainActivity.this).create();
        alerta.setView(dialog);
        alerta.show();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(edt_valor.getText().toString());
                dataProviderArrayList.remove(position);
                adapter.notifyItemRemoved(position);
                alerta.dismiss();
            }
        });

    }
    public void add(){
        View dialog = getLayoutInflater().inflate(R.layout.layout_input,null);
        final EditText edt_valor = dialog.findViewById(R.id.edt_valor);
        final Button btn_ok = dialog.findViewById(R.id.btn_ok);
        final AlertDialog alerta = new AlertDialog.Builder(MainActivity.this).create();

        alerta.setView(dialog);
        alerta.show();

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(edt_valor.getText().toString());
                alerta.dismiss();
                dataProviderArrayList.add(position,new DataProvider("Adicionado linha"+position,"Linha 2",R.drawable.ic_android));
                adapter.notifyItemInserted(position);
            }
        });

    }

    public void createRecycle(){
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdapterCustom(dataProviderArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    public void createData(){
        dataProviderArrayList = new ArrayList<>();
        dataProviderArrayList.add(new DataProvider("Line 1","Line 2",R.drawable.ic_android));
        dataProviderArrayList.add(new DataProvider("Line 3","Line 4",R.drawable.ic_assignment));
        dataProviderArrayList.add(new DataProvider("Line 5","Line 6",R.drawable.ic_music));
        dataProviderArrayList.add(new DataProvider("Line 7","Line 8",R.drawable.ic_backup));

    }

    public void testClick(){
        adapter.setOnItemClickListener(new AdapterCustom.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String clicked = "Clickado";
                String noClicked = "Não Clicado";
                if(dataProviderArrayList.get(position).getAnything() == "Clickado")
                    change(position,noClicked);
                else
                    change(position,clicked);

            }
        });
    }

    public void change(int position, String clicked){
        dataProviderArrayList.get(position).toString(clicked);
        adapter.notifyItemChanged(position);
    }

}
