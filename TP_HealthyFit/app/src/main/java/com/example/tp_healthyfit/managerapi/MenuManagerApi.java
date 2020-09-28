package com.example.tp_healthyfit.managerapi;
import com.example.tp_healthyfit.entitesapi.MenuNutritionel;

import java.util.ArrayList;
public class MenuManagerApi {
    private static ArrayList<MenuNutritionel> menus;
    static {
        menus = new ArrayList<>();
    }
    public static ArrayList<MenuNutritionel> getAll() {
        return menus;
    }
}
