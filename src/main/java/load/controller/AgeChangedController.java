package load.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class AgeChangedController {

    @Autowired KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/changeAge")
    public void changeAgeAndSendToKafka(@RequestBody Map<String, Object> data) throws JsonProcessingException {
        changeAge(data, 96);
        String dataJson = new ObjectMapper().writeValueAsString(data);
        kafkaTemplate.send("vtbTest", dataJson);
    }

    private void changeAge(Map<String, Object> data, int newAge) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getKey()
                     .equals("age")) {
                entry.setValue(newAge);
            } else if (entry.getValue() instanceof Map) {
                changeAge((Map<String, Object>) entry.getValue(), newAge);
            } else if (entry.getValue() instanceof List) {
                List<Object> list = (List<Object>) entry.getValue();
                for (Object item : list) {
                    if (item instanceof Map) {
                        changeAge((Map<String, Object>) item, newAge);
                    }
                }
            }
        }
    }

}


