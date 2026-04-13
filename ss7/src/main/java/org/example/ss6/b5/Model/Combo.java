package org.example.ss6.b5.Model;

import java.util.List;

public class Combo {
    private String comboName;
    private List<String> itemList;
    private String bannerUrl;

    public Combo(String comboName, List<String> itemList, String bannerUrl) {
        this.comboName = comboName;
        this.itemList = itemList;
        this.bannerUrl = bannerUrl;
    }

    public Combo() {
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
}