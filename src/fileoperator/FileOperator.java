package fileoperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperator {
	private String filepath="expressions.txt";
	public Boolean expressionoutput(String string) {
		// 1：利用File类找到要操作的对象
        File file = new File(filepath);
       /* if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }*/
     
        try {
        	  //2：准备输出流
            Writer out = new FileWriter(file,true);
			out.write(string+"\r\n");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
        
	}
	

public ArrayList<String> getexpressions() {

		// 使用ArrayList来存储每行读取到的字符串

		ArrayList<String> arrayList = new ArrayList<>();

		try {

			FileReader fr = new FileReader(filepath);

			BufferedReader bf = new BufferedReader(fr);

			String str;

			// 按行读取字符串

			while ((str = bf.readLine()) != null) {

				arrayList.add(str);

			}

			bf.close();

			fr.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return arrayList;

	}

	
}
