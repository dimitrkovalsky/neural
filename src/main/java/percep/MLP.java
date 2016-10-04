package percep;


import com.liberty.neuro.ActivationFunctions;

public class MLP {

  private double[] inputs;
  private double[] hidden;
  private double out;
  double[][] inputHiddenWeights;
  double[] hiddenOutWeights;
  double[][] patterns = {
      {0, 0}, {1, 0}, {0, 1}, {1, 1}
  };
  double[] answers = {0, 1, 1, 0};

  public MLP() {
    inputs = new double[patterns[0].length];
    hidden = new double[2];
    inputHiddenWeights = new double[inputs.length][hidden.length];
    hiddenOutWeights = new double[hidden.length];
    init();
    train();
    test();
  }

  private void test() {
    for (double[] pattern : patterns) {
      System.arraycopy(pattern, 0, inputs, 0, inputs.length);

      evaluateOut();
      System.out.println(out);
    }

  }

  private void init() {
    for (int i = 0; i < inputHiddenWeights.length; i++) {
      for (int j = 0; j < inputHiddenWeights[i].length; j++) {
        inputHiddenWeights[i][j] = Math.random() * 0.3 + 0.1;
      }
    }

    for (int i = 0; i < hiddenOutWeights.length; i++) {
      hiddenOutWeights[i] = Math.random() * 0.3 + 0.1;
    }
  }

  public void evaluateOut() {
    for (int i = 0; i < hidden.length; i++) {
      hidden[i] = 0;
      for (int j = 0; j < inputs.length; j++) {
        hidden[i] += inputs[j] * inputHiddenWeights[j][i];
      }
      hidden[i] = ActivationFunctions.heavisideFunction().apply(hidden[i]);
    }
    out = 0;
    for (int i = 0; i < hidden.length; i++) {
      out += hidden[i] * hiddenOutWeights[i];
    }
    out = ActivationFunctions.heavisideFunction().apply(out);

  }

  public void train() {
    double[] err = new double[hidden.length];
    double globalError;
    int count = 0;
    do {
      System.out.println("Training iteration #" + count);
      count++;
      globalError = 0;
      for (int p = 0; p < patterns.length; p++) {
        System.arraycopy(patterns[p], 0, inputs, 0, inputs.length);

        evaluateOut();
        double localError = answers[p] - out;
        globalError += Math.abs(localError);

        for (int i = 0; i < hidden.length; i++) {
          err[i] = localError * hiddenOutWeights[i];
        }

        for (int i = 0; i < inputs.length; i++) {
          for (int j = 0; j < hidden.length; j++) {
            inputHiddenWeights[i][j] += 0.1 * err[j] * inputs[i];
          }
        }

        for (int i = 0; i < hidden.length; i++) {
          hiddenOutWeights[i] += 0.1 * localError * hidden[i];
        }
      }
    } while (globalError != 0);
    System.out.println("Training finished in #" + count + " iterations");
  }


  public static void main(String[] args) {
    MLP mlp = new MLP();

  }
}
