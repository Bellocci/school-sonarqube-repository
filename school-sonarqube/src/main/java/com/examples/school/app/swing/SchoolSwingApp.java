package com.examples.school.app.swing;

import java.awt.EventQueue;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.examples.school.controller.SchoolController;
import com.examples.school.repository.mongo.StudentMongoRepository;
import com.examples.school.view.swing.StudentSwingView;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * accepts the command line arguments for specifying the MongoDB host and
 * port and for the database and collection names. Otherwise, it relies on some default values.
 * We are using Picocli library for accepts the command line arguments.
 */
@Command(mixinStandardHelpOptions = true)
public class SchoolSwingApp implements Callable<Void>{
	
	@Option(names = { "--mongo-host" }, description = "MongoDB host address")
	private String mongoHost = "localhost";
	
	@Option(names = { "--mongo-port" }, description = "MongoDB host port")
	private int mongoPort = 27017;
	
	@Option(names = { "--db-name" }, description = "Database name")
	private String databaseName = "school";
	
	@Option(names = { "--db-collection" }, description = "Collection name")
	private String collectionName = "student";

	/**
	 * Launch the application.
	 * 
	 * We are using the args argument, containing the command line arguments, to make our application able to
	 * connect to any MongoDB server, given the host and the port. The defaults are still “localhost:27017"
	 * 
	 * EventQueue.invokeLater(() -> {
				try {
					String mongoHost = "localhost";
					int mongoPort = 27017;
					if (args.length > 0)
						mongoHost = args[0];
					if (args.length > 1)
						mongoPort = Integer.parseInt(args[1]);
					StudentMongoRepository studentRepository = 	new StudentMongoRepository(
						new MongoClient(
							new ServerAddress(mongoHost, mongoPort)), "school", "student");
					StudentSwingView studentView = new StudentSwingView();
					SchoolController schoolController =	new SchoolController(studentView, studentRepository);
					studentView.setSchoolController(schoolController);
					studentView.setVisible(true);
					schoolController.allStudents();
				} catch (Exception e) {
					e.printStackTrace();
				}
				});
				
	 * If we use Picocli we move the code from main to call method.
	 */
	
	public static void main(String[] args) {
		CommandLine.call(new SchoolSwingApp(), args);
	}

	@Override
	public Void call() throws Exception {
		EventQueue.invokeLater(() -> {
			try {
				StudentMongoRepository studentRepository = new StudentMongoRepository(new MongoClient(
						new ServerAddress(mongoHost, mongoPort)), databaseName, collectionName);
				StudentSwingView studentView = new StudentSwingView();
				SchoolController schoolController = new SchoolController(studentView, studentRepository);
				studentView.setSchoolController(schoolController);
				studentView.setVisible(true);
				schoolController.allStudents();
			} catch(Exception e) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
			}
		});
		return null;
	}
}
