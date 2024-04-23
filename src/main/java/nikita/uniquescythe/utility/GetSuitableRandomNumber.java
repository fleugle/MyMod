package nikita.uniquescythe.utility;

import java.util.Random;

public class GetSuitableRandomNumber {


	Random random = new Random();

	public float generateRandomFloatNumber(float maxValue){
		float randomFloatNumber;
		randomFloatNumber = random.nextFloat(maxValue);

		return randomFloatNumber;
	}

	public double generateRandomDoubleNumber(double maxValue){
		double randomDoubleNumber;
		randomDoubleNumber = random.nextDouble(maxValue);

		return randomDoubleNumber;
	}


	public int generateRandomIntNumber(int maxValue){
		/*
		 * BTW,  max value stands for a value that will never be reached,
		 * so I guess the correct way to do it here is to put a number of
		 * cases instead of an actual maxValue for int. Others not tested yet.
		 */
		int randomIntNumber;
		randomIntNumber = random.nextInt(maxValue);

		return randomIntNumber;
	}
}
