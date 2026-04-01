package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SefData {
    private final HashMap<String, byte[]> mDataMap = new HashMap<>();
    private final HashMap<String, Integer> mDataTypeMap = new HashMap<>();
    private String mFilename;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$write$0(SefFileCompat sefFileCompat, File file, String str, byte[] bArr) {
        try {
            sefFileCompat.addData(file, str, bArr);
        } catch (Exception unused) {
            Log.e("SefData", "write failed{" + str + "}");
        }
    }

    public boolean contain(String str) {
        return this.mDataMap.containsKey(str);
    }

    public boolean exists() {
        return !this.mDataMap.isEmpty();
    }

    public SefData read(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        this.mFilename = str;
        File file = new File(str);
        try {
            SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
            String[] keyNameArray = sefFileCompat.getKeyNameArray(file);
            if (keyNameArray != null) {
                if (keyNameArray.length != 0) {
                    for (String str2 : keyNameArray) {
                        this.mDataMap.put(str2, sefFileCompat.getData(file, str2));
                        this.mDataTypeMap.put(str2, Integer.valueOf(sefFileCompat.getDataType(file, str2)));
                    }
                    Log.d("SefData", "read {" + this.mDataMap.size() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                    return this;
                }
            }
            return this;
        } catch (IOException e) {
            Log.e((CharSequence) "SefData", "read failed", (Throwable) e);
        }
    }

    public void write(String str) {
        if (exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            SecureFile secureFile = new SecureFile(str);
            this.mDataMap.forEach(new C0677o(1, SeApiCompat.getSefFileCompat(), secureFile));
            Log.d("SefData", "write {" + this.mDataMap.size() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        }
    }
}
