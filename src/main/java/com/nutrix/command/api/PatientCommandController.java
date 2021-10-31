package com.nutrix.command.api;

import com.nutrix.command.application.services.PatientCommandService;
import com.nutrix.command.domain.Patient;
import com.nutrix.command.dtos.FavoriteRecipes;
import com.nutrix.command.dtos.Recipe;
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
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@Api(tags = "Patient", value = "Servicio Web RESTful de Patients")
public class PatientCommandController {

    @Autowired
    private PatientCommandService patientService;
    @Autowired
    private RestTemplate template;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de patients", notes = "Método para agregar un patient")
    @ApiResponses({
            @ApiResponse(code = 201, message = "patient agregado"),
            @ApiResponse(code = 404, message = "patient no fue agregado")
    })
    public ResponseEntity<Patient> insertCustomer(@Valid @RequestBody Patient patient)
    {
        try{
            Patient newPatient = patientService.save(patient);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de patient", notes = "Método que actualiza los datos de un patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "patient actualizado"),
            @ApiResponse(code = 404, message = "patient no fue encontrado")
    })
    public ResponseEntity<Patient> updatePatient(
            @PathVariable("id") Integer id, @Valid @RequestBody Patient patient){
        try {
            Optional<Patient> foundPatient = patientService.getById(id);
            if(!foundPatient.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            patient.setId(id);
            patientService.save(patient);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de un patient", notes = "Método para eliminar un patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "patient eliminado"),
            @ApiResponse(code = 404, message = "patient no encontrado")
    })
    public ResponseEntity<Patient> deleteCustomer(@PathVariable("id") Integer id)
    {
        try{
            Optional<Patient> deletedCustomer = patientService.getById(id);
            if(!deletedCustomer.isPresent())
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            patientService.delete(id);
            return new ResponseEntity<Patient>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/{recipe_id}/{patient_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Adición de Recipe favorita a la lista de favoritos de un patient", notes = "Método que añade una Recipe favorita a la lista de favoritos de un patient")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Recipe añadida a la lista de favoritos del patient"),
            @ApiResponse(code = 404, message = "Recipe o patient no encontrado")
    })
    public ResponseEntity<FavoriteRecipes> addFavoriteRecipe(@PathVariable("recipe_id") Integer recipe_id,
                                                             @PathVariable("patient_id") Integer patient_id){
        try {
            Recipe foundRecipe = template.getForObject("http://localhost:8989/recipe/{recipe_id}", Recipe.class, recipe_id);
            Optional<Patient> foundPatient = patientService.getById(patient_id);
            if(!foundPatient.isPresent())
                return new ResponseEntity<FavoriteRecipes>(HttpStatus.NOT_FOUND);
            if(foundRecipe == null)
                return new ResponseEntity<FavoriteRecipes>(HttpStatus.NOT_FOUND);

            FavoriteRecipes patientFavoriteRecipes = new FavoriteRecipes();
            patientFavoriteRecipes.setPatientId(foundPatient.get().getId());
            patientFavoriteRecipes.setRecipe(foundRecipe);

            FavoriteRecipes patientFavoriteRecipesSaved = new FavoriteRecipes();

            patientFavoriteRecipesSaved = template.postForObject("http://localhost:8989/favorite-recipes", patientFavoriteRecipes, FavoriteRecipes.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(patientFavoriteRecipesSaved);
        }catch (Exception e){
            return new ResponseEntity<FavoriteRecipes>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //move to pub
//    @GetMapping(value = "/findpatientFavoriteRecipes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Buscar Recipes favoritos de un patient", notes = "Método para listar Recipes favoritos de un patients")
//    @ApiResponses({
//            @ApiResponse(code = 201, message = "Recipes encontrados"),
//            @ApiResponse(code = 404, message = "Recipes no encontrados")
//    })
//    public ResponseEntity<Recipe[]> findPatientFavoriteRecipes(@PathVariable("id") Integer id)
//    {
//        try {
//            Recipe[] recipes = template.getForObject("http://localhost:8989/recipe/", Recipe[].class);
//            //recipes = patientFavoriteRecipesRepository.findByPatient(id);
//            if(recipes == null)
//                return new ResponseEntity<Recipe[]>(HttpStatus.NOT_FOUND);
//            return new ResponseEntity<Recipe[]>(recipes, HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<Recipe[]>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    //move to publications
//    @DeleteMapping(value = "/{recipe_id}/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Eliminación de un Recipe de la lista de favoritos de un patient", notes = "Método para eliminar un Recipe de la lista de favoritos de un patient")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Recipe eliminado"),
//            @ApiResponse(code = 404, message = "Recipe no encontrado")
//    })
//    public ResponseEntity<Patient> deletePatientFavoriteRecipe(@PathVariable("recipe_id") Integer recipe_id,
//                                                             @PathVariable("patient_id") Integer patient_id)
//    {
//        try{
//
//            FavoriteRecipes favoriteRecipes = new FavoriteRecipes();
//            Optional<Patient> foundPatient = patientService.getById(patient_id);
//            favoriteRecipes.setPatientId(foundPatient.get().getId());
//
//            if(!foundPatient.isPresent())
//                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
//
//            Recipe foundRecipe = template.getForObject("http://localhost:8989/recipe/{recipe_id}", Recipe.class, recipe_id);
//            favoriteRecipes.setRecipe(foundRecipe);
//
//            if(foundRecipe == null)
//                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
//
//            if(favoriteRecipes == null)
//                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
//
//            //patientFavoriteRecipesRepository.delete(patientFavoriteRecipes.get());
//
//            Integer favoriteRecipesToDeleteId = favoriteRecipesRepository.findByFavoriteRecipe(favoriteRecipes.getRecipe().getId(), favoriteRecipes.getPatientId();
//
//            favoriteRecipesRepository.deleteById(favoriteRecipesToDeleteId);
//
//            return new ResponseEntity<Patient>(HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}