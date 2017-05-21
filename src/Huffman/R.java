package Huffman;

/**
 * 二进制读写文件
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class R
{
	static String read(){
		try{
			BufferedReader in=new BufferedReader(new FileReader(HuffmanCode.readfilepath));
			String line=null;
			StringBuffer s=new StringBuffer();
			while((line=in.readLine())!=null){
				s.append(line);
			}
			in.close();
			return s.toString();
		}catch(Exception e){
			;
		}
		return null;
	}
}


