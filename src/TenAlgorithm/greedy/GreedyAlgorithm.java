package TenAlgorithm.greedy;

import java.util.*;


//贪心算法解决集合覆盖问题
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到map中
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //创建电台覆盖set
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");
        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");
        HashSet<String> set3 = new HashSet<>();
        set3.add("上海");
        set3.add("杭州");
        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");
        HashSet<String> set5 = new HashSet<>();
        set5.add("成都");
        set5.add("杭州");
        set5.add("大连");
        //将电台放入broadcasts
        broadcasts.put("k1",set1);
        broadcasts.put("k2",set2);
        broadcasts.put("k3",set3);
        broadcasts.put("k4",set4);
        broadcasts.put("k5",set5);
        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        for(Map.Entry<String, HashSet<String>> areas:broadcasts.entrySet()){
            allAreas.addAll(areas.getValue());
        }
        //System.out.println(allAreas);
        //创建ArrayList 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合，在遍历过程中存放电台能覆盖的地区和当前没有覆盖地区的交集
        HashSet<String> tempset = new HashSet<>();

        //定义一个maxkey，保存在一次遍历过程中能够覆盖的最多未覆盖地区对应的电台
        //如果maxkey不等于空 则加入到selects里面
        String maxkey = null;
        HashSet<String> maxArea = null;
        while(allAreas.size() != 0){//如果allAreas不等于零，则表示还没有覆盖所有地区
            //开始遍历broadcasts
            maxkey = null;//将maxkey置空
            maxArea = null;
            for(String key:broadcasts.keySet()){
                tempset.clear();//将tempset清空
                HashSet<String> areas = broadcasts.get(key);//当前这个key能覆盖的地区
                tempset.addAll(areas);
                tempset.retainAll(allAreas);//求出tempset和allAreas的交集 交集会赋给tempset
                if(tempset.size() > 0 &&
                        (maxkey == null || tempset.size() > maxArea.size())){
                    maxkey = key;//如果当前电台的覆盖地区比maxkey覆盖地区还多 则重置maxkey
                    //上句为贪婪算法特点所在 每次都选最优的
                    maxArea = broadcasts.get(maxkey);
                    maxArea.retainAll(allAreas);
                }
            }

            if(maxkey != null){
                selects.add(maxkey);
                //将maxkey指向的广播电台覆盖的地区从allAreas中去除调
                allAreas.removeAll(broadcasts.get(maxkey));
            }
        }

        System.out.println(selects);
    }
}
