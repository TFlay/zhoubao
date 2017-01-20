package info.haominglfs.zb.zb;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class PropertiesHelper {

    public static final String      CONFIG_File_NAME = "config.properties";

    private static PropertiesHelper propertiesHelper = null;

    public static PropertiesHelper getInstance() {
        if (null == PropertiesHelper.propertiesHelper) {
            PropertiesHelper.propertiesHelper = new PropertiesHelper();
        }

        return PropertiesHelper.propertiesHelper;
    }

    private Properties properties = null;

    private PropertiesHelper() {
        try {
            InputStream stream = null;
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader != null) {
                Reader reader = null;
                try {
                    stream = classLoader.getResourceAsStream(PropertiesHelper.CONFIG_File_NAME);
                    reader = new InputStreamReader(stream, "UTF-8");
                    this.properties = new Properties();
                    properties.load(reader);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally{
                    if(null!=reader){
                        reader.close();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 得到配置文件中对应元素值
     * 
     * @param kay
     * @return
     * @throws IOException
     */
    public String get(final String key) {
        final String value = this.properties.getProperty(key);
        if (!"".equals(value)) {
            return value.trim();
        }
        return null;
    }
}
