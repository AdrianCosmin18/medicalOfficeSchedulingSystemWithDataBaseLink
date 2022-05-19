package View;

import classes.persoane.Client;

public class ViewClient extends View{

    public ViewClient(Client client){

        super(client);
    }

    @Override
    public void meniu() {

        System.out.println("\n\nContul meu : " + client.getNume());
        System.out.println("======================================");
        System.out.println("1. Lista tuturor clinicilor MedLife cu informatii");
        System.out.println("2. Informatii suplimentare despre o anumita clinica");//afiseaza describe-ul clincii dar si informatii depsre medici in acelasi timp
        System.out.println("3. Afla lista specializarilor unei clinici");
        System.out.println("4. Afla adresa unei anumite clinici");
        System.out.println("5. Lista tuturor medicilor");
        System.out.println("6. Inregistreaza o programare");
        System.out.println("7. Toate programarilor mele");
        System.out.println("8. Modifica data unei programari");
        System.out.println("9. Stergeti o programare");
        System.out.println("10. Iesire");
    }

    @Override
    public void play() {

        boolean run = true;
        int alegere;

        while (run) {

            meniu();
            System.out.println("\nTasta : ");
            alegere = Integer.parseInt(read.nextLine());

            switch (alegere) {

                case 1:
                    listaClinici();
                    break;

                case 2:
                    infoAnumitaClinica();
                    break;

                case 3:
                    listaSpecializari();
                    break;

                case 4:
                    adresaClinica();
                    break;

                case 5:
                    listaMedici();
                    break;

                case 6:
                    inregistreazaProgramare();
                    break;

                case 7:
                    istoricProgramari();
                    break;

                case 8:
                    modificaDataProgramare();
                    break;

                case 9:
                    stergeProgramare();
                    break;

                case 10:
                    run = false;
                    break;
            }
        }
    }
}
