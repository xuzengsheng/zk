package com.nccs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @program: wx-mall
 * @author: xuzengsheng
 * @create: 2020-11-23 14:33
 * @description:通用的list转json tree的工具类
 **/
public class TreeBuilder {
    /**
     - listToTree
     - <p>方法说明<p>
     - 将JSONArray数组转为树状结构
     - @param arr 需要转化的数据
     - @param id 数据唯一的标识键值
     - @param pid 父id唯一标识键值
     - @param child 子节点键值
     - @return JSONArray
     */
    public static JSONArray listToTree(JSONArray arr, String id, String pid, String child){
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        //将数组转为Object的形式，key为数组中的id
        for(int i=0;i<arr.size();i++){
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString(id), json);
        }
        //遍历结果集
        for(int j=0;j<arr.size();j++){
            //单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            //在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get(pid).toString());
            //如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if(hashVP!=null){
                //检查是否有child属性
                if(hashVP.get(child)!=null){
                    JSONArray ch = (JSONArray) hashVP.get(child);
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }else{
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put(child, ch);
                }
            }else{
                r.add(aVal);
            }
        }
        return r;
    }


    public static void main(String[] args) {
        List list = new ArrayList();

        Map map = new HashMap();
        map.put("id","t1");
        map.put("parentId","t0");
        map.put("name","tom");

        Map map1 = new HashMap();
        map1.put("id","t2");
        map1.put("parentId","t1");
        map1.put("name","cat");

        Map map2 = new HashMap();
        map2.put("id","t3");
        map2.put("parentId","t2");
        map2.put("name","jerry");

        Map map3 = new HashMap();
        map3.put("id","t4");
        map3.put("parentId","t0");
        map3.put("name","jeck");

        Map map4 = new HashMap();
        map4.put("id","t5");
        map4.put("parentId","t1");
        map4.put("name","jeck");

        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        JSONArray result = TreeBuilder.listToTree(JSONArray.parseArray(JSON.toJSONString(list)),"id","parentId","children");
        System.out.println(result);

    }


}
