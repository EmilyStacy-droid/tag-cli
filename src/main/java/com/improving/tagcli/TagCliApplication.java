package com.improving.tagcli;

import com.improving.tagcli.Database.DatabaseClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		databaseClient.insertIntoTable();
        databaseClient.readDataFromTable();
		//Class.forName("com.mysql.jdbc.Driver").newInstance();

		//System.out.println("Hello Mrs. World");

		//DatabaseClient client = new DatabaseClient();

		//client.readRecordFromDatabase();

	}
}
