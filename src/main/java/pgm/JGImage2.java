package main.java.pgm;

//JGImage2.java
// Program to input PGM format images or multiple alignments
// PGM input is (c) 2000 John R. Smith with modification by Randolph L. McKinley
// Multiple alignment input is (c) 2000 Randolph L McKinley

import java.io.*;
import java.awt.image.*;
import java.util.Random;

// declare main class
public class JGImage2 {

	// declare and initialize variables and arrays
	public int d_height = 0;
	public int d_width = 0;
	public short d_data[];
	private boolean d_imageGood = false;

	// declare modified main class
	public JGImage2(int w, int h) {
		d_height = w;
		d_width = h;
		if (w * h > 0) {
			d_data = new short[w * h];
			d_imageGood = true;
		}
	}

	// declare modified main class
	public JGImage2() {
		this(0, 0);
	}

	public JGImage2(JGImage2 A) {
		d_height = A.height();
		d_width = A.width();
		d_imageGood = A.imageGood();
		if (A.d_imageGood) {
			d_data = (short[]) A.d_data.clone();
		}
	}

	// declare modified main class
	public JGImage2(String filename) {
		this(0, 0);
		readpgm(filename);
	}

	// read bytes from input file
	private byte readByte(InputStream i) throws IOException {
		int c = i.read();
		if (c == -1)
			throw new IOException();
		return (byte) c;
	}

	private int readInt(InputStream i) throws IOException {
		int c = i.read();
		if (c == -1)
			throw new IOException();
		return (int) c;
	}

	// method to read PGM format image or sequence file
	public boolean readpgm(String filename) {
		try {
			DataInputStream is = new DataInputStream(new FileInputStream(
					filename));
			StreamTokenizer st = new StreamTokenizer(is);
			FileReader text = new FileReader(filename);
			BufferedReader buff = new BufferedReader(text);
			st.nextToken();
			text.close();

			st.nextToken();
			d_width = (int) st.nval;
			st.nextToken();
			d_height = (int) st.nval;
			st.nextToken();
			d_data = new short[pels()];

			for (int i = 0; i < pels(); ++i) {
				d_data[i] = (short) readByte(is);

				// assign random value to insertions/deletions in alignment

			}

			// set flag for good image/sequence
			d_imageGood = true;

		}

		// throw exception on error condition
		catch (IOException io) {
			System.out.println("ERROR reading image: " + width() + ", "
					+ height());
			d_imageGood = false;
			d_width = d_height = 0;
			d_data = null;
			return false;
		}
		return true;
	}

	// method for writing PGM format image file
	public boolean writepgm(String filename) {
		if (!imageGood())
			return false;
		try {
			String s = new String("P5\n");
			s += d_width;
			s += " ";
			s += d_height;
			s += "\n";
			s += 255;
			s += "\n";
			DataOutputStream os = new DataOutputStream(new FileOutputStream(
					filename));
			os.writeBytes(s);
			for (int i = 0; i < pels(); ++i) {
				os.write(d_data[i]);
			}
		} catch (IOException io) {
			System.out.println("ERROR writing image ");
			return false;
		}
		return true;
	}

	// declare public variables
	public int width() {
		return d_width;
	}

	public int height() {
		return d_height;
	}

	public int pels() {
		return d_width * d_height;
	}

	public short[] data() {
		return d_data;
	}

	public boolean imageGood() {
		return d_imageGood;
	}

	// get image/sequence data
	int[] getIntData() {
		if (!imageGood())
			return null;
		int temp[] = new int[pels()];
		int d;
		for (int i = 0; i < pels(); i++) {
			d = d_data[i];
			if (d < 0)
				d += 256;
			temp[i] = (0xff << 24) | (d << 16) | (d << 8) | d;
		}
		return temp;
	}

	// class for getting image/sequence
	MemoryImageSource getAWTImage() {
		int[] temp = getIntData();
		return (temp != null) ? (new MemoryImageSource(width(), height(), temp,
				0, width())) : null;
	}

}
