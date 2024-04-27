import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class c_d {
    private Node head; // Reference to the head node

    // Inner class representing a node
    private class Node {
        int data;
        Node next;
        public c_d.Node right;
        public c_d.Node left;
        public Character character;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method to insert a new node
    public void insertNode(int data) {
        if (head == null) {
            // If the network is empty, create a new head node
            head = new Node(data);
        } else {
            // Else add a new node at the beginning
            Node newNode = new Node(data);
            newNode.next = head;
            head = newNode;
        }
    }

    // Method to delete a node
    public void deleteNode(int data) {
        if (head == null) {
            // Network is empty, nothing to delete
            return;
        }

        if (head.data == data) {
            // If the head node matches the data, remove it
            head = head.next;
            return;
        }

        Node prev = null;
        Node current = head;

        // Traverse the network to find the node with the specified info
        while (current != null && current.data != data) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            // Node with specified info not found
            return;
        }

        // Remove the node by updating the previous node's next reference
        prev.next = current.next;
    }

    // Method to compress a message using Huffman Coding
    public String compressMessage(String message) {
        // Build character frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : message.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Creating a priority queue for Huffman tree construction
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.data - b.data);
        for (char c : freqMap.keySet()) {
            pq.offer(new Node(freqMap. get(c)));
        }

        // Build the Huffman tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node merged = new Node(left.data + right.data);
            merged.left = left;
            merged.right = right;
            pq.offer(merged);
        }

        // Generate Huffman codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(pq.peek(), "", huffmanCodes);

        // Compress the message
        StringBuilder compressed = new StringBuilder();
        for (char c : message.toCharArray()) {
            compressed.append(huffmanCodes.get(c));
        }
        return compressed.toString();
    }

    // Helper method to generate Huffman codes
    private void generateHuffmanCodes(Node node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
            return;
        }
        generateHuffmanCodes(node.left, code + "0", huffmanCodes);
        generateHuffmanCodes(node.right, code + "1", huffmanCodes);
    }

    // Method to decompress a compressed message
    public String decompressMessage(String compressed) {
        // We can implement Huffman decoding logic here
        return ""; // Placeholder
    }
}