package com.GMH.digital.BarberPub.by.GMH.config;

import com.GMH.digital.BarberPub.by.GMH.entities.Business;
import com.GMH.digital.BarberPub.by.GMH.entities.Service;
import com.GMH.digital.BarberPub.by.GMH.repositories.BusinessRespository;
import com.GMH.digital.BarberPub.by.GMH.repositories.ServiceRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

//@Profile("test")
@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private BusinessRespository businessRespository;

    @Autowired
    private ServiceRespository serviceRespository;

    @Override
    public void run(String... args) {
        businessRespository.deleteAll();
        serviceRespository.deleteAll();

        List<Business> fakeBusinesses = getFakeBusinesses();
        businessRespository.saveAll(fakeBusinesses);

        for (Business business : fakeBusinesses) {
            serviceRespository.saveAll(getFakeServices(business));
        }
    }

    private List<Business> getFakeBusinesses() {
        List<Business> businesses = new ArrayList<>();
        businesses.add(new Business(null, "Barbearia do João", "12345678000101", "Rua das Flores, 123", "joao@barbearia.com", "(11) 99999-1111", "A melhor barbearia do bairro!"));
        businesses.add(new Business(null, "Barbearia Estilo", "22345678000102", "Av. Brasil, 456", "contato@estilo.com", "(11) 99999-2222", "Cortes modernos e atendimento vip."));
        businesses.add(new Business(null, "Barbearia Clássica", "32345678000103", "Praça Central, 789", "classica@barbearia.com", "(11) 99999-3333", "Tradição e qualidade desde 1980."));
        businesses.add(new Business(null, "Barbearia do Zé", "42345678000104", "Rua do Comércio, 321", "ze@barbearia.com", "(11) 99999-4444", "Ambiente descontraído e profissionais experientes."));
        businesses.add(new Business(null, "Barbearia Premium", "52345678000105", "Av. Paulista, 1000", "premium@barbearia.com", "(11) 99999-5555", "Serviços premium para clientes exigentes."));
        return businesses;
    }

    private List<Service> getFakeServices(Business business) {
        List<Service> services = new ArrayList<>();
        services.add(new Service(null, "Corte Masculino", 40, "00:30", business));
        services.add(new Service(null, "Barba Completa", 30, "00:20", business));
        services.add(new Service(null, "Corte + Barba", 60, "00:45", business));
        services.add(new Service(null, "Sobrancelha", 15, "00:10", business));
        services.add(new Service(null, "Hidratação Capilar", 25, "00:25", business));
        return services;
    }
}
