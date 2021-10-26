import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
 

public class Client extends Application {
	
	static int curr_uid = 0;
	static String curr_fname = "";
	static String curr_lname = "";
	static String curr_uname = "";
	static String curr_pword = "";
	static int busids[] = null;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, InterruptedException {
        welcomePage(primaryStage);
        primaryStage.show();        
    }
    
    public void welcomePage(Stage primaryStage) throws FileNotFoundException {
    	
    	Label welcome = new Label("Welcome to BlueBus!");
    	welcome.setFont(new Font("Arial", 60));
    	welcome.setWrapText(true);
    	welcome.setStyle("-fx-text-alignment: center;");
    	Button signup = new Button("Create an Account");
    	signup.setFont(new Font("Arial", 20));
    	signup.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	Label login = new Label("Already have an account?");
    	login.setFont(new Font("Arial", 36));
    	login.setWrapText(true);
    	login.setStyle("-fx-text-alignment: center;");
    	Button signin = new Button("Sign In");
    	signin.setFont(new Font("Arial", 20));
    	signin.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	Image image = new Image(new FileInputStream("C:\\Users\\Amisha Upadhyay\\Desktop\\images\\main_page.gif"));
        ImageView busgif = new ImageView(image);
        
        signup.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
                signupPage(primaryStage);
            }
        });
    	
    	signin.setOnAction(new EventHandler<ActionEvent>() {
    		 
            @Override
            public void handle(ActionEvent event) {
                signinPage(primaryStage);
            }
        });
    	
    	VBox vb = new VBox();
    	
    	vb.getChildren().addAll(welcome, busgif, signup, login, signin);
    	vb.setSpacing(50);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	primaryStage.setScene(new Scene(vb, 400, 700));
    	
    }
    
    public void signupPage(Stage primaryStage) {
    	
    	Label top_msg = new Label("Please enter your name, username and password to Create a new account");
    	top_msg.setFont(new Font("Arial", 28));
    	top_msg.setWrapText(true);
    	top_msg.setStyle("-fx-text-alignment: center;");
    	top_msg.setMaxWidth(300);
    	TextField firstname = new TextField();
    	firstname.setPromptText("Firstname");
    	firstname.setFont(new Font("Arial", 20));
    	firstname.setMaxWidth(300);
    	TextField lastname = new TextField();
    	lastname.setPromptText("Lastname");
    	lastname.setFont(new Font("Arial", 20));
    	lastname.setMaxWidth(300);
    	TextField username = new TextField();
    	username.setPromptText("Username");
    	username.setFont(new Font("Arial", 20));
    	username.setMaxWidth(300);
    	TextField password = new TextField();
    	password.setPromptText("Password");
    	password.setFont(new Font("Arial", 20));
    	password.setMaxWidth(300);
    	Button signup = new Button("Sign Up");
    	signup.setFont(new Font("Arial", 20));
    	signup.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	signup.setMinWidth(300);
    	Button cancel = new Button("Cancel");
    	cancel.setFont(new Font("Arial", 20));
    	cancel.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	cancel.setMinWidth(300);
    	Label error = new Label();
    	error.setFont(new Font("Arial", 20));
    	error.setWrapText(true);
    	error.setMaxWidth(300);
    	error.setMinSize(300, 100);
    	error.setTextAlignment(TextAlignment.CENTER);
    	error.setStyle("-fx-text-fill: grey; -fx-text-alignment: center; -fx-label-padding: 5px");
    	
    	cancel.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
                try {
					welcomePage(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	VBox btns = new VBox();
    	btns.getChildren().addAll(signup, cancel);
    	btns.setSpacing(20);
    	btns.setAlignment(Pos.CENTER);
    	
    	VBox inputs = new VBox();
    	inputs.getChildren().addAll(firstname, lastname, username, password);
    	inputs.setSpacing(20);
    	inputs.setAlignment(Pos.CENTER);
    	
    	VBox vb = new VBox();
    	
    	vb.getChildren().addAll(top_msg, inputs, btns, error);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	signup.setOnAction(new EventHandler<ActionEvent>() {
     		 
            @Override
            public void handle(ActionEvent event) {
                signupbtn(primaryStage, error, firstname.getText(), lastname.getText(), username.getText(), password.getText());
            }
        });
    	
    	primaryStage.setScene(new Scene(vb, 400, 700));
    	
    }
    
    public void signupbtn(Stage primaryStage, Label error, String fname, String lname, String uname, String pword) {
    	
    	Users user_table = new Users();
    	
    	if(fname.isEmpty() || uname.isEmpty() || pword.isEmpty()) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Firstname, Username and/or Password cannot be empty");
    	}
    	else if(user_table.usernameexists(uname)) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Sorry, that username is already taken!");
    	}
    	else {
    		user_table.addRow(fname, lname, uname, pword);
    		String user_info[] = user_table.getUser(uname);
    		curr_uid = Integer.parseInt(user_info[0]);
    		curr_fname = user_info[1];
    		curr_lname = user_info[2];
    		curr_uname = user_info[3];
    		curr_pword = user_info[4];
    		try {
				preferancePage(primaryStage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    public void signinPage(Stage primaryStage) {
    	
    	Label top_msg = new Label("Please enter your username and password to Sign-in to your account");
    	top_msg.setFont(new Font("Arial", 32));
    	top_msg.setWrapText(true);
    	top_msg.setStyle("-fx-text-alignment: center;");
    	top_msg.setMaxWidth(300);
    	TextField username = new TextField();
    	username.setPromptText("Username");
    	username.setFont(new Font("Arial", 20));
    	username.setMaxWidth(300);
    	TextField password = new TextField();
    	password.setPromptText("Password");
    	password.setFont(new Font("Arial", 20));
    	password.setMaxWidth(300);
    	Button signin = new Button("Sign In");
    	signin.setFont(new Font("Arial", 20));
    	signin.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	signin.setMinWidth(300);
    	Button cancel = new Button("Cancel");
    	cancel.setFont(new Font("Arial", 20));
    	cancel.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	cancel.setMinWidth(300);
    	Label error = new Label();
    	error.setFont(new Font("Arial", 20));
    	error.setWrapText(true);
    	error.setMaxWidth(300);
    	error.setMinSize(300, 100);
    	error.setTextAlignment(TextAlignment.CENTER);
    	error.setStyle("-fx-text-fill: grey; -fx-text-alignment: center; -fx-label-padding: 5px");
    	
    	cancel.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
                try {
					welcomePage(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	VBox btns = new VBox();
    	btns.getChildren().addAll(signin, cancel);
    	btns.setSpacing(20);
    	btns.setAlignment(Pos.CENTER);
    	
    	VBox vb = new VBox();
    	
    	vb.getChildren().addAll(top_msg, username, password, btns, error);
    	vb.setSpacing(50);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	signin.setOnAction(new EventHandler<ActionEvent>() {
     		 
            @Override
            public void handle(ActionEvent event) {
                signinbtn(primaryStage, error, username.getText(), password.getText());
            }
        });
    	
    	primaryStage.setScene(new Scene(vb, 400, 700));
    	
    }
    
    public void signinbtn(Stage primaryStage, Label error, String uname, String pword) {
    	
    	Users user_table = new Users();
    	
    	if(uname.isEmpty() || pword.isEmpty()) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Username and/or Password cannot be empty");
    	}
    	else if(!user_table.usernameexists(uname)) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Either username or password is wrong!");
    	}
    	else if(!user_table.correctpassword(uname, pword)) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Either username or password is wrong!");
    	}
    	else {
    	    String user_info[] = user_table.getUser(uname);
    		curr_uid = Integer.parseInt(user_info[0]);
    		curr_fname = user_info[1];
    		curr_lname = user_info[2];
    		curr_uname = user_info[3];
    		curr_pword = user_info[4];
    		try {
				preferancePage(primaryStage);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    public void preferancePage(Stage primaryStage) throws FileNotFoundException {
    	
    	Buses bus_table = new Buses();
    	
    	Menu menu1 = new Menu("Account Settings");
    	
    	MenuItem m1 = new MenuItem("Edit Profile");
        MenuItem m2 = new MenuItem("My Bookings");
        MenuItem m3 = new MenuItem("My Reviews");
        MenuItem m4 = new MenuItem("Log Out");
        MenuItem m5 = new MenuItem("Delete My Account");
              
        menu1.getItems().add(m1);
        menu1.getItems().add(m2);
        menu1.getItems().add(m3);
        menu1.getItems().add(m4);
        menu1.getItems().add(m5);
        
        m1.setOnAction(new EventHandler<ActionEvent>() {
      		 
            @Override
            public void handle(ActionEvent event) {
            	edit_profile(primaryStage);
            }
        });
        
        m2.setOnAction(new EventHandler<ActionEvent>() {
     		 
            @Override
            public void handle(ActionEvent event) {
            	my_bookings(primaryStage);
            }
        });
        
        m3.setOnAction(new EventHandler<ActionEvent>() {
    		 
            @Override
            public void handle(ActionEvent event) {
            	my_reviews(primaryStage);
            }
        });
        
        m4.setOnAction(new EventHandler<ActionEvent>() {
     		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					logout_delete(primaryStage, false);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        m5.setOnAction(new EventHandler<ActionEvent>() {
     		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					logout_delete(primaryStage, true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        MenuBar menu = new MenuBar();
        menu.getMenus().add(menu1);
        menu.setMaxHeight(30);
    	menu.setStyle("-fx-background-color: lightgrey");
    	
    	Label top_msg = new Label("Welcome "+curr_fname+"!\nPlease enter your bus preferences");
    	top_msg.setFont(new Font("Arial", 28));
    	top_msg.setWrapText(true);
    	top_msg.setStyle("-fx-text-alignment: center;");
    	top_msg.setMaxWidth(300);
    	
    	HBox hb1 = new HBox();
    	Label from_city = new Label("Board at:");
    	from_city.setFont(new Font("Arial", 20));
    	from_city.setWrapText(true);
    	String from_cities[] = bus_table.uniq_values("from_city");
      	ComboBox cb1 = new ComboBox(FXCollections.observableArrayList(from_cities));
    	cb1.setVisibleRowCount(5);
    	cb1.setValue(from_cities[0]);
    	hb1.getChildren().addAll(from_city, cb1);
    	hb1.setSpacing(20);
    	hb1.setAlignment(Pos.CENTER);
    	
    	HBox hb2 = new HBox();
    	Label to_city = new Label("Deboard at:");
    	to_city.setFont(new Font("Arial", 20));
    	to_city.setWrapText(true);
    	String to_cities[] = bus_table.uniq_values("to_city");
      	ComboBox cb2 = new ComboBox(FXCollections.observableArrayList(to_cities));
    	cb2.setVisibleRowCount(5);
    	cb2.setValue(to_cities[0]);
    	hb2.getChildren().addAll(to_city, cb2);
    	hb2.setSpacing(20);
    	hb2.setAlignment(Pos.CENTER);
    	
    	HBox hb3 = new HBox();
    	Label dep_time = new Label("Departure time between:");
    	dep_time.setFont(new Font("Arial", 20));
    	dep_time.setWrapText(true);
    	Label to = new Label("to");
    	to.setFont(new Font("Arial", 20));
    	to.setWrapText(true);
    	String ampm[] = {"AM", "PM"};
      	ComboBox cb3 = new ComboBox(FXCollections.observableArrayList(ampm));
    	cb3.setValue(ampm[0]);
    	//cb3.setMaxWidth(50);
    	TextField from_time = new TextField();
    	from_time.setText("10");
    	from_time.setFont(new Font("Arial", 20));
    	from_time.setMaxWidth(50);
    	ComboBox cb4 = new ComboBox(FXCollections.observableArrayList(ampm));
    	cb4.setValue(ampm[0]);
    	//cb4.setMaxWidth(50);
    	TextField to_time = new TextField();
    	to_time.setText("10");
    	to_time.setFont(new Font("Arial", 20));
    	to_time.setMaxWidth(50);
    	hb3.getChildren().addAll(dep_time);
    	hb3.setSpacing(20);
    	hb3.setAlignment(Pos.CENTER);
    	
    	HBox hb4 = new HBox();
    	hb4.getChildren().addAll(from_time, cb3, to, to_time, cb4);
    	hb4.setSpacing(10);
    	hb4.setAlignment(Pos.CENTER);
    	
    	Label fare = new Label("Fare less than:");
    	fare.setFont(new Font("Arial", 20));
    	fare.setWrapText(true);
    	TextField farels = new TextField();
    	farels.setText("250");
    	farels.setFont(new Font("Arial", 20));
    	farels.setMaxWidth(100);
    	
    	HBox hb5 = new HBox();
    	hb5.getChildren().addAll(fare, farels);
    	hb5.setSpacing(20);
    	hb5.setAlignment(Pos.CENTER);
    	
    	Button submit = new Button("Submit");
    	submit.setFont(new Font("Arial", 20));
    	submit.setMinWidth(300);
    	submit.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	Label error = new Label();
    	error.setFont(new Font("Arial", 16));
    	error.setWrapText(true);
    	error.setMaxWidth(300);
    	error.setMinSize(300, 100);
    	error.setTextAlignment(TextAlignment.CENTER);
    	error.setStyle("-fx-text-fill: grey; -fx-text-alignment: center; -fx-label-padding: 5px");
    	
    	submit.setOnAction(new EventHandler<ActionEvent>() {
    		 
            @Override
            public void handle(ActionEvent event) {
            	submitbtn(primaryStage, error, cb1.getValue().toString(), cb2.getValue().toString(), from_time.getText(), to_time.getText(), cb3.getValue().toString(), cb4.getValue().toString(), farels.getText());
            }
        });
    	
    	VBox vb = new VBox();
    	
    	vb.getChildren().addAll(menu, top_msg, hb1, hb2, hb3, hb4, hb5, submit, error);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    	primaryStage.setScene(new Scene(vb, 400, 700));
    	
    }
    
    public void edit_profile (Stage primaryStage) {
    	
    	HBox top = new HBox();
    	Button back = new Button("<");
    	back.setFont(new Font("Arial", 30));
    	top.getChildren().add(back);
    	top.setStyle("-fx-background-color: teal");
    	back.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	
    	back.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					preferancePage(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	Label edit = new Label("Edit Profile");
    	edit.setFont(new Font("Arial", 36));
    	edit.setStyle("-fx-text-fill: white; -fx-label-padding: 5px; -fx-background-color: teal");
    	
    	top.setSpacing(50);
    	
    	top.getChildren().add(edit);
    	
    	Label currpass = new Label("Enter current password:");
    	currpass.setFont(new Font("Arial", 20));
    	currpass.setWrapText(true);
    	TextField pass = new TextField();
    	pass.setFont(new Font("Arial", 20));
    	pass.setMaxWidth(300);
    	
    	HBox hb1 = new HBox();
    	Label field = new Label("Change my: ");
    	field.setFont(new Font("Arial", 20));
    	field.setWrapText(true);
    	String fields[] = {"firstname", "lastname", "username", "password"};
      	ComboBox cb1 = new ComboBox(FXCollections.observableArrayList(fields));
    	cb1.setVisibleRowCount(5);
    	cb1.setValue(fields[0]);
    	hb1.getChildren().addAll(field, cb1);
    	hb1.setSpacing(20);
    	hb1.setAlignment(Pos.CENTER);
    	
    	Label newval = new Label("Enter new value:");
    	newval.setFont(new Font("Arial", 20));
    	newval.setWrapText(true);
    	TextField val = new TextField();
    	val.setFont(new Font("Arial", 20));
    	val.setMaxWidth(300);
    	
    	Button submit = new Button("Submit");
    	submit.setFont(new Font("Arial", 20));
    	submit.setMinWidth(300);
    	submit.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	Label error = new Label();
    	error.setFont(new Font("Arial", 16));
    	error.setWrapText(true);
    	error.setMaxWidth(300);
    	error.setMinSize(300, 100);
    	error.setTextAlignment(TextAlignment.CENTER);
    	error.setStyle("-fx-text-fill: grey; -fx-text-alignment: center; -fx-label-padding: 5px");
    	
    	submit.setOnAction(new EventHandler<ActionEvent>() {
     		 
            @Override
            public void handle(ActionEvent event) {
            	if(!cb1.getValue().toString().equals("lastname") && val.getText().isEmpty()) {
            		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
            		error.setText("Firstname, Username and Password cannot be empty!");
            	}
            	else if(!pass.getText().equals(curr_pword)) {
            		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
            		error.setText("\tPassword entered is incorrect!");
            	}
            	else {
            		try {
						profileupdatedPage(primaryStage, cb1.getValue().toString(), val.getText());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            }
        });
    	
    	VBox vb = new VBox();
    	
    	vb.getChildren().addAll(top, currpass, pass, hb1, newval, val, submit, error);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    	primaryStage.setScene(new Scene(vb, 400, 700));
    	
    }
    
    public void profileupdatedPage(Stage primaryStage, String colname, String val) throws FileNotFoundException {
    	
    	Users users_table = new Users();
    	users_table.updateRow(curr_uid, colname, val);
    	
    	Image image = new Image(new FileInputStream("C:\\Users\\Amisha Upadhyay\\Desktop\\images\\green_tick.gif"));
        ImageView tick = new ImageView(image);
        
        Label success = new Label();
        success.setText("Your "+colname+" has been updated successfully!");
        success.setFont(new Font("Arial", 20));
        success.setWrapText(true);
        success.setMaxWidth(300);
        success.setAlignment(Pos.CENTER);
            	
    	Button ok = new Button("OK");
    	ok.setFont(new Font("Arial", 20));
    	ok.setMinWidth(300);
    	ok.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	ok.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	edit_profile(primaryStage);
            }
        });
    	
    	VBox vb = new VBox();
        
        vb.getChildren().addAll(tick, success, ok);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	primaryStage.setScene(new Scene(vb, 400, 700));    
    	
    }
    
    public void my_bookings (Stage primaryStage) {
    	
    	HBox top = new HBox();
    	Button back = new Button("<");
    	back.setFont(new Font("Arial", 30));
    	top.getChildren().add(back);
    	top.setStyle("-fx-background-color: teal");
    	back.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	
    	back.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					preferancePage(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	Label edit = new Label("My Bookings");
    	edit.setFont(new Font("Arial", 36));
    	edit.setStyle("-fx-text-fill: white; -fx-label-padding: 5px; -fx-background-color: teal");    	
    	
    	Bookings book_table = new Bookings();
    	String arr[][] = book_table.getbookings(curr_uid);
    	
    	HBox vbs[] = new HBox[arr.length];
    	
    	Label busids[] = new Label[arr.length];
    	Label seatno[] = new Label[arr.length];
    	Label dep_date[] = new Label[arr.length];
    	
    	Button cancel[] = new Button[arr.length];
    	
    	for(int i=0;i<arr.length;i++) {
    		busids[i] = new Label("Bus ID: "+arr[i][0]+"\t");
    		seatno[i] = new Label("Seat No.: "+arr[i][1]+"\t");
    		dep_date[i] = new Label("Date: "+arr[i][2]+"\t");
    		int id = Integer.parseInt(arr[i][0]);
    		int sno = Integer.parseInt(arr[i][1]);
    		String ddate = arr[i][2];
    		cancel[i] = new Button("Cancel");
    		cancel[i].setStyle("-fx-text-fill: white; -fx-background-color: teal");
    		cancel[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	book_table.deleteRow(curr_uid, id, sno, ddate);
                	my_bookings(primaryStage);
                }
            });
    		vbs[i] = new HBox();
    		vbs[i].setAlignment(Pos.CENTER);
    		vbs[i].setStyle("-fx-border-color: black;");
    		vbs[i].getChildren().addAll(busids[i], seatno[i], dep_date[i], cancel[i]);
    	}
    	
    	top.setSpacing(50);
    	
    	top.getChildren().add(edit);
    	
    	VBox vb = new VBox();
    	
    	vb.getChildren().addAll(top);
    	
    	for(int i=0;i<arr.length;i++) {
    		vb.getChildren().add(vbs[i]);
    	}
    	
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    	primaryStage.setScene(new Scene(vb, 400, 700));
    	
    }
    
    public void my_reviews (Stage primaryStage) {
    	
    	HBox top = new HBox();
    	Button back = new Button("<");
    	back.setFont(new Font("Arial", 30));
    	top.getChildren().add(back);
    	top.setStyle("-fx-background-color: teal");
    	back.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	
    	back.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					preferancePage(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	Label edit = new Label("My Reviews");
    	edit.setFont(new Font("Arial", 36));
    	edit.setStyle("-fx-text-fill: white; -fx-label-padding: 5px; -fx-background-color: teal");    	
    	
    	Ratings_and_Reviews rr_table = new Ratings_and_Reviews();
    	String arr[][] = rr_table.getinfo(curr_uid);
    	
    	HBox vbs[] = new HBox[arr.length];
    	VBox main_vbs[] = new VBox[arr.length];
    	
    	Label busids[] = new Label[arr.length];
    	Label rating[] = new Label[arr.length];
    	Label review[] = new Label[arr.length];
    	
    	Button cancel[] = new Button[arr.length];
    	
    	for(int i=0;i<arr.length;i++) {
    		busids[i] = new Label("Bus ID: "+arr[i][0]+"\t");
    		rating[i] = new Label("Rating: "+arr[i][1]+"/5\t");
    		review[i] = new Label(arr[i][2]);
    		review[i].setMinHeight(50);
    		review[i].setStyle("-fx-label-padding: 5px");
    		int id = Integer.parseInt(arr[i][0]);
    		cancel[i] = new Button("Delete");
    		cancel[i].setStyle("-fx-text-fill: white; -fx-background-color: teal");
    		cancel[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	rr_table.deleteRow(curr_uid, id);
                	my_reviews(primaryStage);
                }
            });
    		vbs[i] = new HBox();
    		vbs[i].setAlignment(Pos.CENTER);
    		vbs[i].setStyle("-fx-border-color: black;");
    		vbs[i].getChildren().addAll(busids[i], rating[i], cancel[i]);
    		vbs[i].setSpacing(40);
    		vbs[i].setMinHeight(50);
    		
    		main_vbs[i] = new VBox();
    		//main_vbs[i].setAlignment(Pos.CENTER);
    		main_vbs[i].setStyle("-fx-border-color: black;");
    		main_vbs[i].getChildren().addAll(vbs[i], review[i]);
    	}
    	
    	top.setSpacing(50);
    	
    	top.getChildren().add(edit);
    	
    	VBox vb = new VBox();
    	
    	vb.getChildren().addAll(top);
    	
    	for(int i=0;i<arr.length;i++) {
    		vb.getChildren().add(main_vbs[i]);
    	}
    	
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    	primaryStage.setScene(new Scene(vb, 400, 700));
    	
    }
    
    public void logout_delete(Stage primaryStage, boolean flag) throws FileNotFoundException {
    	
    	if(flag) {
    		Users user_table = new Users();
    		user_table.deleteRow(curr_uid);
    	}
    	
    	curr_uid = 0;
    	curr_fname = "";
    	curr_lname = "";
    	curr_uname = "";
    	curr_pword = "";
    	busids = null;
    	
    	Image image = new Image(new FileInputStream("C:\\Users\\Amisha Upadhyay\\Desktop\\images\\green_tick.gif"));
        ImageView tick = new ImageView(image);
        
        Label success = new Label();
        success.setText("You have logged out successfully!");
        success.setFont(new Font("Arial", 20));
        success.setWrapText(true);
        success.setMaxWidth(300);
        success.setAlignment(Pos.CENTER);
        
        if(flag) {
        	success.setText("You account has been deleted successfully!");
        }
            	
    	Button ok = new Button("Go to Home Page");
    	ok.setFont(new Font("Arial", 20));
    	ok.setMinWidth(300);
    	ok.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	ok.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					welcomePage(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	VBox vb = new VBox();
        
        vb.getChildren().addAll(tick, success, ok);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	primaryStage.setScene(new Scene(vb, 400, 700)); 
    	
    }
    
    public void submitbtn(Stage primaryStage, Label error, String from_city, String to_city, String from_time, String to_time, String ampm1, String ampm2, String fare) {
    	
    	Buses buses_table = new Buses();
    	
    	this.busids = null;
    	
    	if(from_time.isEmpty() || to_time.isEmpty() || fare.isEmpty()) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Departure time and/or Fare less than cannot be empty!");
    	}
    	else if(!correcttime(from_time)) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Departure time can only take values between 1 to 12");
    	}
    	else if(!correcttime(to_time)) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Departure time can only take values between 1 to 12");
    	}
    	else if(!correctfare(fare)) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Fare can only take integer values greater than 0");
    	}
    	
    	try {
    		from_time = converttime(Integer.parseInt(from_time), ampm1);
        	to_time = converttime(Integer.parseInt(to_time), ampm2);
		} catch (Exception e) {
			return;
		}
    	
    	if(to_time.compareTo(from_time)<0) {
    		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
    		error.setText("Please check departure time values!");
    	}
    	
    	else {
    	    this.busids = buses_table.getbusids(from_city, to_city, from_time, to_time, Integer.parseInt(fare));
    		try {
				busResults(primaryStage, busids);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    public boolean correcttime(String time) {
    	
    	if(time.length()>2) {
    		return false;
    	}
    	for(int i=0;i<time.length();i++) {
    		if(!Character.isDigit(time.charAt(i))) {
    			return false;
    		}
    	}
    	int num = Integer.parseInt(time);
    	if(num<1 && num>12) {
    		return false;
    	}
    	
    	
    	return true;
    	
    }
    
    public boolean correctfare(String fare) {
    	
    	for(int i=0;i<fare.length();i++) {
    		if(!Character.isDigit(fare.charAt(i))) {
    			return false;
    		}
    	}    	
    	
    	return true;
    	
    }
    
    public static String converttime(int time, String ampm) {
        
    	if(ampm.equals("PM")) {
    		if(time!=12) {
    			time+=12;
    		}
    	}
    	else {
    		if(time==12) {
    			time = 0;
    		}
    	}
    	
    	String ans = "";
    	
    	if(time<10) {
    		ans+="0";
    	}
    	
    	ans+=Integer.toString(time)+":00:00";
    	
    	return ans;
    	
    }
    
    public void busResults(Stage primaryStage, int busids[]) throws FileNotFoundException {
    	
    	Buses bus_table = new Buses();
    	
    	int len = busids.length;
    	
    	String from_city[] = new String[len];
    	String to_city[] = new String[len];
    	String dep_time[] = new String[len];
    	String arr_time[] = new String[len];
    	float fare[] = new float[len];
    	float rating[] = new float[len];
    	String bus_img[] = new String[len];
    	
    	for(int i=0;i<len;i++) {
    		String res[] = bus_table.getbusRow(busids[i]);
    		from_city[i] = res[0];
    		to_city[i] = res[1];
    		dep_time[i] = res[2];
    		arr_time[i] = res[3];
    		fare[i] = Float.parseFloat(res[4]);
    		rating[i] = Float.parseFloat(res[5]);
    		bus_img[i] = res[6];
    	}
    	
    	Font font = Font.font("Arial", FontWeight.BOLD, 15);
    	
    	HBox top = new HBox();
    	Button back = new Button("<");
    	back.setFont(new Font("Arial", 30));
    	top.getChildren().add(back);
    	top.setStyle("-fx-background-color: teal");
    	back.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	
    	back.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					preferancePage(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	Label city[] = new Label[len];
    	
    	for(int i=0;i<len;i++) {
    		city[i] = new Label();
    		city[i].setText(from_city[i]+" to "+to_city[i]);
    		city[i].setFont(font);
    	}
    	
    	Label time[] = new Label[len];
    	
    	for(int i=0;i<len;i++) {
    		time[i] = new Label();
    		time[i].setText(dep_time[i]+" to "+arr_time[i]);
    		time[i].setFont(font);
    	}
    	
    	Label fare_rate[] = new Label[len];
    	
    	for(int i=0;i<len;i++) {
    		fare_rate[i] = new Label();
    		fare_rate[i].setText("Rs."+fare[i]+"\t\t"+rating[i]+"/5");
    		fare_rate[i].setFont(font);
    	}

    	ImageView busimgs[] = new ImageView[len];
    	
    	for(int i=0;i<len;i++) {
        	Image image = new Image(new FileInputStream("C:\\Users\\Amisha Upadhyay\\Desktop\\images\\"+bus_img[i]));
    		busimgs[i] = new ImageView();
        	busimgs[i].setImage(image);
    		busimgs[i].setFitHeight(150);
            busimgs[i].setFitWidth(200);
    	}
    	
    	Button book[] = new Button[len];
    	
    	for(int i=0;i<len;i++) {
    		book[i] = new Button();
    		book[i].setText("Book Now");
    		book[i].setFont(new Font("Arial", 15));
        	book[i].setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	}
    	
    	for(int i=0;i<len;i++) {
    		
    		int id = returnid(i);
    		
    		book[i].setOnAction(new EventHandler<ActionEvent>() {
    	   		 
                @Override
                public void handle(ActionEvent event) {
                	bookingPage(primaryStage, id);
                }
            });

    	}
    	
    	VBox info[] = new VBox[len];
    	
    	for(int i=0;i<len;i++) {
    		info[i] = new VBox();
    		info[i].getChildren().addAll(city[i], time[i], fare_rate[i], book[i]);
    		info[i].setAlignment(Pos.CENTER);
    		info[i].setSpacing(10);
    	}
    	
    	HBox busrow[] = new HBox[len];
    	
    	for(int i=0;i<len;i++) {
    		busrow[i] = new HBox();
    		busrow[i].getChildren().addAll(busimgs[i], info[i]);
    		busrow[i].setSpacing(20);
    		busrow[i].setStyle("-fx-border-color: black;");
    	}
        
        VBox vb = new VBox();
        
        vb.getChildren().add(top);
    	
        for(int i=0;i<len;i++) {
        	vb.getChildren().add(busrow[i]);
        }
    	
    	//vb.setSpacing(30);
    	//vb.setAlignment(Pos.TOP_CENTER);
        if(len==0) {
    		Label notfound = new Label("No buses match your preferences");
    		notfound.setFont(new Font("Arial", 30));
    		notfound.setTextAlignment(TextAlignment.CENTER);
    		notfound.setStyle("-fx-label-padding: 5px;");
    		notfound.setWrapText(true);
    		vb.getChildren().add(notfound);
    	}
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    	primaryStage.setScene(new Scene(vb, 400, 700));    	
        
    }
    
    public int returnid(int i) {
    	return busids[i];
    }
    
    public void bookingPage(Stage primaryStage, int id) {
    	
    	HBox top = new HBox();
    	Button back = new Button("<");
    	back.setFont(new Font("Arial", 30));
    	top.getChildren().add(back);
    	top.setStyle("-fx-background-color: teal");
    	back.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	
    	back.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	try {
					busResults(primaryStage, busids);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    	
    	Label thisbus_id = new Label("Bus ID: "+id);
    	thisbus_id.setFont(new Font("Arial", 36));
    	thisbus_id.setStyle("-fx-text-fill: white; -fx-label-padding: 5px; -fx-background-color: teal");
    	
    	
    	top.setSpacing(50);
    	
    	top.getChildren().add(thisbus_id);
    	
    	int noofseats = 42;
    	
    	Label seats[] = new Label[noofseats];
    	
    	seats[0] = new Label("Driver");
    	seats[0].setStyle("-fx-text-fill: white; -fx-background-color: skyblue; -fx-border-color: blue;");
    	seats[0].setMinSize(50, 25);
    	
    	for(int i=1;i<noofseats;i++) {
    		seats[i] = new Label(""+i);
        	seats[i].setStyle("-fx-text-fill: white; -fx-background-color: skyblue; -fx-border-color: blue;");
        	seats[i].setMinSize(25, 25);
    	}
    	
    	GridPane gridPane = new GridPane();
    	
    	gridPane.add(seats[0], 4, 0);
    	
    	for(int i=1;i<=20;i+=2) {
    		gridPane.add(seats[i], 0, i/2+1);
    		gridPane.add(seats[i+1], 1, i/2+1);
    	}
    	
    	gridPane.add(seats[21], 2, 10);
    	
    	for(int i=22;i<=41;i+=2) {
    		gridPane.add(seats[i], 3, (i-21)/2+1);
    		gridPane.add(seats[i+1], 4, (i-21)/2+1);
    	}
    	
    	gridPane.setAlignment(Pos.CENTER);
    	gridPane.setHgap(10);
    	gridPane.setVgap(10);
    	
    	DatePicker date = new DatePicker();
    	date.setPromptText("dd/mm/yyyy");
    	
    	Label pick_date = new Label("Pick a date: ");
    	pick_date.setFont(new Font("Arial", 20));
    	
    	Button go = new Button("Go");
    	go.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	    	
    	HBox hb1 = new HBox();
    	hb1.getChildren().addAll(pick_date, date, go);
    	hb1.setSpacing(5);
    	hb1.setAlignment(Pos.CENTER);
    	
    	TextField seatno = new TextField();
    	seatno.setPromptText("Pick a no. from 1-41");
    	
    	Label pick_seat = new Label("Seat no.: ");
    	pick_seat.setFont(new Font("Arial", 20));
    	
    	Button book = new Button("Book");
    	book.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	
    	HBox hb2 = new HBox();
    	hb2.getChildren().addAll(pick_seat, seatno, book);
    	hb2.setSpacing(5);
    	hb2.setAlignment(Pos.CENTER);
    	
    	Button review = new Button("Leave a Review");
    	//review.setFont(new Font("Arial", 30));
    	review.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	review.setMaxWidth(300);
    	review.setAlignment(Pos.CENTER);
    	Ratings_and_Reviews rr_table = new Ratings_and_Reviews();
    	boolean flag = rr_table.reviewexists(curr_uid, id);
    	
    	if(flag) {
    		review.setText("Edit my Review");
    	}
    	
    	review.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	reviewPage(primaryStage, id);
            }
        });
    	
    	VBox vb1 = new VBox();
    	
    	Label msg = new Label();
    	msg.setFont(new Font("Arial", 20));
    	msg.setAlignment(Pos.CENTER);
    	msg.setStyle("-fx-text-alignment: center;");
    	msg.setWrapText(true);
    	
    	Button ok = new Button("OK");
    	ok.setStyle("-fx-text-fill: white; -fx-background-color: teal");
    	ok.setAlignment(Pos.BOTTOM_CENTER);
    	
    	vb1.setStyle("-fx-background-color: pink; -fx-border-color: red;");
    	vb1.setAlignment(Pos.CENTER);
    	vb1.getChildren().addAll(msg, ok);
    	vb1.setMaxSize(300, 200);
    	vb1.setSpacing(30);
    	    	
    	VBox vb = new VBox();
        
        vb.getChildren().addAll(top, gridPane, hb1, hb2, review);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    	StackPane root = new StackPane(vb);
    	
    	ok.setOnAction(new EventHandler<ActionEvent>() {
    		 
            @Override
            public void handle(ActionEvent event) {
            	root.getChildren().remove(vb1);
            }
        });
    	
    	go.setOnAction(new EventHandler<ActionEvent>() {
     		 
            @Override
            public void handle(ActionEvent event) {
            	goBtn(seats, date.getValue().toString(), root, vb1, msg, id);
            }
        });
    	
    	book.setOnAction(new EventHandler<ActionEvent>() {
    		 
            @Override
            public void handle(ActionEvent event) {
            	bookBtn(primaryStage, date.getValue().toString(), seatno.getText(), root, vb1, msg, id);
            }
        });
    	
    	primaryStage.setScene(new Scene(root, 400, 700));    
    	
    }
    
    public void goBtn(Label seats[], String date, StackPane root, VBox vb1, Label msg, int busid) {
    	
    	LocalDate d = LocalDate.now();
    	String today = d.toString();
    	
    	if(date.compareTo(today)<0) {
    		msg.setText("Date entered is invalid");
    		root.getChildren().add(vb1);
    	}
    	else {
    		Bookings btable = new Bookings();
    		HashSet<Integer> h = btable.bookedseats(busid, date);
    		for(Integer n: h) {
    			seats[n].setStyle("-fx-background-color: pink; -fx-border-color: red;");
    		}
    	}
    	    	
    }
    
    public void bookBtn(Stage primaryStage, String date, String seatno, StackPane root, VBox vb1, Label msg, int busid) {
    	
    	Bookings btable = new Bookings();
    	
    	if(seatno.isEmpty()) {
    		msg.setText("Seat number cannot be empty");
    		root.getChildren().add(vb1);
    	}
    	else if(!isNum(seatno)) {
    		msg.setText("Seat No. format is incorrect");
    		root.getChildren().add(vb1);
    	}
    	else if(Integer.parseInt(seatno)<1 || Integer.parseInt(seatno)>41) {
    		msg.setText("Seat No. entered is invalid");
    		root.getChildren().add(vb1);
    	}
    	else if(btable.bookedseats(busid, date).contains(Integer.parseInt(seatno))) {
    		msg.setText("Sorry! That seat is already booked");
    		root.getChildren().add(vb1);
    	}
    	else {
    		try {
				successPage(primaryStage, busid, Integer.parseInt(seatno), date);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    public void successPage(Stage primaryStage, int id, int seatno, String date) throws FileNotFoundException {
    	
    	Bookings book_table = new Bookings();
    	book_table.addRow(curr_uid, id, seatno, date);
    	
    	Image image = new Image(new FileInputStream("C:\\Users\\Amisha Upadhyay\\Desktop\\images\\green_tick.gif"));
        ImageView tick = new ImageView(image);
        
        Label success = new Label();
        success.setText("Booked Successfully!");
        success.setFont(new Font("Arial", 20));
        
        Label passenger = new Label();
        passenger.setText("Name: "+curr_fname+" "+curr_lname);
        passenger.setFont(new Font("Arial", 20));
        passenger.setWrapText(true);
        passenger.setAlignment(Pos.CENTER);
        
        Label busid = new Label();
        busid.setText("Bus ID: "+id);
        busid.setFont(new Font("Arial", 20));
        
        Label seat = new Label();
        seat.setText("Seat No.: "+seatno);
        seat.setFont(new Font("Arial", 20));
        
        Label bookdate = new Label();
        bookdate.setText("Date: "+date);
        bookdate.setFont(new Font("Arial", 20));
        
        VBox vb1 = new VBox();
        
        vb1.getChildren().addAll(passenger, busid, seat, bookdate);
    	vb1.setSpacing(20);
    	vb1.setAlignment(Pos.CENTER);
    	vb1.setMaxWidth(300);
    	vb1.setMinHeight(250);
    	vb1.setStyle("-fx-background-color: skyblue; -fx-border-color: blue;");
    	
    	Button ok = new Button("OK");
    	ok.setFont(new Font("Arial", 20));
    	ok.setMinWidth(300);
    	ok.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	ok.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	bookingPage(primaryStage, id);
            }
        });
    	
    	VBox vb = new VBox();
        
        vb.getChildren().addAll(tick, success, vb1, ok);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	primaryStage.setScene(new Scene(vb, 400, 700));    
    	
    }
    
    public boolean isNum (String str) {
    	
    	for(int i=0;i<str.length();i++) {
    		if(!Character.isDigit(str.charAt(i))) {return false;}
    	}
    	
    	return true;
    	
    }
    
    public String getDate(String str) {
    	Date date = null;
    	String res = null;
    	
    	try {
    	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	    formatter.setLenient(false);
    	    date = formatter.parse(str);
    	} catch (ParseException e) { 
    	    return null;
    	}
    	
    	String arr[] = str.split("/");
    	
    	return arr[2]+"-"+arr[1]+"-"+arr[0];
    }
    
    public void reviewPage(Stage primaryStage, int busid) {
    	
    	Ratings_and_Reviews rr_table = new Ratings_and_Reviews();
    	
    	boolean flag = rr_table.reviewexists(curr_uid, busid);
    	
    	Label topmsg = new Label();
        if(flag) {
        	topmsg.setText("Edit your review for Bus ID: "+busid);
        }
        else {
        	topmsg.setText("Post your review for Bus ID: "+busid);
        }
        topmsg.setFont(new Font("Arial", 20));
        
        Integer arr[] = {1, 2, 3, 4, 5};
        
        ComboBox cb1 = new ComboBox(FXCollections.observableArrayList(arr));
    	cb1.setValue(arr[4]);

    	Label rate = new Label("Rating: ");
    	rate.setFont(new Font("Arial", 20));
    	    	    	
    	HBox hb1 = new HBox();
    	hb1.getChildren().addAll(rate, cb1);
    	hb1.setSpacing(5);
    	hb1.setAlignment(Pos.CENTER);
    	
    	Label review = new Label("Feedback: ");
    	review.setFont(new Font("Arial", 20));
    	
    	TextArea feedback = new TextArea();
    	feedback.setPromptText("Please share your experience with us");
    	feedback.setMaxSize(300, 200);
    	feedback.setMinSize(300, 200);
    	feedback.setWrapText(true);
    	
    	if(flag) {
    		feedback.setText(rr_table.getreview(curr_uid, busid));
    	}
    	
    	Label error = new Label();
    	error.setFont(new Font("Arial", 20));
    	error.setWrapText(true);
    	error.setMaxWidth(300);
    	error.setMinSize(300, 100);
    	error.setTextAlignment(TextAlignment.CENTER);
    	error.setStyle("-fx-text-fill: grey; -fx-text-alignment: center; -fx-label-padding: 5px");
    	error.setAlignment(Pos.CENTER);
    	
    	Button submit = new Button("Submit");
    	submit.setFont(new Font("Arial", 20));
    	submit.setMinWidth(300);
    	submit.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	submit.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	if(feedback.getText().isEmpty()) {
            		error.setStyle("-fx-border-color: blue; -fx-background-color: skyblue;");
            		error.setText("Feedback cannot be empty!");
            	}
            	else {
            		try {
            			int rating = Integer.parseInt(cb1.getValue().toString());
    					reviewpostedPage(primaryStage, busid, flag, rating, feedback.getText());
    				} catch (FileNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
            	}           	
            }
        });
    	
    	Button cancel = new Button("Cancel");
    	cancel.setFont(new Font("Arial", 20));
    	cancel.setMinWidth(300);
    	cancel.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	cancel.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	bookingPage(primaryStage, busid);
            }
        });
    	
    	VBox vb = new VBox();
        
        vb.getChildren().addAll(topmsg, hb1, review, feedback, submit, cancel, error);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	primaryStage.setScene(new Scene(vb, 400, 700));

    	
    }
    
    public void reviewpostedPage(Stage primaryStage, int id, boolean flag, int rating, String review) throws FileNotFoundException {
    	
    	Ratings_and_Reviews rr_table = new Ratings_and_Reviews();
    	
    	if(flag) {
    		rr_table.updateRow(curr_uid, id, rating, review);
    	}
    	else {
    		rr_table.addRow(curr_uid, id, rating, review);
    	}
    	
    	Image image = new Image(new FileInputStream("C:\\Users\\Amisha Upadhyay\\Desktop\\images\\green_tick.gif"));
        ImageView tick = new ImageView(image);
        
        Label success = new Label();
        success.setText("Your review has been posted successfully!");
        success.setFont(new Font("Arial", 20));
        success.setWrapText(true);
        success.setMaxWidth(300);
        
        if(flag) {
        	success.setText("Your review has been updated successfully!");
        }
            	
    	Button ok = new Button("OK");
    	ok.setFont(new Font("Arial", 20));
    	ok.setMinWidth(300);
    	ok.setStyle("-fx-text-fill: white; -fx-background-radius: 25; -fx-border-radius: 25; -fx-background-color: teal");
    	
    	ok.setOnAction(new EventHandler<ActionEvent>() {
   		 
            @Override
            public void handle(ActionEvent event) {
            	reviewPage(primaryStage, id);
            }
        });
    	
    	VBox vb = new VBox();
        
        vb.getChildren().addAll(tick, success, ok);
    	vb.setSpacing(30);
    	vb.setAlignment(Pos.TOP_CENTER);
    	vb.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	primaryStage.setScene(new Scene(vb, 400, 700));    
    	
    }
    
}