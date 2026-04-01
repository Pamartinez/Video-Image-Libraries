package com.samsung.android.gallery.database.dal.local.table;

import android.util.Log;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import i.C0212a;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AlbumEntryJsonUtil {
    private static String convertInputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader;
        StringBuilder sb2 = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb2.append(readLine);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return sb2.toString();
        throw th;
    }

    public static ArrayList<AlbumEntry> parseJsonForAlbumDb(String str, String str2) {
        Throwable th;
        boolean z;
        String str3;
        String str4;
        String str5;
        String str6;
        int i2;
        int i7;
        long j2;
        AlbumEntry albumEntry;
        int i8;
        int i10;
        Log.d("AlbumEntryJsonUtil", "parseJsonForAlbumDb");
        FileInputStream fileInputStream = new FileInputStream(C0212a.p(C0212a.s(str), File.separator, str2));
        try {
            String convertInputStreamToString = convertInputStreamToString(fileInputStream);
            fileInputStream.close();
            ArrayList<AlbumEntry> arrayList = new ArrayList<>();
            JSONArray jSONArray = new JSONArray(convertInputStreamToString);
            for (int i11 = 0; i11 < jSONArray.length(); i11++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i11);
                boolean has = jSONObject.has("album_order");
                boolean has2 = jSONObject.has("folder_name");
                if (jSONObject.has("__absPath") || jSONObject.has("default_cover_path") || jSONObject.has("cover_path")) {
                    z = false;
                } else {
                    z = true;
                }
                String str7 = null;
                if (jSONObject.has("__absPath")) {
                    str3 = jSONObject.getString("__absPath");
                } else {
                    str3 = null;
                }
                if (jSONObject.has("cover_path")) {
                    str4 = jSONObject.getString("cover_path");
                } else {
                    str4 = null;
                }
                if (jSONObject.has("default_cover_path")) {
                    str5 = jSONObject.getString("default_cover_path");
                } else {
                    str5 = null;
                }
                if (jSONObject.has("cover_rect")) {
                    str6 = jSONObject.getString("cover_rect");
                } else {
                    str6 = null;
                }
                if (has2) {
                    str7 = jSONObject.getString("folder_name");
                }
                String str8 = str7;
                if (jSONObject.has("__albumType")) {
                    i2 = jSONObject.getInt("__albumType");
                } else {
                    i2 = 0;
                }
                if (jSONObject.has("__albumLevel")) {
                    i7 = jSONObject.getInt("__albumLevel");
                } else {
                    i7 = 0;
                }
                long j3 = 0;
                if (jSONObject.has("essential_album_order")) {
                    j2 = jSONObject.getLong("essential_album_order");
                } else {
                    j2 = 0;
                }
                if (!z || AlbumType.isMergedAlbum(i2)) {
                    int i12 = i2;
                    int i13 = i7;
                    long j8 = j2;
                    int i14 = jSONObject.getInt("__bucketID");
                    String string = jSONObject.getString("__Title");
                    if (has2) {
                        i8 = jSONObject.getInt("folder_id");
                    } else {
                        i8 = 0;
                    }
                    if (has) {
                        j3 = jSONObject.getLong("album_order");
                    }
                    albumEntry = new AlbumEntry(i14, str3, str4, str5, str6, string, i8, str8, j3, has, i12, i13, j8);
                } else {
                    int i15 = i2;
                    int i16 = i7;
                    int i17 = jSONObject.getInt("__bucketID");
                    long j10 = j2;
                    String string2 = jSONObject.getString("__Title");
                    long j11 = jSONObject.getLong("album_order");
                    if (has2) {
                        i10 = jSONObject.getInt("folder_id");
                    } else {
                        i10 = 0;
                    }
                    albumEntry = new AlbumEntry(i17, string2, j11, i10, str8, i15, i16, j10);
                }
                arrayList.add(albumEntry);
            }
            return arrayList;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static boolean saveJsonForAlbumDb(List<AlbumEntry> list, String str, String str2) {
        Log.e("AlbumEntryJsonUtil", "saveJsonForAlbumDb");
        JSONArray jSONArray = new JSONArray();
        try {
            for (AlbumEntry next : list) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__bucketID", next.getBucketId());
                jSONObject.put("__Title", next.getTitle());
                jSONObject.put("album_order", next.getAlbumOrder());
                if (next.hasFolderInfo()) {
                    jSONObject.put("folder_id", next.getFolderId());
                    jSONObject.put("folder_name", next.getFolderName());
                }
                if (!next.isFolder() || next.isMergedAlbum()) {
                    jSONObject.put("__absPath", next.getAlbumPath());
                    jSONObject.put("cover_path", next.getCoverPath());
                    jSONObject.put("default_cover_path", next.getDefaultCoverPath());
                    jSONObject.put("cover_rect", next.getCoverRect());
                }
                if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    jSONObject.put("__albumType", next.getAlbumType());
                    jSONObject.put("__albumLevel", next.getAlbumLevel());
                    jSONObject.put("essential_album_order", next.getAlbumEssOrder());
                }
                jSONArray.put(jSONObject);
            }
            FileUtils.makeDirectoryIfAbsent(str);
            byte[] bytes = jSONArray.toString().getBytes();
            return saveStreamToFile(bytes, str + File.separator + str2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean saveStreamToFile(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                fileOutputStream = new FileOutputStream(new SecureFile(str));
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = byteArrayInputStream.read(bArr2, 0, 1024);
                    if (read != -1) {
                        fileOutputStream.write(bArr2, 0, read);
                    } else {
                        fileOutputStream.close();
                        byteArrayInputStream.close();
                        return true;
                    }
                }
            } catch (Throwable th) {
                byteArrayInputStream.close();
                throw th;
            }
            throw th;
        } catch (IOException e) {
            Log.e("AlbumEntryJsonUtil", "saveStream failed e=" + e.getMessage());
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }
}
