package xinguanbingdu.compare;

/**
 * 判断空文件，并未删除
 */

import java.io.File;

public class DeleteTheNullFile {
    public static void main(String[] args) {
        int num = 0;
        for (int i = 1; i <= 497; i++) {
            String path = "E:\\冯老师\\文本\\" + i + ".txt";
            if (new DeleteTheNullFile().isNull(new File(path)))
                num++;
        }
        System.out.println("有"+num+"个空文件");
    }

    public boolean isNull(File file){
        if (file.length()<2)
            return true;
        else
            return false;
    }
    public void deleteNullFile(File file) {
        if (file.length() <= 0)
            file.delete();
    }
}
