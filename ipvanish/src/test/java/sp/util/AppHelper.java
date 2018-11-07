package sp.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AppHelper {

    static final Configuration CONFIGURATION = Configuration.getCONFIGURATION();

    public static boolean launchApp(){
        boolean isAccessible;
        try {
            Desktop.getDesktop().open(new File(CONFIGURATION.getApplicationPath()));
            isAccessible = true;
        } catch (IOException e) {
            isAccessible = false;
        }
        return isAccessible;
    }
}
