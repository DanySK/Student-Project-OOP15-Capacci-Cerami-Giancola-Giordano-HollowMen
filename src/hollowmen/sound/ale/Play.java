package hollowmen.sound.ale;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Play implements PlayInterface{
        
        /**
         * The {@code sketchPath} method is used to transform a fileName into an absolute path
         * 
         * @param fileName
         * @return
         */
        public String sketchPath(final String fileName) {
                return fileName;
        }
        
        /**
         * The {@code createInput} method is used to create the stream of the audio starting from the 
         * fileName,creating a BufferedInputStream
         * 
         * @param fileName
         * @return 
         * @throws FileNotFoundException
         */
        public InputStream createInput(final String fileName){
                InputStream input=null;
                try{
                 input=new BufferedInputStream(new FileInputStream(this.sketchPath(fileName)));
                }catch(FileNotFoundException e){
                        System.out.println("FILE NON TROVATO!!");
                }
                return input;
        }
        
}
