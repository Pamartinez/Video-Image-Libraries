package com.samsung.android.sum.core.filter;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4077a;
    public final /* synthetic */ Enum b;

    public /* synthetic */ b(Enum enumR, int i2) {
        this.f4077a = i2;
        this.b = enumR;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4077a;
        Enum enumR = this.b;
        Function function = (Function) obj;
        switch (i2) {
            case 0:
                return DecoratePluginInOutStreamFilter.lambda$getOutputChannel$1(enumR, function);
            case 1:
                return DecoratePluginInOutStreamFilter.lambda$getInputChannel$0(enumR, function);
            case 2:
                return MediaInOutStreamFilterBase.lambda$getOutputChannel$1(enumR, function);
            case 3:
                return MediaInOutStreamFilterBase.lambda$getInputChannel$0(enumR, function);
            case 4:
                return MediaInputStreamFilterBase.lambda$getInputChannel$0(enumR, function);
            case 5:
                return MediaOutputStreamFilterBase.lambda$getOutputChannel$0(enumR, function);
            case 6:
                return OutStreamPluginFilter.lambda$getOutputChannel$1(enumR, function);
            case 7:
                return PluginInOutStreamFilter.lambda$getOutputChannel$1(enumR, function);
            default:
                return PluginInOutStreamFilter.lambda$getInputChannel$0(enumR, function);
        }
    }
}
