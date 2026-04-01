package com.samsung.android.sdk.moneta.travel.internal;

import gg.a;
import ig.f;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import jg.c;
import jg.d;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eR\u001c\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/sdk/moneta/travel/internal/ZonedDateTimeSerializer;", "Lgg/a;", "Ljava/time/ZonedDateTime;", "<init>", "()V", "Ljg/c;", "decoder", "deserialize", "(Ljg/c;)Ljava/time/ZonedDateTime;", "Ljg/d;", "encoder", "value", "Lme/x;", "serialize", "(Ljg/d;Ljava/time/ZonedDateTime;)V", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "formatter", "Ljava/time/format/DateTimeFormatter;", "Lig/f;", "getDescriptor", "()Lig/f;", "descriptor", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ZonedDateTimeSerializer implements a {
    public static final ZonedDateTimeSerializer INSTANCE = new ZonedDateTimeSerializer();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    private ZonedDateTimeSerializer() {
    }

    public f getDescriptor() {
        return L2.a.a("ZonedDateTime");
    }

    public ZonedDateTime deserialize(c cVar) {
        j.e(cVar, "decoder");
        ZonedDateTime parse = ZonedDateTime.parse(cVar.B(), formatter);
        j.d(parse, "parse(...)");
        return parse;
    }

    public void serialize(d dVar, ZonedDateTime zonedDateTime) {
        j.e(dVar, "encoder");
        j.e(zonedDateTime, "value");
        String format = zonedDateTime.format(formatter);
        j.d(format, "format(...)");
        dVar.e(format);
    }
}
