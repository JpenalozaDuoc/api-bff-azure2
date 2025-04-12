package bffapi.bff.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class BffController {

    // Inyecta las URLs de las Azure Functions (puedes cambiarlas si las mueves a application.properties)
    @Value("${azure.function.testOracleConnectionUrl}")
    private String testOracleConnectionUrl;
    
    @Value("${azure.function.getAllRolesUrl}")
    private String getAllRolesUrl;
    
    /*@Value("${azure.function.getAllUsersUrl}")
    private String getAllUsersUrl;
    */

    @Value("${azure.function.updateRoleUrl}")
    private String updateRoleUrl;
    
    @Value("${azure.function.deleteRoleUrl}")
    private String deleteRoleUrl;
    
    @Value("${azure.function.createRoleUrl}")
    private String createRoleUrl;


    private final RestTemplate restTemplate;

    public BffController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Endpoint que llama a la función de conexión a Oracle
     
    /*@GetMapping("/testOracleConnection")
    public ResponseEntity<?> testOracleConnection() {
        try {
            return restTemplate.getForEntity(testOracleConnectionUrl, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al verificar conexión con Oracle.");
        }
    }*/
    
    // Endpoint que llama a la función que obtiene todos los roles
    @GetMapping("/getAllRoles")
    public ResponseEntity<?> getAllRoles() {
        try {
            return restTemplate.getForEntity(getAllRolesUrl, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener roles.");
        }
    }

     // Endpoint para actualizar un rol
    @PutMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(@PathVariable("id") String id, @RequestBody String roleData) {
        try {
            // Crear la URL para la solicitud PUT
            String url = updateRoleUrl.replace("{id}", id);
            // Crear un HttpEntity con el body roleData
            HttpEntity<String> entity = new HttpEntity<>(roleData, new HttpHeaders());
            // Realizar la solicitud PUT a la Azure Function
            return restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el rol.");
        }
    }

    // Endpoint para eliminar un rol
    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") String id) {
        try {
            // Crear la URL para la solicitud DELETE
            String url = deleteRoleUrl.replace("{id}", id);
            // Realizar la solicitud DELETE a la Azure Function
            return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el rol.");
        }
    }

    // Endpoint para crear un rol
    @PostMapping("/createRole")
    public ResponseEntity<?> createRole(@RequestBody String roleData) {
        try {
            // Realizar la solicitud POST a la Azure Function
            return restTemplate.exchange(createRoleUrl, HttpMethod.POST, new HttpEntity<>(roleData), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el rol.");
        }
    }



    // Endpoint que llama a la función que obtiene todos los usuarios
    /*@GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        try {
            return restTemplate.getForEntity(getAllUsersUrl, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener usuarios.");
        }
    }*/
}
