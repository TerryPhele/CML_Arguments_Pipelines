package org.thereso;

import org.apache.commons.cli.*;

public class Main {

    private String name;
    private String lastName;
    private String email;
    private int cell;

    private Options cmlOptions ;
    private CommandLineParser parser ;
    private  HelpFormatter helpFormatter;
    private CommandLine cmlArgs;


    public Main( String[] Args){
        this.cmlOptions = new Options();
        this.parser = new DefaultParser();
        this.helpFormatter = new HelpFormatter();
        configure(Args);
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){ return this.name;}

    public void setLastName( String lname){
        this.lastName = lname;
    }

    public  String getLastName() { return this.lastName;}

    private void setEmail( String email){
        this.email = email;
    }

    public  String getEmail(){ return this.email;}
    private void setCell( int cellNo){
      this.cell = cellNo;
    }

    public int getCell(){ return this.cell;}

    public  void configure( String[] args)
    {
        Option  nameOption = new Option("n","firstname",true,"user firstName");
        nameOption.setRequired( true);
        Option  lastNameOption = new Option("l","lastname",true,"user lastName");
        lastNameOption.setRequired(true);
        Option emailOption = new Option("e", "email",true,"user email address");
        emailOption.setRequired(true);
        Option cellOption = new Option("c","cell",true,"user cellphone number");

        cmlOptions.addOption(nameOption);
        cmlOptions.addOption(lastNameOption);
        cmlOptions.addOption(emailOption);
        cmlOptions.addOption(cellOption);

        try {
            cmlArgs = this.parser.parse(this.cmlOptions, args);
        }catch ( ParseException e)
        {
            helpFormatter.printHelp("User Profile Info", this.cmlOptions);
        }
        if( cmlArgs != null) {
            setName(this.cmlArgs.getOptionValue(nameOption));
            setLastName(this.cmlArgs.getOptionValue("lastname"));
            setEmail(this.cmlArgs.getOptionValue("e"));
            if( this.cmlArgs.hasOption("c")){
                setCell( Integer.parseInt(this.cmlArgs.getOptionValue("c")));
            }else{
                setCell(0);
            }
        }

    }

    public void displayUserInfo(){
        System.out.println("-".repeat(5)+"USER PROFILE INFORMATION"+"-".repeat(5));
        System.out.println("_".repeat(34));
        System.out.printf("Name: %28s", getName());
        System.out.printf("LastName: %24s",getLastName());
        System.out.printf("Email Address: %19s", getEmail());
        if( this.cell != 0)
            System.out.printf("Cell No: %25d", getCell());
        else
            System.out.printf("Cell No: %25s", "N/A");
    }
    public static void main(String[] args) {

        Main main;
        if(args.length !=0) main = new Main( args);
        else main = new Main( new String[]{"-n Thereso --lastname Phele -e thereso199@gmail.com -cell +27765641019"});

        main.displayUserInfo();
    }
}
