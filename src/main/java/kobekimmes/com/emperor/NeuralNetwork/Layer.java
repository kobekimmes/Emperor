package kobekimmes.com.emperor.NeuralNetwork;

class Layer {
    int numIn;
    int numOut;
    double[][] weights;
    double[] bias;

    Layer(int _in, int _out) {
        numIn = _in;
        numOut = _out;

        weights = new double[numOut][numIn];
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; i < weights[i].length; j++) {
                weights[i][j] = (Math.random()) / Math.sqrt(numIn);
            }
        }

        bias = new double[numOut];
        for (int i = 0; i < bias.length; i++) {
            bias[i] = 0;
        }
    }

    double[] produceOutput(double[] inputs) {
        double[] outputs = new double[numOut];

        for (int i = 0; i < numOut; i++) {
            double weightedVal = bias[i];
            for (int j = 0; j < numIn; j++) {
                weightedVal += (inputs[j] * weights[i][j]);
           }
            outputs[i] = weightedVal;
        }
        return outputs;
    }






}