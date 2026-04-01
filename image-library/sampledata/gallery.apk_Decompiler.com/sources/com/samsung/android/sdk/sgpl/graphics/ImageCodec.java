package com.samsung.android.sdk.sgpl.graphics;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.graphics.CodecConfig;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageCodec {
    final boolean DEBUG;
    final boolean HEAP;
    final String TAG;
    final ImageCodecImpl[] codecChains;
    final CodecConfig.CustomCodec customCodec;
    final SharedFixedBufferPool sharedFixedBufferPool = new SharedFixedBufferPool(5242880);

    public ImageCodec(CodecConfig.Builder builder) {
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        ImageCodecImpl[] imageCodecImplArr = builder.imageCodecs;
        this.codecChains = imageCodecImplArr;
        this.HEAP = builder.inHeap;
        this.DEBUG = builder.inDebug;
        this.customCodec = builder.inCustomCodec;
        Log.i(simpleName, C0212a.p(new StringBuilder("codec ["), (String) Stream.of(imageCodecImplArr).map(new b(0)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "]"));
    }

    public Bitmap decodeByteArray(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
        long j2;
        if (this.DEBUG) {
            j2 = System.currentTimeMillis();
        } else {
            j2 = 0;
        }
        try {
            Bitmap decodeByteArrayInChain = decodeByteArrayInChain(bArr, i2, i7, options);
            if (this.DEBUG) {
                Log.v(this.TAG, C0086a.j(j2, Log.vars(Integer.valueOf(i2), Integer.valueOf(i7), decodeByteArrayInChain, options), " +", new StringBuilder("decodeByteArray")));
            }
            return decodeByteArrayInChain;
        } catch (Throwable th) {
            if (this.DEBUG) {
                Log.v(this.TAG, C0086a.j(j2, Log.vars(Integer.valueOf(i2), Integer.valueOf(i7), null, options), " +", new StringBuilder("decodeByteArray")));
            }
            throw th;
        }
    }

    public Bitmap decodeByteArrayInChain(byte[] bArr, int i2, int i7, BitmapFactory.Options options) {
        CodecConfig.CustomCodec customCodec2 = this.customCodec;
        if (customCodec2 != null && customCodec2.decodeByteArray(bArr, i2, i7, options)) {
            return options.inBitmap;
        }
        if (options.inBitmap == null) {
            BitmapToolkit.applyBitmap(options);
        }
        ImageCodecImpl[] imageCodecImplArr = this.codecChains;
        int length = imageCodecImplArr.length;
        int i8 = 0;
        while (i8 < length) {
            try {
                Bitmap decodeByteArray = imageCodecImplArr[i8].decodeByteArray(bArr, i2, i7, options);
                if (decodeByteArray != null) {
                    return decodeByteArray;
                }
                i8++;
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeByteArray failed. e=")));
                if (e instanceof AssertionError) {
                    throw e;
                }
            }
        }
        return null;
    }

    public Bitmap decodeDngThumbnail(String str, int i2) {
        ImageCodecImpl[] imageCodecImplArr = this.codecChains;
        int length = imageCodecImplArr.length;
        int i7 = 0;
        while (i7 < length) {
            try {
                Bitmap decodeDngThumbnail = imageCodecImplArr[i7].decodeDngThumbnail(str, i2);
                return (decodeDngThumbnail == null || Math.max(decodeDngThumbnail.getWidth(), decodeDngThumbnail.getHeight()) <= i2 * 2) ? decodeDngThumbnail : BitmapToolkit.resize(decodeDngThumbnail, i2);
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeDngThumbnail failed. e=")));
                i7++;
            }
        }
        return null;
    }

    public Bitmap decodeFile(String str, BitmapFactory.Options options) {
        long j2;
        String str2;
        StringBuilder sb2;
        String vars;
        if (this.DEBUG) {
            j2 = System.currentTimeMillis();
        } else {
            j2 = 0;
        }
        Bitmap bitmap = null;
        try {
            if (this.HEAP) {
                File file = new File(str);
                long length = file.length();
                if (length <= 0) {
                    if (this.DEBUG) {
                        str2 = this.TAG;
                        sb2 = new StringBuilder("decodeFile");
                        vars = Log.vars(str, null, options);
                    }
                    return bitmap;
                } else if (length <= 5242880) {
                    byte[] readFile = readFile(file, length);
                    if (readFile != null) {
                        bitmap = decodeByteArrayInChain(readFile, 0, (int) length, options);
                        if (!this.DEBUG) {
                            return bitmap;
                        }
                        str2 = this.TAG;
                        sb2 = new StringBuilder("decodeFile");
                        vars = Log.vars(str, bitmap, options);
                    }
                }
                Log.v(str2, C0086a.j(j2, vars, " +", sb2));
                return bitmap;
            }
            bitmap = decodeFileInChain(str, options);
            if (!this.DEBUG) {
                return bitmap;
            }
            str2 = this.TAG;
            sb2 = new StringBuilder("decodeFile");
            vars = Log.vars(str, bitmap, options);
            Log.v(str2, C0086a.j(j2, vars, " +", sb2));
            return bitmap;
        } catch (Throwable th) {
            if (this.DEBUG) {
                Log.v(this.TAG, C0086a.j(j2, Log.vars(str, null, options), " +", new StringBuilder("decodeFile")));
            }
            throw th;
        }
    }

    public Bitmap decodeFileInChain(String str, BitmapFactory.Options options) {
        CodecConfig.CustomCodec customCodec2 = this.customCodec;
        if (customCodec2 != null && customCodec2.decodeFile(str, options)) {
            return options.inBitmap;
        }
        if (options.inBitmap == null) {
            BitmapToolkit.applyBitmap(options);
        }
        ImageCodecImpl[] imageCodecImplArr = this.codecChains;
        int length = imageCodecImplArr.length;
        int i2 = 0;
        while (i2 < length) {
            try {
                Bitmap decodeFile = imageCodecImplArr[i2].decodeFile(str, options);
                if (decodeFile != null) {
                    return decodeFile;
                }
                i2++;
            } catch (Error | Exception e) {
                String str2 = this.TAG;
                Log.e(str2, "decodeFile failed" + Log.vars(new File(str), options), e);
                if (e instanceof AssertionError) {
                    throw e;
                }
            }
        }
        return null;
    }

    public Bitmap decodeHeifThumbnail(String str, int i2) {
        ImageCodecImpl[] imageCodecImplArr = this.codecChains;
        int length = imageCodecImplArr.length;
        int i7 = 0;
        while (i7 < length) {
            try {
                Bitmap decodeHeifThumbnail = imageCodecImplArr[i7].decodeHeifThumbnail(str, i2);
                if (decodeHeifThumbnail != null) {
                    return decodeHeifThumbnail;
                }
                i7++;
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeHeifThumbnail failed. e=")));
            }
        }
        return null;
    }

    public Bitmap decodeStream(InputStream inputStream, BitmapFactory.Options options) {
        long j2;
        if (this.DEBUG) {
            j2 = System.currentTimeMillis();
        } else {
            j2 = 0;
        }
        Bitmap bitmap = null;
        try {
            if (options.inBitmap == null) {
                BitmapToolkit.applyBitmap(options);
            }
            if (inputStream.markSupported()) {
                inputStream.mark(Integer.MAX_VALUE);
            }
            for (ImageCodecImpl decodeStream : this.codecChains) {
                bitmap = decodeStream.decodeStream(inputStream, options);
                if (bitmap != null) {
                    if (this.DEBUG) {
                        Log.v(this.TAG, C0086a.j(j2, Log.vars(bitmap, options), " +", new StringBuilder("decodeStream")));
                    }
                    return bitmap;
                }
                if (inputStream.markSupported()) {
                    try {
                        inputStream.reset();
                    } catch (IOException unused) {
                    }
                }
            }
            if (this.DEBUG) {
                Log.v(this.TAG, C0086a.j(j2, Log.vars(bitmap, options), " +", new StringBuilder("decodeStream")));
            }
            return bitmap;
        } catch (Error | Exception e) {
            Log.e(this.TAG, "decodeStream failed. e=" + e.getMessage());
            if (e instanceof AssertionError) {
                throw e;
            }
        } catch (Throwable th) {
            if (this.DEBUG) {
                Log.v(this.TAG, C0086a.j(j2, Log.vars(bitmap, options), " +", new StringBuilder("decodeStream")));
            }
            throw th;
        }
    }

    public Bitmap decodeThumbnail(String str, int i2) {
        try {
            return this.codecChains[0].decodeThumbnail(str, i2);
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("decodeThumbnail failed. e=")));
            return null;
        }
    }

    public byte[] readFile(File file, long j2) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] peek = this.sharedFixedBufferPool.peek();
            int read = fileInputStream.read(peek, 0, (int) j2);
            if (((long) read) < j2) {
                String str = this.TAG;
                Log.e(str, "readStream failed fs=" + j2 + ",bs=" + peek.length + ",rs=" + read);
                fileInputStream.close();
                return null;
            }
            fileInputStream.close();
            return peek;
        } catch (IOException e) {
            String str2 = this.TAG;
            Log.e(str2, "readFile failed. e=" + e.getMessage());
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void recycle() {
        if (this.HEAP) {
            this.sharedFixedBufferPool.clear();
        }
    }

    public Bitmap decodeThumbnail(byte[] bArr, int i2, int i7, int i8) {
        try {
            return this.codecChains[0].decodeThumbnail(bArr, i2, i7, i8);
        } catch (Error | Exception e) {
            Log.e(this.TAG, j.i(e, new StringBuilder("decodeThumbnail(byte) failed. e=")));
            return null;
        }
    }

    public Bitmap decodeHeifThumbnail(byte[] bArr, int i2, int i7, int i8) {
        ImageCodecImpl[] imageCodecImplArr = this.codecChains;
        int length = imageCodecImplArr.length;
        int i10 = 0;
        while (i10 < length) {
            try {
                Bitmap decodeHeifThumbnail = imageCodecImplArr[i10].decodeHeifThumbnail(bArr, i2, i7, i8);
                if (decodeHeifThumbnail != null) {
                    return decodeHeifThumbnail;
                }
                i10++;
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeHeifThumbnail(byte) failed. e=")));
            }
        }
        return null;
    }

    public Bitmap decodeDngThumbnail(byte[] bArr, int i2, int i7, int i8) {
        ImageCodecImpl[] imageCodecImplArr = this.codecChains;
        int length = imageCodecImplArr.length;
        int i10 = 0;
        while (i10 < length) {
            try {
                Bitmap decodeDngThumbnail = imageCodecImplArr[i10].decodeDngThumbnail(bArr, i2, i7, i8);
                if (decodeDngThumbnail != null) {
                    return Math.max(decodeDngThumbnail.getWidth(), decodeDngThumbnail.getHeight()) > i8 * 2 ? BitmapToolkit.resize(decodeDngThumbnail, i8) : decodeDngThumbnail;
                }
                i10++;
            } catch (Error | Exception e) {
                Log.e(this.TAG, j.i(e, new StringBuilder("decodeDngThumbnail(byte) failed. e=")));
            }
        }
        return null;
    }
}
