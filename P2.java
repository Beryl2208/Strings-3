//time- o(m+n) - m-haystack length, n - needle length
//space- o(n)

class Solution {
    public int strStr(String haystack, String needle) {
        
        if(needle.length() ==0) return 0;
        
        int i =0;
        int j =0;
        
        int[] lps = lps(needle);
        
        while(i<haystack.length())
        {
            //if chars equal, increment prefix window, 
            //and if we reached pattern length, then return the index, we found our needle
            if(haystack.charAt(i) == needle.charAt(j))
            {
                j++;
                i++;
                
                if(j==needle.length())
                {
                    return i-needle.length();
                }
            }            
            //if not equal, decrement prefix length, to see which prefix matches my current suffix
            else if(haystack.charAt(i) != needle.charAt(j) && j>0)
            {
                j = lps[j-1];
            }
            //if not equal, for first char, we know its valid lps, so inc i
            else if(haystack.charAt(i) != needle.charAt(j) && j==0)
            {
                i++;
            }
        }
        
        return -1;
    }
    
    //build lps array, longest prefix suffix array
    public int[] lps(String s)
    {
        int i= 1;
        int j= 0;
        int[] lps = new int[s.length()];
        while(i<s.length())
        {
            //if chars equal, increment prefix window
            if(s.charAt(i) == s.charAt(j))
            {
                j++;
                lps[i] = j;
                i++;
            }
            
            //if not equal, decrement prefix length, to see which prefix matches my current suffix
            else if(s.charAt(i) != s.charAt(j) && j>0)
            {
                j = lps[j-1];
            }
            //if not equal, for first char, we know its valid lps, so inc i
            else if(s.charAt(i) != s.charAt(j) && j==0)
            {
                //lps[i]=0;
                i++;
            }
        }
        
        return lps;
    }
}
/*for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}*/