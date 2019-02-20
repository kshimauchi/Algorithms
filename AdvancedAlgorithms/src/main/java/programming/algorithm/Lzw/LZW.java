package programming.algorithm.Lzw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {

    //private int dictionarySize =256; //This is fine if called once
    //Receives Strings
    public List<Integer> compress(String text){
        if(text == null) return null;
        int dictionarySize = 256;
        List<Integer> result = new ArrayList<Integer>();

        Map<String, Integer> dictionary = new HashMap<String, Integer>();

        for(int i =0 ; i < dictionarySize; i++){
            dictionary.put("" + (char) i, i); //need to cast the i here

        }
        String previous = "";
        for(char c: text.toCharArray()){
            String combined = previous + c ;
            if(dictionary.containsKey( combined )){
                previous = combined;

            }else{
                result.add(dictionary.get(previous));
                dictionary.put(combined, dictionarySize++);
                previous = "" + c;
            }
        }
        if(!previous.equals(""))
            result.add(dictionary.get(previous));
        return result;
    }
    //Receives list of integers
    public String decompress(List<Integer> compressed ){

        if(compressed == null) return null;
        int dictionarySize = 256;

        Map<Integer, String> dictionary = new HashMap<Integer, String>();

        for(int i = 0; i < dictionarySize; i++){
            dictionary.put(i, ""+(char) i);  //opposite to compress
        }
        //Multiple cast, casted to int first, then to character
        String previous = "" + (char)(int)compressed.remove(0);
        //Here we are dealing with integers
        StringBuilder result = new StringBuilder(previous);

        for(int j : compressed ){
            String combined;
            //if we find j in the dictionary
            if(dictionary.containsKey(j)){
                //we combine it with the value of the dictionary
                combined = dictionary.get( j );

            }else if(j == dictionarySize){
                //find a character that has not been calculated yet
                //ex: 270
                combined = previous + previous.charAt(0);  //special case
            }else{
            //if we do not find it
                return "-1";
            }
            result.append( combined ); // result = a b
            dictionary.put(dictionarySize++, previous + combined.charAt(0));
            previous = combined;
        }
        return result.toString();
    }
}
