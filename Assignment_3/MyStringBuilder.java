//Reggie Barnett
//Assignment 3

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
		{
      makeBuilder(s, 0);
		}
	}

	private void makeBuilder(String s, int pos)//for strings
	{
      if (pos < s.length())
      {
           makeBuilder(s, pos+1);
           firstC = new CNode(s.charAt(pos), firstC);
           length++;
     	}
      else  // Base case
      {
      	length = 0;
         firstC = null;
      }
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		makeBuilder(s, 0);
	}

	private void makeBuilder(char[] s, int pos)//for characters
	{
      if (pos < s.length)
      {
           makeBuilder(s, pos+1);
           firstC = new CNode(s[pos], firstC);
           length++;
     	}
      else  // Base case
      {
      	length = 0;
         firstC = null;
      }
	}
	
	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		makeBuilder(0);
	}
	
	private void makeBuilder(int pos)//for empty
	{
   	length = 0;
   	firstC = null;
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
			firstC = new CNode(s.charAt(0),null);
			length++;
			appendBuilder(s,firstC,1);
		}
		else
		{
			appendBuilder(s, firstC, 0);
		}
		return this;
	}

	private void appendBuilder(String s, CNode curr,int pos)
	{

		if(pos<s.length())
		{
			if (curr.next == null)
			{
				curr.next = new CNode(s.charAt(pos), null);
				length++;
				appendBuilder(s, curr.next,pos+1);
			}
			else
			{
				appendBuilder(s, curr.next,pos);
			}
		}	
	}	
	
	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		appendBuilder(c, firstC, 0);
		return this;
	}

	private void appendBuilder(char[] c, CNode curr,int pos)
	{
		if(pos<c.length)
		{
			if (curr.next == null)
			{
				curr.next = new CNode(c[pos], null);
				length++;
				appendBuilder(c, curr.next,pos+1);
			}
			else
			{
			appendBuilder(c, curr.next,pos);
			}
		}
	}
	
	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an exception.
	public char charAt(int index)
	{
		return charAt(index, firstC,0);
	}
	
	private char charAt(int index, CNode curr, int pos)
	{
		if(pos == index)
			return curr.data;
		else
		{
			return charAt(index, curr.next,pos+1);
		}
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing.  if "end" is 
	// past the end of the MyStringBuilder, only remove up until the end of
	// the MyStringBuilder. Be careful for special cases!
public MyStringBuilder delete(int start, int end)
	{
		if(start >-1 && end >= start)
		{
			if(end > length)
			{
				end = length;
			}
			delete(start,end,0,firstC, null);
		}
		return this;
	}
	
	private void delete(int s,int e,int p,CNode curr, CNode temp)
	{
		if(p == s-1)
		{
			temp = curr;
		}
		else if(p == e)
		{
			if(s==0)
			{
				firstC = curr;
			}
			else
			{
				temp.next = curr;
			}	
			length = length - (e-s);
			return;
		}
		delete(s,e,p+1,curr.next,temp);
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing.  Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(index > 0 && index <length)
		{
			deleteCharAt(index,0, firstC);
		}
		else if(index == 0)
		{
			if(length > 1)
			{
				firstC = firstC.next;
			}
			else
			{
				firstC = null;
			}
			length--;
		}
		return this;
	}
	
	private void deleteCharAt(int index,int pos, CNode curr)
	{
		if(index - 1 == pos)
		{
			if(index == length)
			{
				curr.next = null;
			}
			else
			{
				curr.next = curr.next.next;
			}
			length--;
		}
		else
		{
			deleteCharAt(index,pos+1,curr.next);
		}
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		return (indexOf(str,0,0,firstC));
	}
	
	private int indexOf(String s,int pos,int pos2,CNode curr)
	{
		if(pos == length)
		{
			return -1;
		}	
		if(pos2 > 0)
		{
			if(curr.data == s.charAt(pos2))
			{
				if(pos2 == s.length()-1)
				{
					return pos - (s.length()-1);
				}
				pos2++;
			}
			else
			{
				pos2 = 0;
			}
		}
		if(curr.data == s.charAt(0))
		{
			pos2 = 1;
		}
		return indexOf(s,pos+1,pos2,curr.next);
	} 

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same ass append.  If "offset" is invalid
	// do nothing.
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
			insert(offset,str,0,firstC,null);
			return this;
		}
	}
	
	private void insert(int offset,String s, int pos, CNode curr, CNode endNode)
	{
		if(pos<offset-1)
		{
			insert(offset,s,pos+1,curr.next,endNode);
		}
		else if(pos==offset-1)
		{
			endNode = curr.next;
			curr.next = new CNode(s.charAt(0),null);
			insert(offset,s,pos+1,curr.next,endNode);
		}
		else if(pos==offset+s.length()-1)
		{
			curr.next = endNode;
			length += s.length();
			return;
		}
		else
		{
			CNode temp = new CNode(s.charAt(pos-offset+1),null);
			curr.next = temp;
			insert(offset,s,pos+1,temp,endNode);
		}
		
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
			insert(offset,c,0,firstC,null);
			return this;
		}

	}

	private void insert(int offset,char c, int pos, CNode curr, CNode endNode)
	{
		if(pos<offset-1)
		{
			insert(offset,c,pos+1,curr.next,endNode);
		}
		else if(pos==offset-1)
		{
			endNode = curr.next;
			curr.next = new CNode(c,null);
			insert(offset,c,pos+1,curr.next,endNode);
		}
		else if(pos==offset)
		{
			curr.next = endNode;
			length++;
			return;
		}
		else
		{
			CNode temp = new CNode(c,null);
			curr.next = temp;
			insert(offset,c,pos+1,temp,endNode);
		}
		
	}
	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		return insert(offset, new String(c));
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
		return (substring(start,end,0,firstC,""));
	}
	
	private String substring(int s, int e, int pos, CNode curr, String str)
	{
		if(s>e||s<0||e<0||s==e)
		{
			return null;
		}
		if(pos==e)
		{
			length = length-(e-s);
			return str;
		}
		else if(pos>=s)
		{
			str += curr.data;
			substring(s,e,pos+1,curr.next,str);
		}
		return substring(s,e,pos+1,curr.next,str);
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString() 
	{
      char [] c = new char[length];
      getString(c, 0, firstC);
      return (new String(c));
	}
	
	private void getString(char [] c, int pos, CNode curr)
	{
      if (curr != null)
      {
            c[pos] = curr.data;
            getString(c, pos+1, curr.next);
      }
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