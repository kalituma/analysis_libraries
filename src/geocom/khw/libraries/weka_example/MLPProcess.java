package geocom.khw.libraries.weka_example;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.Utils;

import java.io.FileReader;
import java.io.IOException;

public class MLPProcess {
    public MLPProcess(){
        try{
            FileReader fr = new FileReader("resources/vehicledata.arff");

            Instances training = new Instances(fr);
            training.setClassIndex(training.numAttributes()-1);
            MultilayerPerceptron mlp = new MultilayerPerceptron();
            mlp.setOptions(Utils.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H 4"));

            mlp.buildClassifier(training);

            FileReader tr = new FileReader("resources/tesetdata.arff");
            Instances testdata = new Instances(tr);
            testdata.setClassIndex(testdata.numAttributes()-1);

            Evaluation eval = new Evaluation(training);
            eval.evaluateModel(mlp, testdata);

            System.out.println(eval.toSummaryString("\nResults\n*******\n",false));

            tr.close();
            fr.close();

        }catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
