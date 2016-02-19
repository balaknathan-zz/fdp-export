package com.flipkart.fdp.export.cli;

import com.flipkart.fdp.ddf.models.action.Feed;

import java.util.List;

/**
 * Created by arjun.r on 19/02/16.
 */
public interface DataStore {

    long getData(List<Feed> partitionsFeed);
}
