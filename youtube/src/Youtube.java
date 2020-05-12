import java.io.File;
import java.io.IOException;
import java.util.List;

public class Youtube {
    public static String url = "";
    public static String name = "";
    public static String type = "";
    public static String path = getCurrentDirectory();
    public static Boolean IsPlayList = false;
    static String downLoadMusic = "";

    public static void main(String[] args) {
        createFile("/download_music");
        Gui gui = new Gui("誠的 Youtube 下載器");
        gui.setVisible(true);
    }

    public static void DownLoadOne() {
        // 下載單曲
        String cmd = getCmd(Youtube.type);
        ExcuteCmd(cmd, "網址異常");
        moveFile(0);
    }

    public static void DownLoadPlayList() {
        // 下載播放清單
        if(Gui.fileName == null)
        {
            Gui.fileName = "playlist";
        }
        createFile("/download_music/"+Gui.fileName);
        String cmd = getCmd(Youtube.type);
        ExcuteCmd(cmd, "網址異常");
        moveFile(1);
    }

    private static void moveFile(int i) {
        // 移動檔案
        List<String> music = FileReader.getFileList(path);
        for (int index = 0; index < music.size(); index++) {
            switch (i) {
                case 0:
                    String cmd_0 = "cmd /c  move  " + '"' + path + music.get(index) + '"' + " ./download_music";
                    downLoadMusic = "已下載   ------   " + music.get(index);
                    ExcuteCmd(cmd_0, "移動失敗，請手動移動檔案");
                    break;
                case 1:
                    String cmd_1 = "cmd /c move " + '"' + path + music.get(index) + '"' + " ./download_music/"+Gui.fileName;
                    downLoadMusic += ("已下載   ------   " + music.get(index) + "\n");
                    ExcuteCmd(cmd_1, "移動失敗，請手動移動檔案");
                    break;
            }
            if (music.get(index) == "") {
                return;
            }
            Gui.result.setText(downLoadMusic);
        }
        downLoadMusic = "";
    }

    public static void ExcuteCmd(String cmd, String errorMsg) {
        // 執行執行續
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);
            int done = process.waitFor();
            if (done != 0) {
                // 如果不正常退出就關掉
                process.destroy();
                Gui.result.setText(errorMsg);
                return;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getCmd(String select) {
        // 創造 cmd
        if (select.equals("mp3")) {
            return "cmd /c  youtube-dl --extract-audio --audio-format mp3 " + url;
        } else {
            return "cmd /c  youtube-dl -f mp4 " + url;
        }
    }

    public static void createFile(String file) {
        // 創建資料夾
        File f = null;
        f = new File(path + file);
        f.mkdir();
    }

    public static String getCurrentDirectory() {
        // 取得當前目錄
        File now_directory = new File(".");
        String current_path = now_directory.getAbsolutePath().replaceAll("\\.", "");
        return current_path;
    }
}
