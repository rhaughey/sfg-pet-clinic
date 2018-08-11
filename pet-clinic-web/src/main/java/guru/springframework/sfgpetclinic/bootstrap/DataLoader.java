package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0){
            loadData();
        }
    }

    private void loadData() {
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

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


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

        Visit catVisit = new Visit();
        catVisit.setPet(jessPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Slappy");
        vet2.setLastName("McClain");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
