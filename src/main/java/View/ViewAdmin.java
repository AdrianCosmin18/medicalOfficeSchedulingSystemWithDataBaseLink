package View;

import classes.clinica.Clinica;
import classes.persoane.Admin;
import classes.persoane.Client;
import classes.persoane.Medic;

import java.util.ArrayList;

public class ViewAdmin extends View{

    public ViewAdmin(Admin admin){

        super(admin);
    }

    @Override
    public void meniu(){

        System.out.println("\n\nCont meu de Medic Admin : " + admin.getNume());
        System.out.println("\nInformtii generale: ");
        System.out.print("Numarul total de clienti inregistrati : " + clientRepository.all().size());
        System.out.println("");

        System.out.println("\n======================================");
        System.out.println("Meniul de tip client:");
        System.out.println("1. Lista tuturor clinicilor MedLife cu informatii");
        System.out.println("2. Informatii suplimentare despre o anumita clinica");//afiseaza describe-ul clincii dar si informatii depsre medici in acelasi timp
        System.out.println("3. Afla lista specializarilor unei clinici");
        System.out.println("4. Afla adresa unei anumite clinici");
        System.out.println("5. Lista tuturor medicilor");


        System.out.println("\n======================================");
        System.out.println("Meniu de admin:");
        System.out.println("10. Lista tuturor persoanelor: ");
        System.out.println("11. Adauga un nou client/medic");
        System.out.println("12. Sterge un client/medic");
        System.out.println("13. Modifica un client/medic");

        System.out.println("\n14. Adauga o noua clinica");
        System.out.println("15. Sterge o clinica");
        System.out.println("16. Modifica o clinica");

        System.out.println("\n20. Iesire");
    }


    @Override
    public void play() {

        boolean run = true;
        int alegere;

        while(run){

            meniu();
            System.out.println("\nTasta : ");
            alegere = Integer.parseInt(read.nextLine());

            switch (alegere){

                case 1: listaClinici();
                    break;

                case 2: infoAnumitaClinica();
                    break;

                case 3: listaSpecializari();
                    break;

                case 4 : adresaClinica();
                    break;

                case 5 : listaMedici();
                    break;

                case 10: traverseAll();
                    break;

                case 11: addPerson();
                    break;

                case 12: deletePerson();
                    break;

                case 13: updatePerson();
                    break;

                case 14: addClinic();
                    break;

                case 15: deleteClinic();
                    break;

                case 16: updateClinic();
                    break;


                case 20: run = false;
                    break;
            }
        }
    }



    public void traverseAll(){

        System.out.println("Clienti: ");
        clientRepository.traverse();

        System.out.println("\nMedici: ");
        medicRepository.traverse();
    }


    public void addPerson(){

        System.out.println("1. Client / 2.Medic");
        int alegere = Integer.parseInt(read.nextLine());
        switch (alegere){

            case 1:{

                System.out.println("Se creeaza un nou client:");
                System.out.print("\nNume client : ");
                String nume = read.nextLine();
                System.out.print("\nParola : ");
                String parola = read.nextLine();
                System.out.print("\nVarsta : ");
                int varsta = Integer.parseInt(read.nextLine());
                System.out.print("\nAdresa : ");
                String adresa = read.nextLine();
                System.out.print("\nTelefon : ");
                String telefon = read.nextLine();

                Client clientNou = new Client(nume, parola, varsta, "Client", adresa, telefon);
                clientRepository.insert(clientNou);
                iOsingleton.write("adaugatClientNou");
                System.out.println("Client nou adaugat cu succes");
            }
            break;

            case 2:{

                System.out.println("Se creeaza un nou medic:");
                System.out.print("\nNume medic : ");
                String nume = read.nextLine();
                System.out.print("\nParola : ");
                String parola = read.nextLine();
                System.out.print("\nVarsta : ");
                int varsta = Integer.parseInt(read.nextLine());
                System.out.print("\nSpecializare : ");
                String specializare = read.nextLine();
                System.out.print("\nSalariu : ");
                double salariu = Double.parseDouble(read.nextLine());
                System.out.println("\nExperienta");
                int xp = Integer.parseInt(read.nextLine());

                Medic medicNou = new Medic(nume, parola, varsta, "Medic", specializare, salariu, xp);
                medicRepository.insert(medicNou);
                iOsingleton.write("adaugatMedicNou");
                System.out.println("Medic nou adaugat cu succes");
            }
            break;
        }
    }

    public void deletePerson(){

        System.out.println("Dortiti sa stergeti medic sau client ?");
        System.out.println("1. Client / 2.Medic");
        int alegere = Integer.parseInt(read.nextLine());
        System.out.print("ID-ul persoanei pe care doriti sa o stergeti: ");
        int id = Integer.parseInt(read.nextLine());

        switch (alegere){

            case 1:{

                Client client = clientRepository.getClientByID(id);
                if(client != null){

                    clientRepository.delete(id);
                    System.out.println("Client sters cu succes !");
                    iOsingleton.write("clientSters");
                }
                else{

                    System.out.println("nu exista client cu acest id");
                }
            }
            break;


            case 2:{

                Medic medic = medicRepository.getMedicByID(id);
                if(medic != null){

                    medicRepository.delete(id);
                    System.out.println("Medic sters cu sucees");
                    iOsingleton.write("medicSters");
                }
                else{

                    System.out.println("nu existyta medic cu acest id");
                }
            }
            break;
        }
    }

