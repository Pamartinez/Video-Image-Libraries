package com.samsung.o3dp.jpeg3dcontainer.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Marker {
    TEM(65281),
    SOF0(65472),
    SOF1(65473),
    SOF2(65474),
    SOF3(65475),
    DHT(65476),
    SOF5(65477),
    SOF6(65478),
    SOF7(65479),
    JPG(65480),
    SOF9(65481),
    SOF10(65482),
    SOF11(65483),
    DAC(65484),
    SOF13(65485),
    SOF14(65486),
    SOF15(65487),
    RST0(65488),
    RST1(65489),
    RST2(65490),
    RST3(65491),
    RST4(65492),
    RST5(65493),
    RST6(65494),
    RST7(65495),
    SOI(65496),
    EOI(65497),
    SOS(65498),
    DQT(65499),
    DNL(65500),
    DRI(65501),
    DHP(65502),
    EXP(65503),
    APP0(65504),
    APP1(65505),
    APP2(65506),
    APP3(65507),
    APP4(65508),
    APP5(65509),
    APP6(65510),
    APP7(65511),
    APP8(65512),
    APP9(65513),
    APP10(65514),
    APP11(65515),
    APP12(65516),
    APP13(65517),
    APP14(65518),
    APP15(65519),
    JPG0(65520),
    JPG1(65521),
    JPG2(65522),
    JPG3(65523),
    JPG4(65524),
    JPG5(65525),
    JPG6(65526),
    JPG7(65527),
    JPG8(65528),
    JPG9(65529),
    JPG10(65530),
    JPG11(65531),
    JPG12(65532),
    JPG13(65533),
    COM(65534),
    UNKNOWN(65535);
    
    private static final Map<Integer, Marker> MARKER_MAP = null;
    private final int bytesValue;

    static {
        int i2;
        MARKER_MAP = new HashMap();
        for (Marker marker : values()) {
            MARKER_MAP.put(Integer.valueOf(marker.getBytesValue()), marker);
        }
    }

    private Marker(int i2) {
        this.bytesValue = i2;
    }

    public static Marker fromValue(int i2) {
        Marker orDefault = MARKER_MAP.getOrDefault(Integer.valueOf(i2), UNKNOWN);
        Objects.requireNonNull(orDefault);
        return orDefault;
    }

    public static int getLength() {
        return 2;
    }

    public int getBytesValue() {
        return this.bytesValue;
    }

    public byte[] toBytes() {
        int i2 = this.bytesValue;
        return new byte[]{(byte) (i2 >> 8), (byte) i2};
    }
}
