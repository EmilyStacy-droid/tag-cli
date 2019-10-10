package com.improving.tagcli;

import com.improving.tagcli.Database.DatabaseClient;
import com.improving.tagcli.models.Emote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TagCliApplication implements CommandLineRunner {

	private final DatabaseClient databaseClient;

	public TagCliApplication(DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(TagCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//    	Emote emote = new Emote("Hello","World");
//		System.out.println("INSERT INTO emote (prompt, text)" +
//				" Values " +  "(" + "'" + emote.getPrompt() + "'" + "," + ", '" + emote.getText()+ "'" + ")");;
		while (true) {
			System.out.println("Do you want to (1) Add something or (2) Read something? Type 1 or 2");
			Scanner scanner = new Scanner(System.in);
			String userInput = scanner.nextLine();
			
				if (userInput.equals("1")) {
					System.out.println("Do you want to add (1)emote table or (2) Weapon table. Type 1 or 2");
					String secondUserInput = scanner.nextLine();
					if (secondUserInput.equals("1")) {
						System.out.println("Enter your Emote prompt");
						String prompt = scanner.nextLine();
						System.out.println("Enter your Emote text");
						String text = scanner.nextLine();
						Emote emote = new Emote(prompt, text);
						databaseClient.insertEmoteIntoTable(emote);
						System.out.println("Update success!");
					} else if (secondUserInput.equals("2")) {
						System.out.println("Invalid command");

					}

				} else {
					System.out.println("Invalid command");

				}
			}
		}

		//databaseClient.insertEmoteIntoTable(emote);
		//databaseClient.readDataFromTable();
		//Class.forName("com.mysql.jdbc.Driver").newInstance();

		//System.out.println("Hello Mrs. World");

		//DatabaseClient client = new DatabaseClient();

		//client.readRecordFromDatabase();


}
