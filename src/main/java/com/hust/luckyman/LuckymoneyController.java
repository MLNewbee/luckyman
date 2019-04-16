package com.hust.luckyman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController // 返回json 相当于 @ResponseBody + @Controller
public class LuckymoneyController {

    @Autowired
    private LuckymoneyRepository repository;


    //获取红包列表
    @GetMapping("/luck")
    public List<LuckyMoney> list(){
        return repository.findAll();
    }


    //创建红包
    @PostMapping("/luck")
    public  LuckyMoney create(@RequestParam("producer")String producer, @RequestParam("money")BigDecimal money){
        LuckyMoney luckyMoney = new LuckyMoney();
        luckyMoney.setProducer(producer);
        luckyMoney.setMoney(money);
        return repository.save(luckyMoney);
    }

    //通过id查询红包
    @GetMapping("/luck/{id}")
    public LuckyMoney findById(@PathVariable("id")Integer id){
        return repository.findById(id).orElse(null);
    }

    //更新红包（领红包）
    @PutMapping("/luck/{id}")
    public LuckyMoney update (@PathVariable("id")Integer id,
                              @RequestParam("consumer")String consumer){
        Optional<LuckyMoney> optional = repository.findById(id);
        if(optional.isPresent()){
            LuckyMoney luckyMoney = optional.get();
            luckyMoney.setConsumer(consumer);
            return repository.save(luckyMoney);
        }
        return null;
    }
}
