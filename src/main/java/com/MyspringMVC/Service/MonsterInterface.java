package com.MyspringMVC.Service;

import com.formatData.Monster;

import java.util.List;

public interface MonsterInterface {
    List<Monster> listMonsters();
    public List<Monster> findMonster(String name);
}
