package com.samsung.android.sum.core.plugin;

import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.functional.OperatorChain;
import com.samsung.android.sum.core.functional.OperatorWrapper;
import com.samsung.android.sum.core.functional.f;
import com.samsung.android.sum.core.types.ImgpType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImgpPlugin extends PluginFixture<ImgpPlugin> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImgpPluginGroup implements Plugin<ImgpPlugin> {
        private final Map<Enum<?>, Operator> operatorMap;

        public ImgpPluginGroup(ImgpPlugin... imgpPluginArr) {
            this.operatorMap = (Map) Arrays.stream(imgpPluginArr).flatMap(new b(0)).collect(Collectors.toMap(new f(5), new f(2), new c(0), new u(0)));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Operator lambda$new$1(Operator operator, Operator operator2) {
            return new OperatorChain(operator, operator2);
        }

        public void bindToFixture(ImgpPlugin imgpPlugin) {
            imgpPlugin.operatorMap = this.operatorMap;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        ANY,
        SIMGP,
        OPENCV,
        RENDERSCRIPT,
        CUSTOM,
        NATIVE_UNIIMGP
    }

    public ImgpPlugin(Plugin<ImgpPlugin> plugin) {
        super(plugin);
    }

    public static ImgpPlugin join(ImgpPlugin... imgpPluginArr) {
        return new ImgpPlugin(new ImgpPluginGroup(imgpPluginArr));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Operator lambda$getImgProcessor$1(Enum enumR) {
        return OperatorWrapper.of(enumR, this.operatorMap.get(enumR));
    }

    public Operator getImgProcessor(Enum<?> enumR) {
        if (enumR == ImgpType.ANY) {
            return OperatorWrapper.of(this.operatorMap);
        }
        if (this.operatorMap.containsKey(enumR)) {
            return OperatorWrapper.of(enumR, this.operatorMap.get(enumR));
        }
        return null;
    }

    public Operator getOperator(Enum<?> enumR) {
        return getImgProcessor(enumR);
    }

    public boolean isValid() {
        return !this.operatorMap.keySet().isEmpty();
    }

    public ImgpPlugin setImgProcessor(Enum<?> enumR, Operator operator) {
        if (this.operatorMap == null) {
            this.operatorMap = new HashMap();
        }
        this.operatorMap.put(enumR, operator);
        return this;
    }

    public Operator getOperator(String str) {
        return getImgProcessor(str);
    }

    public ImgpPlugin setOperator(Enum<?> enumR, Operator operator) {
        return setImgProcessor(enumR, operator);
    }

    public Operator getImgProcessor(String str) {
        return (Operator) this.operatorMap.keySet().stream().filter(new a(0, str)).findFirst().map(new m(1, this)).orElse((Object) null);
    }
}
