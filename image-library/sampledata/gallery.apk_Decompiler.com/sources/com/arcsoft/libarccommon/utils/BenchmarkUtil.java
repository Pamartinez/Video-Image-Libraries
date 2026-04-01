package com.arcsoft.libarccommon.utils;

import java.util.LinkedHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BenchmarkUtil {
    private static final String TAG = "ArcSoft_BenchmarkUtil";
    private static boolean m_BenchMarkEnabled = false;
    private static LinkedHashMap<String, BenchmarkItem> m_BenchmarkMap = new LinkedHashMap<>();
    private static int m_Count = 0;
    private static LinkedHashMap<String, Long> m_StartTimeMap = new LinkedHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BenchmarkItem {
        public int Count = 0;
        public long lMaxCost = 0;
        public long lMinCost = 0;
        public long lTotalTime = 0;
        public String sItemName = null;
    }

    private static void benchmark_ending() {
        ArcCommonLog.d(TAG, "=====================================================================");
    }

    private static void benchmark_print(BenchmarkItem benchmarkItem) {
        ArcCommonLog.d(TAG, "[TOTAL STATISTICS] Name = " + benchmarkItem.sItemName + ", Count = " + benchmarkItem.Count + ", Total Time = " + benchmarkItem.lTotalTime + ", MaxCost = " + benchmarkItem.lMaxCost + ", MinCost = " + benchmarkItem.lMinCost + ", Avg = " + (((float) benchmarkItem.lTotalTime) / ((float) benchmarkItem.Count)) + ";");
    }

    private static void benchmark_title() {
        String str = TAG;
        ArcCommonLog.d(str, "=====================================================================");
        ArcCommonLog.d(str, "====              App Benchmark of ArcSoft                       ====");
        ArcCommonLog.d(str, "=====================================================================");
    }

    public static synchronized void close() {
        synchronized (BenchmarkUtil.class) {
            try {
                int i2 = m_Count;
                if (i2 > 1) {
                    m_Count = i2 - 1;
                } else {
                    summaryBenchMark();
                    m_StartTimeMap.clear();
                    m_BenchmarkMap.clear();
                    m_BenchMarkEnabled = false;
                    m_Count = 0;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static synchronized void open() {
        synchronized (BenchmarkUtil.class) {
            try {
                if (!m_BenchMarkEnabled) {
                    m_BenchMarkEnabled = true;
                    m_StartTimeMap.clear();
                    m_BenchmarkMap.clear();
                }
                m_Count++;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public static synchronized void start(String str) {
        synchronized (BenchmarkUtil.class) {
            if (m_BenchMarkEnabled) {
                if (str != null) {
                    m_StartTimeMap.put(str + "_" + Thread.currentThread().getId(), Long.valueOf(System.currentTimeMillis()));
                }
            }
        }
    }

    public static synchronized void stop(String str) {
        synchronized (BenchmarkUtil.class) {
            try {
                if (m_BenchMarkEnabled) {
                    if (str != null) {
                        Long remove = m_StartTimeMap.remove(str + "_" + Thread.currentThread().getId());
                        if (remove != null) {
                            long currentTimeMillis = System.currentTimeMillis() - remove.longValue();
                            ArcCommonLog.d(TAG, "benckmark name: " + str + " cost time = " + currentTimeMillis + " ms");
                            BenchmarkItem benchmarkItem = m_BenchmarkMap.get(str);
                            if (benchmarkItem == null) {
                                benchmarkItem = new BenchmarkItem();
                                benchmarkItem.lMaxCost = currentTimeMillis;
                                benchmarkItem.lMinCost = currentTimeMillis;
                                benchmarkItem.sItemName = str;
                                m_BenchmarkMap.put(str, benchmarkItem);
                            }
                            if (benchmarkItem.lMaxCost < currentTimeMillis) {
                                benchmarkItem.lMaxCost = currentTimeMillis;
                            }
                            if (benchmarkItem.lMinCost > currentTimeMillis) {
                                benchmarkItem.lMinCost = currentTimeMillis;
                            }
                            benchmarkItem.lTotalTime += currentTimeMillis;
                            benchmarkItem.Count++;
                        }
                    }
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private static void summaryBenchMark() {
        if (m_BenchMarkEnabled) {
            benchmark_title();
            for (String str : m_BenchmarkMap.keySet()) {
                benchmark_print(m_BenchmarkMap.get(str));
            }
            benchmark_ending();
        }
    }
}
