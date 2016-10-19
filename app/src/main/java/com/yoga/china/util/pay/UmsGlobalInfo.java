package com.yoga.china.util.pay;

public class UmsGlobalInfo {

	// 网络时间
	public static String netTimestamp = "";
	public static String netTimestampForImg = "";
	/** 会话ID */
	public static String sessionID = "";
	// 网络状态
	public static int netStatus = 1;
	// 当前联网标志
	public static byte netWorkClass = 0;
	// 任务线程
	public static TaskExecutor taskExecutor;
	// 网络超时
	public static int netTimeOut = 20000;
	// 网络返回结果
	public static String netResult;
	public static String netResultForImg;
	// 匿名线程是否取消联网
	public static boolean isCancelThread;
	// 当前联网线程
	public static HttpTask currentHttpTask;
}
