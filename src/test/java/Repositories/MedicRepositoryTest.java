package Repositories;

import classes.persoane.Medic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicRepositoryTest {

    @Test
    public void testInsert(){

        MedicRepository medicRepository = new MedicRepository();
        medicRepository.insert(new Medic("Mita", "mita", 54, "Medic", "cardiologie", 4750, 27));
    }

    @Test
    public void testDelete(){

        MedicRepository medicRepository = new MedicRepository();
        medicRepository.delete(11);
    }

    @Test
    public void testAll(){

        MedicRepository medicRepository = new MedicRepository();
        System.out.println(medicRepository.all());
    }

    @Test
    public void testUpdate(){

        MedicRepository medicRepository = new MedicRepository();
        Medic m = new Medic("Vlad Mihai Morar","prola",35,"Medic","nutritie",3300.0,9);
        m.setId(10);

        medicRepository.update(m);
    }

    @Test
    public void testExistsID(){

        MedicRepository medicRepository = new MedicRepository();
        assertEquals(false, medicRepository.existsID(11));
    }


    @Test
    public void testGetMedicByID(){

        MedicRepository medicRepository = new MedicRepository();
        System.out.println(medicRepository.getMedicByID(11));
    }
}