package Repositories;

import classes.persoane.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryTest {

    @Test
    public void testInsert(){

        AdminRepository adminRepository = new AdminRepository();
        Admin theo = new Admin("Theo Furtuna", "theo", 20, "Admin");
        Admin radu = new Admin("Radu Mihai", "radu", 21, "Admin");
        adminRepository.insert(theo);
        adminRepository.insert(radu);
    }

    @Test
    public void testAll(){

        AdminRepository adminRepository = new AdminRepository();
        System.out.println(adminRepository.all());
    }

    @Test
    public void testDelete(){

        AdminRepository adminRepository = new AdminRepository();
        adminRepository.delete(3);
    }

    @Test
    public void testUpdate(){

        AdminRepository adminRepository = new AdminRepository();
        Admin theo = new Admin("Theodor Furtuna", "theo", 21, "Admin");
        theo.setId(2);
        adminRepository.update(theo);
    }

    @Test
    public void testExistsID(){

        AdminRepository adminRepository = new AdminRepository();
        System.out.println(adminRepository.existsID(3));
    }

    @Test
    public void testgetAdminByID(){

        AdminRepository adminRepository = new AdminRepository();
        System.out.println(adminRepository.getAdminByID(3));
    }

}