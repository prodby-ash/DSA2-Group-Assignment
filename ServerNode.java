public class ServerNode {
    private Object[] data; // // Array for data values storage
    private ServerNode[] peripheralNodes; // Array to store references to peripheral nodes

   //Constractor
    public ServerNode(int dataSize, int peripheralNodeCount) {
        data = new Object[dataSize]; 
        peripheralNodes = new ServerNode[peripheralNodeCount];
    }

   // Method for assigning data to a certain index
    public void setData(int index, Object value) {
        if (index >= 0 && index < data.length) {
            data[index] = value;
        } else {
            System.out.println("Invalid index for data.");
        }
    }

    // Method to get data at a specific index
    public Object getData(int index) {
        if (index >= 0 && index < data.length) {
            return data[index];
        } else {
            System.out.println("Invalid index for data.");
            return null;
        }
    }

    // Method to connect a peripheral node at a specific index
    public void connectPeripheralNode(int index, ServerNode peripheralNode) {
        if (index >= 0 && index < peripheralNodes.length) {
            peripheralNodes[index] = peripheralNode;
        } else {
            System.out.println("Invalid index for peripheral node.");
        }
    }

    // Method to get the peripheral node at a specific index
    public ServerNode getPeripheralNode(int index) {
        if (index >= 0 && index < peripheralNodes.length) {
            return peripheralNodes[index];
        } else {
            System.out.println("Invalid index for peripheral node.");
            return null; 
        }
    }

    // Method to send a message to a peripheral node
    public void sendMessage(ServerNode peripheralNode, Object message) {
        // Broker the message to the specified peripheral node
        System.out.println("Message sent to peripheral node: " + message);
        peripheralNode.receiveMessage(message);
    }

    // Method to receive a message from a peripheral node
    public void receiveMessage(Object message) {
        // Process the received message
        System.out.println("Message received: " + message);
        // Additional processing logic can be added here
    }
}
