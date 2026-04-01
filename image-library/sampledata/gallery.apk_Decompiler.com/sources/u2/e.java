package U2;

import java.io.FilterWriter;
import java.io.StringWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends FilterWriter {
    public final String d;
    public final int e;
    public final int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f861h;

    /* renamed from: i  reason: collision with root package name */
    public int f862i;

    public e(StringWriter stringWriter, int i2, String str) {
        super(stringWriter);
        int i7;
        if (i2 >= 0) {
            if (i2 != 0) {
                i7 = i2;
            } else {
                i7 = Integer.MAX_VALUE;
            }
            this.e = i7;
            boolean z = true;
            int i8 = i2 >> 1;
            this.f = i8;
            this.d = str.length() == 0 ? null : str;
            this.g = 0;
            this.f861h = i8 == 0 ? false : z;
            this.f862i = 0;
            return;
        }
        throw new IllegalArgumentException("width < 0");
    }

    public final void write(int i2) {
        int i7;
        synchronized (this.lock) {
            try {
                boolean z = true;
                if (this.f861h) {
                    if (i2 == 32) {
                        int i8 = this.f862i + 1;
                        this.f862i = i8;
                        int i10 = this.f;
                        if (i8 >= i10) {
                            this.f862i = i10;
                            this.f861h = false;
                        }
                    } else {
                        this.f861h = false;
                    }
                }
                if (this.g == this.e && i2 != 10) {
                    this.out.write(10);
                    this.g = 0;
                }
                if (this.g == 0) {
                    String str = this.d;
                    if (str != null) {
                        this.out.write(str);
                    }
                    if (!this.f861h) {
                        int i11 = 0;
                        while (true) {
                            i7 = this.f862i;
                            if (i11 >= i7) {
                                break;
                            }
                            this.out.write(32);
                            i11++;
                        }
                        this.g = i7;
                    }
                }
                this.out.write(i2);
                if (i2 == 10) {
                    this.g = 0;
                    if (this.f == 0) {
                        z = false;
                    }
                    this.f861h = z;
                    this.f862i = 0;
                } else {
                    this.g++;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void write(char[] cArr, int i2, int i7) {
        synchronized (this.lock) {
            while (i7 > 0) {
                try {
                    write(cArr[i2]);
                    i2++;
                    i7--;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final void write(String str, int i2, int i7) {
        synchronized (this.lock) {
            while (i7 > 0) {
                try {
                    write(str.charAt(i2));
                    i2++;
                    i7--;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
