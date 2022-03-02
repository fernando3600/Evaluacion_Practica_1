package com.example.evaluacion_practica_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ActivityPest extends AppCompatActivity {
    TabLayout tabL;
    ViewPager view1;
    TabItem tab1,tab2,tab3;
    PagerController pController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pest);
        tabL=findViewById(R.id.tab1);
        view1=findViewById(R.id.viewpagg1);
        tab1=findViewById(R.id.barPro);
        tab2=findViewById(R.id.gps);
        tab3=findViewById(R.id.Hora);
        Button salir = findViewById(R.id.btnSalir);

        pController= new PagerController(getSupportFragmentManager(),tabL.getTabCount());
        view1.setAdapter(pController);
        tabL.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view1.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0){
                    pController.notifyDataSetChanged();
                }
                if(tab.getPosition()==1){
                    pController.notifyDataSetChanged();
                }
                if(tab.getPosition()==2){
                    pController.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        view1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabL));

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityBienvenida.class);
                startActivity(intent);
            }
        });


    }
}