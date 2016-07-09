// CS 0445 Spring 2013
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only two instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.
	private CNode firstC;
	private int length;

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		if (s.length() == 0) 
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode current = firstC;
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				current.next = newNode;
				current = newNode;
				length++;
			}
		}
		
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		if (s.length == 0) 
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s[0]);
			length = 1;
			CNode current = firstC;
			for (int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode(s[i]);
				current.next = newNode;
				current = newNode;
				length++;
			}
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
			firstC = null;
			length = 0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		if(b != null)
		{
			this.append(b.toString());
		}
		return this;
	}

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		if(firstC == null)
		{
			firstC = new CNode(s.charAt(0));
			CNode curr = firstC;
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				curr.next = newNode;
				curr = newNode;
			}
		}
		else
		{
			CNode lastNode = firstC;
			for(int i = 1; i < length; i++)
			{
				lastNode = lastNode.next;
			}
			for (int i = 0; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				lastNode.next = newNode;
				lastNode = newNode;
			}
		}
		length += s.length();
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		CNode lastNode = firstC;
		for(int i = 1; i < length; i++)
		{
			lastNode = lastNode.next;
		}
		for (int i = 0; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);
				lastNode.next = newNode;
				lastNode = newNode;
				length++;
			}
		length += c.length;
		return this;
		
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an exception.
	public char charAt(int index)
	{
		char result = ' ';
		CNode charNode = firstC;
		for(int i=0;i<=index;i++)
		{
			result = charNode.data;
			charNode = charNode.next;
		}
		return result;
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing.  if "end" is 
	// past the end of the MyStringBuilder, only remove up until the end of
	// the MyStringBuilder. Be careful for special cases!
	public MyStringBuilder delete(int start, int end) //FIX####
	{
		assert(start>-1 && end >= start);
		if(end > length)
		{
			end = length;
		}
		CNode startNode=null,endNode = firstC;

		for(int i=1;i<end;i++)
		{
			if(i == start)
			{
				startNode=endNode;
			}
			endNode = endNode.next;	
		}
		if(start == 0)
		{
			firstC = endNode.next;
		}
		else
		{
			startNode.next = endNode.next;
		}
		length -= (end - start);
		return this;	
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing.  Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(index<0 || index>length)
		{
			return this;
		}
		else if(index==0)
		{
			firstC=firstC.next;
		}
		else
		{
			CNode startNode = firstC;
			for(int i=1;i<index;i++)
			{
				startNode = startNode.next;
			}
			startNode.next = startNode.next.next;
		}
		length--;
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		CNode curr = firstC;
		boolean good = true;
		for(int i = 0; i < length; i++)
		{
			if(str.charAt(0) == curr.data)
			{
				if((length - 1) < str.length())
				{
					return -1;
				}
				good = true;
				CNode current = curr;
				for(int j = 1; j < str.length(); j++)
				{
					current = current.next;
					if(current == null || current.data != str.charAt(j))
					{
						good = false;
						break;
					}
				}
				if(good)
				{
					return i;
				}			
			}
			curr = curr.next;
		}
		return -1;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.*/
	public MyStringBuilder insert(int offset, String str)
	{
		if(str.length() == 0 || offset > length)
		{
			return null;
		}
		else if(offset == length)
		{
			return this.append(str);
		}
		else
		{
			CNode startNode,endNode,curr;
			
			if(offset == 0)
			{
				startNode = new CNode(str.charAt(0));
				endNode = firstC;
				firstC = startNode;
				curr = startNode;
				for(int i=1;i<str.length();i++)
				{
				curr.next = new CNode(str.charAt(i));
				curr = curr.next;
				}
				curr.next = endNode;
			}
			else{
				startNode = firstC;
				int index = 1;
				while(index < offset)
				{
					index++;
					startNode = startNode.next;
				}
		
				CNode temp = startNode.next;
				startNode.next = new CNode(str.charAt(0));
				curr = startNode.next;
		
				for(int i = 1; i < str.length(); i++)
				{
					curr.next = new CNode(str.charAt(i));
					curr = curr.next;
				}
				curr.next = temp;
			}
		}
		length += str.length();
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if(offset > length)
		{
			return null;
		}
		else
		{
			CNode curr = firstC;
			int index = 0;
		
			while(index < offset)
			{
				curr = curr.next;
				index++;
			}
			if(offset == length)
			{
				curr.next = new CNode(c);
			}
			else 
			{
				CNode newNode = new CNode(c, curr.next.next);
				curr.next = newNode;
			}
		}
		length++;
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		if(offset > length || c.length == 0)
		{
			return this;
		}
		else if(offset == length)
		{
			return this.append(c);
		}
		else
		{
			CNode startNode = firstC;
			int index = 1;
			while(index < offset)
			{
				index++;
				startNode = startNode.next;
			}
			
			CNode temp = startNode.next;
			startNode.next = new CNode(c[0]);
			CNode curr = startNode.next;
			
			for(int i = 1; i < c.length; i++)
			{
				curr.next = new CNode(c[i]);
				curr = curr.next;
			}
			curr.next = temp;	
		}
		length += c.length;
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}


	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the curent
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.
	public MyStringBuilder replace(int start, int end, String str)
	{
		if(start<0 || end<0 || end<start || str.length() == 0 || start == end)
		{
			return this;
		}
		this.delete(start,end);
		return this.insert(start, str); 
	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		if(start>end||start<0||end<0||start==end)
		{
		return null;
		}
		String str = "";
		CNode curr = firstC;
		for(int i = 0; i<end; i++)
		{
			if(i>=start)
			{
				str += curr.data;
			}
			curr = curr.next;
		}
		return str;
	}

	// Return the entire contents of the current MyStringBuilder as a String*/
	public String toString()
	{
		String str = "";
		CNode current = firstC;
		while(current != null)
		{
			str += current.data;
			current = current.next;
		}
		return str;
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
