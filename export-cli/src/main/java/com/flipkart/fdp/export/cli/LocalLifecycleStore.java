package com.flipkart.fdp.export.cli;

import java.io.*;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arjun.r on 18/02/16.
 */
class LocalLifecycleStore implements LifecycleStore{

    Map<String,Long> appkeyToSequenceIdMap ;

    LocalLifecycleStore(){
        appkeyToSequenceIdMap = new HashMap<String, Long>();
        init();
    }

    void init(){
        String hostname=null;
        String username=null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            username = System.getProperty("user.name");
        } catch(Exception e){

        }

        String appkey = hostname+username;

        readFromFile();

        if(!appkeyToSequenceIdMap.containsKey(appkey))
        appkeyToSequenceIdMap.put(appkey, 0l);

    }

    public long getLastSequenceId(String appkey){

        return appkeyToSequenceIdMap.get(appkey);
    }

    public String getAppKey(){
        String hostname=null;
        String username=null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            username = System.getProperty("user.name");
        } catch(Exception e){

        }

        return hostname+username;

    }

    public void setLastSequenceId(String appkey, long lastSequenceId){
        appkeyToSequenceIdMap.put(appkey, lastSequenceId);
        writeToFile();
    }

    private void readFromFile(){


        try {
            BufferedReader br = new BufferedReader(new FileReader("/tmp/lifecycle.txt")) ;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                String key = values[0];
                Long value = Long.parseLong(values[1]);
                appkeyToSequenceIdMap.put(key,value);

            }
        } catch(IOException e) {

        }
    }

    private void writeToFile(){
        try {
            PrintWriter writer = new PrintWriter("/tmp/lifecycle.txt", "UTF-8");

            for(Map.Entry<String, Long> entry : appkeyToSequenceIdMap.entrySet() ) {
                writer.print(entry.getKey()+" "+entry.getValue());
            }

            writer.close();
        }catch (IOException e){

        }
    }
}
