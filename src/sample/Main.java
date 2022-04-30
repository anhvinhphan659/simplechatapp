package sample;


import org.json.JSONObject;

import java.io.*;

public class Main  {



    public static void main(String[] args) {

//        File file=new File("D:\\Bai Lam\\Nam 3\\CV_Zalo Fresher_Phan-Nguyen-Anh-Vinh - Copy.pdf");
//        System.out.println(file.exists());
//        if(file.exists())
//        {
//            try {
//                DataInputStream din=new DataInputStream(new FileInputStream(file));
//                byte[] data=din.readAllBytes();
//                for (int i=0;i<data.length;i++)
//                    System.out.println(data[i]);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        JSONObject jo=new JSONObject();
        jo.put("from","socket0");
        jo.put("to","socket1");
        jo.put("to","socket3");
        jo.put("type","text");
        jo.put("content","sdasdsadadsadaa");
        jo.put("data",new byte[]{11,25,56,55});
        System.out.println(jo);
        System.out.println(jo.get("data"));
        String jo_str= jo.toString();
        System.out.println(jo_str);
        JSONObject JO2=new JSONObject(jo_str);
        System.out.println(JO2.get("from"));
    }
}
