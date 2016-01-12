package test;

public class Test {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		int toNum = 100;
		/*
		try{
		 toNum = ScannerInput.readInt();
		}catch(Exception e){
			System.out.println("예외발생:");
		}
		*/
		int sumOdd = 0;
		int sumEven = 0;
		
		sumEven = summEven(1, toNum);
		System.out.println("1 to "+ toNum+" even sum "+sumEven);
		
		sumOdd = summOdd(1,toNum);
		System.out.println("1 to "+ toNum+" odd sum "+sumEven);
		
	}
	
	public static int summEven(int start, int end)
	{
		int sumEvent = 0;
		//짝수합
		for(int i=1; i<=end; i++){
			if(i%2 != 0){
				continue;
			}
			sumEvent += i;
		}
		
		return sumEvent;
	}
	
	public static int summOdd(int start, int end)
	{
		int sumOdd = 0;
		//홀수합
		for(int i=1; i<end; i++){
			if(i%2 == 0){
				continue;
			}
			sumOdd += i;
		}
		
		return sumOdd;
	}

}
