package GetResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Common.Commons;
import Common.Commons;

public class GetFps {
    private static int WAITTIME = 1600;
    //采样频率，单位ms
    private static float countTime = 0;
    //清空之前采样的数据，防止统计重复的时间
    private static String clearCommand = "adb shell dumpsys SurfaceFlinger --latency-clear";
    public static long JumpingFrames = 0;
    public static long TalFrames = 0;
    public static double frameRate = 0.0;
    public static ArrayList<Float> fps;
    public static ArrayList<Double> frameRateList;

    public static void main(String[] args) throws InterruptedException, ArrayIndexOutOfBoundsException {
        String result = "com.ytsc";
        System.out.println(fps(result));
        System.out.println(fps.toString());

    }

    public static double[] fps(String packages) throws InterruptedException, ArrayIndexOutOfBoundsException {
        Commons commons = Commons.load("/globalConfig.yaml");
        String devices = commons.mconfig.phoneid;
        String[] winCmd = new String[]{"cmd.exe", "/C", "adb -s "+ devices+" shell dumpsys gfxinfo "+packages};
        String gfxCMD = "adb -s " + devices + " shell dumpsys gfxinfo " + packages;
        double[] result = new double[2];
        try {
            fps = new ArrayList();
            frameRateList = new ArrayList();
            Runtime.getRuntime().exec(clearCommand);
            String osname=System.getProperty("os.name");
            result = getFps(gfxCMD, winCmd);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

    public static double[] getFps(String gfxCMD, String[] winCmd) {
        double[] result = new double[2];
        float ffps = 0;
        BufferedReader br = null, br2 = null, br3 = null;
        try {
            Process prt;
            String osname=System.getProperty("os.name");
            if (osname.startsWith("Windows")){
                prt = Runtime.getRuntime().exec(winCmd);
            } else {
                prt = Runtime.getRuntime().exec(gfxCMD);
            }
            br3 = new BufferedReader(new InputStreamReader(prt.getInputStream()));
            String line;
            boolean flag = false;
            int frames2 = 0, jankCount = 0, vsync_overtime = 0;
            while ((line = br3.readLine()) != null) {
                if (line.length() > 0) {
                    if (line.contains("Execute")) {
                        flag = true;
                        continue;
                    }
                    if (line.contains("View hierarchy:")) {
                        flag = false;
                        continue;
                    }
                    if (flag) {
                        if (!line.contains(":") && !line.contains("@")) {
                            String[] timeArray = line.trim().split("\t");
                            float oncetime = Float.parseFloat(timeArray[0]) + Float.parseFloat(timeArray[1])
                                + Float.parseFloat(timeArray[2]) + Float.parseFloat(timeArray[3]);
                            frames2 += 1;
                            countTime = countTime + oncetime;
                            if (oncetime > 19.67) {
                                jankCount += 1;
                                if (oncetime % 19.67 == 0) {
                                    vsync_overtime += oncetime / 19.67 - 1;
                                } else {
                                    vsync_overtime += oncetime / 19.67;
                                }
                            }
                        }
                    }

                }
            }
            if ((frames2 + vsync_overtime) > 0) {
                //fps>60也输出60
                ffps = frames2 * 60 / (frames2 + vsync_overtime);
                JumpingFrames += jankCount;
                TalFrames += frames2;
                fps.add(ffps);
                double psl = ((double)JumpingFrames / (double)TalFrames);
                frameRate = psl;
                frameRateList.add(frameRate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    Runtime.getRuntime().exec(clearCommand);
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        result[0] = ffps;
        result[1] = Commons.streamDouble(frameRate * 100);
        return result;
    }

    /**
     * 求平均值
     *
     * @param list
     * @return
     */
    public static int getAvarage(ArrayList<Float> list) {
        Float fps = 0f;
        for (int i = 0; i < list.size(); i++) {
            fps += list.get(i);
        }
        return (int)(fps / list.size());
    }

}

