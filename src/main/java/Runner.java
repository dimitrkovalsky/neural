import com.liberty.neuro.ActivationFunctions;
import com.liberty.neuro.Neuron;
import com.liberty.neuro.NeuronTrainer;

public class Runner {

  public static void main(String[] args) {
    Neuron neuron = new Neuron(2, ActivationFunctions.heavisideFunction());
    NeuronTrainer trainer = new NeuronTrainer("and.dat");
    trainer.train(neuron);
    System.out.println(neuron.evaluate(1d,1d));
    System.out.println(neuron.evaluate(1d,0d));
    System.out.println(neuron.evaluate(0d,1d));
    System.out.println(neuron.evaluate(0d,0d));

    NeuronTrainer trainer2 = new NeuronTrainer("or.dat");
    trainer2.train(neuron);
    System.out.println(neuron.evaluate(1d,1d));
    System.out.println(neuron.evaluate(1d,0d));
    System.out.println(neuron.evaluate(0d,1d));
    System.out.println(neuron.evaluate(0d,0d));

    System.out.println(neuron.getWeights());
  }
}
