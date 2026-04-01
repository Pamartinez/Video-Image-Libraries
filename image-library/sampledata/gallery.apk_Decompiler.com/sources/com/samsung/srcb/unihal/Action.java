package com.samsung.srcb.unihal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Action {
    public static final int BASKETBALL = 13;
    public static final int BIRTHDAY = 11;
    public static final int COMPLEX = 30;
    public static final int DAILY = 31;
    public static final int DANCING = 1;
    public static final int EATING = 0;
    public static final Map<Integer, String> EMap;
    public static final int FACE_ANGRY = 27;
    public static final int FACE_DISGUST = 26;
    public static final int FACE_FEAR = 25;
    public static final int FACE_HAPPY = 23;
    public static final int FACE_SAD = 22;
    public static final int FACE_SURPRISE = 24;
    public static final int FACE_ZOOM_IN = 20;
    public static final int FACE_ZOOM_OUT = 21;
    public static final int FIREWORK = 10;
    public static final int FOOD = 32;
    public static final int FOOD_OUT_PLOT = 15;
    public static final int FOOTBALL = 12;
    public static final int HAND_GESTURE = 5;
    public static final int HOLDING = 14;
    public static final int JUMPING = 2;
    public static final int KISSING = 16;
    public static final int MOVINET_HIGHLIGHT = 28;
    public static final int PETS = 35;
    public static final int PLAY_WITH_PET = 4;
    public static final int RINGS_EXCHANGE = 9;
    public static final int RUNNING = 3;
    public static final int SPORTS = 33;
    public static final int TRAVELING = 34;
    public static final int WALKING = 8;

    static {
        HashMap hashMap = new HashMap();
        EMap = hashMap;
        hashMap.put(0, "eating");
        hashMap.put(1, "dancing");
        hashMap.put(2, "jumping");
        hashMap.put(3, "running");
        hashMap.put(4, "play with pet");
        hashMap.put(5, "hand gesture");
        hashMap.put(9, "rings exchange");
        hashMap.put(10, "firework");
        hashMap.put(8, "walking");
        hashMap.put(11, "birthday congratulations");
        hashMap.put(12, "football");
        hashMap.put(13, "basketball");
        hashMap.put(14, "holding");
        hashMap.put(15, "food out plot");
        hashMap.put(16, "kissing");
        hashMap.put(20, "face zoom in");
        hashMap.put(21, "face zoom out");
        hashMap.put(22, "sad");
        hashMap.put(23, "laugh");
        hashMap.put(24, "surprise");
        hashMap.put(25, "fear");
        hashMap.put(26, "disgust");
        hashMap.put(27, "angry");
        hashMap.put(28, "movinet_highlight");
        hashMap.put(31, "daily");
        hashMap.put(32, "food");
        hashMap.put(33, "sports");
        hashMap.put(34, "traveling");
        hashMap.put(35, "pets");
    }

    public static int getTypeIdByName(String str) {
        for (Integer next : EMap.keySet()) {
            int intValue = next.intValue();
            if (EMap.get(next).equals(str)) {
                return intValue;
            }
        }
        return -1;
    }

    public static String getTypeNameById(int i2) {
        return EMap.get(Integer.valueOf(i2));
    }
}
