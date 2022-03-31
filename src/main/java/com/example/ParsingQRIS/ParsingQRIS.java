package com.example.ParsingQRIS;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gemuruh Geo Pratama
 * @created 31/03/2022-14.10
 */
@Service
public class ParsingQRIS {
    public Map<String, String[]> doParse(String qrisString) {
        try {
            Map<String, String[]> resultMap = new HashMap<>();
            //Check Always Start With 00
            String start = qrisString.substring(0,2);
            if(!start.equals("00")) {
                throw new IllegalStateException("Invalid Data, Not Start With Value 00");
            }
            int lengthBuffer = qrisString.length();
            while (lengthBuffer > 0) {
                String dataGuard = qrisString.substring(0,4);
                String lengthAsString = dataGuard.substring(2);
                int length;
                if(lengthAsString.startsWith("0")) {
                    length = Integer.parseInt(lengthAsString.substring(1));
                }else {
                    length = Integer.parseInt(lengthAsString);
                }

                String data = qrisString.substring(4, 4+length);
                resultMap.put(dataGuard.substring(0,2), new String[]{lengthAsString, data});

                qrisString = qrisString.substring(length + dataGuard.length());
                lengthBuffer = qrisString.length();

            }
            return resultMap;
        }catch (Exception e) {
            throw new IllegalStateException("Invalid QRIS Format");
        }

    }
}
