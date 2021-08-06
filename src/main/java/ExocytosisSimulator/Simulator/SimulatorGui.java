package ExocytosisSimulator.Simulator;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;

import ij.measure.Calibration;
import ij.process.ShortProcessor;
import net.miginfocom.swing.MigLayout;

public class SimulatorGui {
	ImagePlus imp;
	ImageStack is;
	Calibration cal;
	int nSlice;
	double PSFxy;
	double PSFZ;
	int globalMAX = 0;
	DefaultTableModel list_dtm;
	Button addButton = new Button("Add");
	Button finishButton = new Button("Finish");


	public SimulatorGui() {
		Random RandomLocation = new Random();
		JFrame imageparametresWindows = new JFrame("Create image");

		JLabel pixelSiezFieldLable = new JLabel("Pixel size (µm): ");
		JLabel timeInervalFieldLable = new JLabel("Time interval (s) ");
		JLabel timeFieldLable = new JLabel("Duration (frames) ");
		JLabel PSFSiezFieldLable = new JLabel("PSFxy size (µm): ");
		JLabel PSFZFieldLable = new JLabel("PSFZ size (µm): ");
		JTextField pixelSiezField = new JTextField("0.16", 5);
		JTextField timeInervalField = new JTextField("1", 5);
		JTextField timeField = new JTextField("60", 5);
		JTextField PSFSizeField = new JTextField("0.4", 5);
		JTextField PSFZField = new JTextField("1.2", 5);
		Panel imageparametresWindowsPanel = new Panel();
		imageparametresWindowsPanel.setLayout(new MigLayout("", "[][]", "[][]"));
		imageparametresWindowsPanel.add(pixelSiezFieldLable, "cell 0 0");
		imageparametresWindowsPanel.add(pixelSiezField, "cell 1 0");
		imageparametresWindowsPanel.add(timeInervalFieldLable, "cell 0 1");
		imageparametresWindowsPanel.add(timeInervalField, "cell 1 1");
		imageparametresWindowsPanel.add(timeFieldLable, "cell 0 1");
		imageparametresWindowsPanel.add(timeField, "cell 1 1");
		imageparametresWindowsPanel.add(PSFSiezFieldLable, "cell 0 2");
		imageparametresWindowsPanel.add(PSFSizeField, "cell 1 2");
		imageparametresWindowsPanel.add(PSFZFieldLable, "cell 0 3");
		imageparametresWindowsPanel.add(PSFZField, "cell 1 3");
		Panel buttonPanel1 = new Panel();
		buttonPanel1.setLayout(new FlowLayout());
		Button cancelButton = new Button("Cancel");
		cancelButton.addActionListener((ActionEvent e) -> {
			imageparametresWindows.dispose();
		});
		Button generateImageButton = new Button("Generate image");

		buttonPanel1.add(cancelButton);
		buttonPanel1.add(generateImageButton);
		imageparametresWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageparametresWindows.setResizable(false);
		imageparametresWindows.setSize(400, 200);
		imageparametresWindows.setLayout(new FlowLayout());
		imageparametresWindows.add(imageparametresWindowsPanel);
		imageparametresWindows.add(buttonPanel1);
		imageparametresWindows.setVisible(true);

		JFrame vesucleParametresWindows = new JFrame("Secretion simulator");

		JCheckBox useBleaching = new JCheckBox("", false);
		JCheckBox useSecreting = new JCheckBox("", true);
		JCheckBox useRebounding = new JCheckBox("", false);
		JCheckBox useVibrating = new JCheckBox("", false);
		JCheckBox useCruising = new JCheckBox("", false);
		JCheckBox isDiffusion2D = new JCheckBox("2D diffusion", true);
		JLabel BleachingFieldLabel = new JLabel("Q. Yield and Bleaching coef: ");
		JLabel radiusFieldLabel = new JLabel("Vesicule Radius (µm): ");
		JLabel XposFieldLabel = new JLabel("Vesicule X position (pixels): ");
		JLabel YposFieldLabel = new JLabel("Vesicule Y position (pixels): ");
		JLabel ZposFieldLabel = new JLabel("Vesicule Z position (µm): ");
		JLabel DensityFieldLabel = new JLabel("Vesicule density (Nb particles): ");
		JLabel DiffusionFieldLabel = new JLabel("Diffusion coefficient (µm²/s): ");
		JLabel secretingLabel = new JLabel("Secretion time (s): ");
		JLabel recruitingLabel = new JLabel("Recruiting speed (µm/s): ");
		JLabel fusionLabel = new JLabel("fusion speed (µm/s): ");
		JLabel reboundingTimeLabel = new JLabel("rebounding time (frame): ");
		JLabel reboundLabel = new JLabel("rebounding speed (µm/s): ");
		JLabel vibratingLabel = new JLabel("vibrating rate: ");
		JLabel cruisingLabel = new JLabel("Cruising Speed (µm/s): ");
		JTextField qYieldField = new JTextField("1", 5);
		JTextField BleachingField = new JTextField("1", 5);
		JTextField radiusField = new JTextField("0.2", 5);
		JTextField XposField = new JTextField(String.valueOf(RandomLocation.nextInt(480) + 16 ), 5);
		JTextField YposField = new JTextField(String.valueOf(RandomLocation.nextInt(480) + 16 ), 5);
		JTextField ZposField = new JTextField("2", 5);
		JTextField DensityField = new JTextField("2000", 5);
		JTextField DiffusionField = new JTextField("0.1", 5);
		JTextField secretingField = new JTextField("5", 5);
		JTextField recruitingField = new JTextField("0.4", 5);
		JTextField fusionField = new JTextField("0.05", 5);
		JTextField reboundingTimeField = new JTextField("2", 5);
		JTextField reboundField = new JTextField("0.2", 5);
		JTextField vibratingField = new JTextField("0.2", 5);
		JTextField cruisingField = new JTextField("0.2", 5);
	
		
		
		Panel ParametresPanel = new Panel();
		ParametresPanel.setLayout(new MigLayout("", "[][][][]", "[][]"));
		ParametresPanel.add(useBleaching, "cell 0 0");
		ParametresPanel.add(BleachingFieldLabel, "cell 1 0");
		ParametresPanel.add(qYieldField, "cell 2 0");
		ParametresPanel.add(BleachingField, "cell 3 0");
		ParametresPanel.add(radiusFieldLabel, "cell 1 1");
		ParametresPanel.add(radiusField, "cell 2 1");
		ParametresPanel.add(XposFieldLabel, "cell 1 2");
		ParametresPanel.add(XposField, "cell 2 2");
		ParametresPanel.add(YposFieldLabel, "cell 1 3");
		ParametresPanel.add(YposField, "cell 2 3");
		ParametresPanel.add(ZposFieldLabel, "cell 1 4");
		ParametresPanel.add(ZposField, "cell 2 4");
		ParametresPanel.add(DensityFieldLabel, "cell 1 5");
		ParametresPanel.add(DensityField, "cell 2 5");
		ParametresPanel.add(DiffusionFieldLabel, "cell 1 6");
		ParametresPanel.add(DiffusionField, "cell 2 6");
		ParametresPanel.add(isDiffusion2D, "cell 3 6");
		ParametresPanel.add(useSecreting, "cell 0 7");
		ParametresPanel.add(secretingLabel, "cell 1 7");
		ParametresPanel.add(secretingField, "cell 2 7");
		ParametresPanel.add(recruitingLabel, "cell 1 8");
		ParametresPanel.add(recruitingField, "cell 2 8");
		ParametresPanel.add(fusionLabel, "cell 1 9");
		ParametresPanel.add(fusionField, "cell 2 9");
		ParametresPanel.add(useRebounding, "cell 0 10");
		ParametresPanel.add(reboundingTimeLabel, "cell 1 10");
		ParametresPanel.add(reboundingTimeField, "cell 2 10");
		ParametresPanel.add(reboundLabel, "cell 1 11");
		ParametresPanel.add(reboundField, "cell 2 11");
		ParametresPanel.add(useVibrating, "cell 0 12");
		ParametresPanel.add(vibratingLabel, "cell 1 12");
		ParametresPanel.add(vibratingField, "cell 2 12");
		ParametresPanel.add(useCruising, "cell 0 13");
		ParametresPanel.add(cruisingLabel, "cell 1 13");
		ParametresPanel.add(cruisingField, "cell 2 13");
		Panel buttonPanel2 = new Panel();
		buttonPanel2.setLayout(new FlowLayout());

		buttonPanel2.add(addButton);
		buttonPanel2.add(finishButton);


		vesucleParametresWindows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vesucleParametresWindows.setResizable(false);
		vesucleParametresWindows.setLayout(new BorderLayout());
		vesucleParametresWindows.add("Center", ParametresPanel);
		vesucleParametresWindows.add("South", buttonPanel2);
		vesucleParametresWindows.pack();

		generateImageButton.addActionListener((ActionEvent e) -> {
			is = new ImageStack(512, 512);
			PSFxy = Double.parseDouble(PSFSizeField.getText());
			PSFZ = Double.parseDouble(PSFZField.getText());
			nSlice = Integer.parseInt(timeField.getText());
			for (int i = 1; i <= nSlice; i++) {
				is.addSlice(new ShortProcessor(512, 512));
			}
			imp = new ImagePlus("Simulation", is);
			cal = imp.getCalibration();
			cal.setUnit("µm");
			cal.setTimeUnit("sec");
			cal.pixelWidth = Double.parseDouble(pixelSiezField.getText());
			cal.pixelHeight = Double.parseDouble(pixelSiezField.getText());
			cal.pixelDepth = Double.parseDouble(pixelSiezField.getText());
			cal.frameInterval = Double.parseDouble(timeInervalField.getText());
			imp.setCalibration(cal);
			imp.setDisplayMode(IJ.GRAYSCALE);
			imp.show();
			
			vesucleParametresWindows.setVisible(true);
			imageparametresWindows.dispose();

		});

		addButton.addActionListener((ActionEvent e) -> {
			parameter p = new parameter();

			p.Bleaching = useBleaching.isSelected();
			p.Secreting = useSecreting.isSelected();
			p.Rebounding = useRebounding.isSelected();
			p.Vibrating = useVibrating.isSelected();
			p.Cruising = useCruising.isSelected();
			p.is2dDiff = isDiffusion2D.isSelected();
			p.qYield = Double.parseDouble(qYieldField.getText());
			p.bleachingRate = Double.parseDouble(BleachingField.getText());
			p.radius = Double.parseDouble(radiusField.getText());
			p.Xpos = Double.parseDouble(XposField.getText());
			p.Ypos = Double.parseDouble(YposField.getText());
			p.Zpos = Double.parseDouble(ZposField.getText());
			p.density = Integer.parseInt(DensityField.getText());
			p.diffusion = Double.parseDouble(DiffusionField.getText());
			p.secretion_start_time = Double.parseDouble(secretingField.getText());
			p.recruitingV = Double.parseDouble(recruitingField.getText());
			p.fusionV = Double.parseDouble(fusionField.getText());
			p.reboundingTime = Double.parseDouble(reboundingTimeField.getText());
			p.reboundV = Double.parseDouble(reboundField.getText());
			p.vibrateV = Double.parseDouble(vibratingField.getText());
			p.cruisingV = Double.parseDouble(cruisingField.getText());


			addButton.setEnabled(false);
			finishButton.setEnabled(false);
			new generateVesicle(p).start();
			
			

			XposField.setText(String.valueOf(RandomLocation.nextInt(480) + 16));
			YposField.setText(String.valueOf(RandomLocation.nextInt(480) + 16));

		});

		finishButton.addActionListener((ActionEvent e) -> {
			vesucleParametresWindows.dispose();
		});

	}

