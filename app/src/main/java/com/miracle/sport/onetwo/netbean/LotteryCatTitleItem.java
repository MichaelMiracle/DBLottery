package com.miracle.sport.onetwo.netbean;

import java.io.Serializable;

public class LotteryCatTitleItem implements Serializable {
    int id;
    String name;

    @Override
    public String toString() {
        return "LotteryCatTitleItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
