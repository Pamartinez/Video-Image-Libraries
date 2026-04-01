package Tf;

import Ae.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements c {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ w(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008c, code lost:
        if (r3 == null) goto L_0x008e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object r17, java.lang.Object r18) {
        /*
            r16 = this;
            r0 = r16
            int r1 = r0.d
            java.lang.Object r0 = r0.e
            switch(r1) {
                case 0: goto L_0x00d9;
                case 1: goto L_0x00ca;
                case 2: goto L_0x0055;
                case 3: goto L_0x0042;
                case 4: goto L_0x002b;
                case 5: goto L_0x001c;
                default: goto L_0x0009;
            }
        L_0x0009:
            android.graphics.Point r0 = (android.graphics.Point) r0
            r1 = r17
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableLine r1 = (com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableLine) r1
            r2 = r18
            com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableLine r2 = (com.samsung.android.app.sdk.deepsky.textextraction.selectionui.data.SelectableLine) r2
            int r0 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.textselection.TextSelectionHelper.findNearestLineInBlockLineByLine$lambda$30(r0, r1, r2)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L_0x001c:
            com.samsung.android.motionphoto.utils.v2.video.VideoTransfer r0 = (com.samsung.android.motionphoto.utils.v2.video.VideoTransfer) r0
            r1 = r17
            com.samsung.android.motionphoto.utils.v2.MimeType r1 = (com.samsung.android.motionphoto.utils.v2.MimeType) r1
            r2 = r18
            android.media.MediaFormat r2 = (android.media.MediaFormat) r2
            me.x r0 = com.samsung.android.motionphoto.utils.v2.video.VideoTransfer._init_$lambda$1(r0, r1, r2)
            return r0
        L_0x002b:
            com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder r0 = (com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder) r0
            r1 = r17
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r2 = r18
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            me.x r0 = com.samsung.android.motionphoto.utils.v2.video.VideoTranscoder.prepareDecoder$lambda$26(r0, r1, r2)
            return r0
        L_0x0042:
            java.util.function.BiConsumer r0 = (java.util.function.BiConsumer) r0
            r1 = r17
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            r2 = r18
            com.samsung.android.motionphoto.utils.v2.video.ExportEvent r2 = (com.samsung.android.motionphoto.utils.v2.video.ExportEvent) r2
            me.x r0 = com.samsung.android.motionphoto.utils.v2.MotionScrap.Builder.setOnCompleteListener$lambda$0(r0, r1, r2)
            return r0
        L_0x0055:
            Zf.l r0 = (Zf.l) r0
            r1 = r17
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r2 = r18
            qe.f r2 = (qe.C1230f) r2
            qe.g r3 = r2.getKey()
            qe.h r0 = r0.e
            qe.f r0 = r0.get(r3)
            Vf.y r4 = Vf.C0887y.e
            if (r3 == r4) goto L_0x0079
            if (r2 == r0) goto L_0x0076
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x008e
        L_0x0076:
            int r1 = r1 + 1
            goto L_0x008e
        L_0x0079:
            r3 = r0
            Vf.e0 r3 = (Vf.C0867e0) r3
            Vf.e0 r2 = (Vf.C0867e0) r2
        L_0x007e:
            r0 = 0
            if (r2 != 0) goto L_0x0083
            r2 = r0
            goto L_0x008a
        L_0x0083:
            if (r2 != r3) goto L_0x0086
            goto L_0x008a
        L_0x0086:
            boolean r4 = r2 instanceof cg.r
            if (r4 != 0) goto L_0x00b8
        L_0x008a:
            if (r2 != r3) goto L_0x0093
            if (r3 != 0) goto L_0x0076
        L_0x008e:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            return r0
        L_0x0093:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of "
            r1.<init>(r4)
            r1.append(r2)
            java.lang.String r2 = ", expected child of "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00b8:
            cg.r r2 = (cg.r) r2
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = Vf.n0.e
            java.lang.Object r2 = r4.get(r2)
            Vf.o r2 = (Vf.C0878o) r2
            if (r2 == 0) goto L_0x00c8
            Vf.e0 r0 = r2.getParent()
        L_0x00c8:
            r2 = r0
            goto L_0x007e
        L_0x00ca:
            com.samsung.android.gallery.module.search.engine.PersonalDataCore r0 = (com.samsung.android.gallery.module.search.engine.PersonalDataCore) r0
            r1 = r17
            Vf.A r1 = (Vf.A) r1
            r2 = r18
            qe.c r2 = (qe.C1227c) r2
            java.lang.Object r0 = r0.lambda$prepareSearchEngine$14(r1, r2)
            return r0
        L_0x00d9:
            java.util.List r0 = (java.util.List) r0
            r3 = r17
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r1 = r18
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            java.lang.String r2 = "$this$DelimitedRangesSequence"
            kotlin.jvm.internal.j.e(r3, r2)
            java.util.Collection r0 = (java.util.Collection) r0
            int r2 = r0.size()
            r4 = 1
            r7 = 0
            if (r2 != r4) goto L_0x0113
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.lang.Object r0 = ne.C1194l.a1(r0)
            java.lang.String r0 = (java.lang.String) r0
            r2 = 4
            int r1 = Tf.n.B0(r3, r0, r1, r2)
            if (r1 >= 0) goto L_0x0108
        L_0x0105:
            r2 = r7
            goto L_0x01a5
        L_0x0108:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            me.i r2 = new me.i
            r2.<init>(r1, r0)
            goto L_0x01a5
        L_0x0113:
            Ge.e r2 = new Ge.e
            if (r1 >= 0) goto L_0x0118
            r1 = 0
        L_0x0118:
            int r5 = r3.length()
            r2.<init>(r1, r5, r4)
            boolean r4 = r3 instanceof java.lang.String
            r6 = 0
            int r14 = r2.f
            int r15 = r2.e
            if (r4 == 0) goto L_0x0169
            if (r14 <= 0) goto L_0x012c
            if (r1 <= r15) goto L_0x0130
        L_0x012c:
            if (r14 >= 0) goto L_0x0105
            if (r15 > r1) goto L_0x0105
        L_0x0130:
            r12 = r1
        L_0x0131:
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x0138:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0155
            java.lang.Object r2 = r1.next()
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            r11 = r3
            java.lang.String r11 = (java.lang.String) r11
            int r13 = r8.length()
            r9 = 0
            r10 = r6
            boolean r4 = Tf.v.q0(r8, r9, r10, r11, r12, r13)
            if (r4 == 0) goto L_0x0138
            goto L_0x0156
        L_0x0155:
            r2 = r7
        L_0x0156:
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0165
            java.lang.Integer r0 = java.lang.Integer.valueOf(r12)
            me.i r1 = new me.i
            r1.<init>(r0, r2)
            r2 = r1
            goto L_0x01a5
        L_0x0165:
            if (r12 == r15) goto L_0x0105
            int r12 = r12 + r14
            goto L_0x0131
        L_0x0169:
            if (r14 <= 0) goto L_0x016d
            if (r1 <= r15) goto L_0x0171
        L_0x016d:
            if (r14 >= 0) goto L_0x0105
            if (r15 > r1) goto L_0x0105
        L_0x0171:
            r4 = r1
        L_0x0172:
            r1 = r0
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r8 = r1.iterator()
        L_0x0179:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x0192
            java.lang.Object r9 = r8.next()
            r1 = r9
            java.lang.String r1 = (java.lang.String) r1
            r2 = 0
            int r5 = r1.length()
            boolean r1 = Tf.n.G0(r1, r2, r3, r4, r5, r6)
            if (r1 == 0) goto L_0x0179
            goto L_0x0193
        L_0x0192:
            r9 = r7
        L_0x0193:
            java.lang.String r9 = (java.lang.String) r9
            if (r9 == 0) goto L_0x01a1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            me.i r2 = new me.i
            r2.<init>(r0, r9)
            goto L_0x01a5
        L_0x01a1:
            if (r4 == r15) goto L_0x0105
            int r4 = r4 + r14
            goto L_0x0172
        L_0x01a5:
            if (r2 == 0) goto L_0x01ba
            java.lang.Object r0 = r2.d
            java.lang.Object r1 = r2.e
            java.lang.String r1 = (java.lang.String) r1
            int r1 = r1.length()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            me.i r7 = new me.i
            r7.<init>(r0, r1)
        L_0x01ba:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: Tf.w.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
