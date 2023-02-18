package cn.imut.ncee.util;

import cn.imut.ncee.domain.ByteFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 22:56
 */
public class FileUtil {

    /**
     * 文件转Byte[]
     * @param file
     * @param fileName
     * @return
     * @throws IOException
     */
    public static ByteFile uploadFile(MultipartFile file, String fileName) throws IOException {
        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        byte[] bytes = new byte[1024];
        //转字节数组流
        while ((i = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, i);
        }
        //放入字节数组
        byte[] fileByte = byteArrayOutputStream.toByteArray();
        inputStream.close();
        byteArrayOutputStream.close();
        ByteFile byteFile = new ByteFile(fileByte, fileName);
        return byteFile;
    }

    /**
     * 文件转List
     * @param fileName
     * @return
     * @throws IOException
     */
    public List<String> fileToList(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        List<String> result = new ArrayList<>();
        String str;
        while (null != (str = bufferedReader.readLine())) {
            result.add(str);
        }
        bufferedReader.close();
        return result;
    }
}
