package com.lost.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description: 比心吧抽奖
 * @Auther: liuweicheng
 * @Date: 2020-03-22 13:16
 */
@SuppressWarnings("all")
public class LuckyDraw {

    public static void main(String[] args) {

        //中奖人数最大值
        int num = 10;
        //去重集合（中奖楼层）
        Set<Integer> winningFloor = new HashSet<>();

        for (int i = 0; i < num; i = winningFloor.size()) {
            //随机数 2-100 区间
            winningFloor.add(ThreadLocalRandom.current().nextInt(2, 100));

        }

        winningFloor.stream().forEach(s -> System.out.println(s));
    }
}
