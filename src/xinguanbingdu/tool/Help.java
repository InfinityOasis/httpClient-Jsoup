package xinguanbingdu.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Help {

    public static void wirte2File(String str, String path,boolean flag) throws IOException {
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();
        FileWriter fileWriter = new FileWriter(path, flag);
        if (str != null)
            fileWriter.write(str + "\n");
        fileWriter.close();
    }

}
