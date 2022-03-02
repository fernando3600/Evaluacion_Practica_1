package com.example.evaluacion_practica_1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerController extends FragmentPagerAdapter {
    int numtabs;

    public PagerController(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numtabs=behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BarraP();
            case 2:
                return new gps();
            case 3:
                return new hora();
            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return numtabs;
    }
}
