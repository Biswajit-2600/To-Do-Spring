import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoHttpClient {


    static void create() throws IOException, InterruptedException {

        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the task : ");
        String task = scn.nextLine();
        System.out.print("Enter the date : ");
        String date = scn.nextLine();

        String inputJson = "{\n" +
                "\"date\":\"" + date + "\",\n" +
                "\"task\":\"" + task + "\"\n}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/toDoList/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
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

        int choice;
        String newDate, newTask, inputJson = null, newCompletionStatus;
        Scanner scn = new Scanner(System.in);


        System.out.print("Enter the serialNumber : ");
        int serialNumber = scn.nextInt();

        HttpClient tempClient = HttpClient.newHttpClient();
        HttpRequest tempRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/toDoList/update/" + serialNumber))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{}"))
                .build();

        HttpResponse<String> tempResponse = tempClient.send(tempRequest,
                HttpResponse.BodyHandlers.ofString());



        if (tempResponse.statusCode() == 404) {
            System.out.println("\nResponse Status Code : " + tempResponse.statusCode());
            System.out.println(tempResponse.body());
        }

        else {
            System.out.println("\nWhat do you want to update? : \n");
            System.out.print("""
                    1 - Date only
                    2 - Task only
                    3 - Date and Task both
                    4 - Completion Status

                    Enter choice :\s""");
            choice = scn.nextInt();
            switch (choice) {
                case 1 -> {
                    scn.nextLine();
                    System.out.print("Enter the new date : ");
                    newDate = scn.nextLine();
                    inputJson = "{\n" +
                            "\"newDate\":\"" + newDate + "\"\n}";
                }
                case 2 -> {
                    scn.nextLine();
                    System.out.print("Enter the new task : ");
                    newTask = scn.nextLine();
                    inputJson = "{\n" +
                            "\"newTask\":\"" + newTask + "\"\n}";
                }
                case 3 -> {
                    scn.nextLine();
                    System.out.print("Enter the new date : ");
                    newDate = scn.nextLine();
                    System.out.print("Enter the new task : ");
                    newTask = scn.nextLine();
                    inputJson = "{\n" +
                            "\"newDate\":\"" + newDate + "\",\n" +
                            "\"newTask\":\"" + newTask + "\"\n}";
                }
                case 4 -> {
                    scn.nextLine();
                    System.out.print("Enter the new completion status (true / false) : ");
                    newCompletionStatus = scn.nextLine();
                    inputJson = "{\n\"newCompletionStatus\":\"" + newCompletionStatus + "\"\n}";
                }
                default -> System.out.println("Invalid choice, enter again : ");
            }

            HttpClient client = HttpClient.newHttpClient();
            assert inputJson != null;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/toDoList/update/" + serialNumber))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(inputJson))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("\nResponse Status Code : " + response.statusCode());
            System.out.println(response.body());
        }
    }

    static void delete() throws IOException, InterruptedException {

        Scanner scn = new Scanner(System.in);

        System.out.print("Enter the serialNumber : ");
        int serialNumber = scn.nextInt();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/toDoList/delete/" + serialNumber))
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