	class generateVesicle extends Thread {
		
		parameter p = new parameter();

		public generateVesicle(parameter p) {
			this.p = p;
		}

		public void run() {
			
			

			Vesicle3D aVesicle = new Vesicle3D(p.Xpos * cal.pixelWidth, p.Ypos * cal.pixelHeight, p.Zpos, p.density,
					p.radius, p.diffusion, p.qYield, p.is2dDiff);
			
			double recruit_finish_time = 0;
			double fusion_finish_time = 0;
			
			double time = 0;
			double time_left = 0;
			
			Random random = new Random();
			
			
			if (p.Secreting) {

				if (aVesicle.z > p.radius) {
					double time_needed_for_recriting = (aVesicle.z - p.radius) / p.recruitingV;
					
					if (time_needed_for_recriting > p.secretion_start_time) {
						aVesicle.z = p.recruitingV * p.secretion_start_time + p.radius;
						// ZposField.setText(String.valueOf(aVesicle.z));
						recruit_finish_time = 0;
						fusion_finish_time = p.secretion_start_time + 2 * p.radius / p.fusionV;
					} else {
						recruit_finish_time = p.secretion_start_time - time_needed_for_recriting;
						fusion_finish_time = p.secretion_start_time + 2 * p.radius / p.fusionV;
					}
				} else {
					recruit_finish_time = p.secretion_start_time;
					fusion_finish_time = p.secretion_start_time + (aVesicle.z + p.radius) / p.fusionV;
				}
				for (int i = 1; i <= nSlice; i++) {
					// Acquisition
					acquisition( aVesicle,  i);
				
					if (time >= recruit_finish_time && time < p.secretion_start_time) {
						if (aVesicle.z - p.recruitingV * cal.frameInterval >= p.radius) {
							aVesicle.move(0, 0, -p.recruitingV, cal.frameInterval);
						}
						if (aVesicle.z - p.recruitingV * cal.frameInterval < p.radius
								&& aVesicle.z - p.recruitingV * cal.frameInterval >= -p.radius) {
							time_left = cal.frameInterval - (aVesicle.z - p.radius) / p.recruitingV;
							aVesicle.z = p.radius;
							aVesicle.move(0, 0, -p.fusionV, time_left);
							if (time - p.secretion_start_time > 0) {
								if (p.is2dDiff)
									aVesicle.exocytosis2d(time - p.secretion_start_time);
								else
									aVesicle.exocytosis3d(time - p.secretion_start_time);
							}


						}
						if (aVesicle.z - p.recruitingV * cal.frameInterval < -p.radius) {
							time_left = cal.frameInterval - (aVesicle.z - p.radius) / p.recruitingV;
							aVesicle.z = -p.radius;
							if (time - p.secretion_start_time > 0) {
								if (p.is2dDiff)
									aVesicle.exocytosis2d(time - p.secretion_start_time);
								else
									aVesicle.exocytosis3d(time - p.secretion_start_time);
							}

						}
					}
					if (time >= p.secretion_start_time && time < fusion_finish_time) {

						if (aVesicle.z - p.fusionV * cal.frameInterval >= -p.radius) {
							aVesicle.move(0, 0, -p.fusionV, cal.frameInterval);

							if (p.is2dDiff)
								aVesicle.exocytosis2d(time - p.secretion_start_time);
							else
								aVesicle.exocytosis3d(time - p.secretion_start_time);
						} else {
							aVesicle.z = -p.radius;
							if (p.is2dDiff)
								aVesicle.exocytosis2d(time - p.secretion_start_time);
							else
								aVesicle.exocytosis3d(time - p.secretion_start_time);
						}
					}
					if (time >= fusion_finish_time) {
						aVesicle.z = -p.radius;
						if (p.is2dDiff)
							aVesicle.exocytosis2d(time - p.secretion_start_time);
						else
							aVesicle.exocytosis3d(time - p.secretion_start_time);
					}
					time = time + cal.frameInterval;
					//timelaps = timelaps+ cal.frameInterval;
					if (p.Bleaching) {
						aVesicle.bleach(p.bleachingRate);
					}
				}
			}
			
			if (p.Rebounding) {
				if (aVesicle.z > p.radius) {
					double time_needed = (aVesicle.z - p.radius) / p.reboundV;
					if (time_needed > p.reboundingTime) {
						aVesicle.z = p.reboundV * p.reboundingTime + p.radius;
						recruit_finish_time = 0;
					} else {
						recruit_finish_time = p.reboundingTime - time_needed;
					}
				} else {
					recruit_finish_time = p.reboundingTime;
				}
				for (int i = 1; i <= nSlice; i++) {
					

					
					// Acquisition
					acquisition( aVesicle,  i);
					// Move
					if (time >= recruit_finish_time && time < p.reboundingTime) {
						if (aVesicle.z - p.reboundV * cal.frameInterval >= p.radius) {
							aVesicle.move(0, 0, -p.reboundV, cal.frameInterval);
						} else {
							time_left = cal.frameInterval - (aVesicle.z - p.radius) / p.reboundV;
							aVesicle.z = p.radius;
							aVesicle.move(0, 0, p.reboundV, time_left);
						}
					}
					if (time >= p.reboundingTime) {
						aVesicle.move(0, 0, p.reboundV, cal.frameInterval);
					}
					time = time + cal.frameInterval;
					if (p.Bleaching) {
						aVesicle.bleach(p.bleachingRate);
					}
				}
			}
			if (p.Vibrating) {

				for (int i = 1; i <= nSlice; i++) {
					

					
					// Acquisition
					acquisition( aVesicle,  i);
					// Move
					aVesicle.move(p.vibrateV * random.nextGaussian(), p.vibrateV * random.nextGaussian(), 0,
							cal.frameInterval);

					time = time + cal.frameInterval;
					if (p.Bleaching) {
						aVesicle.bleach(p.bleachingRate);
					}
				}
			}
			if (p.Cruising) {

				double coefVx, coefVy;
				for (int i = 1; i <= nSlice; i++) {
					
					

					// Acquisition
					acquisition( aVesicle,  i);
					// Move
					coefVx = random.nextDouble() * 2 - 1;
					coefVy = Math.sqrt(1 - coefVx * coefVx);
					if (random.nextBoolean())
						aVesicle.move(p.cruisingV * coefVx, p.cruisingV * coefVy, 0, cal.frameInterval);
					else
						aVesicle.move(p.cruisingV * coefVx, p.cruisingV * -coefVy, 0, cal.frameInterval);
					time = time + cal.frameInterval;
					if (p.Bleaching) {
						aVesicle.bleach(p.bleachingRate);
					}
				}
			}

			imp.setDisplayRange(0, globalMAX);
			imp.updateAndDraw();
			addButton.setEnabled(true);
			finishButton.setEnabled(true);
			
		}
		
