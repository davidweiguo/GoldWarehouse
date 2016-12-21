package com.goldwarehouse.common;

/**
 * Created by David on 2016/12/20.
 */
public class SystemInfo {
    private String env = "Test";
    private String category = "EB";
    private String id = "CSTW";
    private String url = "nio://localhost:61616";
    private String downStream = "DownStream";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEnv() {
        return env;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownStream() {
        return downStream;
    }

    public void setDownStream(String downStream) {
        this.downStream = downStream;
    }

    @Override
    public String toString() {
        return env + ":" + category + ":" + id + ":" + url;
    }
}
