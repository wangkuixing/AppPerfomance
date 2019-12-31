package GetResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import Common.Commons;
import performance.Main;

public class GetTop {
	private static  double Cpu = 0;

	public static void main( String[] args ) throws IOException{
		for ( int i = 0; i < 10; i++ ){
			System.out.println( " Cpu：" + topCpu( "com.ss.android.ugc.aweme" ) );
		}
	}

	  public static double topCpu(String packageName) throws IOException {
		  String cpuStr="0";
		  try{
		  	Runtime runtime = Runtime.getRuntime();
		    System.out.println(runtime);
		    String[] cmd = new String[]{"cmd.exe", "/C", "adb -s "+Main.devices+" shell top -n 1| findstr "+packageName};
		    Process proc = runtime.exec(cmd);
		    try {
		        if (proc.waitFor() != 0) {
		            System.err.println("exit value = " + proc.exitValue());
		        }
		        BufferedReader in = new BufferedReader(new InputStreamReader( proc.getInputStream()));
		        String line = null;
		        while ((line = in.readLine()) != null) {
					if(line.contains(packageName)&&line.contains("/")==false){
						List<String> strList = Arrays.asList(line.split("\\s+"));
						cpuStr = strList.get(9).trim();
						break;
		        	}
		        }
		    	Cpu=Double.parseDouble( cpuStr );
		    } catch (InterruptedException e) {
		        System.err.println(e);
		    }finally{
		        try {
		            proc.destroy();
		        } catch (Exception e2) {
		        }
		    }}
			catch ( Exception StringIndexOutOfBoundsException ){
				System.out.print( "请检查设备是否连接TopCpu" );
				return -0.1;
			}
			return Commons.streamDouble(Cpu);
	  }




}


