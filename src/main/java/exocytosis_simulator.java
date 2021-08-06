/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */


import ExocytosisSimulator.Simulator.SimulatorGui;
import ij.IJ;
import ij.ImageJ;
import ij.plugin.PlugIn;



/**
 *Philippe BUN
 *Junjun LIU
 *
 * @author 
 */
public class exocytosis_simulator implements PlugIn {
	
	/**
	 * Main method.
	 */
	
	//GUI windows
	@Override
	public void run(String arg) {

		new SimulatorGui(); 
		
	}
	
	public void showAbout() {

	}
	

	/**
	 * Main method for debugging.
	 *
	 * For debugging, it is convenient to have a method that starts ImageJ, loads
	 * an image and calls the plugin, e.g. after setting breakpoints.
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {
		// set the plugins.dir property to make the plugin appear in the Plugins menu
		Class<?> clazz = exocytosis_simulator.class;
		String url = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class").toString();
		String pluginsDir = url.substring("file:".length(), url.length() - clazz.getName().length() - ".class".length());
		System.setProperty("plugins.dir", pluginsDir);

		// start ImageJ
		new ImageJ();


		// run the plugin
		IJ.runPlugIn(clazz.getName(), "");
	}

	
}
