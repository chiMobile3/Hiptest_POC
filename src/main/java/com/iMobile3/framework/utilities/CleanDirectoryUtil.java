package com.iMobile3.framework.utilities;


import java.io.File;

public class CleanDirectoryUtil {


    public static void RemoveFiles(){
        File dir = new File(System.getProperty("user.dir") + File.separator + "Downloads");
        for (File file: dir.listFiles())
            if (!file.isDirectory())
                file.delete();
    }


}
