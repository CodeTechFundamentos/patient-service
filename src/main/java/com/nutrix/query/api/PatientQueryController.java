package com.nutrix.query.api;

import com.nutrix.command.domain.Patient;
import com.nutrix.command.dtos.FavoriteRecipes;
import com.nutrix.command.dtos.Recipe;
import com.nutrix.query.application.services.PatientQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@Api(tags = "Patient", value = "Servicio Web RESTful de Patients")
public class PatientQueryController {

    @Autowired
    private PatientQueryService patientService;
//    @Autowired
//    private RestTemplate template;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Patients", notes = "Método para listar todos los patients")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Patients encontrados"),
            @ApiResponse(code = 404, message = "Patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findAll() {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.getAll();
            if (patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Patient por Id", notes = "Método para encontrar un Patient por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patient encontrado"),
            @ApiResponse(code = 404, message = "patient no encontrado")
    })
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id)
    {
        try{
            Optional<Patient> patient = patientService.getById(id);
            if(!patient.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Patient>(patient.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar patient por username", notes = "Método para encontrar un patient por username")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patient encontrado"),
            @ApiResponse(code = 404, message = "patient no encontrado")
    })
    public ResponseEntity<Patient> findByUsername(@PathVariable("username") String username)
    {
        try{
            Patient patient = patientService.findByUsername(username);
            if(patient == null)
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstName/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Patients por firstname", notes = "Método para encontrar patients por firstname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patients encontrados"),
            @ApiResponse(code = 404, message = "patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByFirstName(@PathVariable("firstname") String firstname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByFirstName(firstname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByLastName/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar patients por lastname", notes = "Método para encontrar patients por lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patients encontrados"),
            @ApiResponse(code = 404, message = "patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByLastName(@PathVariable("lastname") String lastname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByLastName(lastname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchByFirstNameAndLastName/{firstname}/{lastname}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar patients por firstname y lastname", notes = "Método para encontrar patients por firstname y lastname")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patients encontrados"),
            @ApiResponse(code = 404, message = "patients no encontrados")
    })
    public ResponseEntity<List<Patient>> findByFirstNameAndLastName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname)
    {
        try {
            List<Patient> patients = new ArrayList<>();
            patients = patientService.findByFirstNameAndLastName(firstname, lastname);
            if(patients.isEmpty())
                return new ResponseEntity<List<Patient>>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Patient>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}