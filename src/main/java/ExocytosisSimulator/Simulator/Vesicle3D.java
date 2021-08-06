package ExocytosisSimulator.Simulator;

import java.util.Random;
import java.util.Vector;

public class Vesicle3D {
	public double x, y, z;
	private double density, radius;
	private double qYield;
	private Vector<Molecule> population = new Vector<Molecule>(); 

	public Vesicle3D(double ax, double ay, double az, double aDensity, double aRadius, double aDiffuse, double qYield, boolean diffusion2D) {
		this.x = ax;
		this.y = ay;
		this.z = az;
		this.density = aDensity;
		this.radius = aRadius;
		this.qYield = qYield;
		double mz = 0;
		Random random = new Random();
		for (int i=0; i<density; i++ ) {
			double mx = (2*random.nextDouble()-1)*radius;
			double my = (2*random.nextDouble()-1)*Math.sqrt(Math.pow(radius, 2)-Math.pow(mx, 2));
			if (!diffusion2D) {
				mz = (2*random.nextDouble()-1)*Math.sqrt(Math.pow(radius, 2)-Math.pow(mx, 2)-Math.pow(my, 2));
			}
				
			population.addElement(new Molecule(mx, my, mz, aDiffuse));
		}	
	}
	
	public void move(double vx, double vy, double vz, double t) {
		x = x + vx*t;
		y = y + vy*t;
		z = z + vz*t;
	}
	public void exocytosis3d( double timelaps) {
		for (int i=0; i<density; i++ ) {		
			population.elementAt(i).diffuse3dConfined(timelaps, z, 0);
		}	
	}
	public void exocytosis2d( double timelaps) {
		for (int i=0; i<density; i++ ) {		
			population.elementAt(i).diffuse2d(timelaps);
		}	
	}
	public double getIntensity(double Xcoor, double Ycoor, double PSFxy, double PSFZ ) {
		double intensity = 0;
		double W02 =  Math.pow(PSFxy,2);
		double Wz2 =  Math.pow(PSFZ,2);
		double distance_xy2;
		double distance_z2;

		for (int i = 0; i < population.size(); i++) {
			distance_xy2 = Math.pow( Xcoor - x - population.elementAt(i).mx, 2) + Math.pow( Ycoor - y - population.elementAt(i).my, 2);
			distance_z2 = Math.pow( z + population.elementAt(i).mz, 2);
			intensity = intensity + qYield * Math.exp( -2 *distance_xy2/W02 ) *Math.exp (-2* distance_z2/Wz2);
		}
		
		if (intensity >65535) {
			intensity = 65535;
		}
		
		return intensity;
	}
	public void bleach(double coeff) {
		qYield = qYield*coeff;
	}
	
	public class Molecule{
		public double mx, mx_initial_exocytosis, my, my_initial_exocytosis, mz, D;
		public 	Molecule(double ax, double ay, double az, double diffuseCoeff) {
			this.mx= ax;
			this.mx_initial_exocytosis = ax;
			this.my = ay;
			this.my_initial_exocytosis = ay;
			this.mz = az;
			this.D = diffuseCoeff;
		}
		public void diffuse3d(double T) {
			Random random = new Random();
			mx = mx + random.nextGaussian()*Math.sqrt(2*D*T);
			my = my + random.nextGaussian()*Math.sqrt(2*D*T);
			mz = mz + random.nextGaussian()*Math.sqrt(2*D*T);
			if (mz > 0) mz = -mz;
		}
		public void diffuse3dConfined(double T, double zVesicle, double zlimit) {
			Random random = new Random();
			double tempZ;
			if (mz + zVesicle <= zlimit) {
				
				mx = mx + random.nextGaussian()*Math.sqrt(2*D*T);
				my = my + random.nextGaussian()*Math.sqrt(2*D*T);
				tempZ = mz + random.nextGaussian()*Math.sqrt(2*D*T);	
				if ( tempZ +zVesicle > zlimit) {
					mz = - (tempZ+zVesicle);
				} 
				else {
					mz = tempZ;
				}
			}
			else{
				tempZ = mz + random.nextGaussian()*Math.sqrt(2*D*T);
				if (tempZ<=zlimit) {
					mx = mx + random.nextGaussian()*Math.sqrt(2*D*T);
					my = my + random.nextGaussian()*Math.sqrt(2*D*T);
					mz = tempZ;
				}
			}
		}
		public void diffuse2d(double timelaps) {
			Random random = new Random();
			mx = mx_initial_exocytosis + random.nextGaussian()*Math.sqrt(2*D*timelaps);
			my = my_initial_exocytosis + random.nextGaussian()*Math.sqrt(2*D*timelaps);
		}
		public void diffuse2drond(double timelaps) {
			Random random = new Random();
			double gauss = random.nextGaussian()*Math.sqrt(4*D*timelaps);
			double randomRadian = 2 * random.nextDouble() * Math.PI;
			mx = mx_initial_exocytosis + gauss * Math.sin(randomRadian) ;
			my = my_initial_exocytosis + gauss * Math.cos(randomRadian);
		}
	}
}

