package com.src.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileHandler {

    public List<String> getCarRegistrations(String fileName) throws IOException
    {
        List<String> fileData = new ArrayList<String>();
        FileInputStream inputStream = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = br.readLine()) != null)
        {
            Pattern patt = Pattern.compile("([A-Z]+[0-9]+\\s?[A-Z]*[0-9]*)([a-z\\s]+)*([A-Z]+[0-9]+\\s?[A-Z]*[0-9]*)*");
            Matcher match = patt.matcher(line);
            if(match.find())
            {
                    if(match.group(1) != null) {
                        String reg = match.group(1).toString().replaceAll("\\s","");
                        fileData.add(reg);
                    }
                    if(match.group(3) != null) {
                        String reg = match.group(3).toString().replaceAll("\\s","");
                        fileData.add(reg);
                    }
            }

        }
        return fileData;
    }

    public HashMap<String,ArrayList<String>> getExpecetedData(String outputFile) throws IOException
    {
        FileInputStream inputStream = new FileInputStream(outputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        HashMap<String,ArrayList<String>> outData = new HashMap<String,ArrayList<String>>();
        String line;
        while((line = br.readLine()) != null)
        {
            if(line.contains("REGISTRATION"))
                continue;
            ArrayList<String> carData = new ArrayList<String>();
            for(String data: line.split(","))
                carData.add(data);
            outData.put(carData.get(0),carData);
        }
        return outData;
    }
}
