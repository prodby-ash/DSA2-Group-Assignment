import java.util.ArrayList;
import java.util.List;

public class Star {
    private ServerNode centralNode;
    private List<ClientNode> peripheralNodes;

    public Star() {
        peripheralNodes = new ArrayList<>();
        centralNode = new ServerNode();
    }

    public void insertNode(ClientNode node) {
        peripheralNodes.add(node);
        centralNode.registerClient(node);
    }

    public void deleteNode(ClientNode node) {
        peripheralNodes.remove(node);
    }

    public List<ClientNode> getPeripheralNodes() {
        return peripheralNodes;
    }
}