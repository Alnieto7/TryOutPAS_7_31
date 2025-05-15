package com.example.tryoutpas_7_31;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterfaceSpainLeague {
    @GET("/api/v1/json/3/search_all_teams.php?l=Spanish%20La%20Liga")
    Call<TeamResponse> getAllTeams();
}
