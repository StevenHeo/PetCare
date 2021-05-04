package com.jeongseok.petcare;

import java.util.Arrays;
import java.util.List;

public class HealthFinder {

    //{key, Result, tip}
    private static String[][] checkData = {
            {"[1]", "정상입니다. 안심하세요!", ""},
            {"[2]", "변비 또는 췌장기능에 이상이 있을 수 있어요.", "기름기가 없다면 변비이므로, 오랜만에 강아지와 산책을 갔다 온 후 아랫배를 가볍게 마사지해 주세요!"},
            {"[3]", "소화기능의 문제가 생긴 확률이 높습니다. 특히 소화기능에 궤양이나 종양이 생겼을 경우 검은 혈변을 보게 되므로 빠른 시간안에 병원에 내원하셔야합니다.", ""},
            {"[4]", "소화기관의 문제를 의심해야합니다. 특히 위장관 출혈,위장염 일 수 있습니다.혹은 항문이 찢어졌을 경우도 있습니다. 지독한 냄새와 함께 발견된다면 파보 바이러스일 가능성이 있습니다.",""},
            {"[5]", "풀/야채를 먹었거나, 쓸개즙으로 인한 소화 장애 현상입니다. 일시적이라면 걱정하지 않으셔도 됩니다.",""},
            {"[6]", "황달의 증상을 의심해야 합니다. 피부와 점막,소변까지 노랗게 된다면 황달일 확률이 높습니다. 아니라면 특정 음식에 대한 소화 불량입니다.","사료를 바꾸거나 식단을 체크해 주세요. 황달이 의심된다면, 병으로 인해 나타나는 증상이므로 검사가 필요합니다."},
            {"[7]", "강아지 몸안에 기생충 존재한다는 신호입니다.",""},
    };

    public static String[] checkHealthList(List<Integer> checkList) {
        for (String[] data : checkData) {
            String[] items = data[0].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
            int[] results = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                try {
                    results[i] = Integer.parseInt(items[i]);
                } catch (NumberFormatException nfe) {
                    return null;
                }
            }

            if (checkList.containsAll(Arrays.asList(results)))
                return data;
        }

        return null;
    }
}
