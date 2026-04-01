package com.samsung.android.sum.core.channel;

import Ad.C0720a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.evaluate.EvalNone;
import com.samsung.android.sum.core.evaluate.Evaluator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class ChannelRouterBase extends BufferChannelGroupBase {
    protected Map<Evaluator, BufferChannel> evChannelMap;

    public ChannelRouterBase(Map<Evaluator, BufferChannel> map) {
        Def.check(!map.isEmpty(), "no edge given", new Object[0]);
        Map map2 = (Map) map.entrySet().stream().collect(Collectors.partitioningBy(new a(0)));
        this.channels = (List) Optional.ofNullable((List) map2.get(Boolean.TRUE)).map(new b(0)).orElseGet(new C0720a(1));
        this.evChannelMap = (Map) Optional.ofNullable((List) map2.get(Boolean.FALSE)).map(new b(1)).orElseGet(new C0720a(10));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(Map.Entry entry) {
        if (entry.getKey() == null || (entry.getKey() instanceof EvalNone)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$new$1(List list) {
        return (List) list.stream().map(new b(6)).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ LinkedHashMap lambda$new$3(List list) {
        return (LinkedHashMap) list.stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(new b(7), new b(6), new c(0), new C0720a(10)));
    }

    public ChannelRouterBase(List<BufferChannel> list) {
        this.channels = list;
        this.evChannelMap = new LinkedHashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ BufferChannel lambda$new$2(BufferChannel bufferChannel, BufferChannel bufferChannel2) {
        return bufferChannel;
    }
}
