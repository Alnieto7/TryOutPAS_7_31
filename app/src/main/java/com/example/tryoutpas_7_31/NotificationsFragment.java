package com.example.tryoutpas_7_31;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class NotificationsFragment extends Fragment {
    TextView tvInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        return view;
    }
}
