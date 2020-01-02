package testCase;

import Common.Commons;
import Common.Log;
import net.sf.json.JSONObject;
import org.testng.annotations.Test;

/**
 * Created by wangkx on 2019/12/25.
 */
public class test {
    @Test
    public void test1() throws Exception {

        JSONObject searchBug= Commons.searchBug( "FM97uf9gQS0=", "test", 0);
        Log.logs("response："+searchBug.toString());
        if(searchBug.get("data")!=null){
            Log.logs("用例执行成功");
        }
    }
}
