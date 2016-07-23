/*
 * Property.java
 *
 * Created on 2007/12/24, 上午 11:28:38
 *
 */
package jlib.tool;

import jlib.tool.Debug;
import jlib.tool.Toolet;
import static jlib.Constants.*;
import java.io.FileInputStream;
import java.util.Properties;

public class AppProp {
    protected static Debug de = new Debug(true);
    
    protected static Properties prop;
    public Properties getProp(){return prop;}
    protected String propFilePath = null;
    protected String propFileName = null;
    
    public AppProp() {
//        this.propFilePath = (new Toolet()).getHomePath()+propertyFolderStr+appStr;
//        prop = load(this.propFilePath);
    }
    
    public AppProp(String propFilePath, String propFileName) {
        this.propFilePath = propFilePath;
        this.propFileName = propFileName;
        prop = load(this.propFilePath + this.propFileName);
    }
    
    public static Properties load(String propFilePath){
        Properties prop = null;

        try {
            prop = new Properties();
            FileInputStream in = new FileInputStream(propFilePath);
            prop.load(in);
            
            in.close();

        } catch (Exception ex) {
          ex.printStackTrace();
        }

        return prop;
    } 
    
    public static String getValue(String propName) {
        return prop.getProperty(propName);
    }
    
}
