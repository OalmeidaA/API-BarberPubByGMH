package com.GMH.digital.BarberPub.by.GMH.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GMH.digital.BarberPub.by.GMH.dto.BarberDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.BarbershopDTO;
import com.GMH.digital.BarberPub.by.GMH.dto.ServicesBarberDTO;
import com.GMH.digital.BarberPub.by.GMH.entities.Barber;
import com.GMH.digital.BarberPub.by.GMH.entities.ServicesBarber;
import com.GMH.digital.BarberPub.by.GMH.services.BarberService;
import com.GMH.digital.BarberPub.by.GMH.services.BarbershopService;
import com.GMH.digital.BarberPub.by.GMH.services.ServicesBarberService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	private ServicesBarberService servicesBarberService;
	
	@Autowired
	private BarberService barberService;
	
	@Autowired
	private BarbershopService shopService;
	
	@PostMapping
	public ResponseEntity<Void> insertService(@RequestBody ServicesBarberDTO serviceDto){
		ServicesBarber obj = servicesBarberService.insertService(serviceDto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ServicesBarberDTO>> findAllServices(){
		List<ServicesBarber> list = servicesBarberService.findAllServices();
		List<ServicesBarberDTO> listdto = list.stream().map(x -> new ServicesBarberDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listdto);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteService(@PathVariable Long id) throws Exception {
		servicesBarberService.deleteService(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<ServicesBarberDTO> updateService(@PathVariable Long id, @RequestBody ServicesBarberDTO serviceDTO) throws Exception {
		serviceDTO = servicesBarberService.updateServices(id, serviceDTO);
		return ResponseEntity.ok(serviceDTO);
	}
	
	@GetMapping("/barber")
	public ResponseEntity<List<BarberDTO>> findAllBarber(){
		List<Barber> list = barberService.findAllBarber();
		List<BarberDTO> listDto = list.stream().map(x -> new BarberDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(listDto);
	}
	
	@PostMapping("/barber")
	public ResponseEntity<Void> insertBarber(@RequestBody BarberDTO barberDTO){
		Barber newBarber = barberService.insertBarber(barberDTO);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/barber/{id}")
	public ResponseEntity<Void> deleteBarber(@PathVariable Long id) throws Exception{
		barberService.deleteBarber(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/barber/{id}")
	public ResponseEntity<BarberDTO> updateBarber(@RequestBody BarberDTO dto, @PathVariable Long id){
		BarberDTO update = barberService.updateBarber(dto, id);
		return ResponseEntity.ok(update);
	}
	
	@PostMapping("/barbershop")
	public ResponseEntity<BarbershopDTO> createBarbershop(@RequestBody BarbershopDTO dto){
		BarbershopDTO newShop = shopService.createBarbershop(dto);
		return ResponseEntity.ok(newShop);
	}
	
	@GetMapping("/barbershop")
	public ResponseEntity<List<BarbershopDTO>> findAllBarbershop(){
		List<BarbershopDTO> listShop = shopService.findAllBarbershop();
		return ResponseEntity.ok(listShop);
	}
	
}

