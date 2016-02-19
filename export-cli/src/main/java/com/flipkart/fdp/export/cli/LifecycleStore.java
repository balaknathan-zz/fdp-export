package com.flipkart.fdp.export.cli;

/**
 * Created by arjun.r on 19/02/16.
 */
public interface LifecycleStore {

    public long getLastSequenceId(String appkey);
    public void setLastSequenceId(String appkey, long lastSequenceId);
    public String getAppKey();

    }
