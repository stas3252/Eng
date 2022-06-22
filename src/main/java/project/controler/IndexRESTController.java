package project.controler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexRESTController {
    @GetMapping("")
    public ResponseEntity f() {
        return ResponseEntity.ok("все намана! ура!");
    }
}
