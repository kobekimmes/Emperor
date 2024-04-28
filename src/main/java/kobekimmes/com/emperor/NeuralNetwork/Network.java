package kobekimmes.com.emperor.NeuralNetwork;

class Network {

    Layer[] layers;
    //double[] weights;

    Network(int... _layers) {
        int layerCount = _layers.length - 1;
        layers = new Layer[layerCount];

        for (int i = 0; i < layerCount; i++) {
            layers[i] = new Layer(i, i + 1);
        }
    }



}