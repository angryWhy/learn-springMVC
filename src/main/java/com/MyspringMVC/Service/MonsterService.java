package com.MyspringMVC.Service;

import com.formatData.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterService implements MonsterInterface{
    @Override
    public List<Monster> findMonster(String name) {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster());
        monsters.add(new Monster());
        for(Monster monster:monsters){
//            monster.getName().equals(name)
        }
        return null;
    }

    @Override
    public List<Monster> listMonsters() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster());
        monsters.add(new Monster());
        return monsters;
    }
    public boolean login(String name){
        if("白骨精".equals(name)){
            return true;
        }else {
            return false;
        }
    }
}
