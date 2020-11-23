package me.zhengjie.modules.mskj.common;

public class WebToolUtils {
    public static String getLocal() {
        if (isWindowsOS()) {
            return "C:/Tools";
        } else {
            return "/usr/share/nginx/html/Tools";
        }
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }
}
