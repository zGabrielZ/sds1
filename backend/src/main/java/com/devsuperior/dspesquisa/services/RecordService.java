package com.devsuperior.dspesquisa.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.entities.dto.RecordDTO;
import com.devsuperior.dspesquisa.entities.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.repositories.GameRepository;
import com.devsuperior.dspesquisa.repositories.RecordRepository;
@Service
public class RecordService {

	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<RecordDTO> findAll(){
		List<Record> records =  recordRepository.findAll();
		return records.stream().map(x-> new RecordDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public RecordDTO insert(RecordInsertDTO recordInsertDTO) {
		Record record = new Record();
		record.setName(recordInsertDTO.getName());
		record.setAge(recordInsertDTO.getAge());
		record.setMoment(Instant.now());
		
		Game game = gameRepository.getOne(recordInsertDTO.getGameId());
		
		record.setGame(game);
		
		record =  recordRepository.save(record);
		return new RecordDTO(record);
	}
}
