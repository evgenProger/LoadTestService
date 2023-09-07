package load.controller;

import load.storage.Storage;
import load.model.NumberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/api")
    public class NumberController {

        @Autowired private Storage storage;


        @PostMapping("/addNumber")
        public String addNumber(@RequestBody NumberRequest numberRequest) {
            int number = numberRequest.getNumber();

            if (isFiveDigitNumber(number)) {
                storage.put(number);
                return "Число " + number + " успешно добавлено в массив.";
            } else {
                return "Ошибка: Введите пятизначное число.";
            }
        }

        @GetMapping("/getNumber")
        public String getNumber() {
           return  storage.getNumbers().toString();

        }


        private boolean isFiveDigitNumber(int number) {
            return number >= 10000 && number <= 99999;
        }
    }


