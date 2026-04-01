package B3;

import M1.c;
import M1.d;
import O1.b;
import O1.e;
import android.os.Parcelable;
import b2.C0081b;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.material.datepicker.C0117b;
import com.google.android.material.datepicker.C0119d;
import com.google.android.material.datepicker.u;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import k2.l;
import q1.g;
import q1.i;
import q1.k;
import t1.C0276a;
import t1.C0278c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Parcelable.Creator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f81a;

    public /* synthetic */ a(int i2) {
        this.f81a = i2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: android.view.View} */
    /* JADX WARNING: type inference failed for: r0v13, types: [Dc.a, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v9, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v10, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v11, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v12, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v13, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v22, types: [java.lang.Object, M1.c] */
    /* JADX WARNING: type inference failed for: r2v32, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v29, types: [U1.b, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v30, types: [android.view.View$BaseSavedState, b2.b, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v40, types: [java.lang.Object, com.samsung.android.sivs.ai.sdkcommon.translation.a] */
    /* JADX WARNING: type inference failed for: r0v41, types: [java.lang.Object, com.samsung.android.sivs.ai.sdkcommon.translation.b] */
    /* JADX WARNING: type inference failed for: r0v43, types: [k2.l, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v44, types: [android.view.ViewGroup$MarginLayoutParams, java.lang.Object, q1.g] */
    /* JADX WARNING: type inference failed for: r0v45, types: [androidx.recyclerview.widget.RecyclerView$LayoutParams, android.view.ViewGroup$MarginLayoutParams, java.lang.Object, q1.i] */
    /* JADX WARNING: type inference failed for: r0v46, types: [q1.k, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createFromParcel(android.os.Parcel r30) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            int r0 = r0.f81a
            switch(r0) {
                case 0: goto L_0x06ee;
                case 1: goto L_0x06e2;
                case 2: goto L_0x06d6;
                case 3: goto L_0x06ca;
                case 4: goto L_0x06be;
                case 5: goto L_0x06b9;
                case 6: goto L_0x06a1;
                case 7: goto L_0x065c;
                case 8: goto L_0x05f9;
                case 9: goto L_0x05c3;
                case 10: goto L_0x058c;
                case 11: goto L_0x047c;
                case 12: goto L_0x0441;
                case 13: goto L_0x0401;
                case 14: goto L_0x03c1;
                case 15: goto L_0x02d6;
                case 16: goto L_0x02be;
                case 17: goto L_0x0284;
                case 18: goto L_0x027a;
                case 19: goto L_0x026d;
                case 20: goto L_0x025b;
                case 21: goto L_0x0249;
                case 22: goto L_0x0242;
                case 23: goto L_0x0228;
                case 24: goto L_0x01a2;
                case 25: goto L_0x0127;
                case 26: goto L_0x0115;
                case 27: goto L_0x008b;
                case 28: goto L_0x0046;
                default: goto L_0x0009;
            }
        L_0x0009:
            int r0 = a.C0068a.d0(r1)
            r2 = -1
            r4 = 0
            r5 = 0
        L_0x0011:
            int r6 = r1.dataPosition()
            if (r6 >= r0) goto L_0x003d
            int r6 = r1.readInt()
            char r7 = (char) r6
            r8 = 1
            if (r7 == r8) goto L_0x0038
            r8 = 2
            if (r7 == r8) goto L_0x0033
            r8 = 3
            if (r7 == r8) goto L_0x0029
            a.C0068a.X(r1, r6)
            goto L_0x0011
        L_0x0029:
            r2 = 8
            a.C0068a.h0(r6, r2, r1)
            long r2 = r1.readLong()
            goto L_0x0011
        L_0x0033:
            int r4 = a.C0068a.Q(r1, r6)
            goto L_0x0011
        L_0x0038:
            java.lang.String r5 = a.C0068a.p(r1, r6)
            goto L_0x0011
        L_0x003d:
            a.C0068a.u(r1, r0)
            t1.c r0 = new t1.c
            r0.<init>(r5, r4, r2)
            return r0
        L_0x0046:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r4 = r3
            r5 = r4
            r3 = r2
        L_0x004f:
            int r6 = r1.dataPosition()
            if (r6 >= r0) goto L_0x0082
            int r6 = r1.readInt()
            char r7 = (char) r6
            r8 = 1
            if (r7 == r8) goto L_0x007d
            r8 = 2
            if (r7 == r8) goto L_0x0078
            r8 = 3
            if (r7 == r8) goto L_0x006f
            r8 = 4
            if (r7 == r8) goto L_0x006a
            a.C0068a.X(r1, r6)
            goto L_0x004f
        L_0x006a:
            java.lang.String r3 = a.C0068a.p(r1, r6)
            goto L_0x004f
        L_0x006f:
            android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r6, r2)
            android.app.PendingIntent r2 = (android.app.PendingIntent) r2
            goto L_0x004f
        L_0x0078:
            int r5 = a.C0068a.Q(r1, r6)
            goto L_0x004f
        L_0x007d:
            int r4 = a.C0068a.Q(r1, r6)
            goto L_0x004f
        L_0x0082:
            a.C0068a.u(r1, r0)
            t1.a r0 = new t1.a
            r0.<init>(r4, r5, r2, r3)
            return r0
        L_0x008b:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r5 = 0
            r8 = r2
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r16 = r13
            r17 = r16
            r18 = r17
            r19 = r18
            r14 = r3
            r7 = r5
        L_0x00a3:
            int r2 = r1.dataPosition()
            if (r2 >= r0) goto L_0x010c
            int r2 = r1.readInt()
            char r3 = (char) r2
            switch(r3) {
                case 1: goto L_0x0106;
                case 2: goto L_0x0100;
                case 3: goto L_0x00fa;
                case 4: goto L_0x00f4;
                case 5: goto L_0x00ee;
                case 6: goto L_0x00e4;
                case 7: goto L_0x00de;
                case 8: goto L_0x00d3;
                case 9: goto L_0x00cc;
                case 10: goto L_0x00c3;
                case 11: goto L_0x00bc;
                case 12: goto L_0x00b5;
                default: goto L_0x00b1;
            }
        L_0x00b1:
            a.C0068a.X(r1, r2)
            goto L_0x00a3
        L_0x00b5:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r19 = r2
            goto L_0x00a3
        L_0x00bc:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r18 = r2
            goto L_0x00a3
        L_0x00c3:
            android.os.Parcelable$Creator<com.google.android.gms.common.api.Scope> r3 = com.google.android.gms.common.api.Scope.CREATOR
            java.util.ArrayList r2 = a.C0068a.r(r1, r2, r3)
            r17 = r2
            goto L_0x00a3
        L_0x00cc:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r16 = r2
            goto L_0x00a3
        L_0x00d3:
            r3 = 8
            a.C0068a.h0(r2, r3, r1)
            long r2 = r1.readLong()
            r14 = r2
            goto L_0x00a3
        L_0x00de:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r13 = r2
            goto L_0x00a3
        L_0x00e4:
            android.os.Parcelable$Creator r3 = android.net.Uri.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            android.net.Uri r2 = (android.net.Uri) r2
            r12 = r2
            goto L_0x00a3
        L_0x00ee:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r11 = r2
            goto L_0x00a3
        L_0x00f4:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r10 = r2
            goto L_0x00a3
        L_0x00fa:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r9 = r2
            goto L_0x00a3
        L_0x0100:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r8 = r2
            goto L_0x00a3
        L_0x0106:
            int r2 = a.C0068a.Q(r1, r2)
            r7 = r2
            goto L_0x00a3
        L_0x010c:
            a.C0068a.u(r1, r0)
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r6 = new com.google.android.gms.auth.api.signin.GoogleSignInAccount
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r16, r17, r18, r19)
            return r6
        L_0x0115:
            q1.k r0 = new q1.k
            r0.<init>()
            int r2 = r1.readInt()
            r0.d = r2
            int r1 = r1.readInt()
            r0.e = r1
            return r0
        L_0x0127:
            q1.i r0 = new q1.i
            r2 = -2
            r0.<init>((int) r2, (int) r2)
            r2 = 0
            r0.d = r2
            r2 = 1065353216(0x3f800000, float:1.0)
            r0.e = r2
            r2 = -1
            r0.f = r2
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0.g = r2
            r2 = 16777215(0xffffff, float:2.3509886E-38)
            r0.f1871j = r2
            r0.k = r2
            float r2 = r1.readFloat()
            r0.d = r2
            float r2 = r1.readFloat()
            r0.e = r2
            int r2 = r1.readInt()
            r0.f = r2
            float r2 = r1.readFloat()
            r0.g = r2
            int r2 = r1.readInt()
            r0.f1869h = r2
            int r2 = r1.readInt()
            r0.f1870i = r2
            int r2 = r1.readInt()
            r0.f1871j = r2
            int r2 = r1.readInt()
            r0.k = r2
            byte r2 = r1.readByte()
            if (r2 == 0) goto L_0x017a
            r2 = 1
            goto L_0x017b
        L_0x017a:
            r2 = 0
        L_0x017b:
            r0.l = r2
            int r2 = r1.readInt()
            r0.bottomMargin = r2
            int r2 = r1.readInt()
            r0.leftMargin = r2
            int r2 = r1.readInt()
            r0.rightMargin = r2
            int r2 = r1.readInt()
            r0.topMargin = r2
            int r2 = r1.readInt()
            r0.height = r2
            int r1 = r1.readInt()
            r0.width = r1
            return r0
        L_0x01a2:
            q1.g r0 = new q1.g
            r2 = 0
            r0.<init>(r2, r2)
            r3 = 1
            r0.d = r3
            r4 = 0
            r0.e = r4
            r4 = 1065353216(0x3f800000, float:1.0)
            r0.f = r4
            r4 = -1
            r0.g = r4
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0.f1863h = r5
            r0.f1864i = r4
            r0.f1865j = r4
            r4 = 16777215(0xffffff, float:2.3509886E-38)
            r0.k = r4
            r0.l = r4
            int r4 = r1.readInt()
            r0.d = r4
            float r4 = r1.readFloat()
            r0.e = r4
            float r4 = r1.readFloat()
            r0.f = r4
            int r4 = r1.readInt()
            r0.g = r4
            float r4 = r1.readFloat()
            r0.f1863h = r4
            int r4 = r1.readInt()
            r0.f1864i = r4
            int r4 = r1.readInt()
            r0.f1865j = r4
            int r4 = r1.readInt()
            r0.k = r4
            int r4 = r1.readInt()
            r0.l = r4
            byte r4 = r1.readByte()
            if (r4 == 0) goto L_0x0201
            r2 = r3
        L_0x0201:
            r0.m = r2
            int r2 = r1.readInt()
            r0.bottomMargin = r2
            int r2 = r1.readInt()
            r0.leftMargin = r2
            int r2 = r1.readInt()
            r0.rightMargin = r2
            int r2 = r1.readInt()
            r0.topMargin = r2
            int r2 = r1.readInt()
            r0.height = r2
            int r1 = r1.readInt()
            r0.width = r1
            return r0
        L_0x0228:
            k2.l r0 = new k2.l
            r0.<init>()
            int r2 = r1.readInt()
            r0.d = r2
            java.lang.Class<k2.l> r2 = k2.l.class
            java.lang.ClassLoader r2 = r2.getClassLoader()
            android.os.Parcelable r1 = r1.readParcelable(r2)
            h2.i r1 = (h2.i) r1
            r0.e = r1
            return r0
        L_0x0242:
            com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection r0 = new com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection
            r2 = 0
            r0.<init>((android.os.Parcel) r1, (int) r2)
            return r0
        L_0x0249:
            com.samsung.android.sivs.ai.sdkcommon.translation.b r0 = new com.samsung.android.sivs.ai.sdkcommon.translation.b
            r0.<init>()
            java.lang.String r2 = r1.readString()
            r0.d = r2
            float r1 = r1.readFloat()
            r0.e = r1
            return r0
        L_0x025b:
            com.samsung.android.sivs.ai.sdkcommon.translation.a r0 = new com.samsung.android.sivs.ai.sdkcommon.translation.a
            r0.<init>()
            java.lang.String r2 = r1.readString()
            r0.d = r2
            java.lang.String r1 = r1.readString()
            r0.e = r1
            return r0
        L_0x026d:
            int r0 = r1.readInt()
            int r1 = r1.readInt()
            com.google.android.material.datepicker.u r0 = com.google.android.material.datepicker.u.b(r0, r1)
            return r0
        L_0x027a:
            com.google.android.material.datepicker.d r0 = new com.google.android.material.datepicker.d
            long r1 = r1.readLong()
            r0.<init>(r1)
            return r0
        L_0x0284:
            java.lang.Class<com.google.android.material.datepicker.u> r0 = com.google.android.material.datepicker.u.class
            java.lang.ClassLoader r2 = r0.getClassLoader()
            android.os.Parcelable r2 = r1.readParcelable(r2)
            r4 = r2
            com.google.android.material.datepicker.u r4 = (com.google.android.material.datepicker.u) r4
            java.lang.ClassLoader r2 = r0.getClassLoader()
            android.os.Parcelable r2 = r1.readParcelable(r2)
            r5 = r2
            com.google.android.material.datepicker.u r5 = (com.google.android.material.datepicker.u) r5
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r1.readParcelable(r0)
            r7 = r0
            com.google.android.material.datepicker.u r7 = (com.google.android.material.datepicker.u) r7
            java.lang.Class<com.google.android.material.datepicker.d> r0 = com.google.android.material.datepicker.C0119d.class
            java.lang.ClassLoader r0 = r0.getClassLoader()
            android.os.Parcelable r0 = r1.readParcelable(r0)
            r6 = r0
            com.google.android.material.datepicker.d r6 = (com.google.android.material.datepicker.C0119d) r6
            int r8 = r1.readInt()
            com.google.android.material.datepicker.b r3 = new com.google.android.material.datepicker.b
            r3.<init>(r4, r5, r6, r7, r8)
            return r3
        L_0x02be:
            b2.b r0 = new b2.b
            r0.<init>(r1)
            java.lang.Class<b2.b> r2 = b2.C0081b.class
            java.lang.ClassLoader r2 = r2.getClassLoader()
            java.lang.Object r1 = r1.readValue(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r0.d = r1
            return r0
        L_0x02d6:
            U1.b r0 = new U1.b
            r0.<init>()
            r2 = 255(0xff, float:3.57E-43)
            r0.l = r2
            r2 = -2
            r0.n = r2
            r0.f852o = r2
            r0.f853p = r2
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            r0.w = r2
            int r2 = r1.readInt()
            r0.d = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.e = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.g = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f849h = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f850i = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f851j = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.k = r2
            int r2 = r1.readInt()
            r0.l = r2
            java.lang.String r2 = r1.readString()
            r0.m = r2
            int r2 = r1.readInt()
            r0.n = r2
            int r2 = r1.readInt()
            r0.f852o = r2
            int r2 = r1.readInt()
            r0.f853p = r2
            java.lang.String r2 = r1.readString()
            r0.r = r2
            java.lang.String r2 = r1.readString()
            r0.s = r2
            int r2 = r1.readInt()
            r0.t = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.v = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f854x = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.y = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.z = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f845A = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.B = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f846C = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.f847F = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.D = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0.E = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            r0.w = r2
            java.io.Serializable r2 = r1.readSerializable()
            java.util.Locale r2 = (java.util.Locale) r2
            r0.q = r2
            java.io.Serializable r1 = r1.readSerializable()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            r0.f848G = r1
            return r0
        L_0x03c1:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r4 = r3
            r3 = r2
        L_0x03c9:
            int r5 = r1.dataPosition()
            if (r5 >= r0) goto L_0x03f8
            int r5 = r1.readInt()
            char r6 = (char) r5
            r7 = 1
            if (r6 == r7) goto L_0x03f3
            r7 = 2
            if (r6 == r7) goto L_0x03ea
            r7 = 3
            if (r6 == r7) goto L_0x03e1
            a.C0068a.X(r1, r5)
            goto L_0x03c9
        L_0x03e1:
            android.os.Parcelable$Creator<w1.n> r3 = w1.n.CREATOR
            android.os.Parcelable r3 = a.C0068a.o(r1, r5, r3)
            w1.n r3 = (w1.n) r3
            goto L_0x03c9
        L_0x03ea:
            android.os.Parcelable$Creator<t1.a> r2 = t1.C0276a.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r5, r2)
            t1.a r2 = (t1.C0276a) r2
            goto L_0x03c9
        L_0x03f3:
            int r4 = a.C0068a.Q(r1, r5)
            goto L_0x03c9
        L_0x03f8:
            a.C0068a.u(r1, r0)
            O1.e r0 = new O1.e
            r0.<init>(r4, r2, r3)
            return r0
        L_0x0401:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = r2
            r4 = r3
        L_0x0408:
            int r5 = r1.dataPosition()
            if (r5 >= r0) goto L_0x0438
            int r5 = r1.readInt()
            char r6 = (char) r5
            r7 = 1
            if (r6 == r7) goto L_0x0422
            r7 = 2
            if (r6 == r7) goto L_0x041d
            a.C0068a.X(r1, r5)
            goto L_0x0408
        L_0x041d:
            java.lang.String r4 = a.C0068a.p(r1, r5)
            goto L_0x0408
        L_0x0422:
            int r3 = a.C0068a.R(r1, r5)
            int r5 = r1.dataPosition()
            if (r3 != 0) goto L_0x042e
            r3 = r2
            goto L_0x0408
        L_0x042e:
            java.util.ArrayList r6 = r1.createStringArrayList()
            int r5 = r5 + r3
            r1.setDataPosition(r5)
            r3 = r6
            goto L_0x0408
        L_0x0438:
            a.C0068a.u(r1, r0)
            O1.d r0 = new O1.d
            r0.<init>(r4, r3)
            return r0
        L_0x0441:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r4 = r3
        L_0x0448:
            int r5 = r1.dataPosition()
            if (r5 >= r0) goto L_0x0473
            int r5 = r1.readInt()
            char r6 = (char) r5
            r7 = 1
            if (r6 == r7) goto L_0x046e
            r7 = 2
            if (r6 == r7) goto L_0x0469
            r7 = 3
            if (r6 == r7) goto L_0x0460
            a.C0068a.X(r1, r5)
            goto L_0x0448
        L_0x0460:
            android.os.Parcelable$Creator r2 = android.content.Intent.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r5, r2)
            android.content.Intent r2 = (android.content.Intent) r2
            goto L_0x0448
        L_0x0469:
            int r4 = a.C0068a.Q(r1, r5)
            goto L_0x0448
        L_0x046e:
            int r3 = a.C0068a.Q(r1, r5)
            goto L_0x0448
        L_0x0473:
            a.C0068a.u(r1, r0)
            O1.b r0 = new O1.b
            r0.<init>(r3, r4, r2)
            return r0
        L_0x047c:
            int r0 = a.C0068a.d0(r1)
            r4 = 0
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 1056964608(0x3f000000, float:0.5)
            r13 = r4
            r14 = r13
            r15 = r14
            r25 = r15
            r26 = r25
            r23 = r5
            r21 = r6
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r16 = 0
            r20 = 0
            r22 = 0
            r24 = 0
            r27 = 0
            r28 = 0
        L_0x04a2:
            int r3 = r1.dataPosition()
            if (r3 >= r0) goto L_0x0518
            int r3 = r1.readInt()
            char r2 = (char) r3
            switch(r2) {
                case 2: goto L_0x050e;
                case 3: goto L_0x0509;
                case 4: goto L_0x0504;
                case 5: goto L_0x04ff;
                case 6: goto L_0x04fa;
                case 7: goto L_0x04f5;
                case 8: goto L_0x04f0;
                case 9: goto L_0x04eb;
                case 10: goto L_0x04e6;
                case 11: goto L_0x04e1;
                case 12: goto L_0x04dc;
                case 13: goto L_0x04d7;
                case 14: goto L_0x04d2;
                case 15: goto L_0x04cd;
                case 16: goto L_0x04b0;
                case 17: goto L_0x04c8;
                case 18: goto L_0x04c3;
                case 19: goto L_0x04be;
                case 20: goto L_0x04b9;
                case 21: goto L_0x04b4;
                default: goto L_0x04b0;
            }
        L_0x04b0:
            a.C0068a.X(r1, r3)
            goto L_0x04a2
        L_0x04b4:
            float r28 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04b9:
            java.lang.String r27 = a.C0068a.p(r1, r3)
            goto L_0x04a2
        L_0x04be:
            int r26 = a.C0068a.Q(r1, r3)
            goto L_0x04a2
        L_0x04c3:
            android.os.IBinder r16 = a.C0068a.P(r1, r3)
            goto L_0x04a2
        L_0x04c8:
            int r25 = a.C0068a.Q(r1, r3)
            goto L_0x04a2
        L_0x04cd:
            float r24 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04d2:
            float r23 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04d7:
            float r22 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04dc:
            float r21 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04e1:
            float r20 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04e6:
            boolean r15 = a.C0068a.M(r1, r3)
            goto L_0x04a2
        L_0x04eb:
            boolean r14 = a.C0068a.M(r1, r3)
            goto L_0x04a2
        L_0x04f0:
            boolean r13 = a.C0068a.M(r1, r3)
            goto L_0x04a2
        L_0x04f5:
            float r12 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04fa:
            float r11 = a.C0068a.N(r1, r3)
            goto L_0x04a2
        L_0x04ff:
            android.os.IBinder r10 = a.C0068a.P(r1, r3)
            goto L_0x04a2
        L_0x0504:
            java.lang.String r9 = a.C0068a.p(r1, r3)
            goto L_0x04a2
        L_0x0509:
            java.lang.String r8 = a.C0068a.p(r1, r3)
            goto L_0x04a2
        L_0x050e:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r2 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r3, r2)
            r7 = r2
            com.google.android.gms.maps.model.LatLng r7 = (com.google.android.gms.maps.model.LatLng) r7
            goto L_0x04a2
        L_0x0518:
            a.C0068a.u(r1, r0)
            M1.c r0 = new M1.c
            r0.<init>()
            r0.f418h = r6
            r0.f419i = r5
            r1 = 1
            r0.k = r1
            r0.l = r4
            r1 = 0
            r0.m = r1
            r0.n = r6
            r0.f421o = r1
            r0.f422p = r5
            r0.r = r4
            r0.d = r7
            r0.e = r8
            r0.f = r9
            if (r10 != 0) goto L_0x0540
            r1 = 0
            r0.g = r1
            goto L_0x054c
        L_0x0540:
            r1 = 0
            B1.b r2 = new B1.b
            C1.a r3 = C1.b.d(r10)
            r2.<init>((C1.a) r3)
            r0.g = r2
        L_0x054c:
            r0.f418h = r11
            r0.f419i = r12
            r0.f420j = r13
            r0.k = r14
            r0.l = r15
            r2 = r20
            r0.m = r2
            r6 = r21
            r0.n = r6
            r2 = r22
            r0.f421o = r2
            r5 = r23
            r0.f422p = r5
            r2 = r24
            r0.q = r2
            r4 = r26
            r0.t = r4
            r4 = r25
            r0.r = r4
            C1.a r2 = C1.b.d(r16)
            if (r2 != 0) goto L_0x057a
            r3 = r1
            goto L_0x0581
        L_0x057a:
            java.lang.Object r1 = C1.b.e(r2)
            r3 = r1
            android.view.View r3 = (android.view.View) r3
        L_0x0581:
            r0.s = r3
            r3 = r27
            r0.u = r3
            r2 = r28
            r0.v = r2
            return r0
        L_0x058c:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r4 = r2
        L_0x0593:
            int r6 = r1.dataPosition()
            if (r6 >= r0) goto L_0x05ba
            int r6 = r1.readInt()
            char r7 = (char) r6
            r8 = 2
            r9 = 8
            if (r7 == r8) goto L_0x05b2
            r8 = 3
            if (r7 == r8) goto L_0x05aa
            a.C0068a.X(r1, r6)
            goto L_0x0593
        L_0x05aa:
            a.C0068a.h0(r6, r9, r1)
            double r4 = r1.readDouble()
            goto L_0x0593
        L_0x05b2:
            a.C0068a.h0(r6, r9, r1)
            double r2 = r1.readDouble()
            goto L_0x0593
        L_0x05ba:
            a.C0068a.u(r1, r0)
            com.google.android.gms.maps.model.LatLng r0 = new com.google.android.gms.maps.model.LatLng
            r0.<init>(r2, r4)
            return r0
        L_0x05c3:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = r2
        L_0x05c9:
            int r4 = r1.dataPosition()
            if (r4 >= r0) goto L_0x05f0
            int r4 = r1.readInt()
            char r5 = (char) r4
            r6 = 2
            if (r5 == r6) goto L_0x05e7
            r6 = 3
            if (r5 == r6) goto L_0x05de
            a.C0068a.X(r1, r4)
            goto L_0x05c9
        L_0x05de:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r3 = a.C0068a.o(r1, r4, r3)
            com.google.android.gms.maps.model.LatLng r3 = (com.google.android.gms.maps.model.LatLng) r3
            goto L_0x05c9
        L_0x05e7:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r2 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r4, r2)
            com.google.android.gms.maps.model.LatLng r2 = (com.google.android.gms.maps.model.LatLng) r2
            goto L_0x05c9
        L_0x05f0:
            a.C0068a.u(r1, r0)
            com.google.android.gms.maps.model.LatLngBounds r0 = new com.google.android.gms.maps.model.LatLngBounds
            r0.<init>(r2, r3)
            return r0
        L_0x05f9:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r4 = r2
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
        L_0x0603:
            int r2 = r1.dataPosition()
            if (r2 >= r0) goto L_0x0653
            int r2 = r1.readInt()
            char r3 = (char) r2
            r9 = 2
            if (r3 == r9) goto L_0x0649
            r9 = 3
            if (r3 == r9) goto L_0x063f
            r9 = 4
            if (r3 == r9) goto L_0x0635
            r9 = 5
            if (r3 == r9) goto L_0x062b
            r9 = 6
            if (r3 == r9) goto L_0x0621
            a.C0068a.X(r1, r2)
            goto L_0x0603
        L_0x0621:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLngBounds> r3 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            r8 = r2
            com.google.android.gms.maps.model.LatLngBounds r8 = (com.google.android.gms.maps.model.LatLngBounds) r8
            goto L_0x0603
        L_0x062b:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            r7 = r2
            com.google.android.gms.maps.model.LatLng r7 = (com.google.android.gms.maps.model.LatLng) r7
            goto L_0x0603
        L_0x0635:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            r6 = r2
            com.google.android.gms.maps.model.LatLng r6 = (com.google.android.gms.maps.model.LatLng) r6
            goto L_0x0603
        L_0x063f:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            r5 = r2
            com.google.android.gms.maps.model.LatLng r5 = (com.google.android.gms.maps.model.LatLng) r5
            goto L_0x0603
        L_0x0649:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r3 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = (com.google.android.gms.maps.model.LatLng) r4
            goto L_0x0603
        L_0x0653:
            a.C0068a.u(r1, r0)
            M1.d r3 = new M1.d
            r3.<init>(r4, r5, r6, r7, r8)
            return r3
        L_0x065c:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r3
            r3 = r4
        L_0x0665:
            int r6 = r1.dataPosition()
            if (r6 >= r0) goto L_0x0698
            int r6 = r1.readInt()
            char r7 = (char) r6
            r8 = 2
            if (r7 == r8) goto L_0x068f
            r8 = 3
            if (r7 == r8) goto L_0x068a
            r8 = 4
            if (r7 == r8) goto L_0x0685
            r8 = 5
            if (r7 == r8) goto L_0x0680
            a.C0068a.X(r1, r6)
            goto L_0x0665
        L_0x0680:
            float r4 = a.C0068a.N(r1, r6)
            goto L_0x0665
        L_0x0685:
            float r3 = a.C0068a.N(r1, r6)
            goto L_0x0665
        L_0x068a:
            float r2 = a.C0068a.N(r1, r6)
            goto L_0x0665
        L_0x068f:
            android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r5 = com.google.android.gms.maps.model.LatLng.CREATOR
            android.os.Parcelable r5 = a.C0068a.o(r1, r6, r5)
            com.google.android.gms.maps.model.LatLng r5 = (com.google.android.gms.maps.model.LatLng) r5
            goto L_0x0665
        L_0x0698:
            a.C0068a.u(r1, r0)
            com.google.android.gms.maps.model.CameraPosition r0 = new com.google.android.gms.maps.model.CameraPosition
            r0.<init>(r5, r2, r3, r4)
            return r0
        L_0x06a1:
            Dc.a r0 = new Dc.a
            r0.<init>()
            java.lang.String r2 = r1.readString()
            r0.d = r2
            java.lang.String r2 = r1.readString()
            r0.e = r2
            int r1 = r1.readInt()
            r0.f = r1
            return r0
        L_0x06b9:
            Cc.a r0 = Cc.a.b(r1)
            return r0
        L_0x06be:
            java.lang.String r0 = "source"
            kotlin.jvm.internal.j.e(r1, r0)
            B3.g r0 = new B3.g
            r0.<init>(r1)
            return r0
        L_0x06ca:
            java.lang.String r0 = "source"
            kotlin.jvm.internal.j.e(r1, r0)
            B3.f r0 = new B3.f
            r0.<init>(r1)
            return r0
        L_0x06d6:
            java.lang.String r0 = "source"
            kotlin.jvm.internal.j.e(r1, r0)
            B3.e r0 = new B3.e
            r0.<init>(r1)
            return r0
        L_0x06e2:
            java.lang.String r0 = "source"
            kotlin.jvm.internal.j.e(r1, r0)
            B3.d r0 = new B3.d
            r0.<init>(r1)
            return r0
        L_0x06ee:
            java.lang.String r0 = "source"
            kotlin.jvm.internal.j.e(r1, r0)
            B3.b r0 = new B3.b
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: B3.a.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final Object[] newArray(int i2) {
        switch (this.f81a) {
            case 0:
                return new b[i2];
            case 1:
                return new d[i2];
            case 2:
                return new e[i2];
            case 3:
                return new f[i2];
            case 4:
                return new g[i2];
            case 5:
                return new Cc.a[i2];
            case 6:
                return new Dc.a[i2];
            case 7:
                return new CameraPosition[i2];
            case 8:
                return new d[i2];
            case 9:
                return new LatLngBounds[i2];
            case 10:
                return new LatLng[i2];
            case 11:
                return new c[i2];
            case 12:
                return new b[i2];
            case 13:
                return new O1.d[i2];
            case 14:
                return new e[i2];
            case 15:
                return new U1.b[i2];
            case 16:
                return new C0081b[i2];
            case 17:
                return new C0117b[i2];
            case 18:
                return new C0119d[i2];
            case 19:
                return new u[i2];
            case 20:
                return new com.samsung.android.sivs.ai.sdkcommon.translation.a[i2];
            case 21:
                return new com.samsung.android.sivs.ai.sdkcommon.translation.b[i2];
            case 22:
                return new LanguageDirection[i2];
            case 23:
                return new l[i2];
            case 24:
                return new g[i2];
            case 25:
                return new i[i2];
            case 26:
                return new k[i2];
            case 27:
                return new GoogleSignInAccount[i2];
            case 28:
                return new C0276a[i2];
            default:
                return new C0278c[i2];
        }
    }
}
