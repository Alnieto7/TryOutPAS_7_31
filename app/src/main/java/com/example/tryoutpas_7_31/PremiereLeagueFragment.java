package com.example.tryoutpas_7_31;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PremiereLeagueFragment extends Fragment implements TeamAdapter.OnItemClickListener{
    private RecyclerView rvListClub;
    private TeamAdapter adapterListClub;
    private ArrayList<Team> dataClub = new ArrayList<>();
    private ProgressBar pbLoading;

    public PremiereLeagueFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler_view, container, false);

        rvListClub = view.findViewById(R.id.rvListClub);
        pbLoading = view.findViewById(R.id.pbLoading);

        setupRecyclerView();
        fetchDataClub();

        return view;
    }

    private void setupRecyclerView() {
        rvListClub.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void fetchDataClub() {
        pbLoading.setVisibility(View.VISIBLE);
        rvListClub.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterfacePremiereLeague apiService = retrofit.create(ApiInterfacePremiereLeague.class);
        Call<TeamResponse> call = apiService.getAllTeams();

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                pbLoading.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    dataClub = new ArrayList<>(response.body().getTeams());
                    adapterListClub = new TeamAdapter(dataClub, PremiereLeagueFragment.this);
                    rvListClub.setAdapter(adapterListClub);
                    rvListClub.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(Team clubModel) {
        Toast.makeText(getContext(), clubModel.getNamaClub() + " - " + clubModel.getStadion(), Toast.LENGTH_SHORT).show();

    }
}
