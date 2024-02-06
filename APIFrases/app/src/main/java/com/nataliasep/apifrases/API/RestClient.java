package com.nataliasep.apifrases.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String BASE_URL ="http://192.168.1.175:8080/";
    //"http://192.168.50.135:8080/";

    //http://192.168.50.145:8082/
    //http://frases.germangascon.com/
    private static IAPIService instance;
    private RestClient() {}
    public static IAPIService getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(IAPIService.class);
        }
        return instance;
    }
}
