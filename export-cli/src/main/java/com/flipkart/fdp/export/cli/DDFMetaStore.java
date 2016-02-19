package com.flipkart.fdp.export.cli;

import com.flipkart.fdp.ddf.client.DDFRestClient;
import com.flipkart.fdp.ddf.models.action.Feed;
import com.flipkart.fdp.ddf.models.data.PartitionUri;
import com.flipkart.fdp.ddf.models.data.SDUri;

import java.util.List;

/**
 * Created by arjun.r on 18/02/16.
 */
public class DDFMetaStore implements MetaStore{

    public List<Feed> getPartitionFeed(String ddfUri, long last_sequence_id){

        DDFRestClient restClient = new DDFRestClient("10.33.25.203", 18080);

        String inputUri = "";
        SDUri sdUri = new SDUri(ddfUri);
        List<Feed> partitionFeeds = restClient.partitionFeed(last_sequence_id, sdUri);
        return partitionFeeds;
    }
}
