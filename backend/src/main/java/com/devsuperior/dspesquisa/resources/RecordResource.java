package com.devsuperior.dspesquisa.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.devsuperior.dspesquisa.entities.dto.RecordDTO;
import com.devsuperior.dspesquisa.entities.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;

@RestController
@RequestMapping(value = "/records")
public class RecordResource {

	@Autowired
	private RecordService recordService;
	
	@GetMapping
	public ResponseEntity<List<RecordDTO>> findAll(){
		List<RecordDTO> records = recordService.findAll();
		return ResponseEntity.ok().body(records);
	}
	
	@PostMapping
	public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO recordInsertDTO){
		RecordDTO recordDTO = recordService.insert(recordInsertDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(recordDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
