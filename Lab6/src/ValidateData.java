
public class ValidateData {
	int dataInt = 0;
	
	public boolean isValidInt(String intString,int min,int max){
		for(int i = 0;i < intString.length();i++){
			if(Character.isDigit(intString.charAt(i))){
				int c = Integer.parseInt(Character.toString(intString.charAt(i)));
				if(c < min || c > max){
					return false;
				}
			}
			else{
				return false;
			}
		}
		dataInt = Integer.parseInt(intString);
		return true;
	}
	
	public int getData(){return dataInt;}
}
