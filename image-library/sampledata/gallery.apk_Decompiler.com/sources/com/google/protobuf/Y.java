package com.google.protobuf;

import A.a;
import Od.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Y implements Schema {

    /* renamed from: a  reason: collision with root package name */
    public final MessageLite f1597a;
    public final h0 b;

    /* renamed from: c  reason: collision with root package name */
    public final r f1598c;

    public Y(h0 h0Var, r rVar, MessageLite messageLite) {
        this.b = h0Var;
        rVar.getClass();
        this.f1598c = rVar;
        this.f1597a = messageLite;
    }

    public final void a(Object obj, P p6) {
        ((C0146s) this.f1598c).getClass();
        a.t(obj);
        throw null;
    }

    public final void b(Object obj, Object obj2) {
        g0.k(this.b, obj, obj2);
    }

    public final void c(Object obj) {
        this.b.getClass();
        UnknownFieldSetLite unknownFieldSetLite = ((GeneratedMessageLite) obj).unknownFields;
        if (unknownFieldSetLite.e) {
            unknownFieldSetLite.e = false;
        }
        ((C0146s) this.f1598c).getClass();
        a.t(obj);
        throw null;
    }

    public final boolean d(Object obj) {
        ((C0146s) this.f1598c).getClass();
        a.t(obj);
        throw null;
    }

    public final int e(AbstractMessageLite abstractMessageLite) {
        this.b.getClass();
        UnknownFieldSetLite unknownFieldSetLite = ((GeneratedMessageLite) abstractMessageLite).unknownFields;
        int i2 = unknownFieldSetLite.d;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < unknownFieldSetLite.f1588a; i8++) {
            int b0 = CodedOutputStream.b0(unknownFieldSetLite.b[i8] >>> 3) + CodedOutputStream.a0(2) + (CodedOutputStream.a0(1) * 2);
            int a0 = CodedOutputStream.a0(3);
            int size = ((ByteString) unknownFieldSetLite.f1589c[i8]).size();
            i7 += CodedOutputStream.b0(size) + size + a0 + b0;
        }
        unknownFieldSetLite.d = i7;
        return i7;
    }

    public final Object f() {
        MessageLite messageLite = this.f1597a;
        if (messageLite instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) messageLite).newMutableInstance();
        }
        return messageLite.newBuilderForType().buildPartial();
    }

    public final void g(Object obj, C0141m mVar, ExtensionRegistryLite extensionRegistryLite) {
        this.b.getClass();
        h0.a(obj);
        ((C0146s) this.f1598c).getClass();
        obj.getClass();
        throw new ClassCastException();
    }

    public final int h(GeneratedMessageLite generatedMessageLite) {
        this.b.getClass();
        return generatedMessageLite.unknownFields.hashCode();
    }

    public final boolean i(GeneratedMessageLite generatedMessageLite, GeneratedMessageLite generatedMessageLite2) {
        this.b.getClass();
        if (!generatedMessageLite.unknownFields.equals(generatedMessageLite2.unknownFields)) {
            return false;
        }
        return true;
    }

    public final void j(Object obj, byte[] bArr, int i2, int i7, b bVar) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        if (generatedMessageLite.unknownFields == UnknownFieldSetLite.f) {
            generatedMessageLite.unknownFields = new UnknownFieldSetLite();
        }
        obj.getClass();
        throw new ClassCastException();
    }
}
