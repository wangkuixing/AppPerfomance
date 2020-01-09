package GetResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import Common.Commons;
import Common.Commons;

public class GetMemory {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(talHeapSize("com.ytsc"));
        }
    }

    /**
     * test
     *
     * @param PackageName
     * @return
     * @throws IOException
     */
    public static double talHeapSize(String PackageName) throws IOException {
        Commons commons = Commons.load("/globalConfig.yaml");
        String devices = commons.mconfig.phoneid;
        double Heap = 0;
        String heapStr = "0";
        try {
            Runtime runtime = Runtime.getRuntime();

            String[] winCmd = new String[]{"cmd.exe", "/C", "adb -s "+ devices+" shell top -n 1| findstr "+ PackageName};
            String macCmd = "adb -s "+ devices+" shell top -n 1| grep "+ PackageName;
            Process proc;
            String osname=System.getProperty("os.name");
            if (osname.startsWith("Windows")){
                proc = runtime.exec(winCmd);
            } else {
                proc = runtime.exec(macCmd);
            }

            try {
                if (proc.waitFor() != 0) {
                    System.err.println("exit value = " + proc.exitValue());
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    if (line.contains(PackageName) && line.contains("/") == false) {
                        List<String> strList = Arrays.asList(line.split("\\s+"));
                        heapStr = strList.get(6).trim();
                        if (heapStr.contains("M")) {
                            heapStr = strList.get(6).trim().replace("M", "");
                            break;
                        }
                    }
                }
                Heap = Double.parseDouble(heapStr);
            } catch (InterruptedException e) {
                System.err.println(e);
            } finally {
                try {
                    proc.destroy();
                } catch (Exception e2) {
                }
            }
        } catch (Exception StringIndexOutOfBoundsException) {
            System.out.print("请检查设备是否连接 内存 ");
            StringIndexOutOfBoundsException.printStackTrace();
            return (-0.1);
        }
        return Commons.streamDouble(Heap);
    }

}