		private void acquisition(Vesicle3D aVesicle, int i) {
			int w = is.getWidth();
			int h = is.getHeight();
			int pixel = 0;
			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					if ((Math.pow(x * cal.pixelWidth - aVesicle.x, 2) + Math.pow(y * cal.pixelHeight - aVesicle.y, 2)) < Math.pow(PSFxy * 3 + p.radius,2)
							&& aVesicle.z < PSFZ * 3 + p.radius) {
						pixel = (int) aVesicle.getIntensity(x * cal.pixelWidth, y * cal.pixelHeight, PSFxy,
								PSFZ);
						if (pixel > globalMAX)
							globalMAX = pixel;
						is.getProcessor(i).set(x, y, pixel);
					}
				}
		
			}
		}
		

	}

	
	
	private class parameter {
		boolean Bleaching;
		boolean Secreting;
		boolean Rebounding;
		boolean Vibrating;
		boolean Cruising;
		boolean is2dDiff;
		double qYield;
		double bleachingRate;
		double radius;
		double Xpos;
		double Ypos;
		double Zpos;
		int density;
		double diffusion;
		double secretion_start_time;
		double recruitingV;
		double fusionV;
		double reboundingTime;
		double reboundV;
		double vibrateV;
		double cruisingV;

		public parameter() {

		}
	}

}
