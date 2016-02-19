package com.flipkart.fdp.export.cli;

/**
 * Created by arjun.r on 18/02/16.
 */
public class Main {


    private static final String DDF_URI ="fdp:/ddf/test_org/test_namespace/test_table/HDFS/1";

    public static void main(String[] args){

        //Get input Uri
        String ddfUri = args[0];

        //export data
        Exporter exporter = new Exporter();
        exporter.export(DDF_URI);
    }
}
