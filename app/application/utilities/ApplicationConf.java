package application.utilities;

import play.Play;

/**
 * Created by pkonwar on 2/11/2017.
 */
public class ApplicationConf {

    //reading from default application.conf
    public static String readProperty(String key) {
        String value = Play.application().configuration().getString(key);
        return value;
    }
}
