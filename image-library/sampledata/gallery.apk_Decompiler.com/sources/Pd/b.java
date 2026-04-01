package Pd;

import android.util.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {
    public static final String[] f = {"colr", "hvcC", "auxC", "ispe", "irot", "pixi", "rloc", "lsel", "clap", "pasp", "imir"};

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f3628a;
    public final HashMap b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    public final int f3629c;
    public ByteBuffer d;
    public boolean e = false;

    public b(int i2) {
        this.f3629c = i2 + 8;
        this.f3628a = new ArrayList();
    }

    public final boolean a(ByteBuffer byteBuffer) {
        int i2;
        int i7;
        try {
            int capacity = byteBuffer.capacity();
            int i8 = 0;
            int i10 = 1;
            while (i8 < capacity) {
                if (i8 < 0 || (i2 = i8 + 4) > capacity) {
                    throw new IndexOutOfBoundsException("Index out of buffer bounds");
                }
                byte[] bArr = new byte[4];
                byteBuffer.position(i8);
                byteBuffer.get(bArr);
                int i11 = ByteBuffer.wrap(bArr).getInt();
                if (i2 < 0 || (i7 = i8 + 8) > capacity) {
                    throw new IndexOutOfBoundsException("Index out of buffer bounds");
                }
                byte[] bArr2 = new byte[4];
                byteBuffer.position(i2);
                byteBuffer.get(bArr2);
                this.f3628a.add(new a(new String(bArr2), i8, i11, i10));
                int i12 = i11 - 8;
                if (i7 < 0 || (i8 = i7 + i12) > capacity) {
                    throw new IndexOutOfBoundsException("Index out of buffer bounds");
                }
                i10++;
            }
            return true;
        } catch (IndexOutOfBoundsException e7) {
            Log.e("IpcoTable", "Error parsing IPCO Table: " + e7.getMessage());
            return false;
        }
    }

    public final int b() {
        ArrayList arrayList = this.f3628a;
        if (arrayList == null) {
            Log.e("IpcoTable", "No Ipco Item in the list");
            return -2;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.d != aVar.e) {
                return 2;
            }
        }
        Log.i("IpcoTable", "All items are supported");
        return 1;
    }

    public final void c() {
        if (this.f3628a == null) {
            Log.e("IpcoTable", "No Ipco Item in the list");
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        Iterator it = this.f3628a.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            sb2.append(" ");
            sb2.append(aVar.f3626a);
            sb2.append("(");
            sb2.append(aVar.d);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(aVar.e);
            sb2.append(")");
        }
        Log.d("IpcoTable", sb2.toString());
    }

    public final void d(ByteBuffer byteBuffer) {
        ArrayList arrayList = this.f3628a;
        if (arrayList == null) {
            Log.e("IpcoTable", "No Ipco Item in the list");
            return;
        }
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += ((a) it.next()).f3627c;
        }
        if (i2 != byteBuffer.capacity()) {
            Log.e("IpcoTable", "Invalid mapping table");
        } else {
            this.d = byteBuffer.duplicate();
        }
    }

    public final boolean e() {
        String[] strArr;
        if (this.f3628a == null) {
            Log.e("IpcoTable", "No Ipco Item in the list");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f3628a.iterator();
        int i2 = 1;
        while (true) {
            boolean hasNext = it.hasNext();
            strArr = f;
            if (!hasNext) {
                break;
            }
            a aVar = (a) it.next();
            String str = aVar.f3626a;
            int i7 = 0;
            while (true) {
                if (i7 >= 11) {
                    break;
                } else if (str.equals(strArr[i7])) {
                    aVar.e = i2;
                    arrayList.add(aVar);
                    i2++;
                    break;
                } else {
                    i7++;
                }
            }
        }
        Iterator it2 = this.f3628a.iterator();
        while (it2.hasNext()) {
            a aVar2 = (a) it2.next();
            String str2 = aVar2.f3626a;
            int i8 = 0;
            while (true) {
                if (i8 >= 11) {
                    aVar2.e = i2;
                    arrayList.add(aVar2);
                    i2++;
                    break;
                } else if (str2.equals(strArr[i8])) {
                    break;
                } else {
                    i8++;
                }
            }
        }
        this.f3628a.clear();
        this.f3628a = arrayList;
        HashMap hashMap = this.b;
        hashMap.clear();
        Iterator it3 = this.f3628a.iterator();
        while (it3.hasNext()) {
            a aVar3 = (a) it3.next();
            hashMap.put(Integer.valueOf(aVar3.d), Integer.valueOf(aVar3.e));
        }
        return true;
    }
}
