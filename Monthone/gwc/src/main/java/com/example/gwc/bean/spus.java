package com.example.gwc.bean;

import com.example.gwc.bean.skus;

import java.util.List;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public class spus {
     public String name;
     public String pic_url;
     public String praise_num;
     public String restaurant_id;
     public String id;
     public String created_at;
     public List<skus> skus;
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

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPraise_num() {
        return praise_num;
    }

    public void setPraise_num(String praise_num) {
        this.praise_num = praise_num;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<com.example.gwc.bean.skus> getSkus() {
        return skus;
    }

    public void setSkus(List<com.example.gwc.bean.skus> skus) {
        this.skus = skus;
    }
}
