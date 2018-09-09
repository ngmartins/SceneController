package scene_server.util;

import java.io.*;

public class fileUtil {

    /*
    A little bit of dark magic here!!
     */
    public static File getFileFromIS(InputStream is) throws IOException
    {
        OutputStream outputStream = null;
        File file = new File("temp-xml");

        try {
            outputStream = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
        finally{
            if(outputStream != null){
                outputStream.close();
            }
        }
        return file;
    }

}
