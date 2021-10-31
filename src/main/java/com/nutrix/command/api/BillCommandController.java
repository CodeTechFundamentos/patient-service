package com.nutrix.command.api;

import com.nutrix.command.application.services.BillCommandService;
import com.nutrix.command.domain.Bill;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/bill")
@Api(tags="Bill", value = "Servicio Web RESTFul de Bill")
public class BillCommandController {

    @Autowired
    private BillCommandService billService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un Bill de un Patient", notes = "Método que registra un Bill")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Bill creado"),
            @ApiResponse(code = 404, message = "Bill no creado")
    })
    public ResponseEntity<Bill> insertBill(@Valid @RequestBody Bill bill) {
        try {
            Bill billNew = billService.save(bill);
            return ResponseEntity.status(HttpStatus.CREATED).body(billNew);
        } catch (Exception e) {
            return new ResponseEntity<Bill>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Actualización de datos de Bill", notes = "Método que actualizar los datos de Bill")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Bill actualizados"),
            @ApiResponse(code = 404, message = "Bill no actualizado")
    })
    public ResponseEntity<Bill> updateBill(@PathVariable("id") Integer id, @Valid @RequestBody Bill bill) {
        try {
            Optional<Bill> billOld = billService.getById(id);
            if (!billOld.isPresent())
                return new ResponseEntity<Bill>(HttpStatus.NOT_FOUND);
            else {
                bill.setId(id);
                billService.save(bill);
                return new ResponseEntity<Bill>(bill, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<Bill>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Eliminación de Bill por Id", notes = "Método que elimina los datos de un Bill")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Datos de Bill eliminados"),
            @ApiResponse(code = 404, message = "Bill no eliminados")
    })
    public ResponseEntity<Bill> deleteBill(@PathVariable("id") Integer id) {
        try {
            Optional<Bill> billDelete = billService.getById(id);
            if (billDelete.isPresent()) {
                billService.delete(id);
                return new ResponseEntity<Bill>(HttpStatus.OK);
            } else
                return new ResponseEntity<Bill>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Bill>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}