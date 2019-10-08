package otoassistant;


public class OtoObject
{
    /*
    * Taylor Hennessy
    * celestrai
    *
    * This file creates the oto string as an object-oriented
    * object. It includes the following objects:
    *
    * String : file = Name/filepath of the .wav file
    * String : alias = Alias for the oto
    * Double : offset = The beginning of the oto (in miliseconds)
    * Double : fixed = The end of the fixed (consonant) part of
                                         the oto (in miliseconds)
    * Double : blank = The end of the
    * Double : preutterance =
    * Double : overlap =
    *
    *
    * FUNCTIONS TODO:
    * -     override < & > to evaluate alphabetically based on alias
    * -     override toString
    * -
    * -
    *
    */

    String file;
    String alias;
    Double offset;
    Double fixed;
    Double blank;
    Double preutterance;
    Double overlap;

    OtoObject(){



    }

    //constructor that parses a line from the oto into an OtoObject
    OtoObject(String line){

        int eqsign = line.indexOf('=');

        //parse out filename
        file = line.substring(0, eqsign);
        line = line.substring(eqsign + 1);

        int comma = line.indexOf(',');

        //parse alias
        alias = line.substring(0, comma);
        line = line.substring(comma + 1);

        comma = line.indexOf(',');


        //--------------------------DOUBLES--------------------------
        //parse offset
        offset = Double.parseDouble(line.substring(0, comma));
        line = line.substring(comma + 1);

        comma = line.indexOf(',');

        //parse fixed
        fixed = Double.parseDouble(line.substring(0, comma));
        line = line.substring(comma + 1);

        comma = line.indexOf(',');

        //parse blank
        blank = Double.parseDouble(line.substring(0, comma));
        line = line.substring(comma + 1);

        comma = line.indexOf(',');

        //parse preutterance
        preutterance = Double.parseDouble(line.substring(0, comma));
        line = line.substring(comma + 1);

        //parse overlap
        overlap = Double.parseDouble(line);

    }


    public String toString(){

        String returnstring = file + "=" + alias + "," + offset.toString() + ",";
        returnstring = returnstring + fixed.toString() + "," + blank.toString() + ",";
        returnstring =  returnstring + preutterance.toString() + "," + overlap.toString();

        return returnstring;
    }


}
