package com.GMH.digital.BarberPub.by.GMH.config;

import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.Address;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRepository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public void run(String... args) {
//        serviceRespository.deleteAll();
//        businessRespository.deleteAll();
//
//        List<Business> fakeBusinesses = getFakeBusinesses();
//        businessRespository.saveAll(fakeBusinesses);
//
//        for (Business business : fakeBusinesses) {
//            serviceRespository.saveAll(getFakeServices(business));
//        }
    }

    private List<Business> getFakeBusinesses() {
        List<Business> businesses = new ArrayList<>();

        // Criar endereços para cada business
        Address address1 = new Address("Rua das Flores", "123", null, "Centro", "São Paulo", "SP", "01000-000", "Brasil");
        Address address2 = new Address("Av. Brasil", "456", null, "Vila Olimpia", "São Paulo", "SP", "04038-001", "Brasil");
        Address address3 = new Address("Praça Central", "789", null, "Centro Histórico", "São Paulo", "SP", "01002-000", "Brasil");
        Address address4 = new Address("Rua do Comércio", "321", null, "Liberdade", "São Paulo", "SP", "01503-000", "Brasil");
        Address address5 = new Address("Av. Paulista", "1000", "Sala 101", "Bela Vista", "São Paulo", "SP", "01310-100", "Brasil");

        businesses.add(new Business(null, "Barbearia do João", "12345678000101", address1, "joao@barbearia.com", "(11) 99999-1111", "A melhor barbearia do bairro!"));
        businesses.add(new Business(null, "Barbearia Estilo", "22345678000102", address2, "contato@estilo.com", "(11) 99999-2222", "Cortes modernos e atendimento vip."));
        businesses.add(new Business(null, "Barbearia Clássica", "32345678000103", address3, "classica@barbearia.com", "(11) 99999-3333", "Tradição e qualidade desde 1980."));
        businesses.add(new Business(null, "Barbearia do Zé", "42345678000104", address4, "ze@barbearia.com", "(11) 99999-4444", "Ambiente descontraído e profissionais experientes."));
        businesses.add(new Business(null, "Barbearia Premium", "52345678000105", address5, "premium@barbearia.com", "(11) 99999-5555", "Serviços premium para clientes exigentes."));

        return businesses;
    }
}
