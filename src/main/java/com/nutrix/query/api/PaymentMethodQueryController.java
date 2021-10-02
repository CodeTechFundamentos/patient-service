package com.nutrix.query.api;

import com.nutrix.command.domain.PaymentMethod;
import com.nutrix.query.application.services.PaymentMethodQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment-method")
@Api(tags="PaymentMethod", value = "Servicio Web RESTFul de PaymentMethod")
public class PaymentMethodQueryController {

    @Autowired
    private PaymentMethodQueryService paymentMethodService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Listar PaymentMethods", notes="Método para listar todos los PaymentMethods")
    @ApiResponses({
            @ApiResponse(code=201, message = "PaymentMethods encontrados"),
            @ApiResponse(code=404, message = "PaymentMethods no encontrados")
    })
    public ResponseEntity<List<PaymentMethod>> findAllPaymentMethod(){
        try{
            List<PaymentMethod> paymentMethods = paymentMethodService.getAll();
            if(paymentMethods.size()>0)
                return new ResponseEntity<List<PaymentMethod>>(paymentMethods, HttpStatus.OK);
            else
                return new ResponseEntity<List<PaymentMethod>>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<List<PaymentMethod>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar PaymentMethod por Id", notes="Método para listar un PaymentMethod por Id")
    @ApiResponses({
            @ApiResponse(code=201, message = "PaymentMethod encontrado"),
            @ApiResponse(code=404, message = "PaymentMethod no encontrado")
    })
    public ResponseEntity<PaymentMethod>findPaymentMethodById(@PathVariable("id") Integer id){
        try{
            Optional<PaymentMethod> paymentMethod= paymentMethodService.getById(id);
            if(!paymentMethod.isPresent())
                return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<PaymentMethod>(paymentMethod.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchPaymentMethodByPatientId/{patient_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Buscar PaymentMethod por patient id", notes = "Método para encontrar PaymentMethod por patient id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "PaymentMethod encontrados"),
            @ApiResponse(code = 404, message = "PaymentMethod no encontrados")
    })
    public ResponseEntity<List<PaymentMethod>> findByPatient(@PathVariable("patient_id") Integer patient_id)
    {
        try{
            List<PaymentMethod> paymentMethods = paymentMethodService.findAllByPatient(patient_id);
            if(paymentMethods.size()>0)
                return new ResponseEntity<List<PaymentMethod>>(paymentMethods, HttpStatus.OK);
            return new ResponseEntity<List<PaymentMethod>>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<List<PaymentMethod>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
