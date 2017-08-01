package com.renke.util.Browers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPExample {
	 
    public static void main(String[] args) {
    	fileExample();
//    	try {
//			byteExample();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    }
    
    public static void byteExample() throws IOException{
    	String str = "你好，世界(Hello world)";
    	GZIPOutputStream gout = new GZIPOutputStream(new FileOutputStream(new File("mytest")));
    	gout.write(str.getBytes());
    	gout.flush();
    	gout.close();
    	FileInputStream gin = new FileInputStream(new File("GZIP"));
    	byte[] buf = new byte[1024];
    	int len = 0;
    	 while((len = gin.read(buf)) != -1){
    		 for(int i=0;i<len;i++){
    			 System.out.print(Integer.toHexString(buf[i]));
    		 }
//    		 System.out.println(new String(buf,0,len));
    	 }
    	 gin.close();
    }
    //1fffffff8b800000003bfffffff278ffffffd7ffffffe1ffffffc56b4e1dffffffd8fffffffb5cffffffc3233527275fffffffa13cffffffbf28274513040ffffffbf265217000
    //1fffffff8b80000000ffffffdbfffffff96fffffffe7ffffffbfdb77fffffffeffffffe3ffffffe53afffffff278ffffffd7ffffffe1431f78ffffffb932527372fffffff215ffffffdcffffffa33c30324c3f3d1c000
    public static void fileExample(){
        String file = "helloGZIP";
        String gzipFile = "GZIP";
        String newFile = "newFile";
        compressGzipFile(file, gzipFile);
        decompressGzipFile(gzipFile, newFile);
    }
    
    private static void decompressGzipFile(String gzipFile, String newFile) {
    	//通过gzip读取流文件，读取时，自动将数据转换为正常数据
        try {
            FileInputStream fis = new FileInputStream(gzipFile);
            GZIPInputStream gis = new GZIPInputStream(fis);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int len;
            while((len = gis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
            fos.flush();
            //close resources
            fos.close();
            gis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
 
    private static void compressGzipFile(String file, String gzipFile) {
        try {
        	
        	//读取正常文件，将文件经过gzip流输出
        	BufferedReader br = new BufferedReader(new FileReader(file));
            FileOutputStream fos = new FileOutputStream(gzipFile);
            GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
//            byte[] buffer = new byte[1024];
            String line = br.readLine();
            while(line != null){
            	gzipOS.write(line.getBytes());
            	line = br.readLine();
            	if(line != null)
            		gzipOS.write("\r\n".getBytes());
            }
            br.close();
            gzipOS.flush();
            //close resources
            gzipOS.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
 
}
