package main.java.models;

public class Image {

	private int width;
	private int height;
	private int imageSize;
	private short[] imageData;
	private boolean isValidData;

	public Image() {
		this.width = 0;
		this.height = 0;
		this.imageSize = 0;
		this.isValidData = false;
	}

	public Image(int width, int height) {
		this.width = width;
		this.height = height;
		// no logic in pojo but should check that width*height is positive
		this.imageData = new short[width * height];
		this.imageSize = width * height;
		this.isValidData = true;
	}

	public Image(Image image) {
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.imageSize = image.getImageSize();
		this.imageData = image.getImageData();
		this.isValidData = image.isValidData();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getImageSize() {
		return imageSize;
	}

	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}

	public short[] getImageData() {
		return imageData;
	}

	public void setImageData(short[] imageData) {
		this.imageData = imageData;
	}

	public boolean isValidData() {
		return isValidData;
	}

	public void setValidData(boolean isValidData) {
		this.isValidData = isValidData;
	}

}
