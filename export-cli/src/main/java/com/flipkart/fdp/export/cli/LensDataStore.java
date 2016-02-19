package com.flipkart.fdp.export.cli;

import com.flipkart.fdp.ddf.models.action.Feed;
import com.flipkart.fdp.ddf.models.data.PartitionUri;
import com.flipkart.fdp.ddf.models.data.SDUri;

import java.util.List;
import java.util.Map;

/**
 * Created by arjun.r on 18/02/16.
 */
public class LensDataStore  implements DataStore{

    public long getData(List<Feed> partitionFeeds) {

        long nextSequenceId = 1;

        //construct query
        for (Feed feed : partitionFeeds) {
            String partitionUri = feed.getResourceUri();
            PartitionUri uri = new PartitionUri(partitionUri);
            Map<String, String> partitionKeyValues = uri.getPartitionKeyValues();
            SDUri sdUri = uri.getSDUri();
            System.out.println(generateQuery(uri));
            long curSequenceId =feed.getSequenceId();
            if (nextSequenceId < curSequenceId){
                nextSequenceId = curSequenceId;
            }
        }


        return nextSequenceId;
    }

    private String generateQuery(PartitionUri partitionUri ){
        StringBuffer curQuery = new StringBuffer("Select * from ");
        Map<String, String> partitionKeyValues = partitionUri.getPartitionKeyValues();
        SDUri sdUri = partitionUri.getSDUri();

        curQuery.append("test_bigfoot_external_neo.");
        curQuery.append(sdUri.getName());

        curQuery.append(" where ");

        boolean first=true;

        for(Map.Entry<String,String> entry : partitionKeyValues.entrySet()){
            if(!first){
                curQuery.append(" AND ");
            }
            first = false;
            String partitionKey = entry.getKey();
            String partitonVal = entry.getValue();
            curQuery.append(partitionKey);
            curQuery.append("=");
            curQuery.append(partitonVal);
        }
        return curQuery.toString();
    }
}
