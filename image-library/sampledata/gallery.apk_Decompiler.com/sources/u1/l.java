package u1;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import w1.C0316c;
import w1.d;
import w1.f;
import w1.h;
import w1.i;
import w1.m;
import w1.n;
import w1.x;
import x0.C0329g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements Parcelable.Creator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1937a;

    public /* synthetic */ l(int i2) {
        this.f1937a = i2;
    }

    public static void a(d dVar, Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        int i7 = dVar.d;
        c.Y(1, 4, parcel);
        parcel.writeInt(i7);
        int i8 = dVar.e;
        c.Y(2, 4, parcel);
        parcel.writeInt(i8);
        int i10 = dVar.f;
        c.Y(3, 4, parcel);
        parcel.writeInt(i10);
        c.T(parcel, 4, dVar.g);
        c.R(parcel, 5, dVar.f1998h);
        c.U(parcel, 6, dVar.f1999i, i2);
        Bundle bundle = dVar.f2000j;
        if (bundle != null) {
            int W10 = c.W(parcel, 7);
            parcel.writeBundle(bundle);
            c.X(parcel, W10);
        }
        c.S(parcel, 8, dVar.k, i2);
        c.U(parcel, 10, dVar.l, i2);
        c.U(parcel, 11, dVar.m, i2);
        boolean z = dVar.n;
        c.Y(12, 4, parcel);
        parcel.writeInt(z ? 1 : 0);
        int i11 = dVar.f2001o;
        c.Y(13, 4, parcel);
        parcel.writeInt(i11);
        boolean z3 = dVar.f2002p;
        c.Y(14, 4, parcel);
        parcel.writeInt(z3 ? 1 : 0);
        c.T(parcel, 15, dVar.q);
        c.X(parcel, W6);
    }

    /* JADX WARNING: type inference failed for: r2v34, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r0v14, types: [w1.x, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v36, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v43, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r2v45, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v46, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r2v47, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r0v17, types: [android.view.View$BaseSavedState, x0.g, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object createFromParcel(android.os.Parcel r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            int r0 = r0.f1937a
            switch(r0) {
                case 0: goto L_0x037c;
                case 1: goto L_0x0333;
                case 2: goto L_0x0303;
                case 3: goto L_0x0292;
                case 4: goto L_0x0249;
                case 5: goto L_0x01f9;
                case 6: goto L_0x01af;
                case 7: goto L_0x014d;
                case 8: goto L_0x00e3;
                case 9: goto L_0x0038;
                default: goto L_0x0009;
            }
        L_0x0009:
            x0.g r0 = new x0.g
            r0.<init>(r1)
            java.lang.String r2 = r1.readString()
            r0.d = r2
            float r2 = r1.readFloat()
            r0.f = r2
            int r2 = r1.readInt()
            r3 = 1
            if (r2 != r3) goto L_0x0022
            goto L_0x0023
        L_0x0022:
            r3 = 0
        L_0x0023:
            r0.g = r3
            java.lang.String r2 = r1.readString()
            r0.f2052h = r2
            int r2 = r1.readInt()
            r0.f2053i = r2
            int r1 = r1.readInt()
            r0.f2054j = r1
            return r0
        L_0x0038:
            int r0 = a.C0068a.d0(r1)
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            com.google.android.gms.common.api.Scope[] r3 = w1.d.r
            r4 = 0
            r5 = 0
            t1.c[] r6 = w1.d.s
            r14 = r2
            r13 = r3
            r11 = r4
            r12 = r11
            r15 = r12
            r21 = r15
            r8 = r5
            r9 = r8
            r10 = r9
            r18 = r10
            r19 = r18
            r20 = r19
            r16 = r6
            r17 = r16
        L_0x005b:
            int r2 = r1.dataPosition()
            if (r2 >= r0) goto L_0x00da
            int r2 = r1.readInt()
            char r3 = (char) r2
            switch(r3) {
                case 1: goto L_0x00d5;
                case 2: goto L_0x00d0;
                case 3: goto L_0x00cb;
                case 4: goto L_0x00c6;
                case 5: goto L_0x00c1;
                case 6: goto L_0x00b7;
                case 7: goto L_0x00a1;
                case 8: goto L_0x0097;
                case 9: goto L_0x0069;
                case 10: goto L_0x008c;
                case 11: goto L_0x0081;
                case 12: goto L_0x007c;
                case 13: goto L_0x0077;
                case 14: goto L_0x0072;
                case 15: goto L_0x006d;
                default: goto L_0x0069;
            }
        L_0x0069:
            a.C0068a.X(r1, r2)
            goto L_0x005b
        L_0x006d:
            java.lang.String r21 = a.C0068a.p(r1, r2)
            goto L_0x005b
        L_0x0072:
            boolean r20 = a.C0068a.M(r1, r2)
            goto L_0x005b
        L_0x0077:
            int r19 = a.C0068a.Q(r1, r2)
            goto L_0x005b
        L_0x007c:
            boolean r18 = a.C0068a.M(r1, r2)
            goto L_0x005b
        L_0x0081:
            android.os.Parcelable$Creator<t1.c> r3 = t1.C0278c.CREATOR
            java.lang.Object[] r2 = a.C0068a.q(r1, r2, r3)
            r17 = r2
            t1.c[] r17 = (t1.C0278c[]) r17
            goto L_0x005b
        L_0x008c:
            android.os.Parcelable$Creator<t1.c> r3 = t1.C0278c.CREATOR
            java.lang.Object[] r2 = a.C0068a.q(r1, r2, r3)
            r16 = r2
            t1.c[] r16 = (t1.C0278c[]) r16
            goto L_0x005b
        L_0x0097:
            android.os.Parcelable$Creator r3 = android.accounts.Account.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            r15 = r2
            android.accounts.Account r15 = (android.accounts.Account) r15
            goto L_0x005b
        L_0x00a1:
            int r2 = a.C0068a.R(r1, r2)
            int r3 = r1.dataPosition()
            if (r2 != 0) goto L_0x00ad
            r14 = r4
            goto L_0x005b
        L_0x00ad:
            android.os.Bundle r5 = r1.readBundle()
            int r3 = r3 + r2
            r1.setDataPosition(r3)
            r14 = r5
            goto L_0x005b
        L_0x00b7:
            android.os.Parcelable$Creator<com.google.android.gms.common.api.Scope> r3 = com.google.android.gms.common.api.Scope.CREATOR
            java.lang.Object[] r2 = a.C0068a.q(r1, r2, r3)
            r13 = r2
            com.google.android.gms.common.api.Scope[] r13 = (com.google.android.gms.common.api.Scope[]) r13
            goto L_0x005b
        L_0x00c1:
            android.os.IBinder r12 = a.C0068a.P(r1, r2)
            goto L_0x005b
        L_0x00c6:
            java.lang.String r11 = a.C0068a.p(r1, r2)
            goto L_0x005b
        L_0x00cb:
            int r10 = a.C0068a.Q(r1, r2)
            goto L_0x005b
        L_0x00d0:
            int r9 = a.C0068a.Q(r1, r2)
            goto L_0x005b
        L_0x00d5:
            int r8 = a.C0068a.Q(r1, r2)
            goto L_0x005b
        L_0x00da:
            a.C0068a.u(r1, r0)
            w1.d r7 = new w1.d
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r7
        L_0x00e3:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r5 = r2
            r8 = r5
            r10 = r8
            r6 = r3
            r7 = r6
            r9 = r7
        L_0x00ef:
            int r3 = r1.dataPosition()
            if (r3 >= r0) goto L_0x0144
            int r3 = r1.readInt()
            char r4 = (char) r3
            switch(r4) {
                case 1: goto L_0x013a;
                case 2: goto L_0x0135;
                case 3: goto L_0x0130;
                case 4: goto L_0x011b;
                case 5: goto L_0x0116;
                case 6: goto L_0x0101;
                default: goto L_0x00fd;
            }
        L_0x00fd:
            a.C0068a.X(r1, r3)
            goto L_0x00ef
        L_0x0101:
            int r3 = a.C0068a.R(r1, r3)
            int r4 = r1.dataPosition()
            if (r3 != 0) goto L_0x010d
            r10 = r2
            goto L_0x00ef
        L_0x010d:
            int[] r10 = r1.createIntArray()
            int r4 = r4 + r3
            r1.setDataPosition(r4)
            goto L_0x00ef
        L_0x0116:
            int r9 = a.C0068a.Q(r1, r3)
            goto L_0x00ef
        L_0x011b:
            int r3 = a.C0068a.R(r1, r3)
            int r4 = r1.dataPosition()
            if (r3 != 0) goto L_0x0127
            r8 = r2
            goto L_0x00ef
        L_0x0127:
            int[] r8 = r1.createIntArray()
            int r4 = r4 + r3
            r1.setDataPosition(r4)
            goto L_0x00ef
        L_0x0130:
            boolean r7 = a.C0068a.M(r1, r3)
            goto L_0x00ef
        L_0x0135:
            boolean r6 = a.C0068a.M(r1, r3)
            goto L_0x00ef
        L_0x013a:
            android.os.Parcelable$Creator<w1.h> r4 = w1.h.CREATOR
            android.os.Parcelable r3 = a.C0068a.o(r1, r3, r4)
            r5 = r3
            w1.h r5 = (w1.h) r5
            goto L_0x00ef
        L_0x0144:
            a.C0068a.u(r1, r0)
            w1.c r4 = new w1.c
            r4.<init>(r5, r6, r7, r8, r9, r10)
            return r4
        L_0x014d:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r4
            r6 = r5
        L_0x0156:
            int r7 = r1.dataPosition()
            if (r7 >= r0) goto L_0x019e
            int r7 = r1.readInt()
            char r8 = (char) r7
            r9 = 1
            if (r8 == r9) goto L_0x0188
            r9 = 2
            if (r8 == r9) goto L_0x017f
            r9 = 3
            if (r8 == r9) goto L_0x017a
            r9 = 4
            if (r8 == r9) goto L_0x0171
            a.C0068a.X(r1, r7)
            goto L_0x0156
        L_0x0171:
            android.os.Parcelable$Creator<w1.c> r6 = w1.C0316c.CREATOR
            android.os.Parcelable r6 = a.C0068a.o(r1, r7, r6)
            w1.c r6 = (w1.C0316c) r6
            goto L_0x0156
        L_0x017a:
            int r3 = a.C0068a.Q(r1, r7)
            goto L_0x0156
        L_0x017f:
            android.os.Parcelable$Creator<t1.c> r5 = t1.C0278c.CREATOR
            java.lang.Object[] r5 = a.C0068a.q(r1, r7, r5)
            t1.c[] r5 = (t1.C0278c[]) r5
            goto L_0x0156
        L_0x0188:
            int r4 = a.C0068a.R(r1, r7)
            int r7 = r1.dataPosition()
            if (r4 != 0) goto L_0x0194
            r4 = r2
            goto L_0x0156
        L_0x0194:
            android.os.Bundle r8 = r1.readBundle()
            int r7 = r7 + r4
            r1.setDataPosition(r7)
            r4 = r8
            goto L_0x0156
        L_0x019e:
            a.C0068a.u(r1, r0)
            w1.x r0 = new w1.x
            r0.<init>()
            r0.d = r4
            r0.e = r5
            r0.f = r3
            r0.g = r6
            return r0
        L_0x01af:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r4 = r2
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
        L_0x01b9:
            int r2 = r1.dataPosition()
            if (r2 >= r0) goto L_0x01f0
            int r2 = r1.readInt()
            char r3 = (char) r2
            r9 = 1
            if (r3 == r9) goto L_0x01eb
            r9 = 2
            if (r3 == r9) goto L_0x01e6
            r9 = 3
            if (r3 == r9) goto L_0x01e1
            r9 = 4
            if (r3 == r9) goto L_0x01dc
            r9 = 5
            if (r3 == r9) goto L_0x01d7
            a.C0068a.X(r1, r2)
            goto L_0x01b9
        L_0x01d7:
            int r8 = a.C0068a.Q(r1, r2)
            goto L_0x01b9
        L_0x01dc:
            int r7 = a.C0068a.Q(r1, r2)
            goto L_0x01b9
        L_0x01e1:
            boolean r6 = a.C0068a.M(r1, r2)
            goto L_0x01b9
        L_0x01e6:
            boolean r5 = a.C0068a.M(r1, r2)
            goto L_0x01b9
        L_0x01eb:
            int r4 = a.C0068a.Q(r1, r2)
            goto L_0x01b9
        L_0x01f0:
            a.C0068a.u(r1, r0)
            w1.h r3 = new w1.h
            r3.<init>(r4, r5, r6, r7, r8)
            return r3
        L_0x01f9:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r5 = r2
            r8 = r5
            r9 = r8
            r6 = r3
            r7 = r6
        L_0x0204:
            int r2 = r1.dataPosition()
            if (r2 >= r0) goto L_0x0240
            int r2 = r1.readInt()
            char r3 = (char) r2
            r4 = 1
            if (r3 == r4) goto L_0x023b
            r4 = 2
            if (r3 == r4) goto L_0x0236
            r4 = 3
            if (r3 == r4) goto L_0x022c
            r4 = 4
            if (r3 == r4) goto L_0x0227
            r4 = 5
            if (r3 == r4) goto L_0x0222
            a.C0068a.X(r1, r2)
            goto L_0x0204
        L_0x0222:
            boolean r9 = a.C0068a.M(r1, r2)
            goto L_0x0204
        L_0x0227:
            boolean r8 = a.C0068a.M(r1, r2)
            goto L_0x0204
        L_0x022c:
            android.os.Parcelable$Creator<t1.a> r3 = t1.C0276a.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r2, r3)
            r7 = r2
            t1.a r7 = (t1.C0276a) r7
            goto L_0x0204
        L_0x0236:
            android.os.IBinder r6 = a.C0068a.P(r1, r2)
            goto L_0x0204
        L_0x023b:
            int r5 = a.C0068a.Q(r1, r2)
            goto L_0x0204
        L_0x0240:
            a.C0068a.u(r1, r0)
            w1.n r4 = new w1.n
            r4.<init>(r5, r6, r7, r8, r9)
            return r4
        L_0x0249:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r4 = r3
            r5 = r4
            r3 = r2
        L_0x0252:
            int r6 = r1.dataPosition()
            if (r6 >= r0) goto L_0x0289
            int r6 = r1.readInt()
            char r7 = (char) r6
            r8 = 1
            if (r7 == r8) goto L_0x0284
            r8 = 2
            if (r7 == r8) goto L_0x027b
            r8 = 3
            if (r7 == r8) goto L_0x0276
            r8 = 4
            if (r7 == r8) goto L_0x026d
            a.C0068a.X(r1, r6)
            goto L_0x0252
        L_0x026d:
            android.os.Parcelable$Creator<com.google.android.gms.auth.api.signin.GoogleSignInAccount> r3 = com.google.android.gms.auth.api.signin.GoogleSignInAccount.CREATOR
            android.os.Parcelable r3 = a.C0068a.o(r1, r6, r3)
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r3 = (com.google.android.gms.auth.api.signin.GoogleSignInAccount) r3
            goto L_0x0252
        L_0x0276:
            int r5 = a.C0068a.Q(r1, r6)
            goto L_0x0252
        L_0x027b:
            android.os.Parcelable$Creator r2 = android.accounts.Account.CREATOR
            android.os.Parcelable r2 = a.C0068a.o(r1, r6, r2)
            android.accounts.Account r2 = (android.accounts.Account) r2
            goto L_0x0252
        L_0x0284:
            int r4 = a.C0068a.Q(r1, r6)
            goto L_0x0252
        L_0x0289:
            a.C0068a.u(r1, r0)
            w1.m r0 = new w1.m
            r0.<init>(r4, r2, r5, r3)
            return r0
        L_0x0292:
            int r0 = a.C0068a.d0(r1)
            r2 = -1
            r3 = 0
            r4 = 0
            r5 = 0
            r18 = r2
            r8 = r3
            r9 = r8
            r10 = r9
            r17 = r10
            r15 = r4
            r16 = r15
            r11 = r5
            r13 = r11
        L_0x02a7:
            int r2 = r1.dataPosition()
            if (r2 >= r0) goto L_0x02fa
            int r2 = r1.readInt()
            char r3 = (char) r2
            r4 = 8
            switch(r3) {
                case 1: goto L_0x02f4;
                case 2: goto L_0x02ee;
                case 3: goto L_0x02e8;
                case 4: goto L_0x02df;
                case 5: goto L_0x02d6;
                case 6: goto L_0x02d0;
                case 7: goto L_0x02c9;
                case 8: goto L_0x02c2;
                case 9: goto L_0x02bb;
                default: goto L_0x02b7;
            }
        L_0x02b7:
            a.C0068a.X(r1, r2)
            goto L_0x02a7
        L_0x02bb:
            int r2 = a.C0068a.Q(r1, r2)
            r18 = r2
            goto L_0x02a7
        L_0x02c2:
            int r2 = a.C0068a.Q(r1, r2)
            r17 = r2
            goto L_0x02a7
        L_0x02c9:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r16 = r2
            goto L_0x02a7
        L_0x02d0:
            java.lang.String r2 = a.C0068a.p(r1, r2)
            r15 = r2
            goto L_0x02a7
        L_0x02d6:
            a.C0068a.h0(r2, r4, r1)
            long r2 = r1.readLong()
            r13 = r2
            goto L_0x02a7
        L_0x02df:
            a.C0068a.h0(r2, r4, r1)
            long r2 = r1.readLong()
            r11 = r2
            goto L_0x02a7
        L_0x02e8:
            int r2 = a.C0068a.Q(r1, r2)
            r10 = r2
            goto L_0x02a7
        L_0x02ee:
            int r2 = a.C0068a.Q(r1, r2)
            r9 = r2
            goto L_0x02a7
        L_0x02f4:
            int r2 = a.C0068a.Q(r1, r2)
            r8 = r2
            goto L_0x02a7
        L_0x02fa:
            a.C0068a.u(r1, r0)
            w1.f r7 = new w1.f
            r7.<init>(r8, r9, r10, r11, r13, r15, r16, r17, r18)
            return r7
        L_0x0303:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
        L_0x0309:
            int r4 = r1.dataPosition()
            if (r4 >= r0) goto L_0x032a
            int r4 = r1.readInt()
            char r5 = (char) r4
            r6 = 1
            if (r5 == r6) goto L_0x0325
            r6 = 2
            if (r5 == r6) goto L_0x031e
            a.C0068a.X(r1, r4)
            goto L_0x0309
        L_0x031e:
            android.os.Parcelable$Creator<w1.f> r2 = w1.f.CREATOR
            java.util.ArrayList r2 = a.C0068a.r(r1, r4, r2)
            goto L_0x0309
        L_0x0325:
            int r3 = a.C0068a.Q(r1, r4)
            goto L_0x0309
        L_0x032a:
            a.C0068a.u(r1, r0)
            w1.i r0 = new w1.i
            r0.<init>(r3, r2)
            return r0
        L_0x0333:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r3
            r3 = r4
        L_0x033c:
            int r6 = r1.dataPosition()
            if (r6 >= r0) goto L_0x0373
            int r6 = r1.readInt()
            char r7 = (char) r6
            r8 = 1
            if (r7 == r8) goto L_0x036e
            r8 = 2
            if (r7 == r8) goto L_0x0369
            r8 = 3
            if (r7 == r8) goto L_0x0360
            r8 = 4
            if (r7 == r8) goto L_0x0357
            a.C0068a.X(r1, r6)
            goto L_0x033c
        L_0x0357:
            android.os.Parcelable$Creator<t1.a> r4 = t1.C0276a.CREATOR
            android.os.Parcelable r4 = a.C0068a.o(r1, r6, r4)
            t1.a r4 = (t1.C0276a) r4
            goto L_0x033c
        L_0x0360:
            android.os.Parcelable$Creator r3 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r3 = a.C0068a.o(r1, r6, r3)
            android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            goto L_0x033c
        L_0x0369:
            java.lang.String r2 = a.C0068a.p(r1, r6)
            goto L_0x033c
        L_0x036e:
            int r5 = a.C0068a.Q(r1, r6)
            goto L_0x033c
        L_0x0373:
            a.C0068a.u(r1, r0)
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            r0.<init>(r5, r2, r3, r4)
            return r0
        L_0x037c:
            int r0 = a.C0068a.d0(r1)
            r2 = 0
            r3 = 0
        L_0x0382:
            int r4 = r1.dataPosition()
            if (r4 >= r0) goto L_0x03a1
            int r4 = r1.readInt()
            char r5 = (char) r4
            r6 = 1
            if (r5 == r6) goto L_0x039c
            r6 = 2
            if (r5 == r6) goto L_0x0397
            a.C0068a.X(r1, r4)
            goto L_0x0382
        L_0x0397:
            java.lang.String r2 = a.C0068a.p(r1, r4)
            goto L_0x0382
        L_0x039c:
            int r3 = a.C0068a.Q(r1, r4)
            goto L_0x0382
        L_0x03a1:
            a.C0068a.u(r1, r0)
            com.google.android.gms.common.api.Scope r0 = new com.google.android.gms.common.api.Scope
            r0.<init>(r3, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u1.l.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final Object[] newArray(int i2) {
        switch (this.f1937a) {
            case 0:
                return new Scope[i2];
            case 1:
                return new Status[i2];
            case 2:
                return new i[i2];
            case 3:
                return new f[i2];
            case 4:
                return new m[i2];
            case 5:
                return new n[i2];
            case 6:
                return new h[i2];
            case 7:
                return new x[i2];
            case 8:
                return new C0316c[i2];
            case 9:
                return new d[i2];
            default:
                return new C0329g[i2];
        }
    }
}
