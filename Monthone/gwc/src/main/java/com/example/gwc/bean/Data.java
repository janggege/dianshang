package com.example.gwc.bean;

import java.util.List;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public class Data {
    public String name;
    public List<com.example.gwc.bean.spus> spus;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<com.example.gwc.bean.spus> getSpus() {
        return spus;
    }

    public void setSpus(List<com.example.gwc.bean.spus> spus) {
        this.spus = spus;
    }
}
