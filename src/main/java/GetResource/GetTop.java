package GetResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import Common.Commons;
import Common.Commons;

public class GetTop {
	private static  double Cpu = 0;

	public static void main( String[] args ) throws IOException{
		for ( int i = 0; i < 10; i++ ){
			System.out.println( " Cpu：" + topCpu( "com.ytsc" ) );
		}
	}

	  public static double topCpu(String packageName) throws IOException {
          Commons commons = Commons.load("/globalConfig.yaml");
		  String devices = commons.mconfig.phoneid;
		  String cpuStr="0";
		  try{
		  	Runtime runtime = Runtime.getRuntime();
		    System.out.println(runtime);
		    String[] winCmd = new String[]{"cmd.exe", "/C", "adb -s "+ devices+" shell top -n 1| findstr "+packageName};
		    String macCmd = "adb -s "+ devices+" shell top -n 1| grep "+packageName;
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


