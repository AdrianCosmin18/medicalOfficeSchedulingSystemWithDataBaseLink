package View;

import Repositories.*;
import classes.IOsingleton;
import classes.clinica.Clinica;
import classes.persoane.*;
import classes.programare.Programare;

import java.time.LocalDateTime;
import java.util.*;

public abstract class View {

    protected Scanner read;
    protected Client client;
    protected Medic medic;
    protected Admin admin;

    protected ProgramariRepository programariRepository;
    protected ClinicaRepository clinicaRepository;
    protected AdminRepository adminRepository;
    protected ClientRepository clientRepository;
    protected MedicRepository medicRepository;

    protected IOsingleton iOsingleton;


    public View(Persoana p){

        programariRepository = new ProgramariRepository();
        clinicaRepository = new ClinicaRepository();
        adminRepository = new AdminRepository();
        clientRepository = new ClientRepository();
        medicRepository = new MedicRepository();
        iOsingleton = IOsingleton.getInstance();
        read = new Scanner(System.in);

        if(p instanceof Client){

            this.client = (Client) p;
            this.medic = null;
            this.admin = null;
        }
        else if(p instanceof Medic){

            this.client = null;
            this.medic = (Medic) p;
            this.admin = null;
        }
        else if(p instanceof Admin) {

            this.admin = (Admin) p;
            this.client = null;
            this.medic = null;
        }
    }

    public abstract void meniu();
    public abstract void play();


    public void listaClinici(){

        System.out.print("Doriti afisate clinicile in ordine crescatoare dupa numarul de specializari (da/nu) : ");
        String alegere;
        alegere = read.nextLine();

        List<Clinica> clinici = clinicaRepository.all();

        if(alegere.equals("da")){

            Collections.sort(clinici);

            for(Clinica c : clinici){

                System.out.println(c);
                System.out.println();
            }
        }
        else if(alegere.equals("nu")){

            clientRepository.traverse();
        }
        iOsingleton.write("afiseazaListaClinici");
    }

    public void infoAnumitaClinica(){

        System.out.println("Introducei ID-ul clinicii depsre care doriti informatii amanuntite");
        int id = Integer.parseInt(read.nextLine());


        if(clientRepository.existsID(id)){

            Clinica clinica = clinicaRepository.getClinicaByID(id);
            ArrayList<Integer> listaMedici = clinica.getMediciID();

            System.out.println(clinica);

            for(Integer idMedic : listaMedici){

                Medic medic = (Medic) medicRepository.getMedicByID(idMedic);
                System.out.println("\nMedic :");
                System.out.println(medic);
            }

            iOsingleton.write("infoClinica");
        }
        else{

            System.out.println("Nu exista o clinica cu aceast ID");
        }
    }

    public void adresaClinica(){

        System.out.print("Introduceti ID-ul clincii a carei adresa doriti sa o aflati : ");
        int id = Integer.parseInt(read.nextLine());

        if(clientRepository.existsID(id)){

            Clinica clinica = clinicaRepository.getClinicaByID(id);
            System.out.println("Adresa clinici " + clinica.getNume() + " este : " + clinica.getAdresa());
            iOsingleton.write("adresaClinica");
        }
        else{
            System.out.println("Nu exista clinica cu acest id");
        }
    }

    public void listaSpecializari(){

        System.out.print("Introdu ID-ul clinicii : ");
        int id = Integer.parseInt(read.nextLine());

        if(clinicaRepository.existsID(id)){

            ArrayList<String> specializari = clinicaRepository.getClinicaByID(id).getSpecializari();
            System.out.println("Clinica : " + clinicaRepository.getClinicaByID(id).getNume() + " are specializarile : ");
            for(String s:specializari){

                System.out.println(s);
            }
            iOsingleton.write("afiseazaListaSpecializari");
        }
    }

