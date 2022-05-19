package Repositories;

import classes.programare.Programare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgramariRepositoryTest {

    @Test
    public void testInsert(){

        ProgramariRepository programariRepository = new ProgramariRepository();
        Programare programare = new Programare(1,10,2,"19:30, 19 mai 2022");
        programariRepository.insert(programare);
    }

    @Test
    public void testDelete(){

        ProgramariRepository programariRepository = new ProgramariRepository();
        programariRepository.delete(8);
    }

    @Test
    public void testAll(){

        ProgramariRepository programariRepository = new ProgramariRepository();
        System.out.println(programariRepository.all());
    }

    @Test
    public void testUpdate(){

        ProgramariRepository programariRepository = new ProgramariRepository();
        Programare p = new Programare(7, 1, 2, 2, "16:45, 10 iulie 2022");
        programariRepository.update(p);
    }

    @Test
    public void testExistsID(){

        ProgramariRepository programariRepository = new ProgramariRepository();
        assertEquals(false, programariRepository.existsID(8));
    }

    @Test
    public void testGetProgramarebyID(){

        ProgramariRepository programariRepository = new ProgramariRepository();
        System.out.println(programariRepository.getProgramareByID(8));
    }

    @Test
    public void testGetListByCleintID(){

        ProgramariRepository programariRepository = new ProgramariRepository();
        System.out.println(programariRepository.getListByClientID(3));
    }

}