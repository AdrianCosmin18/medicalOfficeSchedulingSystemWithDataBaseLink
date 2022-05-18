package Repositories;


import classes.clinica.Clinica;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClinicaRepositoryTest {

    @Test
    public void testInsert(){

        ArrayList<String> sp = new ArrayList<>();
        sp.add("nutritie");
        sp.add("ortopedie");
        ArrayList<Integer> id = new ArrayList<>();
        id.add(6);
        id.add(1);
        Clinica clinica = new Clinica("Hyperclinica MedLife Valea Rosu", "Bucuresti", "Strada Turnului 10", sp, id);
        ClinicaRepository clinicaRepository = new ClinicaRepository();
        clinicaRepository.insert(clinica);
    }

    @Test
    public void testDelete(){

        ClinicaRepository clinicaRepository = new ClinicaRepository();
        clinicaRepository.delete(8);
    }

    @Test
    public void testAll(){

        ClinicaRepository clinicaRepository = new ClinicaRepository();
        System.out.println(clinicaRepository.all());
    }

    @Test
    public void testUpdate(){

        ArrayList<String> sp = new ArrayList<>();
        sp.add("sociologie");
        sp.add("dermatologie");
        ArrayList<Integer> id = new ArrayList<>();
        id.add(7);
        id.add(2);
        Clinica clinica = new Clinica(8, "Hyperclinica MedLife Valea Ros", "Buc", "Strada Turnului 1", sp, id);

        ClinicaRepository clinicaRepository = new ClinicaRepository();
        clinicaRepository.update(clinica);
    }

    @Test
    public void testTraverse(){

        ClinicaRepository clinicaRepository = new ClinicaRepository();
        clinicaRepository.traverse();
    }

    @Test
    public void testExistsID(){

        ClinicaRepository clinicaRepository = new ClinicaRepository();
        assertEquals(false, clinicaRepository.existsID(5));
    }

    @Test
    public void testGetClinicaById(){

        ClinicaRepository clinicaRepository = new ClinicaRepository();
        System.out.println(clinicaRepository.getClinicaByID(5));
    }

}