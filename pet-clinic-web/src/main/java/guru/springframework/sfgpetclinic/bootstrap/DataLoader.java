package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {

        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType bird = new PetType();
        bird.setName("Bird");
        PetType savedBirdPetType = petTypeService.save(bird);

        System.out.println("Loaded Pet Types....");


        Owner owner1 = new Owner();
        owner1.setFirstName("Walter");
        owner1.setLastName("White");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Albuquerque");
        owner1.setTelephone("805-555-1234");

        Pet waltsPet = new Pet();
        waltsPet.setPetType(savedDogPetType);
        waltsPet.setName("Heisenberg");
        waltsPet.setOwner(owner1);
        waltsPet.setBirthDate(LocalDate.now());

        owner1.getPets().add(waltsPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jesse");
        owner2.setLastName("Pinkman");
        owner2.setAddress("666 Los Alamos Dr");
        owner2.setCity("Albuquerque");
        owner2.setTelephone("805-555-9666");

        Pet jessPet = new Pet();
        jessPet.setPetType(savedCatPetType);
        jessPet.setName("Capn Cook");
        jessPet.setOwner(owner2);
        jessPet.setBirthDate(LocalDate.now());

        owner2.getPets().add(jessPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Slappy");
        vet2.setLastName("McClain");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");

    }
}