    public void updatePerson() {

        System.out.println("Dortiti sa modificati medic sau client ?");
        System.out.println("1. Client / 2.Medic");
        int alegere = Integer.parseInt(read.nextLine());
        System.out.print("ID-ul persoanei pe care doriti sa o modificati: ");
        int id = Integer.parseInt(read.nextLine());

        switch (alegere) {


            case 1: {

                Client client = clientRepository.getClientByID(id);
                if (client != null) {

                    System.out.print("\nNume modificat client : ");
                    String nume = read.nextLine();
                    System.out.print("\nParola modificata: ");
                    String parola = read.nextLine();
                    System.out.print("\nVarsta modificata: ");
                    int varsta = Integer.parseInt(read.nextLine());
                    System.out.print("\nAdresa : ");
                    String adresa = read.nextLine();
                    System.out.print("\nTelefon : ");
                    String telefon = read.nextLine();

                    Client clientModificat = new Client(nume, parola, varsta, "Client", adresa, telefon);
                    clientModificat.setId(client.getId());
                    clientRepository.update(clientModificat);

                    System.out.println("client modificat cu succes !");
                    iOsingleton.write("modifcareClient");
                }
            }
            break;

            case 2: {

                Medic medic = medicRepository.getMedicByID(id);
                if (medic != null) {

                    System.out.print("\nNume modificat medic : ");
                    String nume = read.nextLine();
                    System.out.print("\nParola modificata : ");
                    String parola = read.nextLine();
                    System.out.print("\nVarsta modificata : ");
                    int varsta = Integer.parseInt(read.nextLine());
                    System.out.print("\nSpecializare : ");
                    String specializare = read.nextLine();
                    System.out.print("\nSalariu : ");
                    double salariu = Double.parseDouble(read.nextLine());
                    System.out.print("\nExperienta :");
                    int xp = Integer.parseInt(read.nextLine());

                    Medic medicModificat = new Medic(nume, parola, varsta, "Medic", specializare, salariu, xp);
                    medicModificat.setId(medic.getId());
                    medicRepository.update(medicModificat);

                    System.out.println("medic modificat cu succes !");
                    iOsingleton.write("modificareMedic");
                }
            }
            break;
        }
    }



    public void addClinic(){

        System.out.println("Atentie, acum la cerarea clinicii, va trebui sa inregistrati si medici pentru aceasta clinica pentru ca nu va avea niciunul la inceput !!!");
        System.out.print("Nume clinica noua : ");
        String nume = read.nextLine();
        System.out.print("Oras: ");
        String oras = read.nextLine();
        System.out.print("Adresa: ");
        String adresa = read.nextLine();

        System.out.println("Veti introduce id-urile medicilor care vor lucra in aceasta clinica iar specializarile se vor adauga automat");
        System.out.println("Atentie, nr de specializari = nr de medic si  medicii trebuie sa existe deja pentru a-i asigna clinicii");

        ArrayList<Integer> mediciID = new ArrayList<>();
        System.out.print("Numarul de medici pe care ii veti adauga:");
        int nrMedici = Integer.parseInt(read.nextLine());

        for(int i = 0; i < nrMedici; i++){

            int id = Integer.parseInt(read.nextLine());
            mediciID.add(id);
        }
        //System.out.println("lista id medici: " + mediciID);

        ArrayList<String> specializari = new ArrayList<>();
        for(Integer id:mediciID){

            Medic medic = medicRepository.getMedicByID(id);
            specializari.add(medic.getSpecializare());
        }
        //System.out.println("specializari: " + specializari);

        clinicaRepository.insert(new Clinica(nume, oras, adresa, specializari, mediciID));
        iOsingleton.write("adaugareClinica");
        System.out.println("Clinica adaugata cu succes !!!");
    }

    public void updateClinic(){

        System.out.print("ID-ul clinici pe care doriti sa o modficati:");
        int id = Integer.parseInt(read.nextLine());

        if(clinicaRepository.getClinicaByID(id) != null){

            Clinica clinica = clinicaRepository.getClinicaByID(id);
            System.out.println("Numele modificat al clinicii: ");
            String nume = read.nextLine();
            System.out.println("Orasul clinicii: ");
            String oras = read.nextLine();
            System.out.println("Adresa clinicii: ");
            String adresa = read.nextLine();

            System.out.println("Veti adauga o lista de medici de la 0 !");

            ArrayList<Integer> mediciID = new ArrayList<>();
            System.out.print("Numarul de medici pe care ii veti adauga:");
            int nrMedici = Integer.parseInt(read.nextLine());
            for(int i = 0; i < nrMedici; i++){

                System.out.print("id= ");
                int medicId = Integer.parseInt(read.nextLine());
                mediciID.add(id);
            }

            ArrayList<String> specializari = new ArrayList<>();
            for(Integer i:mediciID){

                Medic medic = medicRepository.getMedicByID(i);
                specializari.add(medic.getSpecializare());
            }

            Clinica clinicaModificata = new Clinica(nume, oras, adresa, specializari, mediciID);
            clinicaModificata.setId(clinica.getId());
            clinicaRepository.update(clinicaModificata);
            System.out.println("Clinica modificata cu succes !!");
            iOsingleton.write("clinicaModificata");
        }
        else{

            System.out.println("nu exista clinica cu acest id !!!");
        }
    }

    public void deleteClinic(){

        System.out.println("iD-ul clinicii pe care doriti sa o stergeti: ");
        int id = Integer.parseInt(read.nextLine());

        if(clinicaRepository.existsID(id)){

            clinicaRepository.delete(id);
            System.out.println("Clinica stearsa cu succes !!!");
            iOsingleton.write("stergereClinica");
        }
        else{

            System.out.println("nu s-a realizat stergerea");
        }
    }
}

