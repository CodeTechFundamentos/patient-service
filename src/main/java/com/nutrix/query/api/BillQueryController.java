package com.nutrix.query.api;

import com.nutrix.command.domain.Bill;
import com.nutrix.query.application.services.BillQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bill")
@Api(tags="Bill", value = "Servicio Web RESTFul de Bill")
public class BillQueryController {

    @Autowired
    private BillQueryService billService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar Bills", notes = "Método para listar todos los Bills")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Bills encontrados"),
            @ApiResponse(code = 404, message = "Bills no encontrados")
    })
    public ResponseEntity<List<Bill>> findAllBill() {
        try {
            List<Bill> bills = billService.getAll();
            if (bills.size() > 0)
                return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
            else
                return new ResponseEntity<List<Bill>>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<List<Bill>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Bill por Id", notes = "Método para listar un Bill por Id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Bill encontrado"),
            @ApiResponse(code = 404, message = "Bill no encontrado")
    })
    public ResponseEntity<Bill> findBillById(@PathVariable("id") Integer id) {
        try {
            Optional<Bill> bill = billService.getById(id);
            if (!bill.isPresent())
                return new ResponseEntity<Bill>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Bill>(bill.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Bill>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchBillByPatientId/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Bill por patient id", notes = "Método para encontrar Bill por patient id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Bill encontrados"),
            @ApiResponse(code = 404, message = "Bill no encontrados")
    })
    public ResponseEntity<List<Bill>> findByPatient(@PathVariable("patient_id") Integer patient_id) {
        try {
            List<Bill> bills = billService.findAllByPatient(patient_id);
            if (bills.size() > 0)
                return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
            return new ResponseEntity<List<Bill>>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Bill>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchBetweenDates", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar Bill entre fechas", notes = "Método para listar Bill entre fechas")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Bill encontrados"),
            @ApiResponse(code = 404, message = "Bill no encontrados")
    }) //Al requestparam le puedes decir que sea opcional y no necesita estar en el URL
    public ResponseEntity<List<Bill>> findBillByBillDateBetweenDates(@RequestParam("date1") String date1_string,
                                                                     @RequestParam("date2") String date2_string) {
        try {
            Date checking_date = ParseDate(date1_string);
            Date checkout_date = ParseDate(date2_string);
            List<Bill> bills = billService.findBetweenDates(checking_date, checkout_date);
            if (bills != null && bills.size() > 0)
                return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
            else
                return new ResponseEntity<List<Bill>>(bills, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<List<Bill>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static Date ParseDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try {
            result = format.parse(date);
        } catch (Exception e) {
        }
        return result;
    }
}