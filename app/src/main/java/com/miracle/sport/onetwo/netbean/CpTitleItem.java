package com.miracle.sport.onetwo.netbean;

import com.miracle.sport.onetwo.entity.CollecableAbs;

public class CpTitleItem extends CollecableAbs {

    /**
     * type : fc3d
     * name : 福彩3D
     */

    private String type;
    private String name;
    private boolean LOCAL_isCollected = true;
    public boolean isLOCAL_isCollected() {
        return LOCAL_isCollected;
    }
    private String key;


    @Override
    public void setKey(String key) {
        this.key = key;
        this.type = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setLOCAL_isCollected(boolean LOCAL_isCollected) {
        this.LOCAL_isCollected = LOCAL_isCollected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        this.key = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
