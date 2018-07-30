package com.jason.springboot.application.DTO;

import java.util.List;

public class PunishDTO {
    private String item;
    private List<String> titleList;
    private List<String> chapterList;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public List<String> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<String> chapterList) {
        this.chapterList = chapterList;
    }
}
