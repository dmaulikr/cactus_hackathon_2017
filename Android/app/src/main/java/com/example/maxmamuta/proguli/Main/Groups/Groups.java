package com.example.maxmamuta.proguli.Main.Groups;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maxmamuta.proguli.R;

/**
 * Created by Maxim on 5/14/2017.
 */

public class Groups extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_group, container, false);
        return v;
    }
}
