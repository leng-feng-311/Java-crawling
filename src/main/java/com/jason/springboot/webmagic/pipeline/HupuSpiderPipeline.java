package com.jason.springboot.webmagic.pipeline;

import com.jason.springboot.application.DTO.PunishDTO;
import com.jason.springboot.dao.PunishEntityMapper;
import com.jason.springboot.model.PunishEntityWithBLOBs;
import com.jason.springboot.utils.NumberChangeToChinese;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiazefeng on 18/07/26.
 */
@Component("PostInfoPipeline")
public class HupuSpiderPipeline implements Pipeline {

    @Autowired
    private PunishEntityMapper punishEntityMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        NumberChangeToChinese numToChinese = new NumberChangeToChinese();
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().equals("punishInfo")) {
                PunishDTO punishDTO = (PunishDTO) entry.getValue();
                List<String> titleList = new ArrayList<>();//标题List
                List<String> chapterList = new ArrayList<>();//章节list
                List<String> chapterGroup1s = new ArrayList<>();
                List<String> chapterGroup2s = new ArrayList<>();
                List<String> chapterGroup3s = new ArrayList<>();
                List<String> itemGroup1s = new ArrayList<>();
                List<String> itemGroup2s = new ArrayList<>();
                List<String> itemGroup3s = new ArrayList<>();
                List<String> itemGroup4s = new ArrayList<>();
                List<String> itemGroup5s = new ArrayList<>();
                List<String> itemGroup6s = new ArrayList<>();
                List<String> itemGroup7s = new ArrayList<>();
                List<String> itemGroup8s = new ArrayList<>();
                List<String> itemGroup9s = new ArrayList<>();
                List<String> itemGroup10s = new ArrayList<>();
                List<String> itemGroup11s = new ArrayList<>();
                List<String> itemGroup12s = new ArrayList<>();
                List<String> itemGroup13s = new ArrayList<>();
                if (punishDTO.getChapterList() != null && punishDTO.getChapterList().size() > 0) {
                    punishDTO.getChapterList().forEach((chapter) -> {
                        if (!chapter.equals("")) {
                            chapterList.add(chapter);
                        }
                    });
                    chapterGroup1s = chapterList.subList(0, 5);
                    chapterGroup2s = chapterList.subList(5, 12);
                    chapterGroup3s = chapterList.subList(12, chapterList.size());
                }
                if (punishDTO.getItem() != null && !punishDTO.getItem().isEmpty()) {
                    String str = punishDTO.getItem();
                    String[] s = str.split("。");
                    List<String> itemList = new ArrayList<>();
                    String ss = "";
                    int num = 1;
                    for (int i = 0; i < s.length; i++) {
                        String s2 = s[i];
                        String nums = numToChinese.tranWan(num);
                        String s3 = "第" + nums + "条";
                        int k = s2.indexOf(s3);
                        System.out.println(k);
                        if (k == -1) {
                            ss += s[i];
                            for (String si : itemList) {
                                if (ss.indexOf(si) != -1) {
                                    itemList.remove(si);
                                    break;
                                }
                            }
                            itemList.add(ss);
                        } else {
                            ss = s[i];
                            itemList.add(ss);
                            num++;
                        }
                    }
                    itemGroup1s = itemList.subList(0, 3);
                    itemGroup2s = itemList.subList(3, 8);
                    itemGroup3s = itemList.subList(8, 20);
                    itemGroup4s = itemList.subList(20, 29);
                    itemGroup5s = itemList.subList(29, 33);
                    itemGroup6s = itemList.subList(33, 45);
                    itemGroup7s = itemList.subList(45, 56);
                    itemGroup8s = itemList.subList(56, 100);
                    itemGroup9s = itemList.subList(100, 114);
                    itemGroup10s = itemList.subList(114, 126);
                    itemGroup11s = itemList.subList(126, 132);
                    itemGroup12s = itemList.subList(132, 160);
                    itemGroup13s = itemList.subList(160, itemList.size());
                }
                if (punishDTO.getTitleList() != null && punishDTO.getTitleList().size() > 0) {
                    int i = 1;
                    for (String title : punishDTO.getTitleList()) {
                        PunishEntityWithBLOBs titles = new PunishEntityWithBLOBs();
                        titles.setTitle(title);
                        titles.setCategoryid(1);
                        titles.setLevel(1);
                        punishEntityMapper.insert(titles);
                        if (i == 1) {
                            if (chapterGroup1s != null && chapterGroup1s.size() > 0) {
                                int j = 1;
                                for (String chapter : chapterGroup1s) {
                                    PunishEntityWithBLOBs chap = addChapter(chapter, titles.getId(), titles.getCategoryid());
                                    if (j == 1) {
                                        if (itemGroup1s != null && itemGroup1s.size() > 0) {
                                            addItem(itemGroup1s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 2) {
                                        if (itemGroup2s != null && itemGroup2s.size() > 0) {
                                            addItem(itemGroup2s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 3) {
                                        if (itemGroup3s != null && itemGroup3s.size() > 0) {
                                            addItem(itemGroup3s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 4) {
                                        if (itemGroup4s != null && itemGroup4s.size() > 0) {
                                            addItem(itemGroup4s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 5) {
                                        if (itemGroup5s != null && itemGroup5s.size() > 0) {
                                            addItem(itemGroup5s, chap.getId(), chap.getCategoryid());
                                        }
                                    }
                                    j++;
                                }
                            }
                        } else if (i == 2) {
                            if (chapterGroup2s != null && chapterGroup2s.size() > 0) {
                                int j = 6;
                                for (String chapter : chapterGroup2s) {
                                    PunishEntityWithBLOBs chap = addChapter(chapter, titles.getId(), titles.getCategoryid());
                                    if (j == 6) {
                                        if (itemGroup6s != null && itemGroup6s.size() > 0) {
                                            addItem(itemGroup6s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 7) {
                                        if (itemGroup7s != null && itemGroup7s.size() > 0) {
                                            addItem(itemGroup7s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 8) {
                                        if (itemGroup8s != null && itemGroup8s.size() > 0) {
                                            addItem(itemGroup8s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 9) {
                                        if (itemGroup9s != null && itemGroup9s.size() > 0) {
                                            addItem(itemGroup9s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 10) {
                                        if (itemGroup10s != null && itemGroup10s.size() > 0) {
                                            addItem(itemGroup10s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 11) {
                                        if (itemGroup11s != null && itemGroup11s.size() > 0) {
                                            addItem(itemGroup11s, chap.getId(), chap.getCategoryid());
                                        }
                                    } else if (j == 12) {
                                        if (itemGroup12s != null && itemGroup12s.size() > 0) {
                                            addItem(itemGroup12s, chap.getId(), chap.getCategoryid());
                                        }
                                    }
                                    j++;
                                }
                            }
                        } else if (i == 3) {
                            if (chapterGroup3s != null && chapterGroup3s.size() > 0) {
                                for (String chapter : chapterGroup3s) {
                                    PunishEntityWithBLOBs chap = addChapter(chapter, titles.getId(), titles.getCategoryid());
                                    if (itemGroup13s != null && itemGroup13s.size() > 0) {
                                        addItem(itemGroup13s, chap.getId(), chap.getCategoryid());
                                    }
                                }
                            }
                        }
                        i++;
                    }
                }
            }
            if (entry.getKey().equals("aslInfo")) {
                PunishDTO punishDTO = (PunishDTO) entry.getValue();
                List<String> titles = new ArrayList<>();
                List<String> chapters = new ArrayList<>();
                List<String> finalChapters = new ArrayList<>();
                List<String> chapterList = new ArrayList<>();
                List<String> chapterGroup1s = new ArrayList<>();
                List<String> chapterGroup2s = new ArrayList<>();
                List<String> chapterGroup3s = new ArrayList<>();
                List<String> chapterGroup4s = new ArrayList<>();
                List<String> chapterGroup5s = new ArrayList<>();
                List<String> chapterGroup6s = new ArrayList<>();
                List<String> chapterGroup7s = new ArrayList<>();
                if (punishDTO.getChapterList() != null && punishDTO.getChapterList().size() > 0) {
                    punishDTO.getChapterList().forEach((chapter) -> {
                        if (!chapter.equals(" ")) {
                            chapters.add(chapter);
                        }
                    });
                    finalChapters = chapters.subList(8, chapters.size());
                    if (finalChapters != null && !finalChapters.isEmpty()) {
                        int num = 1;
                        String chap = "";
                        for (String chapter : finalChapters) {
                            String nums = numToChinese.tranWan(num);
                            String s3 = "第" + nums + "条";
                            int k = chapter.indexOf(s3);
                            if (k == -1) {
                                chap += chapter;
                                for (String c : chapterList) {
                                    if (chap.indexOf(c) != -1) {
                                        chapterList.remove(c);
                                        break;
                                    }
                                }
                                chapterList.add(chap);
                            } else {
                                chap = chapter;
                                chapterList.add(chap);
                                num++;
                            }
                        }
                        chapterGroup1s = chapterList.subList(0, 6);
                        chapterGroup2s = chapterList.subList(6, 14);
                        chapterGroup3s = chapterList.subList(14, 18);
                        chapterGroup4s = chapterList.subList(18, 29);
                        chapterGroup5s = chapterList.subList(29, 44);
                        chapterGroup6s = chapterList.subList(44, 49);
                        chapterGroup7s = chapterList.subList(49, chapterList.size());
                    }
                }
                if (punishDTO.getTitleList() != null && punishDTO.getTitleList().size() > 0) {
                    titles = punishDTO.getTitleList().subList(2, punishDTO.getTitleList().size());
                    if (titles != null && titles.size() > 0) {
                        int k = 1;
                        for (String title : titles) {
                            PunishEntityWithBLOBs titleEntity = new PunishEntityWithBLOBs();
                            titleEntity.setTitle(title);
                            titleEntity.setCategoryid(2);
                            titleEntity.setLevel(1);
                            punishEntityMapper.insert(titleEntity);
                            if (k == 1) {
                                if (chapterGroup1s != null && !chapterGroup1s.isEmpty()) {
                                    addChapter(chapterGroup1s, titleEntity.getId(), titleEntity.getCategoryid(), 2);
                                }
                            } else if (k == 2) {
                                if (chapterGroup2s != null && !chapterGroup2s.isEmpty()) {
                                    addChapter(chapterGroup2s, titleEntity.getId(), titleEntity.getCategoryid(), 2);
                                }
                            } else if (k == 3) {
                                if (chapterGroup3s != null && !chapterGroup3s.isEmpty()) {
                                    addChapter(chapterGroup3s, titleEntity.getId(), titleEntity.getCategoryid(), 2);
                                }
                            } else if (k == 4) {
                                if (chapterGroup4s != null && !chapterGroup4s.isEmpty()) {
                                    addChapter(chapterGroup4s, titleEntity.getId(), titleEntity.getCategoryid(), 2);
                                }
                            } else if (k == 5) {
                                if (chapterGroup5s != null && !chapterGroup5s.isEmpty()) {
                                    addChapter(chapterGroup5s, titleEntity.getId(), titleEntity.getCategoryid(), 2);
                                }
                            } else if (k == 6) {
                                if (chapterGroup6s != null && !chapterGroup6s.isEmpty()) {
                                    addChapter(chapterGroup6s, titleEntity.getId(), titleEntity.getCategoryid(), 2);
                                }
                            } else if (k == 7) {
                                if (chapterGroup7s != null && !chapterGroup7s.isEmpty()) {
                                    addChapter(chapterGroup7s, titleEntity.getId(), titleEntity.getCategoryid(), 2);
                                }
                            }
                            k++;
                        }
                    }
                }
            }
            if (entry.getKey().equals("aslirInfo")) {
                PunishDTO punishDTO = (PunishDTO) entry.getValue();
                System.out.println(punishDTO);
                List<String> lists = new ArrayList<>();
                List<String> titleList = new ArrayList<>();
                List<String> itemList = new ArrayList<>();
                List<String> items = new ArrayList<>();
                List<String> itemGroup1 = new ArrayList<>();
                List<String> itemGroup2 = new ArrayList<>();
                List<String> itemGroup3 = new ArrayList<>();
                List<String> itemGroup4 = new ArrayList<>();
                List<String> itemGroup5 = new ArrayList<>();
                if (punishDTO.getChapterList() != null && !punishDTO.getChapterList().isEmpty()) {
                    lists = punishDTO.getChapterList().subList(3, 118);
                    System.out.println(lists);
                    if (lists != null && !lists.isEmpty()) {
                        int num = 1;
                        for (String li : lists) {
                            String nums = numToChinese.tranWan(num);
                            String s3 = "第" + nums + "章";
                            int k = li.indexOf(s3);
                            if (k == -1) {
                                itemList.add(li);
                            } else {
                                titleList.add(li);
                                num++;
                            }
                        }
                        System.out.println(itemList);
                        if (itemList != null && !itemList.isEmpty()) {
                            int itnum = 1;
                            String item = "";
                            for (String it : itemList) {
                                String nums = numToChinese.tranWan(itnum);
                                String s = "第" + nums + "条";
                                int k = it.indexOf(s);
                                if (k != 1) {
                                    item += it;
                                    for (String c : items) {
                                        if (item.indexOf(c) != -1) {
                                            items.remove(c);
                                            break;
                                        }
                                    }
                                    items.add(item);
                                } else {
                                    item = it;
                                    items.add(item);
                                    itnum++;
                                }
                            }
                            System.out.println(items);
                            itemGroup1 = items.subList(0, 5);
                            itemGroup2 = items.subList(5, 9);
                            itemGroup3 = items.subList(9, 27);
                            itemGroup4 = items.subList(27, 43);
                            itemGroup5 = items.subList(43, items.size());
                        }
                        System.out.println(titleList);
                        if (titleList != null && titleList.size() > 0) {
                            int i = 1;
                            for (String title : titleList) {
                                PunishEntityWithBLOBs titl = addTitle(title, 1, 3);
                                if (i == 1) {
                                    if (itemGroup1 != null && !itemGroup1.isEmpty()) {
                                        addChapter(itemGroup1, titl.getId(), titl.getCategoryid(), 2);
                                    }
                                } else if (i == 2) {
                                    if (itemGroup2 != null && !itemGroup2.isEmpty()) {
                                        addChapter(itemGroup2, titl.getId(), titl.getCategoryid(), 2);
                                    }
                                } else if (i == 3) {
                                    if (itemGroup3 != null && !itemGroup3.isEmpty()) {
                                        addChapter(itemGroup3, titl.getId(), titl.getCategoryid(), 2);
                                    }
                                } else if (i == 4) {
                                    if (itemGroup4 != null && !itemGroup4.isEmpty()) {
                                        addChapter(itemGroup4, titl.getId(), titl.getCategoryid(), 2);
                                    }
                                } else if (i == 5) {
                                    if (itemGroup5 != null && !itemGroup5.isEmpty()) {
                                        addChapter(itemGroup5, titl.getId(), titl.getCategoryid(), 2);
                                    }
                                }
                                i++;
                            }
                        }
                    }
                }
            }
            if (entry.getKey().equals("cpcInfo")) {
                PunishDTO punishDTO = (PunishDTO) entry.getValue();
                List<String> titleList = new ArrayList<>();
                List<String> itemList = new ArrayList<>();
                List<String> items = new ArrayList<>();
                List<String> finalItem = new ArrayList<>();
                List<String> itemGroup = new ArrayList<>();
                List<String> itemGroup1 = new ArrayList<>();
                List<String> itemGroup2 = new ArrayList<>();
                List<String> itemGroup3 = new ArrayList<>();
                List<String> itemGroup4 = new ArrayList<>();
                List<String> itemGroup5 = new ArrayList<>();
                List<String> itemGroup6 = new ArrayList<>();
                List<String> itemGroup7 = new ArrayList<>();
                List<String> itemGroup8 = new ArrayList<>();
                List<String> itemGroup9 = new ArrayList<>();
                List<String> itemGroup10 = new ArrayList<>();
                List<String> itemGroup11 = new ArrayList<>();
                if (punishDTO.getTitleList() != null && !punishDTO.getTitleList().isEmpty()) {
                    titleList = punishDTO.getTitleList().subList(2, punishDTO.getTitleList().size());
                }
                if (punishDTO.getChapterList() != null && !punishDTO.getChapterList().isEmpty()) {
                    for (String c : punishDTO.getChapterList()) {
                        if (!c.equals("")) {
                            itemList.add(c);
                        }
                    }
                    itemGroup = itemList.subList(0, 30);
                    items = itemList.subList(30, itemList.size());
                    if (items != null && !items.isEmpty()) {
                        int itnum = 1;
                        String item = "";
                        for (String i : items) {
                            String nums = numToChinese.tranWan(itnum);
                            String s = "第" + nums + "条";
                            int k = i.indexOf(s);
                            if (k == -1) {
                                item += i;
                                for (String c : finalItem) {
                                    if (item.indexOf(c) != -1) {
                                        finalItem.remove(c);
                                        break;
                                    }
                                }
                                finalItem.add(item);
                            } else {
                                item = i;
                                finalItem.add(item);
                                itnum++;
                            }
                        }
                        itemGroup1 = finalItem.subList(0, 9);
                        itemGroup2 = finalItem.subList(9, 18);
                        itemGroup3 = finalItem.subList(18, 24);
                        itemGroup4 = finalItem.subList(24, 29);
                        itemGroup5 = finalItem.subList(29, 34);
                        itemGroup6 = finalItem.subList(34, 38);
                        itemGroup7 = finalItem.subList(38, 44);
                        itemGroup8 = finalItem.subList(44, 47);
                        itemGroup9 = finalItem.subList(47, 50);
                        itemGroup10 = finalItem.subList(50, 52);
                        itemGroup11 = finalItem.subList(52, finalItem.size());
                    }
                }
                if (titleList != null && !titleList.isEmpty()) {
                    int i = 0;
                    for (String t : titleList) {
                        PunishEntityWithBLOBs title = addTitle(t, 1, 4);
                        if (i == 0) {
                            if (itemGroup != null && !itemGroup.isEmpty()) {
                                addChapter(itemGroup, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 1) {
                            if (itemGroup1 != null && !itemGroup1.isEmpty()) {
                                addChapter(itemGroup1, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 2) {
                            if (itemGroup2 != null && !itemGroup2.isEmpty()) {
                                addChapter(itemGroup2, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 3) {
                            if (itemGroup3 != null && !itemGroup3.isEmpty()) {
                                addChapter(itemGroup3, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 4) {
                            if (itemGroup4 != null && !itemGroup4.isEmpty()) {
                                addChapter(itemGroup4, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 5) {
                            if (itemGroup5 != null && !itemGroup5.isEmpty()) {
                                addChapter(itemGroup5, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 6) {
                            if (itemGroup6 != null && !itemGroup6.isEmpty()) {
                                addChapter(itemGroup6, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 7) {
                            if (itemGroup7 != null && !itemGroup7.isEmpty()) {
                                addChapter(itemGroup7, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 8) {
                            if (itemGroup8 != null && !itemGroup8.isEmpty()) {
                                addChapter(itemGroup8, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 9) {
                            if (itemGroup9 != null && !itemGroup9.isEmpty()) {
                                addChapter(itemGroup9, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 10) {
                            if (itemGroup10 != null && !itemGroup10.isEmpty()) {
                                addChapter(itemGroup10, title.getId(), title.getCategoryid(), 2);
                            }
                        } else if (i == 11) {
                            if (itemGroup11 != null && !itemGroup11.isEmpty()) {
                                addChapter(itemGroup11, title.getId(), title.getCategoryid(), 2);
                            }
                        }
                        i++;
                    }
                }
            }
        }
    }

    private PunishEntityWithBLOBs addTitle(String title, int level, int categoryId) {
        PunishEntityWithBLOBs titl = new PunishEntityWithBLOBs();
        titl.setTitle(title);
        titl.setLevel(level);
        titl.setCategoryid(categoryId);
        punishEntityMapper.insert(titl);
        return titl;
    }

    private void addChapter(List<String> chapters, int parentId, int categoryId, int level) {
        chapters.forEach((chapterGroup) -> {
            PunishEntityWithBLOBs item = new PunishEntityWithBLOBs();
            item.setItems(chapterGroup);
            item.setParentid(parentId);
            item.setCategoryid(categoryId);
            item.setLevel(level);
            punishEntityMapper.insert(item);
        });
    }

    private PunishEntityWithBLOBs addChapter(String chapter, int parentId, int categoryId) {
        PunishEntityWithBLOBs chap = new PunishEntityWithBLOBs();
        chap.setChapter(chapter);
        chap.setParentid(parentId);
        chap.setCategoryid(categoryId);
        chap.setLevel(2);
        punishEntityMapper.insert(chap);
        return chap;
    }

    private void addItem(List<String> items, int parentId, int categoryId) {
        for (String item : items) {
            PunishEntityWithBLOBs it = new PunishEntityWithBLOBs();
            it.setItems(item);
            it.setParentid(parentId);
            it.setCategoryid(categoryId);
            it.setLevel(3);
            punishEntityMapper.insert(it);
        }

    }
}
