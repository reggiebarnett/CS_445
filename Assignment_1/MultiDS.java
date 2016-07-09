// CS 0445 Spring 2013
// Assignment 1
// Created by Reggie Barnett
// rdb26@pitt.edu

import java.util.*;

public class MultiDS<T> implements PrimQ<T>,Reorder{

private T data[];
private int NumberOfEntries;
	public MultiDS(int length){
	data = (T[])new Object[length];
	NumberOfEntries = 0;
	}
	
	public boolean full(){  //detects if full
		if(NumberOfEntries == data.length){
			return true;
		}
		else
			return false ;
	}

	public boolean empty(){  //detects if empty
		if (NumberOfEntries == 0){
			return true;
		}
		else
			return false;
	}
	
	public int size(){  //number of items
		return NumberOfEntries;
	}

	public boolean addItem(T item){ //adds items into the queue
		if(empty()){
			data[0] = item;    //adds the first item
			NumberOfEntries++;
			return true;
		}	
		else if (full()){  //doesnt allow anymore items to be added
			return false;
		}	
		else{
			for(int i=NumberOfEntries;i>=1;i--){ //adds items, shifts over previous entries
				data[i]=data[i-1];
			}
			data[0] = item;
			NumberOfEntries++;
			return true;
			}
		}
			
	public T removeItem(){ //Removes items from the queue
		if(NumberOfEntries == 0){
			return null;
		}
		else{
			T temp = data[NumberOfEntries - 1];
			data[NumberOfEntries - 1] = null;
			NumberOfEntries--;
			return temp;
		}
	}

	public void clear(){
		for(int i=0;i<NumberOfEntries;i++){
			data[i]= null;
		}
		NumberOfEntries = 0;
	}
	////////////End of PrimQ
	////////////Start Reorder
	public void reverse(){
 		T temp[] = (T[])new Object[NumberOfEntries];
		int tmp = NumberOfEntries;
		for(int i = 0; i < tmp; i++){	
			temp[i] = removeItem();
		}
		data = temp;
		NumberOfEntries = tmp;
	}
 
	public void shiftRight(){
		T temp = removeItem();
		addItem(temp);
		
	}

	public void shiftLeft(){
		for(int i=0;i<NumberOfEntries-1;i++){
			shiftRight();
		}
	}
	public void shuffle(){
		Random rand = new Random();
		int randNum;
		for(int i=0;i<size();i++){
			randNum = rand.nextInt(size());
			T temp = data[i];
			data[i] = data[randNum];
			data[randNum] = temp;
		}
	}
	
	public String toString(){
		String str = "[";
		for(int i=0;i<NumberOfEntries;i++){
			str+=data[i] + ", ";
		}
		str=str.substring(0,str.lastIndexOf(','))+"]";
		return str;
	}

}