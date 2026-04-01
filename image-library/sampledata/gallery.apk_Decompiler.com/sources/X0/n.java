package x0;

import C0.h;
import H0.t;
import I0.c;
import I0.d;
import J0.b;
import J0.g;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import com.samsung.android.ocr.MOCRLang;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import og.i;
import t8.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class n {

    /* renamed from: a  reason: collision with root package name */
    public static final HashMap f2068a = new HashMap();
    public static final HashSet b = new HashSet();

    /* renamed from: c  reason: collision with root package name */
    public static final byte[] f2069c = {80, 75, 3, 4};
    public static final byte[] d = {31, -117, 8};

    public static C0322D a(String str, Callable callable, e eVar) {
        C0332j jVar;
        C0322D d2 = null;
        if (str == null) {
            jVar = null;
        } else {
            jVar = h.b.a(str);
        }
        if (jVar != null) {
            d2 = new C0322D(jVar);
        }
        HashMap hashMap = f2068a;
        if (str != null && hashMap.containsKey(str)) {
            d2 = (C0322D) hashMap.get(str);
        }
        if (d2 != null) {
            if (eVar != null) {
                eVar.run();
            }
            return d2;
        }
        C0322D d3 = new C0322D(callable, false);
        if (str != null) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            d3.b(new l(0, str, atomicBoolean));
            d3.a(new l(1, str, atomicBoolean));
            if (!atomicBoolean.get()) {
                hashMap.put(str, d3);
                if (hashMap.size() == 1) {
                    i();
                }
            }
        }
        return d3;
    }

    public static C0320B b(Context context, String str, String str2) {
        C0332j jVar;
        if (str2 == null) {
            jVar = null;
        } else {
            jVar = h.b.a(str2);
        }
        if (jVar != null) {
            return new C0320B(jVar);
        }
        try {
            i iVar = new i(og.e.a(context.getAssets().open(str)));
            if (h(iVar, f2069c).booleanValue()) {
                return f(context, new ZipInputStream(new og.h(iVar)), str2);
            }
            if (h(iVar, d).booleanValue()) {
                return c(str2, new GZIPInputStream(new og.h(iVar)));
            }
            return c(str2, new og.h(iVar));
        } catch (IOException e) {
            return new C0320B((Throwable) e);
        }
    }

    public static C0320B c(String str, InputStream inputStream) {
        i iVar = new i(og.e.a(inputStream));
        String[] strArr = c.f341h;
        return d(new d(iVar), str, true);
    }

    public static C0320B d(d dVar, String str, boolean z) {
        C0332j jVar;
        if (str == null) {
            jVar = null;
        } else {
            try {
                jVar = h.b.a(str);
            } catch (Exception e) {
                C0320B b5 = new C0320B((Throwable) e);
                if (z) {
                    g.b(dVar);
                }
                return b5;
            } catch (Throwable th) {
                if (z) {
                    g.b(dVar);
                }
                throw th;
            }
        }
        if (jVar != null) {
            C0320B b8 = new C0320B(jVar);
            if (z) {
                g.b(dVar);
            }
            return b8;
        }
        C0332j a7 = t.a(dVar);
        if (str != null) {
            h.b.f98a.put(str, a7);
        }
        C0320B b10 = new C0320B(a7);
        if (z) {
            g.b(dVar);
        }
        return b10;
    }

    public static C0320B e(Context context, int i2, String str) {
        C0332j jVar;
        if (str == null) {
            jVar = null;
        } else {
            jVar = h.b.a(str);
        }
        if (jVar != null) {
            return new C0320B(jVar);
        }
        try {
            i iVar = new i(og.e.a(context.getResources().openRawResource(i2)));
            if (h(iVar, f2069c).booleanValue()) {
                return f(context, new ZipInputStream(new og.h(iVar)), str);
            }
            if (!h(iVar, d).booleanValue()) {
                return c(str, new og.h(iVar));
            }
            try {
                return c(str, new GZIPInputStream(new og.h(iVar)));
            } catch (IOException e) {
                return new C0320B((Throwable) e);
            }
        } catch (Resources.NotFoundException e7) {
            return new C0320B((Throwable) e7);
        }
    }

    public static C0320B f(Context context, ZipInputStream zipInputStream, String str) {
        try {
            return g(context, zipInputStream, str);
        } finally {
            g.b(zipInputStream);
        }
    }

    public static C0320B g(Context context, ZipInputStream zipInputStream, String str) {
        C0332j jVar;
        y yVar;
        FileOutputStream fileOutputStream;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        if (str == null) {
            jVar = null;
        } else {
            try {
                jVar = h.b.a(str);
            } catch (IOException e) {
                return new C0320B((Throwable) e);
            }
        }
        if (jVar != null) {
            return new C0320B(jVar);
        }
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        C0332j jVar2 = null;
        while (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.contains("__MACOSX")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().equalsIgnoreCase("manifest.json")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().contains(".json")) {
                i iVar = new i(og.e.a(zipInputStream));
                String[] strArr = c.f341h;
                jVar2 = d(new d(iVar), (String) null, false).f2042a;
            } else {
                if (!name.contains(".png")) {
                    if (!name.contains(".webp") && !name.contains(".jpg")) {
                        if (!name.contains(".jpeg")) {
                            if (!name.contains(".ttf")) {
                                if (!name.contains(".otf")) {
                                    zipInputStream.closeEntry();
                                }
                            }
                            String[] split = name.split("/");
                            String str2 = split[split.length - 1];
                            String str3 = str2.split("\\.")[0];
                            File file = new File(context.getCacheDir(), str2);
                            new FileOutputStream(file);
                            try {
                                fileOutputStream = new FileOutputStream(file);
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = zipInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (Throwable th) {
                                b.c("Unable to save font " + str3 + " to the temporary file: " + str2 + ". ", th);
                            }
                            Typeface createFromFile = Typeface.createFromFile(file);
                            if (!file.delete()) {
                                b.b("Failed to delete temp font file " + file.getAbsolutePath() + ".");
                            }
                            hashMap2.put(str3, createFromFile);
                        }
                    }
                }
                String[] split2 = name.split("/");
                hashMap.put(split2[split2.length - 1], BitmapFactory.decodeStream(zipInputStream));
            }
            nextEntry = zipInputStream.getNextEntry();
        }
        if (jVar2 == null) {
            return new C0320B((Throwable) new IllegalArgumentException("Unable to parse composition"));
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String str4 = (String) entry.getKey();
            Iterator it = ((HashMap) jVar2.d()).values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    yVar = null;
                    break;
                }
                yVar = (y) it.next();
                if (yVar.d.equals(str4)) {
                    break;
                }
            }
            if (yVar != null) {
                yVar.f = g.e((Bitmap) entry.getValue(), yVar.f2099a, yVar.b);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            boolean z = false;
            for (C0.d dVar : jVar2.f.values()) {
                if (dVar.f92a.equals(entry2.getKey())) {
                    dVar.d = (Typeface) entry2.getValue();
                    z = true;
                }
            }
            if (!z) {
                b.b("Parsed font for " + ((String) entry2.getKey()) + " however it was not found in the animation.");
            }
        }
        if (hashMap.isEmpty()) {
            for (Map.Entry value : ((HashMap) jVar2.d()).entrySet()) {
                y yVar2 = (y) value.getValue();
                if (yVar2 == null) {
                    return null;
                }
                String str5 = yVar2.d;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = true;
                options.inDensity = MOCRLang.GURMUKHI;
                if (str5.startsWith("data:") && str5.indexOf("base64,") > 0) {
                    try {
                        byte[] decode = Base64.decode(str5.substring(str5.indexOf(44) + 1), 0);
                        yVar2.f = g.e(BitmapFactory.decodeByteArray(decode, 0, decode.length, options), yVar2.f2099a, yVar2.b);
                    } catch (IllegalArgumentException e7) {
                        b.c("data URL did not have correct base64 format.", e7);
                        return null;
                    }
                }
            }
        }
        if (str != null) {
            h.b.f98a.put(str, jVar2);
        }
        return new C0320B(jVar2);
        throw th;
    }

    public static Boolean h(i iVar, byte[] bArr) {
        try {
            i iVar2 = new i(new og.g(iVar));
            int length = bArr.length;
            int i2 = 0;
            while (i2 < length) {
                byte b5 = bArr[i2];
                if (!iVar2.k(1)) {
                    throw new EOFException();
                } else if (iVar2.d.c() != b5) {
                    return Boolean.FALSE;
                } else {
                    i2++;
                }
            }
            iVar2.close();
            return Boolean.TRUE;
        } catch (NoSuchMethodError unused) {
            return Boolean.FALSE;
        } catch (Exception unused2) {
            b.f353a.getClass();
            C0323a aVar = C0326d.f2049a;
            return Boolean.FALSE;
        }
    }

    public static void i() {
        ArrayList arrayList = new ArrayList(b);
        if (arrayList.size() > 0) {
            arrayList.get(0).getClass();
            throw new ClassCastException();
        }
    }

    public static String j(Context context, int i2) {
        String str;
        StringBuilder sb2 = new StringBuilder("rawRes");
        if ((context.getResources().getConfiguration().uiMode & 48) == 32) {
            str = "_night_";
        } else {
            str = "_day_";
        }
        sb2.append(str);
        sb2.append(i2);
        return sb2.toString();
    }
}
