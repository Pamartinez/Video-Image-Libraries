package com.samsung.android.motionphoto.utils.v2;

import A.a;
import Ad.C0721b;
import Ae.b;
import Bd.C0725a;
import L1.d;
import Tf.c;
import Tf.e;
import Tf.g;
import Tf.n;
import Tf.v;
import a.C0068a;
import android.media.MediaCodec;
import android.os.Build;
import android.os.SemSystemProperties;
import com.samsung.android.motionphoto.utils.BuildConfig;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.f;
import ne.C1194l;
import ne.C1195m;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0011\n\u0002\u0010\n\n\u0002\b\b\n\u0002\u0010\u0004\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\r\u001a\r\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0019\u0010\u0006\u001a\u00020\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001d\u0010\u000b\u001a\u00020\n*\u00020\u00032\n\u0010\t\u001a\u00020\b\"\u00020\u0004¢\u0006\u0004\b\u000b\u0010\f\u001a\u001d\u0010\u000b\u001a\u00020\n*\u00020\u00032\n\u0010\t\u001a\u00020\u0003\"\u00020\r¢\u0006\u0004\b\u000b\u0010\u000e\u001a\u001d\u0010\u0010\u001a\u00020\n*\u00020\u000f2\n\u0010\t\u001a\u00020\b\"\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001d\u0010\u0010\u001a\u00020\n*\u00020\u00122\n\u0010\t\u001a\u00020\b\"\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0013\u001a\u001d\u0010\u0010\u001a\u00020\n*\u00020\u000f2\n\u0010\t\u001a\u00020\u0003\"\u00020\r¢\u0006\u0004\b\u0010\u0010\u0014\u001a\u001d\u0010\u0010\u001a\u00020\n*\u00020\u00122\n\u0010\t\u001a\u00020\u0003\"\u00020\r¢\u0006\u0004\b\u0010\u0010\u0015\u001a-\u0010\u001b\u001a\u00020\u001a*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\n¢\u0006\u0004\b\u001b\u0010\u001c\u001a#\u0010\u001d\u001a\u00020\u001a*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001d\u0010\u001e\u001a'\u0010#\u001a\u0004\u0018\u00010\"*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b#\u0010$\u001a'\u0010#\u001a\u0004\u0018\u00010\"*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b#\u0010%\u001a%\u0010&\u001a\u00020\"*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b&\u0010$\u001a%\u0010&\u001a\u00020\"*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b&\u0010%\u001a'\u0010(\u001a\u0004\u0018\u00010'*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b(\u0010)\u001a'\u0010(\u001a\u0004\u0018\u00010'*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b(\u0010*\u001a%\u0010+\u001a\u00020'*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b+\u0010)\u001a%\u0010+\u001a\u00020'*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b+\u0010*\u001a'\u0010-\u001a\u0004\u0018\u00010,*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b-\u0010.\u001a'\u0010-\u001a\u0004\u0018\u00010,*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b-\u0010/\u001a%\u00100\u001a\u00020,*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b0\u0010.\u001a%\u00100\u001a\u00020,*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b0\u0010/\u001a\u001b\u00101\u001a\u0004\u0018\u00010\u0000*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b1\u00102\u001a\u001b\u00101\u001a\u0004\u0018\u00010\u0000*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b1\u00103\u001a\u0019\u00104\u001a\u00020\u0000*\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b4\u00102\u001a\u0019\u00104\u001a\u00020\u0000*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b4\u00103\u001a4\u0010:\u001a\u00020\u001a\"\n\b\u0000\u00106\u0018\u0001*\u000205*\u0002072\u0012\u00109\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u000008\"\u00028\u0000H\b¢\u0006\u0004\b:\u0010;\u001a!\u0010=\u001a\u00020\u0004*\u00020\u00122\u0006\u0010<\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b=\u0010>\u001a4\u0010=\u001a\u00020\u001a\"\n\b\u0000\u00106\u0018\u0001*\u000205*\u00020\u00122\u0012\u00109\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u000008\"\u00028\u0000H\b¢\u0006\u0004\b=\u0010?\u001a\u0019\u0010=\u001a\u00020\u0004*\u00020\u00122\u0006\u0010@\u001a\u00020\u0003¢\u0006\u0004\b=\u0010A\u001a)\u0010E\u001a\u00020\u001a*\u00020\u00122\u0006\u0010B\u001a\u00020\u00172\u0006\u0010C\u001a\u00020\u00172\u0006\u0010D\u001a\u00020\u0012¢\u0006\u0004\bE\u0010F\u001a\u0019\u0010H\u001a\u00020\u001a*\u00020\u00122\u0006\u0010G\u001a\u00020\u0004¢\u0006\u0004\bH\u0010I\u001a\u0019\u0010L\u001a\u00020\u0004*\u00020J2\u0006\u0010K\u001a\u00020\u0004¢\u0006\u0004\bL\u0010M\u001a\u0019\u0010O\u001a\u00020\u0004*\u00020\u00042\u0006\u0010N\u001a\u00020\u0004¢\u0006\u0004\bO\u0010P\u001a\u001c\u0010Q\u001a\u00028\u0000\"\u0006\b\u0000\u00106\u0018\u0001*\u00020JH\b¢\u0006\u0004\bQ\u0010R\u001a \u0010T\u001a\u00028\u0000\"\n\b\u0000\u00106\u0018\u0001*\u00020S*\u00020JH\b¢\u0006\u0004\bT\u0010U\u001a\u001d\u0010V\u001a\u00020\u0003*\u00020J2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\bV\u0010W\u001a\u0019\u0010Y\u001a\u00020\u0004*\u00020\r2\u0006\u0010X\u001a\u00020\u0004¢\u0006\u0004\bY\u0010Z\u001a\u0019\u0010[\u001a\u00020\r*\u00020\u00042\u0006\u0010B\u001a\u00020\u0004¢\u0006\u0004\b[\u0010\\\u001a\u001b\u0010]\u001a\u00020\u0017*\u00020'2\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b]\u0010^\u001a\u0011\u0010\u0006\u001a\u00020\u0004*\u00020,¢\u0006\u0004\b\u0006\u0010_\u001a\u0011\u0010]\u001a\u00020\u0017*\u00020,¢\u0006\u0004\b]\u0010`\u001a\u0011\u0010a\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\ba\u0010b\u001a\u0019\u0010e\u001a\u00020\n*\u00020c2\u0006\u0010d\u001a\u00020\u0004¢\u0006\u0004\be\u0010f\u001a?\u0010k\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u00106\"\u0004\b\u0001\u0010g*\b\u0012\u0004\u0012\u00028\u00000h2\u0012\u0010j\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010iH\bø\u0001\u0000¢\u0006\u0004\bk\u0010l\u001a_\u0010k\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u00106\"\u0004\b\u0001\u0010g*\b\u0012\u0004\u0012\u00028\u00000h2\u0012\u0010j\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010i2\u001e\u0010o\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000h0n0mH\bø\u0001\u0000¢\u0006\u0004\bk\u0010p\u001a\u0011\u0010q\u001a\u00020\u0000*\u00020c¢\u0006\u0004\bq\u0010r\u001a+\u0010w\u001a\b\u0012\u0004\u0012\u00028\u00000v\"\u0004\b\u0000\u00106*\b\u0012\u0004\u0012\u00028\u00000s2\u0006\u0010u\u001a\u00020t¢\u0006\u0004\bw\u0010x\u001a\u0011\u0010z\u001a\u00020\u0004*\u00020y¢\u0006\u0004\bz\u0010{\u001a\u001d\u0010}\u001a\u00020\n*\u00020\u00042\n\u0010|\u001a\u00020\b\"\u00020\u0004¢\u0006\u0004\b}\u0010~\u001a\u001d\u0010\u001a\u00020\n*\u00020\u00042\n\u0010|\u001a\u00020\b\"\u00020\u0004¢\u0006\u0004\b\u0010~\u001a\u0010\u0010\u0001\u001a\u00020\u0004¢\u0006\u0006\b\u0001\u0010\u0001\u001a\u0019\u0010\u0001\u001a\u00020\u001a2\u0007\u0010\u0001\u001a\u00020\u0000¢\u0006\u0006\b\u0001\u0010\u0001\u001a\u0015\u0010\u0001\u001a\u00030\u0001*\u00020\u0003¢\u0006\u0006\b\u0001\u0010\u0001\u001a\u001d\u0010\u0001\u001a\u00020\u0000*\u00030\u00012\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0006\b\u0001\u0010\u0001\u001a<\u0010\u0001\u001a\u00020\u001a\"\u0004\b\u0000\u00106*\t\u0012\u0004\u0012\u00028\u00000\u00012\u0013\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0iH\bø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001\u001a<\u0010\u0001\u001a\u00020\u001a\"\u0004\b\u0000\u00106*\t\u0012\u0004\u0012\u00028\u00000\u00012\u0013\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0iH\bø\u0001\u0000¢\u0006\u0006\b\u0001\u0010\u0001\" \u0010\u0001\u001a\u00020\n8FX\u0002¢\u0006\u0010\n\u0006\b\u0001\u0010\u0001\u001a\u0006\b\u0001\u0010\u0001\" \u0010\u0001\u001a\u00020\n8FX\u0002¢\u0006\u0010\n\u0006\b\u0001\u0010\u0001\u001a\u0006\b\u0001\u0010\u0001\" \u0010\u0001\u001a\u00020\n8FX\u0002¢\u0006\u0010\n\u0006\b\u0001\u0010\u0001\u001a\u0006\b\u0001\u0010\u0001\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0001"}, d2 = {"", "getMPUtilsVersion", "()Ljava/lang/String;", "", "", "index", "getInt", "([BI)I", "", "args", "", "contains", "([B[I)Z", "", "([B[B)Z", "Ljava/io/InputStream;", "matched", "(Ljava/io/InputStream;[I)Z", "Ljava/nio/channels/FileChannel;", "(Ljava/nio/channels/FileChannel;[I)Z", "(Ljava/io/InputStream;[B)Z", "(Ljava/nio/channels/FileChannel;[B)Z", "shiftBytes", "", "bufferSize", "isSuccessive", "Lme/x;", "shiftLeftSafely", "(Ljava/nio/channels/FileChannel;IJZ)V", "shiftRightSafely", "(Ljava/nio/channels/FileChannel;IJ)V", "size", "Ljava/nio/ByteOrder;", "order", "Ljava/nio/LongBuffer;", "readAsLongBufferOrNull", "(Ljava/io/InputStream;ILjava/nio/ByteOrder;)Ljava/nio/LongBuffer;", "(Ljava/nio/channels/FileChannel;ILjava/nio/ByteOrder;)Ljava/nio/LongBuffer;", "readAsLongBuffer", "Ljava/nio/IntBuffer;", "readAsIntBufferOrNull", "(Ljava/io/InputStream;ILjava/nio/ByteOrder;)Ljava/nio/IntBuffer;", "(Ljava/nio/channels/FileChannel;ILjava/nio/ByteOrder;)Ljava/nio/IntBuffer;", "readAsIntBuffer", "Ljava/nio/ShortBuffer;", "readAsShortBufferOrNull", "(Ljava/io/InputStream;ILjava/nio/ByteOrder;)Ljava/nio/ShortBuffer;", "(Ljava/nio/channels/FileChannel;ILjava/nio/ByteOrder;)Ljava/nio/ShortBuffer;", "readAsShortBuffer", "readAsStringOrNull", "(Ljava/io/InputStream;I)Ljava/lang/String;", "(Ljava/nio/channels/FileChannel;I)Ljava/lang/String;", "readAsString", "", "T", "Ljava/io/OutputStream;", "", "values", "writeAll", "(Ljava/io/OutputStream;[Ljava/lang/Object;)V", "byte", "write", "(Ljava/nio/channels/FileChannel;BI)I", "(Ljava/nio/channels/FileChannel;[Ljava/lang/Object;)V", "array", "(Ljava/nio/channels/FileChannel;[B)I", "position", "count", "target", "transferAllTo", "(Ljava/nio/channels/FileChannel;JJLjava/nio/channels/FileChannel;)V", "bytes", "skipNBytes", "(Ljava/nio/channels/FileChannel;I)V", "", "mask", "toInt", "(SI)I", "n", "align", "(II)I", "firstByte", "(S)Ljava/lang/Object;", "", "lastByte", "(S)Ljava/lang/Number;", "toByteArray", "(SLjava/nio/ByteOrder;)[B", "shift", "shr", "(BI)I", "getByte", "(II)B", "getLong", "(Ljava/nio/IntBuffer;I)J", "(Ljava/nio/ShortBuffer;)I", "(Ljava/nio/ShortBuffer;)J", "trimToOneLine", "(Ljava/lang/String;)Ljava/lang/String;", "Landroid/media/MediaCodec$BufferInfo;", "flag", "containFlag", "(Landroid/media/MediaCodec$BufferInfo;I)Z", "K", "", "Lkotlin/Function1;", "keySelector", "findMostFrequentOrNull", "(Ljava/util/List;LAe/b;)Ljava/lang/Object;", "Ljava/util/function/Consumer;", "", "block", "(Ljava/util/List;LAe/b;Ljava/util/function/Consumer;)Ljava/lang/Object;", "asString", "(Landroid/media/MediaCodec$BufferInfo;)Ljava/lang/String;", "Ljava/util/concurrent/FutureTask;", "Ljava/util/concurrent/Executor;", "executor", "Ljava/util/concurrent/CompletableFuture;", "toCompletableFuture", "(Ljava/util/concurrent/FutureTask;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;", "", "roundToNearestEven", "(F)I", "flags", "containsAnyFlag", "(I[I)Z", "containsAllFlag", "getSEPVersion", "()I", "lib", "loadALibrary", "(Ljava/lang/String;)V", "Ljava/nio/ByteBuffer;", "toBuffer", "([B)Ljava/nio/ByteBuffer;", "toHexString", "(Ljava/nio/ByteBuffer;I)Ljava/lang/String;", "", "predicate", "moveToLast", "(Ljava/util/List;LAe/b;)V", "moveToFirst", "isJUnit$delegate", "Lme/f;", "isJUnit", "()Z", "isAndroid$delegate", "isAndroid", "isAndroidTest$delegate", "isAndroidTest", "motionphoto_utils_v2.0.17_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CommonsKt {
    private static final f isAndroid$delegate = d.q(new C0721b(7));
    private static final f isAndroidTest$delegate = d.q(new C0721b(8));
    private static final f isJUnit$delegate = d.q(new C0721b(6));

    public static final int align(int i2, int i7) {
        return (((i7 - 1) + i2) / i7) * i7;
    }

    public static final String asString(MediaCodec.BufferInfo bufferInfo) {
        String str;
        String str2;
        j.e(bufferInfo, "<this>");
        int i2 = bufferInfo.size;
        int i7 = bufferInfo.offset;
        long j2 = bufferInfo.presentationTimeUs;
        int i8 = bufferInfo.flags;
        int i10 = c.f3817a;
        Tf.f fVar = Tf.f.d;
        j.e(fVar, "format");
        if (fVar.f3820a) {
            str = "0123456789ABCDEF";
        } else {
            str = "0123456789abcdef";
        }
        e eVar = fVar.f3821c;
        if (eVar.f3819a) {
            str2 = new String(new char[]{str.charAt((i8 >> 28) & 15), str.charAt((i8 >> 24) & 15), str.charAt((i8 >> 20) & 15), str.charAt((i8 >> 16) & 15), str.charAt((i8 >> 12) & 15), str.charAt((i8 >> 8) & 15), str.charAt((i8 >> 4) & 15), str.charAt(i8 & 15)});
        } else {
            str2 = c.b((long) i8, eVar, str, 32);
        }
        StringBuilder h5 = a.h(i2, i7, "size=", ", offset=", ", presentationTimeUs=");
        h5.append(j2);
        h5.append(", flags=");
        h5.append(str2);
        return h5.toString();
    }

    public static final boolean containFlag(MediaCodec.BufferInfo bufferInfo, int i2) {
        j.e(bufferInfo, "<this>");
        if ((bufferInfo.flags & i2) != 0) {
            return true;
        }
        return false;
    }

    public static final boolean contains(byte[] bArr, int... iArr) {
        j.e(bArr, "<this>");
        j.e(iArr, "args");
        int length = iArr.length;
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            if (getInt(bArr, i7) == iArr[i7]) {
                i2++;
            }
        }
        if (i2 == iArr.length) {
            return true;
        }
        return false;
    }

    public static final boolean containsAllFlag(int i2, int... iArr) {
        j.e(iArr, "flags");
        for (int i7 : iArr) {
            if ((i7 & i2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static final boolean containsAnyFlag(int i2, int... iArr) {
        j.e(iArr, "flags");
        for (int i7 : iArr) {
            if ((i7 & i2) != 0) {
                return true;
            }
        }
        return false;
    }

    public static final <T, K> K findMostFrequentOrNull(List<? extends T> list, b bVar) {
        Object obj;
        j.e(list, "<this>");
        j.e(bVar, "keySelector");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next : list) {
            Object invoke = bVar.invoke(next);
            Object obj2 = linkedHashMap.get(invoke);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(invoke, obj2);
            }
            ((List) obj2).add(next);
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        if (!it.hasNext()) {
            obj = null;
        } else {
            obj = it.next();
            if (it.hasNext()) {
                int size = ((List) ((Map.Entry) obj).getValue()).size();
                do {
                    Object next2 = it.next();
                    int size2 = ((List) ((Map.Entry) next2).getValue()).size();
                    if (size < size2) {
                        obj = next2;
                        size = size2;
                    }
                } while (it.hasNext());
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            return entry.getKey();
        }
        return null;
    }

    public static final <T> T firstByte(short s) {
        j.h();
        throw null;
    }

    public static final byte getByte(int i2, int i7) {
        if (i7 == 0) {
            i2 >>= 24;
        } else if (i7 == 1) {
            i2 >>= 16;
        } else if (i7 == 2) {
            i2 >>= 8;
        } else if (i7 != 3) {
            throw new IllegalArgumentException();
        }
        return (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public static final int getInt(byte[] bArr, int i2) {
        j.e(bArr, "<this>");
        return bArr[i2] & 255;
    }

    public static final long getLong(IntBuffer intBuffer, int i2) {
        j.e(intBuffer, "<this>");
        return ((long) intBuffer.get(i2)) & 4294967295L;
    }

    public static /* synthetic */ long getLong$default(IntBuffer intBuffer, int i2, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i2 = 0;
        }
        return getLong(intBuffer, i2);
    }

    public static final String getMPUtilsVersion() {
        return BuildConfig.MP_UTILS_VERSION;
    }

    public static final int getSEPVersion() {
        try {
            return SemSystemProperties.getInt("ro.build.version.sep", Build.VERSION.SEM_PLATFORM_INT);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static final boolean isAndroid() {
        return ((Boolean) isAndroid$delegate.getValue()).booleanValue();
    }

    public static final boolean isAndroidTest() {
        return ((Boolean) isAndroidTest$delegate.getValue()).booleanValue();
    }

    /* access modifiers changed from: private */
    public static final boolean isAndroidTest_delegate$lambda$39() {
        try {
            Class.forName("androidx.test.ext.junit.runners.AndroidJUnit4");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static final boolean isAndroid_delegate$lambda$38() {
        if (!Def.isAndroid() || Build.VERSION.BASE_OS == null) {
            return false;
        }
        return true;
    }

    public static final boolean isJUnit() {
        return ((Boolean) isJUnit$delegate.getValue()).booleanValue();
    }

    /* access modifiers changed from: private */
    public static final boolean isJUnit_delegate$lambda$37() {
        if (!Def.isJUnitTest()) {
            return false;
        }
        try {
            Class.forName("org.junit.jupiter.api.Test");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static final <T extends Number> T lastByte(short s) {
        j.h();
        throw null;
    }

    public static final void loadALibrary(String str) {
        j.e(str, "lib");
        if (!isAndroid() || (isJUnit() && !isAndroidTest())) {
            boolean isAndroid = isAndroid();
            boolean isJUnit = isJUnit();
            boolean isAndroidTest = isAndroidTest();
            SLog.w("Sum", "do not load native except android[isAndroid=" + isAndroid + ", isJUnit=" + isJUnit + ", isAndroidTest=" + isAndroidTest + "]");
            return;
        }
        System.loadLibrary(str);
    }

    public static final boolean matched(InputStream inputStream, int... iArr) {
        j.e(inputStream, "<this>");
        j.e(iArr, "args");
        byte[] bArr = new byte[iArr.length];
        inputStream.read(bArr, 0, iArr.length);
        return contains(bArr, Arrays.copyOf(iArr, iArr.length));
    }

    public static final <T> void moveToFirst(List<T> list, b bVar) {
        j.e(list, "<this>");
        j.e(bVar, "predicate");
        Iterator<T> it = list.iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (((Boolean) bVar.invoke(it.next())).booleanValue()) {
                break;
            } else {
                i2++;
            }
        }
        list.add(0, list.remove(i2));
    }

    public static final <T> void moveToLast(List<T> list, b bVar) {
        j.e(list, "<this>");
        j.e(bVar, "predicate");
        Iterator<T> it = list.iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (((Boolean) bVar.invoke(it.next())).booleanValue()) {
                break;
            } else {
                i2++;
            }
        }
        list.add(list.remove(i2));
    }

    public static final IntBuffer readAsIntBuffer(InputStream inputStream, int i2, ByteOrder byteOrder) {
        j.e(inputStream, "<this>");
        IntBuffer readAsIntBufferOrNull = readAsIntBufferOrNull(inputStream, i2, byteOrder);
        j.b(readAsIntBufferOrNull);
        return readAsIntBufferOrNull;
    }

    public static /* synthetic */ IntBuffer readAsIntBuffer$default(InputStream inputStream, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsIntBuffer(inputStream, i2, byteOrder);
    }

    public static final IntBuffer readAsIntBufferOrNull(InputStream inputStream, int i2, ByteOrder byteOrder) {
        j.e(inputStream, "<this>");
        int i7 = i2 * 4;
        byte[] bArr = new byte[i7];
        if (inputStream.read(bArr, 0, i7) < 0) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.rewind();
        if (byteOrder != null) {
            wrap.order(byteOrder);
        }
        return wrap.asIntBuffer();
    }

    public static /* synthetic */ IntBuffer readAsIntBufferOrNull$default(InputStream inputStream, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsIntBufferOrNull(inputStream, i2, byteOrder);
    }

    public static final LongBuffer readAsLongBuffer(InputStream inputStream, int i2, ByteOrder byteOrder) {
        j.e(inputStream, "<this>");
        LongBuffer readAsLongBufferOrNull = readAsLongBufferOrNull(inputStream, i2, byteOrder);
        j.b(readAsLongBufferOrNull);
        return readAsLongBufferOrNull;
    }

    public static /* synthetic */ LongBuffer readAsLongBuffer$default(InputStream inputStream, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsLongBuffer(inputStream, i2, byteOrder);
    }

    public static final LongBuffer readAsLongBufferOrNull(InputStream inputStream, int i2, ByteOrder byteOrder) {
        j.e(inputStream, "<this>");
        int i7 = i2 * 8;
        byte[] bArr = new byte[i7];
        if (inputStream.read(bArr, 0, i7) < 0) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.rewind();
        if (byteOrder != null) {
            wrap.order(byteOrder);
        }
        return wrap.asLongBuffer();
    }

    public static /* synthetic */ LongBuffer readAsLongBufferOrNull$default(InputStream inputStream, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsLongBufferOrNull(inputStream, i2, byteOrder);
    }

    public static final ShortBuffer readAsShortBuffer(InputStream inputStream, int i2, ByteOrder byteOrder) {
        j.e(inputStream, "<this>");
        ShortBuffer readAsShortBufferOrNull = readAsShortBufferOrNull(inputStream, i2, byteOrder);
        j.b(readAsShortBufferOrNull);
        return readAsShortBufferOrNull;
    }

    public static /* synthetic */ ShortBuffer readAsShortBuffer$default(InputStream inputStream, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsShortBuffer(inputStream, i2, byteOrder);
    }

    public static final ShortBuffer readAsShortBufferOrNull(InputStream inputStream, int i2, ByteOrder byteOrder) {
        j.e(inputStream, "<this>");
        int i7 = i2 * 2;
        byte[] bArr = new byte[i7];
        if (inputStream.read(bArr, 0, i7) < 0) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.rewind();
        if (byteOrder != null) {
            wrap.order(byteOrder);
        }
        return wrap.asShortBuffer();
    }

    public static /* synthetic */ ShortBuffer readAsShortBufferOrNull$default(InputStream inputStream, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsShortBufferOrNull(inputStream, i2, byteOrder);
    }

    public static final String readAsString(InputStream inputStream, int i2) {
        j.e(inputStream, "<this>");
        String readAsStringOrNull = readAsStringOrNull(inputStream, i2);
        j.b(readAsStringOrNull);
        return readAsStringOrNull;
    }

    public static final String readAsStringOrNull(InputStream inputStream, int i2) {
        j.e(inputStream, "<this>");
        byte[] bArr = new byte[i2];
        if (inputStream.read(bArr, 0, i2) < 0) {
            return null;
        }
        return new String(bArr, Tf.a.f3815a);
    }

    public static final int roundToNearestEven(float f) {
        int W6 = C0068a.W(f);
        if (W6 % 2 == 0) {
            return W6;
        }
        int i2 = W6 - 1;
        int i7 = W6 + 1;
        if (f - ((float) i2) <= ((float) i7) - f) {
            return i2;
        }
        return i7;
    }

    public static final void shiftLeftSafely(FileChannel fileChannel, int i2, long j2, boolean z) {
        j.e(fileChannel, "<this>");
        SLog.v("Sum", "shiftLeftSafely E: shift=" + i2 + ", bufferSize=" + j2);
        if (j2 > ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER) {
            try {
                ByteBuffer allocate = ByteBuffer.allocate((int) j2);
                long size = fileChannel.size() - fileChannel.position();
                long position = fileChannel.position();
                int i7 = 0;
                while (((long) i7) < size) {
                    fileChannel.position(position);
                    fileChannel.read(allocate);
                    allocate.flip();
                    long j3 = (long) i2;
                    long j8 = position - j3;
                    fileChannel.position(j8);
                    i7 += fileChannel.write(allocate);
                    position = j8 + j3 + j2;
                    allocate.clear();
                }
                SLog.v("Sum", "shiftLeftSafely X: targetBytes=" + size + ", writeBytes=" + i7);
            } catch (OutOfMemoryError unused) {
                shiftLeftSafely(fileChannel, i2, j2 / ((long) 2), true);
            }
            if (!z) {
                fileChannel.truncate(fileChannel.size() - ((long) i2));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("fail to shift: buffer-size is less than minimum value 1024 byte");
    }

    public static /* synthetic */ void shiftLeftSafely$default(FileChannel fileChannel, int i2, long j2, boolean z, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            j2 = fileChannel.size() - fileChannel.position();
        }
        if ((i7 & 4) != 0) {
            z = false;
        }
        shiftLeftSafely(fileChannel, i2, j2, z);
    }

    public static final void shiftRightSafely(FileChannel fileChannel, int i2, long j2) {
        j.e(fileChannel, "<this>");
        SLog.v("Sum", "shiftRightSafely E: shift=" + i2 + ", bufferSize=" + j2);
        if (j2 > ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER) {
            try {
                ByteBuffer allocate = ByteBuffer.allocate((int) j2);
                long size = fileChannel.size() - fileChannel.position();
                long position = fileChannel.position();
                long size2 = fileChannel.size() - j2;
                int i7 = 0;
                while (((long) i7) < size) {
                    fileChannel.position(size2);
                    fileChannel.read(allocate);
                    allocate.flip();
                    long j3 = (long) i2;
                    long j8 = size2 + j3;
                    fileChannel.position(j8);
                    i7 += fileChannel.write(allocate);
                    size2 = Math.max(position, j8 - (j3 + j2));
                    allocate.clear();
                }
                SLog.v("Sum", "shiftRightSafely X: targetBytes=" + size + ", writeBytes=" + i7);
            } catch (OutOfMemoryError unused) {
                shiftRightSafely(fileChannel, i2, j2 / ((long) 2));
            }
        } else {
            throw new IllegalArgumentException("fail to shift: buffer-size is less than minimum value 1024 byte");
        }
    }

    public static /* synthetic */ void shiftRightSafely$default(FileChannel fileChannel, int i2, long j2, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            j2 = fileChannel.size() - fileChannel.position();
        }
        shiftRightSafely(fileChannel, i2, j2);
    }

    public static final int shr(byte b, int i2) {
        return (b & 255) >> i2;
    }

    public static final void skipNBytes(FileChannel fileChannel, int i2) {
        j.e(fileChannel, "<this>");
        fileChannel.position(fileChannel.position() + ((long) i2));
    }

    public static final ByteBuffer toBuffer(byte[] bArr) {
        j.e(bArr, "<this>");
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        j.d(wrap, "wrap(...)");
        return wrap;
    }

    public static final byte[] toByteArray(short s, ByteOrder byteOrder) {
        byte[] array = ByteBuffer.allocate(2).putShort(s).array();
        j.d(array, "array(...)");
        return array;
    }

    public static /* synthetic */ byte[] toByteArray$default(short s, ByteOrder byteOrder, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            byteOrder = null;
        }
        return toByteArray(s, byteOrder);
    }

    public static final <T> CompletableFuture<T> toCompletableFuture(FutureTask<T> futureTask, Executor executor) {
        j.e(futureTask, "<this>");
        j.e(executor, "executor");
        CompletableFuture<T> supplyAsync = CompletableFuture.supplyAsync(new J5.c(13, futureTask), executor);
        j.d(supplyAsync, "supplyAsync(...)");
        return supplyAsync;
    }

    /* access modifiers changed from: private */
    public static final Object toCompletableFuture$lambda$34(FutureTask futureTask) {
        return futureTask.get();
    }

    public static final String toHexString(ByteBuffer byteBuffer, int i2) {
        j.e(byteBuffer, "<this>");
        ArrayList<Number> arrayList = new ArrayList<>();
        for (int i7 = 0; i7 < i2; i7++) {
            arrayList.add(Byte.valueOf(byteBuffer.get()));
        }
        byteBuffer.rewind();
        int size = arrayList.size();
        byte[] bArr = new byte[size];
        int i8 = 0;
        for (Number byteValue : arrayList) {
            bArr[i8] = byteValue.byteValue();
            i8++;
        }
        C0725a aVar = new C0725a(12);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("");
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            byte b = bArr[i11];
            i10++;
            if (i10 > 1) {
                sb2.append(" ");
            }
            sb2.append((CharSequence) aVar.invoke(Byte.valueOf(b)));
        }
        sb2.append("");
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public static final CharSequence toHexString$lambda$43$lambda$42(byte b) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
    }

    public static final int toInt(short s, int i2) {
        return s & i2;
    }

    public static final void transferAllTo(FileChannel fileChannel, long j2, long j3, FileChannel fileChannel2) {
        j.e(fileChannel, "<this>");
        j.e(fileChannel2, "target");
        long j8 = j2;
        while (true) {
            long j10 = j8 - j2;
            if (j10 < j3) {
                FileChannel fileChannel3 = fileChannel;
                FileChannel fileChannel4 = fileChannel2;
                long transferTo = fileChannel3.transferTo(j8, j3 - j10, fileChannel4);
                if (transferTo > 0) {
                    j8 += transferTo;
                    fileChannel = fileChannel3;
                    fileChannel2 = fileChannel4;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public static final String trimToOneLine(String str) {
        List list;
        j.e(str, "<this>");
        if (!n.C0("|")) {
            g gVar = new g(str);
            if (!gVar.hasNext()) {
                list = C1202t.d;
            } else {
                Object next = gVar.next();
                if (!gVar.hasNext()) {
                    list = C0246a.e0(next);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(next);
                    while (gVar.hasNext()) {
                        arrayList.add(gVar.next());
                    }
                    list = arrayList;
                }
            }
            int length = str.length();
            list.size();
            int p02 = C1195m.p0(list);
            ArrayList arrayList2 = new ArrayList();
            int i2 = 0;
            for (Object next2 : list) {
                int i7 = i2 + 1;
                String str2 = null;
                if (i2 >= 0) {
                    String str3 = (String) next2;
                    if (!(i2 == 0 || i2 == p02) || !n.C0(str3)) {
                        int length2 = str3.length();
                        int i8 = 0;
                        while (true) {
                            if (i8 >= length2) {
                                i8 = -1;
                                break;
                            } else if (!B1.a.I(str3.charAt(i8))) {
                                break;
                            } else {
                                i8++;
                            }
                        }
                        if (i8 != -1 && str3.startsWith("|", i8)) {
                            str2 = str3.substring(1 + i8);
                            j.d(str2, "substring(...)");
                        }
                        if (str2 == null) {
                            str2 = str3;
                        }
                    }
                    if (str2 != null) {
                        arrayList2.add(str2);
                    }
                    i2 = i7;
                } else {
                    C1195m.v0();
                    throw null;
                }
            }
            StringBuilder sb2 = new StringBuilder(length);
            C1194l.Q0(arrayList2, sb2, "\n", (String) null, (String) null, (b) null, 124);
            return v.s0(sb2.toString(), "\n", "");
        }
        throw new IllegalArgumentException("marginPrefix must be non-blank string.");
    }

    public static final <T> void write(FileChannel fileChannel, T... tArr) {
        j.e(fileChannel, "<this>");
        j.e(tArr, StateHandler.VALUES);
        j.h();
        throw null;
    }

    public static final <T> void writeAll(OutputStream outputStream, T... tArr) {
        j.e(outputStream, "<this>");
        j.e(tArr, StateHandler.VALUES);
        j.h();
        throw null;
    }

    public static final int getInt(ShortBuffer shortBuffer) {
        j.e(shortBuffer, "<this>");
        return shortBuffer.get() & 65535;
    }

    public static final long getLong(ShortBuffer shortBuffer) {
        j.e(shortBuffer, "<this>");
        return ((long) shortBuffer.get()) & 65535;
    }

    public static final IntBuffer readAsIntBuffer(FileChannel fileChannel, int i2, ByteOrder byteOrder) {
        j.e(fileChannel, "<this>");
        IntBuffer readAsIntBufferOrNull = readAsIntBufferOrNull(fileChannel, i2, byteOrder);
        j.b(readAsIntBufferOrNull);
        return readAsIntBufferOrNull;
    }

    public static /* synthetic */ IntBuffer readAsIntBuffer$default(FileChannel fileChannel, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsIntBuffer(fileChannel, i2, byteOrder);
    }

    public static /* synthetic */ IntBuffer readAsIntBufferOrNull$default(FileChannel fileChannel, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsIntBufferOrNull(fileChannel, i2, byteOrder);
    }

    public static final LongBuffer readAsLongBuffer(FileChannel fileChannel, int i2, ByteOrder byteOrder) {
        j.e(fileChannel, "<this>");
        LongBuffer readAsLongBufferOrNull = readAsLongBufferOrNull(fileChannel, i2, byteOrder);
        j.b(readAsLongBufferOrNull);
        return readAsLongBufferOrNull;
    }

    public static /* synthetic */ LongBuffer readAsLongBuffer$default(FileChannel fileChannel, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsLongBuffer(fileChannel, i2, byteOrder);
    }

    public static /* synthetic */ LongBuffer readAsLongBufferOrNull$default(FileChannel fileChannel, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsLongBufferOrNull(fileChannel, i2, byteOrder);
    }

    public static final ShortBuffer readAsShortBuffer(FileChannel fileChannel, int i2, ByteOrder byteOrder) {
        j.e(fileChannel, "<this>");
        ShortBuffer readAsShortBufferOrNull = readAsShortBufferOrNull(fileChannel, i2, byteOrder);
        j.b(readAsShortBufferOrNull);
        return readAsShortBufferOrNull;
    }

    public static /* synthetic */ ShortBuffer readAsShortBuffer$default(FileChannel fileChannel, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsShortBuffer(fileChannel, i2, byteOrder);
    }

    public static /* synthetic */ ShortBuffer readAsShortBufferOrNull$default(FileChannel fileChannel, int i2, ByteOrder byteOrder, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            byteOrder = null;
        }
        return readAsShortBufferOrNull(fileChannel, i2, byteOrder);
    }

    public static final String readAsString(FileChannel fileChannel, int i2) {
        j.e(fileChannel, "<this>");
        String readAsStringOrNull = readAsStringOrNull(fileChannel, i2);
        j.b(readAsStringOrNull);
        return readAsStringOrNull;
    }

    public static final int write(FileChannel fileChannel, byte b, int i2) {
        j.e(fileChannel, "<this>");
        byte[] bArr = new byte[i2];
        Arrays.fill(bArr, b);
        return fileChannel.write(ByteBuffer.wrap(bArr));
    }

    public static final boolean contains(byte[] bArr, byte... bArr2) {
        j.e(bArr, "<this>");
        j.e(bArr2, "args");
        int length = bArr2.length;
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            if (bArr[i7] == bArr2[i7]) {
                i2++;
            }
        }
        if (i2 == bArr2.length) {
            return true;
        }
        return false;
    }

    public static final boolean matched(FileChannel fileChannel, int... iArr) {
        j.e(fileChannel, "<this>");
        j.e(iArr, "args");
        ByteBuffer wrap = ByteBuffer.wrap(new byte[iArr.length]);
        fileChannel.read(wrap);
        byte[] array = wrap.array();
        j.d(array, "array(...)");
        return contains(array, Arrays.copyOf(iArr, iArr.length));
    }

    public static final String readAsStringOrNull(FileChannel fileChannel, int i2) {
        j.e(fileChannel, "<this>");
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        if (fileChannel.read(allocate) < 0) {
            return null;
        }
        allocate.rewind();
        byte[] array = allocate.array();
        j.d(array, "array(...)");
        return new String(array, Tf.a.f3815a);
    }

    public static final int write(FileChannel fileChannel, byte[] bArr) {
        j.e(fileChannel, "<this>");
        j.e(bArr, "array");
        return fileChannel.write(ByteBuffer.wrap(bArr));
    }

    public static final boolean matched(InputStream inputStream, byte... bArr) {
        j.e(inputStream, "<this>");
        j.e(bArr, "args");
        byte[] bArr2 = new byte[bArr.length];
        if (inputStream.read(bArr2, 0, bArr.length) == bArr.length) {
            return contains(bArr2, Arrays.copyOf(bArr, bArr.length));
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public static final IntBuffer readAsIntBufferOrNull(FileChannel fileChannel, int i2, ByteOrder byteOrder) {
        j.e(fileChannel, "<this>");
        ByteBuffer allocate = ByteBuffer.allocate(i2 * 4);
        if (fileChannel.read(allocate) < 0) {
            return null;
        }
        allocate.rewind();
        if (byteOrder != null) {
            allocate.order(byteOrder);
        }
        return allocate.asIntBuffer();
    }

    public static final LongBuffer readAsLongBufferOrNull(FileChannel fileChannel, int i2, ByteOrder byteOrder) {
        j.e(fileChannel, "<this>");
        ByteBuffer allocate = ByteBuffer.allocate(i2 * 8);
        if (fileChannel.read(allocate) < 0) {
            return null;
        }
        allocate.rewind();
        if (byteOrder != null) {
            allocate.order(byteOrder);
        }
        return allocate.asLongBuffer();
    }

    public static final ShortBuffer readAsShortBufferOrNull(FileChannel fileChannel, int i2, ByteOrder byteOrder) {
        j.e(fileChannel, "<this>");
        ByteBuffer allocate = ByteBuffer.allocate(i2 * 2);
        if (fileChannel.read(allocate) < 0) {
            return null;
        }
        allocate.rewind();
        if (byteOrder != null) {
            allocate.order(byteOrder);
        }
        return allocate.asShortBuffer();
    }

    public static final <T, K> K findMostFrequentOrNull(List<? extends T> list, b bVar, Consumer<Map<K, List<T>>> consumer) {
        Object obj;
        j.e(list, "<this>");
        j.e(bVar, "keySelector");
        j.e(consumer, "block");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object next : list) {
            Object invoke = bVar.invoke(next);
            Object obj2 = linkedHashMap.get(invoke);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(invoke, obj2);
            }
            ((List) obj2).add(next);
        }
        consumer.accept(linkedHashMap);
        Iterator it = linkedHashMap.entrySet().iterator();
        if (!it.hasNext()) {
            obj = null;
        } else {
            obj = it.next();
            if (it.hasNext()) {
                int size = ((List) ((Map.Entry) obj).getValue()).size();
                do {
                    Object next2 = it.next();
                    int size2 = ((List) ((Map.Entry) next2).getValue()).size();
                    if (size < size2) {
                        obj = next2;
                        size = size2;
                    }
                } while (it.hasNext());
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            return entry.getKey();
        }
        return null;
    }

    public static final boolean matched(FileChannel fileChannel, byte... bArr) {
        j.e(fileChannel, "<this>");
        j.e(bArr, "args");
        ByteBuffer wrap = ByteBuffer.wrap(new byte[bArr.length]);
        if (fileChannel.read(wrap) == bArr.length) {
            byte[] array = wrap.array();
            j.d(array, "array(...)");
            return contains(array, Arrays.copyOf(bArr, bArr.length));
        }
        throw new IllegalArgumentException("Failed requirement.");
    }
}
