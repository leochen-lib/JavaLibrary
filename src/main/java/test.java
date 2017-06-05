
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jlib.Constants.dbDriverMySQL;
import jlib.db.DBFunction;
import jlib.tool.AppProp;
import jlib.tool.Debug;
import jlib.util.ALHM;
import jlib.util.HM;
import jlib.tool.Print;
import static jlib.tool.TooletStatic.list2json;
import org.json.JSONArray;
import static jlib.tool.TooletStatic.list2json;
import static jlib.tool.TooletStatic.list2json;
import static jlib.tool.TooletStatic.list2json;

public class test {
    protected static Debug de = new Debug(true);
    
    public static final String dbconn = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8";
    public static final String dbaccount = "test";
    public static final String dbpwd = "test";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] nums = {3,3};
        int[] result = twoSum(nums , 6);
        System.out.println(result[0] + ", "+result[1]);
//        testDBFSelect();
//        testDBFUpdate();
//        testType(new ALHM());
//        testProp();
    }
    
    static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for( int i=0 ; i<nums.length ; i++){
            for( int j=0 ; j<nums.length ; j++ ){
                if ( nums[i]+nums[j] == target ){
                    System.out.println(i+";"+j);
                    System.out.println(i+";"+j);
                    result[0] = i;
                    result[1] = j;
                }else{
                    result[0] = -1;
                    result[1] = -1;
                }
            }
        }
        return result;
    }
    
    static void testProp(){
        AppProp app = new AppProp("/Volumes/SD_120/Data/work/project.self/JavaLibrary/src/main/java/properties/", "app.properties");
        String env = app.getValue("env.filter");
        de.println(env);
    }
    
    static void testDBFUpdate(){
        DBFunction dbf = new DBFunction(dbDriverMySQL, dbconn, dbaccount, dbpwd);
        try {
            dbf.connect(true);
//            ALHM result = dbf.setALHM("INSERT INTO `location` (`account_id`, `location`, `info`) VALUES (?, ?, ?)", "1", "SHA", "Working");
//            ALHM result = dbf.setALHM("UPDATE `location` SET `location`=? WHERE `id`=?", "Tokyo", 3);
//            ALHM result = dbf.setALHM("DELETE FROM `location` WHERE `id`=?", 5);
//            JSONArray result = dbf.setJson("INSERT INTO `location` (`account_id`, `location`, `info`) VALUES (?, ?, ?)", "1", "SHA", "Working");
            JSONArray result = dbf.setJson("UPDATE `location` SET `location`=? WHERE `id`=?", "SHA", 2);
//            JSONArray result = dbf.setJson("DELETE FROM `location` WHERE `id`=?", 5);
            dbf.disconnect();
            
//            System.out.println( list2json( result ) );
            System.out.println(result.toString());
            
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void testDBFSelect(){
        DBFunction dbf = new DBFunction(dbDriverMySQL, dbconn, dbaccount, dbpwd);
        try {
            dbf.connect(true);
//            JSONArray result = dbf.getJson("SELECT * FROM account WHERE account = ?", "leo");
//            JSONArray result = dbf.getJson("select * from `account`, `location` where `location`.`account_id` = `account`.`id`");
            ALHM result = dbf.getALHM("select * from `account`, `location` where `location`.`account_id` = `account`.`id`");
            dbf.disconnect();
            
//            System.out.println(result.toString());
            System.out.println( list2json( result ) );
            
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void testList() {
        ALHM list = new ALHM();
        HM map = new HM();

        map.put("abc", "123456");
        map.put("def", "hmm123");

        List list1 = new ArrayList();
        Map<String, Object> map1 = new HashMap();
        map1.put("TheOne", "abc");
        map1.put("TheTwo", "abc");
        map1.put("TheThree", "abc");

        List list2 = new ArrayList();
        Map<String, Object> map2 = new HashMap();
        map2.put("qw", "1");
        map2.put("qq", 33);
        map2.put("rr", "gfee");

        List list3 = new ArrayList();
        Map<String, Object> map3 = new HashMap();
        map3.put("qw", "1");
        map3.put("qq", 33);
        map3.put("rr", "gfee");

        Map<String, Object> map4 = new HashMap();
        map4.put("qw", "1");

        map3.put("map4", map4);

        list3.add(map3);

        map2.put("list3", list3);

        list2.add(map2);

        map1.put("list2", list2);

        list1.add(map1);

        map.put("list1", list1);

        list.add((HashMap<String, Object>) map);

        list.printAll();

    }
}
