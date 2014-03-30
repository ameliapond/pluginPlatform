package cle.producer.data;

public class Size {

	double width, height;

	public Size() {

	}
	
	public Size(double width,double height) {
		
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Size [width=" + width + ", height=" + height + "]";
	}
	
	
}
