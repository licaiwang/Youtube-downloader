import java.io.File;

public class FileReader {
    public static String getFileList(String folderPath) {
        new StringBuffer();
        try {
            java.io.File folder = new java.io.File(folderPath);
            String[] list = folder.list(); 
            for (int i = 0; i < list.length; i++) {
                File tempFile = new File(folderPath + "\\" + list[i]);
                if (!tempFile.isDirectory() && list[i].indexOf(".mp")!=-1)
                    return list[i];
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return "";
    }
}