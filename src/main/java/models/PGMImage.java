package main.java.models;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import main.java.exceptions.ReadPGMException;
import main.java.exceptions.WritePGMException;


public class PGMImage extends Image {

	public void readPGMData(String filename) throws ReadPGMException{
		
	}
	
	public void writePGMData (String filename) throws WritePGMException{
		try {
			String s = generateHeader();
			DataOutputStream os = new DataOutputStream(new FileOutputStream(
					filename));
			os.writeBytes(s);
			short[] imageData = this.getImageData();
			for (int i = 0; i < this.getImageSize(); ++i) {
				os.write(imageData[i]);
			}
			os.close();
		} catch (IOException io) {
			throw new WritePGMException("Failed to write to PGM file");
		}
	}
	
	public String generateHeader() {
		String s = new String("P5\n");
		s += this.getWidth();
		s += " ";
		s += this.getHeight();
		s += "\n";
		s += 255;
		return s += "\n";
	}
}
