package com.example.tp_healthyfit.managerapi;
import com.example.tp_healthyfit.entitesapi.MetabolismeApi;

import java.util.ArrayList;
public class MetabolismeManagerApi {
    private static ArrayList<MetabolismeApi> metabolisme;
    static {
        metabolisme = new ArrayList<>();
    }
    public static ArrayList<MetabolismeApi> getAll() {
        return metabolisme;
    }
}
