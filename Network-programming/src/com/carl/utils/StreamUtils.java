package com.carl.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author Carl
 * @version 1.0
 */
public class StreamUtils {
    /**
     * 功能 : 将输入流转换成byte[], 即可以把文件的内容读入到byte[]
     * @param is
     * @return
     * @throws Exception
     */
    public static byte[] streamToByteArray(InputStream is) throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();//创建输出流
        byte[] b = new byte[1024];//字节数组
        int len;
        while((len = len=is.read(b))!=-1){//循环读取
            bos.write(b,0,len);//把读取到的数据,写入bos
        }
        byte[] array = bos.toByteArray();//然后将
        bos.close();
        return array;
    }
}
