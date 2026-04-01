package com.samsung.android.gallery.support.library.sef;

import A8.C0545b;
import N2.j;
import Pa.a;
import android.util.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SefParser {
    protected boolean mAltered;
    protected final String mFilePath;
    protected HashMap<String, SefData> mMap = new HashMap<>();
    protected HashMap<Integer, SefData> mMap2 = new HashMap<>();
    protected long mSefFileOffset;
    protected TailData mTailData;

    public SefParser(String str) {
        this.mFilePath = str;
    }

    private void addSefData(SefData sefData) {
        this.mMap.put(sefData.name, sefData);
        this.mMap2.put(Integer.valueOf(sefData.drdh.dataType), sefData);
    }

    public static boolean isMajorType(int i2) {
        if (i2 < 2048 || i2 % 16 != 0) {
            return false;
        }
        return true;
    }

    public static boolean isVisibleChar(char c5) {
        if (c5 < ' ' || c5 > '~') {
            return false;
        }
        return true;
    }

    private static SefData iterateSefData(String str, BiFunction<RandomAccessFile, SefData, Boolean> biFunction) {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(str, "r");
            TailData unpack = new TailData().load(randomAccessFile).unpack();
            if (unpack.count > 0) {
                long j2 = (long) unpack.packetSize;
                long length = randomAccessFile.length();
                Iterator<Sdr> it = unpack.list.iterator();
                while (it.hasNext()) {
                    Sdr next = it.next();
                    SefData load = new SefData(length - (((long) next.drsp) + j2), next.drsl).load(randomAccessFile);
                    if (biFunction.apply(randomAccessFile, load).booleanValue()) {
                        randomAccessFile.close();
                        return load;
                    }
                }
            }
            randomAccessFile.close();
            return null;
        } catch (IOException e) {
            Log.e("SefParser", "iterateSefData failed e=" + e.getMessage());
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ SefData lambda$addData$1(String str, int i2, String str2) {
        return new SefData(str, i2, (byte[]) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$apply$3(SefData sefData) {
        if (sefData.packetSize == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$apply$5(SefData sefData) {
        if (sefData.packetSize == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$toReadableString$7(Function function, StringBuilder sb2, String str, SefData sefData) {
        int i2;
        String str2;
        Drdh drdh = sefData.drdh;
        if (drdh != null) {
            i2 = drdh.dataType;
        } else {
            i2 = 0;
        }
        if (i2 == 2977) {
            str2 = new String(sefData.data);
        } else {
            str2 = substring(new String(sefData.data), 30);
        }
        if (str2.isEmpty() || !isVisibleChar(str2.charAt(0))) {
            str2 = C0212a.p(new StringBuilder("Hex{"), (String) function.apply(sefData.data), "}");
        }
        sb2.append("   ");
        sb2.append(sefData.name);
        sb2.append("=[");
        sb2.append(i2);
        sb2.append(NumericEnum.SEP);
        sb2.append(sefData.data.length);
        sb2.append(NumericEnum.SEP);
        sb2.append(str2);
        sb2.append("]\n");
    }

    public static SefParser of(String str) {
        SefParser sefParser = new SefParser(str);
        return (str == null || str.length() <= 0) ? sefParser : sefParser.unpack();
    }

    public boolean addData(String str, int i2, byte[] bArr) {
        SefData sefData = this.mMap.get(str);
        if (sefData != null && sefData.drdh.dataType == i2 && Arrays.equals(sefData.data, bArr)) {
            return false;
        }
        SefData computeIfAbsent = this.mMap.computeIfAbsent(str, new a(str, i2, 0));
        computeIfAbsent.data = bArr;
        this.mMap2.put(Integer.valueOf(computeIfAbsent.drdh.dataType), computeIfAbsent);
        this.mAltered = true;
        return true;
    }

    public boolean apply() {
        RandomAccessFile randomAccessFile;
        if (!this.mAltered) {
            Log.d("SefParser", "apply skip. nothing changed");
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            randomAccessFile = new RandomAccessFile(this.mFilePath, "rw");
            long length = randomAccessFile.length();
            randomAccessFile.seek(this.mSefFileOffset);
            apply((DataOutput) randomAccessFile);
            long filePointer = randomAccessFile.getFilePointer();
            if (length > filePointer) {
                randomAccessFile.getChannel().truncate(filePointer);
            }
            Log.d("SefParser", "apply {" + length + ',' + randomAccessFile.length() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            randomAccessFile.close();
            return true;
        } catch (Exception e) {
            j.D(e, new StringBuilder("apply failed. e="), "SefParser");
            return true;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean available() {
        if (this.mMap.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean deleteAllData() {
        if (this.mMap.size() <= 0) {
            return false;
        }
        this.mMap.clear();
        this.mMap2.clear();
        this.mAltered = true;
        return true;
    }

    public boolean deleteData(String str) {
        SefData remove = this.mMap.remove(str);
        if (remove == null) {
            return false;
        }
        this.mMap2.remove(Integer.valueOf(remove.drdh.dataType));
        this.mAltered = true;
        return true;
    }

    public byte[] getData(String str) {
        SefData sefData = this.mMap.get(str);
        if (sefData != null) {
            return sefData.data;
        }
        return null;
    }

    public long[] getDataPosition(String str) {
        SefData sefData = this.mMap.get(str);
        if (sefData != null) {
            return sefData.getDataPosition();
        }
        return null;
    }

    public int getDataType(String str) {
        SefData sefData = this.mMap.get(str);
        if (sefData != null) {
            return sefData.drdh.dataType;
        }
        return -1;
    }

    public int[] getDataTypeArray() {
        if (this.mMap.size() == 0) {
            return new int[0];
        }
        return this.mMap.values().stream().mapToInt(new g(0)).toArray();
    }

    public String[] getKeyNameArray() {
        if (this.mMap.size() == 0) {
            return new String[0];
        }
        return (String[]) this.mMap.keySet().toArray(new String[0]);
    }

    public byte[] getMediaData() {
        FileInputStream fileInputStream;
        if (this.mSefFileOffset <= 0) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(this.mFilePath);
            byte[] bArr = new byte[((int) this.mSefFileOffset)];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (IOException e) {
            Log.e("SefParser", "getMediaData failed", e);
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String getPath() {
        return this.mFilePath;
    }

    public boolean hasData(String str) {
        return this.mMap.containsKey(str);
    }

    public String substring(String str, int i2) {
        if (str != null) {
            return str.substring(0, Math.min(i2, str.length()));
        }
        return null;
    }

    public String toReadableString(Function<byte[], String> function) {
        String str;
        String str2;
        StringBuilder sb2 = new StringBuilder();
        this.mMap.forEach(new e(this, function, sb2));
        if (sb2.length() > 0) {
            str = sb2.substring(0, sb2.length() - 1);
        } else {
            str = null;
        }
        StringBuilder sb3 = new StringBuilder("SefData#");
        sb3.append(this.mMap.size());
        sb3.append(" {");
        if (str != null) {
            str2 = C0212a.m("\n", str, "\n");
        } else {
            str2 = "null";
        }
        return C0212a.p(sb3, str2, "}");
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        Iterator<Sdr> it = this.mTailData.list.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().toString());
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        }
        if (sb2.length() > 0) {
            str = sb2.substring(0, sb2.length() - 1);
        } else {
            str = "";
        }
        return "SefParser{" + this.mTailData.version + ',' + this.mMap.size() + ',' + this.mSefFileOffset + "} " + str + "";
    }

    public SefParser unpack() {
        RandomAccessFile randomAccessFile;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            randomAccessFile = new RandomAccessFile(this.mFilePath, "r");
            long length = randomAccessFile.length();
            this.mSefFileOffset = length;
            TailData unpack = new TailData().load(randomAccessFile).unpack();
            this.mTailData = unpack;
            if (unpack.count == 0) {
                randomAccessFile.close();
                return this;
            }
            long j2 = (long) unpack.packetSize;
            Iterator<Sdr> it = unpack.list.iterator();
            while (it.hasNext()) {
                Sdr next = it.next();
                int i2 = next.drsl;
                byte[] bArr = new byte[i2];
                long j3 = length - (((long) next.drsp) + j2);
                randomAccessFile.seek(j3);
                if (randomAccessFile.read(bArr) != i2) {
                    Log.e("SefParser", "read failed");
                }
                addSefData(new SefData(j3, bArr));
                if (this.mSefFileOffset > j3) {
                    this.mSefFileOffset = j3;
                }
            }
            randomAccessFile.close();
            Log.d("SefParser", this + " +" + (System.currentTimeMillis() - currentTimeMillis));
            return this;
        } catch (Error | Exception e) {
            Log.e("SefParser", "parse failed", e);
            if (this.mTailData == null) {
                this.mTailData = new TailData();
            }
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static boolean hasData(String str, String str2) {
        return iterateSefData(str, new f(str2, 1)) != null;
    }

    public static long[] getDataPosition(String str, String str2) {
        SefData iterateSefData = iterateSefData(str, new f(str2, 0));
        if (iterateSefData != null) {
            return iterateSefData.getDataPosition();
        }
        return null;
    }

    public static SefParser of(File file) {
        return of(file != null ? file.getPath() : "");
    }

    public static int[] getDataTypeArray(String str) {
        ArrayList arrayList = new ArrayList();
        iterateSefData(str, new d(arrayList, 1));
        return arrayList.stream().mapToInt(new C0545b(25)).toArray();
    }

    public static String[] getKeyNameArray(String str) {
        ArrayList arrayList = new ArrayList();
        iterateSefData(str, new d(arrayList, 0));
        return (String[]) arrayList.toArray(new String[0]);
    }

    public void apply(DataOutput dataOutput) {
        ArrayList arrayList = new ArrayList();
        this.mMap.forEach(new c(arrayList, 1));
        arrayList.removeIf(new b(2));
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                dataOutput.write(((SefData) it.next()).packet);
            }
            dataOutput.write(this.mTailData.pack(arrayList).data);
        }
    }

    public void apply(OutputStream outputStream) {
        ArrayList arrayList = new ArrayList();
        this.mMap.forEach(new c(arrayList, 0));
        arrayList.removeIf(new b(1));
        if (arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                outputStream.write(((SefData) it.next()).packet);
            }
            outputStream.write(this.mTailData.pack(arrayList).data);
        }
    }
}
