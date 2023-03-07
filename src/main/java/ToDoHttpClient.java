import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoHttpClient {


    static void create() throws IOException, InterruptedException {

        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the task : ");
        String task = scn.nextLine();
        System.out.print("Enter the date : ");
        String date = scn.nextLine();

        var values = new HashMap<String, String>() {{
            put("task", task);
            put("date", date);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/toDoList/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println("\nResponse Status Code : " + response.statusCode());
        System.out.println(response.body());
    }

    static void read() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/toDoList/read"))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\nResponse Status Code : " + response.statusCode());
        System.out.println(response.body());
    }

    static void update() throws IOException, InterruptedException {

        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the serialNumber : ");
        int serialNumber = scn.nextInt();
        System.out.print("Enter the new task : ");
        String newTask = scn.next();
        System.out.print("Enter the new date : ");
        String newDate = scn.next();
        System.out.print("Enter the new date : ");
        boolean newCompletionStatus = Boolean.parseBoolean(scn.next());


        var values = new HashMap<String, String>() {{
            put("task", newTask);
            put("date", newDate);
        }};

        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writeValueAsString(values);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/toDoList/update"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println("\nResponse Status Code : " + response.statusCode());
        System.out.println(response.body());
    }

    static void delete() throws IOException, InterruptedException {

        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the serialNumber : ");
        int serialNumber = scn.nextInt();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/toDoList/delete/"+serialNumber))
                .DELETE()
                .build();
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("\nResponse Status Code : " + response.statusCode());
        System.out.println(response.body());
    }

    public static void main(String[] args) throws IOException, InterruptedException, InputMismatchException {

        Scanner scn = new Scanner(System.in);

        int choice = 1;

        System.out.print("Welcome to your to-do list\n\n" +
                "What would you like to do?\n\n" +
                "Available choices : \n");

        while (choice != 5) {
            System.out.print("\n1 - Create a new to-do \n" +
                    "2 - Read your to-do list \n" +
                    "3 - Update your to-do list\n" +
                    "4 - Delete your to-do list\n" +
                    "5 - Exit!\n\n" +
                    "Enter choice : ");
            choice = scn.nextInt();
            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    read();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("\nInvalid Choice, enter again.\n");
            }
        }
        System.out.println("\nThank you for using the to-do");
    }
}
