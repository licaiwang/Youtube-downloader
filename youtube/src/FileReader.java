import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
   
    public static List<String> getFileList(String folderPath) {
        new StringBuffer();
        try {
            List<String> music_list = new ArrayList<String>();
            java.io.File folder = new java.io.File(folderPath);
            String[] list = folder.list();
            for (int i = 0; i < list.length; i++) {
                File tempFile = new File(folderPath + "\\" + list[i]);
                if (!tempFile.isDirectory() && list[i].indexOf(".mp") != -1)
                {
                    music_list.add(list[i]);
                }                  
            }
            return music_list;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;      
    }
}