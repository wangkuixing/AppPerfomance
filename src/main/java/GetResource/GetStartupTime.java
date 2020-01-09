package GetResource;

import Common.Commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class GetStartupTime {

    public static void main(String[] args){
        for (int i = 0; i < 5; i++) {
            System.out.println(getTime("com.ytsc", "com.zztzt.android.simple.activity.tztCommHeadPageActivity"));
        }
    }

    public static int getTime(String packageName, String activityName){
        Commons commons = Commons.load("/globalConfig.yaml");
        String devices = commons.mconfig.phoneid;
        int coldTime = 0;
        String timeStr = "0";
        Process proc;

        String macCmd = "adb -s " + devices + " shell am start -S -W "+ packageName + "/" + activityName;
        String cmd[] = new String[]{"cmd.exe", "/C", "adb shell am force-stop com.ytsc", "adb -s "+ devices+ "shell am start -S -W "+ packageName + "/" +activityName };
        Runtime runtime = Runtime.getRuntime();
        try {
            proc = runtime.exec(macCmd);

            try {
                if (proc.waitFor() != 0){
                    System.err.println("exit value = " + proc.exitValue());
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    if (line.contains("WaitTime")) {
                        timeStr = line.substring(10);
                    }
                }
                coldTime = Integer.parseInt(timeStr);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coldTime;
    }
}
