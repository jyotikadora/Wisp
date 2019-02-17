package com.coreoz.wisp;

import java.time.Instant;

import com.coreoz.wisp.schedule.Schedule;

/**
 * A {@code Job} is the association of a {@link Runnable} process
 * and its running {@link Schedule}.<br/>
 * <br/>
 * A {@code Job} also contains information about its status and its running
 * statistics.
 */
public class Job {

	private JobStatus status;
	private volatile long nextExecutionTimeInMillis;
	private volatile int executionsCount;
	private Long lastExecutionTimeInMillis;
	private Long timeInMillisSinceJobRunning;
	private Thread threadRunningJob;
	private final String name;
	private Schedule schedule;
	private final Runnable runnable;
	private Runnable runningJob;

	// public API

	public JobStatus status() {
		return status;
	}

	public long nextExecutionTimeInMillis() {
		return nextExecutionTimeInMillis;
	}

	public int executionsCount() {
		return executionsCount;
	}

	public Long lastExecutionTimeInMillis() {
		return lastExecutionTimeInMillis;
	}

	/**
	 * The time of the start of the job execution.
	 *
	 * Not null only when {@code #status() == JobStatus.RUNNING}.
	 */
	public Long timeInMillisSinceJobRunning() {
		return timeInMillisSinceJobRunning;
	}

	public Thread threadRunningJob() {
		return threadRunningJob;
	}

	public String name() {
		return name;
	}

	public Schedule schedule() {
		return schedule;
	}

	public Runnable runnable() {
		return runnable;
	}

	// package API

	Job(JobStatus status, long nextExecutionTimeInMillis, int executionsCount,
			Long lastExecutionTimeInMillis, String name, Schedule schedule, Runnable runnable) {
		this.status = status;
		this.nextExecutionTimeInMillis = nextExecutionTimeInMillis;
		this.executionsCount = executionsCount;
		this.lastExecutionTimeInMillis = lastExecutionTimeInMillis;
		this.name = name;
		this.schedule = schedule;
		this.runnable = runnable;
	}

	void status(JobStatus status) {
		this.status = status;
	}

	void nextExecutionTimeInMillis(long nextExecutionTimeInMillis) {
		this.nextExecutionTimeInMillis = nextExecutionTimeInMillis;
	}

	void executionsCount(int executionsCount) {
		this.executionsCount = executionsCount;
	}

	void lastExecutionTimeInMillis(Long lastExecutionTimeInMillis) {
		this.lastExecutionTimeInMillis = lastExecutionTimeInMillis;
	}

	void timeInMillisSinceJobRunning(Long timeInMillisSinceJobRunning) {
		this.timeInMillisSinceJobRunning = timeInMillisSinceJobRunning;
	}

	void threadRunningJob(Thread threadRunningJob) {
		this.threadRunningJob = threadRunningJob;
	}

	void schedule(Schedule schedule) {
		this.schedule = schedule;
	}

	void runningJob(Runnable runningJob) {
		this.runningJob = runningJob;
	}

	Runnable runningJob() {
		return runningJob;
	}

	// toString

	@Override
	public String toString() {
		return "Job " + name + " [" + status + "] - will run " + schedule
				+ " - next execution at " + Instant.ofEpochMilli(nextExecutionTimeInMillis);
	}

}
