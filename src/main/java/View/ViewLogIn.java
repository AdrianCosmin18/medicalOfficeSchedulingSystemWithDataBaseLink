package View;

import Repositories.AdminRepository;
import Repositories.ClientRepository;
import classes.persoane.Admin;
import classes.persoane.Client;

import java.util.Scanner;

public class ViewLogIn {

    private Scanner read ;
    private ClientRepository clientRepository;
    private AdminRepository adminRepository;

    public ViewLogIn(){

        read = new Scanner(System.in);
        clientRepository = new ClientRepository();
        adminRepository = new AdminRepository();
    }

    public void menu(){

        System.out.println("Exit : press 1");
        System.out.println("Log In : press 2");
        System.out.println("Register as customer, new account : press 3");
    }

    public void play(){

        boolean run = true;
        int choice;

        while (run){

            menu();
            System.out.println("\nChoice : ");
            choice = Integer.parseInt(read.nextLine());

            switch (choice){

                case 1 : run = false;
                    break;

                case 2 : logIn();
                    break;

                case 3 : register();
                    break;
            }
        }
    }

    public void logIn(){

        System.out.println("Name : ");
        String name = read.nextLine();

        System.out.println("Password : ");
        String password = read.nextLine();

        Client client = clientRepository.getClientByNameAndPassword(name, password);
        Admin admin = adminRepository.getAdminByNameAndPassword(name, password);

        if(client != null){

            new ViewClient(client).play();
        }
        else if(admin != null){

            new ViewAdmin(admin).play();
        }
        else{

            System.out.println("Nume sau parola gresita !!!");
        }
    }

    public void register(){

        System.out.println("Name : ");
        String nume = read.nextLine();

        while(clientRepository.existsName(nume)){

            System.out.println("This name is already taken, try another one");
            System.out.print("Name : ");
            nume = read.nextLine();
        }

        System.out.print("Parola : ");
        String parola = read.nextLine();

        System.out.print("Varsta : ");
        int varsta = Integer.parseInt(read.nextLine());

        String tip = "Client";

        System.out.print("Adresa: ");
        String adresa = read.nextLine();

        System.out.print("Telefon: ");
        String telefon = read.nextLine();

        Client clientNou = new Client(nume, parola, varsta, tip, adresa, telefon);
        clientRepository.insert(clientNou);
    }
}
