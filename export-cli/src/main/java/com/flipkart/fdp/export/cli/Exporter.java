package com.flipkart.fdp.export.cli;
import com.flipkart.fdp.ddf.client.DDFRestClient;
import com.flipkart.fdp.ddf.models.action.Feed;
import com.flipkart.fdp.ddf.models.data.DDFUri;
import com.flipkart.fdp.ddf.models.data.PartitionUri;
import com.flipkart.fdp.ddf.models.utils.DDFUtils;
import com.flipkart.fdp.ddf.models.data.SDUri;
import java.util.List;
import java.util.Map;

/**
 *   Created by arjun.r on 17/02/16.
 */


public class Exporter {



    public void export(String ddfUri) {

        //Get LastSequenceNumber
        LifecycleStore lcStore = new LocalLifecycleStore();
        String appKey = lcStore.getAppKey();
        long lastSequenceId = lcStore.getLastSequenceId(appKey);
        long nextSequenceId = lastSequenceId;

        //Get metadata
        MetaStore metastore = new DDFMetaStore();
        List<Feed> partitionFeeds =metastore.getPartitionFeed(ddfUri, lastSequenceId+1);

        //Get data
        DataStore ds = new LensDataStore();
        long curSequenceId = ds.getData(partitionFeeds);

        if(nextSequenceId < curSequenceId ) {
            nextSequenceId = curSequenceId;
        }

        //Persist/Stream results

        //Update lifecycle
        lcStore.setLastSequenceId(appKey,nextSequenceId);

    }


}

