package com.arianahmadifard.task.viewmodel;

import com.arianahmadifard.task.model.TaskResponse;
import com.arianahmadifard.task.model.TaskResponseItem;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MainViewModelTest extends TestCase {

    public List<TaskResponseItem> whenMerger() {
            List<TaskResponseItem> l1=new ArrayList<>();
            List<TaskResponseItem> l2=new ArrayList<>();
            TaskResponseItem item1A=new TaskResponseItem();
            TaskResponseItem item2A=new TaskResponseItem();
            TaskResponseItem item3A=new TaskResponseItem();
            TaskResponseItem item4A=new TaskResponseItem();
            TaskResponseItem item5A=new TaskResponseItem();
            TaskResponseItem item1B=new TaskResponseItem();
            TaskResponseItem item2B=new TaskResponseItem();
            TaskResponseItem item3B=new TaskResponseItem();
            TaskResponseItem item4B=new TaskResponseItem();
            TaskResponseItem item5B=new TaskResponseItem();
//*****************************
            item1A.setId(1);
            item1A.setDescription("Somethings");
            item1A.setCountry("Iran");
            item1A.setGuid("100");
            item1A.setLandscape("landscape-image");
            item1A.setPortrait("portrait-image");
            item1A.setUpdatedDate(1000200L);
            item1A.setLikeStatus(true);
            item1A.setName("Amir");

            item2A.setId(2);
            item2A.setDescription("Somethings");
            item2A.setCountry("Iran");
            item2A.setGuid("101");
            item2A.setLandscape("landscape-image");
            item2A.setPortrait("portrait-image");
            item2A.setUpdatedDate(1000201L);
            item2A.setLikeStatus(true);
            item2A.setName("Amir");

            item3A.setId(3);
            item3A.setDescription("Somethings");
            item3A.setCountry("Iran");
            item3A.setGuid("102");
            item3A.setLandscape("landscape-image");
            item3A.setPortrait("portrait-image");
            item3A.setUpdatedDate(1000200L);
            item3A.setLikeStatus(true);
            item3A.setName("Amir");

            item4A.setId(4);
            item4A.setDescription("Somethings");
            item4A.setCountry("Iran");
            item4A.setGuid("103");
            item4A.setLandscape("landscape-image");
            item4A.setPortrait("portrait-image");
            item4A.setUpdatedDate(1000200L);
            item4A.setLikeStatus(true);
            item4A.setName("Amir");

            item5A.setId(5);
            item5A.setDescription("Somethings");
            item5A.setCountry("Iran");
            item5A.setGuid("104");
            item5A.setLandscape("landscape-image");
            item5A.setPortrait("portrait-image");
            item5A.setUpdatedDate(1000200L);
            item5A.setLikeStatus(true);
            item5A.setName("Amir");
//*****************************
            item1B.setId(6);
            item1B.setDescription("Somethings");
            item1B.setCountry("Iran");
            item1B.setGuid("102");
            item1B.setLandscape("landscape-image");
            item1B.setPortrait("portrait-image");
            item1B.setUpdatedDate(1000199L);
            item1B.setLikeStatus(true);
            item1B.setName("Amir");

            item2B.setId(7);
            item2B.setDescription("Somethings");
            item2B.setCountry("Iran");
            item2B.setGuid("105");
            item2B.setLandscape("landscape-image");
            item2B.setPortrait("portrait-image");
            item2B.setUpdatedDate(1000200L);
            item2B.setLikeStatus(true);
            item2B.setName("Amir");

            item3B.setId(8);
            item3B.setDescription("Somethings");
            item3B.setCountry("Iran");
            item3B.setGuid("106");
            item3B.setLandscape("landscape-image");
            item3B.setPortrait("portrait-image");
            item3B.setUpdatedDate(1000250L);
            item3B.setLikeStatus(true);
            item3B.setName("Amir");

            item4B.setId(9);
            item4B.setDescription("Somethings");
            item4B.setCountry("Iran");
            item4B.setGuid("106");
            item4B.setLandscape("landscape-image");
            item4B.setPortrait("portrait-image");
            item4B.setUpdatedDate(1000202L);
            item4B.setLikeStatus(true);
            item4B.setName("Amir");


            item5B.setId(10);
            item5B.setDescription("Somethings");
            item5B.setCountry("Iran");
            item5B.setGuid("109");
            item5B.setLandscape("landscape-image");
            item5B.setPortrait("portrait-image");
            item5B.setUpdatedDate(1000240L);
            item5B.setLikeStatus(true);
            item5B.setName("Amir");
            //*****************************
           l1.add(item1A);
           l1.add(item2A);
           l1.add(item3A);
           l1.add(item4A);
           l1.add(item5A);
           //******************
           l2.add(item1B);
           l2.add(item2B);
           l2.add(item3B);
           l2.add(item4B);
           l2.add(item5B);

            Hashtable<String, TaskResponseItem> dict = new Hashtable<String, TaskResponseItem>();
            l1.addAll(l2);
            for (TaskResponseItem item : l1) {
                    TaskResponseItem obj = dict.get(item.getGuid());
                    if (obj == null)
                            dict.put(item.getGuid(), item);
                    else if (item.getUpdatedDate() > obj.getUpdatedDate()) {
                            dict.put(item.getGuid(), item);
                    } else { // uncomment to show removed items
                            System.out.println(item.getGuid() + " **** " + item.getGuid());
                    }
            }
            return new ArrayList<TaskResponseItem>(dict.values());
    }
}