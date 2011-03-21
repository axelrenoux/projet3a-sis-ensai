package calculsDesClusters.axe;

public class CoupleAxe {
	
	private Axe axe1;
	private Axe axe2;
	private double variance;
	
	
	
	public CoupleAxe(Axe axe1, Axe axe2) {
		super();
		this.axe1 = axe1;
		this.axe2 = axe2;
	}
	public Axe getAxe1() {
		return axe1;
	}
	public void setAxe1(Axe axe1) {
		this.axe1 = axe1;
	}
	public Axe getAxe2() {
		return axe2;
	}
	public void setAxe2(Axe axe2) {
		this.axe2 = axe2;
	}
	public double getVariance() {
		return variance;
	}
	public void setVariance(double variance) {
		this.variance = variance;
	}
	
	

}
