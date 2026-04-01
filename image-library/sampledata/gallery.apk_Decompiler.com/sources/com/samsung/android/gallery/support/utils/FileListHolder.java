package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileListHolder {
    private static final String TAG = "FileListHolder";
    final String filePath;

    public FileListHolder(String str) {
        this.filePath = str;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$truncate$0(String str) {
        return str.getBytes().length + 1;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$truncate$1(String str) {
        return str.getBytes().length + 1;
    }

    public void appendFile(List<String> list) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(this.filePath, true);
            for (String str : list) {
                fileWriter.write(str + "\n");
            }
            fileWriter.close();
            return;
        } catch (IOException e) {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder("appendFile failed. ");
            sb2.append(this);
            sb2.append(" e=");
            j.r(e, sb2, str2);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void deleteFile() {
        File file = new File(this.filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    public List<String> readFile() {
        ArrayList arrayList = new ArrayList();
        if (new File(this.filePath).exists()) {
            try {
                return Files.readAllLines(Paths.get(this.filePath, new String[0]));
            } catch (IOException e) {
                String str = TAG;
                StringBuilder sb2 = new StringBuilder("readFile failed. ");
                sb2.append(this);
                sb2.append(" e=");
                j.r(e, sb2, str);
            }
        }
        return arrayList;
    }

    public String toString() {
        Object obj;
        File file = new File(this.filePath);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(TAG);
        sb2.append("[");
        if (file.exists()) {
            obj = Long.valueOf(file.length());
        } else {
            obj = "-1";
        }
        sb2.append(obj);
        sb2.append("]");
        return sb2.toString();
    }

    public List<String> trimLastLines(int i2) {
        List<String> list;
        long currentTimeMillis = System.currentTimeMillis();
        List<String> readFile = readFile();
        int size = readFile.size();
        if (size == 0) {
            deleteFile();
            String str = TAG;
            a.A(new Object[]{Integer.valueOf(size), 0, Integer.valueOf(i2), Long.valueOf(currentTimeMillis)}, new StringBuilder("trimLastLines"), str);
            return readFile;
        }
        if (size > i2) {
            list = readFile.subList(size - i2, size);
        } else {
            list = readFile;
        }
        truncate(readFile, list);
        String str2 = TAG;
        a.A(new Object[]{Integer.valueOf(size), Integer.valueOf(size - i2), Integer.valueOf(list.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("trimLastLines"), str2);
        return list;
    }

    public void truncate(List<String> list, List<String> list2) {
        RandomAccessFile randomAccessFile;
        long currentTimeMillis = System.currentTimeMillis();
        if (list.size() > list2.size()) {
            int sum = list.stream().mapToInt(new C0672j(0)).sum();
            int length = (int) new File(this.filePath).length();
            if (sum != length) {
                Log.d(TAG, "truncate wrong file size", Integer.valueOf(sum), Integer.valueOf(length));
            }
            int sum2 = sum - list2.stream().mapToInt(new C0672j(1)).sum();
            try {
                randomAccessFile = new RandomAccessFile(this.filePath, "rw");
                long j2 = (long) sum2;
                randomAccessFile.seek(j2);
                String readLine = randomAccessFile.readLine();
                if (readLine == null || !readLine.equals(list2.get(0))) {
                    throw new IOException("truncate position mismatch total=" + sum + ",pos=" + sum2);
                }
                randomAccessFile.getChannel().truncate(j2).close();
                randomAccessFile.close();
            } catch (IOException | NullPointerException e) {
                String str = TAG;
                Log.e(str, "truncate failed. e=" + e.getMessage());
                List<String> subList = list.subList(0, list.size() - list2.size());
                deleteFile();
                if (subList.size() > 0) {
                    appendFile(subList);
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            deleteFile();
        }
        String str2 = TAG;
        a.A(new Object[]{Integer.valueOf(list.size()), Integer.valueOf(list2.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("truncate"), str2);
        return;
        throw th;
    }

    public FileListHolder writeFile(List<String> list) {
        long currentTimeMillis = System.currentTimeMillis();
        deleteFile();
        appendFile(list);
        String str = TAG;
        StringBuilder sb2 = new StringBuilder("writeFile ");
        sb2.append(this);
        sb2.append(" +");
        a.x(sb2, currentTimeMillis, str);
        return this;
    }
}
