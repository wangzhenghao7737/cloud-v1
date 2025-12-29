package com.xiaosa.clouddemo.utils;

import com.xiaosa.clouddemo.domain.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GenerateUser {

    private static final String[] SURNAMES = {"赵","孙","李","王","陈","卫","沈","杨","朱","秦","许","吕","张","孔","曹","华","魏","苏","韦","昌","马","方","任","袁","柳","史","唐","薛","雷","贺","汤","滕","罗","安","常","齐","康","余","孟","黄","穆","萧","董"};
    private static final String[] MALE_NAMES = {
            "伟", "强", "磊", "军", "洋", "勇", "峰", "涛", "杰", "明",
            "超", "鹏", "浩", "亮", "健", "波", "鑫", "宇", "凯", "宁",
            "志", "刚", "毅", "豪", "俊", "睿", "哲", "轩", "航", "瑞",
            "文", "武", "斌", "翔", "飞", "阳", "晨", "旭", "昊", "博",
            "宏", "鸿", "楠", "川", "海", "泽", "清", "朗", "恒", "安",
            "平", "康", "栋", "龙", "霄", "越", "骏", "骁", "天佑", "博文",
            "浩然", "子豪", "俊杰", "宇轩", "景辰", "承宇", "致远", "睿哲", "弘文", "俊凯",
            "天宇", "浩宇", "泽宇", "铭轩", "峻熙", "宇航", "振宇", "佳豪", "子轩", "文博",
            "俊豪", "辰逸", "鸿涛", "嘉懿", "煜城", "烨磊", "旭尧", "昊焱", "俊驰", "鸿煊",
            "绍辉", "泽洋", "冠宇", "永康", "世杰", "荣轩", "健雄", "立诚", "明辉", "光辉"
    };
    private static final String[] FEMALE_NAMES = {
            "芳", "娜", "敏", "静", "丽", "娟", "艳", "玲", "雪", "婷",
            "丹", "梅", "倩", "琳", "颖", "莹", "洁", "霞", "燕", "莉",
            "慧", "晶", "芸", "茜", "薇", "蕊", "欣", "怡", "悦", "琪",
            "瑶", "璇", "瑾", "瑜", "涵", "淑", "清", "雅", "宁", "婉",
            "诗", "语", "思", "梦", "依", "心", "若", "芷", "萱", "蕾",
            "诗涵", "雨桐", "欣怡", "梓涵", "语彤", "可馨", "雅琪", "梦瑶", "静怡", "思颖",
            "婉婷", "雅欣", "雨欣", "若曦", "书涵", "依诺", "梓萌", "欣然", "佳怡", "语嫣",
            "诗琪", "梦洁", "雅静", "雨菲", "馨月", "瑾萱", "若彤", "思涵", "梓彤", "欣妍",
            "雅雯", "梦琪", "雨晴", "静雅", "可欣", "语晨", "诗瑶", "婉清", "瑾瑜", "涵韵"
    };
    private static final Random random = new Random();

    public static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            users.add(generateRandomUser(i));
        }
        return users;
    }

    private static User generateRandomUser(int seq) {
        User user = new User();

        boolean isMale = random.nextBoolean();
        int sex = isMale ? 0 : 1;

        String surname = SURNAMES[random.nextInt(SURNAMES.length)];
        String givenName = isMale
                ? MALE_NAMES[random.nextInt(MALE_NAMES.length)]
                : FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
        String name = surname + givenName;

        String account = "user_" + (1000000 + seq);
        String password = "123456";
        String phone = String.format("138%08d", (10000000 + seq) % 100000000);
        int age = 18 + random.nextInt(43); // 18-60
        BigDecimal money = new BigDecimal(String.format("%.2f", random.nextDouble() * 50000))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        long score = Math.abs(random.nextLong() % 10001);
        String email = account + "@example.com";
        int userStatus = (random.nextInt(100) < 5) ? 0 : 1;
        int logicDelete = (random.nextInt(200) < 1) ? 1 : 0;

        // ⏰ 关键：生成 java.util.Date（带时分秒）
        Date now = new Date();
        long maxPastMillis = 30L * 24 * 60 * 60 * 1000; // 30天毫秒数
        long randomPast = (long) (random.nextDouble() * maxPastMillis);
        Date createTime = new Date(now.getTime() - randomPast);
        Date updateTime = new Date(createTime.getTime() + random.nextInt(3600) * 1000L); // 最多1小时后更新

        user.setUserName(name);
        user.setAccount(account);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAge(age);
        user.setMoney(money);
        user.setScore(score);
        user.setSex(sex);
        user.setEmail(email);
        user.setUserStatus(userStatus);
        user.setCreateTime(createTime);
        user.setUpdateTime(updateTime);
        user.setLogicDelete(logicDelete);

        return user;
    }

    private static int getRandomWeightedRole() {
        int r = random.nextInt(100);
        if (r < 70) return 1;
        else if (r < 90) return 2;
        else return 3;
    }

    private static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
