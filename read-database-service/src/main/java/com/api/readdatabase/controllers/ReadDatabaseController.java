package com.api.readdatabase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.readdatabase.dtos.ColumnDto;
import com.api.readdatabase.dtos.DataBaseDto;
import com.api.readdatabase.dtos.TableDto;
import com.api.readdatabase.services.ColumnService;
import com.api.readdatabase.services.DataBaseService;
import com.api.readdatabase.services.TableService;

@RestController
public class ReadDatabaseController implements BaseController {

	@Autowired
	private DataBaseService dataBaseService;

	@Autowired
	private TableService tableService;

	@Autowired
	private ColumnService columnService;

	@GetMapping(value = "/databases")
	public ResponseEntity<List<DataBaseDto>> getAllDatabases() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.dataBaseService.findAll());
	}

	@GetMapping(value = "/databases/initializers")
	public ResponseEntity<List<DataBaseDto>> getDatabasesInitializers() throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.dataBaseService.getDataBasesInitializer());
	}

	@GetMapping(value = "/tables/{database}")
	public ResponseEntity<List<TableDto>> getTablesByDataBase(@PathVariable String database) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.tableService.getByDataBase(database));
	}

	@GetMapping(value = "/columns/{database}/{table}")
	public ResponseEntity<List<ColumnDto>> getColumns(@PathVariable String database, @PathVariable String table)
			throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(this.columnService.getByTable(database, table));
	}
}
