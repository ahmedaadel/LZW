package lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class compression {

    public List<Integer> comp(String c) {
        String s = c;
        Map<String, Integer> Dic = new HashMap<>();
        List<Integer> compress = new ArrayList<>();
        int dicSize = 256, ctr = 0;
        for (int i = 0; i < 256; i++) {
            Dic.put("" + (char) i, i);
        }

        for (int i = 0; i < s.length(); i++) {
            if (ctr >= s.length()) {
                break;
            }
            String test = "", e = "";
            test += s.charAt(ctr);

            while (Dic.containsKey(test)) {
                ctr++;
                if (ctr >= s.length()) {
                    break;
                }
                e = test;
                test += s.charAt(ctr);
            }

            if (ctr != s.length()) {
                compress.add(Dic.get(e));
                Dic.put(test, dicSize++);
            } else {
                compress.add(Dic.get(test));
            }

        }
        return compress;
    }
}

class Decompression 
{
    public List<String> Decom()
    {
        List<Integer> com=new ArrayList<>();
        List<String> dee=new ArrayList<>();
        int comNum=1;
        System.out.println("enter compressed number");
        while (comNum!=0)
        {
            Scanner input=new Scanner(System.in);
            comNum=input.nextInt();
            com.add(comNum);
        }
        Map<Integer,String> Dic=new HashMap<>();
        
        for (int i =0;i<256;i++)
            Dic.put(i, "" + (char)i);
        int temp , dicSize=256,ctr=0;
        
        
        for (int i=0;i<com.size()-1;i++) {
            temp=com.get(i);
            if (i==0)
            {
               dee.add(Dic.get(temp));
               continue;
               
            }
            if (Dic.containsKey(temp))
            {
                 dee.add(Dic.get(temp));
                 String s= dee.get(dee.size()-1);
                 String newAdded= dee.get(dee.size()-2)+s.charAt(0);
                 Dic.put(dicSize++, newAdded);
            }
            
            else
            {
                String s= dee.get(dee.size()-1);
                String newAdded= s+s.charAt(0);
                dee.add(newAdded);
                Dic.put(dicSize++, newAdded);
            }
            
            
        }
        
        return dee;
    }
}

public class Lzw {

    public static void main(String[] args) {
        
        compression com=new compression();
        System.out.println(com.comp("TOBEORNOTTOBEORTOBEORNOT"));

                
        
        Decompression de=new Decompression();
        System.out.println(de.Decom());
    }

}


