import java.io.File;
import java.io.IOException;

public class Youtube {
    public static String url = "";
    public static String name = "";
    public static String type = "";
    public static String path = getCurrentDirectory();
    public static Boolean IsPlayList = false;
    static String downLoadMusic = "";

    public static void main(String[] args) {
        init();
        Gui gui = new Gui("港 Youtube 更竟");
        gui.setVisible(true);
    }

    private static void init() {
    }

    public static void DownLoadOne() {
        downLoadMusic = "";
        Gui.result.setText("");
        String cmd = getCmd(Youtube.type);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();   
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moveFile(0);
    }

    public static void DownLoadPlayList() {
        downLoadMusic = "";
        Gui.result.setText("");
        File f = null;
        try {
            // returns pathnames for files and directory
            f = new File(path + "/download_music/playList");
            f.mkdir();
            String cmd = getCmd(Youtube.type);
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
        moveFile(1);
    }

    private static void moveFile(int i) {
        String music = FileReader.getFileList(path);
        if (music == "") {
            return;
        }
        try {
            if (i == 0) {
                String cmd_0 = "cmd /c  move  " + '"' +path+music+ '"' + " ./download_music";
                downLoadMusic = "更   ------   " + music;
                Runtime.getRuntime().exec(cmd_0);
                Gui.result.setText(downLoadMusic);
                return;
            } else if (i == 1) {
                String cmd_1 = "cmd /c move " + '"' +path+music+ '"'+ "/download_music/playList";
                downLoadMusic += ("更   ------   " + music + "\n");
                Runtime.getRuntime().exec(cmd_1); 
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } 
        moveFile(i);
        Gui.result.setText(downLoadMusic);
    }

    private static String getCmd(String select) {
        if (select.equals("mp3")) {
            return "cmd /c  youtube-dl --extract-audio --audio-format mp3 " + url;
        } else {
            return "cmd /c  youtube-dl -f mp4 " + url;
        }
    }

    public static String getCurrentDirectory() {
        File now_directory = new File(".");
        String current_path = now_directory.getAbsolutePath().replaceAll("\\.", "");
        return current_path;
    }

}
