package com.jaemzware;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> logLines = ReadTerminalLogFile("logdata20121229.txt");
        List<String> logLinesSummarized = SumPacketUsagePerIpAddress(logLines);
        WriteHtmlLogFile(logLinesSummarized);
    }

    public static List<String> ReadTerminalLogFile(String fileName) {
        List<String> linesOfLogData = new ArrayList<String>();
        //...write lines of logfile into a String List...
        linesOfLogData.add("2012-12-29 22:00 172.16.8.48 drops 24 packets");
        linesOfLogData.add("2012-12-29 22:01 172.16.8.48 buffer full");
        linesOfLogData.add("2012-12-29 22:02 172.16.8.45 drops 21 packets");
        linesOfLogData.add("2012-12-29 22:03 172.16.8.44 drops 10 packets");
        linesOfLogData.add("2012-12-29 22:04 172.16.8.48 drops 10 packets");
        linesOfLogData.add("2012-12-29 22:04 172.16.8.48 latency 3 seconds");
        linesOfLogData.add("2012-12-29 22:03 172.16.8.45 drops 2 packets");
        return linesOfLogData;
    }

    public static void WriteHtmlLogFile(List<String> logData) {
        //...put all List Strings in a StringBuilder with HTML formatting and write it to an .htm file...
        for(int i=logData.size()-1;i>=0;i--){
            System.out.println(logData.get(i));
        }
    }

    public static List<String> SumPacketUsagePerIpAddress(List<String> logData){
        Hashtable<String,Integer> summationMap = new Hashtable<String, Integer>();
        List<String> summationDataList = new ArrayList<String>();

        for(int i=0;i<logData.size();i++){
            String[] logDataArray = logData.get(i).split(" ");
            if(logDataArray[3].equals("drops")){
                String ipAddress = logDataArray[2];
                String bytesDropped = logDataArray[4];
                if(summationMap.containsKey(ipAddress)){
                    summationMap.put(ipAddress,summationMap.get(ipAddress)+Integer.parseInt(bytesDropped));
                }
                else{
                    summationMap.put(ipAddress,Integer.parseInt(bytesDropped));
                }
            }
        }

        for(String key:summationMap.keySet()){
            String ip = key;
            String sum = Integer.toString(summationMap.get(key));

            summationDataList.add(ip + " drops total " + sum + " packets");
        }
        return summationDataList;
    }
}
