package com.example.tryoutpas_7_31;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DashboardFragment extends Fragment {
    Button btnPremiereLeague, btnSpainLeague;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btnPremiereLeague = view.findViewById(R.id.btnPremiereLeague);
        btnSpainLeague = view.findViewById(R.id.btnSpainLeague);

        btnPremiereLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadChildFragment(new PremiereLeagueFragment());
            }
        });

        btnSpainLeague.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadChildFragment(new SpainLeagueFragment());
            }
        });

        return view;
    }

    private void loadChildFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.childFragmentContainer, fragment);
        transaction.commit();
    }
}
