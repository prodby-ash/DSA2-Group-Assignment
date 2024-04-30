import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

            public class Main {
                public static void main(String[] args) {
                    // Create the network model
                    Star network = null;
                    ServerNode serverNode = null;
                    List<ClientNode> clientNodes = new ArrayList<>();

                    Scanner scanner = new Scanner(System.in);
                    boolean exit = false;

                    while (!exit) {
                        System.out.println("Main Menu:");
                        System.out.println("1. Create a Network");
                        System.out.println("2. Exit");
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        switch (choice) {
                            case 1:
                                network = createNetwork(scanner, clientNodes);
                                serverNode = new ServerNode();
                                boolean networkMenu = true;
                                while (networkMenu) {
                                    System.out.println("\nNetwork Menu:");
                                    System.out.println("1. Send a Message");
                                    System.out.println("2. Add a Client Node");
                                    System.out.println("3. Delete a Client Node");
                                    System.out.println("4. Go Back to Main Menu");
                                    System.out.println("5. Exit");
                                    System.out.print("Enter your choice: ");
                                    int networkChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character

                                    switch (networkChoice) {
                                        case 1:
                                            sendMessage(scanner, clientNodes, serverNode);
                                            break;
                                        case 2:
                                            addClientNode(scanner, network, clientNodes);
                                            break;
                                        case 3:
                                            deleteClientNode(scanner, network, clientNodes);
                                            break;
                                        case 4:
                                            networkMenu = false;
                                            break;
                                        case 5:
                                            exit = true;
                                            networkMenu = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice. Please try again.");
                                            break;
                                    }
                                }
                                break;
                            case 2:
                                exit = true;
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                    }
                }

                private static Star createNetwork(Scanner scanner, List<ClientNode> clientNodes) {
                    System.out.println("Enter the number of client nodes for your Network:");
                    int numClients = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Star network = new Star();
                    for (int i = 1; i <= numClients; i++) {
                        System.out.println("Enter the name of client node " + i + ":");
                        String clientName = scanner.nextLine();
                        ClientNode clientNode = new ClientNode(clientName);
                        clientNodes.add(clientNode);
                        network.insertNode(clientNode);
                    }

                    return network;
                }

                private static void sendMessage(Scanner scanner, List<ClientNode> clientNodes, ServerNode serverNode) {
                    if (!clientNodes.isEmpty()) {
                        System.out.println("Enter the name of the sender:");
                        String senderName = scanner.nextLine();
                        ClientNode sender = null;

                        for (ClientNode client : clientNodes) {
                            if (client.getId().equals(senderName)) {
                                sender = client;
                                break;
                            }
                        }

                        System.out.println("Enter the name of the recipient:");
                        String recipientName = scanner.nextLine();
                        ClientNode recipient = null;

                        for (ClientNode client : clientNodes) {
                            if (client.getId().equals(recipientName)) {
                                recipient = client;
                                break;
                            }
                        }

                        if (sender != null && recipient != null) {
                            System.out.println("Please type your message:");
                            String message = scanner.nextLine();
                            sender.send(serverNode, message, recipient);
                            System.out.println("Message sent from " + senderName + " to " + recipientName);
                        } else {
                            System.out.println("Sender or recipient not found!");
                        }
                    } else {
                        System.out.println("No client nodes available.");
                    }
                }

                private static void addClientNode(Scanner scanner, Star network, List<ClientNode> clientNodes) {
                    System.out.println("Enter the name of the client node to add:");
                    String clientName = scanner.nextLine();
                    ClientNode clientNode = new ClientNode(clientName);
                    clientNodes.add(clientNode);
                    network.insertNode(clientNode);
                    System.out.println("Added client node: " + clientName);
                }

                private static void deleteClientNode(Scanner scanner, Star network, List<ClientNode> clientNodes) {
                    System.out.println("Enter the name of the client node to delete:");
                    String clientName = scanner.nextLine();
                    ClientNode clientToDelete = null;

                    for (ClientNode client : clientNodes) {
                        if (client.getId().equals(clientName)) {
                            clientToDelete = client;
                            break;
                        }
                    }

                    if (clientToDelete != null) {
                        network.deleteNode(clientToDelete);
                        clientNodes.remove(clientToDelete);
                        System.out.println("Deleted client node: " + clientName);
                    } else {
                        System.out.println("Client node not found!");
                    }
                }
            }