    public void listaMedici(){

        System.out.print("Doriti afisati medici in ordine crescatoare dupa numarul de ani de experienta ai acestora (da/nu) : ");
        String alegere;
        alegere = read.nextLine();

        List<Medic> medici = medicRepository.all();

        if(alegere.equals("da")){

            Collections.sort(medici);
        }

        for(Medic m : medici){

            System.out.println(m);
            System.out.println();
        }

        iOsingleton.write("afiseazaListaMedici");
    }

    public void inregistreazaProgramare(){

        System.out.println("Introduce ID-ul clinicii in care doriti sa va programati :");
        int idClinica = Integer.parseInt(read.nextLine());

        if(clinicaRepository.existsID(idClinica)){//verif daca exista clinica

            Clinica clinica = clinicaRepository.getClinicaByID(idClinica);

            System.out.println("Clinica : " + clinica.getNume() + " are specializarile : ");//afis specializ clinicii
            for(String s: clinica.getSpecializari()){

                System.out.println(s);
            }

            System.out.print("Introdcueti specializarea la care doriti sa va programati : ");
            String specializare = read.nextLine();

            if(clinica.existaSpecializare(specializare)){//verif dc clinica are specializarea introdusa

                int medicID = -1;
                ArrayList<Integer> mediciID = clinica.getMediciID();//obt id-ul medicului pt a inregistra programarea
                for(Integer id: mediciID) {

                    Medic m = (Medic) medicRepository.getMedicByID(id);
                    if(m.getSpecializare().equals(specializare)){

                        medicID = m.getId();
                        break;
                    }
                }

                System.out.println("Introdu noua data la care vrei sa te programezi");
                System.out.println("Exemplu introducere data : 16:30, 18 decembrie 2022");
                System.out.print("Data : ");
                String data = read.nextLine();

                programariRepository.insert(new Programare(this.client.getId(), medicID, clinica.getId(), data));
                System.out.println("Inregistrare realizata cu succes !!!");

                iOsingleton.write("inregistrareProgramare");
            }
            else{

                System.out.println("Nu exista aceasta specializare in aceasta clinica");
            }
        }
        else{

            System.out.println("Nu exista o clinica cu acest ID");
        }
    }

    public void istoricProgramari(){

        System.out.println("Istoricul meu :");
        List<Programare> programari = programariRepository.getListByClientID(client.getId());

        int count = 0;
        for(Programare p: programari){

            count++;
            System.out.println("Programarea : " + count);
            System.out.println("ID: " + p.getId());
            System.out.println("Clinica: " + clinicaRepository.getClinicaByID(p.getIdClinica()).getNume());
            System.out.println("Specializarea: " + (medicRepository.getMedicByID(p.getIdMedic())).getSpecializare());
            System.out.println("Data: " + p.getData());
            System.out.println();
        }

        iOsingleton.write("afiseazaIstoricProgramari");
    }

    public void modificaDataProgramare(){

        System.out.println("Introduce data la care esti programat");
        System.out.println("Exemplu introducere data : 16:30, 18 decembrie 2022");
        System.out.print("Data :");
        String dataVeche = read.nextLine();


        System.out.println("Introdu noua data la care vrei sa te programezi");
        System.out.println("Exemplu introducere data : 16:30, 18 decembrie 2022");
        System.out.print("Data :");
        String dataNoua = read.nextLine();


        List<Programare> programari = programariRepository.getListByClientID(client.getId());
        for(Programare p: programari){

            if(p.getData().equals(dataVeche)){

                p.setData(dataNoua);
                programariRepository.update(p);
                System.out.println("Data modificata cu succes !!!");
                break;
            }
        }
        iOsingleton.write("modificareProgramare");
    }

    public void stergeProgramare(){

        System.out.print("Introduce ID-ul programarii pe care vrei sa o stergi : ");
        int id = Integer.parseInt(read.nextLine());

        if(programariRepository.existsID(id)){

            programariRepository.delete(id);
            System.out.println("Stergerea programarii a fost realizata cu succes !!!");
            iOsingleton.write("stergeProgramare");
        }
        else{
            System.out.println("Nu exista o programare cu acest ID !!!");
        }
    }
}
