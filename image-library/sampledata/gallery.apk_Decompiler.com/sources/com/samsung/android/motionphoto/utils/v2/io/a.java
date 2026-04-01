package com.samsung.android.motionphoto.utils.v2.io;

import java.io.FileInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Ae.a {
    public final /* synthetic */ int d;
    public final /* synthetic */ FileInputStream e;

    public /* synthetic */ a(FileInputStream fileInputStream, int i2) {
        this.d = i2;
        this.e = fileInputStream;
    }

    public final Object invoke() {
        int f;
        int i2 = this.d;
        FileInputStream fileInputStream = this.e;
        switch (i2) {
            case 0:
                f = HEIFMetaReader.parseILOCBox$lambda$7(fileInputStream);
                break;
            case 1:
                f = HEIFMetaReader.parseILOCBox$lambda$8(fileInputStream);
                break;
            case 2:
                f = HEIFMetaReader.parsePITMBox$lambda$4(fileInputStream);
                break;
            case 3:
                f = HEIFMetaReader.parsePITMBox$lambda$5(fileInputStream);
                break;
            case 4:
                f = HEIFMetaReader.parseIREFBox$lambda$1(fileInputStream);
                break;
            default:
                f = HEIFMetaReader.parseIREFBox$lambda$2(fileInputStream);
                break;
        }
        return Integer.valueOf(f);
    }
}
