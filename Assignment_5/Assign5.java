//Reggie Barnett
//Assignment 5
import java.io.*;
import java.util.*;

public class Assign5{

	public static BinaryNode<Character> Tree = new BinaryNode<Character>();
	
	public static void main(String args[])throws Exception{
		Scanner readFile = new Scanner(new File(args[0]));
		int[] leafCount = new int[1];
		leafCount[0]=0;
		//Building the tree
		Tree = buildTree(readFile,readFile.nextLine(),leafCount);
		System.out.println("The Huffman Tree has been restored\n");
		String[] table = new String[leafCount[0]];
		char[] letters = new char[leafCount[0]];
		StringBuilder huffCode = new StringBuilder();
		//Building the table
		buildTable(Tree,table,letters,huffCode);
		int choice = 0;
		//Lets you continue to choose if you want to decode or encode until you quit
		while(choice != 3){
			System.out.println("Please choose from the following:\n1) Encode a text string\n2) Decode a Huffman string\n3) Quit");
			choice = 0;
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();
			if(choice == 1){ //decode
				encode(table,letters,leafCount);
			}
			else if(choice == 2){ //encode
				for(int i=0;i<leafCount[0];i++){	//Prints encoding table
					System.out.println(letters[i]+": "+table[i]);
				}
				System.out.println("Please enter a Huffman String (one line, no spaces):");
				Scanner in = new Scanner(System.in);
				String huffInput = in.nextLine();
				StringBuilder huffOut = new StringBuilder();
				huffOut = decode(Tree,huffInput,huffOut,0);
				System.out.println("Text string:\n"+huffOut.toString());
			}
			else if(choice == 3){//quit
				break;
			}
			else{
				System.out.println("Please enter 1, 2, or 3");
			}
		}
		System.out.println("Goodbye!");
	}//end main
	
	public static BinaryNode<Character> buildTree(Scanner readFile,String letter,int[] leafCount){
		BinaryNode<Character> currNode;
		//Check for Node
		if(letter.charAt(0)=='I'){
			currNode = new BinaryNode<Character>(null); //sets interior node to null
			currNode.setLeftChild(buildTree(readFile,readFile.nextLine(),leafCount)); //recursive call for left branch
			currNode.setRightChild(buildTree(readFile,readFile.nextLine(),leafCount)); //recursive call for right branch
		}
		//Check for Leaf
		else{
			currNode = new BinaryNode<Character>(letter.charAt(2));
			leafCount[0]++;
		}
		return currNode;
	}//end buildTree
	
	private static void buildTable(BinaryNodeInterface<Character> currNode, String[] table, char[] letters, StringBuilder huffCode){
		//interior node
		if(currNode.getData() == null){
			huffCode.append('0');
			buildTable(currNode.getLeftChild(),table,letters,huffCode);
			huffCode.deleteCharAt(huffCode.length()-1);
			
			huffCode.append('1');
			buildTable(currNode.getRightChild(),table,letters,huffCode);
			huffCode.deleteCharAt(huffCode.length()-1);
		}
		//leaf node
		else{
			int index = (int)currNode.getData()-65;
			table[index] = huffCode.toString(); //array for Huffman Codes
			letters[index] = currNode.getData();//array for letters
		}
	}//end buildTable
	
	private static void encode(String[] table, char[] letters,int[] leafCount){
		System.out.println("Enter a String from the following characters:");
		for(int i=0;i<leafCount[0];i++){
			System.out.print(letters[i]);
		}System.out.println();
		String output = "";
		Scanner huffstring = new Scanner(System.in);
		String huffletter = huffstring.nextLine();
		for(int i=0;i<huffletter.length();i++){//Checks for matching letters
			for(int j=0;j<leafCount[0];j++){
				if(huffletter.charAt(i)==letters[j]){//If a match, finds equivalent code for letter
					output = output+table[j]+"\n";
				}
			}
		}
		System.out.println("Huffman String:");
		System.out.println(output);
	}//end encode
	
	private static StringBuilder decode(BinaryNodeInterface<Character> currNode, String huffInput, StringBuilder huffOut, int pos){
		if(currNode.isLeaf()){
			huffOut.append(currNode.getData());
			if(pos<huffInput.length()){
				huffOut = decode(Tree,huffInput,huffOut,pos);
			}
		}
		//goes left
		else if(huffInput.charAt(pos)=='0'){
			huffOut = decode(currNode.getLeftChild(),huffInput,huffOut,pos+1);
		}
		//goes right
		else{
			huffOut = decode(currNode.getRightChild(),huffInput,huffOut,pos+1);
		}
		return huffOut;
	}//end decode
}//end


/*public static String decodeInput(String input){
	return decodeInput(input, new StringBuilder(), 0, ROOT).toString();
}
public static StringBuilder decodeInput(String input, StringBuilder decodedString, int currentChar, BinaryNode<Character> currentNode){

	if(currentNode.isLeaf()){
		decodedString.append(currentNode.getData());
		if(currentChar < input.length()){
		decodedString = decodeInput(input, decodedString, currentChar, ROOT);
		}
	}

	else if(input.charAt(currentChar) == '0'){
		decodedString = decodeInput(input, decodedString, currentChar + 1, currentNode.getLeftChild());
	}

	else{
		decodedString = decodeInput(input, decodedString, currentChar + 1, currentNode.getRightChild());
	}

	return decodedString;
}*/