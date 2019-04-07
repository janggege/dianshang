package com.example.gwc.bean;

import java.util.List;

/*Time:2019/4/2
 *Author:刘江
 *Description:
 */public class Bean {
    public String message;
    public String status;
    public List<Data> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
