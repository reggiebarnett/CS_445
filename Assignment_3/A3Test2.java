// CS 0445 Spring 2013
// Simple test to make sure append separates new nodes from old ones.
public class A3Test2
{
	public static void main(String [] args)
	{
		MyStringBuilder b1 = new MyStringBuilder("First Part ");
		MyStringBuilder b2 = new MyStringBuilder("Second Part ");
		MyStringBuilder b3 = new MyStringBuilder("Third Part ");
		b1.append(b2);
		b2.append(b3);
		System.out.println("b1: " + b1);
		System.out.println("b2: " + b2);
		System.out.println("b3: " + b3);
	}
}