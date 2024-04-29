public class ClientNode {
    private String id; // Unique identifier or name for the client node
    private Object[] data; // Array to store data values
    private ServerNode centralNode; // Reference to the central (server) node

    // Constructor with ID parameter
    public ClientNode(String id, int dataSize) {
        this.id = id;
        data = new Object[dataSize];
    }

    // Method to get the ID of the client node
    public String getId() {
        return id;
    }

    // Method to set data at a specific index
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

    // Method to connect to the central (server) node
    public void connectToCentralNode(ServerNode centralNode) {
        this.centralNode = centralNode;
    }

    // Method to send a message to the central (server) node
    public void send(Object message) {
        if (centralNode != null) {
            centralNode.receiveMessageFromPeripheralNode(message, this);
        } else {
            System.out.println("Central node not connected.");
        }
    }

    // Method to receive a message from the central (server) node
    public void receive(Object message) {
        System.out.println("Message received from central node: " + message);
    }
}
