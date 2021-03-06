package ar.edu.unq.desapp.grupoh.webservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoh.dto.ClientPlatformDTO;
import ar.edu.unq.desapp.grupoh.service.ClientPlatformService;
import ar.edu.unq.desapp.grupoh.service.LogInRequestBody;

@RestController
public class ClientPlatformController {
	@Autowired
	private ClientPlatformService clientService;
	private final String commonPath = "/api/clientplatforms";
	
	@CrossOrigin
    @PostMapping(commonPath)
    public ResponseEntity<Map<String, String>> createAndSaveNewClientPlatform(@RequestBody ClientPlatformDTO requestBody) {
		String apiKey = this.clientService.add(requestBody);
		Map<String, String> resMap = new HashMap<String, String>();
		resMap.put("apiKey", apiKey);
		return ResponseEntity.status(HttpStatus.CREATED).body(resMap);
	}
	
	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<String> validCredentials(@RequestBody LogInRequestBody requestBody) {
		return ResponseEntity.ok(this.clientService.validateCredentials(requestBody));
	}
}
