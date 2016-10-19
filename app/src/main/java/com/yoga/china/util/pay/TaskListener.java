package com.yoga.china.util.pay;

public interface TaskListener {
	public void taskStarted(Task<?> task);

	public void taskCompleted(Task<?> task, Object obj);

	public void taskFailed(Task<?> task, boolean netWorkState, boolean cancelNetWork, Throwable ex);
}
