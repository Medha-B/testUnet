package org.sbml.spatial.segmentation;

import java.io.File;

import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.util.ModelSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//This is for loading the saved UNet model and then testing it.
public class UnetLoadAndTest {

	private static final Logger log = LoggerFactory.getLogger(UnetLoadAndTest.class);

	public static void main(String[] args) {
		try {

			// String home = System.getProperty("user.home");
			String directory = System.getProperty("user.dir");
			String dataPath = directory + File.separator + "dataset";

			String pathToImage;
			if (args.length > 0) {
				pathToImage = args[0];
			} else {
				pathToImage = dataPath + File.separator + "raw_images" + File.separator + "F01_621w1_crop13.tif";
			}

			log.info("*****LOAD MODEL******");
			// Location where the model is saved
			// File locationTosave = new File(home + File.separator + "unetSave.zip");
			//Depends upon where the model weights are actually saved
			File locationTosave = new File(directory + File.separator + "unetSave.zip");
			ComputationGraph model = ModelSerializer.restoreComputationGraph(locationTosave);

			log.info("*****EVALUATE MODEL******");
			Inference infer = new Inference(model, pathToImage, directory);
			infer.imgOut();

		} catch (Exception e) {
			System.err.println("Oooooops");
			e.printStackTrace();
		}
	}
}
