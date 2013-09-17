import java.util.Scanner;
import java.io.*;
import java.math.*;
public class fileDecrMain {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String fileName;
		int numOfChar= 0;
		String decrText;
		FileReader fileIn = null;
		FileWriter fileOut = null;
		int[] passes;
		ValidateData check = new ValidateData();
		int key = 0;
		int charVal = 0;
		System.out.println("Press 1 to decript, press 2 to encrypt");
		while(!check.isValidInt(in.next(), 0, 999999)){	}
		switch(check.getData()){
		case 1:
			decrText = new String();
			System.out.println("Enter the name of the file(.txt assumed)");
			fileName = in.next() + ".txt";
			System.out.println("Enter the key");
			while(!check.isValidInt(in.next(), 0, 999999)){
				System.out.println("Enter the key");
			}
			key = check.getData();
			passes = new int[key];
			try{
				fileIn = new FileReader(fileName);
				while(fileIn.read() != -1){
					numOfChar++;
				}
				fileIn.close();
				for(int i = 0;i < key;i++){
					passes[i] = 0;
					fileIn = new FileReader(fileName);
					fileIn.skip(i);
					while(fileIn.read() != -1){
						passes[i]++;
						fileIn.skip(key-1);
					}
					fileIn.close();
				}
				for(int i = 0;i < passes[0];i++){
					fileIn = new FileReader(fileName);
					for(int j = 0;j < key;j++){
						if(j == 0){
							fileIn.skip(i);
						}
						else{
							fileIn.skip((passes[j-1]-1));
						}
						charVal = fileIn.read();
						if(charVal != -1){
							decrText += Character.toString((char)charVal);
							if(decrText.length() == numOfChar){
								break;
							}
						}
					}
					fileIn.close();
				}
			}catch(IOException  e){
				System.out.println(e);
			}finally{
				System.out.print(decrText);
			}
			break;
		case 2:
			System.out.println("Enter text to decrpt");
			String normalText;
			while(true){
				if(!(normalText = in.nextLine()).isEmpty()){
					break;
				}
			}
			System.out.println("Enter key value to decript with.");
			while(!check.isValidInt(in.next(), 0, 999999)){}
			key = check.getData();
			decrText = new String();
			for(int i = 0;i < key;i++){
				for(int j = 0;j < normalText.length();j+=key){
					try{
						decrText += normalText.charAt(i+j);
					}catch(Exception e){
						break;
					}
				}
			}
			System.out.println("enter filename.(will be saved in current directory)");
			fileName = in.next();
			try{
				fileOut = new FileWriter(fileName+".txt");
				fileOut.write(decrText);
				System.out.println("File Saved. decripted text: " + decrText);
				fileOut.close();
			}catch(Exception e){
				break;
			}
			break;
		default:
			return;
		}
	}
}
